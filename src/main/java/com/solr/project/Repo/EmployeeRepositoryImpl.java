package com.solr.project.Repo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Repository;

import com.solr.project.document.Employee;


@Repository
public class EmployeeRepositoryImpl implements CustomSolrRepository{

   
    HttpSolrServer server = new HttpSolrServer("http://localhost:8983/solr/basic_core");
    @Override
    public List<String> getAllLastNames() {
	List<String> lastNameList = new ArrayList<String>();

	SolrQuery query = new SolrQuery("*:*");
	query.setFields("id","firstName","lastName","department");
	System.out.println("The SOLR Query : "+query.toString());

	try {
		QueryResponse response = server.query(query);
		SolrDocumentList res = response.getResults();
		for (SolrDocument  doc : res) {
			Collection<String> fieldNames = doc.getFieldNames();
		    for (String s: fieldNames) {
		        System.out.println(doc.getFieldValue(s).toString());
		    }
		}
	  
	} catch (SolrServerException e) {
	    e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}

	return lastNameList;
    }
    
    @Override
    public List<String> getNamesForDepartment(String department) {
	List<String> list = new ArrayList<String>();

	//SolrQuery query = new SolrQuery("*:*");
	SolrQuery query = new SolrQuery();
	query.setQuery("department:"+department);
	query.setFields("id","firstName","lastName","department");
	System.out.println("The SOLR Query : "+query.toString());

	try {
		QueryResponse response = server.query(query);
		SolrDocumentList res = response.getResults();
		for (SolrDocument  doc : res) {
			System.out.println(doc.getFieldValue("firstName").toString().concat(" "+doc.getFieldValue("lastName")));
			/*Collection<String> fieldNames = doc.getFieldNames();
		    for (String s: fieldNames) {
		        System.out.println(doc.getFieldValue(s).toString());
		    }*/
		}
	  
	} catch (SolrServerException e) {
	    e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	return list;
    }

	@Override
	public List<Employee> useFilterQuery(String department, String wildcard,String sortField) {

		List<Employee> list = new ArrayList<Employee>();
		
		SolrQuery query = new SolrQuery();
		query.setQuery("department:"+department);
		query.setFilterQueries("firstName:"+wildcard+" OR lastName:"+wildcard);
		query.setFields("id","firstName","lastName","department");
		query.addSort(sortField,ORDER.asc);
		System.out.println("The SOLR Query : "+query.toString());

		try {
			QueryResponse response = server.query(query);
			SolrDocumentList res = response.getResults();
			System.out.println(" size ------"+res.size());
			for (SolrDocument  doc : res) {
				Collection<String> fieldNames = doc.getFieldNames();
			    for (String s: fieldNames) {
			        System.out.println(doc.getFieldValue(s).toString());
			    }
			}
		  
		} catch (SolrServerException e) {
		    e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	    
	}

	@Override
	public List<Employee> groupQuery(String groupBy) {
		List<Employee> list = new ArrayList<Employee>();

		SolrQuery query = new SolrQuery();
		query.setQuery(groupBy);
		query.add("facet","true");
		query.add("facet.field",groupBy);
		System.out.println("The SOLR Query : "+query.toString());

		try {
			QueryResponse response = server.query(query);
			List<FacetField> fields = response.getFacetFields();
			for (FacetField  value : fields) {
				List<Count> counts = value.getValues();
			    for(Count c : counts){
			        String facetLabel = c.getName();
			        long facetCount = c.getCount();
			        System.out.println("Name + Count "+facetLabel+" "+facetCount);
			    }
				}
		  
		} catch (SolrServerException e) {
		    e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return list;
	    }

	}
