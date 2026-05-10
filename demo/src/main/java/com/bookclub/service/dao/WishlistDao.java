/*
    Walls, C. (2019). Spring In Action (5th ed.). Shelter Island, NY: Manning Publications.
        Modified by Terrence Galamison (2023)
    Dupuis, J. (2025). CSI 505 Intermediate Java Programming. Bellevue University. Modified by A. Student 2025.
*/

package com.bookclub.service.dao;

import com.bookclub.model.WishListItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WishlistDao implements GenericCrudDao<WishListItem, String> {

    @Autowired
    private MongoTemplate mongoTemplate;

    // CREATE
    @Override
    public void add(WishListItem entity) {
        mongoTemplate.save(entity);
    }

    // READ (single item)
    @Override
    public WishListItem find(String key) {
        return mongoTemplate.findById(key, WishListItem.class);
    }

    // READ (list by username)
    @Override
    public List<WishListItem> list(String username) {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(username));
        return mongoTemplate.find(query, WishListItem.class);
    }

    // UPDATE
    @Override
    public void update(WishListItem entity) {

        // Find existing record
        WishListItem existing = mongoTemplate.findById(entity.getId(), WishListItem.class);

        if (existing != null) {
            existing.setIsbn(entity.getIsbn());
            existing.setTitle(entity.getTitle());
            existing.setUsername(entity.getUsername()); // keep user attached

            mongoTemplate.save(existing);
        }
    }

    // DELETE
    @Override
    public boolean remove(String key) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(key));

        return mongoTemplate.remove(query, WishListItem.class).wasAcknowledged();
    }
}
