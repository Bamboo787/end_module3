<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Dashboard | Uplon - Responsive Bootstrap 4 Admin Dashboard</title>
  <%--1--%>
  <%@include file="/cpa/layout/head_1.jsp"%>
</head>

<body>


<div id="wrapper">

  <%--2--%>
  <%@include file="/cpa/layout/navbar_custom_top_2.jsp"%>




  <div class="left-side-menu">
    <%--3--%>
    <%@include file="/cpa/layout/hearder_left_3.jsp"%>

  </div>

  <div class="content-page">
    <%--4--%>
    <%@include file="/cpa/layout/content_page_4.jsp"%>
  </div>

    <!-- Footer Start -->
    <footer class="footer">
      <%--5--%>
      <%@include file="/cpa/layout/footer_5.jsp"%>
    </footer>

    <%@include file="/cpa/layout/script/scipt-6.jsp"%>

</body>

</html>