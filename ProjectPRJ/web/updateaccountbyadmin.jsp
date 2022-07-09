<%-- 
    Document   : home1
    Created on : Feb 25, 2022, 4:18:33 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>PRJ Shop</title>
        <!-- Bootstrap-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

        <!-- Font Awesome Icon -->
        <script src="https://kit.fontawesome.com/3c84cb624f.js" crossorigin="anonymous"></script>


        <!--custom style-->
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <c:set var="a"  value="${requestScope.a1}"/>
        <div>
            <form action="updateaccountadmin" method="post">
                <div class="profile-infor-container">
                    <div class="profile-fields"><strong>Username:</strong> <input type="text" name="username" value="${a.username}" readonly style="width: 100px;"/></div>
                    <div class="profile-fields"><strong>Contact Name</strong><input type="text" name="contact" value="${a.contactName}"/></div>
                    <div class="profile-fields"><strong>Address:</strong> <input type="text" name="address" value="${a.address}" style="width: 400px;"/></div>
                    <div class="profile-fields"><strong>City:</strong> <input type="text" name="city" value="${a.city}" style="width: 100px;"/></div>
                    <div class="profile-fields"><strong>Phone:</strong> <input type="text" name="phone" value="${a.phone}" style="width: 150px;"/></div>
                    <div class="profile-fields"><strong>Email:</strong> <input type="email" name="email" value="${a.email}"/></div>
                    <input type="submit" value="Save change"/> <span style="color: #D10024;margin-left: 10px; font-size: 20px; font-weight: 500;">${requestScope.message}</span></div>
                </div>

            </form>
        </div>



        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    </body>
</html>
