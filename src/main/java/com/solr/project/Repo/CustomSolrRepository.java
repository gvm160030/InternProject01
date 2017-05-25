/**
 * 
 */
package com.solr.project.Repo;
import java.util.List;

import com.solr.project.document.Employee; 

/**
 * @author gamutha
 *
 */



public interface CustomSolrRepository {

    List<String> getAllLastNames();

	List<String> getNamesForDepartment(String department);
	
	List<Employee> useFilterQuery(String department, String wildcard,String sortfield );
	
	List<Employee> groupQuery(String groupBy);
    
}
