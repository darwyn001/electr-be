package gt.sgo.bedistelsatracking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.time.Instant;

@JsonSerialize
@Table(indexes = {@Index(name = "id_supervisor", columnList = "id_supervisor")})
@Entity
public class Gestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gestion", nullable = false)
    private Integer id;

    @Column(name = "branch", length = 15)
    private String branch;

    @Column(name = "nombre", length = 50)
    private String name;

    @Column(name = "direccion", length = 100)
    private String address;

    @Column(name = "fecha_montaje")
    private Instant creationDate;

    @Column(name = "fecha_apertura")
    private Instant openingDate;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_supervisor")
    private Usuario attendant;

    public Usuario getAttendant() {
        return attendant;
    }

    public void setAttendant(Usuario idSupervisor) {
        this.attendant = idSupervisor;
    }

    public Instant getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(Instant fechaApertura) {
        this.openingDate = fechaApertura;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant fechaMontaje) {
        this.creationDate = fechaMontaje;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String direccion) {
        this.address = direccion;
    }

    public String getName() {
        return name;
    }

    public void setName(String nombre) {
        this.name = nombre;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}