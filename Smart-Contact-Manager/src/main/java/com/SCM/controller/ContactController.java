package com.SCM.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.SCM.entities.Contact;
import com.SCM.entities.User;
import com.SCM.forms.ContactSearchForm;
import com.SCM.forms.Contactform;
import com.SCM.helpers.AppConstant;
import com.SCM.helpers.Helper;
import com.SCM.helpers.MessageType;
import com.SCM.helpers.message;
import com.SCM.services.ContactService;
import com.SCM.services.ImageService;
import com.SCM.services.UserService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user/contacts")
public class ContactController {

    private Logger logger = org.slf4j.LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ImageService imageService;

    @Autowired
    public ContactService contactService;

    @Autowired
    private UserService userService;

    private Page<Contact> searchByName;

    // add contact page
    @RequestMapping("/add")
    public String addContactView(Model model) {

        Contactform contactForm = new Contactform();

        model.addAttribute("contactForm", contactForm);

        return "user/add_contact";
    }

    @PostMapping("/add")

    public String saveContact(@Valid @ModelAttribute("contactForm") Contactform contactForm, BindingResult result,
            Authentication authentication, HttpSession session, Model model) {

        // process the form data
        String filename = UUID.randomUUID().toString();

        String username = Helper.getEmailofLoggedinUser(authentication);

        // validate the form
        if (result.hasErrors()) {

            User user = userService.getUserByEmail(username);
            model.addAttribute("loggedInUser", user);

            session.setAttribute("message", message.builder()
                    .content("please correct the following errors")
                    .type(MessageType.red)
                    .build());

            return "user/add_contact";
        }

        // form covert into contact

        User user = userService.getUserByEmail(username);

        // image processing
        // to upload
        String fileURL = imageService.uploadImage(contactForm.getContactImage(), filename);

        Contact contact = new Contact();
        contact.setName(contactForm.getName());
        contact.setFavourite(contactForm.isFavourite());
        contact.setEmail(contactForm.getEmail());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setAddress(contactForm.getAddress());
        contact.setDescription(contactForm.getDescription());
        contact.setUser(user);
        contact.setWebsiteLink(contactForm.getWebsiteLink());
        contact.setLinkdInLink(contactForm.getLinkedInLink());
        contact.setPicture(fileURL);
        contact.setCloudinaryImagePublicId(filename);

        contactService.save(contact);

        System.out.println(contactForm);

        session.setAttribute("message", message.builder()
                .content("You have successfully saved contact.")
                .type(MessageType.green)
                .build()

        );

        return "redirect:/user/contacts/add";
    }

    @RequestMapping
    public String viewContacts(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", required = false) Integer size,
            @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            Model model, Authentication authentication) {
        if (size == null || size <= 0) {
            size = 5;
        }

        String username = Helper.getEmailofLoggedinUser(authentication);

        User user = userService.getUserByEmail(username);
        // load all the coanatct
        Page<Contact> pageContact = contactService.getByUser(user, page, size, sortBy, direction);

        model.addAttribute("pageContact", pageContact);
        model.addAttribute("contactSearchForm", new ContactSearchForm());
        model.addAttribute("isFavouritePage", false);
        return "user/contacts";
    }

    // serch handler

    @RequestMapping("/search")
    public String searchHandler(
            @ModelAttribute ContactSearchForm contactSearchForm,
            @RequestParam(value = "size", defaultValue = AppConstant.PAGE_SIZE + "") int size,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            Model model,
            Authentication authentication) {
        if (contactSearchForm.getField() == null ||
                contactSearchForm.getField().isEmpty()) {
            return "redirect:/user/contacts";
        }

        logger.info("field{} keyword {}", contactSearchForm.getField(), contactSearchForm.getValue());

        var user = userService.getUserByEmail(Helper.getEmailofLoggedinUser(authentication));

        Page<Contact> pageContact = null;
        if (contactSearchForm.getField().equalsIgnoreCase("name")) {
            pageContact = contactService.searchByName(contactSearchForm.getValue(), size, page, sortBy, direction,
                    user);
        } else if (contactSearchForm.getField().equalsIgnoreCase("email")) {
            pageContact = contactService.searchByEmail(contactSearchForm.getValue(), size, page, sortBy, direction,
                    user);
        } else if (contactSearchForm.getField().equalsIgnoreCase("phone")) {
            pageContact = contactService.searchByphoneNumber(contactSearchForm.getValue(), size, page, sortBy,
                    direction, user);
        }
        logger.info("pageContact {}", pageContact);
        model.addAttribute("contactSearchForm", contactSearchForm);

        model.addAttribute("pageContact", pageContact);
        model.addAttribute("pagesize", AppConstant.PAGE_SIZE);
        return "user/search";

    }

