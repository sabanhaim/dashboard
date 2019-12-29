package compute_dashboard.dto;
import java.net.Inet4Address;
import compute_dashboard.model.Availability_zone;
import compute_dashboard.model.State;
import compute_dashboard.model.Type;


public class EC2_dto {

	private String name;
	
	private Integer state;
	
	private Integer type;
	
	private Integer az;
	
	private String  private_ip;
	
	private String  public_ip;
	
	private int active=-1;
	
	private Type new_type;
	
	private State new_state;
	
	private Inet4Address new_private_ip;
	
	private Inet4Address new_public_ip;
	
	private Availability_zone new_az;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public  Integer getAz() {
		return az;
	}

	public void setAz(Integer az) {
		this.az = az;
	}

	public String getPrivate_ip() {
		return private_ip;
	}

	public void setPrivate_ip(String private_ip) {
		this.private_ip = private_ip;
	}

	public String getPublic_ip() {
		return public_ip;
	}

	public void setPublic_ip(String public_ip) {
		this.public_ip = public_ip;
	}

	public void set_new_type(Type type) {
		// TODO Auto-generated method stub
		this.new_type=type;		
	}

	public Type getNew_type() {
		return new_type;
	}

	public void setNew_type(Type new_type) {
		this.new_type = new_type;
	}

	public State getNew_state() {
		return new_state;
	}

	public void setNew_state(State new_state) {
		this.new_state = new_state;
	}

	public Inet4Address getNew_private_ip() {
		return new_private_ip;
	}

	public void setNew_private_ip(Inet4Address new_private_ip) {
		this.new_private_ip = new_private_ip;
	}

	public Inet4Address getNew_public_ip() {
		return new_public_ip;
	}

	public void setNew_public_ip(Inet4Address new_public_ip) {
		this.new_public_ip = new_public_ip;
	}

	public Availability_zone getNew_az() {
		return new_az;
	}

	public void setNew_az(Availability_zone new_az) {
		this.new_az = new_az;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}
	

}
