package telran.java47.person.model;

import java.time.LocalDate;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import telran.java47.person.dto.AddressDto;
import telran.java47.person.dto.EmployeeDto;
import telran.java47.person.dto.PersonDto;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Employee extends Person {

	private static final long serialVersionUID = 1046119864761696101L;
	
	String company;
	int salary;
	
	public Employee(Integer id, String name, LocalDate birthDate, Adress address, String company, int salary) {
		super(id, name, birthDate, address);
		this.company = company;
		this.salary = salary;
	}
	
	@Override
	public PersonDto convertToDto() {
		AddressDto addressDto = new AddressDto(adress.getCity(), adress.getStreet(), adress.getBuilding());
		return new EmployeeDto(id, name, birthDate, addressDto, company, salary);
	}
	
	

}
