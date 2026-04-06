/*
    Walls, C. (2019). Spring In Action (5th ed.). Shelter Island, NY: Manning Publications.
        Modified by Terrence Galamison (2023)
    Dupuis, J. (2025). CSI 505 Intermediate Java Programming. Bellevue University. Modified by A. Student 2025.
*/

package com.bookclub.service.impl;

import com.bookclub.model.WishListItem;
import com.bookclub.service.dao.WishlistDao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MemWishlistDao implements WishlistDao {

    private List<WishListItem> items = new ArrayList<>();

    @Override
    public List<WishListItem> list() {
        return items;
    }

    @Override
    public WishListItem find(String id) {
        return items.stream()
                .filter(i -> String.valueOf(i.getId()).equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void add(WishListItem item) {
        // Assign a unique ID if needed
        if (item.getId() == 0) {
            item.setId(UUID.randomUUID().hashCode());
        }
        items.add(item);
    }

    @Override
    public void update(WishListItem updatedItem) {
        delete(String.valueOf(updatedItem.getId()));
        items.add(updatedItem);
    }

    @Override
    public void delete(String id) {
        items.removeIf(i -> String.valueOf(i.getId()).equals(id));
    }
}
