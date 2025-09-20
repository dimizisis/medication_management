package com.zisis.medication.service.mapper;

import com.zisis.medication.api.dto.request.CreateMedicationRequest;
import com.zisis.medication.api.dto.response.MedicationResponse;
import com.zisis.medication.api.dto.response.MedicationsResponse;
import com.zisis.medication.model.Medication;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MedicationMapper {

    @Mapping(target = "id", ignore = true)
    Medication toEntity(CreateMedicationRequest dto);

    MedicationResponse toDto(Medication entity);

    default MedicationsResponse toDtos(List<Medication> entities) {
        var responses = entities.stream()
                .map(this::toDto)
                .toList();
        return new MedicationsResponse(responses);
    }
}

