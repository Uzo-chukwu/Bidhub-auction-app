package com.bidhub.dto;

import com.bidhub.model.AuctionStatus;
import lombok.Data;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class AuctionItemDTO {
    private String id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @Min(value = 0, message = "Starting bid must be non-negative")
    private double startingBid;

    private double currentHighestBid;
    private String highestBidderId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private AuctionStatus status;
}