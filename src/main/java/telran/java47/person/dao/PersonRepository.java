package telran.java47.person.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import telran.java47.person.dto.CityPopulationDto;
import telran.java47.person.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
	List<Person> findByNameIgnoreCase(String name);
	List<Person> findByAdressCityIgnoreCase(String city);
	List<Person> findByBirthDateBetween(LocalDate from, LocalDate to);
	
	@Query("select new telran.java47.person.dto.CityPopulationDto(p.adress.city, count(p)) from Person p group by p.adress.city order by count(p) desc")
	List<CityPopulationDto> getCitiesPopulation();
	
	@Query("select c from Child c")
	List<Person> findAllChildren();
	
	@Query("select e from Employee e where e.salary >= :from and e.salary <= :to")
	List<Person> findEmployeeBySalary(@Param("from") Integer from, @Param("to") Integer to);
}
