package dao;

import entities.Stal;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class StalDAO {
    public void saveStal(Stal stal){
        Transaction transaction = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            System.out.println("transaction created");
            session.save(stal);
            System.out.println("saved");
            transaction.commit();
            System.out.println("commited");


        } catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Stal readStal(int id){
        Stal stal = null;
        Transaction transaction = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            stal = session.get(Stal.class, id);


            transaction.commit();


        } catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return stal;
    }

    public void deleteStal(Stal stal){
        Transaction transaction = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            session.delete(stal);

            transaction.commit();


        } catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
