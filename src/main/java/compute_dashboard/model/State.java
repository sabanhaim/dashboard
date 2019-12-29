package compute_dashboard.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class State {


	@Id
	@GeneratedValue
	@NotNull
	@Column(name = "state_id")
	private Integer id;
	

	@NotNull
	@Column(name = "name")
	private String name;


	public Integer getId() {
		return id;
	}
	protected State() {
		super();
	}
	public State(@NotNull String name) {
		super();
		this.name = name;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getState() {
		return name;
	}


	public void setState(String state) {
		this.name = state;
	}
}
