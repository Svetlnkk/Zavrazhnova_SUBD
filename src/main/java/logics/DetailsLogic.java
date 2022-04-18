package logics;
import models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;
public class DetailsLogic {
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
        System.out.println("Введите тип детали");
        String detailType = scanner.next();
        System.out.println("Введите количество деталей");
        int numbOfDetail = scanner.nextInt();
        System.out.println("Введите стоимость детали");
        int detailPrice = scanner.nextInt();
        System.out.println("Введите номер склада");
        int idWarehouse = scanner.nextInt();

        Details detail = new Details(detailType, numbOfDetail, detailPrice, session.get(Warehouse.class,idWarehouse));
        session.save(detail);
    }

    private void read(Session session) {
        List<Details> detail = session.createQuery("SELECT a from Details a", Details.class).getResultList();
        System.out.println(detail);
    }

    private void update(Session session) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите id детали");
        int id = scanner.nextInt();

        System.out.println("Введите тип детали");
        String detailType = scanner.next();
        System.out.println("Введите количество деталей");
        int numbOfDetail = scanner.nextInt();
        System.out.println("Введите стоимость детали");
        int detailPrice = scanner.nextInt();
        System.out.println("Введите номер склада");
        int idWarehouse = scanner.nextInt();

        Details detail = session.get(Details.class, id);
        detail.setDetailType(detailType);
        detail.setNumbOfDetail(numbOfDetail);
        detail.setDetailPrice(detailPrice);
        detail.setWarehouse(session.get(Warehouse.class, idWarehouse));
        session.save(detail);
    }

    private void delete(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите id детали");
        int id = scanner.nextInt();

        Details detail = session.get(Details.class, id);
        session.delete(detail);
    }

    private void filterRead(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите 1 для фильтрации по типу детали");
        System.out.println("Введите 2 для фильтрации по количеству деталей");
        System.out.println("Введите 3 для фильтрации по цене деталей");
        System.out.println("Введите 4 для фильтрации по номеру склада");
        int numb = scanner.nextInt();
        List<Details> detail = null;
        switch(numb) {
            case 1:
                System.out.println("Введите тип детали");
                String detailType = scanner.next();
                detail = session.createQuery("SELECT a from Details a where detailType = \'" + detailType + "\'", Details.class).getResultList();
                break;
            case 2:
                System.out.println("Введите количество деталей");
                int numbOfDetail = scanner.nextInt();
                detail = session.createQuery("SELECT a from Details a where numbOfDetail = \'" + numbOfDetail + "\'", Details.class).getResultList();
                break;
            case 3:
                System.out.println("Введите цену");
                int detailPrice = scanner.nextInt();
                detail = session.createQuery("SELECT a from Details a where detailPrice =\'" + detailPrice + "\'", Details.class).getResultList();
                break;
            case 4:
                System.out.println("Введите номер склада");
                int idWarehouse = scanner.nextInt();
                detail = session.createQuery("SELECT a from Details a where idWarehouse = " + idWarehouse , Details.class).getResultList();
                break;
        }
        System.out.println(detail);
    }
}
