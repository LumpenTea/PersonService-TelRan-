package telran.java47.person.model;

import java.time.LocalDate;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import telran.java47.person.dto.AddressDto;
import telran.java47.person.dto.ChildDto;
import telran.java47.person.dto.EmployeeDto;
import telran.java47.person.dto.PersonDto;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Child extends Person {

	private static final long serialVersionUID = 1361306948330547404L;
	
	String kindergarten;

	public Child(Integer id, String name, LocalDate birthDate, Adress address, String kindergarten) {
		super(id, name, birthDate, address);
		this.kindergarten = kindergarten;
	}
	
	@Override
	public PersonDto convertToDto() {
		AddressDto addressDto = new AddressDto(adress.getCity(), adress.getStreet(), adress.getBuilding());
		return new ChildDto(id, name, birthDate, addressDto, kindergarten);
	}
}
