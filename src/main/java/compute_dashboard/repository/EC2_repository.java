package compute_dashboard.repository;


import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import compute_dashboard.model.Availability_zone;
import compute_dashboard.model.EC2_instance;
import compute_dashboard.model.User;

public interface EC2_repository extends  CrudRepository<EC2_instance,String>{

	List<EC2_instance> findByuserId(Integer userId, Pageable pageable);
	
	List<EC2_instance> findByuserAndAzIn(User user,List<Availability_zone> az_regions, Pageable pageable);

	List<EC2_instance> findByuserAndActive(User user,Integer active,Pageable pageable);

	EC2_instance findByuserAndName(User user, String name);

}
