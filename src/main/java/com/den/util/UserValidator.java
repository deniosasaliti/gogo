package com.den.util;




import com.den.Entity.User;
import com.den.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator  implements Validator {

    @Autowired
    UserService userService;

    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {


        User user = (User) o;
        if (userService.getByEmail(user.getEmail()) != null){
            errors.rejectValue("email","","this email is already use");
        }else return;

    }
}
