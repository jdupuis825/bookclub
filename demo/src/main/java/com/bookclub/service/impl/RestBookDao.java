package com.bookclub.service.impl;

import com.bookclub.model.Book;
import com.bookclub.service.dao.BookDao;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

public class RestBookDao implements BookDao {

    // ------------------------------------------------------------
    // FETCH RAW JSON FROM OPENLIBRARY
    // ------------------------------------------------------------
    public Object getBooksDoc(String isbnString) {

        String openLibraryUrl = "https://openlibrary.org/api/books";

        RestTemplate rest = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(openLibraryUrl)
                .queryParam("bibkeys", isbnString)
                .queryParam("format", "json")
                .queryParam("jscmd", "details");

        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<String> response = rest.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class
        );

        String jsonBookList = response.getBody();

        return Configuration.defaultConfiguration().jsonProvider().parse(jsonBookList);
    }

    // ------------------------------------------------------------
    // STEP 8: UPDATED list(String key) — USED BY HOMEPAGE
    // ------------------------------------------------------------
    @Override
    public List<Book> list(String key) {

        Object doc = getBooksDoc(key);

        List<Book> books = new ArrayList<>();

        List<String> titles = JsonPath.read(doc, "$..details.title");
        List<String> isbns = JsonPath.read(doc, "$..bib_key");
        List<String> infoURLs = JsonPath.read(doc, "$..info_url");

        for (int i = 0; i < titles.size(); i++) {
            books.add(new Book(
                    isbns.get(i),
                    titles.get(i),
                    "N/A",
                    infoURLs.get(i),
                    0
            ));
        }

        return books;
    }

    // ------------------------------------------------------------
    // ORIGINAL list() — USED BY MONTHLY BOOKS PAGE (STATIC LIST)
    // ------------------------------------------------------------
    @Override
    public List<Book> list() {

        // Split into two calls so OpenLibrary never drops Dune
        String isbnString1 = "ISBN:9780593099322"; // Dune
        String isbnString2 = "ISBN:9780859399322,ISBN:9780261102361,ISBN:9780261102378,ISBN:9780590302715,ISBN:9780316769532";

        Object doc1 = getBooksDoc(isbnString1);
        Object doc2 = getBooksDoc(isbnString2);

        List<Book> books = new ArrayList<>();

        // First batch (Dune)
        List<String> titles1 = JsonPath.read(doc1, "$..details.title");
        List<String> isbns1 = JsonPath.read(doc1, "$..bib_key");
        List<String> infoURLs1 = JsonPath.read(doc1, "$..info_url");

        for (int i = 0; i < titles1.size(); i++) {
            books.add(new Book(
                    isbns1.get(i),
                    titles1.get(i),
                    "N/A",
                    infoURLs1.get(i),
                    0
            ));
        }

        // Second batch (other 4 books)
        List<String> titles2 = JsonPath.read(doc2, "$..details.title");
        List<String> isbns2 = JsonPath.read(doc2, "$..bib_key");
        List<String> infoURLs2 = JsonPath.read(doc2, "$..info_url");

        for (int i = 0; i < titles2.size(); i++) {
            books.add(new Book(
                    isbns2.get(i),
                    titles2.get(i),
                    "N/A",
                    infoURLs2.get(i),
                    0
            ));
        }

        return books;
    }

    // ------------------------------------------------------------
    // FIND A SINGLE BOOK BY ISBN
    // ------------------------------------------------------------
    @Override
    public Book find(String key) {

        Object doc = getBooksDoc(key);

        List<String> isbns = JsonPath.read(doc, "$..bib_key");
        List<String> titles = JsonPath.read(doc, "$..details.title");
        List<String> subtitles = JsonPath.read(doc, "$..details.subtitle");
        List<String> infoURLs = JsonPath.read(doc, "$..info_url");
        List<Integer> pages = JsonPath.read(doc, "$..details.number_of_pages");

        String isbn = isbns.size() > 0 ? isbns.get(0) : "N/A";
        String title = titles.size() > 0 ? titles.get(0) : "N/A";
        String desc = subtitles.size() > 0 ? subtitles.get(0) : "N/A";
        String infoUrl = infoURLs.size() > 0 ? infoURLs.get(0) : "N/A";
        int numOfPages = pages.size() > 0 ? pages.get(0) : 0;

        return new Book(isbn, title, desc, infoUrl, numOfPages);
    }

    // ------------------------------------------------------------
    // UNUSED CRUD METHODS
    // ------------------------------------------------------------
    @Override
    public void add(Book entity) { }

    @Override
    public void update(Book entity) { }

    @Override
    public void delete(String key) { }
}
