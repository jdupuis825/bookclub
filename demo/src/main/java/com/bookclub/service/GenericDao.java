/*
    Walls, C. (2019). Spring In Action (5th ed.). Shelter Island, NY: Manning Publications.
        Modified by Terrence Galamison (2023)
    Dupuis, J. (2025). CSI 505 Intermediate Java Programming. Bellevue University. Modified by A. Student 2025.
*/

package com.bookclub.service;

import java.util.List;

public interface GenericDao<E, K> {

    List<E> list();   // Return a list of objects of type E

    E find(K key);    // Return an object of type E by key of type K
}

