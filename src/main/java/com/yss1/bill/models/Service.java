package com.yss1.bill.models;

public class Service{
	private int id;
	private String name;
	private short constant;
	
	public short getConstant() {
		return constant;
	}
	public void setConstant(short cons) {
		this.constant = cons;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}