package com.bookclub.service.dao;

import java.util.List;

public interface GenericCrudDao<E, K> {
    void add(E entity);
    void update(E entity);
    boolean remove(K key);
    List<E> list(K key); // Return a list of type E
    E find(K key); // Return an object of type E
}

