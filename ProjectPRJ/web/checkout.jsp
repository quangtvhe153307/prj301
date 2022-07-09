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
    <body style="background: rgba(0,0,0,.05);">
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

        <div class="main-content" style="width: 1200px;
             margin-left: auto;
             margin-right: auto;">
            <div class="th flex">
                <div>
                    <!--<input type="checkbox"/>-->
                </div>
                <div>Sản phẩm</div>
                <div>Đơn giá</div>
                <div>Số lượng</div>
                <div>Số tiền</div>
                <div>Thao tác</div>
            </div>
            <c:forEach items="${requestScope.providerList}" var="p">
                <div class="shop-product-container flex flex-column">
                    <div class="shop-name">${p}</div>
                    <div>
                        <c:forEach items="${requestScope.cart.items}" var="i">
                            <c:if test="${p eq i.product.userID}">
                                <div class="cart-item-container">
                                    <div class="cart-item flex">
                                        <div>
                                            <!--<input type="checkbox"/>-->
                                        </div>
                                        <div class="cart-item-info flex">
                                            <img src="${i.product.image}"/>
                                            <div>${i.product.name}</div>
                                        </div>
                                        <div></div>
                                        <div class="cart-item-price"><fmt:formatNumber value="${i.product.price}" type="currency" currencySymbol="đ"/></div>
                                        <div class="cart-item-quantity">
                                            <div><button><a href="process?num=-1&id=${i.product.productID}">-</a></button>
                                                <input type="text" value="${i.quantity}"/>
                                                <button><a href="process?num=1&id=${i.product.productID}">+</a></button></div>
                                            <div>Còn ${i.product.unitsInStock} sản phẩm</div>

                                        </div>
                                        <div class="cart-item-totalMoney"><fmt:formatNumber value="${i.quantity * i.price}" type="currency" currencySymbol="đ"/></div>
                                        <div class="cart-item-action">
                                            <form action="process" method="post">
                                                <input type="hidden" name="id" value="${i.product.productID}"/>
                                                <input type="submit" value="Delete"/>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>
                </div>


            </c:forEach>
            <div class="address-container">
                <h3>Địa chỉ nhận hàng</h3>
                <form action="checkout" method="post" id="checkOutForm">
                    <div class="reveive-address-container flex flex-column">
                        <div class="reveive-address-container-row flex">
                            <div class="name"><span>Ship Name: </span><input type="text" name="name" value="${sessionScope.account.contactName}" required/></div>
                            <div class="phone"><span>Ship Phone: </span><input type="text" name="phone" value="${sessionScope.account.phone}" required /></div>
                        </div>
                        <div class="reveive-address-container-row flex">
                            <div class="address"><span>Ship Address: </span><input type="text" name="address" value="${sessionScope.account.address}" required/></div>
                            <div class="city"><span>Ship City </span><input type="text" name="city" value="${sessionScope.account.city}" required/></div>

                        </div>
                        <div class="reveive-address-container-row flex">
                            <div class="email"><span>Ship Email: </span><input type="email" name="email" value="${sessionScope.account.email}" required/></div>
                            <div class="shipper flex">
                                <span>Đơn vị vận chuyển: </span>
                                <select name="shipper">
                                    <c:forEach items="${requestScope.shipList}" var="i">
                                        <option value="${i.id}">${i.companyName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>

                </form>
            </div>
            <div class="cart-summay-container">
                <div class="cart-check-all">
                </div>
                <div style="flex: 1"></div>
                <div style="color: red; margin-right: 30px; font-weight: bold;">${requestScope.message}</div>
                <div class="cart-summay-total">
                    <div>
                        <div class="cart-summary-number-items">Tổng thanh toán (${requestScope.cart.getTotalProduct()} sản phẩm):</div>
                        <div class="cart-summary-total-price"><fmt:formatNumber value="${requestScope.cart.getTotalMoney()}" type="currency" currencySymbol="đ"/></div>
                    </div>
                </div>
                <button class="cart-summary-action2" onclick="checkOut()">Mua hàng</button>
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
