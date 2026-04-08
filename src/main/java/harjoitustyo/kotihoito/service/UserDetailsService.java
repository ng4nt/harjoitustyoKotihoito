package harjoitustyo.kotihoito.service;

import harjoitustyo.kotihoito.model.Hoitaja;
import harjoitustyo.kotihoito.repository.HoitajaRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// Admin on kovakoodattu, hoitajat tunnistetaan hoitaja-taulun sähköpostin perusteella

@Service
public class UserDetailsService
        implements org.springframework.security.core.userdetails.UserDetailsService {

    private final HoitajaRepository hoitajaRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDetailsService(
            HoitajaRepository hoitajaRepository,
            PasswordEncoder passwordEncoder) {

        this.hoitajaRepository = hoitajaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        // Kovakoodattu admin
        if (email.equalsIgnoreCase("admin")) {
            return User.withUsername("admin")
                .password(passwordEncoder.encode("admin123"))
                .roles("ADMIN")
                .build();
        }

        // Etsi hoitaja tietokannasta
        Hoitaja hoitaja = hoitajaRepository.findBySahkoposti(email)
            .orElseThrow(() ->
                new UsernameNotFoundException("Hoitajaa ei löytynyt sähköpostilla: " + email)
            );

        return User.withUsername(hoitaja.getSahkoposti())
            .password(hoitaja.getPasswordHash())
            .roles("HOITAJA")
            .build();
    }
}