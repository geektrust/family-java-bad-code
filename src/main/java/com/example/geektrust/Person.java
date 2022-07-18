package com.example.geektrust;

import java.util.ArrayList;
import java.util.List;

public class Person {
	private String name;
	private String gender;
	private String fatherName;
	private String motherName;
	private String spouseName;
	private List<Person> children;	

	public String getSpouseName() {
		return spouseName;
	}

	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

	public List<Person> getChildren() {
		return children;
	}

	public void setChildren(List<Person> children) {
		this.children = children;
	}

	public Person(String name,String gender) {
		this.name=name;
		this.gender=gender;
		this.setFatherName(null);
		this.motherName=null;
		this.children=new ArrayList<>();
	}
	
	public Person(String name, String gender, String fatherName, String motherName) {
		// TODO Auto-generated constructor stub
		this.name=name;
		this.gender=gender;
		this.fatherName=fatherName;
		this.motherName=motherName;
		this.children=new ArrayList<>();
	}

	public boolean addChild(Person child) {
		this.children.add(child);
		return true;
	}
	
	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getFatherName() {
		//System.out.println(this);
		return this.fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	
	public String toString() {
		return this.getName();
	}
}
