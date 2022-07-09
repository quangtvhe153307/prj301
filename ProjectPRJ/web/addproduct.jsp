<%-- 
    Document   : home1
    Created on : Feb 25, 2022, 4:18:33 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>PRJ Shop</title>
        <!-- Bootstrap-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <!--<link href="css/bootstrap.min.css" rel="stylesheet">-->
        <!-- Font Awesome Icon -->
        <script src="https://kit.fontawesome.com/3c84cb624f.js" crossorigin="anonymous"></script>


        <!--custom style-->
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <header>
            <!-- MAIN HEADER -->
            <div id="header">
                <!-- container -->
                <div class="container">
                    <!-- row -->
                    <div class="row">
                        <!-- LOGO -->
                        <div class="col-md-2">
                            <div class="header-logo" style="float: right;">
                                <a href="home" class="logo">
                                    <img src="image/PRJ.png" alt="" style="height: 70px; width: 70px;">
                                </a>
                            </div>
                        </div>
                        <!-- /LOGO -->

                        <!-- SEARCH BAR -->
                        <div class="col-md-6" style="margin-top: 20px;">
                            <div class="header-search">
                                <form action="search" method="get">
                                    <input class="input" placeholder="  Search here" name="key" id="searchKey" value="${requestScope.key}">
                                    <button class="search-btn">Search</button>
                                </form>
                            </div>
                        </div>
                        <!-- /SEARCH BAR -->

                        <!--CART-->
                        <div class="col-md-2 clearfix">
                            <div class="header-ctn">
                                <!-- Cart -->
                                <div class="dropdown">
                                    <a class="dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false" id="dropdowncart">
                                        <i class="fa fa-shopping-cart"></i>
                                        <span>Your Cart</span>
                                        <div class="qty">${requestScope.cartSize}</div>
                                    </a>
                                    <div class="dropdown-menu carts-menu" aria-labelledby="dropdowncart">
                                        <c:set var="items" value="${requestScope.cart.items}" />
                                        <c:forEach items="${items}" var="t">
                                            <div class="dropdown-item">
                                                <div class="flex">
                                                    <div class="product-img">
                                                        <img src="${t.product.image}" alt="">
                                                    </div>
                                                    <div class="product-body">
                                                        <h3 class="product-name"><a href="product?productid=${t.product.productID}">${t.product.name}</a></h3>
                                                        <h4 class="product-price"><span class="qty">${t.quantity}x</span><fmt:formatNumber value="${t.price}" type="currency" currencySymbol="đ"/></h4>
                                                    </div>
                                                </div>

                                            </div>
                                        </c:forEach>
                                        <div class="dropdown-divider"><hr></div>
                                        <div class="cart-summary dropdown-item">
                                            <small>${requestScope.cartSize} Item(s) selected</small>
                                            <h5>SUBTOTAL: <fmt:formatNumber value="${requestScope.cart.getTotalMoney()}" type="currency" currencySymbol="đ"/></h5>
                                        </div>
                                        <div class="cart-btns dropdown-item">
                                            <a href="cart">View Cart</a>
                                            <a href="checkout">Checkout  <i class="fa fa-arrow-circle-right"></i></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div><!-- /Cart -->
                        <!--Account-->
                        <div class="col-md-2">
                            <div class="header-menu">
                                <div class="btn-group">
                                    <c:if test="${sessionScope.account == null}"><button type="button" class="btn btn-danger"><i class="fa-solid fa-user"></i></button></c:if>
                                    <c:if test="${sessionScope.account != null}"><button type="button" class="btn btn-danger">${sessionScope.account.username}</button></c:if>

                                        <button type="button" class="btn btn-danger dropdown-toggle dropdown-toggle-split" data-bs-toggle="dropdown" aria-expanded="false">
                                        </button>
                                        <ul class="dropdown-menu">
                                        <c:if test="${sessionScope.account == null}">
                                            <li><a class="dropdown-item" href="login">Login</a></li>
                                            <li><a class="dropdown-item" href="register">Sign up</a></li>
                                            </c:if>
                                            <c:if test="${sessionScope.account != null}">
                                            <li><a class="dropdown-item" href="userprofile">Profile</a></li>
                                            <c:if test="${sessionScope.account.role eq 2}"><li><a class="dropdown-item" href="productmanage">Manage your product</a></li></c:if>
                                            <c:if test="${sessionScope.account.role eq 1}"><li><a class="dropdown-item" href="sellerregister">Register to be seller</a></li></c:if>
                                            <c:if test="${sessionScope.account.role eq 3}"><li><a class="dropdown-item" href="admin">Admin</a></li></c:if>
                                                <li><hr class="dropdown-divider"></li>
                                                <li><a class="dropdown-item" href="changepassword">Change password</a></li>
                                                <li><a class="dropdown-item" href="logout">Logout</a></li>
                                            </c:if>

                                    </ul>
                                </div>
                            </div>
                        </div>
                        <!-- /ACCOUNT -->
                    </div>
                    <!-- row -->
                </div>
                <!-- container -->
            </div>
            <!-- /MAIN HEADER -->
        </header>

        <div class="page-product">
            <h2 style="margin: 0 0 50px 15%;">Add product</h2>
            <div class="main-content flex">
                <form style="padding: 30px;" action="addproduct" method="post">
                    <div class="update-product-container">
                        <div class="items-des flex flex-column flex-auto">
                            <div class="flex update-product-row">
                                <div class="flex update-product-category" style="margin-left: 0;"> 
                                    <div>Category </div>
                                    <select name="categoryid" style="margin-left: 10px; height: 40px;">
                                        <c:forEach items="${requestScope.list}" var="i">
                                            <option value="${i.catID}">${i.catName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="flex update-product-row update-product-name">
                                <div>Name </div><input type="text" placeholder="Enter product name" name="name"/>
                            </div>
                            <div class="flex update-product-row">
                                <div class="flex update-product-price">
                                    <div>
                                        UnitPrice
                                    </div>
                                    <input type="number" value="<fmt:formatNumber value="${requestScope.product.price}" type="number" pattern="#"/>" name="price" placeholder="Unit price"/></div>
                                <div class="flex update-product-quantityperunit">
                                    <div>Quantity per unit</div><input type="text" name="quantityPerUnit" placeholder="Enter quantity per unit"/>
                                </div>
                            </div>
                            <div class="flex update-product-row update-product-image">
                                <div>Image </div><input type="text" placeholder="Enter image src" name="img"/>
                            </div>
                            <div class="flex update-product-row">
                                <div class="flex update-product-stock">
                                    <div>Units in Stock</div>
                                    <input type="number" placeholder="Enter units in stock" name="unitsInStock"/>
                                </div>
                                <div class="flex update-product-brand">
                                    <div>Brand</div>
                                    <input type="text" placeholder="Enter brand" name="brand"/>
                                </div>
                            </div>

                        </div>
                    </div>
                    <input type="submit" value="Add" id="updateProductSubmit"/><span style="color: red;
                                                                                     margin-left: 20px;
                                                                                     font-weight: bold;">${requestScope.message}</span>
                </form>


            </div>          

        </div>


        <!--footer-->
        <div class="footer">
            <div class="container">
                <div class="row">
                    <div class="col-md-6">
                        <h3 class="footer-title">About us</h3>
                        Death is like the wind, always by my side!
                        <ul class="footer-links">
                            <li><a href=""><i class="fa-solid fa-location-dot"></i>Khu Công nghệ cao Hòa Lạc, Km29, Đại lộ Thăng Long, H. Thạch Thất, Tp. Hà Nội</a></li>
                            <li><a href=""><i class="fa-solid fa-phone"></i>+84395073662</a></li>
                            <li><a href=""><i class="fa-solid fa-envelope"></i>quangtvhe153307@fpt.edu.vn</a></li>
                        </ul>
                    </div>
                    <div class="col-md-3">
                        <h3 class="footer-title">Information</h3>
                        <ul class="footer-links">
                            <li><a href="">About us</a></li>
                            <li><a href="">Contact us<a/></li>
                            <li><a href="">Privacy Prolicy</a></li>
                            <li><a href="">Orders and Returns</a></li>
                            <li><a href="">Terms & Condition</a></li>
                        </ul>
                    </div>
                    <div class="col-md-3">
                        <h3 class="footer-title">Service</h3>
                        <ul class="footer-links">
                            <li><a href="">My Account</a></li>
                            <li><a href="">Contact us<a/></li>
                            <li><a href="">View Cart</a></li>
                            <li><a href="">Track My Order</a></li>
                            <li><a href="">Help</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    </body>
</html>
