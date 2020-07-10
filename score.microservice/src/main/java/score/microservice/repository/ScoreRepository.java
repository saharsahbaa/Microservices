package score.microservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import score.microservice.model.Score;

@Repository
public interface ScoreRepository extends MongoRepository<Score, String>{
	List<Score> findByPlayerOrderByScoreDesc(String player);
	List<Score> findByIdOrderByScoreDesc(String id);
	 Score findByid(String id);
	
}
