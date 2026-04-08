package harjoitustyo.kotihoito.controller;

import harjoitustyo.kotihoito.model.*;
import harjoitustyo.kotihoito.repository.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/potilaat/{potilasId}/hoitosuunnitelma")
@Tag(name = "Hoitosuunnitelma API", description = "Potilaan hoitosuunnitelman hallinta")
public class HoitosuunnitelmaController {

    private final HoitosuunnitelmaRepository hoitosuunnitelmaRepository;
    private final PotilasRepository potilasRepository;

    public HoitosuunnitelmaController(
            HoitosuunnitelmaRepository hoitosuunnitelmaRepository,
            PotilasRepository potilasRepository) {
        this.hoitosuunnitelmaRepository = hoitosuunnitelmaRepository;
        this.potilasRepository = potilasRepository;
    }

        @PostMapping
        @Operation(summary = "Luo potilaalle hoitosuunnitelma")
        public ResponseEntity<Hoitosuunnitelma> createHoitosuunnitelma(
                @PathVariable Integer potilasId,
                @Valid @RequestBody Hoitosuunnitelma suunnitelma) {

        Potilas potilas = potilasRepository.findById(potilasId)
                .orElseThrow(() -> new RuntimeException("Potilasta ei löytynyt"));

        Hoitosuunnitelma savedSuunnitelma = hoitosuunnitelmaRepository.save(suunnitelma);

        potilas.setHoitosuunnitelma(savedSuunnitelma);
        potilasRepository.save(potilas);

        return ResponseEntity.ok(savedSuunnitelma);
        }

        @GetMapping
        @Operation(summary = "Hae potilaan hoitosuunnitelma")
        public ResponseEntity<Hoitosuunnitelma> getHoitosuunnitelma(
                @PathVariable Integer potilasId) {

        Potilas potilas = potilasRepository.findById(potilasId)
                .orElseThrow(() -> new RuntimeException("Potilasta ei löytynyt"));

        Hoitosuunnitelma suunnitelma = potilas.getHoitosuunnitelma();

        if (suunnitelma == null) {
                return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(suunnitelma);
        } 

        @PutMapping
        @Operation(summary = "Päivitä hoitosuunnitelma")
        public ResponseEntity<Hoitosuunnitelma> updateHoitosuunnitelma(
                @PathVariable Integer potilasId,
                @Valid @RequestBody Hoitosuunnitelma updated) {

        Potilas potilas = potilasRepository.findById(potilasId)
                .orElseThrow(() -> new RuntimeException("Potilasta ei löytynyt"));

        Hoitosuunnitelma existing = potilas.getHoitosuunnitelma();

        if (existing == null) {
                return ResponseEntity.notFound().build();
        }

        existing.setKuvaus(updated.getKuvaus());

        Hoitosuunnitelma saved =
                hoitosuunnitelmaRepository.save(existing);

        return ResponseEntity.ok(saved);
        }
}