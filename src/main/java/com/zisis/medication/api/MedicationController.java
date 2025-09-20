package com.zisis.medication.api;

import com.zisis.medication.api.dto.request.CreateMedicationRequest;
import com.zisis.medication.api.dto.response.MedicationResponse;
import com.zisis.medication.api.dto.response.MedicationsResponse;
import com.zisis.medication.service.MedicationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/medications")
public class MedicationController {

    private final MedicationService medicationService;

    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    @PostMapping
    public ResponseEntity<MedicationResponse> addMedication(@RequestBody @Valid CreateMedicationRequest medication) {
        return ResponseEntity.ok(medicationService.createMedication(medication));
    }

    @GetMapping
    public ResponseEntity<MedicationsResponse> getMedications(){
        return ResponseEntity.ok(medicationService.getMedications());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicationResponse> getMedicationById(@PathVariable long id){
        return ResponseEntity.ok(medicationService.getMedication(id));
    }
}
