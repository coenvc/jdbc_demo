package com.company;

public class Main {

    public static void main(String[] args) {
    	Employee Coen = new Employee("Coen","van Campenhout","coenvc@gmail.com");
		Database db = new Database();
		db.insert(Coen);
	}
}
