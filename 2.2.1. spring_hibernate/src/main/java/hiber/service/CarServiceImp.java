package hiber.service;


import hiber.dao.CarDao;
import hiber.model.Car;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarServiceImp implements CarService{
    private CarDao carDao;

    public CarServiceImp() {
    }

    @Autowired
    public CarServiceImp(CarDao carDao) {
        this.carDao = carDao;
    }

    @Transactional
    @Override
    public void add(Car car) {
        carDao.add(car);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Car> listCar() {
        return carDao.listCar();
    }

    @Transactional(readOnly = true)
    @Override
    public User getCarUser(String model, int series) {
        return carDao.getCarUser(model, series);
    }
}
