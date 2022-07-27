<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Dashboard | Uplon - Responsive Bootstrap 4 Admin Dashboard</title>
    <%--1--%>
    <%@include file="/cpa/layout/head_1.jsp" %>
    <style>
        table{
            text-align: center;
        }

        table tbody tr td.pd-top{
            padding-top: 30px;
        }
        table tbody tr th.pd-top{
            padding-top: 30px;
        }
        table tbody tr td.pd-top-action{
            padding-top: 20px;
        }
        .table thead{
            background:#150a9391;
            color: white;
        }
        .create{
            margin-top: 10px;
        }
    </style>

</head>

<body>


<div id="wrapper">

    <%--2--%>
    <%@include file="/cpa/layout/navbar_custom_top_2.jsp" %>


    <div class="left-side-menu">
        <%--3--%>
        <%@include file="/cpa/layout/hearder_left_3.jsp" %>
    </div>

    <div class="content-page">
        <%--4--%>
        <div class="content">
            <!-- Start Content-->
            <div class="container-fluid">
                <!-- start page title -->
                <div class="row">
                    <div class="col-12">
                        <div class="page-title-box">
                            <div class="page-title-right">
                                <ol class="breadcrumb m-0">
                                    <li class="breadcrumb-item"><a href="javascript: void(0);">Uplon</a></li>
                                    <li class="breadcrumb-item active">Dashboard</li>
                                </ol>
                            </div>
                            <h4 class="page-title">Welcome Store Clock</h4>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-10 d-none d-sm-block" style="margin-bottom: 20px">
                        <form class="form-inline">
                            <input class="form-control col-3 mr-sm-2" type="search" placeholder="Search" aria-label="Search" name="keyword">
                            <button class="btn btn-primary" type="submit"> <i class="fas fa-search"></i></button>
                        </form>
                    </div>
                </div>

                <div class="row">
                    <div class="col-10">
                        <h2>List of users</h2>
                    </div>
                    <div class="col-2 create">
                        <a href="/cpa/user?action=create">
                            <button type="button" class="btn btn-success"> Create User</button>
                        </a>
                    </div>

                    <table class="table table-hover table-bordered">
                        <thead>
                        <tr>
<%--                            <th>Id</th>--%>
<%--                            <th>username</th>--%>
<%--                            <th>password</th>--%>
                            <th>Avarta</th>
                            <th>Full Name</th>
                            <th>Phone</th>
                            <th>Email</th>
                            <th>City</th>
                            <th>Role</th>
                            <th>Update</th>
                            <th>Create</th>
                            <th colspan="2">Action</th>
                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach items="${listS}" var="item" >
                            <tr>
<%--                                <th>${item.id}</th>--%>
<%--                                <th>${item.username}</th>--%>
<%--                                <th>${item.password}</th>--%>
                                <td class=""><img src="/assetsa/images/users/${item.getImg()}" width="60px" height="60px" > </td>
                                <th class="pd-top">${item.getFullname()}</th>
                                <td class="pd-top">${item.getPhone()}</td>
                                <td class="pd-top">${item.getEmail()}</td>
                                <td class="pd-top">${item.getName()}</td>
                                <th class="pd-top">${item.getRole()}</th>
                                <td class="pd-top">${item.getUpdatedAt()}</td>
                                <td class="pd-top">${item.getCreatedAt()}</td>
                                <td class="pd-top-action">
                                    <a href="user?action=edit&id=${item.getId()}"><button type="button" class="btn btn-outline-success btn-rounded"><i class="fas fa-edit"></i></button></a>
                                </td>
                                <td class="pd-top-action">
                                    <a href="#" onclick="showMess(${item.getId()})"> <button type="button" class="btn btn-outline-danger btn-rounded"><i class="fa-solid fa-trash-can"></i></button></a>
                                </td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
        <script>
            function showMess(id){
                var option = confirm("Bạn có muốn xóa sản phẩm này không rứa?")
                if(option === true){
                    window.location.href ='user?action=delete&sid=' + id;
                }
            }
        </script>


    <!-- Footer Start -->
    <footer class="footer">
        <%--5--%>
        <%@include file="/cpa/layout/footer_5.jsp" %>
    </footer>

    <%@include file="/cpa/layout/script/scipt-6.jsp" %>

</body>

</html>