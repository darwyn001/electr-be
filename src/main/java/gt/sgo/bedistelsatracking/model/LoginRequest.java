package gt.sgo.bedistelsatracking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class LoginRequest {
    @JsonProperty("email")
    String user;
    @JsonProperty("password")
    String passkey;

    public String getUser() {
        return user;
    }

    public String getPasskey() {
        return passkey;
    }
}
