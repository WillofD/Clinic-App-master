package com.example.lucy.clincapp.Test2;


public class QuestionTest2 {
	private int ID;
	private String QUESTION;
	private String OPYES;
	private String OPNO;
	private String ANSWER;


	public QuestionTest2() {
		ID = 0;
		QUESTION = "";
		OPYES = "";
		OPNO = "";
		ANSWER = "";
	}

	public QuestionTest2(String qUESTION, String oPYES, String oPNO,
						 String aNSWER) {

		QUESTION = qUESTION;
		OPYES = oPYES;
		OPNO = oPNO;
		ANSWER = aNSWER;
	}

	public int getID() {
		return ID;
	}

	public String getQUESTION() {
		return QUESTION;
	}

	public String getANSWER() {
		return ANSWER;
	}

	public void setID(int id) {
		ID = id;
	}

	public void setQUESTION(String qUESTION) {
		QUESTION = qUESTION;
	}

	public void setANSWER(String aNSWER) {
		ANSWER = aNSWER;
	}

	public String getOPNO() {
		return OPNO;
	}

	public void setOPNO(String OPNO) {
		this.OPNO = OPNO;
	}

	public String getOPYES() {
		return OPYES;
	}

	public void setOPYES(String OPYES) {
		this.OPYES = OPYES;
	}
}
