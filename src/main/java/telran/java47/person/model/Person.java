package telran.java47.person.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import telran.java47.person.dto.AddressDto;
import telran.java47.person.dto.PersonDto;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Person implements Serializable{
	
	private static final long serialVersionUID = -1250554987023825175L;
	
	@Id
	Integer id;
	@Setter
    String name;
    LocalDate birthDate;
    @Setter
    @Embedded
    Adress adress;
    
    public PersonDto convertToDto() {
    	AddressDto addressDto = new AddressDto(adress.getCity(), adress.getStreet(), adress.getBuilding());
    	return new PersonDto(id, name, birthDate, addressDto);
    }
}
