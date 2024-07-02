<%-- 
    Document   : attent
    Created on : Mar 4, 2024, 8:33:49 AM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            table{
                width: 100%;
            }
            th{
                text-align: center;
                padding: 15px 0;
                font-size: 18px;
                border: white solid 1px;
                background-color: #3498db;
                color: white;
            }
            td{
               border-left: white solid 1px;
               border-right: white solid 1px;
               text-align: center;
            }
            .description input{
                padding: 10px 5px 5px 5px;
                border:none;
                border-bottom: darkblue solid 1px;
                outline:none;
                
            }
            .button_Save{
                padding: 10px; 
                border-radius: 5px; 
                background-color: #6f6fff; 
                color: white; 
                border: none; 
                font-size: 19px;
                transition: 0.3s;
            }
            .button_Save:hover{
                cursor: pointer;
                background-color: blueviolet;
            }
            .home_button{
                display: block;
                margin: 5px 0px;
                padding: 5px 10px;
                color: darkblue;
                font-weight: 700;
                width: fit-content;
                    font-size: 20px;
            }
            .home_button:hover{
                cursor: pointer;
                opacity: 0.8;
            }
        </style>
    </head>
    <body>
        
        
        <form action="atts" method="POST">
            <input type="hidden" name="id" value="${param.id}"/>
            <div style="margin: 0 8%">
                <div style="background-color: #fcf8f8; padding: 2px 5px; margin-bottom: 10px;">
                    <a href="../view/authentication/home.jsp" class="home_button">Home</a>
                </div>
                <table border="1px" style="border-collapse: collapse;">
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Image</th>
                        <th>Presented</th>
                        <th>Taker</th>
                        <th>Description</th>
                        <th>Time</th>
                    </tr>
                    <c:forEach items="${requestScope.atts}" var="a">
                     <tr>
                        <td style="color:darkblue;    font-weight: 700;">${a.student.id}</td>
                        <td style="color:darkblue;    font-weight: 700;" >${a.student.name}</td>
                        <td style="min-width: 170px; padding: 10px 0; text-align: center; ">
                            <img src  style="height: 146px; width: 111px;"/> 
                        </td>
                        <td>
                            <input 
                                   ${!a.present?"checked=\"checked\"":""}
                                   type="radio" value="no" name="present${a.student.id}" /> <span  style="color:red;">Absent </span>
                            <input type="radio"
                                   ${a.present?"checked=\"checked\"":""}
                                   value="yes" name="present${a.student.id}" />  <span  style="color:green;">Attended </span>
                        </td>
                        <td style="color: #00b8f4;">${account.username}</td>
                        <td class="description">
                            <input name="description${a.student.id}" type="text" value="${a.description}"/>
                        </td>
                        <td>${a.time}</td>
                        
                    </tr>   
                    </c:forEach>
                </table>
                <div style="margin-top: 30px;">
                   <input class="button_Save" type="submit" value="Save" />
                </div>
                
                                
            </div>
        </form>
    </body>
</html>
