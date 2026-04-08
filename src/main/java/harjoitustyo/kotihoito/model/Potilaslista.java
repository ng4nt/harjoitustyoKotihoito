package harjoitustyo.kotihoito.model;

import jakarta.persistence.*;

@Entity
@Table(name = "potilaslista",
       uniqueConstraints = {
           @UniqueConstraint(columnNames = {"hoitaja_id", "potilas_id"})
       })
public class Potilaslista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "potilaslista_id")
    private Integer potilaslistaId;

    @ManyToOne
    @JoinColumn(name = "hoitaja_id", nullable = false)
    private Hoitaja hoitaja;

    @ManyToOne
    @JoinColumn(name = "potilas_id", nullable = false)
    private Potilas potilas;

    public Potilaslista() {
    }

    public Potilaslista(Hoitaja hoitaja, Potilas potilas) {
        this.hoitaja = hoitaja;
        this.potilas = potilas;
    }

    public Integer getPotilaslistaId() {
        return potilaslistaId;
    }

    public Hoitaja getHoitaja() {
        return hoitaja;
    }

    public void setHoitaja(Hoitaja hoitaja) {
        this.hoitaja = hoitaja;
    }

    public Potilas getPotilas() {
        return potilas;
    }

    public void setPotilas(Potilas potilas) {
        this.potilas = potilas;
    }
}