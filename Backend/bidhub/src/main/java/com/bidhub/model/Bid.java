package com.bidhub.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "bids")
public class Bid {
    @Id
    private String id;
    private String auctionItemId;
    private String userId;
    private double bidAmount;
    private LocalDateTime bidTime;
}
