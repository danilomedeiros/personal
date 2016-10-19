package com.medeiros.personal.domain;

public class Aluno {
	
	private String firstName;
	private String lastName;
	private boolean male;
	private String email;
	private String phone;
	private String bio;

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(final String phone) {
		this.phone = phone;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(final String bio) {
		this.bio = bio;
	}

	public boolean isMale() {
		return male;
	}

	public void setMale(final boolean male) {
		this.male = male;
	}
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}
}
