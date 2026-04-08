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
@RequestMapping("/api/potilaat")
@Tag(name = "Potilas API", description = "Endpointit potilaiden hallintaan")
public class PotilasController {

    private final PotilasRepository potilasRepository;

    public PotilasController(PotilasRepository potilasRepository) {
        this.potilasRepository = potilasRepository;
    }

    @PostMapping
    @Operation(summary = "Luo uusi potilas")
    public ResponseEntity<Potilas> createPotilas(
            @Valid @RequestBody Potilas potilas) {

        return ResponseEntity.ok(potilasRepository.save(potilas));
    }

    @GetMapping
    @Operation(summary = "Hae kaikki potilaat")
    public ResponseEntity<List<Potilas>> getAllPotilaat() {
        return ResponseEntity.ok(potilasRepository.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Hae potilas id:n perusteella")
    public ResponseEntity<Potilas> getPotilasById(@PathVariable Integer id) {
        return potilasRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Muokkaa potilaan tietoja id:n avulla")
    public ResponseEntity<Potilas> updatePotilas(
            @PathVariable Integer id,
            @Valid @RequestBody Potilas updated) {

        return potilasRepository.findById(id)
                .map(existing -> {
                    existing.setEtunimi(updated.getEtunimi());
                    existing.setSukunimi(updated.getSukunimi());
                    existing.setHetu(updated.getHetu());
                    existing.setPuhelin(updated.getPuhelin());
                    existing.setOsoite(updated.getOsoite());
                    return ResponseEntity.ok(potilasRepository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Poista potilas id:n avulla")
    public ResponseEntity<Void> deletePotilas(@PathVariable Integer id) {
        if (!potilasRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        potilasRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}