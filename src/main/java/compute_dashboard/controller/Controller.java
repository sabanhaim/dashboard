package compute_dashboard.controller;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import compute_dashboard.dto.EC2_dto;
import compute_dashboard.dto.Sorts;
import compute_dashboard.model.Availability_zone;
import compute_dashboard.model.EC2_instance;
import compute_dashboard.model.Region;
import compute_dashboard.model.State;
import compute_dashboard.model.Type;
import compute_dashboard.model.User;
import compute_dashboard.service.service;


@RestController
public class Controller {
	
	@Autowired
	private service service;
	/*users function*/
	
	@PostMapping("/new_user")
	public  HashMap<String, String>  new_user(String name) throws Exception
	{
	   return service.create_user(name);
	}
	@GetMapping("/region/{region_id}")
	public List<EC2_instance> get_by_region(@RequestHeader("aws-key") String key,@PathVariable Integer region_id
			,Sorts sort) throws Exception
	{
	   return service.get_by_region(key,region_id,0,sort);
	}	
	@GetMapping("/region/{region_id}/page/{page_id}")
	public List<EC2_instance> get_by_region_with_page(@RequestHeader("aws-key") String key,@PathVariable Integer region_id,
			@PathVariable Integer page_id,Sorts sort) throws Exception
	{
	   return service.get_by_region(key,region_id,page_id,sort);
	}
	@GetMapping("/active_instance")
	public List<EC2_instance> active_instance(@RequestHeader("aws-key") String key,Sorts sort) throws Exception
	{
	   return service.active_instance(key,0,sort);
	}
	@GetMapping("/active_instance/page/{page_id}")
	public List<EC2_instance> active_instance_with_page(@RequestHeader("aws-key") String key,
			@PathVariable Integer page_id,Sorts sort) throws Exception
	{
	   return service.active_instance(key,page_id,sort);
	}
	
	@PostMapping("/new_instance")
	public EC2_instance new_instance(@RequestHeader("aws-key") String key,EC2_dto ec2_dto) throws Exception
	{
	   return service.new_instance(key,ec2_dto);
	}
	
	@PostMapping("/instance/{name}")
	public EC2_instance update_instance(@RequestHeader("aws-key") String key,@PathVariable String name,EC2_dto ec2_dto) throws Exception
	{
	   return service.update_instance(key,name,ec2_dto);
	}

	
	/*info function*/
	@GetMapping("/region")
	public List<Region> region() throws Exception
	{
	   return service.region();
	}
	@GetMapping("/az")
	public List<Availability_zone> availability_zone() throws Exception
	{
	   return service.availability_zone();
	}
	@GetMapping("/type")
	public List<Type> type() throws Exception
	{
	   return service.type();
	}
	@GetMapping("/state")
	public List<State> state() throws Exception
	{
	   return service.state();
	}
	
	/*admin function*/	
	@GetMapping("/users")
	public List<User> get_all_users(@RequestHeader("aws-key") String key) throws Exception
	{
	   return service.get_all_users(key);
	}
	@GetMapping("/instances")
	public List<EC2_instance> get_all_ec2_instance(@RequestHeader("aws-key") String key) throws Exception
	{
	   return service.get_all_ec2_instance(key);
	}
	
	
}
