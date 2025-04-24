package com.bidhub.dto;

import lombok.Data;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Data
public class BidRequestDTO {
    @NotBlank(message = "Auction item ID is required")
    private String auctionItemId;

    @Min(value = 0, message = "Bid amount must be positive")
    private double bidAmount;
}