package compute_dashboard.utiles;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import compute_dashboard.model.Availability_zone;
import compute_dashboard.model.EC2_instance;
import compute_dashboard.model.Region;
import compute_dashboard.model.State;
import compute_dashboard.model.Type;
import compute_dashboard.model.User;
import compute_dashboard.repository.Availability_zone_repository;
import compute_dashboard.repository.EC2_repository;
import compute_dashboard.repository.Region_repository;
import compute_dashboard.repository.State_repository;
import compute_dashboard.repository.Type_repository;
import compute_dashboard.repository.User_repository;

public class first_init {
	
	private User_repository user_repository;
	
	private  EC2_repository ec2_repository;
	
	private  Region_repository region_repository;
	
	private  Availability_zone_repository availability_zone_repository;
	
	private  Type_repository type_repository;
	
	private   State_repository state_repository;
	
	public  first_init(User_repository user_repository
									,EC2_repository ec2_repository
									,Region_repository region_repository
									,Availability_zone_repository availability_zone_repository
									,Type_repository type_repository
									,State_repository state_repository) throws UnknownHostException {
		//this.ec2_repository=ec2_repository;
		this.availability_zone_repository=availability_zone_repository;
		this.region_repository=region_repository;
		this.state_repository=state_repository;
		this.type_repository=type_repository;
		this.user_repository=user_repository;
		this.ec2_repository=ec2_repository;
		users_init();
		region_init();
		az_init();
		type_init();
		state_init();
		ec2_instance_init();
	}
	

