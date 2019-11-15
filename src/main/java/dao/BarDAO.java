package dao;

public class BarDAO {
//    public void savePret(Bar pret){
//        Transaction transaction = null;
//
//        try(Session session = HibernateUtil.getSessionFactory().openSession()){
//            transaction = session.beginTransaction();
//
//            session.save(pret);
//
//            transaction.commit();
//
//        } catch (Exception e){
//            if (transaction != null){
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        }
//    }
//
//    public Bar readPret(int id){
//        Bar pret = null;
//        Transaction transaction = null;
//
//        try(Session session = HibernateUtil.getSessionFactory().openSession()){
//            transaction = session.beginTransaction();
//
//            pret = session.get(Bar.class, id);
//
//            transaction.commit();
//
//
//        } catch (Exception e){
//            if (transaction != null){
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        }
//
//        return pret;
//    }
//
//    public void deletePret(Bar pret){
//        Transaction transaction = null;
//
//        try(Session session = HibernateUtil.getSessionFactory().openSession()){
//            transaction = session.beginTransaction();
//
//            session.delete(pret);
//
//            transaction.commit();
//
//        } catch (Exception e){
//            if (transaction != null){
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        }
//    }
//

}
