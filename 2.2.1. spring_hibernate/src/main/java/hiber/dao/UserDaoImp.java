package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
   private SessionFactory sessionFactory;

   public UserDaoImp() {   }

   @Autowired
   public UserDaoImp(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public List<User> listUsers() {
      String getListUser = "select user from User user";

      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery(getListUser, User.class);
      return query.getResultList();
   }

   @Override
   public User getCarUser(String model, int series) {
      String getCarUser = "select user from User user where user.usersCar.model = :model and user.usersCar.series = :series";

      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(getCarUser, User.class);
      query.setParameter("model", model).setParameter("series", series);
      try {
         return query.setMaxResults(1).getSingleResult();
      } catch (NoResultException e) {
         System.out.println("Нет User'а с таким Car'ом" );
      }
      return null;
   }
}
