<%-- 
    Document   : dashboard
    Created on : Mar 14, 2022, 9:59:32 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Top Sellers</title>




        <!-- Bootstrap core CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <!-- Font Awesome Icon -->
        <script src="https://kit.fontawesome.com/3c84cb624f.js" crossorigin="anonymous"></script>

        <style>
            .bd-placeholder-img {
                font-size: 1.125rem;
                text-anchor: middle;
                -webkit-user-select: none;
                -moz-user-select: none;
                -ms-user-select: none;
                user-select: none;
            }

            @media (min-width: 768px) {
                .bd-placeholder-img-lg {
                    font-size: 3.5rem;
                }
            }
            .pagination-container{
                margin-top: 30px;
                margin-left: 10%;
            }
            .pagination-container a{
                color: black;
            }
        </style>


        <!-- Custom styles for this template -->
        <link href="css/dashboard.css" rel="stylesheet">
    </head>
    <body>

        <nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
            <a class="navbar-brand col-md-3 col-lg-2 mr-0 px-3" href="home">PRJ Shop</a>
            <button class="navbar-toggler position-absolute d-md-none collapsed" type="button" data-toggle="collapse" data-target="#sidebarMenu" aria-controls="sidebarMenu" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <input class="form-control form-control-dark w-100" type="text" placeholder="Search" aria-label="Search">
            <ul class="navbar-nav px-3">
                <li class="nav-item text-nowrap">
                    <a class="nav-link" href="logout">Sign out</a>
                </li>
            </ul>
        </nav>

        <div class="container-fluid">
            <div class="row">
                <nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
                    <div class="sidebar-sticky pt-3">
                        <ul class="nav flex-column">
                            <li class="nav-item">
                                <a class="nav-link" href="admin">
                                    <span data-feather="home"></span>
                                    Dashboard <span class="sr-only">(current)</span>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="order">
                                    <span data-feather="file"></span>
                                    Orders
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="productmanageadmin">
                                    <span data-feather="shopping-cart"></span>
                                    Products
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="accountmanageadmin">
                                    <span data-feather="users"></span>
                                    Customers
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="topproduct">
                                    <span data-feather="bar-chart-2"></span>
                                    Top 10 products
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active" href="topsellers">
                                    <span data-feather="layers"></span>
                                    Top 5 seller
                                </a>
                            </li>
                        </ul>

                        <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                            <span>Statistic</span>
                            <a class="d-flex align-items-center text-muted" href="#" aria-label="Add a new report">
                                <span data-feather="plus-circle"></span>
                            </a>
                        </h6>
                        <ul class="nav flex-column mb-2">
                            <li class="nav-item">
                                <a class="nav-link" href="weeklystatisticsservlet">
                                    <span data-feather="file-text"></span>
                                    Stats by day of week
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="statsbymonthofyear">
                                    <span data-feather="file-text"></span>
                                    Stats by month of year
                                </a>
                            </li>

                        </ul>
                    </div>
                </nav>

                <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4" style="background: rgba(0,0,0,.05); padding-top: 50px;">

                    <c:if test="${requestScope.data.size() == 0}">
                        <div style="margin: 20px 0 20px 10%; height: 50vh;">There is no product in your shop</div>
                    </c:if>
                    <c:if test="${requestScope.data.size() != 0}">        
                        <div class="table-container"><table class="table table-hover table-bordered caption-top table-responsive bg-light" style="margin: 0 auto; width: 80%">
                                <caption>Top sellers</caption>
                                <thead><tr><th class="col">username</th>
                                        <th class="col">password</th>
                                        <th class="col">contactName</th>
                                        <th class="col">address</th>
                                        <th class="col">city</th>
                                        <th class="col">phone</th>
                                        <th class="col">email</th>
                                        <th class="col">Action</th>

                                    </tr></thead>
                                <tbody>
                                    <c:forEach items="${requestScope.data}" var="i">
                                        <tr>
                                            <td>${i.username}</td>
                                            <td>${i.password}</td>
                                            <td>${i.contactName}</td>
                                            <td>${i.address}</td>
                                            <td>${i.city}</td>
                                            <td>${i.phone}</td>
                                            <td>${i.email}</td>
                                            <td><a href="updateaccountadmin?username=${i.username}" class="userCRUD" style="text-decoration: none">Update</a></td>
                                        </tr>
                                    </c:forEach>
                                </tbody>

                            </table></div></c:if>


                </main>
            </div>
        </div>


        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script>window.jQuery || document.write('<script src="../assets/js/vendor/jquery.slim.min.js"><\/script>')</script> <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>


        <script src="https://cdn.jsdelivr.net/npm/feather-icons@4.28.0/dist/feather.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.4/dist/Chart.min.js"></script>
        <script src="js/dashboard.js"></script>
    </body>
</html>


