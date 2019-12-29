package compute_dashboard.dto;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public class Sorts {
	
	private String sort_by;
	
	private String sort_order;

	public String getSort_by() {
		if(sort_by == null || sort_by.isEmpty()){
			return "id";
		}
		
		return sort_by;
	}

	public void setSort_by(String sort_by) {
		this.sort_by = sort_by;
	}

	public String getSort_order() {
		return sort_order;
	}

	public void setSort_order(String sort_order) {
		this.sort_order = sort_order;
	}
	
	public Direction get_direction() {
		if(this.sort_order !=null && this.sort_order.equals("desc")) {
			return Sort.Direction.DESC;
		}
		return Sort.Direction.ASC;
	}
	

}
