package arkapulse.core.dto.customer;

import arkapulse.core.entities.enums.RiskProfile;

import java.time.LocalDateTime;

public record ResponseCustomer(
         Long id,
         String name,
         String email,
         boolean isVerified,
         RiskProfile riskProfile,
         LocalDateTime createdAt
) {
}
