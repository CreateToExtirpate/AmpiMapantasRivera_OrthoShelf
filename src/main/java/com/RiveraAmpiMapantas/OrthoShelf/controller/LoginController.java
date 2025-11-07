package com.RiveraAmpiMapantas.OrthoShelf.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.RiveraAmpiMapantas.OrthoShelf.domain.userEntity.Login;
import com.RiveraAmpiMapantas.OrthoShelf.service.LoginService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/")
    public String rootRedirect() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("user", new Login());
        return mav;
    }

    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute("user") Login user) {

        Login oauthUser = loginService.login(user.getUsername(), user.getPassword());

        if (Objects.nonNull(oauthUser)) {
            System.out.println("Login Controller User found: " + oauthUser.getUsername());
            System.out.println("Redirecting to index page");
            return new ModelAndView("redirect:/index");

        } else {
            ModelAndView mav = new ModelAndView("login");
            mav.addObject("user", new Login());
            mav.addObject("error", "Invalid username or password");
            return mav;
        }
    }

    @RequestMapping(value = {"/logout"}, method = RequestMethod.POST)
    public String logoutDo(HttpServletRequest request, HttpServletResponse response) {
        return"redirect:/login";
    }

}
