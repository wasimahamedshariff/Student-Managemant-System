package sdbms;

import java.util.Scanner;

import customexception.InvalidChoiceException;


public class Solution {
	public static void main(String[] args) {
		System.out.println("Welcome to Student Management System");
		System.out.println("------------------------------------");

		Scanner scan=new Scanner(System.in);
		StudentManagementSystem sms=new StudentManagementSystemImpl();//Upcastingt(Contract of abstract)


		while(true) {
			System.out.println("1:Add Student\n2:Display Student");
			System.out.println("3:Display All Students\n4:Remove Student");
			System.out.println("5:Remove All Student\n6:Update Student");
			System.out.println("7:Count Student\n8:Sort Student\n9:Get Student With Highest Marks\n10:Get Student Lowest Marks\n11:EXIT");
			System.out.println("Enter Choice");
			int choice=scan.nextInt();

			switch(choice) {
			case 1:
				sms.addStudent();
				break;
			case 2:
				sms.displayStudent();
				break;
			case 3:
				sms.displayAllStudent();
				break;
			case 4:
				sms.removeStudent();
				break;
			case 5:
				sms.removeAllStudent();
				break;
			case 6:
				sms.updateStudent();
				break;
			case 7:
				sms.countStudent();
				break;
			case 8:
				sms.sortStudents();
				break;
			case 9:
				sms.getStudentWithHighestMarks();
			case 10:
				sms.getStudentWithLowestMarks();
			case 11:
				System.out.println("THANK YOU");
				System.exit(0);
				break;
			default:
				try {
					throw new InvalidChoiceException("Enter Valid Choice");
				}
				catch(Exception e) {
					System.out.println(e.getMessage());
				}
			}
			System.out.println("-----------");
		}
	}
}