<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>THSR Search</title>
<link rel="stylesheet" href="css/select_page.css">
<link rel="stylesheet" href="css/select_result.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
</head>
</head>
<body>
	<div class="page-body">
        <jsp:include page="nav-items.jsp" />

    	<div class="content-wrap" id="query-board">
        	<jsp:include page="query-wrap.jsp"/>

        	<div class="time-table-wrap" id="time-table-wrap">
            	<h2 class="table-title">查詢結果</h2>
             	<div class="time-nav-head">
		       		<div class="trn-title">
			          	<div class="from">
			            	<c:forEach var="rs" items="${railStations}" >
								<c:if test="${rs.stationId.equals(param.OriginStationID)}">${rs.stationName}</c:if>
							</c:forEach>
						</div>
						<div class="arrow"><span>往</span></div>
			            <div class="to">
			            	<c:forEach var="rs" items="${railStations}" >
								<c:if test="${rs.stationId.equals(param.DestinationStationID)}">${rs.stationName}</c:if>
							</c:forEach>
			            </div>
		        	</div>
		        	<div class="trn-datetime">${param.TrainDate}<span id="trn-datetime-week"></span> ${param.startTime}</div>
		        	<div class="trn-button">
		            	<div id="prevPage">
		                	<i class="material-icons">arrow_back</i>
		                	<span class="trn-page">較早班次</span>
		                </div>
		            	<div title="" id="nextPage">
		                	<span class="trn-page">較晚班次</span>
		                	<i class="material-icons">arrow_forward</i>
		            	</div>
		        	</div>
		    	</div>
           		<div class="time-table">
	            	<div id="time-table-head" class="time-table-head">
	                 	<div class="item-table">出發時間</div>
	                  	<div class="item-table">行車時間</div>
	                  	<div class="item-table">抵達時間</div>
	                   	<div class="item-table">車次</div>
	              	</div>
	               	<div id="time-table-body" class="time-table-body">
	                	<c:forEach var="stInfo" items="${stopTimeInfoList}">
	                		<div class="time-table-panel" data-gtId="${stInfo.gtId}">
		                    	<div class="item-table">
		                    		<span>${stInfo.departureTime}</span>
		                    	</div>
			                 	<div class="item-table">
			                 		<div class="traffic-time">
			                 			<p>${stInfo.tripTime}</p>
			                 		</div>
			                 	</div>
			                  	<div class="item-table">
			                  		<span>${stInfo.arrivalTime}</span>
			                  	</div>
			                 	<div class="item-table">${stInfo.trainNo}</div>
			                 	<div class="item-trip" data-gtId="${stInfo.gtId}">
		                            <div class="station-container">
		                                <ul class="station-ul">
		                                </ul>
		                            </div>
		                        </div>
		                 	</div>
		            	</c:forEach>
	            	</div>
           		</div>
           	</div>
           	<div class="fare-table-wrap" id="fare-table-wrap">
           		<h2 class="table-title">車廂票價</h2>
              	<div class="fare-table">
                 	<div class="fare-table-head" id="fare-table-head">
                 	    <div class="item-table"></div>
                    	<div class="item-table">全票</div>
                    	<div class="item-table">孩童票/敬老票/愛心票</div>
                    	<div class="item-table">團體票</div>
                  	</div>
                  	<c:forEach var="rf" items="${fareInfoList}">
                        <div class="fare-table-body" id="fare-table-body">
                  	        <div class="item-table">${rf.fareName}</div>
                  	        <div class="item-table">${rf.adultPrice}</div>
                            <div class="item-table">${rf.concessionPrice}</div>
                            <div class="item-table">${rf.groupPrice}</div>
                  	    </div>
                  	</c:forEach>
              	</div>
       		</div>
      	</div>
	</div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.js"></script>
<script type="text/javascript" src="js/query-station.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#trn-datetime-week").text(getWeekDay($("#query-date").val()));
		$(".query-button").click(registerQuery);

   		function getWeekDay(date) {
   			const week = new Date(date).getDay();
   			switch (week) {
   		  		case 1: 
   		  			return " (一)";
   		  		case 2: 
		  			return " (二)";
   			 	case 3: 
	  				return " (三)";
   				case 4: 
	  				return " (四)";
   				case 5: 
	  				return " (五)";
   				case 6: 
	  				return " (六)";
   				case 0: 
	  				return " (日)";
   			}
   		}
   		
   		$(".time-table-panel").click(function(){
   			var item_trip = $(this).find('.item-trip');
   			if (item_trip.css("display") == "inline-block") {
   				item_trip.css("display", "none");
   			} else {
   			    searchStopTimeDetail(item_trip, item_trip.attr("data-gtId"));
	   			$(".item-trip").each(function(){
	   				$(this).css("display", "none");  				
	   			});
   				item_trip.css("display", "inline-block");
   			}
   		});
   		
   		function searchStopTimeDetail(item_trip, gtId) {
   			$.ajax({
                type: "POST",
                url: "/thsr/getStopTimeDetail",
                data: {
                	gtId : gtId
                },             	
                dataType: "json",
                success: function (result) {
                	var ul = item_trip.find(".station-ul");
                	ul.empty();
                	
                	$.each(result, function (i, j) {
                		var departTime = j.departureTime;
                		console.log(departTime);
                		if (departTime === null) {
                			departTime = j.arrivalTime;
                		}
                		
                		var li = 
                		`<li class="station-li">
	                    	<div class="station-Name">` + j.stationName + `</div>
	                      	<span class="dot"></span>
	                     	<p class="station-departTime">` + departTime + `</p>
                  		</li>`;
                  		
                		ul.append(li);
                	});
                },
                error: function (xhr, ajaxOptions, thrownError) {
                	console.log("ajax失敗"+xhr.responseText);
                }
   			});
   		}
	});
</script>
</html>