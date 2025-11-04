package com.embabel.example.model;

import jakarta.validation.constraints.NotBlank;

public record EmbabelEnquiryRequest(@NotBlank String request) {
}
