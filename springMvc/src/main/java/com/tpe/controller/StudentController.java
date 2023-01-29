package com.tpe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/student") //requests that started with "/student" end point will be handled here
public class StudentController {

    @GetMapping("/hi") // "/student/hi"
    public ModelAndView sayHi(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", "Hello");
        mav.addObject("messagebody", "I am Student management System");
        mav.setViewName("hi");
        return mav;
    }

}