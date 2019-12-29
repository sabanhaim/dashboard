package compute_dashboard.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import compute_dashboard.utiles.utilies;

@Entity
public class User {

	@Id
	@GeneratedValue
	@NotNull
	@Column(name = "userId")
	private Integer userId;
	
	@NotNull
	@GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
    strategy = "org.hibernate.id.UUIDGenerator"
    )
	@Column(name = "awskey", nullable = false)
	private String awskey;

	@Column(name = "name")
	private String name;
	
	 protected User() {
		// TODO Auto-generated constructor stub
	}
	 

	public User(@NotNull Integer userId, @NotNull String awskey, String name) {
		super();
		this.userId = userId;
		this.awskey = awskey;
		this.name = name;
	} 
	 
	public User(String name) {
		// TODO Auto-generated constructor stub
		super();
		this.name=name;
		this.awskey=utilies.getAlphaNumericString(20);
	}

	public Integer getId() {
		return userId;
	}

	public String getAws_key() {
		return awskey;
	}
	
	public String getName() {
		return name;
	}
	
	
	
	
	
	
	
}
