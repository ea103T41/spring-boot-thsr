<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>THSR Search</title>
<link rel="stylesheet" href="css/select_page.css">
</head>
<body>
	<div class="page-body">
    	<jsp:include page="nav-items.jsp" />

    	<div class="content-wrap" id="query-board">
            <jsp:include page="query-wrap.jsp"/>

            <c:if test="${not empty errorMessage}">
               <div class="content-wrap" id="query-result">
                   <h2 class="table-title">查詢結果</h2>
                   <div>${errorMessage}</div>
               </div>
            </c:if>
        </div>
	</div>

</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.js"></script>
<script type="text/javascript" src="js/query-station.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#query-date").val(getDate());
		$("#query-time").val(getTime());
		$(".query-button").click(registerQuery);
	});
</script>
</html>