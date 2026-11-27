package arkapulse.core.controller;

import arkapulse.core.dto.recommendation.ResponseRecommendation;
import arkapulse.core.entities.Recommendation;
import arkapulse.core.services.RecommendationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @PostMapping
    public ResponseEntity<ResponseRecommendation> saveRecommendation(@RequestBody Recommendation recommendation){
        return ResponseEntity.status(HttpStatus.CREATED).body(recommendationService.saveRecommentatio(recommendation));
    }

    @GetMapping("/recommendation/latest/{id}")
    public  ResponseEntity<ResponseRecommendation> getLatestRecommendation(@PathVariable Long id){
        return ResponseEntity.ok().body(recommendationService.findLatestByGoalId(id));
    }

    @GetMapping("/history/{id}")
    public ResponseEntity<List<ResponseRecommendation>> findHistory(@PathVariable Long id){
        return ResponseEntity.ok().body(recommendationService.findHistoryByGoalId(id));
    }
}
