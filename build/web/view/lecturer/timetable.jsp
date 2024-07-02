<%-- 
    Document   : timetable
    Created on : Feb 28, 2024, 8:53:28 AM
    Author     : sonnt
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>

            tr.date-row td {
                background-color: #7191e4; /* Màu xanh cho hàng chứa ngày và thứ */
                text-align: center;
                border: white solid 1px;
                font-weight: 700;
                font-size: 17px;
                color: white;
            }
            table {
                border-collapse: collapse;
                width: 100%;
            }
            td {
                text-align: center;
                padding: 8px;
                border-bottom: 1px solid #ddd;
                min-width: 100px; /* Định kích thước tối thiểu cho các ô */
            }
            table {
                border-collapse: collapse;
                width: 100%;
            }
            th {
                background-color: #3498db; /* Màu xanh cho hàng đầu tiên */
                color: white;
            }
            th, td {
                padding: 8px;
                border-bottom: 1px solid #ddd;
            }

            .form-select-sm{
                padding: 2px;
                border:none;
            }
            .edunext{
                display: block;
                padding: 4px 2px;
                background-color: #0dc51e;
                border-radius: 5px;
                text-decoration: none;
                color: white;
                font-size: 12px;
            }
            .func__button a{
                margin-right:  5px;
            }
            .func__button{
                justify-content: center;
            }
            .container{
                margin: 0 8%;
            }
            .table_content td{
                border-left: white solid 1px;
                border-right: white solid 1px;
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
        </style>
    </head>
    <body>
        <div class="container">
            <div style="display:flex; justify-content: space-between">
                <h1 style="margin-bottom: 30px;">FPT University Academic Portal</h1>
                <div class="col-md-4">
                    <table>
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
                    <span id="ctl00_lblNavigation"><a href="../view/authentication/home.jsp">Home</a>&nbsp;|&nbsp;<b>View Schedule</b></span></li>
            </ol>
            <h2>Activities for ${account.username} (${account.displayname}) </h2>


            <p>
                <b>Note</b>: These activities do not include extra-curriculum activities, such as
                club activities ...
            </p>
            <p>
                <b>Chú thích</b>: Các hoạt động trong bảng dưới không bao gồm hoạt động ngoại khóa,
                <a href="grading.jsp"></a>
                ví dụ như hoạt động câu lạc bộ ...
            <div id="ctl00_mainContent_ghichu">
                <p>
                    Các phòng bắt đầu bằng AL thuộc tòa nhà Alpha. VD: AL...<br>
                    Các phòng bắt đầu bằng BE thuộc tòa nhà Beta. VD: BE,..<br>
                    Các phòng bắt đầu bằng G thuộc tòa nhà Gamma. VD: G201,...<br>
                    Các phòng tập bằng đầu bằng R thuộc khu vực sân tập Vovinam.<br>
                    Các phòng bắt đầu bằng DE thuộc tòa nhà Delta. VD: DE,..<br>
                    Little UK (LUK) thuộc tầng 5 tòa nhà Delta
                </p>
            </div>
        </p>
        <table border="1px">
            <tr class="date-row">
                <td rowspan="2" style="text-align: left; width: 12%; min-width: 196px;">
                    <form action="timetable" method="POST">
                        <div class="d-flex">
                            <span style="width: 50px; display: inline-block;">Year</span>
                            <select name="selectYear" class="form-select-sm" onchange="this.form.submit()">
                                <c:forEach begin="2020" end="${year}" var="i">
                                    <option ${i == selectYear ? "selected":""}>${i}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </form>
                    <form action="timetable" method="POST">
                        <div class="d-flex mt-2" style="margin-top: 5px;">
                            <input type="hidden" name="selectYear"  value="${selectYear}"/>
                            <input type="hidden" name="action"  value="week"/>
                            <span style="width: 50px; display: inline-block;">Week</span>
                            <select name="selectDate" class="form-select-sm" onchange="this.form.submit()">
                                <c:forEach items="${listW}" var="i">
                                    <option ${selectDate eq i ? "selected":""}>${i}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </form>
                </td>
                <td>Monday</td>
                <td>Tuesday</td>
                <td>Wednesday</td>
                <td>Thursday</td>
                <td>Friday</td>
                <td>Saturday</td>
                <td>Sunday</td>


            </tr>
            <tr class="date-row">
                <c:forEach items="${dates}" var="d">
                    <td>${d}</td>
                </c:forEach>
            </tr>
            <c:forEach items="${requestScope.slots}" var="slot">
                <tr class="table_content">
                    <td>${slot.id}</td>
                    <c:forEach items="${dates}" var="d">
                        <td style="    color: #005c9a;">
                            <c:set var="hasData" value="false" />
                            <c:forEach items="${requestScope.lessions}" var="les">
                                <c:if test="${d eq les.date and les.slot.id eq slot.id}">
                                    ${les.group.name} <br/>
                                    at ${les.room.id}<br/>

                                    (${les.slot.description})<br/>
                                    <div style="display: flex;" class="func__button">
                                        <a href="atts?id=${les.id}" style="display: flex;align-items:  center;font-size: 19px;"> 
                                            <c:if test="${les.attended}">
                                                Edit
                                            </c:if>
                                        </a>
                                        <a href="https://fu-edunext.fpt.edu.vn/" class="edunext">EduNext</a>
                                        <a href="atts?id=${les.id}" style="display: flex; align-items:  center;"> 
                                            <c:if test="${!les.attended}">
                                                Take
                                            </c:if>  
                                        </a>
                                    </div>
                                    <c:set var="hasData" value="true" />
                                </c:if>
                            </c:forEach>
                            <c:if test="${!hasData}">
                                -
                            </c:if>
                        </td>
                    </c:forEach>
                </tr>  
            </c:forEach>
        </table>    
    </div>
</body>

</html>