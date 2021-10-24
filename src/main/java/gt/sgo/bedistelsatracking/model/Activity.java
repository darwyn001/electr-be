package gt.sgo.bedistelsatracking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;

@Table(name = "actividad", indexes = {
        @Index(name = "estado", columnList = "estado"),
        @Index(name = "responsable", columnList = "responsable")
})
@Entity
@JsonSerialize
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_actividad", nullable = false)
    @JsonProperty
    private Integer id;

    @JsonProperty
    @Column(name = "nombre", length = 50)
    private String name;

    @JsonProperty
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "responsable")
    private Usuario attendant;

    @JsonProperty
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "estado")
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

    public void setAttendant(Usuario responsable) {
        this.attendant = responsable;
    }

    public String getName() {
        return name;
    }

    public void setName(String nombre) {
        this.name = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}