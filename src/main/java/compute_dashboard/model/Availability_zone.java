package compute_dashboard.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Availability_zone {
	

	@Id
	@GeneratedValue
	@NotNull
	@Column(name = "az_id")
	private Integer azId;
		
	@NotNull
	@Column(name = "name")
	private String name;
	
	@NotNull
	@Cascade(value = { CascadeType.MERGE  })
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "region_id")
	private Region region;
	
	protected Availability_zone() {
		super();
	}
	
	public Availability_zone(Region region, String name) {
		// TODO Auto-generated constructor stub
		super();
		this.name=name;
		this.region=region;
	}

	public Integer getId() {
		return azId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegion() {
		return region.getName()+this.getName()+"-----region_id:"+region.getId();
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	

}
