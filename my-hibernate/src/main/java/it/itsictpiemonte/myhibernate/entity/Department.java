package it.itsictpiemonte.myhibernate.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "DEPARTMENT",
uniqueConstraints = { @UniqueConstraint(columnNames = { "DEPT_NO" }) })
public class Department {

	@Id
	@Column(name = "DEPT_ID")
	private int deptId;

	@Column(name = "DEPT_NO", length = 20, nullable = false)
	private String deptNo;

	@Column(name = "DEPT_NAME", nullable = false)
	private String deptName;

	@Column(name = "LOCATION")
	private String location;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
	private Set<Employee> employees = new HashSet<Employee>(0);

	public Department() {
	}

	public Department(int deptId, String deptName, String location) {
		this.deptId = deptId;
		this.deptNo = "D" + this.deptId;
		this.deptName = deptName;
		this.location = location;
	}

	public int getDeptId() {
		return deptId;
	}

	public String getDeptNo() {
		return deptNo;
	}

	public String getDeptName() {
		return deptName;
	}

	public String getLocation() {
		return location;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}


}

