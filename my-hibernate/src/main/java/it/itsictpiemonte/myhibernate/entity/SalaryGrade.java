package it.itsictpiemonte.myhibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SALARY_GRADE")
public class SalaryGrade {

	@Id
	@Column(name = "GRADE")
	private int grade;

	@Column(name = "HIGH_SALARY", nullable = false)
	private float lowSalary;

	@Column(name = "LOW_SALARY", nullable = false)
	private float highSalary;

	public SalaryGrade() {
	}

	public SalaryGrade(int grade, float lowSalary, float highSalary) {
		super();
		this.grade = grade;
		this.lowSalary = lowSalary;
		this.highSalary = highSalary;
	}

	public int getGrade() {
		return grade;
	}

	public float getLowSalary() {
		return lowSalary;
	}

	public float getHighSalary() {
		return highSalary;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public void setLowSalary(float lowSalary) {
		this.lowSalary = lowSalary;
	}

	public void setHighSalary(float highSalary) {
		this.highSalary = highSalary;
	}

	
}

