package harjoitustyo.kotihoito.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.http.HttpMethod;
import harjoitustyo.kotihoito.service.UserDetailsService;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**", "/api/**"))
            .headers(headers -> headers
                        .frameOptions(frame -> frame.sameOrigin()))
            .authorizeHttpRequests(auth -> auth

                // Login sallitaan kaikille
                .requestMatchers("/login", "/css/**", "/js/**").permitAll()

                // Swagger sallitaan kaikille
                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").authenticated()
                .requestMatchers("/", "/h2-console/**").authenticated()

                //hoitaja.html oikeudet
                .requestMatchers(HttpMethod.GET, "/hoitajat").hasAnyRole("HOITAJA", "ADMIN")
                .requestMatchers("/hoitajat/**").hasRole("ADMIN")

                // Hoitajalle vain tietyt endpointit
                .requestMatchers(
                        HttpMethod.GET,
                        "/api/potilaat/*/hoitosuunnitelma",
                        "/api/potilaat/*/paivakirjat"
                ).hasAnyRole("HOITAJA", "ADMIN")

                .requestMatchers(
                        HttpMethod.POST,
                        "/api/potilaat/*/hoitosuunnitelma",
                        "/api/potilaat/*/paivakirjat/**"
                ).hasAnyRole("HOITAJA", "ADMIN")

                // Adminille kaikki oikeudet
                .requestMatchers("/api/**").hasRole("ADMIN")

                .anyRequest().authenticated()
            )

            .formLogin(form -> form
                .defaultSuccessUrl("/hoitajat", true)
                .permitAll()
            )
  
            .httpBasic(Customizer.withDefaults())
            .userDetailsService(userDetailsService)
            .logout(Customizer.withDefaults());

        return http.build();
    }

}