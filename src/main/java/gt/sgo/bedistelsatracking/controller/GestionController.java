package gt.sgo.bedistelsatracking.controller;

import gt.sgo.bedistelsatracking.model.Gestion;
import gt.sgo.bedistelsatracking.model.ResponseModel;
import gt.sgo.bedistelsatracking.model.State;
import gt.sgo.bedistelsatracking.model.Usuario;
import gt.sgo.bedistelsatracking.repository.GestionRepository;
import gt.sgo.bedistelsatracking.repository.StateRepository;
import gt.sgo.bedistelsatracking.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gestions")
public class GestionController {
    private final GestionRepository gestionRepository;
    private final UserRepository userRepository;
    private final StateRepository stateRepository;

    public GestionController(GestionRepository gestionRepository, UserRepository userRepository, StateRepository stateRepository) {
        this.gestionRepository = gestionRepository;
        this.userRepository = userRepository;
        this.stateRepository = stateRepository;
    }

    @GetMapping("/")
    public ResponseEntity<?> getGestions() {

        Iterable<Gestion> dataList = gestionRepository.findAll();

        if (dataList.iterator().hasNext())
            return new ResponseEntity<>(dataList, HttpStatus.OK);

        return new ResponseEntity<>(new ResponseModel("No se encontraron gestiones"), HttpStatus.NO_CONTENT);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createGestion(@RequestBody Gestion gestion) {
        try {
            if (gestion.getAttendant().getidUsuario() != 0L) {
                Usuario user = userRepository.findByIdUsuario(gestion.getAttendant().getidUsuario());
                gestion.setAttendant(user);
            }
            if (gestion.getState().getId() != 0L) {
                State state = stateRepository.findStateById(gestion.getState().getId());
                gestion.setState(state);
            }

            gestionRepository.save(gestion);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            ResponseModel responseModel = new ResponseModel(e.toString());
            return new ResponseEntity<>(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
