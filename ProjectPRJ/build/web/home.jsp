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


        <!--Category list-->
        <div id="categoryList">
            <h2 class="title">Danh mục ngành hàng</h2>
            <div class="container">
                <div class="row">
                    <c:forEach items="${requestScope.cateList}" var="c">
                        <div class="col-sm-4 col-md-2">
                            <div class="cate-item">
                                <a href="productbycategory?categoryId=${c.catID}"><img src="${c.picture}" class="img-responsive img-circle"/></a>
                                <span class="cate-item-name">${c.catName}</span>

                            </div>
                        </div>
                    </c:forEach>

                </div>
            </div>
        </div>

        <!--best seller-->
        <div class="bestSeller">
            <h2>Sản phẩm bán chạy</h2>
            <div class="container">
                <div class="row">
                    <c:set var="p" value="${requestScope.bestProductList}" />
                    <c:set var="s"  value="${requestScope.bestSoldQuantity}"/>

                    <c:forEach begin="0" end="${p.size()-1}" var="i">
                        <div class="col-sm-6 col-md-3">
                            <div class="product-container"><a id="" href="product?productid=${p.get(i).productID}">
                                    <div class="best-sale-items">
                                        <img src="${p.get(i).image}" class="img-responsive"/>
                                        <div class="product-item-name">${p.get(i).name}</div>
                                        <div class="product-items-price">
                                            <div class="price"><fmt:formatNumber value="${p.get(i).price}" type="currency" currencySymbol="đ"/></div>
                                            <div class="soldQuantity">Đã bán ${s.get(i)}</div>
                                        </div>


                                    </div>
                                </a></div>

                        </div>

                    </c:forEach>
                </div>
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
