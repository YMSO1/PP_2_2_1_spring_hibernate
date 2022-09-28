package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);

      System.out.println();
      System.out.println("Добавляем Юзеров");
      System.out.println("------------------------------------");
      User user1 = new User("User1", "Lastname1", "user1@mail.ru",
              new Car("Lexus", 300));
      userService.add(user1);
      User user2 = new User("User2", "Lastname2", "user2@mail.ru",
              new Car("BMW", 2055));
      userService.add(user2);
      User user3 = new User("User3", "Lastname4", "user3@mail.ru",
              new Car("Toyota", 4998));
      userService.add(user3);
      User user4 = new User("User4", "Lastname4", "user4@mail.ru",
              new Car("Nissan", 20));
      userService.add(user4);

      System.out.println();
      System.out.println("Смотрим Юзеров");
      System.out.println("------------------------------------");
      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println(user);
      }

      System.out.println();
      System.out.println("Смотрим Юзеров по Авто");
      System.out.println("------------------------------------");
      System.out.println(carService.getCarUser("Toyota", 4998));
      System.out.println(userService.getCarUser("BMW", 2055));

      context.close();
   }
}
