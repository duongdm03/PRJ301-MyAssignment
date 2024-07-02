
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
        <style>
            .container{
                margin: 0 8%;
            }
            .breadcrumb {
                padding: 8px 15px;
                margin-bottom: 20px;
                list-style: none;
                background-color: #f5f5f5;
                border-radius: 4px;
                font-size: 20px;
            }
            .breadcrumb a{
                font-size: 20px;
                font-weight: 700;
            }
            
            table{
                border-collapse: collapse;
            }
            
            th{
                text-align: center;
                padding: 15px 10px;
                font-size: 18px;
                border: white solid 1px;
                background-color: #3498db;
                color: white;
            }
            td{
               border-left: white solid 1px;
               border-right: white solid 1px;
               text-align: center;
               padding: 10px;
            }
            .notcsstable th{
                background-color:white;
            }
            .notcsstable td{
                background-color:white;
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
            .button_Save2{
                padding: 10px; 
                border-radius: 5px; 
                background-color: orange; 
                color: white; 
                border: none; 
                font-size: 19px;
                transition: 0.3s;
                
            }
            .button_Save2:hover{
                cursor: pointer;
                background-color: yellow;
                color: blueviolet;
            }
            select{
                padding: 7px;
                border: darkred solid 1px;
                border-radius: 5px;
                color: purple;
                font-weight: 600;
            }
            p{
                margin: 0;
            }
        </style>
    </head>
    <body>
        <div class="container">
            
        
        <div style="display:flex; justify-content: space-between">
                <h1 style="margin-bottom: 30px;">FPT University Academic Portal</h1>
                <div class="col-md-4">
                    <table class="notcsstable">
                        <tbody>
                            <tr>
                                <td colspan="2" class="auto-style1" style="border-bottom: none;"><strong>FAP mobile app (myFAP) is ready at</strong></td>
                            </tr>
                            <tr>
                                <td style="border-bottom: none;"><a href="https://apps.apple.com/app/id1527723314">
                                        <img src="https://fap.fpt.edu.vn/images/app-store.png" style="width: 120px; height: 40px" alt="apple store"></a></td>
                                <td style="border-bottom: none;"><a href="https://play.google.com/store/apps/details?id=com.fuct">
                                        <img src="https://fap.fpt.edu.vn/images/play-store.png" style="width: 120px; height: 40px" alt="google store"></a></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        <ol class="breadcrumb">
                <li>
                    <span id="ctl00_lblNavigation"><a href="../view/authentication/home2.jsp">Home</a>&nbsp;|&nbsp;<b>View Schedule</b></span></li>
            </ol>
            <h2>Activities for ${account.username} (${account.displayname}) </h2>
        <form method="get" action="mark">
            <select name="sub" onchange="this.form.submit()">
                <c:forEach var="s" items="${listS}">
                    <option value="${s}" ${sub eq s ? "selected":""}>
                        ${s}
                    </option>
                </c:forEach>

            </select>
        </form>

        <table border="1" >
            <thead>
                <tr>
                    <th>Assessment Name</th>
                    <th>Weight</th>
                    <th>Score</th>
                    <th>Comment</th>
                </tr>
            </thead>
            <c:set var="isPassFe" value="Pass" ></c:set>
            <tbody>
                <c:forEach var="s" items="${listA}">
                    <c:if test="${fn:startsWith(s.assName, 'Final') && s.score < 4}">
                         <c:set var="isPassFe" value="Not Pass" ></c:set>
                    </c:if>
                    <tr>
                        <td >${s.assName}</td>
                        <td>${s.weight}%</td>
                        <td>${s.score}</td>
                        <td>
                            ${s.comment}
                        </td>
                    <tr/>
                </c:forEach>

                <tr>
                    <td colspan="2" style="background-color: #ddd">AVERAGE</td>
                    <td colspan="1" id="total">
                        <c:if test="${average >= 0}">
                            ${average}
                        </c:if>
                    </td>
                    <td colspan="1"></td>
                </tr>
               
                <tr>
                    <td colspan="3" style="background-color: #ddd">STATUS</td>
                    <td colspan="1" id="status">
                        <c:if test="${average >= 5 && isPassFe ne 'Not Pass'}">
                            <p style="color:green; font-weight: 600">PASSED <i class="fas fa-check"></i></p>
                        </c:if>
                         <c:if test="${(average < 5 && average >= 0)|| isPassFe eq 'Not Pass'}">
                             <p style="color:red; font-weight: 600">NOT PASSED <i class="fas fa-exclamation"></i></p>
                        </c:if>
                         <c:if test="${average < 0}">
                             <p style="color:darkblue; font-weight: 600">Studying</p>
                        </c:if>
                    </td>
                </tr>
            </tbody>
        </table>
            </div>
    </body>

</html>
