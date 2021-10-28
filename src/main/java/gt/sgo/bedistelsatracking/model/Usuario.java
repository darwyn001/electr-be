package gt.sgo.bedistelsatracking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@JsonSerialize
@Entity
public class Usuario {
    @Id
    @GeneratedValue
    @Column(name = "id_usuario", nullable = false)
    @JsonProperty("id")
    long idUsuario;

    @JsonProperty("name")
    String nombre;
    @JsonProperty("lastname")
    String apellido;
    @JsonProperty("email")
    String email;
    @JsonProperty(value = "password", access = JsonProperty.Access.WRITE_ONLY)
    String contrasenia;
    @JsonProperty("employe_id")
    int codigoEmpleado;

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getEmail() {
        return email;
    }

    public long getidUsuario() {
        return idUsuario;
    }

}
