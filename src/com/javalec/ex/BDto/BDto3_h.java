package com.javalec.ex.BDto;

public class BDto3_h {

	// mvc_mem (회원 db)

	String hobby;
	String id;
	

	public BDto3_h() {

	}

	public BDto3_h(String id, String hobby) {

		this.hobby = hobby;
		this.id = id;
		
	}
	
	

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	

}
