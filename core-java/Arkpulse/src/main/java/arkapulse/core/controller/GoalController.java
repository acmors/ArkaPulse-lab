package arkapulse.core.controller;

import arkapulse.core.dto.goal.CreateGoalDTO;
import arkapulse.core.dto.goal.ResponseGoal;
import arkapulse.core.services.GoalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/goal")
public class GoalController {

    private final GoalService goalService;

    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @PostMapping
    public ResponseEntity<ResponseGoal> createGoal(@RequestBody CreateGoalDTO create){
        return  ResponseEntity.status(HttpStatus.CREATED).body(goalService.createGoal(create));
    }
}
