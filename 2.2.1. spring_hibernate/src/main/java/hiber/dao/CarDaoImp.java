package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CarDaoImp implements CarDao{
    private SessionFactory sessionFactory;

    public CarDaoImp() {}

    @Autowired
    public CarDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    public List<Car> listCar() {
        String getListCar = "select car from Car car";
        TypedQuery<Car> query=sessionFactory.getCurrentSession().createQuery(getListCar, Car.class);
        return query.getResultList();
    }

    @Override
    public User getCarUser(String model, int series) {
        String getCarUser = "select car from Car car where car.model = :model AND car.series = :series";

        TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery(getCarUser, Car.class);
        query.setParameter("model", model).setParameter("series", series);
        try {
            Car car = query.setMaxResults(1).getSingleResult();
            return car.getCarUser();
        } catch (NoResultException e) {
            System.out.println("Нет User'а с таким Car'ом" );
        }
        return null;
    }
}
