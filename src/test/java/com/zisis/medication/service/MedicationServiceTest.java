package com.zisis.medication.service;

import com.zisis.medication.api.dto.request.CreateMedicationRequest;
import com.zisis.medication.api.dto.response.MedicationResponse;
import com.zisis.medication.api.dto.response.MedicationsResponse;
import com.zisis.medication.model.Medication;
import com.zisis.medication.repository.MedicationRepository;
import com.zisis.medication.service.mapper.MedicationMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MedicationServiceTest {

    @Mock
    private MedicationRepository medicationRepository;
    @Mock
    private MedicationMapper mapper;

    @InjectMocks
    private MedicationService service;

    // ---------- helpers ----------
    private static Medication mkEntity(Long id) {
        Medication m = new Medication();
        m.setId(id);
        m.setName("Aspirin");
        m.setCode("ASP100");
        m.setIngredient("Acetylsalicylic Acid");
        m.setManufacturer("Bayer");
        m.setExpiryDate(LocalDate.parse("2026-12-31"));
        m.setQuantity(30);
        return m;
    }

    private static MedicationResponse mkDto(Long id) {
        return new MedicationResponse(
                id, "Aspirin", "ASP100", "Bayer", "Acetylsalicylic Acid", LocalDate.parse("2026-12-31"), 30
        );
    }

    private static CreateMedicationRequest mkCreateReq() {
        return new CreateMedicationRequest(
                "Aspirin", "1234567890", "Bayer", "Acetylsalicylic Acid", LocalDate.parse("2026-12-31"), 30
        );
    }

    @Test
    void shouldReturnWrappedList_WhenMedicationsExist() {
        var entities = List.of(mkEntity(1L), mkEntity(2L));
        var dtos = new MedicationsResponse(List.of(mkDto(1L), mkDto(2L)));

        when(medicationRepository.findAll()).thenReturn(entities);
        when(mapper.toDtos(entities)).thenReturn(dtos);

        var result = service.getMedications();

        assertThat(result.medications()).hasSize(2);
        verify(medicationRepository).findAll();
        verify(mapper).toDtos(entities);
        verifyNoMoreInteractions(medicationRepository, mapper);
    }

    @Test
    void shouldReturnMedication_WhenFoundById() {
        var entity = mkEntity(42L);
        var dto = mkDto(42L);

        when(medicationRepository.findById(42L)).thenReturn(Optional.of(entity));
        when(mapper.toDto(entity)).thenReturn(dto);

        var result = service.getMedication(42L);

        assertThat(result.id()).isEqualTo(42L);
        verify(medicationRepository).findById(42L);
        verify(mapper).toDto(entity);
        verifyNoMoreInteractions(medicationRepository, mapper);
    }

    @Test
    void shouldThrowNoSuchElement_WhenMedicationNotFound() {
        when(medicationRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.getMedication(99L))
                .isInstanceOf(NoSuchElementException.class);

        verify(medicationRepository).findById(99L);
        verifyNoInteractions(mapper);
    }

    @Test
    void shouldMapAndSave_WhenCreatingMedication() {
        var req = mkCreateReq();
        var toSave = mkEntity(null);         // mapper creates entity (no id yet)
        var saved = mkEntity(5L);            // repo returns saved with id
        var dto = mkDto(5L);

        when(mapper.toEntity(req)).thenReturn(toSave);
        when(medicationRepository.save(toSave)).thenReturn(saved);
        when(mapper.toDto(saved)).thenReturn(dto);

        var result = service.createMedication(req);

        assertThat(result.id()).isEqualTo(5L);
        verify(mapper).toEntity(req);
        verify(medicationRepository).save(toSave);
        verify(mapper).toDto(saved);
        verifyNoMoreInteractions(medicationRepository, mapper);
    }
}
