package org.arpit.java2blog.main;

import java.util.List;

import javax.sql.DataSource;

import org.arpit.java2blog.jpa.CountryRepository;
import org.arpit.java2blog.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class SpringApplicationMainBean
{
@Autowired
private CountryRepository repository;

 public static void main(String[] args) {

  ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
  SpringApplicationMainBean samb= (SpringApplicationMainBean) context.getBean("mainClass");  
  samb.addCountries();
  
  System.out.println("=====================================");
  // Read
  Country countryRead = samb.getCountry(3);
  System.out.println("Getting country with ID 3::" + countryRead.getCountryName());

  System.out.println("=====================================");
  System.out.println("Getting all Countries");
  // Get All
  Iterable<Country> countryList = samb.getAllCountries();
  countryList.forEach(c -> System.out.println(c));

  System.out.println("=====================================");
  System.out.println("Finding countryName Bhutan");
  // find country by name
  Country country =samb.findCountryByName("Bhutan");
  System.out.println(country);
  
  System.out.println("=====================================");
  System.out.println("Finding All Countries which have population from 2000 to 20000");
  // find countries by population ranges
  List<Country> countries=samb.findCountryByPopulationRange(2000,20000);
  System.out.println(countries);

  System.out.println("=====================================");
  System.out.println("We are done with all operations");
 }
 
 public void addCountries()
 {
	  // Add Country object to database
	  Country countryUSA = new Country();
	  countryUSA.setCountryName("USA");
	  countryUSA.setPopulation(10000);
	  
	  Country countryIndia = new Country();
	  countryIndia.setCountryName("India");
	  countryIndia.setPopulation(20000);
	  
	  Country countryChina = new Country();
	  countryChina.setCountryName("China");
	  countryChina.setPopulation(30000);
	  
	  Country countryBhutan = new Country();
	  countryBhutan.setCountryName("Bhutan");
	  countryBhutan.setPopulation(5000);
	  

	  // Add Country
	  repository.save(countryUSA);
	  repository.save(countryIndia);
	  repository.save(countryChina);
	  repository.save(countryBhutan);
 }
 public Country getCountry(int id)
 {
	 return repository.findOne(id);
 }
 public Iterable<Country> getAllCountries()
 {
	 return repository.findAll();
 }
 
 public Country findCountryByName(String countryName)
 {
	 return repository.findCountryByCountryName(countryName);
 }
 
 public List<Country> findCountryByPopulationRange(long populationTo,long populationFrom)
 {
	 return repository.findCountriesPopulationRange(populationTo,populationFrom);
 }
 
 
}
