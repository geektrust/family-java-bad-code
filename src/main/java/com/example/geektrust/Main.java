package com.example.geektrust;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		String[] inputFamily={"Shan Anga Chit Male",
		                      "Shan Anga Ish Male",
		                      "Shan Anga Vich Male",
		                      "Shan Anga Aras Male",
		                      "Dummy Dummy Vyan Male",
		                      "Dummy Dummy Amba Female",
		                      "Dummy Dummy Lika Female",
		                      "Dummy Dummy Chitra Female",
		                      "Shan Anga Satya Female",
		                      "Chit Amba Dritha Female",
		                      "Chit Amba Tritha Female",
		                      "Chit Amba Vritha Male",
		                      "Dummy Dummy Jaya Male",
		                      "Jaya Dritha Yodhan Male",
		                      "Vich Lika Vila Female",
		                      "Vich Lika Chika Female",
		                      "Aras Chitra Janki Female",
		                      "Aras Chitra Ahit Male",
		                      "Dummy Dummy Arit Male",
		                      "Arit Janki Laki Male",
		                      "Arit Janki Lavnya Female",
		                      "Vyan Satya Asva Male",
		                      "Vyan Satya Vyas Male",
		                      "Vyan Satya Atya Female",
		                      "Dummy Dummy Satvy Female",
		                      "Asva Satvy Vasa Male",
		                      "Dummy Dummy Krpi Female",
		                      "Vyas Krpi Kriya Male",
		                      "Vyas Krpi Krithi Female",
		                      "Chit Amba",
		                      "Vich Lika",
		                      "Aras Chitra",
		                      "Vyan Satya",
		                      "Jaya Dritha",
		                      "Arit Janki",
		                      "Asva Satvy",
		                      "Vyas Krpi"} ;	
		// Family Instance		
		Family family=Family.getFamilyInstance(inputFamily);	
		
		// Query
		File file=new File(args[0]);		
		BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
		String query=new String();
		while((query=bufferedReader.readLine())!=null) {
			String[] command=query.split(" ");
			if(command.length==3) {
				family.getRelationship(command[1], command[2]);
			}
			else if(command.length==4) {
				family.addMember(command[1], command[2],command[3]);
			}
		}
	}
}
