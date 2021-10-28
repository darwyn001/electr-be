package gt.sgo.bedistelsatracking.repository;

import gt.sgo.bedistelsatracking.model.State;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface StateRepository extends CrudRepository<State, Integer> {

    @Query("SELECT s FROM State s WHERE s.id = ?1")
    State findStateById(int id);
}
