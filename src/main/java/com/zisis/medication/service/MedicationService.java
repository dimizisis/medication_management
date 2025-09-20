package com.zisis.medication.service;

import com.zisis.medication.api.dto.request.CreateMedicationRequest;
import com.zisis.medication.api.dto.response.MedicationResponse;
import com.zisis.medication.api.dto.response.MedicationsResponse;
import com.zisis.medication.model.Medication;
import com.zisis.medication.repository.MedicationRepository;
import com.zisis.medication.service.mapper.MedicationMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MedicationService {

    private final MedicationRepository medicationRepository;
    private final MedicationMapper mapper;

    public MedicationService(MedicationRepository medicationRepository, MedicationMapper mapper) {
        this.medicationRepository = medicationRepository;
        this.mapper = mapper;
    }

    public MedicationsResponse getMedications() {
        return mapper.toDtos(medicationRepository.findAll());
    }

    public MedicationResponse getMedication(Long id) {
        return medicationRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow();
    }

    @Transactional
    public MedicationResponse createMedication(CreateMedicationRequest createMedicationRequest) {
        Medication entity = mapper.toEntity(createMedicationRequest);
        return mapper.toDto(medicationRepository.save(entity));
    }
}
