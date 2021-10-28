package gt.sgo.bedistelsatracking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class LoginResponse {
    @JsonProperty("name")
    String name;
    @JsonProperty("lastname")
    String lastname;
    @JsonProperty("email")
    String email;
    @JsonProperty("id")
    long id;
    @JsonProperty("employeId")
    int codigoEmpleado;

    public LoginResponse(long id, String name, String lastname, String email, int codigoEmpleado) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.codigoEmpleado = codigoEmpleado;
    }

    public static LoginResponse userToLoginResponse(Usuario usuario) {
        LoginResponse x = new LoginResponse(usuario.idUsuario, usuario.nombre, usuario.apellido, usuario.email, usuario.codigoEmpleado);
        return x;
    }
}
