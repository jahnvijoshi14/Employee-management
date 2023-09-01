package com.dto;

public enum Status {
	
	 ACTIVE('A'),
	 INACTIVE('I');
    private char status;
	Status(char status) {
		// TODO Auto-generated constructor stub
		this.status=status;
	}
	
	public char getStatus()
	{
		return this.status;
	}
	

}
