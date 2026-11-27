package arkapulse.core.dto.recommendation;

import arkapulse.core.entities.Recommendation;

import java.time.LocalDateTime;

public record ResponseRecommendation(
        Long id,
        Long goalId,
        String headline,
        String explanation,
        String avoidWarning,
        String suggestedMixJson,
        String marketSnapshotJson,
        LocalDateTime createdAt
) {
    public ResponseRecommendation(Recommendation recommendation){
        this(
                recommendation.getId(),
                recommendation.getGoal().getId(),
                recommendation.getHeadline(),
                recommendation.getExplanation(),
                recommendation.getAvoidWarning(),
                recommendation.getSuggestedMixJson(),
                recommendation.getMarketSnapshotJson(),
                recommendation.getCreatedAt()
        );
    }
}
