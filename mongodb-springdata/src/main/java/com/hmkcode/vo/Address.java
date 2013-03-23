package com.hmkcode.vo;

public class Address {

	private String country;
	private String state;
	private String city;
	
	public Address(String country,String state,String city){
		this.country = country;
		this.state = state;
		this.city = city;
	}
	
	public Address(){}
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public String toString(){
		return "{country: "+this.country+" , state: "+this.state+" , city: "+this.city+"}";
	}
}
