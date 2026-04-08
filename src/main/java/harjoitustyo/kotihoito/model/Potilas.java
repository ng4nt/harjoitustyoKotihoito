package harjoitustyo.kotihoito.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "potilaat")
public class Potilas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "potilas_id")
    private Integer potilasId;

    @ManyToOne
    @JoinColumn(name = "suunnitelma_id")
    private Hoitosuunnitelma hoitosuunnitelma;

    @NotBlank(message = "Etunimi on pakollinen")
    @Column(name = "etunimi", nullable = false, length = 150)
    private String etunimi;

    @NotBlank(message = "Sukunimi on pakollinen")
    @Column(name = "sukunimi", nullable = false, length = 150)
    private String sukunimi;

    @NotBlank(message = "Henkilötunnus on pakollinen")
    @Size(min = 11, max = 20, message = "Hetu pituus on virheellinen")
    @Column(name = "hetu", nullable = false, unique = true, length = 20)
    private String hetu;

    @Column(name = "puhelin", length = 20)
    private String puhelin;

    @Column(name = "osoite", length = 150)
    private String osoite;

    public Potilas() {
    }

    public Potilas(String etunimi, String sukunimi, String hetu) {
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
        this.hetu = hetu;
    }

    public Integer getPotilasId() {
        return potilasId;
    }

    public Hoitosuunnitelma getHoitosuunnitelma() {
        return hoitosuunnitelma;
    }

    public void setHoitosuunnitelma(Hoitosuunnitelma hoitosuunnitelma) {
        this.hoitosuunnitelma = hoitosuunnitelma;
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

    public String getHetu() {
        return hetu;
    }

    public void setHetu(String hetu) {
        this.hetu = hetu;
    }

    public String getPuhelin() {
        return puhelin;
    }

    public void setPuhelin(String puhelin) {
        this.puhelin = puhelin;
    }

    public String getOsoite() {
        return osoite;
    }

    public void setOsoite(String osoite) {
        this.osoite = osoite;
    }
}