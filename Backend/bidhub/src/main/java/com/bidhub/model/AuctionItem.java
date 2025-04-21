package com.bidhub.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Document(collection = "auctions")
public class AuctionItem {
    @Id
    private String id;
    private String title;
    private String description;
    private double startingPrice;
    private double highestBid;
    private String highestBidderId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean sold = false;
    private boolean isPaid = false;

}

