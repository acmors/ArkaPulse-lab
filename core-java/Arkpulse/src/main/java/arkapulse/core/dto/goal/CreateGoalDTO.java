package arkapulse.core.dto.goal;

import arkapulse.core.entities.enums.GoalStatus;
import arkapulse.core.entities.enums.GoalType;

import java.math.BigDecimal;

public record CreateGoalDTO(
        Long customerId,
        GoalType type,
        String title,
        BigDecimal targetAmount,
        Integer horizonMonths,
        GoalStatus status
) {
}
