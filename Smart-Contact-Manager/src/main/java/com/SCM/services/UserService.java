package com.SCM.services;

import java.util.List;
import java.util.Optional;

import com.SCM.entities.User;
import com.SCM.forms.UserForms;

public interface UserService {
//save user
    User saveUser(UserForms userForms);
    //get user
  Optional<User> getUserById(String userId);
// update user
    Optional<User>  updateUser(User user);
    //delete user
    User deleteUser(String userId);
    //check user exists
    boolean isUserExists(String userId);
    boolean isUserExistsByEmail(String email);
    //get all user
    List<User> getAllUsers();
    
    User saveUser(User user);
   //get user by email
    User getUserByEmail(String email);
    

  

}
