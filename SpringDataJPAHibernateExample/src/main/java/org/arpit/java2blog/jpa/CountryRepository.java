package org.arpit.java2blog.jpa;

import java.util.List;

import org.arpit.java2blog.model.Country;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country,Integer> { 
    Country findCountryByCountryName(String name);
    @Query("select country from Country country where country.population >= ?1 and country.population  <= ?2")
    List<Country> findCountriesPopulationRange(long from, long to); 
}