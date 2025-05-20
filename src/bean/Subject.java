package bean;

import java.io.Serializable;

public class Subject implements Serializable {
	private School school;
	private String cd;
	private String name;

	public School getSchool() {
		return school;
	}

	public String getCd() {
		return cd;
	}

	public String getName() {
		return name;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public void setCd(String cd) {
		this.cd = cd;
	}

	public void setName(String name) {
		this.name = name;
	}
}
