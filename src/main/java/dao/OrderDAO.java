package dao;


import entities.Order;
import entities.Pret;
import entities.Stal;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class OrderDAO {
    public void saveOrder(Order order){
        Transaction transaction = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            for (Stal stal : order.getStalList()){
                session.save(stal);
            }

            for (Pret pret : order.getPretList()){
                session.save(pret);
            }

            session.save(order);

            transaction.commit();


        } catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Order getOrder(int id){
        Order order = null;
        Transaction transaction = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            order = session.get(Order.class, id);

            transaction.commit();


        } catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return order;

    }

    public void saveOrUpdateOrder(Order order){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            session.saveOrUpdate(order);

            transaction.commit();

        } catch (Exception e){
            if (transaction!= null){
                System.out.println("in dao");
                transaction.rollback();
            }
            e.printStackTrace();
        }
        
    }

    public void deleteOrder(Order order){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            session.delete(order);

            transaction.commit();

        } catch (Exception e){
            if (transaction!= null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Order> getAllOrders(){
        Transaction transaction = null;
        List<Order> allOrderList = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Order> cq = cb.createQuery(Order.class);
            Root<Order> root = cq.from(Order.class);
            CriteriaQuery<Order> all = cq.select(root);

            TypedQuery<Order> allOrders = session.createQuery(all);

            allOrderList = allOrders.getResultList();

            transaction.commit();

        } catch (Exception e){
            if (transaction!= null){
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return allOrderList;
    }

    public void deleteById(int id){
        Order order = null;
        Transaction transaction = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            order = new Order();
            order.setId(id);
            session.delete(order);


            transaction.commit();


        } catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Order> getOrdersWithNoRoute(){
        List<Order> orderList = null;
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Order> cq = cb.createQuery(Order.class);
            Root<Order> root = cq.from(Order.class);
            CriteriaQuery<Order> all = cq.select(root).where(cb.equal(root.get("routeId"), 0));

            TypedQuery<Order> allOrders = session.createQuery(all);

            orderList = allOrders.getResultList();

            transaction.commit();

        } catch (Exception e){
            if (transaction!= null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return orderList;
    }
}
