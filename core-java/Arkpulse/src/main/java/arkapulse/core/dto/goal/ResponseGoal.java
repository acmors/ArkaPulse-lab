package arkapulse.core.dto.goal;

import arkapulse.core.entities.Goal;
import arkapulse.core.entities.enums.GoalStatus;
import arkapulse.core.entities.enums.GoalType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ResponseGoal(
        Long id,
        Long customerId,
        GoalType type,
        String title,
        BigDecimal targetAmount,
        Integer horizonMonths,
        GoalStatus status,
        LocalDateTime createdAt
) {
    public ResponseGoal(Goal goal){
        this(
                goal.getId(),
                goal.getCustomer().getId(),
                goal.getType(),
                goal.getTitle(),
                goal.getTargetAmount(),
                goal.getHorizonMonths(),
                goal.getStatus(),
                goal.getCreatedAt()
        );
    }
}
