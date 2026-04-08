package harjoitustyo.kotihoito.model;

import jakarta.persistence.*;

@Entity
@Table(name = "hoitosuunnitelmat")
public class Hoitosuunnitelma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "suunnitelma_id")
    private Integer suunnitelmaId;

    @Column(name = "kuvaus", length = 1000)
    private String kuvaus;

    public Hoitosuunnitelma() {
    }

    public Hoitosuunnitelma(String kuvaus) {
        this.kuvaus = kuvaus;
    }

    public Integer getSuunnitelmaId() {
        return suunnitelmaId;
    }

    public void setSuunnitelmaId(Integer suunnitelmaId) {
        this.suunnitelmaId = suunnitelmaId;
    }

    public String getKuvaus() {
        return kuvaus;
    }

    public void setKuvaus(String kuvaus) {
        this.kuvaus = kuvaus;
    }
}
