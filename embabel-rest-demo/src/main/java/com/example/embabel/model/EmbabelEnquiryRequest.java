package com.example.embabel.model;

import jakarta.validation.constraints.NotBlank;

public record EmbabelEnquiryRequest(@NotBlank String request) {
}
