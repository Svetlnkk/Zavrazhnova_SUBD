package logics;
import models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;
public class MasterLogic {
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
        System.out.println("Введите имя мастера");
        String name = scanner.next();
        System.out.println("Введите зарплату мастера");
        int salary = scanner.nextInt();
        System.out.println("Введите номер склада, на котором работает мастер");
        int idWarehouse = scanner.nextInt();

        Master master = new Master(name, salary, session.get(Warehouse.class,idWarehouse));
        session.save(master);
    }

    private void read(Session session) {
        List<Master> master = session.createQuery("SELECT a from Master a", Master.class).getResultList();
        System.out.println(master);
    }

    private void update(Session session) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите id мастера");
        int id = scanner.nextInt();

        System.out.println("Введите имя мастера");
        String name = scanner.next();
        System.out.println("Введите зарплату мастера");
        int salary = scanner.nextInt();
        System.out.println("Введите номер склада, на котором работает мастер");
        int idWarehouse = scanner.nextInt();

        Master master = session.get(Master.class, id);
        master.setMasterName(name);
        master.setSalary(salary);
        master.setWarehouse(session.get(Warehouse.class, idWarehouse));
        session.save(master);
    }

    private void delete(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите id мастера");
        int id = scanner.nextInt();

        Master master = session.get(Master.class, id);
        session.delete(master);
    }

    private void filterRead(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите 1 для фильтрации по имени");
        System.out.println("Введите 2 для фильтрации по зарплате");
        System.out.println("Введите 3 для фильтрации по номеру склада");
        int numb = scanner.nextInt();
        List<Master> master = null;
        switch(numb) {
            case 1:
                System.out.println("Введите имя");
                String name = scanner.next();
                master = session.createQuery("SELECT a from Master a where name = \'" + name + "\'", Master.class).getResultList();
                break;
            case 2:
                System.out.println("Введите зарплату");
                int salary = scanner.nextInt();
                master = session.createQuery("SELECT a from Master a where salary = \'" + salary + "\'", Master.class).getResultList();
                break;
            case 3:
                System.out.println("Введите номер склада");
                String idWarehouse = scanner.next();
                master = session.createQuery("SELECT a from Master a where idWarehouse = " + idWarehouse, Master.class).getResultList();
                break;
        }
        System.out.println(master);
    }
}
