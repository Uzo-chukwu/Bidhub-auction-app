package com.bidhub.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "auction_items")
public class AuctionItem {
    @Id
    private String id;
    private String name;
    private String description;
    private double startingBid;
    private double currentHighestBid;
    private String highestBidderId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private AuctionStatus status;
}
