
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import logics.*;
import models.*;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Details.class)
                .addAnnotatedClass(Master.class)
                .addAnnotatedClass(Orders.class)
                .addAnnotatedClass(Service.class)
                .addAnnotatedClass(Warehouse.class)
                .buildSessionFactory();

        boolean isWork = true;
        while(isWork){
            System.out.println("Введите 1 для работы с заказчиками");
            System.out.println("Введите 2 для работы с деталями");
            System.out.println("Введите 3 для работы с мастерами");
            System.out.println("Введите 4 для работы с заказами");
            System.out.println("Введите 5 для работы с услугой");
            System.out.println("Введите 6 для работы со складами");
            System.out.println("Введите 7 для выхода");
            System.out.println("Введите 8 для вывода мастеров и их заказов");

            Scanner scanner = new Scanner(System.in);
            int numb = scanner.nextInt();

            switch (numb){
                case 1:
                    CustomerLogic customerLogic = new CustomerLogic();
                    customerLogic.work(sessionFactory);
                    break;
                case 2:
                    DetailsLogic detailsLogic = new DetailsLogic();
                    detailsLogic.work(sessionFactory);
                    break;
                case 3:
                    MasterLogic masterLogic = new MasterLogic();
                    masterLogic.work(sessionFactory);
                    break;
                case 4:
                    OrdersLogic ordersLogic = new OrdersLogic();
                    ordersLogic.work(sessionFactory);
                    break;
                case 5:
                    ServiceLogic serviceLogic = new ServiceLogic();
                    serviceLogic.work(sessionFactory);
                    break;
                case 6:
                    WarehouseLogic warehouseLogic = new WarehouseLogic();
                    warehouseLogic.work(sessionFactory);
                    break;
                case 7:
                    isWork = false;
                    break;
                case 8:
                    MasterOrderLogic masterOrderLogic=new MasterOrderLogic();
                    masterOrderLogic.work(sessionFactory);
                    break;
            }
        }
        sessionFactory.close();
    }
}
