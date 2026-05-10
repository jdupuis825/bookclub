/*
    Walls, C. (2019). Spring In Action (5th ed.). Shelter Island, NY: Manning Publications.
        Modified by Terrence Galamison (2023)
    Dupuis, J. (2025). CSI 505 Intermediate Java Programming. Bellevue University. Modified by A. Student 2025.
*/

package com.bookclub.model;

public class Book {

    // --- Private fields ---
    private String isbn;
    private String title;
    private String description;
    private String infoUrl;
    private int numOfPages;

    // --- Default constructor ---
    public Book() {
    }

    // --- Full constructor ---
    public Book(String isbn, String title, String description, String infoUrl, int numOfPages) {
        this.isbn = isbn;
        this.title = title;
        this.description = description;
        this.infoUrl = infoUrl;
        this.numOfPages = numOfPages;
    }

    // --- Getters and setters ---
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInfoUrl() {
        return infoUrl;
    }

    public void setInfoUrl(String infoUrl) {
        this.infoUrl = infoUrl;
    }

    public int getNumOfPages() {
        return numOfPages;
    }

    public void setNumOfPages(int numOfPages) {
        this.numOfPages = numOfPages;
    }

    // --- toString override ---
    @Override
    public String toString() {
        return "Book{isbn=" + isbn +
                ", title=" + title +
                ", description=" + description +
                ", infoUrl=" + infoUrl +
                ", numOfPages=" + numOfPages +
                "}";
    }
}
