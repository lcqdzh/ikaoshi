package com.iKaoshi.bean;

import java.sql.Timestamp;

public class TeaTestInfo {
	String test_name;
	int test_id;
	int tiku_id;
	int tea_id;
	public Timestamp getBegin_time() {
		return begin_time;
	}
	public void setBegin_time(Timestamp begin_time) {
		this.begin_time = begin_time;
	}
	public int getDt_easy() {
		return dt_easy;
	}
	public void setDt_easy(int dt_easy) {
		this.dt_easy = dt_easy;
	}
	public int getDt_hard() {
		return dt_hard;
	}
	public void setDt_hard(int dt_hard) {
		this.dt_hard = dt_hard;
	}
	Timestamp begin_time;
	Timestamp end_time;
	int time_long;
	int dx_score;
	int dx_easy;
	int dx_medium;
	public String getTest_name() {
		return test_name;
	}
	public void setTest_name(String test_name) {
		this.test_name = test_name;
	}
	public int getTest_id() {
		return test_id;
	}
	public void setTest_id(int test_id) {
		this.test_id = test_id;
	}
	public int getTiku_id() {
		return tiku_id;
	}
	public void setTiku_id(int tiku_id) {
		this.tiku_id = tiku_id;
	}
	public int getTea_id() {
		return tea_id;
	}
	public void setTea_id(int tea_id) {
		this.tea_id = tea_id;
	}
	public Timestamp getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Timestamp end_time) {
		this.end_time = end_time;
	}
	public int getTime_long() {
		return time_long;
	}
	public void setTime_long(int time_long) {
		this.time_long = time_long;
	}
	public int getDx_score() {
		return dx_score;
	}
	public void setDx_score(int dx_score) {
		this.dx_score = dx_score;
	}
	public int getDx_easy() {
		return dx_easy;
	}
	public void setDx_easy(int dx_easy) {
		this.dx_easy = dx_easy;
	}
	public int getDx_medium() {
		return dx_medium;
	}
	public void setDx_medium(int dx_medium) {
		this.dx_medium = dx_medium;
	}
	public int getDx_hard() {
		return dx_hard;
	}
	public void setDx_hard(int dx_hard) {
		this.dx_hard = dx_hard;
	}
	public int getPd_score() {
		return pd_score;
	}
	public void setPd_score(int pd_score) {
		this.pd_score = pd_score;
	}
	public int getPd_easy() {
		return pd_easy;
	}
	public void setPd_easy(int pd_easy) {
		this.pd_easy = pd_easy;
	}
	public int getPd_medium() {
		return pd_medium;
	}
	public void setPd_medium(int pd_medium) {
		this.pd_medium = pd_medium;
	}
	public int getPd_hard() {
		return pd_hard;
	}
	public void setPd_hard(int pd_hard) {
		this.pd_hard = pd_hard;
	}
	public int getDt_score() {
		return dt_score;
	}
	public void setDt_score(int dt_score) {
		this.dt_score = dt_score;
	}
	public int getDt_medium() {
		return dt_medium;
	}
	public void setDt_medium(int dt_medium) {
		this.dt_medium = dt_medium;
	}
	int dx_hard;
	int pd_score;
	int pd_easy;
	int pd_medium ;
	int pd_hard;
	int dt_score;
	int dt_easy;
	int dt_medium;
	int dt_hard;
	public String toString()
	{
		return "test_name=" + this.test_name+
				" tiku_ID=" + this.tiku_id+
				" begin_time=" + this.begin_time+
				" end_time=" + this.end_time+
				" time_long=" + this.time_long+
				" dx_score=" + this.dx_score+
				" dx_easy=" + this.dx_easy+
				" dx_medium=" + this.dx_medium+
				" dx_hard=" + this.dx_hard+
				" pd_score=" + this.pd_score+
				" pd_easy=" + this.pd_easy+
				" pd_medium=" + this.pd_medium+
				" pd_hard=" + this.pd_hard+
				" dt_score=" + this.dt_score+
				" dt_easy=" + this.dt_easy+
				" dt_medium=" + this.dt_medium+
				" dt_hard="+this.dt_hard;
	}
}
