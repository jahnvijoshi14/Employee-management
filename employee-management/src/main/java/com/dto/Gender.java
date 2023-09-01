package com.dto;

public enum Gender {
	 MALE("Male"),
	 FEMALE("Female"),
	 OTHERS("Others");
	 
    private String gender;
	Gender(String gender) {
		// TODO Auto-generated constructor stub
		this.gender=gender;
	}
	
	public String getGender()
	{
		return this.gender;
	}

}
