package player.microservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import player.microservice.model.Player;

@Repository
//@FeignClient(name = "Player")
public interface PlayerRepository extends MongoRepository<Player, String>{
	//@RequestMapping("player/{nickname}")
	Player findByNickname(String nickname);

}
