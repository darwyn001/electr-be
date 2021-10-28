package gt.sgo.bedistelsatracking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "responsable")
    private Usuario attendant;

    @JsonProperty
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "estado")
    private State state;

    @JsonProperty
    @Column(name = "fecha_creacion", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @CreationTimestamp
    private Date created;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}