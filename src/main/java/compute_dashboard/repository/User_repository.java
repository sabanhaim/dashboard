package compute_dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import compute_dashboard.model.User;

public interface User_repository extends  JpaRepository<User,Integer>{

	User findByawskey(String awskey);

}
