package gt.sgo.bedistelsatracking.controller;

import gt.sgo.bedistelsatracking.model.Activity;
import gt.sgo.bedistelsatracking.model.ResponseModel;
import gt.sgo.bedistelsatracking.model.State;
import gt.sgo.bedistelsatracking.model.Usuario;
import gt.sgo.bedistelsatracking.repository.ActivitiesRepository;
import gt.sgo.bedistelsatracking.repository.StateRepository;
import gt.sgo.bedistelsatracking.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {
    private final ActivitiesRepository activitiesRepository;
    private final UserRepository userRepository;
    private final StateRepository stateRepository;

    public ActivityController(ActivitiesRepository activitiesRepository, UserRepository userRepository, StateRepository stateRepository) {
        this.activitiesRepository = activitiesRepository;
        this.userRepository = userRepository;
        this.stateRepository = stateRepository;
    }

    @GetMapping("/")
    public ResponseEntity<?> getActivities() {
        Iterable<Activity> dataList = activitiesRepository.findAll();

        if (dataList.iterator().hasNext())
            return new ResponseEntity<>(dataList, HttpStatus.OK);

        return new ResponseEntity<>(new ResponseModel("No se encontraron actividades"), HttpStatus.NO_CONTENT);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createActivity(@RequestBody Activity activity) {
        try {
            if (activity.getAttendant().getidUsuario() != 0L) {
                Usuario user = userRepository.findByIdUsuario(activity.getAttendant().getidUsuario());
                activity.setAttendant(user);
            }
            if (activity.getState().getId() != 0L) {
                State state = stateRepository.findStateById(activity.getState().getId());
                activity.setState(state);
            }

            activitiesRepository.save(activity);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            ResponseModel responseModel = new ResponseModel(e.toString());
            return new ResponseEntity<>(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
