package com.company;

public class Main {

    public static void main(String[] args) {
    	Employee Coen = new Employee("Jan","van Campenhout","coenvc@gmail.com");
		Database db = new Database();
		db.update(Coen,9);
	}
}
