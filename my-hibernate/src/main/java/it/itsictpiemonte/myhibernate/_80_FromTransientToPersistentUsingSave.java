package it.itsictpiemonte.myhibernate;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import it.itsictpiemonte.myhibernate.entity.Employee;
import it.itsictpiemonte.myhibernate.entity.Timekeeper;
import it.itsictpiemonte.myhibernate.utils.DataUtils;
import it.itsictpiemonte.myhibernate.utils.HibernateUtils;


public class _80_FromTransientToPersistentUsingSave {

     private static Timekeeper persist_Transient(Session session, Employee emp) {
     
       // Remember configuration of timekeeperId:
       // Create an Object, Transitent state.        
       Timekeeper tk2 = new Timekeeper();

       tk2.setEmployee(emp);
       tk2.setInOut(Timekeeper.IN);
       tk2.setDateTime(LocalDateTime.now());

       // Now 'tk2' are state Transient.
       System.out.println("- tk2 Persistent? " + session.contains(tk2));

       System.out.println("====== CALL save(tk).... ===========");

       // save() very similar to persist()
       // save() return ID, persist() return void.
       // Hibernate assign ID value to 'tk2', no action with DB
       // And return ID of 'tk2'.        
       Serializable id = session.save(tk2);

       System.out.println("- id = " + id);

       //
       System.out
               .println("- tk2.getTimekeeperId() = " + tk2.getTimekeeperId());

     
       // Now, 'tk2' has Persistent state
       // It has been managed in Session.
       // ==> true
       System.out.println("- tk2 Persistent? " + session.contains(tk2));

       System.out.println("- Call flush..");

       // To push data into the DB, call flush().
       // If not call flush() data will be pushed to the DB when calling commit().
       // Will execute insert statement.
       session.flush();

       int timekeeperId = tk2.getTimekeeperId();
       System.out.println("- timekeeperId = " + timekeeperId);
       System.out.println("- inOut = " + tk2.getInOut());
       System.out.println("- dateTime = " + tk2.getDateTime());
       System.out.println();
       return tk2;
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
