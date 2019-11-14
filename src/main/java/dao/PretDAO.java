package dao;

import entities.Pret;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class PretDAO {
    public void savePret(Pret pret){
        Transaction transaction = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            session.save(pret);

            transaction.commit();

        } catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Pret readPret(int id){
        Pret pret = null;
        Transaction transaction = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            pret = session.get(Pret.class, id);

            transaction.commit();


        } catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return pret;
    }

    public void deletePret(Pret pret){
        Transaction transaction = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            session.delete(pret);

            transaction.commit();

        } catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


}
