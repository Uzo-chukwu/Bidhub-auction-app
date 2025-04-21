package com.bidhub.repository;

import com.bidhub.model.AuctionItem;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AuctionItemRepository extends MongoRepository<AuctionItem, String> {
    List<AuctionItem> findBySoldFalse();
}
