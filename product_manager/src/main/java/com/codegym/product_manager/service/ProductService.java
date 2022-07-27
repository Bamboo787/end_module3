package com.codegym.product_manager.service;

import com.codegym.product_manager.dto.ProductDTO;
import com.codegym.product_manager.model.Product;
import com.codegym.product_manager.utils.DBConText;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductService {

    //Kiểu hiện thị truyền thống nhưng không dùng nữa, ta đã dùng DTO để ánh xạ các thành Phố kiểu(INT) về kiểu(String) để hiển thị
    private static final String SELECT_ALL_PRODUCT = "SELECT * FROM product;";

    @Override
    public List<Product> findAll() {
        List<Product> list = new ArrayList<>();
        try {
            Connection conn = DBConText.getConnection();
            PreparedStatement ps = conn.prepareCall(SELECT_ALL_PRODUCT);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getBigDecimal(3),
                        rs.getInt(4),
                        rs.getDate(5),
                        rs.getDate(6),
                        rs.getInt(7),
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getString(10)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static final String SELECT_ALL_PRODUCTS =
            "SELECT p.id, p.title ,p.price, p.quantity, p.updateAT, p.createAT, c.name, p.size, p.color, p.img " +
                    "FROM product AS p " +
                    "JOIN category AS c " +
                    "ON p.idCategory = c.id;";

    @Override
    public List<ProductDTO> findAllProductDTO() {
        List<ProductDTO> list = new ArrayList<>();
        try {
            Connection conn = DBConText.getConnection();
            PreparedStatement ps = conn.prepareCall(SELECT_ALL_PRODUCTS);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new ProductDTO(rs.getInt(1),
                        rs.getString(2),
                        rs.getBigDecimal(3),
                        rs.getInt(4),
                        rs.getDate(5),
                        rs.getDate(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getString(10)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    /*Xóa user theo id*/
    private static final String DELETE_FROM_PRODUCT = "DELETE FROM product WHERE id = ?;";

    @Override
    public void deleteId(String id) {
        try {
            Connection conn = DBConText.getConnection();
            PreparedStatement ps = conn.prepareCall(DELETE_FROM_PRODUCT);

            ps.setString(1, id);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static final String INSERT_PRODUCT = "" +
            "INSERT INTO `product` (`title`, `price`, `quantity`, `createAT`, `idCategory`, `size`, `color`,`img`) " +
            "VALUES (?, ?, ?, now(), ?, ?, ?, ?);";

    @Override
    public boolean create(Product product) {
        boolean success = false;
        try {
            Connection conn = DBConText.getConnection();
            CallableStatement ps = conn.prepareCall(INSERT_PRODUCT);

            ps.setString(1, product.getTitle());
            ps.setBigDecimal(2, product.getPrice());
            ps.setInt(3, product.getQuantity());
            ps.setInt(4, product.getIdCategory());
            ps.setInt(5, product.getSize());
            ps.setString(6, product.getColor());
            ps.setString(7, product.getImg());

            ps.execute();
            success = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    private static String FIND_PRODUCT_BYID =
            " SELECT p.id ,p.title, p.price, p.quantity , p.idCategory , p.size, p.color, p.img " +
                    " FROM product AS p " +
                    " WHERE p.id = ?;";

    @Override
    public Product findById(int id) {

        try {
            Connection conn = DBConText.getConnection();
            PreparedStatement ps = conn.prepareCall(FIND_PRODUCT_BYID);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getBigDecimal(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getString(8));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static final String UPDATE_BYID_PRODUCT =
            "UPDATE product SET title =? , price = ? , quantity = ? , updateAT = now(), idCategory = ? , size = ?, color = ? , img = ? " +
                    "WHERE id = ?;";

    @Override
    public boolean update(Product product) {
        boolean success = false;
        try {
            Connection conn = DBConText.getConnection();
            PreparedStatement ps = conn.prepareCall(UPDATE_BYID_PRODUCT);

            ps.setString(1, product.getTitle());
            ps.setBigDecimal(2, product.getPrice());
            ps.setInt(3, product.getQuantity());
            ps.setInt(4, product.getIdCategory());
            ps.setInt(5, product.getSize());
            ps.setString(6, product.getColor());
            ps.setString(7, product.getImg());
            ps.setInt(8, product.getId());
            ps.execute();
            success = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    private static final String SEARCHU_PRODUCT =
            "SELECT p.id , p.title,p.price,p.quantity, c.name , p.updateAT, p.createAT , p.size, p.color,p.img " +
                    "FROM product AS p " +
                    "JOIN category AS c ON p.idCategory = c.id " +
                    "WHERE p.title LIKE ? " +
                    "OR p.color LIKE ? " +
                    "OR p.price LIKE ? " +
                    "OR p.quantity LIKE ? " +
                    "OR c.name LIKE ? " +
                    "OR p.updateAT LIKE ? " +
                    "OR p.createAT LIKE ? " +
                    "OR p.size LIKE ?;";

    @Override
    public List<ProductDTO> searchProduct(String keyword) {
        List<ProductDTO> list = new ArrayList<>();
        try {
            Connection conn = DBConText.getConnection();
            PreparedStatement ps = conn.prepareCall(SEARCHU_PRODUCT);

            ps.setString(1, '%' + keyword + '%');
            ps.setString(2, '%' + keyword + '%');
            ps.setString(3, '%' + keyword + '%');
            ps.setString(4, '%' + keyword + '%');
            ps.setString(5, '%' + keyword + '%');
            ps.setString(6, '%' + keyword + '%');
            ps.setString(7, '%' + keyword + '%');
            ps.setString(8, '%' + keyword + '%');

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                BigDecimal price = rs.getBigDecimal("price");
                int quantity = rs.getInt("quantity");
                Date updateAT = rs.getDate("updateAT");
                Date createAT = rs.getDate("createAT");
                String name = rs.getString("name");
                int size = rs.getInt("size");
                String color = rs.getString("color");
                String img = rs.getString("img");

                list.add(new ProductDTO(id, title, price, quantity, updateAT, createAT, name, size, color, img));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
