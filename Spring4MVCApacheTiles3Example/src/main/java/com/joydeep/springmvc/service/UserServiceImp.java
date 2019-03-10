package com.joydeep.springmvc.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joydeep.springmvc.dao.UserDao;
import com.joydeep.springmvc.model.UserDetails;

@Service
public class UserServiceImp implements UserService {

   @Autowired
   private UserDao userDao;

   @Transactional
   public void save(UserDetails user) {
      userDao.save(user);
   }

   @Transactional
   public List<UserDetails> list() {
      return userDao.list();
   }

}
