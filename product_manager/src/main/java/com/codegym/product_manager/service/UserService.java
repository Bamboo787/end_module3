package com.codegym.product_manager.service;

import com.codegym.product_manager.dto.UserDTO;
import com.codegym.product_manager.model.User;
import com.codegym.product_manager.utils.DBConText;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.codegym.product_manager.utils.DBConText.getConnection;

public class UserService implements IUserService {
    private static final String SELECT_ALL_USERA = "SELECT * FROM users;";
    private static final String UPDATE_USERS_SQL = "update users set username = ?,password = ?, fullname = ? , phone = ? ,email= ?,cityId = ? ,Role = ?, img = ?, where id = ?";

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareCall(SELECT_ALL_USERA);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getString(8),
                        rs.getDate(9),
                        rs.getDate(10),
                        rs.getString(11)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    /*Dùng DTO để chuyển lại kiểu dữ liệu cityId(Int) sang kiểu cityName(String) để Lấy ra được Name*/
    private static final String SELECT_ALL_USER =
            "SELECT u.id , u.username, u.password, u.fullname, u.phone,u.email, c.name , u.Role, u.updatedAt,u.createdAt, u.img " +
                    " FROM users AS u  " +
                    "JOIN cities AS c " +
                    " ON u.cityId = c.id;";

    @Override
    public List<UserDTO> findAllUserDTO() {
        List<UserDTO> list = new ArrayList<>();
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareCall(SELECT_ALL_USER);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id= rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String fullname = rs.getString("fullname");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String name = rs.getString("name");
                String role = rs.getString("Role");
                Date updatedAt = rs.getDate("updatedAt");
                Date createdAt = rs.getDate("createdAt");
                String img = rs.getString("img");

                list.add(new UserDTO(id,username, password, fullname, phone, email,name, role, updatedAt, createdAt, img));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /*Xóa user theo id*/
    private static final String DELETE_FROM_USER =
            "DELETE FROM users WHERE id = ?;";

    @Override
    public void deleteId(String id) {
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareCall(DELETE_FROM_USER);
            ps.setString(1, id);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*Thêm mới user*/
    private static String INSERT_USER =
            "INSERT INTO users (username, password, fullname, phone, email, cityId, Role, createdAt, img) " +
                    "VALUES (?,?,?,?,?,?,?, now(),?);";

    @Override
    public boolean create(User user) {
        boolean success = false;
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareCall(INSERT_USER);

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFullname());
            ps.setString(4, user.getPhone());
            ps.setString(5, user.getEmail());
            ps.setInt(6, user.getCityId());
            ps.setString(7, user.getRole());
            ps.setString(8, user.getImg());

            ps.execute();

            success = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    /*Bước 1 Tìm theo id để hiển thị các giá trị theo id đó vào phần ô edit */
    private static String FIND_USER_BYID =
            "SELECT u.id, u.username, u.password, u.fullname, u.phone, u.email, u.cityId, u.Role, u.img " +
                    "FROM users AS u " +
                    "WHERE u.id = ?;";

    @Override
    public User findById(int id) {
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareCall(FIND_USER_BYID);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getString(8),
                        rs.getString(9)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*Update lại các trường theo id */
    private static final String UPDATE_BYID_USER =
            "UPDATE users SET username=? , password=?, fullname=? , phone=?, email =?, cityId=?, Role=?, updatedAt = now(), img =?" +
                    " WHERE id = ?;";

    @Override
    public boolean update(User user) {
        boolean success = false;
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareCall(UPDATE_BYID_USER);

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFullname());
            ps.setString(4, user.getPhone());
            ps.setString(5, user.getEmail());
            ps.setInt(6, user.getCityId());
            ps.setString(7, user.getRole());
            ps.setString(8, user.getImg());
            ps.setInt(9, user.getId());

            ps.execute();
            success = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }


    private static final String EXIST_EMAIL_USER =
            "SELECT COUNT(*) AS count " +
                    "FROM users AS u " +
                    "WHERE u.email = ?;";

    @Override
    public boolean existsByEmail(String email) {
        boolean exists = false;
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareCall(EXIST_EMAIL_USER);

            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int count = rs.getInt("count");

                if (count > 0) {
                    exists = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }


    private static final String EXIST_PHONE_USER =
            "SELECT COUNT(*) AS count " +
                    "FROM users AS u " +
                    "WHERE u.phone = ?;";

    @Override
    public boolean existsByPhone(String phone) {
        boolean exists = false;
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareCall(EXIST_PHONE_USER);

            ps.setString(1, phone);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int count = rs.getInt("count");

                if (count > 0) {
                    exists = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

    private static final String EXIST_USERNAME_USER =
            "SELECT COUNT(*) AS count " +
                    "FROM users AS u  " +
                    "WHERE u.username = ?;";

    @Override
    public boolean existsByUserName(String username) {
        boolean exists = false;
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareCall(EXIST_USERNAME_USER);

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int count = rs.getInt("count");

                if (count > 0) {
                    exists = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

    private static final String SEARCHU_USER ="SELECT u.id , u.username, u.password, u.fullname, u.phone,u.email, c.name , u.Role, u.updatedAt,u.createdAt, u.img" +
            " FROM users AS u  " +
            "JOIN cities AS c  ON u.cityId = c.id " +
            "WHERE u.fullname LIKE ? " +
            "OR u.email LIKE ? " +
            "OR c.name LIKE ? " +
            "OR u.phone LIKE ? " +
            "OR u.Role LIKE ? " +
            "OR u.updatedAt LIKE ? " +
            "OR u.createdAt LIKE ?;";
    @Override
    public List<UserDTO> searchUser(String keyword) {
        List<UserDTO> list = new ArrayList<>();
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareCall(SEARCHU_USER);

            ps.setString(1, '%' + keyword + '%');
            ps.setString(2, '%' + keyword + '%');
            ps.setString(3, '%' + keyword + '%');
            ps.setString(4, '%' + keyword + '%');
            ps.setString(5, '%' + keyword + '%');
            ps.setString(6, '%' + keyword + '%');
            ps.setString(7, '%' + keyword + '%');

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                list.add(new UserDTO(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getDate(9),
                        rs.getDate(10),
                        rs.getString(11)
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean updateUser(User user) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFullname());
            statement.setString(4, user.getPhone());
            statement.setString(5, user.getEmail());
            statement.setInt(6, user.getCityId());
            statement.setString(7, user.getRole());
            statement.setString(8, user.getImg());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
}
