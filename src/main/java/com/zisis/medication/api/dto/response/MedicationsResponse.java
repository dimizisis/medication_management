package com.zisis.medication.api.dto.response;

import java.util.List;

public record MedicationsResponse(List<MedicationResponse> medications) {
}
