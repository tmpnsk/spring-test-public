package com.springtest.controller;

import com.springtest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller

public class UserProfileController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/profile/{id}", method = RequestMethod.GET)
    public ModelAndView profile(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("profile/userProfile.jsp");
		modelAndView.addObject("user", userService.getOne(id));

        return modelAndView;
    }

    @RequestMapping(value = "/profile/{id}", method = RequestMethod.POST)
    public String saveProfile(@RequestParam(value = "validPassword" ,required=false) String validPassword,
                        @RequestParam(value = "newPassword" ,required=false) String newPassword,
                        @PathVariable("id") Long id, RedirectAttributes ra) {

        if (userService.updatePassword(newPassword, validPassword, id)) {
            ra.addFlashAttribute("message", "New password save");
        } else {
            ra.addFlashAttribute("message", "Password valid error");
        }
        return "redirect:/profile/" + Long.toString(id);
    }
}
