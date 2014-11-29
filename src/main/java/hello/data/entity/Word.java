package hello.data.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Word {

	@Id
	private Long	id;
	private String	content;
	private Integer	type;

}