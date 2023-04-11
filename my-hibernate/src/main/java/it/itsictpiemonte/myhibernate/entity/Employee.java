package it.itsictpiemonte.myhibernate.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {

	@Id
	@Column(name = "EMP_ID")
	private long empId;

	@NaturalId // equivalente di unique ma è un dato non aggiornabile (updatable=false)
	@Column(name = "EMP_NO", length = 20, nullable = false, unique=true)
	private String empNo;

	@Column(name = "EMP_NAME", length = 50, nullable = false)
	private String empName;

	@Column(name = "JOB", length = 30, nullable = false)
	private String job;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MNG_ID")
	private Employee manager;

	@Column(name = "HIRE_DATE", nullable = false)
	private LocalDate hireDate;

	@Column(name = "SALARY", nullable = false)
	private float salary;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPT_ID", nullable = false)
	private Department department;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empId")
	private Set<Employee> employees = new HashSet<Employee>(0);

	public Employee() {
	}

	public Employee(long empId, String empNo, String empName, String job, Employee manager, LocalDate hireDate,
			float salary, Department department, Set<Employee> employees) {
		super();
		this.empId = empId;
		this.empNo = empNo;
		this.empName = empName;
		this.job = job;
		this.manager = manager;
		this.hireDate = hireDate;
		this.salary = salary;
		this.department = department;
		this.employees = employees;
	}

	public long getEmpId() {
		return empId;
	}

	public String getEmpNo() {
		return empNo;
	}

	public String getEmpName() {
		return empName;
	}

	public String getJob() {
		return job;
	}

	public Employee getManager() {
		return manager;
	}

	public LocalDate getHireDate() {
		return hireDate;
	}

	public float getSalary() {
		return salary;
	}

	public Department getDepartment() {
		return department;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmpId(long empId) {
		this.empId = empId;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public void setHireDate(LocalDate date) {
		this.hireDate = date;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	
}
