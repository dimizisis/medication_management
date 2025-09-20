package com.zisis.medication.api.dto.response;

import java.time.LocalDate;

public record MedicationResponse(Long id, String name, String code, String manufacturer, String ingredient, LocalDate expiryDate, int quantity) {
}
