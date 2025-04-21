package com.bidhub.service;

import com.bidhub.dto.PlaceBidRequest;
import com.bidhub.model.Bid;

import java.util.List;

public interface BidService {
    Bid placeBid(PlaceBidRequest request);
    List<Bid> getBidsForAuction(String auctionItemId);
}

