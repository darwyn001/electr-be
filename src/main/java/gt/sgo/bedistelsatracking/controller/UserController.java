package gt.sgo.bedistelsatracking.controller;

import gt.sgo.bedistelsatracking.model.LoginRequest;
import gt.sgo.bedistelsatracking.model.LoginResponse;
import gt.sgo.bedistelsatracking.model.ResponseModel;
import gt.sgo.bedistelsatracking.model.Usuario;
import gt.sgo.bedistelsatracking.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> doLogin(@RequestBody LoginRequest loginRequest) {
        try {
            Usuario locatedUser = userRepository.findFirstByEmail(loginRequest.getUser());
            if (BCrypt.checkpw(loginRequest.getPasskey(), locatedUser.getContrasenia())) {
                HttpHeaders headers = new HttpHeaders();
                headers.add("Authorization", JWTController.getJWTToken(loginRequest.getUser()));

                return new ResponseEntity<>(LoginResponse.userToLoginResponse(locatedUser), headers, HttpStatus.OK);
            } else
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        } catch (Exception e) {
            ResponseModel responseModel = new ResponseModel(e.toString());
            return new ResponseEntity<>(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody Usuario usuario) {

        try {
            Usuario locatedUser = userRepository.findFirstByEmail(usuario.getEmail());
            if (locatedUser != null)
                return new ResponseEntity<>("El correo solicitado ya se encuentra registrado", HttpStatus.BAD_REQUEST);

            usuario.setContrasenia(BCrypt.hashpw(usuario.getContrasenia(), BCrypt.gensalt()));
            userRepository.save(usuario);
            return new ResponseEntity<>(new ResponseModel("Usuario creado exitosamente"), HttpStatus.OK);
        } catch (Exception e) {
            ResponseModel responseModel = new ResponseModel(e.toString());
            return new ResponseEntity<>(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
