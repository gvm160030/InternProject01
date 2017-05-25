package com.solr.project.Repo;

import java.util.List;

import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import com.solr.project.document.Employee;

public interface EmployeeRepository extends CustomSolrRepository, SolrCrudRepository<Employee, String>{
	
	@Query("lastName:?0")
    List<Employee> findByLastName(String lastName);
    
    @Query("department:?0")
    List<Employee> findByDepartment(String department);


}
