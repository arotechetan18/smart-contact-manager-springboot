//conatct modal
const BaseURL="http://localhost:8081";
document.addEventListener("DOMContentLoaded", function () {
  console.log("contact js loaded");

  const viewContactModal = document.getElementById("view_contact_modal");

  const options = {
    placement: "center",
    backdrop: "dynamic",
    backdropClasses: "bg-gray-900/50 fixed inset-0 z-40",
    closable: true,
  };

  const instanceOptions = {
    id: "view_contact_modal",
    override: true,
  };

  const contactModal = new Modal(viewContactModal, options, instanceOptions);

  window.loadContactdata = async function (id) {
    try {
      

      const response = await fetch(`/api/contacts/${id}`);

      if (!response.ok) {
        throw new Error("Failed to fetch contact");
      }

      const data = await response.json();
      console.log(data);

      
      document.getElementById("Contact_Name").innerText = data.name || "";

     
      document.getElementById("Contact_Email").innerText = data.email || "";

     
      document.getElementById("Contact_phoneNumber").innerText =
        data.phoneNumber || "";
    
      document.getElementById("Contact_Address").innerText =
        data.address || "Not Available";

    
      document.getElementById("Contact_Description").innerText =
        data.description || "No Description";

 
      document.getElementById("Contact_Image").src =
        data.picture && data.picture.trim() !== ""
          ? data.picture
          : "https://via.placeholder.com/150";

      document.getElementById("Contact_Website").href =
        data.websiteLink && data.websiteLink.trim() !== ""
          ? data.websiteLink
          : "#";

   document.getElementById("Contact_Linkedin").href =
  data.linkedInLink && data.linkedInLink.trim() !== ""
    ? data.linkedInLink
    : "#";

    
      const fav = document.getElementById("Contact_Favourite");
      if (data.favourite === true) {
        fav.classList.remove("hidden");
      } else {
        fav.classList.add("hidden");
      }

      contactModal.show();
    } catch (error) {
      console.error("Error:", error);
      alert("Unable to load contact details");
    }
  };
});

//delete contact
async function deleteContact(id) {
Swal.fire({
  title: "Do you want to delete the Contact ?",
  icon:"warning",
  showCancelButton: true,
  confirmButtonText: "Delete",

}).then((result) => {
  if (result.isConfirmed) {
    Swal.fire("Contact Deleted!", "", "success");
    const url=  `${BaseURL}/user/contacts/delete/`+id;
    window.location.replace(url);
  } 
});
  
}