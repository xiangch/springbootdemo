package com.do1.demo.controller;
import com.do1.demo.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private static final Logger log = LoggerFactory.getLogger(HelloController.class);
    @Autowired
    CustomerService customerService;
    @RequestMapping("/getHello")
    public String index() {
        System.out.println("=======11221=====");


        //return customerService.getById(1).toString();

        return "Customer[id=1, firstName='Jod', lastName='Wall']";
    }
}
