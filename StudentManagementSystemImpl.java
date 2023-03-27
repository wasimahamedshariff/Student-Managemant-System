package sdbms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import customexception.InvalidChoiceException;
import customexception.StudentNotFoundException;
import customsorting.SortStudentByAge;
import customsorting.SortStudentById;
import customsorting.SortStudentByMarks;
import customsorting.SortStudentByName;

public class StudentManagementSystemImpl implements StudentManagementSystem{

	//Scanner
	//M->LHM ->upcasting ->db
	//key->id->String value->student object->student
	Scanner scan=new Scanner(System.in);
	Map<String,Student>db=new LinkedHashMap<String,Student>();
	@Override
	public void addStudent() {
		//Accept age,name& marks
		//create student instance
		//Add Entry into DB(Map)->id,std
		//print the student id
		System.out.println("Enter Student Age:");
		int age=scan.nextInt();
		System.out.println("Enter Student Name:");
		String name=scan.next();
		System.out.println("Enter Student Marks:");
		int marks=scan.nextInt();

		Student std=new Student(age,name,marks);
		db.put(std.getId(),std);
		System.out.println("Student Record Inserted Sucessfully");
		System.out.println("Student Id is "+std.getId());
	}
	@Override
	public void displayStudent() {
		//Accept Id
		//check if id(key) is present in the DB or not ->containsKey()
		//if the key(id) is present,get the value(student object)& display getter()
		//if key(id) is not present,creat an exception->StudentNotFoundException
		//invoke it (throw),handle it with suitable message
		System.out.println("Enter Student Id:");
		String id=scan.next();//String id=scan.next().toUpperCase();
		id=id.toUpperCase();

		if(db.containsKey(id)) {
			Student s=db.get(id);
			System.out.println("Id: "+s.getId());
			System.out.println("Age: "+s.getAge());
			System.out.println("Name: "+s.getName());
			System.out.println("Marks: "+s.getMarks());
		}
		else {
			try {
				throw new StudentNotFoundException("Student with "+id+" is not found");
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	@Override
	public void displayAllStudent() {
		System.out.println("Student details are as follows:");
		System.out.println("-------------------------------");
		Set<String>keys=db.keySet();
		for(String key : keys) {
			Student obj=db.get(key);
			System.out.println(obj);//System.out.println(db.get(key));
		}
	}
	@Override
	public void removeStudent() {
		//Accept ID,conver it to uppercase
		//check if thr id(key)is present or not
		//if the id is present ->remove()
		//else->StudentNotFoundException
		System.out.println("Enter Student Id to be Removed:");
		String id=scan.next();
		id=id.toUpperCase();
		if(db.containsKey(id)) {
			System.out.println("Student Record Found!");
			System.out.println(db.get(id));
			db.remove(id);
			System.out.println("Id Deleted Sucessfully");
		}
		else {
			try {
				throw new StudentNotFoundException("Student with Id "+id+" is not found");
			}
			catch(Exception e) {//(Generalization)upcasting->Exception e=new StudentNotFoundException
				System.out.println(e.getMessage());
			}
		}
	}
	@Override
	public void removeAllStudent() {
		if(db.size()!=0) {
			System.out.println("Available Student Records: "+db.size());
			db.clear();
			System.out.println("All the Students Records Deleted Sucessfully!");
		}
		else {
			try {
				throw new StudentNotFoundException("No Records To Delete!");
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	@Override
	public void updateStudent() {
		//Accept ID,Uppercase->check if id is there or not
		//if->id is present,get the student object->display 1:Age,2: ,3:....
		//Switch->case 1:accept age->setAge(),2: ,3:default :throw ICE
		//else->SNFE
		System.out.println("Enter Student ID to be Updated:");
		String id=scan.next();
		id=id.toUpperCase();

		if(db.containsKey(id)) {
			Student std=db.get(id);
			System.out.println("1:Update Age\n2:Update Name\n3:Update Marks");
			System.out.println("Enter Choice");
			int choice=scan.nextInt();

			switch(choice) {
			case 1:
				System.out.println("Enter Age:");
				int age=scan.nextInt();
				std.setAge(age);//std.setAge(scan.nextInt());
				System.out.println("Age updated Sucessfully");
				break;
			case 2:
				System.out.println("Enter Name:");
				String name=scan.next();
				std.setName(name);
				System.out.println("Name updated Sucessfully");
				break;
			case 3:
				System.out.println("Enter Marks:");
				int marks=scan.nextInt();
				std.setMarks(marks);
				System.out.println("Marks updated Sucessfully");
				break;
			default:
				try {
					throw new InvalidChoiceException("Enter Valid Choice");
				}
				catch(Exception e) {
					System.out.println(e.getMessage());
				}

			}
		}
		else {          
			try {
				throw new StudentNotFoundException("Student with ID "+id+" is not Found!");
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	@Override
	public void countStudent() {
		System.out.println("Number of Records: "+db.size());
	}
	@Override
	public void sortStudents() {
		//Convert Map into set
		//List->ArrayList-><Student>
		//for each loop->get the value(Student Object)from the Map->add it into AL.
		Set<String>keys=db.keySet();
		List<Student>list=new ArrayList<Student>();
		for(String key : keys) {
			Student s=db.get(key);
			list.add(s);//list.add(db.get(key));
		}

		System.out.println("1:Sort By Id\n2:Sort By Age\n3:Sort By Name");
		System.out.println("4:Sort By Marks\nEnter Choice:");
		int choice=scan.nextInt();

		switch(choice) {
		case 1:
			Collections.sort(list,new SortStudentById());
			display(list);
			break;
		case 2:
			Collections.sort(list,new SortStudentByAge());
			display(list);
			break;
		case 3:
			Collections.sort(list,new SortStudentByName());
			display(list);
			break;
		case 4:
			Collections.sort(list,new SortStudentByMarks());
			display(list);
			break;
		default:
			try {
				throw new InvalidChoiceException("Kindly Enter Valid Choice");
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	private void display(List<Student> list) {
		for(Student s:list) {
			System.out.println(s);
		}
	
	}
	@Override
	public void getStudentWithHighestMarks() {
		Set<String> keys=db.keySet();
		List<Student> list=new ArrayList<Student>();
		for(String key : keys) {
			list.add(db.get(key));
		}
		Collections.sort(list,new SortStudentByMarks());
		System.out.println("Student Details With Highest Marks:");
		System.out.println(list.get(list.size()-1));
	}
	@Override
	public void getStudentWithLowestMarks() {
		Set<String> keys=db.keySet();
		List<Student> list=new ArrayList<Student>();
		for(String key : keys) {
			list.add(db.get(key));
		}
		Collections.sort(list,new SortStudentByMarks());
		System.out.println("Student Details With Lowest Marks:");
		System.out.println(list.get(0));
	}
	
}