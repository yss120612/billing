package com.yss1.bill.models;

public class Tel{
	private long number;
	private int territory;
	private String user;
	private short kind;
	
	public long getNumber() {
		return number;
	}
	
	public void setNumber(long n) {
		this.number = n;
	}
	
	public int getTeritory() {
		return territory;
	}
	
	public void setTerritory(int t) {
		this.territory = t;
	}
	
	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public short getKind() {
		return kind;
	}
	
	public void setKind(short k) {
		this.kind = k;
	}
	
	
}