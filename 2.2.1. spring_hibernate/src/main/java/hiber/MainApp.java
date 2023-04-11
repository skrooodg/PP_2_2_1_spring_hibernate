package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);


      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      carService.add(new Car("car1", 1, users.get(1)));
      carService.add(new Car("car2", 2, users.get(0)));
      carService.add(new Car("car3", 3, users.get(2)));
      carService.add(new Car("car4", 4, users.get(3)));

      List<Car> cars = carService.listCars();
      for (Car car : cars) {
         System.out.println("Id = "+car.getId());
         System.out.println("Car model = "+car.getModel());
         System.out.println("Car series = "+car.getSeries());
         System.out.println();
      }

      System.out.println("Человек, которого мы искали: " + userService.findUser("car1", 1));

      context.close();
   }
}
