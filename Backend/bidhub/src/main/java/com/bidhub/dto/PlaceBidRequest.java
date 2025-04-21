package com.bidhub.dto;

import lombok.Data;

@Data
public class PlaceBidRequest {
    private String userId;
    private String auctionItemId;
    private double amount;

}

