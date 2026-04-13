/*
    Walls, C. (2019). Spring In Action (5th ed.). Shelter Island, NY: Manning Publications.
        Modified by Terrence Galamison (2023)
    Dupuis, J. (2025). CSI 505 Intermediate Java Programming. Bellevue University. Modified by A. Student 2025.
*/

package com.bookclub.web;

import com.bookclub.model.WishListItem;
import com.bookclub.service.dao.WishlistDao;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/wishlist")
public class WishlistController {

    private WishlistDao wishlistDao;

    @Autowired
    private void setWishlistDao(WishlistDao wishlistDao) {
        this.wishlistDao = wishlistDao;
    }

    // LIST PAGE
    @GetMapping
    public String showWishList(Model model) {
        model.addAttribute("wishlist", wishlistDao.list());
        return "wishlist/list";
    }

    // NEW ITEM FORM
    @GetMapping("/new")
    public String wishListForm(Model model) {
        model.addAttribute("wishListItem", new WishListItem());
        return "wishlist/new";
    }

    // ADD NEW ITEM
    @PostMapping("/new")
    public String addWishListItem(
            @Valid @ModelAttribute("wishListItem") WishListItem wishListItem,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "wishlist/new";
        }

        wishlistDao.add(wishListItem);
        return "redirect:/wishlist";
    }

    // EDIT FORM
    @GetMapping("/edit/{id}")
    public String editWishListItem(@PathVariable("id") String id, Model model) {
        WishListItem item = wishlistDao.find(id);
        model.addAttribute("wishListItem", item);
        return "wishlist/edit";
    }

    // SAVE EDITED ITEM
    @PostMapping("/edit/{id}")
    public String updateWishListItem(
            @PathVariable("id") String id,
            @Valid @ModelAttribute("wishListItem") WishListItem wishListItem,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "wishlist/edit";
        }

        wishListItem.setId(id);
        wishlistDao.update(wishListItem);

        return "redirect:/wishlist";
    }

    // DELETE ITEM
    @GetMapping("/delete/{id}")
    public String deleteWishListItem(@PathVariable("id") String id) {
        WishListItem item = wishlistDao.find(id);
        wishlistDao.remove(item);
        return "redirect:/wishlist";
    }
}
