package arkapulse.core.repositories;

import arkapulse.core.entities.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
    //vai pegar as recomendações mais recentes da meta
    Optional<Recommendation> findFirstByGoalIdOrderByCreatedAtDesc(Long goalId);
    //vai puxar todo o historico de palpites da meta do felizardo
    List<Recommendation> findByGoalIdOrderByCreatedAtDesc(Long goalId);

}
