package gt.sgo.bedistelsatracking.repository;

import gt.sgo.bedistelsatracking.model.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {

}
