package arkapulse.core.dto.customer;

import arkapulse.core.entities.enums.RiskProfile;

public record CreateCustomerDTO(
        String name,
        String email,
        String password,
        RiskProfile riskProfile,
        String code) {
}
