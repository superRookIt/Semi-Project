package com.javalec.ex.BDto;

import java.sql.Timestamp;

public class BDto2 {

	public BDto2() {

	}

	public BDto2(int bId, String bTitle, String bContent, Timestamp bDate, Timestamp bDate2, String bFile,
			String bFile2) {

		this.bId = bId;
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.bDate = bDate;
		this.bDate2 = bDate;
		this.bFile = bFile;
		this.bFile2 = bFile2;

	}

	int bId;

	String bTitle;
	String bContent;
	String bFile;
	String bFile2;

	Timestamp bDate;
	Timestamp bDate2;

	public int getbId() {
		return bId;
	}

	public void setbId(int bId) {
		this.bId = bId;
	}

	public String getbTitle() {
		return bTitle;
	}

	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}

	public String getbContent() {
		return bContent;
	}

	public void setbContent(String bContent) {
		this.bContent = bContent;
	}

	public String getbFile() {
		return bFile;
	}

	public void setbFile(String bFile) {
		this.bFile = bFile;
	}

	public String getbFile2() {
		return bFile2;
	}

	public void setbFile2(String bFile2) {
		this.bFile2 = bFile2;
	}

	public Timestamp getbDate() {
		return bDate;
	}

	public void setbDate(Timestamp bDate) {
		this.bDate = bDate;
	}

	public Timestamp getbDate2() {
		return bDate2;
	}

	public void setbDate2(Timestamp bDate2) {
		this.bDate2 = bDate2;
	}

}
