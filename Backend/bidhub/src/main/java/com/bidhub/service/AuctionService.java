package com.bidhub.service;

import com.bidhub.dto.CreateAuctionItemRequest;
import com.bidhub.model.AuctionItem;

import java.util.List;

public interface AuctionService {
    AuctionItem createItem(CreateAuctionItemRequest request);
    List<AuctionItem> getActiveAuctions();
}

