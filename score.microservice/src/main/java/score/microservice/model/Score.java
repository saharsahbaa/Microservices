package score.microservice.model;


import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.lang.String;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonView;

import score.microservice.Views;

@Document(collection = "score")
public class Score {
	@Id
	private String id;
	@NotBlank
	@JsonView(Views.class)
	private String player;
	@NotBlank
	@JsonView(Views.class)
	private String gamecode;
	@JsonView(Views.class)
	private Long score;	
	@JsonView(Views.class)
	private Date date;
	public ArrayList<Map<String, Object>> history;
	
	public String getId() {
		return id;
	}
	public void setId(String player, String gamecode) {
		this.id = player+"$"+gamecode;
	}
	public String getPlayer() {
		return player;
	}
	public void setPlayer(String player) {
		this.player = player;
	}
	public String getGamecode() {
		return gamecode;
	}
	public void setGamecode(String gamecode) {
		this.gamecode = gamecode;
	}
	public Long getScore() {
		return score;
	}
	public void setScore(Long score) {
		this.score = score;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public ArrayList<Map<String, Object>> getHistory() {
		return history;
	}
	public void setHistory(ArrayList<Map<String, Object>> history) {
		this.history = history;
	}
	
	
	
	
}
