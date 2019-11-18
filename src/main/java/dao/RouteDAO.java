package dao;

import entities.Order;
import entities.Route;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class RouteDAO {
    public void saveOrUpdateRoute(Route route){
        Transaction transaction = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            session.saveOrUpdate(route);

            transaction.commit();


        } catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Route> getAllRoutes(){
        List<Route> routes = null;
        Transaction transaction = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Route> cq = cb.createQuery(Route.class);
            Root<Route> root = cq.from(Route.class);
            CriteriaQuery<Route> all = cq.select(root);

            TypedQuery<Route> allRoutes = session.createQuery(all);

            routes = allRoutes.getResultList();

            transaction.commit();

        } catch (Exception e){
            if (transaction!= null){
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return routes;
    }

    public void deleteRoute(Route route){
        Transaction transaction = null;


        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            Route routeFromDb = session.get(Route.class, route.getId());
            Hibernate.initialize(routeFromDb.getOrderList());
            for (Order order : routeFromDb.getOrderList()){
                order.setRouteId(0);
            }
            session.delete(routeFromDb);

            transaction.commit();

        } catch (Exception e){
            if (transaction!= null){
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }


    public Route getRouteWithOrders(int id){
        Route route = null;
        Transaction transaction = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            route = session.get(Route.class, id);

            Hibernate.initialize(route.getOrderList());

            transaction.commit();

        } catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return route;
    }
}
