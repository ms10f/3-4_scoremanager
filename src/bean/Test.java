package bean;

import java.io.Serializable;

public class Test implements Serializable {
	private School school;
	private Student student;
	private Subject subject;
	private int no;
	private int point; // -1は無しとして扱う

	public School getSchool() {
		return school;
	}

	public Student getStudent() {
		return student;
	}

	public Subject getSubject() {
		return subject;
	}

	public int getNo() {
		return no;
	}

	public int getPoint() {
		return point;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public void setPoint(int point) {
		this.point = point;
	}
}