    // delete conatct
    @RequestMapping("/delete/{contactId}")
    public String deletContact(@PathVariable("contactId") String contactId) {

        contactService.delete(contactId);
        return "redirect:/user/contacts";
    }

    // view the old contact
    @GetMapping("/view/{contactId}")
    public String updateContactfromView(@PathVariable String contactId, Model model) {

        Contact contact = contactService.getbyid(contactId);

        Contactform contactform = new Contactform();
        contactform.setName(contact.getName());
        contactform.setEmail(contact.getEmail());
        contactform.setPhoneNumber(contact.getPhoneNumber());
        contactform.setAddress(contact.getAddress());
        contactform.setDescription(contact.getDescription());
        contactform.setFavourite(contact.isFavourite());
        contactform.setWebsiteLink(contact.getWebsiteLink());
        contactform.setLinkedInLink(contact.getLinkdInLink());
        contactform.setPicture(contact.getPicture());

        model.addAttribute("contactForm", contactform);
        model.addAttribute("contactId", contactId);

        return "user/update_contact_view";
    }

    // update contact from view
    @PostMapping("/update/{contactId}")
    public String updateContact(
            @PathVariable String contactId,
            @ModelAttribute("contactForm") Contactform contactForm,
            Authentication authentication,
            HttpSession session) {

        Contact existingContact = contactService.getbyid(contactId);

        if (existingContact == null) {
            return "redirect:/user/contacts";
        }

        existingContact.setName(contactForm.getName());
        existingContact.setEmail(contactForm.getEmail());
        existingContact.setPhoneNumber(contactForm.getPhoneNumber());
        existingContact.setAddress(contactForm.getAddress());
        existingContact.setDescription(contactForm.getDescription());
        existingContact.setFavourite(contactForm.isFavourite());
        existingContact.setWebsiteLink(contactForm.getWebsiteLink());
        existingContact.setLinkdInLink(contactForm.getLinkedInLink());

        // image update
        if (contactForm.getContactImage() != null &&
                !contactForm.getContactImage().isEmpty()) {

            String filename = UUID.randomUUID().toString();
            String fileURL = imageService.uploadImage(contactForm.getContactImage(), filename);

            existingContact.setPicture(fileURL);
            existingContact.setCloudinaryImagePublicId(filename);
        }

        contactService.save(existingContact);

        session.setAttribute("message",
                message.builder()
                        .content("Contact Updated Successfully")
                        .type(MessageType.green)
                        .build());

        return "redirect:/user/contacts";
    }

    //favourite contact page

    @GetMapping("/favourites")
    public String viewFavouriteContacts(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", required = false) Integer size,
            @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            Model model,
            Authentication authentication) {

        if (size == null || size <= 0) {
            size = 5;
        }

        String username = Helper.getEmailofLoggedinUser(authentication);
        User user = userService.getUserByEmail(username);

        Page<Contact> pageContact = contactService.getFavouriteByUser(user, page, size, sortBy, direction);

        model.addAttribute("pageContact", pageContact);
        model.addAttribute("contactSearchForm", new ContactSearchForm());
        model.addAttribute("title", " Favourite Contacts");
        model.addAttribute("isFavouritePage", true);

        return "user/contacts";
    }

    // export page
    @GetMapping("/export-page")
    public String exportPage() {
        return "user/export_contacts";
    }

   // export contacts as csv file
    @GetMapping("/export")
    public void exportContacts(HttpServletResponse response,
            Authentication authentication) throws IOException {

        // only logged in user can export their contacts
        String username = Helper.getEmailofLoggedinUser(authentication);
        User user = userService.getUserByEmail(username);

        // Get ALL contacts
        List<Contact> contacts = contactService.getAllByUser(user);

        // CSV response
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition",
                "attachment; filename=all_contacts.csv");

        PrintWriter writer = response.getWriter();

        // csv Header
        writer.println("Name,Email,Phone,Favourite");

        for (Contact c : contacts) {

            writer.println(
                    (c.getName() != null ? c.getName() : "") + "," +
                            (c.getEmail() != null ? c.getEmail() : "") + "," +
                            (c.getPhoneNumber() != null ? c.getPhoneNumber() : "") + "," +
                            c.isFavourite());
        }

        writer.flush();
        writer.close();
    }

}
