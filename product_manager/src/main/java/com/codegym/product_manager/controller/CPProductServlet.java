package com.codegym.product_manager.controller;

import com.codegym.product_manager.dto.ProductDTO;
import com.codegym.product_manager.model.Catogory;
import com.codegym.product_manager.model.Product;
import com.codegym.product_manager.service.CatogoryService;
import com.codegym.product_manager.service.ICatogoryService;
import com.codegym.product_manager.service.IProductService;
import com.codegym.product_manager.service.ProductService;
import com.codegym.product_manager.utils.Validate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CPProductServlet", urlPatterns = "/cpa/product")
public class CPProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    IProductService productService;
    ICatogoryService catogoryService;

    @Override
    public void init() throws ServletException {
        productService = new ProductService();
        catogoryService = new CatogoryService();
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
                showListProduct(request, response);
                break;
            case "delete":
                showDeleteId(request, response);
                break;
            case "create":
                showCreateProduct(request, response);
                break;
            case "edit":
                showEditProduct(request, response);
                break;
            default:
                showListProduct(request, response);
                break;

        }
    }

    private void showEditProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/cpa/product/Editproduct.jsp");
        String id = request.getParameter("id");

        Product product = productService.findById(Integer.parseInt(id));

        List<Catogory> list2 = catogoryService.findAll();
        request.setAttribute("listC", list2);

        if (product != null) {
            request.setAttribute("product", product);
        }
        dispatcher.forward(request, response);
    }

    private void showListProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/cpa/product/Listproduct.jsp");

        String strKeyword = request.getParameter("keyword");

        if (strKeyword != null) {
            List<ProductDTO> list1 = productService.searchProduct(strKeyword);
            request.setAttribute("listP", list1);
            dispatcher.forward(request, response);
        } else {
            List<ProductDTO> list = productService.findAllProductDTO();
            request.setAttribute("listP", list);

            dispatcher.forward(request, response);
        }
    }

    private void showDeleteId(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("sid");
        productService.deleteId(id);

        response.sendRedirect("/cpa/product");
    }

    private void showCreateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/cpa/product/CreateProduct.jsp");

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
                doCreateProduct(request, response);
                break;
            case "edit":
                doUpdateProduct(request, response);
                break;
            default:
                doCreateProduct(request, response);
                break;
        }
    }

    private void doUpdateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/cpa/product/Editproduct.jsp");

        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String strPrice = request.getParameter("price");
        String strQuantity = request.getParameter("quantity");
        String strIdCategory = request.getParameter("idCategory");
        String strSize = request.getParameter("size");
        String color = request.getParameter("color");
        String img = request.getParameter("file");

        List<String> errors = new ArrayList<>();

        if (title.equals("")) {
            errors.add("Vui lòng không được để trống");
        }
        if (!title.equals("ĐỒNG HỒ QUARTZ") && !title.equals("ĐỒNG HỒ AUTOMATIC") && !title.equals("ĐỒNG HỒ SOLAR")) {
            errors.add("Sản phẩm không tồn tại, quí khách vui lòng chọn lại!");
        }

        if (strPrice.equals("")) {
            errors.add("Vui lòng không được để trống");
        }
        if (strIdCategory.equals("")) {
            errors.add("Vui lòng không được để trống");
        }

        if (strSize.equals("")) {
            errors.add("Vui lòng không được để trống");
        }
        if (color.equals("")) {
            errors.add("Vui lòng không được để trống");
        }
        if (!color.equals("RED") && !color.equals("BLUE") && !color.equals("YELLOW") && !color.equals("ORANGE") && !color.equals("BLACK") && !color.equals("WHITE")) {
            errors.add("Nhập sai màu trong danh sách bạn vui lòng chọn lại màu trong danh sách");
        }
        if (img.equals("")) {
            errors.add("Vui lòng không được để trống");
        }

        boolean isPrice = Validate.isNumberValid(strPrice);
        if (!isPrice || strPrice.length() > 10) {
            errors.add("Lỗi nhập sai kiểu dữ liệu HOẶC nhập giá tiền là số âm HOẶC giá tiền lớn hơn 10 số");
        }

        boolean isQuantity = Validate.isNumberValid(strQuantity);
        if (!isQuantity || strQuantity.length() < 0 || strQuantity.length() > 3) {
            errors.add("Nhập sai kiểu dữ liệu Quantity HOẶC Nhập sai số lượng Quantity là số âm HOẶC số lượng lớn hơn 999");
        }

        Product product = null;

        boolean success = false;

        if (errors.size() == 0) {
            product = new Product(Integer.parseInt(id), title, BigDecimal.valueOf(Long.parseLong(strPrice)), Integer.parseInt(strQuantity), Integer.parseInt(strIdCategory), Integer.parseInt(strSize), color, img);
            success = productService.update(product);
        }

        if (success) {
            request.setAttribute("success", true);
        } else {
            errors.add("Thêm mới thất bại");
        }

        if (errors.size() > 0) {
            request.setAttribute("errors", errors);
        }

        request.setAttribute("product", product);

        List<Catogory> list2 = catogoryService.findAll();
        request.setAttribute("listC", list2);

        dispatcher.forward(request, response);
    }


    private void doCreateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/cpa/product/CreateProduct.jsp");

        String title = request.getParameter("title");
        String strPrice = request.getParameter("price");
        String strQuantity = request.getParameter("quantity");
        String strIdCategory = request.getParameter("idCategory");
        String strSize = request.getParameter("size");
        String color = request.getParameter("color");
        String img = request.getParameter("file");

        List<String> errors = new ArrayList<>();

        if (title.equals("")) {
            errors.add("Vui lòng không được để trống");
        }
        if (!title.equals("ĐỒNG HỒ QUARTZ") && !title.equals("ĐỒNG HỒ AUTOMATIC") && !title.equals("ĐỒNG HỒ SOLAR")) {
            errors.add("Sản phẩm không tồn tại, quý khách vui lòng chọn lại!");
        }

        if (strPrice.equals("")) {
            errors.add("Vui lòng không được để trống");
        }
        if (strIdCategory.equals("")) {
            errors.add("Vui lòng không được để trống");
        }
        if (strSize.equals("")) {
            errors.add("Vui lòng không được để trống");
        }
        if (color.equals("")) {
            errors.add("Vui lòng không được để trống");
        }
        if (!color.equals("RED") && !color.equals("BLUE") && !color.equals("YELLOW") && !color.equals("ORANGE") && !color.equals("BLACK") && !color.equals("WHITE")) {
            errors.add("Nhập sai màu trong danh sách bạn vui lòng chọn lại màu trong danh sách");
        }
        if (img.equals("")) {
            errors.add("Vui lòng không được để trống");
        }


        boolean isPrice = Validate.isNumberValid(strPrice);
        if (!isPrice || strPrice.length() > 10) {
            errors.add("Lỗi nhập sai kiểu dữ liệu HOẶC nhập giá tiền là số âm HOẶC giá tiền lớn hơn 10 số");
        }

        boolean isQuantity = Validate.isNumberValid(strQuantity);
        if (!isQuantity || strQuantity.length() < 0 || strQuantity.length() > 3) {
            errors.add("Nhập sai kiểu dữ liệu Quantity HOẶC Nhập sai số lượng Quantity là số âm HOẶC số lượng lớn hơn 999");
        }


        boolean success = false;
        if (errors.size() == 0) {
            Product product = new Product(title, BigDecimal.valueOf(Long.parseLong(strPrice)), Integer.parseInt(strQuantity), Integer.parseInt(strIdCategory), Integer.parseInt(strSize), color, img);
            success = productService.create(product);
        }

        if (success) {
            request.setAttribute("success", true);
        } else {
            errors.add("Lỗi thêm mới");
        }

        if (errors.size() > 0) {
            request.setAttribute("errors", errors);
        }
        dispatcher.forward(request, response);
    }
}
