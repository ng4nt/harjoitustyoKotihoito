package harjoitustyo.kotihoito.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "laakkeet")
public class Laake {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "laake_id")
    private Integer laakeId;

    @NotNull(message = "Hoitosuunnitelma on pakollinen")
    @ManyToOne
    @JoinColumn(name = "suunnitelma_id", nullable = false)
    private Hoitosuunnitelma hoitosuunnitelma;

    @NotBlank(message = "Lääkkeen nimi on pakollinen")
    @Column(name = "nimi", nullable = false, length = 150)
    private String nimi;

    @NotBlank(message = "Annostus on pakollinen")
    @Size(max = 150, message = "Annostus saa olla enintään 150 merkkiä")
    @Column(name = "annos", nullable = false, length = 150)
    private String annos;

    @Column(name = "aika")
    private LocalTime aika;

    @Size(max = 150, message = "Saa olla enintään 150 merkkiä")
    @Column(name = "kuinka_usein", length = 150)
    private String kuinkaUsein;

    @Column(name = "aloituspv")
    private LocalDate aloitusPv;

    @Column(name = "lopetuspv")
    private LocalDate lopetusPv;

    public Laake() {
    }

    public Laake(Hoitosuunnitelma hoitosuunnitelma, String nimi, String annos) {
        this.hoitosuunnitelma = hoitosuunnitelma;
        this.nimi = nimi;
        this.annos = annos;
    }

    public Integer getLaakeId() {
        return laakeId;
    }

    public Hoitosuunnitelma getHoitosuunnitelma() {
        return hoitosuunnitelma;
    }

    public void setHoitosuunnitelma(Hoitosuunnitelma hoitosuunnitelma) {
        this.hoitosuunnitelma = hoitosuunnitelma;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getAnnos() {
        return annos;
    }

    public void setAnnos(String annos) {
        this.annos = annos;
    }

    public LocalTime getAika() {
        return aika;
    }

    public void setAika(LocalTime aika) {
        this.aika = aika;
    }

    public String getKuinkaUsein() {
        return kuinkaUsein;
    }

    public void setKuinkaUsein(String kuinkaUsein) {
        this.kuinkaUsein = kuinkaUsein;
    }

    public LocalDate getAloitusPv() {
        return aloitusPv;
    }

    public void setAloitusPv(LocalDate aloitusPv) {
        this.aloitusPv = aloitusPv;
    }

    public LocalDate getLopetusPv() {
        return lopetusPv;
    }

    public void setLopetusPv(LocalDate lopetusPv) {
        this.lopetusPv = lopetusPv;
    }
}