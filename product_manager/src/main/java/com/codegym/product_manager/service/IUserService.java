package com.codegym.product_manager.service;

import com.codegym.product_manager.dto.UserDTO;
import com.codegym.product_manager.model.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserService extends IGeneralService<User> {

    List<UserDTO> findAllUserDTO();

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    boolean existsByUserName(String username);

    public boolean updateUser(User user) throws SQLException;

    List<UserDTO> searchUser(String keyword);
}

