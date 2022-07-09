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
                                                        <h4 class="product-price"><span class="qty">${t.quantity}x</span><fmt:formatNumber value="${t.price}" type="currency" currencySymbol="đ" maxFractionDigits="0"/></h4>
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
            <div class="main-content flex">
                <div class="items-image flex flex-column">
                    <div class="main-img">
                        <img src="${requestScope.product.image}">
                    </div>
                    <div class="img-list">

                    </div>
                </div>
                <div><div class="items-des flex flex-column flex-auto">
                        <div class="item-name">
                            ${requestScope.product.name}
                        </div>
                        <div class="items-property flex">
                            <div class="numberOfFeedback flex">
                                <div class="number">${requestScope.countFeedback}</div>
                                <div>Đánh giá</div>

                            </div>
                            <div class="numberOfSold flex">
                                <div class="number">${requestScope.soldQuan}</div>
                                <div>Đã bán</div>
                            </div>

                        </div>
                        <div class="items-price"><fmt:formatNumber value="${requestScope.product.price}" type="currency" currencySymbol="đ"/></div>
                        <div class="items-order-number flex">
                            <div>Số lượng</div>
                            <div class="flex items-center">
                                <div margin-right: 15px;>
                                     <div class="flex input-quantity">
                                         <input type="hidden" value="${requestScope.product.productID}" id="productID"/>
                                         <button id="subtract" onclick="subtractQuan()">-</button>
                                         <input type="number" value="1" id="quantity" max="${requestScope.product.unitsInStock}" min="1" oninput="quan()"/>
                                         <button id="add" onclick="addQuan()">+</button>
                                    </div>
                                </div>
                                <div style="padding-left: 20px;">${requestScope.product.unitsInStock} sản phẩm có sẵn</div>
                            </div>
                            <div></div>
                        </div>
                        <div class="items-action" style="margin-top: 15px;">
                            <div style="padding-left: 20px;">
                                <div class="action-container">
                                    <button onclick="addToCart()" class="add-to-cart">Thêm vào giỏ hàng</button>
                                    <form action="" method="post" id="buyNow"><button class="buy-now" onclick="buyNow('${requestScope.product.productID}')">Mua ngay</button></form>
                                    
                                </div>
                            </div>
                        </div>
                    </div></div>

            </div>
            <div class="product-rating" id="product-rating"><div class="feedback">
                    <h2 class="feedback-header">Đánh giá sản phẩm</h2>
                    <div class="feedback-add">
                        <form style="display: flex; width: 100%;" action="addfeedback">
                            <input type="text" placeholder="Add feedback" name="feedback" required id="newFb"/>
                            <input type="hidden" value="${requestScope.product.productID}" name="productid"/>
                            <button>Add</button>
                            
                        </form>
                    </div>
                    <div class="feedback-list flex flex-column">
                        <c:forEach items="${sessionScope.feedback}" var="i"><div class="feedback-container">
                                <strong><a href="">${i.userID}</a></strong>
                                <div class="comment-date">${i.date}</div>
                                <div>${i.content}</div></div>
                            </c:forEach>
                        <input type="hidden" value="${requestScope.product.productID}" id="productID"/>
                        <div class="comment-controller-wrap">
                            <div style="margin: 20px 20px 0 0;">
                                <span id="current-page">${requestScope.page}</span>
                                <span>/</span>
                                <span>${requestScope.num}</span>

                            </div>
                            <div style="margin-top: 20px;">
                                <button <c:if test="${requestScope.page == 1}">disabled</c:if> onclick="previousFeedbackPage()"><i class="fa-solid fa-angle-left"></i></button>
                                <button <c:if test="${requestScope.page == requestScope.num}">disabled</c:if> onclick="nextFeedbackPage()"><i class="fa-solid fa-angle-right"></i></button>
                                </div>

                            </div>
                        </div>
                    </div></div>            

                <div class="items-from-this-shop container">
                    <h2>Product from this shop</h2>
                    <div class="row">
                    <c:forEach items="${requestScope.productFromShop}" var="p">
                        <div class="col-sm-4 col-md-2">
                            <div class="product-container">
                                <a id="" href="product?productid=${p.productID}">
                                    <div class="items">
                                        <img src="${p.image}" class="img-responsive"/>
                                        <div class="product-item-name">${p.name}</div>
                                        <div class="product-items-price"><fmt:formatNumber value="${p.price}" type="currency" currencySymbol="đ"/></div>

                                    </div>
                                </a>
                            </div>

                        </div>
                    </c:forEach>


                </div>
            </div>
            <div class="items-from-this-shop container">
                <h2>Related product</h2>
                <div class="row">
                    <c:forEach items="${requestScope.relatedProduct}" var="p">
                        <div class="col-sm-4 col-md-2">
                            <div class="product-container">
                                <a id="" href="product?productid=${p.productID}">
                                    <div class="items">
                                        <img src="${p.image}" class="img-responsive"/>
                                        <div class="product-item-name">${p.name}</div>
                                        <div class="product-items-price"><fmt:formatNumber value="${p.price}" type="currency" currencySymbol="đ"/></div>

                                    </div>
                                </a>
                            </div>

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
        <script src="js/homeSubmitForm.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    </body>
</html>
