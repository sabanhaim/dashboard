package compute_dashboard.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import compute_dashboard.dto.EC2_dto;
import java.net.Inet4Address;
import java.net.UnknownHostException;

@Entity
@Table(name="instances")
public class EC2_instance {
	
	
	@Id
	@GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
    strategy = "org.hibernate.id.UUIDGenerator"
    )
	@NotNull
	@Column(name = "id")
	private String  id;

	@Column(name = "name", nullable = false)
	private String name;
	
	@NotNull
	@Cascade(value = { CascadeType.MERGE })
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "state_id")
	private State state;
	
	@NotNull
	@Cascade(value = { CascadeType.MERGE })
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "type_id")
	private Type type;
	
	@NotNull
	@Cascade(value = { CascadeType.MERGE })
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "az_id")
	private Availability_zone az;
	
	@NotNull
	@Column(name = "private_ip",nullable = false)
	private Inet4Address  privateIp;
	
	@NotNull
	@Column(name = "public_ip",nullable = false)
	private Inet4Address  publicIp;
	
	@NotNull
	@Column(name = "active",nullable = false)
	private int  active;
	
	@NotNull
	@Cascade(value = { CascadeType.MERGE })
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "userId", nullable = false)
	 private User user;
	
	public EC2_instance(String name,  State state, Type type, Availability_zone az,
			 String private_ip, String public_ip,  int active,
			 User user) throws UnknownHostException {
		super();
		this.name = name;
		this.state = state;
		this.type = type;
		this.az = az;
		this.privateIp=(@NotNull Inet4Address) Inet4Address.getByName(private_ip);
		this.publicIp=(@NotNull Inet4Address)Inet4Address.getByName(public_ip);
		this.active = active;
		this.user = user;
	}

	public EC2_instance(@Valid EC2_dto ec2_dto, User user) throws UnknownHostException {
		// TODO Auto-generated constructor stub
		this.name=ec2_dto.getName();
		this.az=ec2_dto.getNew_az();
		this.active=ec2_dto.getActive();
		this.privateIp=ec2_dto.getNew_private_ip(); 
		this.publicIp=ec2_dto.getNew_public_ip();
		this.type=ec2_dto.getNew_type();
		this.state=ec2_dto.getNew_state();
		this.user=user;
	}
	 protected EC2_instance() {
			// TODO Auto-generated constructor stub
	}
	
	//public String getId() {
	//		return id;
	//}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getAz() {
		return az.getRegion();
	}

	public void setAz(@NotNull Availability_zone az) {
		this.az = az;
	}


	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}


	public String getType() {
		return type.getName()+"."+type.getSize();
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getState() {
		return state.getState();
	}

	public void setState(State state) {
		this.state = state;
	}

	public String getUser() {
		return user.getName();
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Inet4Address getPrivateIp() {
		return privateIp;
	}

	public void setPrivateIp(Inet4Address privateIp) {
		this.privateIp = privateIp;
	}

	public Inet4Address getPublicIp() {
		return publicIp;
	}

	public void setPublicIp(Inet4Address publicIp) {
		this.publicIp = publicIp;
	}
	
	
	
}
