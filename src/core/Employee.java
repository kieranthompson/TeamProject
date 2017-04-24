package core;

import database.DBConnection;

import javax.xml.transform.Result;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Employee {

	private int empID;
	private String firstName, lastName;
	private String password;
	private ArrayList<Employee> employees = new ArrayList<Employee>();

	public Employee(){

	}
	
	public Employee(int empID, String firstName, String lastName, String password){
		this.empID = empID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
	}
	
	public Employee(String firstName, String lastName, String password){
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
	}



	public int getEmpID() {
		return empID;
	}

	public void setEmpID(int empID) {
		this.empID = empID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
}
