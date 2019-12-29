package compute_dashboard.repository;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import compute_dashboard.model.Availability_zone;

public interface Availability_zone_repository extends  JpaRepository<Availability_zone,Integer>{

	List<Availability_zone> findByregionId(Integer id);

	Availability_zone findByAzId(@NotNull Integer azId);
}

