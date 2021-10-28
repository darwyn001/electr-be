package gt.sgo.bedistelsatracking.controller;

import gt.sgo.bedistelsatracking.model.ResponseModel;
import gt.sgo.bedistelsatracking.model.Role;
import gt.sgo.bedistelsatracking.repository.RoleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    private final RoleRepository roleRepository;

    public RoleController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @GetMapping("/")
    public ResponseEntity<?> getRoles() {
        Iterable<Role> dataList = roleRepository.findAll();

        if (dataList.iterator().hasNext())
            return new ResponseEntity<>(dataList, HttpStatus.OK);

        return new ResponseEntity<>(new ResponseModel("No se encontraron roles"), HttpStatus.NO_CONTENT);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createRole(@RequestBody Role role) {
        try {
            roleRepository.save(role);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            ResponseModel responseModel = new ResponseModel(e.toString());
            return new ResponseEntity<>(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
