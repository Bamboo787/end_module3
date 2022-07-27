<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Dashboard | Uplon - Responsive Bootstrap 4 Admin Dashboard</title>
    <%--1--%>
    <%@include file="/cpa/layout/head_1.jsp" %>
    <style>
        .success{
            margin-top: 36px;
            color: #35895c
        }
        .errors{
            margin-top: 36px;
            color: #c64c5e
        }

        .list{
            margin-top: 18px
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
                    <div class="col-10">
                        <h1>Edit Product</h1>
                    </div>
                    <div class="col-2 list">
                        <a href="/cpa/product">
                            <button type="button" class="btn btn-success">List Product</button> </a>
                    </div>
                </div>

                <form method="post">

                    <fieldset class="row">

                        <div class="col-sm-6 mt-3">
                            <label>Title <span class="text-danger">*</span></label>
                            <select name="title"  class="form-control" required id="title">
                                <option value="ĐỒNG HỒ QUARTZ">ĐỒNG HỒ QUARTZ</option>
                                <option value="ĐỒNG HỒ AUTOMATIC">ĐỒNG HỒ AUTOMATIC</option>
                                <option value="ĐỒNG HỒ SOLAR">ĐỒNG HỒ SOLAR</option>
                            </select>
                        </div>

                        <div class="col-sm-6 mt-3">
                            <label>Size<span class="text-danger">*</span></label>
                            <select name="size"  class="form-control"required id="size">
                                <option value="19">19</option>
                                <option value="22.5">22.5</option>
                                <option value="23">23</option>
                                <option value="25">25</option>
                                <option value="26">26</option>
                                <option value="29">29</option>
                            </select>
                        </div>


                        <div class="col-sm-6 mt-3">
                            <label>Color <span class="text-danger">*</span></label>
                            <select name="color"  class="form-control" required id="color">
                                <option value="RED">RED</option>
                                <option value="BLUE">BLUE</option>
                                <option value="YELLOW">YELLOW</option>
                                <option value="ORANGE">ORANGE</option>
                                <option value="BLACK">BLACK</option>
                                <option value="WHITE">WHITE</option>
                            </select>
                        </div>

                        <div class="col-sm-6 mt-3">
                            <label>Price <span class="text-danger">*</span></label>
                            <input type="number" class="form-control" name="price"  min="0" required value="${product.price}">
                        </div>
                        <div class="col-sm-6 mt-3">
                            <label>Quantity <span class="text-danger">*</span></label>
                            <input type="number" class="form-control" name="quantity"  min="0" required value="${product.quantity}" >
                        </div>

                        <div class="col-sm-6 mt-3">
                            <label for="idCategory">IdCategory <span class="text-danger">*</span></label>

                            <select name="idCategory"  class="form-control"  required id="idCategory">

                                <c:forEach items="${listC}" var="item">
                                    <c:choose>
                                        <c:when test="${product.idCategory == item.id}">
                                            <option value="${item.id}" selected>${item.name}</option>
                                        </c:when>

                                        <c:otherwise>
                                            <option value="${item.id}">${item.name}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>

                            </select>
                        </div>

                        <div class="col-sm-12 mt-3">
                            <label for="resume">Image</label>
                            <input type="file" class="form-control-file" required id="resume" name="file">
                        </div>

                        <div class="col-sm-3 mt-3">
                            <button type="submit" class="btn btn-success waves-effect waves-light">Update Product </button>
                        </div>


                    </fieldset>
                </form>

            </div>
        </div>

        <c:forEach items="${requestScope['errors']}" var="item">
            <ul class="alert alert-danger">
                <li>${item}</li>
            </ul>
        </c:forEach>

        <div class="">
            <c:if test="${requestScope['success'] == true}">
                <ul class="alert alert-success">
                    <li>Cập nhật thành công</li>
                </ul>
            </c:if>
        </div>


    </div>
</div>

<!-- Footer Start -->
<footer class="footer">
    <%@include file="/cpa/layout/footer_5.jsp" %>
</footer>

<%@include file="/cpa/layout/script/scipt-6.jsp" %>

</body>

</html>