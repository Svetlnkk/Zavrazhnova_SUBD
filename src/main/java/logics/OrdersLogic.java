package logics;
import models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;
public class OrdersLogic {
    public void work(SessionFactory sessionFactory) {
        System.out.println("Введите 1 для создания заказа");
        System.out.println("Введите 2 для чтения заказа");
        System.out.println("Введите 3 для изменения заказа");
        System.out.println("Введите 4 для удаления заказа");
        System.out.println("Введите 5 для фильтрации");

        Scanner scanner = new Scanner(System.in);
        int numb = scanner.nextInt();
        Session session = null;
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        switch (numb) {
            case 1:
                create(session);
                break;
            case 2:
                read(session);
                break;
            case 3:
                update(session);
                break;
            case 4:
                delete(session);
                break;
            case 5:
                filterRead(session);
                break;
        }
        session.getTransaction().commit();
    }
    private void create(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите номер услуги");
        int idService = scanner.nextInt();
        System.out.println("Введите номер мастера");
        int idMaster = scanner.nextInt();
        System.out.println("Введите номер покупателя");
        int idCustomer = scanner.nextInt();
        System.out.println("Введите дату заказа");
        String orderDate = scanner.next();
        java.util.Date myDate = new java.util.Date(orderDate);
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
        System.out.println("Введите стоимость заказа");
        int price = scanner.nextInt();
        System.out.println("Введите номер детали");
        int idDetail = scanner.nextInt();

        Orders order = new Orders(session.get(Service.class, idService), session.get(Master.class,idMaster), session.get(Customer.class,idCustomer), sqlDate, price, session.get(Details.class,idDetail));
        session.save(order);
    }

    private void read(Session session) {
        List<Orders> order = session.createQuery("SELECT a from Orders a", Orders.class).getResultList();
        System.out.println(order);
    }

    private void update(Session session) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите id заказа");
        int id = scanner.nextInt();

        System.out.println("Введите номер услуги");
        int idService = scanner.nextInt();
        System.out.println("Введите номер мастера");
        int idMaster = scanner.nextInt();
        System.out.println("Введите номер покупателя");
        int idCustomer = scanner.nextInt();
        System.out.println("Введите дату заказа");
        String orderDate = scanner.next();
        java.util.Date myDate = new java.util.Date(orderDate);
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
        System.out.println("Введите стоимость заказа");
        int price = scanner.nextInt();
        System.out.println("Введите номер детали");
        int idDetail = scanner.nextInt();

        Orders order = session.get(Orders.class, id);
        order.setService(session.get(Service.class, idService));
        order.setMaster(session.get(Master.class, idMaster));
        order.setCustomer(session.get(Customer.class, idCustomer));
        order.setOrderDate(sqlDate);
        order.setPrice(price);
        order.setDetail(session.get(Details.class, idDetail));
        session.save(order);
    }

    private void delete(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите id заказа");
        int id = scanner.nextInt();

        Orders order = session.get(Orders.class, id);
        session.delete(order);
    }

    private void filterRead(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите 1 для фильтрации по номеру услуги");
        System.out.println("Введите 2 для фильтрации по номеру мастера");
        System.out.println("Введите 3 для фильтрации по номеру заказчика");
        System.out.println("Введите 4 для фильтрации по дате заказа");
        System.out.println("Введите 5 для фильтрации по стоимости");
        System.out.println("Введите 6 для фильтрации по номеру детали");
        int numb = scanner.nextInt();
        List<Orders> order = null;
        switch(numb) {
            case 1:
                System.out.println("Введите номер услуги");
                int idService = scanner.nextInt();
                order = session.createQuery("SELECT a from Orders a where idService = " + idService, Orders.class).getResultList();
                break;
            case 2:
                System.out.println("Введите номер мастера");
                int idMaster = scanner.nextInt();
                order = session.createQuery("SELECT a from Orders a where idMaster = " + idMaster, Orders.class).getResultList();
                break;
            case 3:
                System.out.println("Введите номер заказчика");
                int idCustomer = scanner.nextInt();
                order = session.createQuery("SELECT a from Orders a where idCustomer = " + idCustomer, Orders.class).getResultList();
                break;
            case 4:
                System.out.println("Введите дату заказа");
                String date = scanner.next();
                java.util.Date myDate = new java.util.Date(date);
                java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
                order = session.createQuery("SELECT a from Orders a where orderDate = \'" + sqlDate + "\'", Orders.class).getResultList();

                break;
            case 5:
                System.out.println("Введите стоимость");
                int price = scanner.nextInt();
                order = session.createQuery("SELECT a from Orders a where price = \'" + price + "\'", Orders.class).getResultList();
                break;
            case 6:
                System.out.println("Введите номер детали");
                int idDetail = scanner.nextInt();
                order = session.createQuery("SELECT a from Orders a where idDetail = " + idDetail, Orders.class).getResultList();
                break;
        }
        System.out.println(order);
    }
}
