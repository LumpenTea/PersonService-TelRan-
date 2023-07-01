package telran.java47.person.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.java47.person.dto.AddressDto;
import telran.java47.person.dto.CityPopulationDto;
import telran.java47.person.dto.PersonDto;
import telran.java47.person.service.PersonService;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {
	final PersonService personService;

	@PostMapping
	public Boolean addPerson(@RequestBody PersonDto personDto) {
		return personService.addPerson(personDto);
	}

	@GetMapping("/{id}")
	public PersonDto findPerson(@PathVariable Integer id) {
		return personService.findPerson(id);
	}
	
	@GetMapping("/city/{city}")
	public List<PersonDto> findByCity(@PathVariable String city) {
		return personService.findByCity(city);
	}

	@GetMapping("/ages/{from}/{to}")
	public List<PersonDto> finByAges(@PathVariable Integer from, @PathVariable Integer to) {
		return personService.finByAges(from, to);
	}

	@PutMapping("/{id}/name/{newName}")
	public PersonDto updateName(@PathVariable Integer id, @PathVariable String newName) {
		return personService.updateName(id, newName);
	}

	@GetMapping("/name/{name}")
	public List<PersonDto> findByName(@PathVariable String name) {
		return personService.findByName(name);
	}

	@GetMapping("/population/city")
	public List<CityPopulationDto> getCitiesPopulation() {
		return personService.getCitiesPopulation();
	}

	@PutMapping("/{id}/address")
	public PersonDto updateAdress(@PathVariable Integer id, @RequestBody AddressDto addressDto) {
		return personService.updateAdress(id, addressDto);
	}

	@DeleteMapping("/{id}")
	public PersonDto deletePerson(@PathVariable Integer id) {
		return personService.deletePerson(id);
	}
}
