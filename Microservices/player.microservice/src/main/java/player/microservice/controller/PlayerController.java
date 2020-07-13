package player.microservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import player.microservice.model.Player;
import player.microservice.repository.PlayerRepository;

@RestController
@RequestMapping("/player")
public class PlayerController {
	@Autowired
	private PlayerRepository repo;
	//Getting Player ID
		@GetMapping("/{nickname}")
		public ResponseEntity<?>  getPlayerByID(@PathVariable String nickname){
			

			try {
				
				Player p = repo.findById(nickname).get();
				return ResponseEntity.ok(p);
				
			} catch (Exception e) {
				return ResponseEntity.status(404).body("Not Found!");
			}
			
		}
		
		//Get All Players
			@GetMapping
			public List<Player> getAllPlayers() {
				return repo.findAll();
				
			}	
		
		//Delete Players
		@DeleteMapping
		public String deleteAllPlayers(){
			repo.deleteAll();
			return "Deleted!";		
		}
		
		
		
		//Create Player
		@PostMapping
		public ResponseEntity<?> createPlayer(@RequestBody @Valid Player player) {
			Player findplayer = repo.findByNickname(player.getNickname());
			if(findplayer != null) {
				return ResponseEntity.status(409).body("Conflict!");
			}
			
				repo.save(player);
				return ResponseEntity.status(201).body("Created!");			
			
		}
		
		

		//Delete player By ID
		@DeleteMapping("/{nickname}")
		public ResponseEntity<?> deletePlayerByID(@PathVariable String nickname){
			try {
				
				Player p = repo.findById(nickname).get();
				repo.deleteById(nickname);
				return ResponseEntity.ok(p);
				
			} catch (Exception e) {
				return ResponseEntity.status(404).body("Not Found!");
			}
		}
		
		//Update Player By ID
		@PutMapping("/{nickname}")
		public ResponseEntity<?> updatePlayerByID(
				@PathVariable("nickname")String nickname,
				@RequestBody @Valid Player player){
			Player findplayer = repo.findByNickname(player.getNickname());
			if(findplayer == null)
				return ResponseEntity.status(404).
						body("There is not Player with indicated Nickname!");
			else
				player.setNickname(nickname);
				repo.save(player);
				return ResponseEntity.ok(player);
			
		}

}
