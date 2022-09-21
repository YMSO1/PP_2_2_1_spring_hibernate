package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      user1.setUsersCar(new Car("Lexus", 300));
      userService.add(user1);
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      user2.setUsersCar(new Car("BMW", 2055));
      userService.add(user2);
      User user3 = new User("User3", "Lastname4", "user3@mail.ru");
      user3.setUsersCar(new Car("Toyota", 4998));
      userService.add(user3);
      User user4 = new User("User4", "Lastname4", "user4@mail.ru");
      user4.setUsersCar(new Car("Nissan", 20));
      userService.add(user4);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println(user);
      }

      try {
         System.out.println(carService.getCarUser("Toyota", 4998));
      } catch (NoResultException e) {
         System.out.println("Нет User с таким Car");
      }
      try {
         System.out.println(userService.getCarUser("Toyota", 4998));
      } catch (NoResultException e) {
         System.out.println("Нет User с таким Car");
      }

      context.close();
   }
}
