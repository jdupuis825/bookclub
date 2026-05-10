package com.bookclub.repository;

import com.bookclub.model.WishListItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WishListItemRepository extends MongoRepository<WishListItem, String> {
}
