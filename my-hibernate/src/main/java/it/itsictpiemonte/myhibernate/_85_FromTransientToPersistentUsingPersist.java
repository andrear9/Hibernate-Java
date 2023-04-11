package it.itsictpiemonte.myhibernate;

import java.time.LocalDateTime;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import it.itsictpiemonte.myhibernate.entity.Employee;
import it.itsictpiemonte.myhibernate.entity.Timekeeper;
import it.itsictpiemonte.myhibernate.utils.DataUtils;
import it.itsictpiemonte.myhibernate.utils.HibernateUtils;


public class _85_FromTransientToPersistentUsingPersist {

   private static Timekeeper persist_Transient(Session session, Employee emp) {
 
       // Note:
       // Configuring of timekeeperId       
       Timekeeper tk1 = new Timekeeper();

       tk1.setEmployee(emp);
       tk1.setInOut(Timekeeper.IN);
       tk1.setDateTime(LocalDateTime.now());
  
       // Now, 'tk1' is transient object
       System.out.println("- tk1 Persistent? " + session.contains(tk1));

       System.out.println("====== CALL persist(tk).... ===========");
 
       
       // Hibernate assign value to Id of 'tk1'
       // No action to DB.
       
       session.persist(tk1);
 
       System.out
               .println("- tk1.getTimekeeperId() = " + tk1.getTimekeeperId());
 
       
       // Now 'tk1' is Persistent object.
       // But no action with DB.
       // ==> true
       System.out.println("- tk1 Persistent? " + session.contains(tk1));

       System.out.println("- Call flush..");
 
       
       // Flush data to DB.
       // Hibernate execute insert statement.
       session.flush();

       int timekeeperId = tk1.getTimekeeperId();
       System.out.println("- timekeeperId = " + timekeeperId);
       System.out.println("- inOut = " + tk1.getInOut());
       System.out.println("- dateTime = " + tk1.getDateTime());
       System.out.println();
       return tk1;
   }

   public static void main(String[] args) {
       SessionFactory factory = HibernateUtils.getSessionFactory();

       Session session = factory.getCurrentSession();
       Employee emp = null;
       try {
           session.getTransaction().begin();

           emp = DataUtils.findEmployee(session, "E7499");

           persist_Transient(session, emp);

           session.getTransaction().commit();
       } catch (Exception e) {
           e.printStackTrace();
           session.getTransaction().rollback();
       }
   }
   
}
