package com.joydeep.springmvc.service;

import java.util.List;

import com.joydeep.springmvc.model.UserDetails;

public interface UserService {
   void save(UserDetails user);

   List<UserDetails> list();
}
