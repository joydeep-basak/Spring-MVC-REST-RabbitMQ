package com.joydeep.springmvc.dao;

import java.util.List;

import com.joydeep.springmvc.model.UserDetails;

public interface UserDao {
   void save(UserDetails user);
   List<UserDetails> list();
}
