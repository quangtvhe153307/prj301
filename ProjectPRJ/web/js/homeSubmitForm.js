/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function submitForms() {
//   product?categoryId=1&brandFilter=Acer&brandFilter=Dell&from=0.0&to=9.299E7
    var check = document.getElementsByClassName("checkBrand");
    var cateSelected = document.getElementById("cateSelected");
    var from = document.getElementById("from");
    var to = document.getElementById("to");
    var select = document.getElementById("sortOption").value;
    var key = document.getElementById("key").value;
    var action = "productbycategory?";
    action += "categoryId=" + cateSelected.value;
    for (var i = 0; i < check.length; i++) {
        if (check[i].checked) {

            action += "&brandFilter=" + check[i].value;
        }
    }
    action += "&from=" + from.value;
    action += "&to=" + to.value;
    action += "&sortBy=" + select;
    action += "&key=" + key;
    window.location.replace(action);
}
function submitFormsSearchProduct() {
    var check = document.getElementsByClassName("checkCategory");
    var from = document.getElementById("from");
    var to = document.getElementById("to");
    var select = document.getElementById("sortOption").value;
    var key = document.getElementById("searchKey").value;
    var action = "search?";
    action += "key=" + key;
    for (var i = 0; i < check.length; i++) {
        if (check[i].checked) {

            action += "&categoryFilter=" + check[i].value;
        }
    }
    action += "&from=" + from.value;
    action += "&to=" + to.value;
    action += "&sortBy=" + select;
    window.location.replace(action);
}

function submitFormsSearchProductPreviousPage() {
    var check = document.getElementsByClassName("checkCategory");
    var from = document.getElementById("from");
    var to = document.getElementById("to");
    var select = document.getElementById("sortOption").value;
    var action = "search?";
    var key = document.getElementById("searchKey").value;
    action += "key=" + key;
    for (var i = 0; i < check.length; i++) {
        if (check[i].checked) {

            action += "&categoryFilter=" + check[i].value;
        }
    }
    action += "&from=" + from.value;
    action += "&to=" + to.value;
    action += "&sortBy=" + select;
    var currenPage = document.getElementById("current-page").innerHTML;
    var nextPage = parseInt(currenPage) - 1;
    action += "&page=" + nextPage;
    window.location.replace(action);
}
function submitFormsSearchProductNextPage() {
    var check = document.getElementsByClassName("checkCategory");
    var from = document.getElementById("from");
    var to = document.getElementById("to");
    var select = document.getElementById("sortOption").value;
    var action = "search?";
    var key = document.getElementById("searchKey").value;
    action += "key=" + key;
    for (var i = 0; i < check.length; i++) {
        if (check[i].checked) {

            action += "&categoryFilter=" + check[i].value;
        }
    }
    action += "&from=" + from.value;
    action += "&to=" + to.value;
    action += "&sortBy=" + select;
    var currenPage = document.getElementById("current-page").innerHTML;
    var nextPage = parseInt(currenPage) + 1;
    action += "&page=" + nextPage;
    window.location.replace(action);
}
function submitToPreviousPage() {
    var check = document.getElementsByClassName("checkBrand");
    var cateSelected = document.getElementById("cateSelected");
    var from = document.getElementById("from");
    var to = document.getElementById("to");
    var select = document.getElementById("sortOption").value;
    var action = "productbycategory?";
    var key = document.getElementById("key").value;
    action += "categoryId=" + cateSelected.value;
    for (var i = 0; i < check.length; i++) {
        if (check[i].checked) {

            action += "&brandFilter=" + check[i].value;
        }
    }
    action += "&from=" + from.value;
    action += "&to=" + to.value;
    action += "&sortBy=" + select;
    action += "&key=" + key;
    var currenPage = document.getElementById("current-page").innerHTML;
    var nextPage = parseInt(currenPage) - 1;
    action += "&page=" + nextPage;
    window.location.replace(action);
}
function submitToNextPage() {

    var check = document.getElementsByClassName("checkBrand");
    var cateSelected = document.getElementById("cateSelected");
    var from = document.getElementById("from");
    var to = document.getElementById("to");
    var select = document.getElementById("sortOption").value;
    var key = document.getElementById("key").value;
    var action = "productbycategory?";
    action += "categoryId=" + cateSelected.value;
    for (var i = 0; i < check.length; i++) {
        if (check[i].checked) {

            action += "&brandFilter=" + check[i].value;
        }
    }
    action += "&from=" + from.value;
    action += "&to=" + to.value;
    action += "&sortBy=" + select;
    action += "&key=" + key;
    var currenPage = document.getElementById("current-page").innerHTML;
    var nextPage = parseInt(currenPage) + 1;
    action += "&page=" + nextPage;
    window.location.replace(action);
//    document.getElementById("quangtran").innerHTML = "quang tran";
}

function nextFeedbackPage() {
    var productid = document.getElementById("productID").value;
    var currentPage = document.getElementById("current-page").innerHTML;
    var nextPage = parseInt(currentPage) + 1;
    var action = "product?productid=";
    action += productid;
    action += "&commentPage=" + nextPage;
    action += "#product-rating";
//    window.location.replace(action);
    window.location.href = action;
//    window.location.href = "#product-rating";
//    window.location.href = "#product-rating";
}
function previousFeedbackPage() {
    var productid = document.getElementById("productID").value;
    var currentPage = document.getElementById("current-page").innerHTML;
    var nextPage = parseInt(currentPage) - 1;
    var action = "product?productid=";
    action += productid;
    action += "&commentPage=" + nextPage;
    action += "#product-rating";
    window.location.href = action;
//    window.location.href = "#product-rating";
}
function addQuan() {
    var quan_raw = document.getElementById("quantity").value;
    var quan = parseInt(quan_raw);
    var max = document.getElementById("quantity").getAttribute("max");
    if (quan < parseInt(max)) {
        quan += 1;
    }
    document.getElementById("quantity").value = quan;
}
function subtractQuan() {
    var quan_raw = document.getElementById("quantity").value;
    var quan = parseInt(quan_raw);
    var max = document.getElementById("quantity").getAttribute("max");
    if (quan > 1) {
        quan -= 1;
    }
    document.getElementById("quantity").value = quan;
}
function quan() {
    var quan_raw = document.getElementById("quantity").value;
    var quan = parseInt(quan_raw);
    var max = document.getElementById("quantity").getAttribute("max");
    if (quan > parseInt(max)) {
        document.getElementById("quantity").value = parseInt(max);
    } else if (quan < 1) {
        document.getElementById("quantity").value = 1;
    }
}
function addToCart() {
    var productId = document.getElementById("productID").value;
    var quan = document.getElementById("quantity").value;
    var action = "addtocart?num=";
    action += quan;
    action += "&id=" + productId;
    window.location.href = action;
}
function buyNow(id) {
    var num = document.getElementById("quantity").value;
    document.getElementById("buyNow").action = "addtocart?num=" + num + "&id=" + id;
    document.getElementById("buyNow").submit();
}
function checkOut() {
    var f = document.getElementById("checkOutForm");
    f.submit();
}
function deleteProduct(id) {
    if (confirm("Do you want to delete product with id = " + id + "?")) {
        window.location = "deleteproduct?productid=" + id;
    }
}
