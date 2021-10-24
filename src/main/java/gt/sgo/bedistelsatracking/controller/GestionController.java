package gt.sgo.bedistelsatracking.controller;

import gt.sgo.bedistelsatracking.model.Gestion;
import gt.sgo.bedistelsatracking.model.ResponseModel;
import gt.sgo.bedistelsatracking.repository.GestionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gestions")
public class GestionController {
    private final GestionRepository gestionRepository;

    public GestionController(GestionRepository gestionRepository) {
        this.gestionRepository = gestionRepository;
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
            gestionRepository.save(gestion);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            ResponseModel responseModel = new ResponseModel(e.toString());
            return new ResponseEntity<>(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
