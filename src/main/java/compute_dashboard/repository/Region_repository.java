package compute_dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import compute_dashboard.model.Region;

public interface Region_repository extends  JpaRepository<Region,Integer>{

	Region findByid(Integer region_id);

	
}
