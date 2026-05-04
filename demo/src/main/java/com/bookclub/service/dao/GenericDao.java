/*
    Walls, C. (2019). Spring In Action (5th ed.). Shelter Island, NY: Manning Publications.
        Modified by Terrence Galamison (2023)
    Dupuis, J. (2025). CSI 505 Intermediate Java Programming. Bellevue University. Modified by A. Student 2025.
*/

package com.bookclub.service.dao;

import java.util.List;

public interface GenericDao<E, K> {

    List<E> list();        // Return all items

    E find(K key);         // Find one item by key

    void add(E entity);    // Add a new item

    void update(E entity); // Update an existing item

    void delete(K key);    // Delete by key
}


