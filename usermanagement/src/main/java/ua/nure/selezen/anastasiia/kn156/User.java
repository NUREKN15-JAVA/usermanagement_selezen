package ua.nure.selezen.anastasiia.kn156;

import java.util.Calendar;
import java.util.Date;

public class User {
	private Long id;
	private String firstName;
	private String lastName;
	private Date dateOfBirthday;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Date getDateOfBirthday() {
		return dateOfBirthday;
	}
	public void setDateOfBirthday(Date dateOfBirthday) {
		this.dateOfBirthday = dateOfBirthday;
	}
	public String getFullName() {
		return new StringBuilder(getLastName()).append(", ").append(getFirstName()).toString();
	}
	public int getAge() {
		Calendar calendar = Calendar.getInstance();
		Calendar today = Calendar.getInstance();
		calendar.setTime(dateOfBirthday);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		int age = today.get(Calendar.YEAR) - calendar.get(Calendar.YEAR);
		if (today.get(Calendar.DAY_OF_YEAR) <= calendar.get(Calendar.DAY_OF_YEAR)) age--;
		return age;
	}
}
