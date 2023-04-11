package it.itsictpiemonte.myhibernate;

import java.time.LocalDateTime;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import it.itsictpiemonte.myhibernate.entity.Employee;
import it.itsictpiemonte.myhibernate.entity.Timekeeper;
import it.itsictpiemonte.myhibernate.utils.DataUtils;
import it.itsictpiemonte.myhibernate.utils.HibernateUtils;


public class _87_FromTransientToPersistentUsingSaveOrUpdate { 


	private static Timekeeper saveOrUpdate_Transient(Session session, Employee emp) {

		// Remember configuration of timekeeperId:
		// Create an Object, Transitent state.
		Timekeeper tk3 = new Timekeeper();

		tk3.setEmployee(emp);
		tk3.setInOut(Timekeeper.IN);
		tk3.setDateTime(LocalDateTime.now());

		// Now 'tk3' are state Transient.
		System.out.println("- tk3 Persistent? " + session.contains(tk3));

		System.out.println("====== CALL saveOrUpdate(tk).... ===========");

		// Here Hibernate checks, 'tk3' have ID or not (timekeeperId)
		// If no, it will be assigned automatically
		session.saveOrUpdate(tk3);

		System.out
		.println("- tk3.getTimekeeperId() = " + tk3.getTimekeeperId());

		// Now 'tk3' has Persistent state
		// It has been managed in Session.
		// But no action insert, or update to DB.
		// ==> true
		System.out.println("- tk3 Persistent? " + session.contains(tk3));

		System.out.println("- Call flush..");

		// To push data into the DB, call flush().
		// If not call flush() data will be pushed to the DB when calling commit().
		// Now possible to Insert or Update DB. (!!!)
		// Depending on the ID of 'tk3' exists in the DB or not
		session.flush();

		int timekeeperId = tk3.getTimekeeperId();
		System.out.println("- timekeeperId = " + timekeeperId);
		System.out.println("- inOut = " + tk3.getInOut());
		System.out.println("- dateTime = " + tk3.getDateTime());
		System.out.println();
		return tk3;
	}

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtils.getSessionFactory();

		Session session = factory.getCurrentSession();
		Employee emp = null;
		try {
			session.getTransaction().begin();

			emp = DataUtils.findEmployee(session, "E7499");

			saveOrUpdate_Transient(session, emp);

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}
}
