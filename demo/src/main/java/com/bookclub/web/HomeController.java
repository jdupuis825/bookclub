package com.bookclub.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller                     // Marks this class as a Spring MVC controller
@RequestMapping(value = "/")    // Base URL for all methods in this controller
public class HomeController {

    @RequestMapping(method = RequestMethod.GET)
    public String showHome(Model model) {
        // Returns the "index.html" template located in src/main/resources/templates
        return "index";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/about")
    public String showAboutUs(Model model) {
        // Returns the "about.html" template
        return "about";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/contact")
    public String showContactUs(Model model) {
        // Returns the "contact.html" template
        return "contact";
    }
}
