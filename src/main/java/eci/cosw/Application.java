package eci.cosw;

import eci.cosw.data.AppConfiguration;
import eci.cosw.data.model.Customer;
import eci.cosw.data.model.Todo;
import eci.cosw.data.model.User;
import eci.cosw.data.repositories.CustomerRepository;
import eci.cosw.data.repositories.TodoRepository;
import eci.cosw.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {


    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TodoRepository todoRepository;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        todoRepository.deleteAll();

        todoRepository.save(new Todo("Laboratorio COSW, me falta el ultimo punto porque no se como hacerlo, me sale un error de CORS (probablemente sea un problema con el Token)", 4, new Date(2018, 9, 18), "juanramirez@test.com", "Pending"));
        todoRepository.save(new Todo("Triangulo de talentos PMI FGPR", 1, new Date(2018 - 1900, 8, 17), "johangonzales@test.com", "Completed"));
        todoRepository.save(new Todo("Creacion de archivos Laboratorio de videojuegos", 6, new Date(2018 - 1900, 8, 7), "juanramirez@test.com", "Pending"));
        todoRepository.save(new Todo("Sprint 1 COSW", 8, new Date(2018 - 1900, 9, 5), "farrappteam@test.com", "Pending"));
        todoRepository.save(new Todo("Revisar convocatoria Apps.co", 4, new Date(2018 - 1900, 8, 20), "farrappteam@test.com", "Completed"));
        todoRepository.save(new Todo("Software de reservas", 7, new Date(2018 - 1900, 11, 20), "juanramirez@test.com", "Pending"));
        todoRepository.save(new Todo("Gestion de la informacion", 7, new Date(2018 - 1900, 11, 20), "nicolasosorio@test.com", "Pending"));
        todoRepository.save(new Todo("ESTI parcial 2", 8, new Date(2018 - 1900, 8, 27), "juanramirez@test.com", "Completed"));
        todoRepository.save(new Todo("Torneo LOL", 1, new Date(2018 - 1900, 9, 15), "juanramirez@test.com", "Pending"));
        todoRepository.save(new Todo("Organizar mi cuarto", 2, new Date(2018 - 1900, 8, 5), "juanramirez@test.com", "Pending"));
        todoRepository.save(new Todo("Netflix", 1, new Date(2018 - 1900, 8, 13), "juanramirez@test.com", "Completed"));
        todoRepository.save(new Todo("PDAM proyecto", 5, new Date(2018 - 1900, 8, 12), "farrappteam@test.com", "Completed"));
        todoRepository.save(new Todo("Clase de ESTI", 7, new Date(2018 - 1900, 8, 12), "juanramirez@test.com", "Completed"));
        todoRepository.save(new Todo("Clase de FGPR", 3, new Date(2018 - 1900, 8, 16), "johangonzales@test.com", "Pending"));
        todoRepository.save(new Todo("Comprar celular", 6, new Date(2018 - 1900, 4, 13), "juanramirez@test.com", "Pending"));
        todoRepository.save(new Todo("Comprar pc", 7, new Date(2018 - 1900, 11, 24), "juanramirez@test.com", "Pending"));
        todoRepository.save(new Todo("Tarea FGPR", 2, new Date(2018 - 1900, 8, 16), "miguel@test.com", "Pending"));
        todoRepository.save(new Todo("Laboratorio SEGI", 6, new Date(2018 - 1900, 8, 16), "julianguzman@test.com", "Pending"));
        todoRepository.save(new Todo("Seminario de seguridad SEGI", 8, new Date(2018 - 1900, 9, 17), "juanramirez@test.com", "Pending"));
        todoRepository.save(new Todo("Parcial final ESTI", 9, new Date(2018 - 1900, 11, 12), "andresrodriguez@test.com", "Pending"));
        todoRepository.save(new Todo("Machine Learning Coursera", 5, new Date(2018 - 1900, 7, 15), "juanramirez@test.com", "Completed"));
        todoRepository.save(new Todo("Parcial 1 ESTI", 8, new Date(2018 - 1900, 7, 25), "manuelaldana@test.com", "Completed"));
        todoRepository.save(new Todo("Llenar cuenta de cobro", 10, new Date(2018 - 1900, 8, 10), "juanmantilla@test.com", "Pending"));
        todoRepository.save(new Todo("Dormir", 6, new Date(2018 - 1900, 8, 12), "camilo@test.com", "Pending"));
        todoRepository.save(new Todo("Preparar Exposicion COSW", 8, new Date(2018 - 1900, 8, 10), "farrappteam@test.com", "Completed"));


        userRepository.deleteAll();

        userRepository.save(new User("1", "Juan David", "juandavid@mail"));
        userRepository.save(new User("2", "Camilo", "camilo@mail"));
        userRepository.save(new User("3", "Andres", "andres@mail"));
        userRepository.save(new User("4", "Felipe", "felipe@mail"));
        userRepository.save(new User("5", "Philip", "philip@mail"));
        userRepository.save(new User("6", "Clarice", "clarice@mail"));
        userRepository.save(new User("7", "Johan", "johan@mail"));
        userRepository.save(new User("8", "Kevin", "kevin@mail"));
        userRepository.save(new User("9", "Jake", "jake@mail"));
        userRepository.save(new User("10", "Michael", "michael@mail"));


        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfiguration.class);
        MongoOperations mongoOperation = (MongoOperations) applicationContext.getBean("mongoTemplate");



        Query firstQuery = new Query();
        firstQuery.addCriteria(Criteria.where("dueDate").lt(new Date(System.currentTimeMillis())));
        List<Todo> todos1 = mongoOperation.find(firstQuery, Todo.class);
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("Todos that the dueDate has expired:");
        for(Todo todo: todos1){
            System.out.println("Due Date: " + todo.getDueDate() + "           Description: " + todo.getDescription());
        }
        System.out.println("--------------------------------------------------------------------------");




        Query secondQuery = new Query();
        secondQuery.addCriteria(Criteria.where("priority").gt(4));
        List<Todo> todos2 = mongoOperation.find(secondQuery, Todo.class);
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("Todos that are assigned to given user and have priority greater equal to 5:");
        for(Todo todo: todos2){
            System.out.println("Due Date: " + todo.getDueDate() + "           Description: " + todo.getDescription() + "           Responsible: " + todo.getResponsible() + "           Priority: " + todo.getPriority());
        }
        System.out.println("--------------------------------------------------------------------------");


    }

}