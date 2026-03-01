package com.SCM.implementation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.SCM.entities.User;
import com.SCM.forms.UserForms;
import com.SCM.helpers.AppConstant;
import com.SCM.helpers.ResourceNotFoundException;
import com.SCM.repositories.Userepo;
import com.SCM.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private Userepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public User saveUser(User user) {

        //user id generate
     String userId = UUID.randomUUID().toString();
    user.setUserid(userId);
     //password encoder
    //  user.setPassword(userId);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
     
    //set user role
        user.setRoleList(List.of(AppConstant.ROLE_USER));
    logger.info(user.getProvider().toString());
      return  userRepo.save(user);
    }

    @Override
    public Optional<User> getUserById(String userId) {
        
        return userRepo.findById(userId);
    }

    @Override
    public Optional<User>  updateUser(User user) {
    User user2=  userRepo.findById(user.getUserid()).orElseThrow(()->new ResourceNotFoundException());
   //update user2 from user
    user2.setName( user.getName());
    user2.setEmail( user.getEmail());
    user2.setPassword( user.getPassword());
    user2.setAbout( user.getAbout());
    user2.setPhoneNumber( user.getPhoneNumber());
    user2.setProfilePic( user.getProfilePic());
    user2.setEnabled(user.isEnabled());
    user2.setEmailVerified(user.isEmailVerified());
    user2.setPhoneVerified(user.isPhoneVerified());
    user2.setProvider(user.getProvider());
    user2.setProvideUserId( user.getProvideUserId());

    //save the updated user in database
    User save = userRepo.save(user2);

    return Optional.ofNullable(save);
    
    }

    @Override
    public User deleteUser(String userId) {
        User user=  userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException());
      userRepo.delete(user);
      return user;
      
    }

    @Override
    public boolean isUserExists(String userId) {
       User user2=userRepo.findById(userId).orElse(null);

       return user2 !=null?true:false;  
    }

    @Override
    public boolean isUserExistsByEmail(String email) {
     User user= userRepo.findByEmail(email).orElse(null);
        return user !=null?true:false;  
    }

    @Override
    public List<User> getAllUsers() {
       return userRepo.findAll();
    }

    @Override
    public User saveUser(UserForms userForms) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveUser'");
    }
@Override
public User getUserByEmail(String email) {

    Optional<User> optionalUser = userRepo.findByEmail(email);

    if (optionalUser.isPresent()) {
        return optionalUser.get();
    }

    // If user not found, create new OAuth user
    User newUser = new User();
    newUser.setUserid(UUID.randomUUID().toString());
    newUser.setEmail(email);
    newUser.setName(email); // or extract proper name
    newUser.setPassword(passwordEncoder.encode("default123"));
    newUser.setRoleList(List.of(AppConstant.ROLE_USER));
    newUser.setEnabled(true);
    newUser.setEmailVerified(true);

    logger.info("New OAuth user created: " + email);

    return userRepo.save(newUser);
}
    

}
