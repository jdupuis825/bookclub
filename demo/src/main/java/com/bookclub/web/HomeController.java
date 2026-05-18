/*
    Walls, C. (2019). Spring In Action (5th ed.). Shelter Island, NY: Manning Publications.
        Modified by Terrence Galamison (2023)
    Dupuis, J. (2025). CSI 505 Intermediate Java Programming. Bellevue University. Modified by A. Student 2025.
*/
package com.bookclub.web;

import com.bookclub.model.Book;
import com.bookclub.model.BookOfTheMonth;
import com.bookclub.service.dao.BookOfTheMonthDao;
import com.bookclub.service.impl.RestBookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    private BookOfTheMonthDao bookOfTheMonthDao;

    @Autowired
    public void setBookOfTheMonthDao(BookOfTheMonthDao bookOfTheMonthDao) {
        this.bookOfTheMonthDao = bookOfTheMonthDao;
    }

    // ------------------------------------------------------------
    // HOME PAGE — SHOW CURRENT MONTH'S BOOK(S)
    // ------------------------------------------------------------
    @RequestMapping(method = RequestMethod.GET)
    public String showHome(Model model) {

        // 1. Get current month (1–12)
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int calMonth = cal.get(Calendar.MONTH) + 1;

        // 2. Get BookOfTheMonth entries for this month
        List<BookOfTheMonth> monthlyBooks = bookOfTheMonthDao.list(Integer.toString(calMonth));

        // 3. Build ISBN string required by assignment
        StringBuilder isbnBuilder = new StringBuilder();
        isbnBuilder.append("ISBN:");

        for (BookOfTheMonth monthlyBook : monthlyBooks) {
            isbnBuilder.append(monthlyBook.getIsbn()).append(",");
        }

        // If no books exist for this month, avoid substring error
        if (monthlyBooks.isEmpty()) {
            model.addAttribute("books", List.of());
            return "index";
        }

        // Remove trailing comma
        String isbnString = isbnBuilder.toString().substring(0, isbnBuilder.length() - 1);

        // 4. Fetch book details from external API
        RestBookDao bookDao = new RestBookDao();
        List<Book> books = bookDao.list(isbnString);

        // 5. Add to model
        model.addAttribute("books", books);

        return "index";
    }

    // ------------------------------------------------------------
    // VIEW A SINGLE BOOK BY ISBN
    // ------------------------------------------------------------
    @RequestMapping(method = RequestMethod.GET, value = "/id/{id}")
    public String getMonthlyBook(@PathVariable("id") String id, Model model) {

        RestBookDao bookDao = new RestBookDao();
        Book book = bookDao.find(id);

        model.addAttribute("book", book);
        return "monthly-books/view";
    }

    // ------------------------------------------------------------
    // STATIC PAGES
    // ------------------------------------------------------------
    @RequestMapping(method = RequestMethod.GET, path = "/about")
    public String showAboutUs(Model model) {
        return "about";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/contact")
    public String showContactUs(Model model) {
        return "contact";
    }
}
