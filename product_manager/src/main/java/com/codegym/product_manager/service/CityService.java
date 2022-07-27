package com.codegym.product_manager.service;

import com.codegym.product_manager.model.City;
import com.codegym.product_manager.utils.DBConText;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityService implements  ICityService{
    private static final String SELECT_ALL_CITY=
            "SELECT * FROM cities;";

    @Override
    public List<City> findAll() {
        List<City> list = new ArrayList<>();
        try {
            Connection conn = DBConText.getConnection();
            PreparedStatement ps = conn.prepareCall(SELECT_ALL_CITY);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");

                list.add(new City(id,name));

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        CityService service = new CityService();
        List<City> list = service.findAll();
        for(City o : list){
            System.out.println(o);
        }
    }
}