	private void users_init() {
		this.user_repository.save(new User(1,"aaaa-bbbb-cccc-dddd","admin"));
		this.user_repository.save(new User(2,"test-test-test-test","test"));
		this.user_repository.save(new User(3,"test2-test2-test2-test2","test2"));     
        
	}
	private void region_init() {
		region_repository.save(new Region("us-east-2"));
        region_repository.save(new Region("us-east-1"));
        region_repository.save(new Region("us-west-1"));
        region_repository.save(new Region("us-west-2"));
        region_repository.save(new Region("ap-east-1"));
        region_repository.save(new Region("ap-east-2"));
        region_repository.save(new Region("ap-south-1"));
        region_repository.save(new Region("ap-northeast-3"));
        region_repository.save(new Region("ap-northeast-2"));
        region_repository.save(new Region("eu-west-1"));
        region_repository.save(new Region("eu-west-2"));
        region_repository.save(new Region("eu-west-3"));
			
	}
	private  void az_init() {
		List<Region> regions = region_repository.findAll();
		for(int i =0 ; i<regions.size();i++) {
       	 availability_zone_repository.save(new Availability_zone(regions.get(i),"a"));
       	 availability_zone_repository.save(new Availability_zone(regions.get(i),"b"));
       	 availability_zone_repository.save(new Availability_zone(regions.get(i),"c"));
       	 availability_zone_repository.save(new Availability_zone(regions.get(i),"d"));
       }
	}
	private  void type_init() {
		List<String> names= Arrays.asList("a1","T3","T3a","T2","M5","M5a");
		for(int i =0 ; i<names.size();i++) {
	        type_repository.save(new Type(names.get(i),"medium"));
	        type_repository.save(new Type(names.get(i),"large"));
	        type_repository.save(new Type(names.get(i),"xlarge"));
	        type_repository.save(new Type(names.get(i),"2xlarge"));
	        type_repository.save(new Type(names.get(i),"4xlarge"));
	        type_repository.save(new Type(names.get(i),"metal"));
	       }
	}
	private  void state_init() {
		 	state_repository.save(new State("pending"));
	        state_repository.save(new State("running"));
	        state_repository.save(new State("stopping"));
	        state_repository.save(new State("stopped"));
	        state_repository.save(new State("shutting-down"));
	        state_repository.save(new State("terminated"));
	}
	private void ec2_instance_init() throws UnknownHostException {
		// TODO Auto-generated method stub
			ec2_repository.save(new EC2_instance("test-1",
												state_repository.findAll().get(0),
												type_repository.findAll().get(0),
				 							   	availability_zone_repository.findAll().get(0),
				 							   	"127.0.0.1",
				 							   	"127.0.0.1",
				 							   	0,
				 							   	user_repository.findAll().get(1)));
			
			ec2_repository.save(new EC2_instance("test-2",
												state_repository.findAll().get(1),
												type_repository.findAll().get(2),
												availability_zone_repository.findAll().get(1),
												"10.20.30.40",
												"54.210.167.206",
												 0,
												 user_repository.findAll().get(1)));
			
			ec2_repository.save(new EC2_instance("test-3",
												state_repository.findAll().get(2),
												type_repository.findAll().get(0),
											   	availability_zone_repository.findAll().get(2),
											   	"10.20.30.40",
												"54.210.167.206",
											   	0,
											   	user_repository.findAll().get(1)));
			
			ec2_repository.save(new EC2_instance("test-4",
												state_repository.findAll().get(3),
												type_repository.findAll().get(4),
												availability_zone_repository.findAll().get(3),
												"10.20.30.42",
												"54.210.167.206",
												 0,
												 user_repository.findAll().get(1)));
			
			ec2_repository.save(new EC2_instance("test-5",
												state_repository.findAll().get(5),
												type_repository.findAll().get(1),
												availability_zone_repository.findAll().get(3),
												"10.20.30.41",
												"54.210.167.207",
												 0,
												 user_repository.findAll().get(1)));
			
					
		    ec2_repository.save(new EC2_instance("test-6",
								state_repository.findAll().get(1),
								type_repository.findAll().get(1),
								   	availability_zone_repository.findAll().get(4),
								   	"127.0.0.1",
								   	"127.0.0.1",
								   	1,
								   	user_repository.findAll().get(1)));
		    
			ec2_repository.save(new EC2_instance("test-7",
								state_repository.findAll().get(2),
								type_repository.findAll().get(1),
								availability_zone_repository.findAll().get(5),
								"10.20.30.40",
								"54.210.167.204",
								 1,
								 user_repository.findAll().get(1)));
			ec2_repository.save(new EC2_instance("test-8",
								state_repository.findAll().get(3),
								type_repository.findAll().get(1),
							   	availability_zone_repository.findAll().get(6),
							   	"10.20.30.49",
								"54.210.167.208",
							   	1,
							   	user_repository.findAll().get(1)));
			ec2_repository.save(new EC2_instance("test-9",
								state_repository.findAll().get(4),
								type_repository.findAll().get(1),
								availability_zone_repository.findAll().get(7),
								"10.20.30.40",
								"54.210.167.204",
								 1,
								 user_repository.findAll().get(1)));
			ec2_repository.save(new EC2_instance("test-10",
								state_repository.findAll().get(0),
								type_repository.findAll().get(1),
								availability_zone_repository.findAll().get(7),
								"10.20.30.40",
								"54.210.167.203",
								 1,
								 user_repository.findAll().get(1)));
			
			
			
			ec2_repository.save(new EC2_instance("test-1",
					state_repository.findAll().get(0),
					type_repository.findAll().get(0),
					   	availability_zone_repository.findAll().get(0),
					   	"10.20.30.40",
						"54.210.167.206",
					   	0,
								   	user_repository.findAll().get(2)));
			
			ec2_repository.save(new EC2_instance("test-2",
								state_repository.findAll().get(1),
								type_repository.findAll().get(2),
								availability_zone_repository.findAll().get(1),
								"10.20.30.41",
								"54.210.167.216",
								 0,
								 user_repository.findAll().get(2)));
			
			ec2_repository.save(new EC2_instance("test-3",
								state_repository.findAll().get(2),
								type_repository.findAll().get(0),
							   	availability_zone_repository.findAll().get(2),
							   	"10.20.30.43",
								"54.210.167.216",
							   	0,
							   	user_repository.findAll().get(2)));
			
			ec2_repository.save(new EC2_instance("test-4",
								state_repository.findAll().get(3),
								type_repository.findAll().get(4),
								availability_zone_repository.findAll().get(3),
								"127.0.0.1",
								"127.0.0.1",
								 0,
								 user_repository.findAll().get(2)));
			
			ec2_repository.save(new EC2_instance("test-5",
								state_repository.findAll().get(5),
								type_repository.findAll().get(1),
								availability_zone_repository.findAll().get(3),
								"10.20.30.40",
								"54.210.167.206",
								 0,
								 user_repository.findAll().get(2)));
			
			
			ec2_repository.save(new EC2_instance("test-6",
				state_repository.findAll().get(1),
				type_repository.findAll().get(1),
				   	availability_zone_repository.findAll().get(4),
				   	"10.20.30.40",
					"54.210.167.206",
				   	1,
				   	user_repository.findAll().get(2)));
			
			ec2_repository.save(new EC2_instance("test-7",
				state_repository.findAll().get(2),
				type_repository.findAll().get(1),
				availability_zone_repository.findAll().get(5),
				"10.20.30.40",
				"54.210.167.206",
				 1,
				 user_repository.findAll().get(2)));
			ec2_repository.save(new EC2_instance("test-8",
				state_repository.findAll().get(3),
				type_repository.findAll().get(1),
			   	availability_zone_repository.findAll().get(6),
			   	"10.20.30.40",
				"54.210.167.206",
			   	1,
			   	user_repository.findAll().get(2)));
			ec2_repository.save(new EC2_instance("test-9",
				state_repository.findAll().get(4),
				type_repository.findAll().get(1),
				availability_zone_repository.findAll().get(7),
				"10.20.30.40",
				"54.210.167.206",
				 1,
				 user_repository.findAll().get(2)));
			ec2_repository.save(new EC2_instance("test-10",
				state_repository.findAll().get(0),
				type_repository.findAll().get(1),
				availability_zone_repository.findAll().get(7),
				"10.20.30.40",
				"54.210.167.206",
				 1,
				 user_repository.findAll().get(2)));
	       
		
	}
	
	

}
