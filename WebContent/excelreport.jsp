<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.List" %>
<%@ page import="com.test.model.User" %>
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
      <title>Sample Hello World Example</title>
   </head>
   <body>
      <h1>Export to Excel Example</h1>
      <table cellpadding="1"  cellspacing="1" border="1" bordercolor="gray">
         <tr>
            <td bgcolor="#FF0000">Name</td>
            <td bgcolor="#FF0000">E-Mail ID</td>
            <td bgcolor="#FF0000">Country </td>
         </tr>
         <%
            List<User> users  = (List<User>)request.getAttribute("listUser");
                  if (users != null) {
                      response.setContentType("application/vnd.ms-excel");
                      response.setHeader("Content-Disposition", "inline; filename="+ "user_report.xls");
                  }
            for(User e: users){
            %>
         <tr>
            <td><%=e.getName()%></td>
            <td><%=e.getEmail()%></td>
            <td><%=e.getCountry()%></td>
         </tr>
         <% 
            }
            %>
      </table>
   </body>
</html>