package harjoitustyo.kotihoito.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Entity
@Table(name = "hoitajat")
public class Hoitaja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hoitaja_id")
    private Integer hoitajaId;

    @NotBlank(message = "Sähköposti on pakollinen")
    @Column(name = "sahkoposti", nullable = false, length = 150)
    private String sahkoposti;

    @Column(name = "puhelin", length = 20)
    private String puhelin;

    @NotBlank(message = "Etunimi on pakollinen")
    @Column(name = "etunimi", nullable = false, length = 150)
    private String etunimi;

    @NotBlank(message = "Sukunimi on pakollinen")
    @Column(name = "sukunimi", nullable = false, length = 150)
    private String sukunimi;

    @NotBlank(message = "Salasana on pakollinen")
    @Column(name = "passwordhash", nullable = false, length = 150)
    private String passwordHash;

    @Column(name = "kayttajaluotu")
    private LocalDateTime kayttajaLuotu;

    public Hoitaja() {
    }

    public Hoitaja(String sahkoposti, String etunimi, String sukunimi, String passwordHash) {
        this.sahkoposti = sahkoposti;
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
        this.passwordHash = passwordHash;
        this.kayttajaLuotu = LocalDateTime.now();
    }

    public Integer getHoitajaId() {
        return hoitajaId;
    }

    public String getSahkoposti() {
        return sahkoposti;
    }

    public void setSahkoposti(String sahkoposti) {
        this.sahkoposti = sahkoposti;
    }

    public String getPuhelin() {
        return puhelin;
    }

    public void setPuhelin(String puhelin) {
        this.puhelin = puhelin;
    }

    public String getEtunimi() {
        return etunimi;
    }

    public void setEtunimi(String etunimi) {
        this.etunimi = etunimi;
    }

    public String getSukunimi() {
        return sukunimi;
    }

    public void setSukunimi(String sukunimi) {
        this.sukunimi = sukunimi;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public LocalDateTime getKayttajaLuotu() {
        return kayttajaLuotu;
    }

    public void setKayttajaLuotu(LocalDateTime kayttajaLuotu) {
        this.kayttajaLuotu = kayttajaLuotu;
    }

    public void setHoitajaId(Integer hoitajaId) {
    this.hoitajaId = hoitajaId;
}
}