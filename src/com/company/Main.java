package com.company;

public class Main {

    public static void main(String[] args) {
    	Employee Employee = new Employee("Bjorn","Hamels","b.hamels@fontys.nl");
		Database db = new Database();
		db.getEmployeById(1);
	}
}
