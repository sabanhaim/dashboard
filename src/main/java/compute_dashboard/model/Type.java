package compute_dashboard.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Type {

	@Id
	@GeneratedValue
	@NotNull
	@Column(name = "type_id")
	private Integer typeId;
	
	
	@NotNull
	@Column(name = "name")
	private String name;
	
	@NotNull
	@Column(name = "size")
	private String size;
	
	public Type() {
		super();
	}

	public Type(String name, String size) {
		// TODO Auto-generated constructor stub
		this.name=name;
		this.size=size;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Integer getType_id() {
		return typeId;
	}


}
