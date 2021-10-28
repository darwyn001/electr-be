package gt.sgo.bedistelsatracking.controller;

import gt.sgo.bedistelsatracking.model.Department;
import gt.sgo.bedistelsatracking.model.ResponseModel;
import gt.sgo.bedistelsatracking.repository.DepartmentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    private final DepartmentRepository departmentRepository;

    public DepartmentController(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @GetMapping("/")
    public ResponseEntity<?> getDepartments() {
        Iterable<Department> dataList = departmentRepository.findAll();

        if (dataList.iterator().hasNext())
            return new ResponseEntity<>(dataList, HttpStatus.OK);

        return new ResponseEntity<>(new ResponseModel("No se encontraron departamentos"), HttpStatus.NO_CONTENT);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createActivity(@RequestBody Department department) {
        try {
            departmentRepository.save(department);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            ResponseModel responseModel = new ResponseModel(e.toString());
            return new ResponseEntity<>(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
