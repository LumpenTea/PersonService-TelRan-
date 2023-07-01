package telran.java47.person.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java47.person.dao.PersonRepository;
import telran.java47.person.dto.AddressDto;
import telran.java47.person.dto.CityPopulationDto;
import telran.java47.person.dto.PersonDto;
import telran.java47.person.exceptions.PersonNotFoundException;
import telran.java47.person.model.Adress;
import telran.java47.person.model.Person;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

	final PersonRepository personRepository;
	final ModelMapper modelMapper;
	
	@Override
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
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public List<PersonDto> findByCity(String city) {
		List<Person> persons = personRepository.findAll();
		return persons.stream()
				.filter(p -> p.getAdress().getCity().equals(city))
				.map(p -> modelMapper.map(p, PersonDto.class))
				.toList();
	}

	@Override
	public List<PersonDto> finByAges(Integer from, Integer to) {
		List<Person> persons = personRepository.findAll();
		LocalDate today = LocalDate.now();
		return persons.stream()
				.filter(p ->  {
					Long longAge = ChronoUnit.YEARS.between(p.getBirthDate(), today);
					Integer age = longAge.intValue();
					return age >= from && age <= to;
				})
				.map(p -> modelMapper.map(p, PersonDto.class))
				.toList();
	}

	@Override
	public PersonDto updateName(Integer id, String newName) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		person.setName(newName);
		personRepository.save(person);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public List<PersonDto> findByName(String name) {
		List<Person> persons = personRepository.findAll();
		return persons.stream()
			.filter(p -> p.getName().equals(name))
			.map(p -> modelMapper.map(p, PersonDto.class))
			.toList();
	}

	@Override
	public List<CityPopulationDto> getCitiesPopulation() {
		List<Person> persons = personRepository.findAll();
		Map<String, Integer> cities = new ConcurrentHashMap<>();
		persons.stream()
			.forEach(p -> {
				String city = p.getAdress().getCity();
				if(cities.get(city) == null) {
					cities.put(city, 1);
				} else {
					Integer prevValue = cities.get(city);
					cities.put(city, ++prevValue);
				}
			});
		return cities.entrySet().stream()
				.map(entry -> new CityPopulationDto(entry.getKey(), entry.getValue()))
				.toList();
	}

	@Override
	public PersonDto updateAdress(Integer id, AddressDto addressDto) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		person.setAdress(modelMapper.map(addressDto, Adress.class));
		personRepository.save(person);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public PersonDto deletePerson(Integer id) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		personRepository.delete(person);
		return modelMapper.map(person, PersonDto.class);
	}

}
