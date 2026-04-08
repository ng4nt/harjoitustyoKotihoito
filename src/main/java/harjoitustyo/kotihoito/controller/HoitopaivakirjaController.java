package harjoitustyo.kotihoito.controller;

import harjoitustyo.kotihoito.model.*;
import harjoitustyo.kotihoito.repository.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/potilaat/{potilasId}/paivakirjat")
@Tag(name = "Hoitopäiväkirja API", description = "Potilaiden hoitopäiväkirjamerkinnät")
public class HoitopaivakirjaController {

    private final HoitopaivakirjaRepository paivakirjaRepository;
    private final PotilasRepository potilasRepository;
    private final HoitajaRepository hoitajaRepository;

    public HoitopaivakirjaController(
            HoitopaivakirjaRepository paivakirjaRepository,
            PotilasRepository potilasRepository,
            HoitajaRepository hoitajaRepository) {
        this.paivakirjaRepository = paivakirjaRepository;
        this.potilasRepository = potilasRepository;
        this.hoitajaRepository = hoitajaRepository;
    }

    @PostMapping("/hoitajat/{hoitajaId}")
    @Operation(summary = "Lisää hoitopäiväkirjamerkintä potilaalle")
    public ResponseEntity<Hoitopaivakirja> createPaivakirja(
            @PathVariable Integer potilasId,
            @PathVariable Integer hoitajaId,
            @Valid @RequestBody Hoitopaivakirja paivakirja) {

        Potilas potilas = potilasRepository.findById(potilasId)
                .orElseThrow(() -> new RuntimeException("Potilasta ei löytynyt"));

        Hoitaja hoitaja = hoitajaRepository.findById(hoitajaId)
                .orElseThrow(() -> new RuntimeException("Hoitajaa ei löytynyt"));

        paivakirja.setPotilas(potilas);
        paivakirja.setHoitaja(hoitaja);

        return ResponseEntity.ok(paivakirjaRepository.save(paivakirja));
    }
    
    @GetMapping
    @Operation(summary = "Hae potilaan hoitopäiväkirjamerkinnät")
    public ResponseEntity<List<Hoitopaivakirja>> getPaivakirjat(
            @PathVariable Integer potilasId) {

        Potilas potilas = potilasRepository.findById(potilasId)
                .orElseThrow(() -> new RuntimeException("Potilasta ei löytynyt"));

        return ResponseEntity.ok(
                paivakirjaRepository.findByPotilas(potilas)
        );
    }    

}