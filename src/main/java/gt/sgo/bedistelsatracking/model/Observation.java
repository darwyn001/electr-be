package gt.sgo.bedistelsatracking.model;

import javax.persistence.*;

@Table(name = "observacion")
@Entity
public class Observation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_observacion", nullable = false)
    private Integer id;

    @Column(name = "contenido")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String contenido) {
        this.content = contenido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}