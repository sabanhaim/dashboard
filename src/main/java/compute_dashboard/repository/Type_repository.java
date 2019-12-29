package compute_dashboard.repository;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;

import compute_dashboard.model.Type;

public interface Type_repository extends  JpaRepository<Type,Integer>{

	Type findByTypeId(@NotNull Integer typeId);
	
	
}