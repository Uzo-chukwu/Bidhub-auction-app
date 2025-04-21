package com.bidhub.dto;

import lombok.Data;

@Data
public class CreateAuctionItemRequest {
    private String title;
    private String description;
    private double startingPrice;

}

