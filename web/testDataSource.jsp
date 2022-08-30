<%@ page import="cn.kgc.impl.NewsDaoImpl" %>
<%@ page import="cn.kgc.dao.NewsDao" %><%--
  Created by IntelliJ IDEA.
  User: chenrong
  Date: 2022-08-28
  Time: 22:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>testDataSource</title>
</head>
<body>
<%
    NewsDao dao = new NewsDaoImpl();
    dao.getData();
%>
</body>
</html>
