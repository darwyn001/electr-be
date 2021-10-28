package gt.sgo.bedistelsatracking.controller;

import gt.sgo.bedistelsatracking.model.Observation;
import gt.sgo.bedistelsatracking.model.ResponseModel;
import gt.sgo.bedistelsatracking.model.Usuario;
import gt.sgo.bedistelsatracking.repository.ObservationRepository;
import gt.sgo.bedistelsatracking.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/observations")
public class ObservationController {
    private final ObservationRepository observationRepository;
    private final UserRepository userRepository;

    public ObservationController(ObservationRepository observationRepository, UserRepository userRepository) {
        this.observationRepository = observationRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public ResponseEntity<?> getObservationsFromGestion() {
        Iterable<Observation> dataList = observationRepository.findAll();

        if (dataList.iterator().hasNext())
            return new ResponseEntity<>(dataList, HttpStatus.OK);

        return new ResponseEntity<>(new ResponseModel("No se encontraron observaciones"), HttpStatus.NO_CONTENT);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createObservation(@RequestBody Observation observation) {
        try {
            if (observation.getAttendant().getidUsuario() != 0L) {
                Usuario user = userRepository.findByIdUsuario(observation.getAttendant().getidUsuario());
                observation.setAttendant(user);
            }

            observationRepository.save(observation);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            ResponseModel responseModel = new ResponseModel(e.toString());
            return new ResponseEntity<>(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
