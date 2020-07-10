package game.microservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import game.microservice.model.Game;

@Repository
//@FeignClient(name= "Game")
public interface GameRepository extends MongoRepository<Game, String>{
	//@RequestMapping("/game/{code}")
	Game findByCode(String code);

}
