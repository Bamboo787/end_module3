package com.codegym.product_manager.controller;

import com.codegym.product_manager.dto.UserDTO;
import com.codegym.product_manager.model.City;
import com.codegym.product_manager.model.User;
import com.codegym.product_manager.service.CityService;
import com.codegym.product_manager.service.ICityService;
import com.codegym.product_manager.service.IUserService;
import com.codegym.product_manager.service.UserService;
import com.codegym.product_manager.utils.Validate;
import com.mysql.cj.xdevapi.Schema;

import javax.management.relation.Role;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@WebServlet(name = "CPUserServlet", urlPatterns = "/cpa/user")
public class CPUserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    UserService userService;
    IUserService iUserService;
    ICityService cityService;

    public void init() throws ServletException {
        userService = new UserService();
        cityService = new CityService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html/charset=UTF-8");

        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "list":
                showListPage(request, response);
                break;
            case "delete":
                deleteID(request, response);
                break;
            case "create":
                showCreate(request, response);
                break;
            case "edit":
                showEdit(request, response);
                break;
            default:
                showListPage(request, response);
                break;
        }
    }

    private void showListPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/cpa/user/listuser.jsp");

        String strKeyword = request.getParameter("keyword");

        if (strKeyword != null) {
            List<UserDTO> list1 = userService.searchUser(strKeyword);
            request.setAttribute("listS", list1);
            dispatcher.forward(request, response);

        } else {
            List<UserDTO> list = userService.findAllUserDTO();
            request.setAttribute("listS", list);
            dispatcher.forward(request, response);
        }

    }

    private void deleteID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("sid");
        userService.deleteId(id);

        response.sendRedirect("/cpa/user");
    }

    private void showCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/cpa/user/createuser.jsp");

        dispatcher.forward(request, response);
    }

    private void showEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/cpa/user/Edituser.jsp");

        String id = request.getParameter("id");

        User user = userService.findById(Integer.parseInt(id));

        List<City> list2 = cityService.findAll();
        request.setAttribute("listC", list2);

        if (user != null) {
            request.setAttribute("user", user);
        }

        dispatcher.forward(request, response);

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html/charset=UTF-8");

        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                doCreate(request, response);
                break;
            case "edit":
                try {
                    doUpdate(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                showListPage(request, response);
                break;
        }
    }

    private void doCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/cpa/user/createuser.jsp");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String fullname = request.getParameter("fullname");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String cityId = request.getParameter("cityId");
        String role = request.getParameter("role");
        String img = request.getParameter("file");


        List<String> errors = new ArrayList<>();
        boolean isUsername = Validate.isUsernameValid(username);
        boolean isPassword = Validate.isPasswordValid(password);
        boolean isFullName = Validate.isFullNameValid(fullname);
        boolean isPhone = Validate.isPhoneValid(phone);
        boolean isEmail = Validate.isEmailValid(email);
        boolean isRole = Validate.isRoleValid(role);

        if (username.equals("")) {
            errors.add("Tên Username không được để trống");
        }
        if (password.equals("")) {
            errors.add("Password không được để trống");
        }
        if (fullname.equals("")) {
            errors.add("FullName không được để trống");
        }
        if (phone.equals("")) {
            errors.add("Phone không được để trống");
        }
        if (email.equals("")) {
            errors.add("Email không được để trống");
        }
        if (cityId.equals("")) {
            errors.add("City không được để trống");
        }

        if (img.equals("")) {
            errors.add("Image không được để trống");
        }
        if (!isUsername) {
            errors.add("Nhập Username sai định dạng(Không bao gồm dấu cách ,kí tự đặt biệt, chỉ được viết thường và số, bao gồm từ 3 đến 18 kí tự)");
        }
        if (!isPassword) {
            errors.add("Password sai định dạng(VD: Abc1 tối thiếu 4 kí tự trở lên)");
        }
        if (!isFullName) {
            errors.add("Nhập Fullname sai định dạng(Tên phải viết hoa chữ cái đầu và không chứa kí tự đặt biệt và không dấu)");
        }
        if (!isPhone) {
            errors.add("Phone sai định dạng (Không bao gồm dấu cách,chữ,kí tự đặc biệt,Phone gồm 10 số và bắt đầu là số 0 và +84)");
        }
        if (!isEmail) {
            errors.add("Nhập Email sai định dạng (vd: dat.trinh@gmail.com)");
        }

        if (!isRole) {
            errors.add("Nhập Role sai định dạng (Bắt buộc: ADMIN hoặc USER)");
        }
        boolean success = false;
        boolean exitsEmail = userService.existsByEmail(email);
        boolean exitsPhone = userService.existsByPhone(phone);
        boolean exitsUsename = userService.existsByUserName(username);
        if (exitsEmail) {
            errors.add("Email đã tồn tại bạn vui lòng nhập lại email khác!");
        } else if (exitsPhone) {
            errors.add("Phone đã tồn tại bạn vui lòng nhập phone khác!");
        } else if (exitsUsename) {
            errors.add("Username đã tồn tại bạn vui lòng nhập username khác!");
        } else {
            if (errors.size() == 0) {
                User user = new User(username, password, fullname, phone, email, Integer.parseInt(cityId), role, img);
                success = userService.create(user);
            }
        }
        if (success) {
            request.setAttribute("success", true);
        } else {
            errors.add("Thêm mới thất bại");
        }


        if (errors.size() > 0) {
            request.setAttribute("errors", errors);
        }

        dispatcher.forward(request, response);

    }


    private void doUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String fullname = request.getParameter("fullname");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        int cityId = Integer.parseInt(request.getParameter("cityId"));
        String role = request.getParameter("role");
