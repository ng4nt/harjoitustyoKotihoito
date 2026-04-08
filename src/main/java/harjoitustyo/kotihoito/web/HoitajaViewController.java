package harjoitustyo.kotihoito.web;

import harjoitustyo.kotihoito.model.Hoitaja;
import harjoitustyo.kotihoito.repository.HoitajaRepository;

import jakarta.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hoitajat")
public class HoitajaViewController {

    private final HoitajaRepository hoitajaRepository;
    private final PasswordEncoder passwordEncoder;

    public HoitajaViewController(HoitajaRepository hoitajaRepository, PasswordEncoder passwordEncoder) {
        this.hoitajaRepository = hoitajaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String hoitajat(Model model) {
        model.addAttribute("hoitajat", hoitajaRepository.findAll());
        model.addAttribute("hoitaja", new Hoitaja());
        return "hoitajat";
    }

    @PostMapping
    public String save(@ModelAttribute @Valid Hoitaja hoitaja) {
        if (hoitaja.getPasswordHash() != null && !hoitaja.getPasswordHash().isEmpty()) {
            hoitaja.setPasswordHash(passwordEncoder.encode(hoitaja.getPasswordHash()));
        }
        
        hoitajaRepository.save(hoitaja);
        return "redirect:/hoitajat";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id) {
        hoitajaRepository.deleteById(id);
        return "redirect:/hoitajat";
    }

    @GetMapping("/{id}/edit")
    public String naytaMuokkauslomake(@PathVariable Integer id, Model model) {
        Hoitaja hoitaja = hoitajaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Virheellinen id:" + id));
        model.addAttribute("hoitaja", hoitaja);
        return "muokkaahoitaja";
    }
}