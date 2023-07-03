package telran.java47.person.dto;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public class ChildDto extends PersonDto{
	String kindergarten;
	
	public ChildDto(Integer id, String name, LocalDate birthDate, AddressDto adress, String kindergarten) {
		super(id, name, birthDate, adress);
		this.kindergarten = kindergarten;
	}
}
