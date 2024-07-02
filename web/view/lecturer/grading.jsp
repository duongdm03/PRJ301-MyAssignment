<%-- 
    Document   : grading
    Created on : Mar 4, 2024, 4:41:24 PM
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
            .container{
                margin: 0 8%;
            }
            table{
                border-collapse: collapse;
                width: 100%;
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
            td{
                background-color: #aadcbc;
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

        <div class="container">
            

            <div style="padding: 0 300px;">
                <img src="https://upload.wikimedia.org/wikipedia/commons/6/68/Logo_FPT_Education.png" style="width: 100%;"/>
            </div>
        <h2 style="margin-bottom: 0px;">Lectuter: ${account.displayname}</h2> 
        <div style="background-color: #fcf8f8; padding: 2px 5px;">
            <a href="../view/authentication/home.jsp" class="home_button">Home</a>
        </div>
        
        <form action="grade" method="get">
            
            <br>
            <select name="group" onchange="this.form.submit()" style="margin-bottom: 10px;">
                <c:forEach var="g" items="${listS}">
                    <option ${g.gID eq gID && g.subID eq subID ? "selected":""} value="${g.gID}:${g.subID}">Subject: ${g.subID} - Group: ${g.gID}</option>
                </c:forEach>
                
            </select>
        </form>
        <form action="grade" method="get">
            <select name="assesment" onchange="this.form.submit()" style="margin-bottom: 5px;">
                <c:forEach var="g" items="${listA}">
                    <option ${ass eq g.assID ?"selected":""} value="${g.assID}">${g.assName} - ${g.weight}</option>
                </c:forEach>

            </select>
            <input type="hidden" name="group" value="${gID}:${subID}" />
        </form>
        <div style="display: grid; grid-template-columns: 60% 35%; justify-content: space-between;">
            
        
        <form action="grade" method="post">
            <table border="1">
                <thead>
                    <tr>
                        <th>Student ID</th>
                        <th>Student Name</th>
                        <th>Assessment</th>
                        <th>Score</th>
                        <th>Comment</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="s" items="${listSt}">
                        <tr>
                            <td><p style="background-color: white;">${s.stuID}</P></td>
                            <td><p style="background-color: white;">${s.stuName}</p></td>
                            <td><p style="background-color: white;">${s.assName}</p></td>
                            <td>
                                <input type="number" step="0.1" min="0" max="10" name="score:${s.exID}:${s.stuID}" value="${s.score}" />
                                <input type="hidden" name="scores" value="${s.exID}:${s.stuID}" />
                            </td>
                            
                           <td style="padding-right:0px;">
                                <input type="text" name="comment:${s.exID}:${s.stuID}" value="${s.comment}" />
                            </td>
                        </tr>
                    </c:forEach>
                        


                </tbody>
            </table>
            <div style="margin-top:30px;">
                <button type="submit" class="button_Save">Update</button>
                <button type="reset" class="button_Save2">Reset</button>
            </div>
        </form>
            <div>
                <img src="https://fpteducationgroup.files.wordpress.com/2015/08/c491e1baa1i-he1bb8dc-fpt-ve1bb9bi-thie1babft-ke1babf-c491e1bb99c-c491c3a1o-soi-bc3b3ng-xue1bb91ng-he1bb932.jpg?w=604&h=270&crop=1" style="width: 100%;position:sticky; top:20px;"/>
                <img src="https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEiFw0rZ_YCk2IeCuKuiLCVvOlm3df-y2PGJQtoPhxQwvrAI4bTWlhpADmoey7EXEahcmaQpY3_J56Imvym5CogUmyNyed4NwsD2aZvomJAT0HlDavV7orGWFBLutQDpJfAq56DN7zlUGnqS/s1600/pkientruc-%C4%91ai+h%E1%BB%8Dc+fpt+(2).jpg" style="width: 100%;position:sticky; top:200px;"/>
            </div>
                </div>
        </div>

    </body>
</html>
