package harjoitustyo.kotihoito.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "hoitopaivakirjat")
public class Hoitopaivakirja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paivakirja_id")
    private Integer paivakirjaId;

    @ManyToOne
    @JoinColumn(name = "potilas_id", nullable = false)
    private Potilas potilas;

    @ManyToOne
    @JoinColumn(name = "hoitaja_id", nullable = false)
    private Hoitaja hoitaja;

    @Column(name = "merkinta", length = 1000)
    private String merkinta;

    @Column(name = "paivays")
    private LocalDateTime paivays;

    public Hoitopaivakirja() {
    }

    public Hoitopaivakirja(Potilas potilas, Hoitaja hoitaja, String merkinta) {
        this.potilas = potilas;
        this.hoitaja = hoitaja;
        this.merkinta = merkinta;
        this.paivays = LocalDateTime.now();
    }

    @PrePersist
    public void onCreate() {
        if (this.paivays == null) {
            this.paivays = LocalDateTime.now();
        }
    }

    public Integer getPaivakirjaId() {
        return paivakirjaId;
    }

    public Potilas getPotilas() {
        return potilas;
    }

    public void setPotilas(Potilas potilas) {
        this.potilas = potilas;
    }

    public Hoitaja getHoitaja() {
        return hoitaja;
    }

    public void setHoitaja(Hoitaja hoitaja) {
        this.hoitaja = hoitaja;
    }

    public String getMerkinta() {
        return merkinta;
    }

    public void setMerkinta(String merkinta) {
        this.merkinta = merkinta;
    }

    public LocalDateTime getPaivays() {
        return paivays;
    }

    public void setPaivays(LocalDateTime paivays) {
        this.paivays = paivays;
    }
}