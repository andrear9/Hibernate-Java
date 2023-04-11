package it.itsictpiemonte.myhibernate;

import java.util.List;

import org.hibernate.query.Query;

import it.itsictpiemonte.myhibernate.DTO.ShortEmpInfo;
import it.itsictpiemonte.myhibernate.entity.Department;
import it.itsictpiemonte.myhibernate.entity.Employee;
import it.itsictpiemonte.myhibernate.utils.HibernateUtils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class _30_QueryProjectionUsingHQLAndJavaBean {

    public static void main(String[] args) {
        SessionFactory factory = HibernateUtils.getSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.getTransaction().begin();
 
            // Using constructor of ShortEmpInfo
            String sql = "Select new " + ShortEmpInfo.class.getName()
                    + "(e.empId, e.empNo, e.empName, d.deptName)" + " from "
                    + Employee.class.getName() + " e, " +Department.class.getName() + " d "
                    + "WHERE e.department.deptId = d.deptId";

            Query<ShortEmpInfo> query = session.createQuery(sql);
 
            
            // Execute query.
            // Get a List of ShortEmpInfo
            List<ShortEmpInfo> employees = query.getResultList();

            for (ShortEmpInfo emp : employees) {
                System.out.println("Emp: " + emp.getEmpNo() + " : "
                        + emp.getEmpName() + ", "+emp.getDeptName());
            }
 
            // Commit data.
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            // Rollback in case of an error occurred.
            session.getTransaction().rollback();
        }
    }
    
}
