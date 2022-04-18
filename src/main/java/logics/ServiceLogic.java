package logics;
import models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;
public class ServiceLogic {
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
        System.out.println("Введите тип услуги");
        String typeOfService = scanner.next();
        System.out.println("Введите цену услуги");
        int price = scanner.nextInt();

        Service service = new Service(typeOfService, price);
        session.save(service);
    }

    private void read(Session session) {
        List<Service> service = session.createQuery("SELECT a from Service a", Service.class).getResultList();
        System.out.println(service);
    }

    private void update(Session session) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите id заказчика");
        int id = scanner.nextInt();

        System.out.println("Введите имя тип услуги");
        String typeOfService = scanner.next();
        System.out.println("Введите цену услуги");
        int price = scanner.nextInt();

        Service service = session.get(Service.class, id);
        service.setTypeOfService(typeOfService);
        service.setPrice(price);
        session.save(service);
    }

    private void delete(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите id заказчика");
        int id = scanner.nextInt();

        Service service = session.get(Service.class, id);
        session.delete(service);
    }

    private void filterRead(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите 1 для фильтрации по типу услуги");
        System.out.println("Введите 2 для фильтрации по цене");
        int numb = scanner.nextInt();
        List<Service> service = null;
        switch(numb) {
            case 1:
                System.out.println("Введите тип услуги");
                String typeOfService = scanner.next();
                service = session.createQuery("SELECT a from Service a where typeOfService = \'" + typeOfService + "\'", Service.class).getResultList();
                break;
            case 2:
                System.out.println("Введите цену");
                int price = scanner.nextInt();
                service = session.createQuery("SELECT a from Service a where salary = \'" + price + "\'", Service.class).getResultList();
                break;
        }
        System.out.println(service);
    }
}
