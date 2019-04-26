package com.springtest.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth!= null && auth.getPrincipal() != "anonymousUser") {
            return "redirect:/";
        }
        return "login.jsp";
    }

    @RequestMapping(value = "/login_failure")
    public String loginFailure(Model model) {
        model.addAttribute("message", "Invalid login or password");
        return "login.jsp";
    }
}
