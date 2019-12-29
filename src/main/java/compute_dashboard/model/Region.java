package compute_dashboard.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Region {
	

	@Id
	@GeneratedValue
	@NotNull
	@Column(name = "region_id")
	private Integer id;
	
	
	
	@NotNull
	@Column(name = "name")
	private String name;
	
	protected Region() {
		super();
	}
	
	public Region(@NotNull String name) {
		super();
		this.name = name;
	}


	public Integer getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	
}
