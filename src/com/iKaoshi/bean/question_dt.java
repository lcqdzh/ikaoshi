package com.iKaoshi.bean;

public class question_dt {
	int question_Id;
	int tiku_Id;
	String question_content;
	int question_type;//1单选，2判断，3大题
	int question_score;
	String choice_A;
	String choice_B;
	String choice_C;
	String choice_D;
	int answer;
	int tiku_id;
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	String stu_answer;
	int score;
	public String getStu_answer() {
		return stu_answer;
	}
	public void setStu_answer(String stu_answer) {
		this.stu_answer = stu_answer;
	}
	public int getTiku_Id() {
		return tiku_Id;
	}
	public void setTiku_Id(int tiku_Id) {
		this.tiku_Id = tiku_Id;
	}
	public int getQuestion_score() {
		return question_score;
	}
	public void setQuestion_score(int question_score) {
		this.question_score = question_score;
	}
	public int getQuestion_Id() {
		return question_Id;
	}
	public void setQuestion_Id(int question_Id) {
		this.question_Id = question_Id;
	}
	public String getQuestion_content() {
		return question_content;
	}
	public void setQuestion_content(String question_content) {
		this.question_content = question_content;
	}
	public int getQuestion_type() {
		return question_type;
	}
	public void setQuestion_type(int question_type) {
		this.question_type = question_type;
	}
	public String getChoice_A() {
		return choice_A;
	}
	public void setChoice_A(String choice_A) {
		this.choice_A = choice_A;
	}
	public String getChoice_B() {
		return choice_B;
	}
	public void setChoice_B(String choice_B) {
		this.choice_B = choice_B;
	}
	public String getChoice_C() {
		return choice_C;
	}
	public void setChoice_C(String choice_C) {
		this.choice_C = choice_C;
	}
	public String getChoice_D() {
		return choice_D;
	}
	public void setChoice_D(String choice_D) {
		this.choice_D = choice_D;
	}
	public int getAnswer() {
		return answer;
	}
	public void setAnswer(int answer) {
		this.answer = answer;
	}
	public int getTiku_id() {
		return tiku_id;
	}
	public void setTiku_id(int tiku_id) {
		this.tiku_id = tiku_id;
	}
	
	
}
