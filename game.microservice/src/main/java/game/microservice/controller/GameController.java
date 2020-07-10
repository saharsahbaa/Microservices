package game.microservice.controller;

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

import game.microservice.model.Game;
import game.microservice.repository.GameRepository;

@RestController
@RequestMapping("/game")
public class GameController {
	@Autowired
	private GameRepository repo1;
	//Getting Game ID
	@GetMapping("/{code}")
	public ResponseEntity<?>  getGameByCode(@PathVariable String code){
		

		try {
			
			Game g = repo1.findById(code).get();
			return ResponseEntity.ok(g);
			
		} catch (Exception e) {
			return ResponseEntity.status(404).body("Not Found!");
		}
		
	}
	
	//Get All Games
		@GetMapping
		public ResponseEntity<?> getAllGames() {
			
			try {
				List<Game> g = repo1.findAll();
				return ResponseEntity.ok(g);
				
			} catch (Exception e) {
				return ResponseEntity.status(400).body("Bad Request!");
			}
			
			
		}	
	
	//Delete All Games
	@DeleteMapping
	public ResponseEntity<?> deleteAllGames(){
		try {
			repo1.deleteAll();
			return ResponseEntity.ok().body("Deleted!");
			
		} catch (Exception e) {
			return ResponseEntity.status(400).body(e);
		}
				
	}
	
	
	
	//Create Game
	@PostMapping
	public ResponseEntity<?> createGame(@RequestBody @Valid Game game) {
		Game findgame = repo1.findByCode(game.getCode());
		if(findgame != null) {
			return ResponseEntity.status(409).body("Conflict!");
		}
		
			repo1.save(game);
			return ResponseEntity.status(201).body("Created!");			
		
	}
	
	

	//Delete Game By Code
	@DeleteMapping("/{code}")
	public ResponseEntity<?> deleteGameByCode(@PathVariable String code){
		try {
			
			Game g = repo1.findById(code).get();
			repo1.deleteById(code);
			return ResponseEntity.ok(g);
			
		} catch (Exception e) {
			return ResponseEntity.status(404).body("Not Found!");
		}
	}
	
	//Update Game By Code
	@PutMapping("/{code}")
	public ResponseEntity<?> updateGameByCode(
			@PathVariable("code")String code,
			@RequestBody @Valid Game game){
		Game findgame = repo1.findByCode(game.getCode());
		if(findgame == null)
			return ResponseEntity.status(404).
					body("There is not Game with indicated Code!");
		else
			game.setCode(code);
			repo1.save(game);
			return ResponseEntity.ok(game);		
		
	}

}

