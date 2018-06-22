<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: VO VAN NHAN
  Date: 6/21/2018
  Time: 9:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jQuery</title>
    <script>
        var arr =[3,4,2,5,3];
        var temp = [];
        <c:if test="$(not empty listDemo)">
            <c:forEach var="item" items="${listDemo}" >
                temp.push('${item.name}');
            </c:forEach>
        </c:if>
        $(document).ready(function () {
            $.each(temp, function (index,name) {
                console.log("Name: " + name + " - Address: "+index);
            });
            $.each(arr,function(index,value){
                console.log("value: " +value + " - positon: "+index);
            });
        })
    </script>
</head>
<body>
<%--jquery change method--%>

</body>
</html>
