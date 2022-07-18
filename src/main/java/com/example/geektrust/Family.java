package com.example.geektrust;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Family {
	private Person king;
	private Person queen;
	private Map<String,Person> record;
	private Map<Person,Person> spouseRecord ;
	private static Family family;
	private static String st=new String();
	private Family(String[] inputFamily) throws IOException {
		king=new Person("Shan","Male");
		queen=new Person("Anga","Female");
		record=new HashMap<>();
		spouseRecord=new HashMap<>();
		record.put("Shan", king);
		record.put("Anga", queen);
		spouseRecord.put(king, queen);
		spouseRecord.put(queen, king);
		createFamily(inputFamily);
	}
	
	private void createFamily(String[] inputFamily) throws IOException {
		for(String st:inputFamily) {			
			String[] command=st.split(" ");
			if(command.length==4) {
				addRelation(command[0],command[1],command[2],command[3]);
			}
			if(command.length==2) {
				addSpouse(command[0],command[1]);
			}
		}
	}
	
	public static Family getFamilyInstance(String[] inputFamily) throws IOException {
		if(family==null) {
			family=new Family(inputFamily);
		}
		return family;
	}
	
	public void addMember(String motherName,String name,String gender) {
		
		Person mother=findPerson(motherName);		
		if(mother==null) {
			System.out.println("PERSON_NOT_FOUND");
			return;
		}
		if(mother.getGender().equals("Male")) {
			System.out.println("CHILD_ADDITION_FAILED");
			return;
		}		
		Person father=spouseRecord.get(mother);		
		Person child=new Person(name,gender,father.getName(),motherName);		
		record.put(name, child);
		mother.addChild(child);
		father.addChild(child);
		System.out.println("CHILD_ADDITION_SUCCEEDED");
		
		return;
	}
	
	public void addRelation(String fatherName,String motherName,String name,String gender) {
		Person father=findPerson(fatherName);
		Person mother=findPerson(motherName);
		Person child=new Person(name,gender,fatherName,motherName);		
		record.put(name, child);
		record.put(fatherName, father);
		record.put(motherName, mother);		
		if(father!=null)
		father.addChild(child);
		if(mother!=null)
		mother.addChild(child);
	}
	
	public void addSpouse(String husbandName,String wifeName) {
		Person husband=findPerson(husbandName);
		Person wife=findPerson(wifeName);		
		spouseRecord.put(wife, husband);
		spouseRecord.put(husband, wife);
		
	}
	
	private Person findPerson(String name) {
		for(Map.Entry<String,Person> entry:record.entrySet()) {
			if(entry.getKey().equals(name)) {				
				return entry.getValue();
			}
				
		}
		return null;
	}

	public void getRelationship(String name,String relation) {
		Person person=findPerson(name);
		if(person==null) {
			System.out.println("PERSON_NOT_FOUND");
			return;
		}
		switch (relation) {
		case "Son":
			List<String> sonList=getSon(person);
			print(sonList);
			break;
		case "Daughter":
			List<String> daughterList=getDaughter(person);
			print(daughterList);
			break;
		case "Siblings":
			List<String> siblingList=getSiblings(person);
			print(siblingList);
			break;
		case "Paternal-Uncle":
			List<String> paternalUnclelist=getPaternaluncle(person);
			print(paternalUnclelist);
			break;	
		case "Maternal-Uncle":
			List<String> maternalUnclelist=getMaternaluncle(person);
			print(maternalUnclelist);
			break;
		case "Maternal-Aunt":
			List<String> maternalAuntlist=getMaternalaunt(person);
			print(maternalAuntlist);
			break;
		case "Paternal-Aunt":
			List<String> paternalAuntlist=getPaternalaunt(person);
			print(paternalAuntlist);
			break;
		case "Sister-In-Law":
			List<String> sisterInlaw=getSisterinlaw(person);
			print(sisterInlaw);
			break;
		case "Brother-In-Law":
			List<String> brotherInlaw=getBrotherinlaw(person);
			print(brotherInlaw);
			break;
		}			
		
	}
	
	private void print(List<String> nameList) {
		if(nameList.size()==0) {
			System.out.println("NONE");
			return;
		}
		for(String name:nameList) {
			System.out.print(name+" ");
		}
		System.out.println();
	}

	public List<String> getSon(Person person){
		List<String> son=new ArrayList<>();
		for(Person child:person.getChildren())
		{
			if(child.getGender().equals("Female"))
				continue;
			son.add(child.getName());
		}
		return son;
	}
	
	public List<String> getDaughter(Person person){		
		List<String> daughter=new ArrayList<>();
		if(person!=null) {
			for(Person child:person.getChildren())
			{
				if(child.getGender().equals("Male"))
					continue;
				daughter.add(child.getName());
			}
		}
		
		return daughter;
	}
	
	public List<String> getSiblings(Person person){
		List<String> siblings=new ArrayList<>();
		String fatherName=person.getFatherName();
		if(fatherName.equals("Dummy"))
			return siblings;
		Person father=findPerson(fatherName);		
		for(Person child:father.getChildren())
		{
			if(child.getName().equals(person.getName()))
				continue;
			siblings.add(child.getName());
		}
		return siblings;
	}
	
	public List<String> getPaternalaunt(Person person){
		List<String> paternalAunt=new ArrayList<>();
		String fatherName=person.getFatherName();
		if(fatherName.equals("Dummy"))
			return paternalAunt;
		Person father=findPerson(fatherName);		
		String grandFathername=father.getFatherName();
		if(grandFathername.equals("Dummy"))
			return paternalAunt;
		Person grandFather=findPerson(grandFathername);		
		for(Person child:grandFather.getChildren())
		{
			if(child.getGender().equals("Male"))
				continue;
			paternalAunt.add(child.getName());
		}
		return paternalAunt;
		
	}
	
	public List<String> getPaternaluncle(Person person){
		List<String> paternalUncle=new ArrayList<>();
		String fatherName=person.getFatherName();
		if(fatherName.equals("Dummy"))
			return paternalUncle;
		Person father=findPerson(fatherName);	
		String grandFathername=father.getFatherName();
		if(grandFathername.equals("Dummy"))
			return paternalUncle;
		Person grandFather=findPerson(grandFathername);		
		for(Person child:grandFather.getChildren())
		{
			if(child.getName().equals(fatherName) || child.getGender().equals("Female"))
				continue;
			paternalUncle.add(child.getName());
		}
		return paternalUncle;
		
	}
	
	
	public List<String> getMaternalaunt(Person person){
		List<String> maternalAunt=new ArrayList<>();
		String motherName=person.getMotherName();
		if(motherName.equals("Dummy"))
			return maternalAunt;
		Person mother=findPerson(motherName);
		String grandFathername=mother.getFatherName();
		if(grandFathername.equals("Dummy"))
			return maternalAunt;
		Person grandFather=findPerson(grandFathername);		
		for(Person child:grandFather.getChildren())
		{
			if(child.getName().equals(motherName) || child.getGender().equals("Male"))
				continue;
			maternalAunt.add(child.getName());
		}
		return maternalAunt;
	}
	

	public List<String> getMaternaluncle(Person person){
		List<String> maternalUncle=new ArrayList<>();
		String motherName=person.getMotherName();
		if(motherName.equals("Dummy"))
			return maternalUncle;
		Person mother=findPerson(motherName);
		String grandFathername=mother.getFatherName();
		if(grandFathername.equals("Dummy"))
			return maternalUncle;
		Person grandFather=findPerson(grandFathername);		
		for(Person child:grandFather.getChildren())
		{
			if(child.getGender().equals("Female"))
				continue;
			maternalUncle.add(child.getName());
		}
		return maternalUncle;
	}
	
	public List<String> getSisterinlaw(Person person){
		List<String> sisterInlaw=new ArrayList<>();
		Person spouse=spouseRecord.get(person);
		
		if(!person.getFatherName().equals("Dummy")) {
			Person personFather=findPerson(person.getFatherName());
			for(Person child:personFather.getChildren()) {
				if(child!=person && child.getGender().equals("Male")) {
					sisterInlaw.add(spouseRecord.get(child).getName());
				}
			}
		}
		if(spouse!=null && !spouse.getFatherName().equals("Dummy")) {
			Person spouseFather=findPerson(spouse.getFatherName());
			for(Person child:spouseFather.getChildren()) {
				if(child.getGender().equals("Female") && child!=spouse) {
					sisterInlaw.add(child.getName());
				}
			}
		}
		
		return sisterInlaw;
	}
	

	public List<String> getBrotherinlaw(Person person){
		List<String> brotherInlaw=new ArrayList<>();
		Person spouse=spouseRecord.get(person);		
		if(!person.getFatherName().equals("Dummy")) {
			Person personFather=findPerson(person.getFatherName());
			for(Person child:personFather.getChildren()) {
				if(child!=person && child.getGender().equals("Female")) {
					brotherInlaw.add(spouseRecord.get(child).getName());
				}
			}
		}
		if(spouse!=null && !spouse.getFatherName().equals("Dummy")) {
			Person spouseFather=findPerson(spouse.getFatherName());
			for(Person child:spouseFather.getChildren()) {
				if(child.getGender().equals("Male") && child!=spouse) {
					brotherInlaw.add(child.getName());
				}
			}
		}
		
		return brotherInlaw;
	}
	
	
}
