package com.bidhub.repository;

import com.bidhub.model.AuctionItem;
import com.bidhub.model.AuctionStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AuctionItemRepository extends MongoRepository<AuctionItem, String> {
    List<AuctionItem> findByStatus(AuctionStatus status);
    List<AuctionItem> findByHighestBidderIdAndStatus(String userId, AuctionStatus status);
}