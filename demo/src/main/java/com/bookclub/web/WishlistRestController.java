package com.bookclub.web;

import com.bookclub.model.WishListItem;
import com.bookclub.repository.WishListItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/wishlist", produces = "application/json")
@CrossOrigin(origins = "*")
public class WishlistRestController {

    @Autowired
    private WishListItemRepository repository;

    // GET all items
    @GetMapping
    public List<WishListItem> showWishlist() {
        return repository.findAll();
    }

    // GET item by ID
    @GetMapping("/{id}")
    public WishListItem findById(@PathVariable String id) {
        return repository.findById(id).orElse(null);
    }
}




