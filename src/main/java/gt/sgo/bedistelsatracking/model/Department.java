package gt.sgo.bedistelsatracking.model;

import javax.persistence.*;

@Table(name = "departamento")
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_departamento", nullable = false)
    private Integer id;

    @Column(name = "nombre_departamento", length = 50)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String nombreDepartamento) {
        this.name = nombreDepartamento;
    }

}