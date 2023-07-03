package telran.java47.person.service;

import java.util.List;

import telran.java47.person.dto.AddressDto;
import telran.java47.person.dto.CityPopulationDto;
import telran.java47.person.dto.PersonDto;

public interface PersonService {
	
	Boolean addPerson(PersonDto personDto);
	PersonDto findPerson(Integer id);
	List<PersonDto> findByCity(String city);
	List<PersonDto> finByAges(Integer from, Integer to);
	PersonDto updateName(Integer id, String newName);
	List<PersonDto> findByName(String name);
	List<CityPopulationDto> getCitiesPopulation();
	PersonDto updateAdress(Integer id, AddressDto addressDto);
	PersonDto deletePerson(Integer id);
	List<PersonDto> getAllChildren();
	List<PersonDto> getEmployeesBySalary(Integer from, Integer to);
}
