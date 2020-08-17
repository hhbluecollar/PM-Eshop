package edu.miu.eshop.eshopadmin.domain;

// HH

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@NoArgsConstructor
@ToString
@Setter
@Getter
@EqualsAndHashCode
public class Category {

	/**
	 * We should ask category status ??? ADDED USER ID add
	 */
	@Id
	//@JsonProperty("key")
	private String key; //id
	//@JsonProperty("label")
	private String label; //categoryName
	//@JsonProperty("nodes")
	private List<Category> nodes = new ArrayList<>(); //subCategories

	public Category(String label) {
		this.label = label;
	}

	public Category addCategory(String parentId, String value){
		if(this.key.equals(parentId)) {
			Category c = new Category(value);
			c.setKey(generateCategoryId());
			this.nodes.add(c);
			return c;
		}

		for(Category c : this.nodes){
			Category category = c.addCategory(parentId, value);
			if(category!=null)
				return category;
		}
		return null;
	}

	public boolean deleteCategory(String id){
		ListIterator<Category> iterator = this.nodes.listIterator();

		while(iterator.hasNext()){
			Category c = iterator.next();
			if(c.key.equals(id)) {
				iterator.remove();
				return true;
			}
			else {
				if(c.deleteCategory( id))
					return true;
			}
		}
		return false;
	}

	public boolean editCategory(String id, String value){
		Iterator<Category> iterator = this.nodes.iterator();
		while(iterator.hasNext()){
			Category c = iterator.next();
			if(c.key.equals(id)  ) {
				c.label=value;
				return true;
			}
			else {
				if(c.editCategory( id, value))
					return true;
			}
		}
		return false;
	}


	//generate order number of length 20
	private String generateCategoryId() {

		// chose a Character random from this String
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
				+ "0123456789" ;

		// create StringBuffer size of AlphaNumericString
		StringBuilder sb = new StringBuilder(15);

		for (int i = 0; i < 20; i++) {

			// generate a random number between 0 to AlphaNumericString variable length
			int index = (int)(AlphaNumericString.length() * Math.random());

			// add Character one by one in end of sb
			sb.append(AlphaNumericString.charAt(index));
		}

		return sb.toString();
	}
}