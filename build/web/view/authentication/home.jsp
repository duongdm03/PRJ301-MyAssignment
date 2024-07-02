<%-- 
    Document   : Home
    Created on : Oct 26, 2022, 3:31:03 AM
    Author     : Khanh Manh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script>
        function Logout(){
            window.location.href = "/Assignment/logout";
        }
    </script>
    <style>
        a{
            text-decoration: none;
            font-size: 19px;
            font-weight: 700;
        }
    </style>
</head>
  <body>
    <div class="container">
        <header>
            <br>
            <h1>FPT University Academic Portal</h1>
            
            <div class="d-flex justify-content-end d-flex align-items-start " style="height: 50px; background-color: #EEE;" >
                <button type="button" class="btn btn-success h-10 pt-0 me-1 mt-1" style="height: 20px; font-size: 12px; background-color: rgb(92,184,92);" >${account.displayname} </button> | 
                <button onclick="Logout()" type="button" class="btn btn-success h-15 pt-0 me-1 ms-1 mt-1" style="height: 20px; font-size: 12px; background-color: rgb(92,184,92);"> logout </button> | 
                <button type="button" class="btn btn-success h-15 pt-0 ms-1 me-5 mt-1" style="height: 20px; font-size: 12px; background-color: rgb(92,184,92);"> CAMPUS: FPTU-Hòa Lạc 
                </button>
            </div>

        </header>
        <div class="row">
            <div class="col-md-4 me-5 mt-5 border">
                <div style="background-color: rgb(14,101,181); width: 45%; padding-top: 1px; border-top-left-radius: 5px; border-top-right-radius: 5px; margin-top: -10px; margin-left: -12px;">
                    <h3 style="font-family:Arial, Helvetica, sans-serif ; color: white; text-align: center;">News</h3>
                </div>
                <div>
                    <p style="display: inline;">Tin tức</p>
                    <button type="button" style="background-color: rgb(239,169,68); color: white; border-color: white; border-radius: 5px; padding: 4px;">xem tại đây</button>
                </div>
                
            </div>
            
            <div class="col-md-7 me-5 mt-5">
                <div>
                <img src="https://upload.wikimedia.org/wikipedia/commons/6/68/Logo_FPT_Education.png" style="width: 100%;"/>
                </div>
                <div class="border">
                        <div style="background-color: rgb(244,153,12); width: 45%; padding-top: 1px; border-top-left-radius: 5px; border-top-right-radius: 5px; margin-top: -10px; margin-left: -12px;">
                            <h3 style="font-family:Arial, Helvetica, sans-serif ; color: white; text-align: center;">Academic Information</h3>
                        </div>

                    <div>
                        <div>
                            <a href="/Assignment/lecturer/grade" id = "styleHref"><i class="fas fa-marker"></i> Grading for students</a> (Chấm điểm cho sinh viên)
                        </div>
                        <div>
                            <a href="/Assignment/lecturer/timetable" id = "styleHref"><i class="fas fa-table"></i> Weekly timetable</a> (Thời khóa biểu từng tuần)
                        </div>
                    </div>
                
                </div>
                
            </div>
        </div>
    </div> 


    
  </body>
</html>




 