package it.itsictpiemonte.myhibernate;

import java.util.List;

import org.hibernate.query.Query;

import it.itsictpiemonte.myhibernate.entity.Employee;
import it.itsictpiemonte.myhibernate.utils.HibernateUtils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class _20_QueryFewRowsUsingHQL {

    public static void main(String[] args) {
        SessionFactory factory = HibernateUtils.getSessionFactory();

        Session session = factory.getCurrentSession();

        try {

            // All the action with DB via Hibernate
            // must be located in one transaction
            // Start Transaction.                
            session.getTransaction().begin();
            
            // Create an HQL statement, query the object.
            // HQL with parameters.            
            // Equivalent to the SQL statement:
            // Select e.* from EMPLOYEE e cross join DEPARTMENT d
            // where e.DEPT_ID = d.DEPT_ID and d.DEPT_NO = :deptNo;        
            String sql = "Select e from " + Employee.class.getName() + " e "
                    + " where e.department.deptNo=:deptNo ";
 
            // Create query object.
            Query<Employee> query = session.createQuery(sql);
            query.setParameter("deptNo", "D10");

 
            // Execute query.
            List<Employee> employees = query.getResultList();

            for (Employee emp : employees) {
                System.out.println("Emp: " + emp.getEmpNo() + " : "
                        + emp.getEmpName());
            }
 
            // Commit data
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            // Rollback in case of an error occurred.
            session.getTransaction().rollback();
        }
    }
}

