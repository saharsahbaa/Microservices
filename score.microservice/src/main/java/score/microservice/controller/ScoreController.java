package score.microservice.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonView;

import score.microservice.Views;
import score.microservice.model.Game;
import score.microservice.model.Player;
import score.microservice.model.Score;
import score.microservice.repository.ScoreRepository;

@RestController
@RequestMapping("/score")
public class ScoreController {
	@Autowired
	private ScoreRepository repo;
	@Autowired
	private RestTemplate restTemplate;
	//@Autowired
	//private MongoTemplate mongoTemplate;
	private ArrayList<Map<String, Object>> histori = new ArrayList<Map<String,Object>>();
	private Map<String, Object> map = new HashMap<String, Object>();
	 private String Url = "http://192.168.99.100:8080/player/";
	 private String Uri = "http://192.168.99.100:8081/game/";
	 
	
	//POST Score
	@PostMapping
	public ResponseEntity<?> createScore(@RequestBody @JsonView(Views.class) @Valid  Score score) {
		 score.setId(score.getPlayer(), score.getGamecode());
		 String id = score.getId();
		if((repo.findByid(id)) == null) {
			try {
				Player player = restTemplate.getForObject(Url+score.getPlayer(), Player.class);
				Game game = restTemplate.getForObject(Uri+score.getGamecode(), Game.class);
				map.put("score", score.getScore());
				map.put("date", score.getDate());
				histori.clear();
				histori.add(map);
				score.setHistory(histori);
				repo.save(score);
				return ResponseEntity.status(201).body("Created!");
				} catch (Exception e) {
					return ResponseEntity.status(400).body("Bad Request!");
				}
		}
			else return ResponseEntity.status(409).body("Conflict!");			
		
	}
	
	//GET ALL SCORES
	@GetMapping
	public ResponseEntity<?> getAllScores(){
		try {
			List<Score> score = repo.findAll();
			return ResponseEntity.ok(score);
		} catch (Exception e) {
			return ResponseEntity.status(400).body("Bad Request!");
		}
	}
	//UPDATE SCORE
	@PutMapping
	public ResponseEntity<?> updateScore(@RequestBody @Valid  Score score){
		try {
			//String id = score.getPlayer()+"$"+score.getGamecode();
			List<Score> sc = repo.findByPlayerOrderByScoreDesc(score.getPlayer());
			map.put("score", score.getScore());
			map.put("date", score.getDate());
			sc.get(0).history.add(map);
			repo.save(sc.get(0));
			return ResponseEntity.ok(sc);
			
		} catch (Exception e) {
			return ResponseEntity.status(400).body("Bad Request!");
		}
		
		//List<Score> s = repo.findByPlayerOrderByScoreDesc(player);
		
	}
	//DELETE ALL SCORES
	@DeleteMapping("/score")
	public ResponseEntity<?> deleteAllScores(){
		repo.deleteAll();
		return ResponseEntity.status(200).body("DELETED!");
	}
	
}
