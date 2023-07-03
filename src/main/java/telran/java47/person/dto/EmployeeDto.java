package telran.java47.person.dto;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public class EmployeeDto extends PersonDto{
	String company;
	int salary;
	
	public EmployeeDto(Integer id, String name, LocalDate birthDate, AddressDto adress, String company, int salary) {
		super(id, name, birthDate, adress);
		this.company = company;
		this.salary = salary;
	}
}
