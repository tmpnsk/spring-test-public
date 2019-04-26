package com.springtest.controller;

import com.springtest.model.User;
import com.springtest.repo.NewsRepository;
import com.springtest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Qualifier("newsRepository")
    @Autowired
    private NewsRepository newsRepository;

    @RequestMapping( method = RequestMethod.GET)
    public ModelAndView userView() {
        ModelAndView modelAndView = new ModelAndView("user/userView.jsp");
        List<User> users = userService.getAll();
        modelAndView.addObject("userName", users);
        return modelAndView;
    }

    @RequestMapping(value ="/add", method=RequestMethod.GET)
    public ModelAndView userAdd (){
        return new ModelAndView("user/userAddForm.jsp");
    }

    @RequestMapping(value ="/add", method=RequestMethod.POST)
    public String userAdd (HttpServletRequest request){
        userService.saveRecordFromRequest(request);
        return "redirect:/user";
    }
    @RequestMapping(value ="/edit/{id}", method=RequestMethod.GET)
    public ModelAndView userEdit (@PathVariable("id") Long id){
        ModelAndView modelAndView = new ModelAndView("user/userEdit.jsp");
        modelAndView.addObject("userName",userService.getOne(id));
        modelAndView.addObject("userNews",newsRepository.findByUser(id));

        return modelAndView;
    }
    @RequestMapping(value ="/edit/{id}", method=RequestMethod.POST)
    public String userEdit(@PathVariable("id") Long id,HttpServletRequest request){
        userService.saveEditFromRequest(request);
        return "redirect:/user";
    }
    @RequestMapping(value ="/delete/{id}", method=RequestMethod.GET)
    public String userDelete(@PathVariable("id") Long id){
        userService.delete(id);
        return"redirect:/user";
    }

}
