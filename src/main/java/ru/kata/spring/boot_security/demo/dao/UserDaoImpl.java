package ru.kata.spring.boot_security.demo.dao;



import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<User> getAll() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User update(User user) {
        return entityManager.merge(user);
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public void delete(User user) {
        User managedUser = entityManager.find(User.class, user.getId());
        if (managedUser != null) {
            entityManager.remove(managedUser);
        } else {
            System.out.println("Сущность не найдена");
        }
    }

    @Override
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

}
