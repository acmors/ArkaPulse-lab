package arkapulse.core.repositories;

import arkapulse.core.entities.Goal;
import arkapulse.core.entities.enums.GoalStatus;
import arkapulse.core.entities.enums.GoalType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GoalRepository extends JpaRepository<Goal,Long> {

    @Query("""
        SELECT g from Goal g
        where g.customer.id = :customerId
            and (:status is null or  g.status = :status)
            and (:type is null or g.type = :type)
    """)
    List<Goal> findByFilters(@Param("customerId") Long customerId, @Param("status") GoalStatus status, @Param("type")GoalType type);

    List<Goal> findByType(GoalType type);
}