//        Date updatedAt = request.getParameter("updatedAt");
//        Date createdAt = request.getParameter("createdAt");
        String img = request.getParameter("img");

        User book = new User(id, username, password, email, fullname, phone, cityId, role, img);
        userService.update(book);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/cpa/user/Edituser.jsp");
        dispatcher.forward(request, response);



//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        String fullname = request.getParameter("fullname");
//        String phone = request.getParameter("phone");
//        String email = request.getParameter("email");
//        String cityId = request.getParameter("cityId");
//        String role = request.getParameter("role");
//        String img = request.getParameter("file");
//
//
//        List<String> errors = new ArrayList<>();
//        boolean isUsername = Validate.isUsernameValid(username);
//        boolean isPassword = Validate.isPasswordValid(password);
//        boolean isFullName = Validate.isFullNameValid(fullname);
//        boolean isPhone = Validate.isPhoneValid(phone);
//        boolean isEmail = Validate.isEmailValid(email);
//
//
//        if (username.equals("")) {
//            errors.add("Tên Username không được để trống");
//        }
//        if (password.equals("")) {
//            errors.add("Password không được để trống");
//        }
//        if (fullname.equals("")) {
//            errors.add("Fullname không được để trống");
//        }
//        if (phone.equals("")) {
//            errors.add("Phone không được để trống");
//        }
//        if (email.equals("")) {
//            errors.add("Email không được để trống");
//        }
//
//        if (cityId.equals("")) {
//            errors.add("City không được để trống");
//        }
//
//        if (img.equals("")) {
//            errors.add("Image không được để trống");
//        }
//        if (!isUsername) {
//            errors.add("Nhập Username sai định dạng(Không bao gồm dấu cách ,kí tự đặt biệt, chỉ được viết thường và số, bao gồm từ 3 đến 18 kí tự)");
//        }
//        if (!isPassword) {
//            errors.add("Password sai định dạng(VD: Ab1@ tối thiếu 4 kí tự trở lên)");
//        }
//        if (!isFullName) {
//            errors.add("Nhập Fullname sai định dạng(Tên phải viết hoa chữ cái đầu và không chứa kí tự đặt biệt và không dấu)");
//        }
//        if (!isPhone) {
//            errors.add("Phone sai định dạng(Không bao gồm dấu cách,chữ,kí tự đặc biệt,Phone gồm 10 đến 11 số và bắt đầu là số 0 và +84)");
//        }
//        if (!isEmail) {
//            errors.add("Nhập Email sai định dạng (vd: dat.trinh@gmail.com)");
//        }

//        if (!role.equals("ADMIN") && !role.equals("USER")) {
//            errors.add("Vui lòng chọn ADMIN hoặc USER ");
//        }


//        boolean success = false;
//        String message = "";
//
//
//        if (errors.size() == 0) {
//            if (success) {
//                request.setAttribute("success", true);
//                request.setAttribute("message", message);
//            } else {
//                errors.add("Cập nhật thất bại");
//            }
//        }
//
//        User book = new User(id, username, password, email, fullname, phone, cityId, role, img);
//        userService.update(book);
//
//
//        if (errors.size() > 0) {
//            request.setAttribute("errors", errors);
//            RequestDispatcher dispatcher = request.getRequestDispatcher("/cpa/user/Edituser.jsp");
//            dispatcher.forward(request, response);
//        }


    }
}



