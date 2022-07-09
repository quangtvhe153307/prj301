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

        <div class="page-product">
            <h2 style="margin: 0 0 50px 15%;">Update product</h2>
            <div class="main-content flex">
                <div class="items-image flex flex-column">
                    <div class="main-img">
                        <img src="${requestScope.product.image}">
                    </div>
                    <div class="img-list">

                    </div>
                </div>
                <form style="padding: 30px;" action="updateproductadmin" method="post">
                    <div class="update-product-container">
                        <div class="items-des flex flex-column flex-auto">
                            <div class="flex update-product-row">
                                <div class="flex update-product-id">
                                    <div>ProductID </div><input type="text" value="${requestScope.product.productID}" readonly class="form-control-plaintext" name="productid"/>
                                </div>
                                <div class="flex update-product-category"> 
                                    <div>CategoryID </div><input type="text" value="${requestScope.product.categoryID}" readonly class="form-control-plaintext" name="categoryid"/>
                                </div>
                            </div>
                            <div class="flex update-product-row update-product-name">
                                <div>Name </div><input type="text" value="${requestScope.product.name}" name="name"/>
                            </div>
                            <div class="flex update-product-row">
                                <div class="flex update-product-price">
                                    <div>
                                        UnitPrice
                                    </div>
                                    <input type="number" value="<fmt:formatNumber value="${requestScope.product.price}" type="number" pattern="#"/>" name="price"/></div>
                                <div class="flex update-product-quantityperunit">
                                    <div>Quantity per unit</div><input type="text" value="${requestScope.product.quantityPerUnit}" name="quantityPerUnit"/>
                                </div>
                            </div>
                            <div class="flex update-product-row update-product-image">
                                <div>Image </div><input type="text" value="${requestScope.product.image}" name="img"/>
                            </div>
                            <div class="flex update-product-row">
                                <div class="flex update-product-stock">
                                    <div>Units in Stock</div>
                                    <input type="number" value="${requestScope.product.unitsInStock}" name="unitsInStock"/>
                                </div>
                                <div class="flex update-product-brand">
                                    <div>Brand</div>
                                    <input type="text" value="${requestScope.product.brand}" name="brand"/>
                                </div>
                            </div>

                        </div>
                    </div>
                    <input type="submit" value="Save change" id="updateProductSubmit"/><span style="color: red;
                                                                                             margin-left: 20px;
                                                                                             font-weight: bold;">${requestScope.message}</span>
                </form>


            </div>          

        </div>

        <script src="js/homeSubmitForm.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    </body>
</html>
