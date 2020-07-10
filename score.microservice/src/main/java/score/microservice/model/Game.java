package score.microservice.model;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "game")
public class Game {
	@Id
	@NotBlank
	private String code;
	@NotBlank
	private String title;
	@NotBlank
	private String software_house;
	@NotBlank
	private String version;
	private String release_year;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSoftware_house() {
		return software_house;
	}
	public void setSoftware_house(String software_house) {
		this.software_house = software_house;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getRelease_year() {
		return release_year;
	}
	public void setRelease_year(String release_year) {
		this.release_year = release_year;
	}
	@Override
	public String toString() {
		return "Game [code=" + code + ", title=" + title + ", software_house=" + software_house + ", version=" + version
				+ ", release_year=" + release_year + "]";
	}
	
}
