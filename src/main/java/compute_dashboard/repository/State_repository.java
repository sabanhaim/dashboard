package compute_dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import compute_dashboard.model.State;


public interface State_repository extends  JpaRepository<State,Integer>{
	
	State findByid(Integer state_id);
		
}

