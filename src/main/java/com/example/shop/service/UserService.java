package com.example.shop.service;

import com.example.shop.domain.dao.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    void save(User user);

    void updateUser(User user, long id);

    void deleteUser(long id);

    Page<User> getPage(Pageable pageable);

    User getUserById(long id);
}
