package com.logan.movie_review.controllers;


import com.logan.movie_review.dao.RoleDao;
import com.logan.movie_review.dao.UserDao;
import com.logan.movie_review.models.Role;
import com.logan.movie_review.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(name = "/signup" , method = RequestMethod.GET)
    public String signupForm(Model model){
        model.addAttribute("user", new User());
        return "signup";
    }
    @RequestMapping(name = "/signup" , method = RequestMethod.POST)
    public String signupForm(@ModelAttribute User user){
        Role userRole = roleDao.findByName("ROLE_USER");
        String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        user.setRole(userRole);
        user.setActive(true);
        userDao.save(user);
        return "redirect:/";
    }

}