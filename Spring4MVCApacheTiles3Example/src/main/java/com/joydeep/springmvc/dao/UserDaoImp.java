package com.joydeep.springmvc.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.joydeep.springmvc.model.UserDetails;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
  // private SessionFactory sessionFactory;

   @Override
   public void save(UserDetails user) {
     // sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public List<UserDetails> list() {
      //@SuppressWarnings("unchecked")
      //TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
      //return query.getResultList();
      return new ArrayList<>();
   }

}
