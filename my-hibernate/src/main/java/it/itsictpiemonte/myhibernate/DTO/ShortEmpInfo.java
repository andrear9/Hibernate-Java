package it.itsictpiemonte.myhibernate.DTO;

public class ShortEmpInfo {

    private Long empId;
    private String empNo;
    private String empName;
    private String deptName;

    //
    // Constructor have 3 parameters, will be used in the Hibernate Query.
    //
    public ShortEmpInfo(Long empId, String empNo, String empName, String deptName) {
        this.empId = empId;
        this.empNo = empNo;
        this.empName = empName;
        this.deptName = deptName;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

}
