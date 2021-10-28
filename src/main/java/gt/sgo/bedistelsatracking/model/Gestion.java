package gt.sgo.bedistelsatracking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.time.Instant;

@Table(name = "gestion", indexes = {
        @Index(name = "id_supervisor", columnList = "id_supervisor")
})
@JsonSerialize
@Entity
public class Gestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gestion", nullable = false)
    @JsonProperty
    private Integer id;

    @Column(name = "direccion", length = 100)
    @JsonProperty
    private String address;

    @Column(name = "branch", length = 15)
    @JsonProperty
    private String branch;

    @Column(name = "fecha_montaje")
    @JsonProperty
    private Instant creationDate;

    @Column(name = "nombre", length = 50)
    @JsonProperty
    private String name;

    @Column(name = "fecha_apertura")
    @JsonProperty
    private Instant openingDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_supervisor")
    @JsonProperty
    private Usuario attendant;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_estado")
    @JsonProperty
    private State state;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Usuario getAttendant() {
        return attendant;
    }

    public void setAttendant(Usuario attendant) {
        this.attendant = attendant;
    }

    public Instant getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(Instant openingDate) {
        this.openingDate = openingDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}