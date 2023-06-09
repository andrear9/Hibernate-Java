package it.itsictpiemonte.myhibernate;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import it.itsictpiemonte.myhibernate.entity.Department;
import it.itsictpiemonte.myhibernate.entity.Employee;
import it.itsictpiemonte.myhibernate.utils.HibernateUtils;


public class _40_QueryUniqueResult {

    public static Department getDepartment(Session session, String deptNo) {
        String sql = "Select d from " + Department.class.getName() + " d "
                + " where d.deptNo= :deptNo ";
        Query<Department> query = session.createQuery(sql);
        query.setParameter("deptNo", deptNo);
        return (Department) query.getSingleResult();
    }

    public static Employee getEmployee(Session session, Long empId) {
        String sql = "Select e from " + Employee.class.getName() + " e "//
                + " where e.empId= :empId ";
        Query<Employee> query = session.createQuery(sql);
        query.setParameter("empId", empId);
        return (Employee) query.getSingleResult();
    }

    public static void main(String[] args) {
        SessionFactory factory = HibernateUtils.getSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.getTransaction().begin();

            Department dept = getDepartment(session, "D10");
            Set<Employee> emps = dept.getEmployees();

            System.out.println("Dept Name: " + dept.getDeptName());
            for (Employee emp : emps) {
                System.out.println(" Emp name: " + emp.getEmpName());
            }

            Employee emp = getEmployee(session, 7839L);
            System.out.println("Emp Name: " + emp.getEmpName());

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }
    
}
