package com.tpe.controller;

import com.tpe.domain.Student;
import com.tpe.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller // here we are saying this class will be in controller layer it has @Component inside it.
@RequestMapping("/students") //requests that started with "/student" end point will be handled here
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/hi") // "/student/hi"
    public ModelAndView sayHi(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", "Hello");
        mav.addObject("messagebody", "I am Student management System");
        mav.setViewName("hi");
        return mav;
    }

    //This method will display studentForm.jsp on the browser
    //students/new is to call this method.
    @GetMapping("/new")
    public String sendStudentForm(@ModelAttribute("student")Student student){
        return "studentForm";
    }

    @PostMapping("/saveStudent")
    public String createStudent(@Valid @ModelAttribute Student student){// Here @Valid is coming from javax validation to check if records valid.
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping
    public ModelAndView getStudents(){
        List<Student> list = studentService.getAllStudents();
        ModelAndView mav = new ModelAndView();
        mav.addObject("students", list);
        mav.setViewName("students");
        return mav;
    }

    // students/update
    @GetMapping("/update")
    public String showFormForUpdate(@RequestParam("id") Long id, Model model){
        Student student = studentService.findStudentById(id);
        model.addAttribute(student);
        return "/studentForm";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id){
        studentService.deleteStudent(id);
        return "redirect:/students";
    }

}