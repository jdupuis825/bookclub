/*
    Walls, C. (2019). Spring In Action (5th ed.). Shelter Island, NY: Manning Publications.
        Modified by Terrence Galamison (2023)
    Dupuis, J. (2025). CSI 505 Intermediate Java Programming. Bellevue University. Modified by A. Student 2025.
*/

package com.bookclub.service.dao;

import com.bookclub.model.Book;
import java.util.List;

public interface BookDao extends GenericDao<Book, String> {

    // Required for RestBookDao.list(String key)
    List<Book> list(String key);

    // Already required by GenericDao, but we explicitly declare find()
    Book find(String key);
}



