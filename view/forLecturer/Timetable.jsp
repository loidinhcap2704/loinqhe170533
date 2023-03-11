<%-- 
    Document   : Timetable
    Created on : Feb 19, 2023, 10:37:55 PM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style type="text/css">
            <%@include file="Teacher.css"%> 
        </style>
    </head>
    <body>
        <h1>FPT University Academic Portal</h1>
    <p class="toolbar"><a style="" href="#" id="home">Home</a>    |    <a href="#">Hello</a></p>
    <form action="lecturer" method="post">
        <p id="campus" >Campus <select name="campus">
            <option>FU_HL</option>
            <option>FU_HVL</option>
            <option>FU_XVL</option>
        </select></p>
        <p id="lecturer">
            Lecturer<select name="lecturer">
                <option>sonnt5</option>
                <option>bantq</option>
                <option>tuanvm</option>
                <option>chilp</option>
            </select>
            <input type="submit" value="View"/>
        </p>
    </form>
    <table>
        <tr>
            <th rowspan="2" class="slot">YEAR<select>
                <option>2020</option>
                <option>2021</option>
                <option>2022</option>
                <option>2023</option>
            </select><br>
            WEEK<select>
                <option>17/01 To 23/01</option>
                <option>24/01 To 30/01</option>
                <option>31/01 To 06/02</option>
                <option>07/02 To 13/02</option>
            </select></th>
            <th>
                MON
            </th>
            <th>
                TUE
            </th>
            <th>
                WED
            </th>
            <th>
                THU
            </th>
            <th>
                FRI
            </th>
            <th>
                SAR
            </th>
            <th>
                SUN
            </th>
        </tr>
        <tr>
            <th class="slot">17/02</th>
            <th>18/02</th>
            <th>19/02</th>
            <th>20/02</th>
            <th>21/02</th>
            <th>22/02</th>
            <th>23/02</th>
        </tr>
        <tr>
            <td class="slot">Slot 1 <time>(07:30-08:50)</time></td>
            <td>
                <a href="#">IOT1702-PRF192</a> <br>
                at BE-301<br>
                (<atd class="attend">attended</atd>)
            </td>
            <td>
                -
            </td>
            <td>
                <a href="#">IOT1702-PRF192</a> <br>
                at BE-301<br>
                (<atd class="attend">attended</atd>)
            </td>
            <td>
                -
            </td>
            <td>
                <a href="#">AI1604-DBI202</a> <br>
                at BE-315<br>
                (<atd class="notYet">Not yet</atd>)
            </td>
            <td>
                -
            </td>
            <td>
                -
            </td>
        </tr>
        <tr>
            <td class="slot">Slot 2 <time>(09:00-12:20)</time></td>
            <td>
                <a href="#">SE1610-PRJ301</a> <br>
                at DE-217<br>
                (<atd class="attend">attended</atd>)
            </td>
            <td>
                <a href="#">SE1501-NET-PRU211m</a><br>
                at DE-331<br>
                (<atd class="attend">attended</atd>)
            </td>
            <td>
                <a href="#">SE1610-PRJ301</a> <br>
                at DE-217<br>
                (<atd class="notYet">Not yet</atd>)
            </td>
            <td>
                <a href="#">SE1501-NET-PRU211m</a><br>
                at DE-331<br>
                (<atd class="notYet">Not yet</atd>)
            </td>
            <td>
                <a href="#">SE1608-PRJ301</a><br>
                at DE-222<br>
                (<atd class="notYet">Not yet</atd>)
            </td>
            <td>
                -
            </td>
            <td>
                -
            </td>
        </tr>
        <tr>
            <td class="slot">Slot 3 <time>(12:50-15:10)</time></td>
            <td>
                <a href="#">SE1608-PRJ301</a><br>
                at DE-222<br>
                (<atd class="attend">attended</atd>)
            </td>
            <td>
                <a href="#">SE1616-PRJ301</a><br>
                at BE-101<br>
                (<atd class="attend">attended</atd>)
            </td>
            <td>
                <a href="#">SE1608-PRJ301</a><br>
                at DE-222<br>
                (<atd class="notYet">Not yet</atd>)
            </td>
            <td>
                <a href="#">SE1616-PRJ301</a><br>
                at BE-101<br>
                (<atd class="notYet">Not yet</atd>)
            </td>
            <td>
                <a href="#">SE1611-PRJ301</a><br>
                at DE-223<br>
                (<atd class="notYet">Not yet</atd>)
            </td>
            <td>
                -
            </td>
            <td>
                -
            </td>
        </tr>
        <tr>
            <td class="slot">Slot 4 <time>(15:20-17:40)</time></td>
            <td>
                <a href="#">SE1611-PRJ301</a><br>
                at DE-223<br>
                (<atd class="attend">attended</atd>)
            </td>
            <td>
                -
            </td>
            <td>
                <a href="#">SE1628-DBI202</a><br>
                at DE-C204<br>
                (<atd class="notYet">Not yet</atd>)
            </td>
            <td>
                -
            </td>
            <td>
                <a href="#">SE1628-DBI202</a><br>
                at DE-C204<br>
                (<atd class="notYet">Not yet</atd>)
            </td>
            <td>
                -
            </td>
            <td>
                -
            </td>
        </tr>
    </table>
    </body>
</html>
