package gt.sgo.bedistelsatracking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class ResponseModel {

    @JsonProperty
    String message;

    @JsonProperty
    Object recordset;

    public ResponseModel(Object recordset) {
        this.recordset = recordset;
    }

    public ResponseModel(String message) {
        this.message = message;
    }
}
