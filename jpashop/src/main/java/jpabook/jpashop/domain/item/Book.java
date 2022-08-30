package jpabook.jpashop.domain.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("B") // DB에서 구별될 수 있도록 
@Getter @Setter
public class Book extends Item {
	
	private String author;
	private String isbn;

}
