package gt.sgo.bedistelsatracking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;

@Table(name = "observacion", indexes = {
        @Index(name = "responsable", columnList = "responsable")
})
@Entity
@JsonSerialize
public class Observation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_observacion", nullable = false)
    @JsonProperty
    private Integer id;

    @Column(name = "contenido")
    @JsonProperty
    private String content;

    @ManyToOne
    @JoinColumn(name = "responsable")
    @JsonProperty
    private Usuario attendant;

    public Usuario getAttendant() {
        return attendant;
    }

    public void setAttendant(Usuario attendant) {
        this.attendant = attendant;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}