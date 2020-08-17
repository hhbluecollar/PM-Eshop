package edu.miu.eshop.bankapi.domain;


import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@ToString
public class PaymentMethod {

	@Id
	private String id;
	private String url;
	private String name;
	private String description;
	private int rangeFrom;
	private int rangeTo;

	public PaymentMethod( String name, String description, String url, int rangefrom, int rangeto) {
		this.url = url;
		this.name = name;
		this.description = description;
		this.rangeFrom = rangefrom;
		this.rangeTo = rangeto;

	}
	
	public String getId() {
		return id;
	}




	public void setId(String id) {
		this.id = id;
	}




	public PaymentMethod() {
		// TODO Auto-generated constructor stub
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getRangeFrom() {
		return rangeFrom;
	}

	public void setRangeFrom(int rangeFrom) {
		this.rangeFrom = rangeFrom;
	}

	public int getRangeTo() {
		return rangeTo;
	}

	public void setRangeTo(int rangeTo) {
		this.rangeTo = rangeTo;
	}

}
