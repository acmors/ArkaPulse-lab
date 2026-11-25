package arkapulse.core.services;

import arkapulse.core.dto.goal.CreateGoalDTO;
import arkapulse.core.dto.goal.ResponseGoal;
import arkapulse.core.entities.Customer;
import arkapulse.core.entities.Goal;
import arkapulse.core.entities.enums.GoalStatus;
import arkapulse.core.entities.enums.GoalType;
import arkapulse.core.exceptions.ResourceNotFoundException;
import arkapulse.core.repositories.GoalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GoalService {

    private final GoalRepository goalRepository;
    private final CustomerService customerService;

    public GoalService(GoalRepository goalRepository, CustomerService customerService) {
        this.goalRepository = goalRepository;
        this.customerService = customerService;
    }

    public ResponseGoal createGoal(CreateGoalDTO create) {
        Customer customer = customerService.findByIdIntern(create.customerId());
        Goal goal = new  Goal();
        goal.setCustomer(customer);
        goal.setType(create.type());
        goal.setTitle(create.title());
        goal.setTargetAmount(create.targetAmount());
        goal.setHorizonMonths(create.horizonMonths());
        goal.setStatus(create.status());
        goal.setCreatedAt(LocalDateTime.now());
        goalRepository.save(goal);

        return new ResponseGoal(
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

    @Transactional(readOnly = true)
    public List<ResponseGoal> findGoalsByFilters(Long customerId, GoalStatus status, GoalType type){
        return goalRepository.findByFilters(customerId, status, type).stream()
                .map(ResponseGoal::new)
                .toList();
    }

    @Transactional
    public Goal findById(Long id){
        return goalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Goal not found."));
    }

}
