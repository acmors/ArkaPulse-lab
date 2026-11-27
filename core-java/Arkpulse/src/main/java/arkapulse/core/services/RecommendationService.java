package arkapulse.core.services;

import arkapulse.core.dto.recommendation.ResponseRecommendation;
import arkapulse.core.entities.Goal;
import arkapulse.core.entities.Recommendation;
import arkapulse.core.entities.enums.GoalStatus;
import arkapulse.core.exceptions.ResourceNotFoundException;
import arkapulse.core.repositories.RecommendationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RecommendationService {

    private final RecommendationRepository repository;
    private final GoalService goalService;

    public RecommendationService(RecommendationRepository repository, GoalService goalService) {
        this.repository = repository;
        this.goalService = goalService;
    }

    @Transactional
    public ResponseRecommendation saveRecommentatio(Recommendation recommendation){
        Goal goal = goalService.findById(recommendation.getGoal().getId());
        Recommendation save = new Recommendation();
        save.setGoal(goal);
        save.setHeadline(recommendation.getHeadline());
        save.setExplanation(recommendation.getExplanation());
        save.setAvoidWarning(recommendation.getAvoidWarning());
        save.setSuggestedMixJson(recommendation.getSuggestedMixJson());
        save.setMarketSnapshotJson(recommendation.getMarketSnapshotJson());
        save.setCreatedAt(LocalDateTime.now());

        repository.save(save);
        goal.setStatus(GoalStatus.COMPLETED);

        return new ResponseRecommendation(recommendation);
    }

    @Transactional(readOnly = true)
    public ResponseRecommendation findLatestByGoalId(Long goalId){
        Recommendation recommendation = repository.findFirstByGoalIdOrderByCreatedAtDesc(goalId)
                .orElseThrow(() -> new ResourceNotFoundException("Objetivo não encontrado"));

        return  new ResponseRecommendation(recommendation);
    }

    @Transactional(readOnly = true)
    public List<ResponseRecommendation> findHistoryByGoalId(Long goalId){
        return repository.findByGoalIdOrderByCreatedAtDesc(goalId).stream()
                .map(ResponseRecommendation::new)
                .toList();
    }
}
