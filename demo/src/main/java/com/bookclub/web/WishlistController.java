/*
    Walls, C. (2019). Spring In Action (5th ed.). Shelter Island, NY: Manning Publications.
        Modified by Terrence Galamison (2023)
    Dupuis, J. (2025). CSI 505 Intermediate Java Programming. Bellevue University. Modified by A. Student 2025.
*/

package com.bookclub.web;

import com.bookclub.model.WishListItem;

import com.bookclub.service.impl.MemWishlistDao;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/wishlist")
public class WishlistController {

    private MemWishlistDao dao = new MemWishlistDao();

    // LIST PAGE
    @GetMapping
    public String showWishList(Model model) {
        model.addAttribute("wishlist", dao.list());
        return "wishlist/list";
    }

    // NEW ITEM FORM
    @GetMapping("/new")
    public String wishListForm(Model model) {
        model.addAttribute("wishListItem", new WishListItem());
        return "wishlist/new";
    }

    // ADD NEW ITEM
    @PostMapping
    public String addWishListItem(@Valid @ModelAttribute("wishListItem") WishListItem wishListItem,
                                  BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "wishlist/new";
        }

        dao.add(wishListItem);
        return "redirect:/wishlist";
    }

    // EDIT FORM
    @GetMapping("/edit/{id}")
    public String editWishListItem(@PathVariable("id") String id, Model model) {
        WishListItem item = dao.find(id);
        model.addAttribute("wishListItem", item);
        return "wishlist/edit";
    }

    // SAVE EDITED ITEM
    @PostMapping("/edit/{id}")
    public String updateWishListItem(@PathVariable("id") String id,
                                     @Valid @ModelAttribute("wishListItem") WishListItem wishListItem,
                                     BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "wishlist/edit";
        }

        wishListItem.setId(Integer.parseInt(id));
        dao.update(wishListItem);

        return "redirect:/wishlist";
    }

    // DELETE ITEM
    @GetMapping("/delete/{id}")
    public String deleteWishListItem() {
        //dao.delete(id);
        return "redirect:/wishlist";
    }
}
