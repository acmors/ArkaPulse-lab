package arkapulse.core.dto.customer;

public record SuitabilityAnswersDTO(
        int ageScore,
        int lossToToleranceScore,
        int experienceScore
) {
}
