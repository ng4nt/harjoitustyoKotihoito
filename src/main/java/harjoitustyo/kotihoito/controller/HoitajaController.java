package harjoitustyo.kotihoito.controller;

import harjoitustyo.kotihoito.model.*;
import harjoitustyo.kotihoito.repository.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@RestController
@RequestMapping("/api/hoitajat")
@Tag(name = "Hoitaja API", description = "Endpointit hoitajien hallintaan")
public class HoitajaController {

    private final HoitajaRepository hoitajaRepository;
    private final PasswordEncoder passwordEncoder;

    public HoitajaController(HoitajaRepository hoitajaRepository, PasswordEncoder passwordEncoder) {
        this.hoitajaRepository = hoitajaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    @Operation(summary = "Luo hoitajalle käyttäjätili")
    public ResponseEntity<Hoitaja> createHoitaja(@Valid @RequestBody Hoitaja hoitaja) {
        if (hoitaja.getPasswordHash() != null) {
            hoitaja.setPasswordHash(passwordEncoder.encode(hoitaja.getPasswordHash()));
        }
        return ResponseEntity.ok(hoitajaRepository.save(hoitaja));
    }

    @GetMapping
    @Operation(summary = "Hae kaikki hoitajien käyttäjätilit")
    public ResponseEntity<List<Hoitaja>> getAllHoitajat() {
        return ResponseEntity.ok(hoitajaRepository.findAll());
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Hae tietty hoitaja id:n avulla")
        public ResponseEntity<Hoitaja> getHoitajaById(@PathVariable Integer id) {
            return hoitajaRepository.findById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }
    
    @PutMapping("/{id}")
    @Operation(summary = "Muokkaa hoitajan tietoja id:n avulla")
    public ResponseEntity<Hoitaja> updateHoitaja(
            @PathVariable Integer id,
            @Valid @RequestBody Hoitaja updated) {

        return hoitajaRepository.findById(id)
                .map(existing -> {
                    existing.setEtunimi(updated.getEtunimi());
                    existing.setSukunimi(updated.getSukunimi());
                    existing.setSahkoposti(updated.getSahkoposti());
                    existing.setPuhelin(updated.getPuhelin());

                    if (updated.getPasswordHash() != null && !updated.getPasswordHash().isEmpty()) {
                        existing.setPasswordHash(passwordEncoder.encode(updated.getPasswordHash()));
                    }
                    
                    return ResponseEntity.ok(hoitajaRepository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Poista hoitaja id:n avulla")
    public ResponseEntity<Void> deleteHoitaja(@PathVariable Integer id) {
        if (!hoitajaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        hoitajaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
