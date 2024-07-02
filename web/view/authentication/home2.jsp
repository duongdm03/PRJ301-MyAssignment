<%-- 
    Document   : home2
    Created on : Mar 9, 2024, 3:04:54 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
    <script>
        function Logout(){
            window.location.href = "/Assignment/logout";
        }
    </script>
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
                <div>
                    <table style="margin-left: 20px; margin-right: -13px; margin-top: 50px; margin-bottom: 30px;">
                        <tr >
                            <td id="color_td" colspan="2"> <div style="color: white; background-color: rgb(0,153,0); margin: 10px; font-size: 20px;">IMPORTANT  NOTICE </div></td>
                            </tr>
                        <tr >
                            <td id="color_td" style="background-color: rgb(51,102,204); color: white; text-align: center; font-family: Arial, Helvetica, sans-serif; font-size: 15px; padding-top: 3px; padding-bottom: 3px;">Type of procedure |<br> Loại thủ tục </td>
                            <td id="color_td" style="background-color: rgb(51,102,204); color: white; text-align: center; font-family: Arial, Helvetica, sans-serif; font-size: 15px; padding-top: 3px; padding-bottom: 3px">Deadline |<br> Hạn nộp Đơn</td>
                        </tr>
                        <tr>
                            <td id="color_td"><span style="color: rgb(47,122,195);">1. Changing major</span> (Chuyển ngành) </td>
                            <td rowspan="2" id="color_td" style="margin-top: -100px;"> <span><span style="color: rgb(47,122,195);">4 weeks before the new semester</span> (4 tuần  trước học kỳ mới)</span></td>
                        </tr>
                        <tr>
                            <td id="color_td"><span style="color: rgb(47,122,195);">2. Changing campus</span> (Chuyển cơ sở) </td>
                        </tr>
                        <tr >
                            <td id="color_td"><span style="color: rgb(47,122,195);">3. Rejoin (Nhập học trở lại)</span></td>
                            <td id="color_td"><span style="color: rgb(47,122,195);">10 days before the new semester</span> (10 ngày trước học kỳ mới)</td>
                        </tr>
                        <tr >
                            <td id="color_td"><br></td>
                            <td id="color_td"><br></td>
                        </tr>
                        <tr>
                            <td id="color_td"><span style="color: rgb(47,122,195);">4. Suspend one semester</span> (Bảo lưu học kỳ)
                            <td rowspan="8" id="color_td"><span style="color: rgb(47,122,195);">1 week before the new semester</span> (1 tuần trước học kỳ mới)</td>
                        </tr>
                        <tr>
                            <td id="color_td"><span style="color: rgb(47,122,195);">5. Suspend one semester to take repeated course</span> (Tạm ngưng  tiến độ 1 học kỳ để học lại)</td>
                        </tr>
                        <tr>
                            <td id="color_td"><span style="color: rgb(47,122,195);">6. Suspend subject</span> (Tạm ngừng môn) </td>
                        </tr>
                        <tr>
                            <td id="color_td"><span style="color: rgb(47,122,195);">7. Register to repeat a course</span> (Đăng ký học lại)</td>
                        </tr>
                        <tr>
                            <td id="color_td"><span style="color: rgb(47,122,195);">8. Register extra courses</span> (Đăng ký học đi chậm kỳ) </td>
                        </tr>
                        <tr>
                            <td id="color_td"><span style="color: rgb(47,122,195);">9. Register to improve mark</span> (Đăng ký học cải thiện) </td>
                        </tr>
                        <tr>
                            <td id="color_td"><span style="color: rgb(47,122,195);">10. Move out class</span> (Chuyển lớp) </td>
                        </tr>
                        <tr>
                            <td id="color_td"><span style="color: rgb(47,122,195);">11. Request a drop out</span> (Thôi học tự nguyện) </td>
                        </tr>
                        <tr >
                            <td id="color_td"><br></td>
                            <td id="color_td"><br></td>
                        </tr>
                        <tr >
                            <td id="color_td"><span style="color: rgb(47,122,195);">12. Retake to improve mark</span> (Thi cải thiện)</td>
                            <td id="color_td"><span style="color: rgb(47,122,195);">12 hours before the final exam resit</span> (12h trước lịch thi lại)</td>
                        </tr>
                        <tr >
                            <td id="color_td"><span style="color: rgb(47,122,195);">13. Re – Examination</span> (Phúc tra)</td>
                            <td id="color_td"><span style="color: rgb(47,122,195);">1 week after the  examination result public</span> (1 tuần sau ngày công bố kết qủa)</td>
                        </tr>
                        <tr >
                            <td id="color_td"><span style="color: rgb(47,122,195);">14. Free of attendance</span> (Miễn điểm danh)</td>
                            <td id="color_td"><span style="color: rgb(47,122,195);">2 weeks after starting the new semester</span> (2 tuần sau khi học kỳ mới bắt đầu)</td>
                        </tr>
                        <tr >
                            <td id="color_td"><span style="color: rgb(47,122,195);">15. Pay specialized tuition</span> (Nộp học phí chuyên ngành)</td>
                            <td id="color_td"><span style="color: rgb(47,122,195);">7 working days before the new semester</span> (7 ngày trước học kỳ học mới không tính T7, CN)</td>
                        </tr>
                        <tr >
                            <td id="color_td"><span style="color: rgb(47,122,195);">16. Pay preparetation English tuition</span> (Nộp học phí Tiếng Anh dự bị)</td>
                            <td id="color_td"><span style="color: rgb(47,122,195);">3 working days before the new course</span> (3 ngày trước khi bắt đầu khóa học không tính T7, CN)</td>
                        </tr>
                    </table>
                </div>
            </div>
            <div class="col-md-7 me-5 mt-5 border">
                <div style="background-color: rgb(244,153,12); width: 45%; padding-top: 1px; border-top-left-radius: 5px; border-top-right-radius: 5px; margin-top: -10px; margin-left: -12px;">
                    <h3 style="font-family:Arial, Helvetica, sans-serif ; color: white; text-align: center;">Academic Information</h3>
                </div>
                <div class="row ms-3 mt-5" >
                    <input class="row" type="text" placeholder="Nhập URL video quá trình test-covid upload lên Youtube">
                </div>
                <div class="ms-1">
                    <input type="radio"> <b  style="font-size: 14px;">Cập nhật tình trạng F0 (Có hiệu lực 7 ngày kể từ thời điểm đăng ký)</b>
                    <p  style="font-size: 14px;">Sinh viên học LUK đăng ký học online vào theo link sau : <a href="#">https://bit.ly/K17P5HN-HocOnline</a></p>
                </div>
                <div class="ms-1">
                    <span style="font-size: 19px; margin-left: 230px;">Reports(Báo cáo)</span>
                </div>
                <div class="mt-2">
                    <table>
                        <tr>
                            
                            <td>
                                <ul style="font-size: 14px;">
                                    
                                    <a href="/Assignment/student/mark" id = "styleHref">Mark Report</a> (Báo cáo điểm)
                                    
                                    
                                </ul>
                            </td>
                        </tr>
                    </table>
                </div>
                <hr>
                
                <div class="mt-2">
                   
                </div>
                <hr>
                
                
            </div>
        </div>
    </div> 


    
  </body>
</html>
