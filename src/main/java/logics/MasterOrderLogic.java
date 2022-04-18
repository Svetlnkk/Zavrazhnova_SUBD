package logics;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import models.*;
import java.util.List;
public class MasterOrderLogic {
    public void work(SessionFactory sessionFactory) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        List<Orders> order = session.createQuery("SELECT ex FROM Orders ex", Orders.class).getResultList();
        System.out.println("Iмя\t\t\t\t\tНомер заказа\t\t\t Дата заказа");
        for (int i = 0; i < order.size(); i++) {
            System.out.format("%s \t\t  %s\t\t%s %n", order.get(i).getMaster().getMasterName(), order.get(i).getId(), order.get(i).getOrderDate());
        }
        session.getTransaction().commit();
    }
}
