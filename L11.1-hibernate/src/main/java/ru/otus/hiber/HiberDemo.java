package ru.otus.hiber;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ru.otus.hiber.model.Person;
import ru.otus.hiber.model.Phone;

/**
 * @author sergey
 * created on 10.02.19.
 */

public class HiberDemo {
    private static final String URL = "jdbc:h2:mem:testDB;DB_CLOSE_DELAY=-1";
    private final SessionFactory sessionFactory;

    public static void main(String[] args) {
        HiberDemo demo = new HiberDemo();

        demo.entityExample();
 //       demo.severalSessionsExample();
 //       demo.loadMethodExample();
//        demo.getMethodExample();
//        demo.updateExample();
    }

    private HiberDemo() {
        Configuration configuration = new Configuration()
                .configure("hibernate.cfg.xml");

        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        Metadata metadata = new MetadataSources(serviceRegistry)
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Phone.class)
                .getMetadataBuilder()
                .build();

        sessionFactory = metadata.getSessionFactoryBuilder().build();
    }

    private void entityExample() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Person person = new Person();
            person.setName("Ivan");
            person.setNickName("Durak");
            person.setAddress("derv str");

            session.save(person);
            System.out.println(">>>>>>>>>>> created:" + person);

            System.out.println(">>>>>>>>>>> before commit");
            session.getTransaction().commit(); // В этот момент делается insert

          //  session.detach(person); //Вариант с давно существующим объектом

            // А тут select не выполняется, Person берется из кэша L1
            Person selected = session.get(Person.class, person.getId());
            System.out.println(">>>>>>>>>>> selected:" + selected);
        }
    }

    private void severalSessionsExample() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Person person = new Person();
            person.setName("Ivan");
            person.setNickName("Durak");
            person.setAddress("derv str");

            session.save(person);
            System.out.println(">>>>>>>>>>> created:" + person);
            session.getTransaction().commit();
        }

        System.out.println(">>>>>>>>>>> second session");

        //кеш ошчистился, за объектом надо обратиться в базу
        try (Session session = sessionFactory.openSession()) {
            Person selected = session.get(Person.class, 1L);
            System.out.println(">>>>>>>>>>> selected:" + selected);
        }
    }

    private void loadMethodExample() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Person person = new Person();
            person.setName("Ivan");
            person.setNickName("Durak");
            person.setAddress("derv str");

            session.save(person);
            session.getTransaction().commit();
            System.out.println(">>>>>>>>>>> created:" + person);

        //    session.detach(person); //Вариант с давно существующим объектом

            System.out.println(">>>>>>>>>>> load not exist");
            Person selected = session.load(Person.class, -1L); //-1L
            System.out.println(">>>>>>>>>>> selectedNotExisted");
            System.out.println(">>>>>>>>>>> selected:" + selected.getId());
            System.out.println(">>>>>>>>>>> select before usage");
            System.out.println(">>>>>>>>>>> selected:" + selected);
            /*
             * Return the persistent instance of the given entity class with the given identifier,
             * assuming that the instance exists. This method might return a proxied instance that
             * is initialized on-demand, when a non-identifier method is accessed.
             */
        }
    }

    private void getMethodExample() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Person person = new Person();
            person.setName("Ivan");
            person.setNickName("Durak");
            person.setAddress("derv str");

            session.save(person);
            session.getTransaction().commit();
            System.out.println(">>>>>>>>>>> created:" + person);

            System.out.println(">>>>>>>>>>> load not exist");
            Person selected = session.get(Person.class, -1L); //-1L
            System.out.println(">>>>>>>>>>> selected:" + selected);

        }
    }

    private void updateExample() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Person person = new Person();
            person.setName("Ivan");
            person.setNickName("Durak");
            person.setAddress("derv str");

            session.save(person);
            session.getTransaction().commit();
            System.out.println(">>>>>>>>>>> created:" + person);

            session.beginTransaction();
            person.setAddress("newAdders");

            session.update(person);
            session.update(person);
            session.update(person);
            session.getTransaction().commit();
            //update выполнится только один раз

        }
    }


}

