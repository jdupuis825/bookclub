/*
    Walls, C. (2019). Spring In Action (5th ed.). Shelter Island, NY: Manning Publications.
        Modified by Terrence Galamison (2023)
    Dupuis, J. (2025). CSI 505 Intermediate Java Programming. Bellevue University. Modified by A. Student 2025.
*/

package com.bookclub.service.impl;

import com.bookclub.model.Book;
import com.bookclub.service.dao.BookDao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * MemBookDao is an in-memory implementation of the BookDao interface.
 * It stores a fixed list of Book objects and provides methods to
 * list all books and find a book by its ISBN.
 */
public class MemBookDao implements BookDao {

    // Internal list of books stored in memory
    private List<Book> books;

    // Constructor initializes the list with five sample books
    public MemBookDao() {
        this.books = new ArrayList<>();

        books.add(new Book(
                "1111",
                "The Silent Forest",
                "A mystery novel set in a remote mountain town.",
                320,
                Arrays.asList("Lena Hart", "Michael Rowan")
        ));

        books.add(new Book(
                "2222",
                "Journey to Andromeda",
                "A sci-fi adventure across galaxies.",
                410,
                Arrays.asList("Tara Quinn")
        ));

        books.add(new Book(
                "3333",
                "Cooking with Joy",
                "A beginner-friendly cookbook full of comfort food.",
                200,
                Arrays.asList("Joy Martinez")
        ));

        books.add(new Book(
                "4444",
                "The Art of Focus",
                "A guide to productivity and deep work.",
                185,
                Arrays.asList("Samuel Lee")
        ));

        books.add(new Book(
                "5555",
                "Legends of Aralon",
                "An epic fantasy tale of heroes and ancient magic.",
                540,
                Arrays.asList("R. K. Donovan", "Elise Trent")
        ));
    }

    /**
     * Returns the full list of books.
     */
    @Override
    public List<Book> list() {
        return this.books;
    }

    /**
     * Finds a book by its ISBN.
     * Returns a new empty Book if no match is found.
     */
    @Override
    public Book find(String key) {
        for (Book book : this.books) {
            if (book.getIsbn().equals(key)) {
                return book;
            }
        }
        return new Book(); // fallback if not found
    }
}

