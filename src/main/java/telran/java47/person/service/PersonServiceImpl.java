package telran.java47.person.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java47.person.dao.PersonRepository;
import telran.java47.person.dto.AddressDto;
import telran.java47.person.dto.CityPopulationDto;
import telran.java47.person.dto.PersonDto;
import telran.java47.person.exceptions.PersonNotFoundException;
import telran.java47.person.model.Adress;
import telran.java47.person.model.Child;
import telran.java47.person.model.Employee;
import telran.java47.person.model.Person;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService, CommandLineRunner {

	final PersonRepository personRepository;
	final ModelMapper modelMapper;
	
	@Override
	@Transactional
	public Boolean addPerson(PersonDto personDto) {
		if(personRepository.existsById(personDto.getId())) {
			return false;
		}
		personRepository.save(modelMapper.map(personDto, Person.class));
		return true;
	}

	@Override
	public PersonDto findPerson(Integer id) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		return person.convertToDto();
	}

	@Override
	public List<PersonDto> findByCity(String city) {
		List<Person> persons = personRepository.findByAdressCityIgnoreCase(city);
		return persons.stream()
				.map(p -> p.convertToDto())
				.toList();
	}

	@Override
	public List<PersonDto> finByAges(Integer minAge, Integer maxAge) {
		LocalDate from = LocalDate.now().minusYears(maxAge);
		LocalDate to = LocalDate.now().minusYears(minAge);
		List<Person> persons = personRepository.findByBirthDateBetween(from, to);
		return persons.stream()
				.map(p -> p.convertToDto())
				.toList();
	}

	@Override
	@Transactional
	public PersonDto updateName(Integer id, String newName) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		person.setName(newName);
		return person.convertToDto();
	}

	@Override
	public List<PersonDto> findByName(String name) {
		List<Person> persons = personRepository.findByNameIgnoreCase(name);
		return persons.stream()
			.map(p -> p.convertToDto())
			.toList();
	}

	@Override
	public List<CityPopulationDto> getCitiesPopulation() {
		return personRepository.getCitiesPopulation();
	}

	@Override
	public PersonDto updateAdress(Integer id, AddressDto addressDto) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		person.setAdress(modelMapper.map(addressDto, Adress.class));
		personRepository.save(person);
		return person.convertToDto();
	}

	@Override
	@Transactional
	public PersonDto deletePerson(Integer id) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		personRepository.delete(person);
		return person.convertToDto();
	}
	
	@Override
	public List<PersonDto> getAllChildren() {
		return personRepository.findAllChildren()
				.stream()
				.map(p -> p.convertToDto())
				.toList();
	}
	
	@Override
	public List<PersonDto> getEmployeesBySalary(Integer from, Integer to) {
		return personRepository.findEmployeeBySalary(from, to)
				.stream()
				.map(p -> p.convertToDto())
				.toList();
	}

	@Override
	public void run(String... args) throws Exception {
		if(personRepository.count() == 0) {
			Person person = new Person(1000, "John", LocalDate.of(1985,  4, 11), new Adress("Tel Aviv", "Ben Gvirol", "87"));
			Child child = new Child(2000, "Mosche", LocalDate.of(2018, 7, 5), new Adress("Ashkelon", "Bar Kohva", "21"), "Shalom");
			Employee employee = new Employee(3000, "Sarah", LocalDate.of(1995, 11, 23), new Adress("Rehovot", "Herzl", "7"), "Motorola", 20_000);
			personRepository.save(person);
			personRepository.save(child);
			personRepository.save(employee);
		}
		
	}
}
