package compute_dashboard.service;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import compute_dashboard.dto.EC2_dto;
import compute_dashboard.dto.Sorts;
import compute_dashboard.model.Availability_zone;
import compute_dashboard.model.EC2_instance;
import compute_dashboard.model.Region;
import compute_dashboard.model.State;
import compute_dashboard.model.Type;
import compute_dashboard.model.User;
import compute_dashboard.repository.User_repository;
import compute_dashboard.utiles.first_init;
import javassist.NotFoundException;
import compute_dashboard.repository.EC2_repository;
import compute_dashboard.repository.Availability_zone_repository;
import compute_dashboard.repository.Region_repository;
import compute_dashboard.repository.State_repository;
import compute_dashboard.repository.Type_repository;

@Service
public class service {
	@Autowired
	private User_repository user_repository;
	@Autowired
	private EC2_repository ec2_repository;
	@Autowired
	private Region_repository region_repository;
	@Autowired
	private Availability_zone_repository availability_zone_repository;
	@Autowired
	private Type_repository type_repository;
	@Autowired
	private State_repository state_repository;
	
	
	@PostConstruct
    private void postConstruct() throws UnknownHostException {
		new first_init(user_repository, ec2_repository, region_repository, availability_zone_repository, type_repository, state_repository); 
  
    }
	
	
	/*users function*/
	public HashMap<String, String> create_user(String name) throws Exception{
		if(name==null || name.isEmpty()) {
			throw new NotFoundException("invalid name");
		}
		User user=user_repository.save(new User(name));
		if(user==null) {
			throw new NotFoundException("invalid aws-key");
		}
		 HashMap<String,String> map = new HashMap<>(); 
		 map.put("aws-key",user.getAws_key());
		 return map;
    }
	
	public List<EC2_instance> get_by_region(String key, Integer region_id, Integer page_id,Sorts sort) throws NotFoundException {
		// TODO Auto-generated method stub
		User user=user_repository.findByawskey(key);
		if(user==null) {
			throw new NotFoundException("invalid aws-key");
		}
		Region region = region_repository.findByid(region_id);
		if(region==null) {
			throw new NotFoundException("invalid region");
		}
		List<Availability_zone> az_regions = availability_zone_repository.findByregionId(region.getId());
		return ec2_repository.findByuserAndAzIn(user,
				az_regions,PageRequest.of(page_id,3,Sort.by(sort.get_direction(),sort.getSort_by())));
	}

	public List<EC2_instance> active_instance(String key, int page_id, Sorts sort) throws NotFoundException {
		// TODO Auto-generated method stub
		User user=user_repository.findByawskey(key);
		if(user==null) {
			throw new NotFoundException("invalid aws-key");
		}
		return ec2_repository.findByuserAndActive(user,1,
				PageRequest.of(page_id,3,Sort.by(sort.get_direction(),sort.getSort_by())));
	}
	
	public EC2_instance new_instance(String key, EC2_dto ec2_dto) throws NotFoundException, UnknownHostException {
		// TODO Auto-generated method stub
		User user=user_repository.findByawskey(key);
		if(user==null) {
			throw new NotFoundException("invalid aws-key");
		}
		valid_ec2_dto(ec2_dto);
		EC2_instance ec2_instance=ec2_repository.findByuserAndName(user, ec2_dto.getName());
		if(ec2_instance!=null) {
			throw new NotFoundException(ec2_instance.getName()+" is exist");
		}
		return ec2_repository.save(new EC2_instance(ec2_dto,user));
	}
	
	public EC2_instance update_instance(String key, String name, EC2_dto ec2_dto) throws NotFoundException, UnknownHostException {
		// TODO Auto-generated method stub
		User user=user_repository.findByawskey(key);
		if(user==null) {
			throw new NotFoundException("invalid aws-key");
		}
		valid_ec2_dto(ec2_dto);
		EC2_instance ec2_instance=ec2_repository.findByuserAndName(user, ec2_dto.getName());
		
		if(ec2_instance!=null && name.equals(ec2_dto.getName())==false) {
			throw new NotFoundException(ec2_instance.getName()+" is exist");
		}
		ec2_instance=ec2_repository.findByuserAndName(user, name);
		if(ec2_instance==null) {
			throw new NotFoundException(name+" not found");
		}
		
		ec2_repository.delete(ec2_instance);
		return ec2_repository.save(new EC2_instance(ec2_dto,user));
	}	

	/*info function*/


	/*info function*/
	public List<Region> region() {
		// TODO Auto-generated method stub
		return region_repository.findAll();
	}
	
	public List<Availability_zone> availability_zone() {
		// TODO Auto-generated method stub
		return availability_zone_repository.findAll();
	}
	public List<Type> type() {
		// TODO Auto-generated method stub
		return type_repository.findAll();
	}
	public List<State> state() {
		return state_repository.findAll();
	}

	/*Admin function*/
	
	/*admin function*/
	public List<User> get_all_users(String key) {
		// TODO Auto-generated method stub
		if(!key.equals("aaaa-bbbb-cccc-dddd")) {
			return null;
		}
		return user_repository.findAll();
	}	
	public List<EC2_instance> get_all_ec2_instance(String key) {
		// TODO Auto-generated method stub
		if(!key.equals("aaaa-bbbb-cccc-dddd")) {
			return null;
		}
		return (List<EC2_instance>) ec2_repository.findAll();
	}


	

	private void valid_ec2_dto(EC2_dto ec2_dto) throws NotFoundException {
		// TODO Auto-generated method stub
		
		if(ec2_dto.getName()==null) {
			throw new NotFoundException("invalid name");
		}
		System.out.print(ec2_dto.getActive());
		if(ec2_dto.getActive()!= 0 && ec2_dto.getActive()!=1) {
			throw new NotFoundException("invalid active - 0 or 1 is valid");
		}
		Type type =type_repository.findByTypeId(ec2_dto.getType());
		if(type==null) {
			throw new NotFoundException("invalid types checks /type");
		}
		ec2_dto.set_new_type(type);
		Availability_zone availability_zone =availability_zone_repository.findByAzId(ec2_dto.getAz());
		if(availability_zone==null) {
			throw new NotFoundException("invalid availability zone checks /az");
		}
		ec2_dto.setNew_az(availability_zone);
		State state = state_repository.findByid(ec2_dto.getState());
		if(state==null) {
			throw new NotFoundException("invalid state checks /state");
		}
		ec2_dto.setNew_state(state);
		if(ec2_dto.getPrivate_ip()==null) {
			throw new NotFoundException("invalid private ip");
		}
		try {
			Inet4Address  private_ip =(Inet4Address) Inet4Address.getByName(ec2_dto.getPrivate_ip());
			if(!private_ip.isSiteLocalAddress()) {
				throw new NotFoundException("invalid private ip");
			}
			ec2_dto.setNew_private_ip(private_ip);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new NotFoundException("invalid private ip");
		}
		if(ec2_dto.getPublic_ip()==null) {
			throw new NotFoundException("invalid public ip");
		}
		try {
			Inet4Address public_ip =(Inet4Address) Inet4Address.getByName(ec2_dto.getPublic_ip());
			if(public_ip.isSiteLocalAddress()) {
				throw new NotFoundException("invalid public ip");
			}
			ec2_dto.setNew_public_ip(public_ip);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new NotFoundException("invalid public ip");
		}
		
	}


	
}
