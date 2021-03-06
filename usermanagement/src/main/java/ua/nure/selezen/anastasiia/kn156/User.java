package ua.nure.selezen.anastasiia.kn156;

import java.util.Calendar;
import java.util.Date;

public class User {
	private static Long id;
	private static String firstName;
	private static String lastName;
	private Date dateOfBirthday;

	public User(User user) {
		id = user.getId();
		firstName = user.getFirstName();
		lastName = user.getLastName();
		dateOfBirthday = user.getDateOfBirthday();
	}

	public User() {

	}

	public static Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public static String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public static String getLastName() {
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
		if (today.get(Calendar.DAY_OF_YEAR) <= calendar.get(Calendar.DAY_OF_YEAR))
			age--;
		return age;
	}

	public User(Long id, String firstName, String lastName, Date date) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirthday = date;
	}

	public User(String firstName2, String lastName2, Date date) {
		this.firstName = firstName2;
		this.lastName = lastName2;
		this.dateOfBirthday = date;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (this.getId() == null && ((User) obj).getId() == null) {
			return true;
		}
		return this.getId().equals(((User) obj).getId());
	}

	public int hashCode() {
		if (this.getId() == null) {
			return 0;
		}
		return this.getId().hashCode();
	}

}
