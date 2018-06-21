package com.iKaoshi.bean;

import java.sql.Timestamp;;

public class test {
	int test_Id;
	String test_name;
	String tea_name;
	int time_long;//单位为Min
	int test_score;
	Timestamp begin_Time;
	Timestamp end_Time;
	public Timestamp getBegin_Time() {
		return begin_Time;
	}
	public void setBegin_Time(Timestamp timestamp) {
		this.begin_Time = timestamp;
	}
	public Timestamp getEnd_Time() {
		return end_Time;
	}
	public void setEnd_Time(Timestamp end_Time) {
		this.end_Time = end_Time;
	}
	public int getTest_score() {
		return test_score;
	}
	public void setTest_score(int test_score) {
		this.test_score = test_score;
	}
	public int getTime_long() {
		return time_long;
	}
	public void setTime_long(int time_long) {
		this.time_long = time_long;
	}
	public int getTest_Id() {
		return test_Id;
	}
	public void setTest_Id(int test_Id) {
		this.test_Id = test_Id;
	}
	public String getTest_name() {
		return test_name;
	}
	public void setTest_name(String test_name) {
		this.test_name = test_name;
	}
	public String getTea_name() {
		return tea_name;
	}
	public void setTea_name(String tea_name) {
		this.tea_name = tea_name;
	}

}
