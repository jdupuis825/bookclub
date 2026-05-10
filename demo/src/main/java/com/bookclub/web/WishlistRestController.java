package com.bookclub.web;

import com.bookclub.model.WishListItem;
import com.bookclub.service.dao.WishlistDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/wishlist")
public class WishlistRestController {

    @Autowired
    private WishlistDao wishListDao;

    // LIST all wishlist items for the logged‑in user
    @GetMapping
    public String showWishlist(Model model, Authentication authentication) {

        String username = authentication.getName();

        model.addAttribute("items", wishListDao.list(username));

        return "wishlist/list";
    }

    // SHOW a single wishlist item for editing
    @GetMapping("/{id}")
    public String showWishlistItem(@PathVariable String id, Model model) {

        WishListItem item = wishListDao.find(id);

        model.addAttribute("item", item);

        return "wishlist/edit";
    }


    // SHOW the New Wishlist Item form
    @GetMapping("/new")
    public String showNewForm(Model model) {
        model.addAttribute("wishListItem", new WishListItem());
        return "wishlist/new";
    }

    // ADD a new wishlist item
    @PostMapping("/new")
    public String addNewItem(@ModelAttribute WishListItem item,
                             Authentication authentication) {

        item.setUsername(authentication.getName());
        wishListDao.add(item);

        return "redirect:/wishlist";
    }




    // UPDATE an existing wishlist item
    @PostMapping("/update")
    public String updateWishlistItem(@ModelAttribute WishListItem item,
                                     Authentication authentication) {

        // ensure the username stays attached to the record
        item.setUsername(authentication.getName());

        wishListDao.update(item);

        return "redirect:/wishlist";
    }

    // DELETE a wishlist item
    @GetMapping("/remove/{id}")
    public String removeWishlistItem(@PathVariable String id) {

        wishListDao.remove(id);

        return "redirect:/wishlist";
    }
}





