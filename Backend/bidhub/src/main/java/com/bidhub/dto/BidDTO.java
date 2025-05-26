package com.bidhub.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BidDTO {
    private String id;
    private String auctionItemId;
    private String userId;
    private double bidAmount;
    private LocalDateTime bidTime;
}