package logics;
import models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;
public class CustomerLogic {
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
        System.out.println("Введите имя заказчика");
        String name = scanner.next();
        System.out.println("Введите номер телефона");
        int phoneNumb = scanner.nextInt();

        Customer customer = new Customer(name, phoneNumb);
        session.save(customer);
    }

    private void read(Session session) {
        List<Customer> customer = session.createQuery("SELECT a from Customer a", Customer.class).getResultList();
        System.out.println(customer);
    }

    private void update(Session session) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите id заказчика");
        int id = scanner.nextInt();

        System.out.println("Введите имя заказчика");
        String name = scanner.next();
        System.out.println("Введите номер телефона");
        int phoneNumb = scanner.nextInt();

        Customer customer = session.get(Customer.class, id);
        customer.setCustomerName(name);
        customer.setPhoneNumb(phoneNumb);
        session.save(customer);
    }

    private void delete(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите id заказчика");
        int id = scanner.nextInt();

        Customer customer = session.get(Customer.class, id);
        session.delete(customer);
    }

    private void filterRead(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите 1 для фильтрации по имени");
        System.out.println("Введите 2 для фильтрации по номеру телефона");
        int numb = scanner.nextInt();
        List<Customer> customer = null;
        switch(numb) {
            case 1:
                System.out.println("Введите имя");
                String name = scanner.next();
                customer = session.createQuery("SELECT a from Customer a where customerName = \'" + name + "\'", Customer.class).getResultList();
                break;
            case 2:
                System.out.println("Введите номер телефона");
                int phoneNumb = scanner.nextInt();
                customer = session.createQuery("SELECT a from Customer a where salary = \'" + phoneNumb + "\'", Customer.class).getResultList();
                break;
        }
        System.out.println(customer);
    }
}
