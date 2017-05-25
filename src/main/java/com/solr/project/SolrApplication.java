package com.solr.project;

import org.apache.solr.common.SolrDocument;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.solr.project.Repo.EmployeeRepository;
import com.solr.project.document.Employee;


@SpringBootApplication
public class SolrApplication{
	public static void main(String[] args) {
		ApplicationContext context = 
		SpringApplication.run(SolrApplication.class, args);
		EmployeeRepository repository = context.getBean(EmployeeRepository.class);

		createSampleSchema(repository);

		System.out.println("All records in solr");
		repository.findAll().forEach(System.out::println);

		System.out.println("===========================================");
		System.out.println("OUTPUT");
		//repository.getAllLastNames().forEach(System.out::println);
		repository.findByLastName("Mutha").forEach(System.out::println);
		//repository.getNamesForDepartment("Finance").forEach(System.out::println);
		//repository.useFilterQuery("IT","N*","id").forEach(System.out::println);
		//repository.groupQuery("department").forEach(System.out::println);
	}
	
	 private static void createSampleSchema(EmployeeRepository repository) {
		 	
			Employee emp1 = new Employee("1", "Gauri", "Mutha","IT");
			repository.save(emp1);
			Employee emp2 = new Employee("2", "Dinesh", "Mutha","Security");
			repository.save(emp2);
			Employee emp3 = new Employee("3", "Dinesh", "Joshi","Finance");
			repository.save(emp3);
			Employee emp4 = new Employee("4", "Rishi", "Naman","IT");
			repository.save(emp4);
			Employee emp5 = new Employee("5", "Yogesh", "Nath","Finance");
			repository.save(emp5);
			Employee emp6 = new Employee("6", "Sean", "Moore","Security");
			repository.save(emp6);
			Employee emp7 = new Employee("7", "Sean", "Wills","Finance");
			repository.save(emp7);
			Employee emp8 = new Employee("8", "Ishank", "Gupta","IT");
			repository.save(emp8);
			Employee emp9 = new Employee("9", "Noala", "Fransis","IT");
			repository.save(emp9);
		    }	 
		 
	 }
