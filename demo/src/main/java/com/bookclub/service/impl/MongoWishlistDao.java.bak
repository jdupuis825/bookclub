package com.bookclub.service.impl;

import com.bookclub.model.WishListItem;
import com.bookclub.service.dao.WishlistDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("wishlistDao")
public class MongoWishlistDao implements WishlistDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void add(WishListItem entity) {
        mongoTemplate.save(entity);
    }

    @Override
    public void update(WishListItem entity) {
        mongoTemplate.save(entity);
    }

    @Override
    public boolean remove(WishListItem entity) {
        mongoTemplate.remove(entity);
        return true;
    }

    @Override
    public List<WishListItem> list() {
        return mongoTemplate.findAll(WishListItem.class);
    }

    @Override
    public WishListItem find(String key) {
        return mongoTemplate.findById(key, WishListItem.class);
    }
}
