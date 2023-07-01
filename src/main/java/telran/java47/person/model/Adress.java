package telran.java47.person.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class Adress implements Serializable {
	private static final long serialVersionUID = -4291541190993415397L;
	String city;
	String street;
	String building;
}
