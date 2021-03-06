package com.home.client;

import java.util.Date;

import org.hibernate.Session;

import com.home.entities.Address;
import com.home.entities.Employee;
import com.home.util.HibernateUtil;

public class ClientTest {

	public static void main(String[] args) {
		try(Session session=HibernateUtil.getSessionFactory().openSession()){
			createEmployee(session);
			//getEmployeeById(session);
			//getAddressById(session);
			//updateEmployeeById(session);
			//deleteEmployeeById(session);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static void getAddressById(Session session) {
		Address address=session.get(Address.class, 1);
		if(address != null) {
			System.out.println(address);
			System.out.println(address.getEmployee());
		}
		else
			System.out.println("Address does not exist!!!");
		}

	private static void deleteEmployeeById(Session session) {
		Employee employee=session.get(Employee.class, 1);
		if(employee != null) {
			session.beginTransaction();
			session.delete(employee);
			session.getTransaction().commit();
		}
	}

	private static void updateEmployeeById(Session session) {
		session.beginTransaction();
		Employee employee=session.get(Employee.class, 1);
		if(employee != null) {
			employee.setEmployeeName("Vivek Garg");
		    employee.setEmail("vivek.garg@gmail.com");
		    employee.setSalary(10000.00);
		    session.update(employee);
		    session.getTransaction().commit();
		}
		else
			System.out.println("Employee does not exist!!!");
		}

	private static void getEmployeeById(Session session) {
		Employee employee=session.get(Employee.class, 1);
		if(employee != null) {
			System.out.println(employee);
		}
		else
			System.out.println("Employee does not exist!!!");
		}

	private static void createEmployee(Session session) {
		session.beginTransaction();
		Employee employee1=new Employee();
		employee1.setEmployeeName("Vivek");
		employee1.setEmail("vivek@gmail.com");
		employee1.setDoj(new Date());
		employee1.setSalary(16000.00);
		
		Employee employee2=new Employee();
		employee2.setEmployeeName("Prabhat");
		employee2.setEmail("prabhat@gmail.com");
		employee2.setDoj(new Date());
		employee2.setSalary(15000.00);
		
		Address address1=new Address();
		address1.setCity("Bulandshahr");
		address1.setState("U.P");
		address1.setStreet("Hanuman Chowck");
		address1.setPin(123456L);
		
		
		Address address2=new Address();
		address2.setCity("Hapur");
		address2.setState("U.P");
		address2.setStreet("Pakka bagh");
		address2.setPin(453456L);
		
		Address address3=new Address();
		address3.setCity("Delhi");
		address3.setState("DL");
		address3.setStreet("Carol bagh");
		address3.setPin(453456L);
		
		employee1.getAddress().add(address1);
		employee1.getAddress().add(address2);
		employee1.getAddress().add(address3);
		
		address1.getEmployee().add(employee1);
		address2.getEmployee().add(employee1);
		address3.getEmployee().add(employee1);
		
		employee2.getAddress().add(address2);
		employee2.getAddress().add(address3);
		
		address2.getEmployee().add(employee2);
		address3.getEmployee().add(employee2);
		
		session.save(employee1);
		session.save(employee1);
		
		session.getTransaction().commit();
	}
}
