package com.den.controller;
import com.den.Entity.User;
import com.den.service.UserService;
import com.den.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.jws.soap.SOAPBinding;
import javax.validation.Valid;
import java.util.ArrayList;

@Controller
public class AuthController {
    ArrayList<User> users  = new ArrayList<>();

    final
    UserValidator userValidator;
    final
    UserService userService;

    public AuthController(UserValidator userValidator, UserService userService) {
        this.userValidator = userValidator;
        this.userService = userService;
    }

    @GetMapping("/sing_up")
    public String createUserPage (Model model){
        model.addAttribute("user",new User());
        return "sing_up";
    }
    @GetMapping("/registration")
    public String registration(){
        return "kk";
    }

    @PostMapping("/registration")
    public String getRegistration(@ModelAttribute User user) {
        users.add(user);
        return "redirect:/kk2";
    }

    @GetMapping("/kk2")
    public String kk2(Model model){
        model.addAttribute("users", users);
        return "kk2";
    }



    @PostMapping("/sing_up")
    public  String addUser(@ModelAttribute @Valid User user, BindingResult result){
        userValidator.validate(user,result);
        if (result.hasErrors()){
            return "sing_up";
        }else userService.save(user);
        return "redirect:/users";
    }



    @RequestMapping("/login")
    public String login(@RequestParam (name = "error",required = false) Boolean error,
                        Model model){
        if (Boolean.TRUE.equals(error)){
            model.addAttribute("error",true);
        }
        return "sing_in";
    }


}
