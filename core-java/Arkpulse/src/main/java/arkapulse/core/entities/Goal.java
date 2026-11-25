package arkapulse.core.entities;
import arkapulse.core.entities.enums.GoalStatus;
import arkapulse.core.entities.enums.GoalType;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "goals")
public class Goal {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private GoalType type;
    private String title;
    private BigDecimal targetAmount;
    private Integer horizonMonths;

    @Enumerated(EnumType.STRING)
    private GoalStatus status;
    private LocalDateTime createdAt;

    public Goal() {
    }

    public Goal(Long id, Customer customer, GoalType type, String title, BigDecimal targetAmount, Integer horizonMonths, GoalStatus status, LocalDateTime createdAt) {
        this.id = id;
        this.customer = customer;
        this.type = type;
        this.title = title;
        this.targetAmount = targetAmount;
        this.horizonMonths = horizonMonths;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public GoalType getType() {
        return type;
    }

    public void setType(GoalType type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(BigDecimal targetAmount) {
        this.targetAmount = targetAmount;
    }

    public Integer getHorizonMonths() {
        return horizonMonths;
    }

    public void setHorizonMonths(Integer horizonMonths) {
        this.horizonMonths = horizonMonths;
    }

    public GoalStatus getStatus() {
        return status;
    }

    public void setStatus(GoalStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
