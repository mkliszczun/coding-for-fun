package entities;

import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OrderIDGenerator {


    public String generate() {
        String prefix = generatePrefix();
        Transaction transaction = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            List results = session.createSQLQuery("SELECT last_num FROM order_id_generator").getResultList();
            int lastNum = (int) results.get(0);
            String generatedId = prefix + lastNum;
            session.createSQLQuery("update order_id_generator set last_num = " + (++lastNum)).executeUpdate();
            transaction.commit();

            return generatedId;


        } catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }


        System.out.println("will return null order id");
        return null;
    }

//    public Serializable generate()
//            throws HibernateException {
//
//        String prefix = generatePrefix();
//        Serializable result = null;
//        Connection connection = null;
////        Statement statement = null;
//        String generatedId;
//
//
//        try (Session session = HibernateUtil.getSessionFactory().openSession()){
//
//            session.doWork(new Work() {
//                @Override
//                public void execute(Connection connection) throws SQLException {
//                    Statement statement = connection.createStatement();
//                    ResultSet rs = statement.executeQuery("SELECT last_num FROM order_id_generator");
//                    int lastNum = rs.getInt(1);
//
//                    if (statement.execute
//                            ("update order_id_generator set last_num = " + (++lastNum))) {
//                        generatedId = prefix + lastNum;
//                        return generatedId;
//                    }                }
//            });
//
//
////            statement = connection.createStatement();
////            ResultSet rs = statement.executeQuery("SELECT last_num FROM order_id_generator");
////            int lastNum = rs.getInt(1);
////
////            if (statement.execute
////                    ("update order_id_generator set last_num = " + (++lastNum))) {
////                String generatedId = prefix + lastNum;
////                return generatedId;
////            }
//            String generatedId = prefix + lastNum;
//            return generatedId;
//
//        } catch (SQLException e){
//            e.printStackTrace();
//        }
//
//
//
//        return null;
//    }

    public static String generatePrefix(){
        StringBuilder id = new StringBuilder();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        id.append(formatter.format(date));
        id.deleteCharAt(2);
        id.delete(4,7);
        return id.toString();
    }
}
