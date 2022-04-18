package logics;
import models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;
public class WarehouseLogic {
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
        System.out.println("Введите количество деталей на складе");
        int numbOfDetail = scanner.nextInt();
        System.out.println("Введите количество техники на складе");
        int numbOfEquipment = scanner.nextInt();

        Warehouse warehouse = new Warehouse(numbOfDetail, numbOfEquipment);
        session.save(warehouse);
    }

    private void read(Session session) {
        List<Warehouse> warehouse = session.createQuery("SELECT a from Warehouse a", Warehouse.class).getResultList();
        System.out.println(warehouse);
    }

    private void update(Session session) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите id склада");
        int id = scanner.nextInt();

        System.out.println("Введите количество деталей на складе");
        int numbOfDetail = scanner.nextInt();
        System.out.println("Введите количество техники на складе");
        int numbOfEquipment = scanner.nextInt();

        Warehouse warehouse = session.get(Warehouse.class, id);
        warehouse.setNumbOfDetails(numbOfDetail);
        warehouse.setNumbOfEquipment(numbOfEquipment);
        session.save(warehouse);
    }

    private void delete(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите id склада");
        int id = scanner.nextInt();

        Warehouse warehouse = session.get(Warehouse.class, id);
        session.delete(warehouse);
    }

    private void filterRead(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите 1 для фильтрации по количеству техники на складе");
        System.out.println("Введите 2 для фильтрации по количеству деталей на складе");
        int numb = scanner.nextInt();
        List<Warehouse> warehouse = null;
        switch(numb) {
            case 1:
                System.out.println("Введите количество техники");
                int numbOfEquipment = scanner.nextInt();
                warehouse = session.createQuery("SELECT a from Warehouse a where numbOfEquipment = \'" + numbOfEquipment + "\'", Warehouse.class).getResultList();
                break;
            case 2:
                System.out.println("Введите количество деталей");
                int numbOfDetail = scanner.nextInt();
                warehouse = session.createQuery("SELECT a from Warehouse a where numbOfDetail = \'" + numbOfDetail + "\'", Warehouse.class).getResultList();
                break;

        }
        System.out.println(warehouse);
    }
}
