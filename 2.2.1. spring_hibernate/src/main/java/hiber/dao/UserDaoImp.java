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

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getCarUser(String model, int series) throws NoResultException {
      String getCarUser = "from User user where user.usersCar.model = :model and user.usersCar.series = :series";

      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(getCarUser);
      query.setParameter("model", model).setParameter("series", series);
      User user = query.setMaxResults(1).getSingleResult();
      return user;
   }
}
