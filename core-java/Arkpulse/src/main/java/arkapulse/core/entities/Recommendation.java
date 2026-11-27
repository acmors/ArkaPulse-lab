package arkapulse.core.entities;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "recommendations")
public class Recommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goal_id", nullable = false)
    private Goal goal;
    @Column(nullable = false)
    private String headline;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String explanation;
    @Column(name = "avoid_warning", columnDefinition = "TEXT", nullable = false)
    private String avoidWarning;
    @Column(name = "suggested_mix_json", columnDefinition = "TEXT", nullable = false)
    private String suggestedMixJson;
    @Column(name = "market_snapshot_json", columnDefinition = "TEXT", nullable = false)
    private String marketSnapshotJson;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public Recommendation() {
    }

    public Recommendation(Long id, Goal goal, String headline, String explanation, String avoidWarning, String suggestedMixJson, String marketSnapshotJson, LocalDateTime createdAt) {
        this.id = id;
        this.goal = goal;
        this.headline = headline;
        this.explanation = explanation;
        this.avoidWarning = avoidWarning;
        this.suggestedMixJson = suggestedMixJson;
        this.marketSnapshotJson = marketSnapshotJson;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getAvoidWarning() {
        return avoidWarning;
    }

    public void setAvoidWarning(String avoidWarning) {
        this.avoidWarning = avoidWarning;
    }

    public String getSuggestedMixJson() {
        return suggestedMixJson;
    }

    public void setSuggestedMixJson(String suggestedMixJson) {
        this.suggestedMixJson = suggestedMixJson;
    }

    public String getMarketSnapshotJson() {
        return marketSnapshotJson;
    }

    public void setMarketSnapshotJson(String marketSnapshotJson) {
        this.marketSnapshotJson = marketSnapshotJson;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
