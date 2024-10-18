package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService){
        this.employeeService=employeeService;
    }

    // add mapping for list<Employees>
    @GetMapping("/list")
    public String listEmployees (Model model){
        // get employees
        List<Employee> employees=employeeService.findAll();
        // addto spring model

        model.addAttribute("employees",employees);
        return "/employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){

        // create model attribute to bind form data
        Employee emp = new Employee();
        model.addAttribute("employee",emp);

        return "employees/employee-form.html";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("empId")int id, Model model){

        // get employee from service
        Employee emp = employeeService.findById(id);

        // set employee to model
        model.addAttribute("employee",emp);

        // send over  form

        return "employees/employee-form";

    }
    @GetMapping("/delete")
    public String deleteEmployee (@RequestParam("empId") int id){

        // delete emp from service
        employeeService.deleteById(id);

        // redirect employeesList
        return "redirect:/employees/list";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee emp){
        employeeService.save(emp);
        return "redirect:/employees/list";
    }
}
