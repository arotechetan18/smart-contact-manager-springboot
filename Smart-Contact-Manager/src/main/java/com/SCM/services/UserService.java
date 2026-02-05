package com.SCM.services;

import java.util.List;
import java.util.Optional;

import com.SCM.entities.User;
import com.SCM.forms.UserForms;

public interface UserService {

    User saveUser(UserForms userForms);
  Optional<User> getUserById(String userId);
    Optional<User>  updateUser(User user);
    User deleteUser(String userId);
    boolean isUserExists(String userId);
    boolean isUserExistsByEmail(String email);
    List<User> getAllUsers();
    User saveUser(User user);
   
    User getUserByEmail(String email);

    //we can add more user-related services here as needed 

}
