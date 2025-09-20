package com.zisis.medication.api.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record CreateMedicationRequest(@NotBlank String name, String code, String manufacturer, String ingredient, LocalDate expiryDate, int quantity) {
}
