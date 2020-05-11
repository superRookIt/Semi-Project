package com.javalec.ex.BDto;

import java.sql.Array;
import java.sql.Date;
import java.sql.Timestamp;

public class BDto3 {

	// mvc_mem (회원 db)

	public BDto3() {

	}

	public BDto3(String name, String id, String pw, String email, String address, String phone, String gender,
			String news, String sms, String day) {

		this.name = name;
		this.id = id;
		this.pw = pw;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.gender = gender;
		this.news = news;
		this.sms = sms;
		this.day = day;

	}

	String name;
	String id;
	String pw;
	String email;
	String address;
	String phone;
	String gender;
	String news;
	String sms;
	String day;

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNews() {
		return news;
	}

	public void setNews(String news) {
		this.news = news;
	}

	public String getSms() {
		return sms;
	}

	public void setSms(String sms) {
		this.sms = sms;
	}

}
