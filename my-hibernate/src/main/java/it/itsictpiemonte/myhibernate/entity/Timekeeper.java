package it.itsictpiemonte.myhibernate.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TIMEKEEPER")
public class Timekeeper {
	
	public static final char IN = 'I';
	public static final char OUT = 'O';

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)  //autoincrement
	private int timekeeperId;

	@Column(name = "DATE_TIME", nullable = false)
	private LocalDateTime dateTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMP_ID", nullable = false)
	private Employee employee;

	// 'I' or 'O'
	@Column(name = "IN_OUT", nullable = false, length = 1)
	private char inOut;

	public Timekeeper(LocalDateTime dateTime, Employee employee, char inOut) {
		super();
		this.dateTime = dateTime;
		this.employee = employee;
		this.inOut = inOut;
	}

	public Timekeeper() {
		super();
	}

	public int getTimekeeperId() {
		return timekeeperId;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public Employee getEmployee() {
		return employee;
	}

	public char getInOut() {
		return inOut;
	}

	public void setTimekeeperId(int timekeeperId) {
		this.timekeeperId = timekeeperId;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public void setInOut(char inOut) {
		this.inOut = inOut;
	}

}
