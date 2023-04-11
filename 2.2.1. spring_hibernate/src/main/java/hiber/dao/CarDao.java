package hiber.dao;

import hiber.model.Car;

import java.util.List;

public interface CarDao {
    void carAdd(Car car);
    List<Car> listOfCars();
}
