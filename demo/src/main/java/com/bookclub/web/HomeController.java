/*
    Walls, C. (2019). Spring In Action (5th ed.). Shelter Island, NY: Manning Publications.
        Modified by Terrence Galamison (2023)
    Dupuis, J. (2025). CSI 505 Intermediate Java Programming. Bellevue University. Modified by A. Student 2025.
*/
package com.bookclub.web;

import com.bookclub.model.Book;
import com.bookclub.service.impl.MemBookDao;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller                     // Marks this class as a Spring MVC controller
@RequestMapping(value = "/")    // Base URL for all methods in this controller
public class HomeController {

    @RequestMapping(method = RequestMethod.GET)
    public String showHome(Model model) {

        // Create a new instance of the in-memory DAO
        MemBookDao bookDao = new MemBookDao();

        // Retrieve the list of all books
        List<Book> books = bookDao.list();

        // Optional: print each book to the console for debugging
        for (Book book : books) {
            System.out.println(book.toString());
        }

        // Add the list of books to the model so Thymeleaf can display them
        model.addAttribute("books", books);

        // Return the index.html page
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

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getMonthlyBook(@PathVariable("id") String id, Model model) {

        // Create a new instance of the in-memory DAO
        MemBookDao bookDao = new MemBookDao();

        // Find the book by its ISBN
        Book book = bookDao.find(id);

        // Add the book to the model so Thymeleaf can display it
        model.addAttribute("book", book);

        // Return the monthly-books/view.html page
        return "monthly-books/view";
    }

}
