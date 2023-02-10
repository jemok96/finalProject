


$(function(){
	$("#dropdownWrite a").on("click",function(){
		var dropvalue = $(this).text();
		
		$("#dropdownMenuButton").text(dropvalue);
		$("#hidestatus").val(dropvalue);
			
	})
	
	$("#todostatusMenu a").on("click",function(){
		var dropvalue = $(this).text();
		
		$("#todoMenuButton").text(dropvalue);
		$("#hidetodostatus").val(dropvalue);
		
			
	})
	
	// modify 담당자 수정
/*	$("#modifytodoTable span").on("click",function(){*/
	$(document).on("click","#modifytodoTable > tbody > tr > th > div > div > option",function(){
		
		
		var cno = $(this).val();
		var tno = $(this).attr("value2");
		var dno = $("#modihidedno").val();
		
		
		$.ajax({
			url : '../todo/modifymanager',
			type : 'post',
			data : {
				cno : cno,
				dno : dno,
				tno : tno
			},
			success : function(list) { 
				
				var data = "";
				data += "<table class='table' id='modifytodoTable' width='100%' cellspacing='0'>";
				for(var i =0; i < list.todoList.length; i++){
					data += "<tr>";
					data += "<th>";
					data += "<div class='dropdown mb-4'>";
					data += "<button class='btn btn-light dropdown-toggle' type='button'";
				    data += " data-toggle='dropdown' aria-haspopup='true'";
				    data += "aria-expanded='false'>"+list.todoList[i].name+"</button>";
				    data += "<div class='dropdown-menu animated--fade-in'";
				    data += "aria-labelledby='dropdownMenuButton' >";
					for(var j = 0; j < list.cmlist.length; j++){ 
					    data += "<option class='dropdown-item' value='"+list.cmlist[j].cldto.cno+"' value2='"+list.todoList[i].tododto.tno+"'>"+list.cmlist[j].name+"</option>";
					}
	             	//data += "<input type='hidden' value='"+list.todoList[i].tododto.tno+"' />"
				    data += "</div>";
				    data += "</div>";
					data += "</th>";
					data += "<td width='185px'>";
					data += "<input type='text' class='form-control form-control' value='"+list.todoList[i].tododto.tname+"' />"
					data += "</td>";
					data +=	"<td>"
                	data += "<a href='#' class='btn btn-success btn-circle btn-sm'>"
                	data += "<input type='hidden' class='modi' value='"+list.todoList[i].tododto.tno+"' />"
                    data += "<i class='fas fa-check'></i>"
                    data += "</a>"
                	data += "</td>"
					data += "<td>";
					data += "<div class='dropdown mb-4'>"
					data +=	"<button class='btn btn-primary dropdown-toggle' type='button'"
					data += " data-toggle='dropdown' aria-haspopup='true'"
					data += "aria-expanded='false'>"+list.todoList[i].tododto.tstatus+"</button>";
					data += "<div class='dropdown-menu animated--fade-in'"
					data += "aria-labelledby='dropdownMenuButton'>"
					data += "<option class='dropdown-item' value='"+list.todoList[i].tododto.tno+"' >일시중지</option>"
					data += "<option class='dropdown-item' value='"+list.todoList[i].tododto.tno+"'>진행중</option>"
					data += "<option class='dropdown-item' value='"+list.todoList[i].tododto.tno+"'>완료</option>"
					data += "</div>";
					data += "</div>";
					data += "</td>";
					data += "<td><span class='btn btn-danger btn-circle btn-sm' value='"+list.todoList[i].tododto.tno+"'>";
					data += "<input type='hidden' value='"+list.todoList[i].tododto.tno+"'/>";
					data += "<i class='fas fa-trash'></i></span></td>";
					
					data += "</tr>";
				}
				data += "</table>";
				
				$("#modifyDiv").html(data);
										
			},
			error : function() {
				alert("에러입니다.");
			}
		});
		
			
	})
	
	// 진행상태 변경
	$(document).on("click","#modifytodoTable > tbody > tr > td > div > div > option",function(){
		
		//console.log("클릭");
		/*var cno = $(this).val();
		var tno = $(this).attr("value2");*/
		var dno = $("#modihidedno").val();
		var tstatus = $(this).text();
		var tno = $(this).val();
		console.log("tstatus"+tstatus);
		console.log("tno"+tno);
		console.log("dno"+dno);
		
		
		
		
		$.ajax({
			url : '../todo/modifystatus',
			type : 'post',
			data : {
				tstatus : tstatus,
				dno : dno,
				tno : tno
			},
			success : function(list) { 
				var data = "";
				data += "<table class='table' id='modifytodoTable' width='100%' cellspacing='0'>";
				for(var i =0; i < list.todoList.length; i++){
					data += "<tr>";
					data += "<th>";
					data += "<div class='dropdown mb-4'>";
					data += "<button class='btn btn-light dropdown-toggle' type='button'";
				    data += " data-toggle='dropdown' aria-haspopup='true'";
				    data += "aria-expanded='false'>"+list.todoList[i].name+"</button>";
				    data += "<div class='dropdown-menu animated--fade-in'";
				    data += "aria-labelledby='dropdownMenuButton' >";
					for(var j = 0; j < list.cmlist.length; j++){ 
					    data += "<option class='dropdown-item' value='"+list.cmlist[j].cldto.cno+"' value2='"+list.todoList[i].tododto.tno+"'>"+list.cmlist[j].name+"</option>";
					}
				    data += "</div>";
				    data += "</div>";
					data += "</th>";
					data += "<td width='185px'>";
					data += "<input type='text' class='form-control form-control' value='"+list.todoList[i].tododto.tname+"' />"
					data += "</td>";
					
					
					data +=	"<td>"
                	data += "<a href='#' class='btn btn-success btn-circle btn-sm'>"
                	data += "<input type='hidden' class='modi' value='"+list.todoList[i].tododto.tno+"' />"
                    data += "<i class='fas fa-check'></i>"
                    data += "</a>"
                	data += "</td>"
					data += "<td>";
					data += "<div class='dropdown mb-4'>"
					data +=	"<button class='btn btn-primary dropdown-toggle' type='button'"
					data += " data-toggle='dropdown' aria-haspopup='true'"
					data += "aria-expanded='false'>"+list.todoList[i].tododto.tstatus+"</button>";
					data += "<div class='dropdown-menu animated--fade-in'"
					data += "aria-labelledby='dropdownMenuButton'>"
					data += "<option class='dropdown-item' value='"+list.todoList[i].tododto.tno+"' >일시중지</option>"
					data += "<option class='dropdown-item' value='"+list.todoList[i].tododto.tno+"'>진행중</option>"
					data += "<option class='dropdown-item' value='"+list.todoList[i].tododto.tno+"'>완료</option>"
					data += "</div>"
					data += "</div>"         
					data += "</td>"
					data += "<td><span class='btn btn-danger btn-circle btn-sm' value='"+list.todoList[i].tododto.tno+"'>";
					data += "<input type='hidden' value='"+list.todoList[i].tododto.tno+"'/>";
					data += "<i class='fas fa-trash'></i></span></td>";
					data += "</tr>";
				}
				data += "</table>";
				
				$("#modifyDiv").html(data);
				console.log("성공")		
			},
			error : function() {
				alert("에러입니다.");
			}
		});
		
			
	})
	
	
	// 할일 내용 변경
	$(document).on("click","#modifytodoTable > tbody > tr > td > a",function(){
		
		
		var dno = $("#modihidedno").val();
		var tno = $(this).find(".modi").val(); 
		var tname = $(this).parent().prev().find("input").val(); 
		
		
		
		
		
		$.ajax({
			url : '../todo/modifytname',
			type : 'post',
			data : {
				tname : tname,
				dno : dno,
				tno : tno
			},
			success : function(list) { 
				var data = "";
				data += "<table class='table' id='modifytodoTable' width='100%' cellspacing='0'>";
				for(var i =0; i < list.todoList.length; i++){
					data += "<tr>";
					data += "<th>";
					data += "<div class='dropdown mb-4'>";
					data += "<button class='btn btn-light dropdown-toggle' type='button'";
				    data += " data-toggle='dropdown' aria-haspopup='true'";
				    data += "aria-expanded='false'>"+list.todoList[i].name+"</button>";
				    data += "<div class='dropdown-menu animated--fade-in'";
				    data += "aria-labelledby='dropdownMenuButton' >";
					for(var j = 0; j < list.cmlist.length; j++){ 
					    data += "<option class='dropdown-item' value='"+list.cmlist[j].cldto.cno+"' value2='"+list.todoList[i].tododto.tno+"'>"+list.cmlist[j].name+"</option>";
					}
				    data += "</div>";
				    data += "</div>";
					data += "</th>";
					
					data += "<td width='185px'>";
					data += "<input type='text' class='form-control form-control' value='"+list.todoList[i].tododto.tname+"' />"
					data += "</td>";
					
					data +=	"<td>"
                	data += "<a href='#' class='btn btn-success btn-circle btn-sm'>"
                	data += "<input type='hidden' class='modi' value='"+list.todoList[i].tododto.tno+"' />"
                    data += "<i class='fas fa-check'></i>"
                    data += "</a>"
                	data += "</td>"
					
					
					data += "<td>";
					data += "<div class='dropdown mb-4'>"
					data +=	"<button class='btn btn-primary dropdown-toggle' type='button'"
					data += " data-toggle='dropdown' aria-haspopup='true'"
					data += "aria-expanded='false'>"+list.todoList[i].tododto.tstatus+"</button>";
					data += "<div class='dropdown-menu animated--fade-in'"
					data += "aria-labelledby='dropdownMenuButton'>"
					data += "<option class='dropdown-item' value='"+list.todoList[i].tododto.tno+"' >일시중지</option>"
					data += "<option class='dropdown-item' value='"+list.todoList[i].tododto.tno+"'>진행중</option>"
					data += "<option class='dropdown-item' value='"+list.todoList[i].tododto.tno+"'>완료</option>"
					data += "</div>"
					data += "</div>"         
					data += "</td>"
					data += "<td><span class='btn btn-danger btn-circle btn-sm' value='"+list.todoList[i].tododto.tno+"'>";
					data += "<input type='hidden' value='"+list.todoList[i].tododto.tno+"'/>";
					data += "<i class='fas fa-trash'></i></span></td>";
					data += "</tr>";
				}
				data += "</table>";
				
				$("#modifyDiv").html(data);
				console.log("성공")		
			},
			error : function() {
				alert("에러입니다.");
			}
		});
		
			
	})
	
	
	
	$("#dropdownMember option").on("click",function(){
		var droptext = $(this).text();
		var dropvalue = $(this).val();
		
		$("#MemberSelect").text(droptext);
		$("#hidecno").val(dropvalue);
		
			
	})
	
	$('#startDay').datepicker({
		dateFormat: 'yy-mm-dd'
		,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
        ,showMonthAfterYear:true //년도 먼저 나오고, 뒤에 월 표시
        ,buttonText: "선택" //버튼에 마우스 갖다 댔을 때 표시되는 텍스트                
        ,yearSuffix: "년" //달력의 년도 부분 뒤에 붙는 텍스트
        ,monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'] //달력의 월 부분 텍스트
        ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip 텍스트
        ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 부분 텍스트
        ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 부분 Tooltip 텍스트
        ,minDate: "-1M" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
        ,maxDate: "+1Y" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)   
	});
	
	$('#endDay').datepicker({
		dateFormat: 'yy-mm-dd'
		,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
        ,showMonthAfterYear:true //년도 먼저 나오고, 뒤에 월 표시
        ,buttonText: "선택" //버튼에 마우스 갖다 댔을 때 표시되는 텍스트                
        ,yearSuffix: "년" //달력의 년도 부분 뒤에 붙는 텍스트
        ,monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'] //달력의 월 부분 텍스트
        ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip 텍스트
        ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 부분 텍스트
        ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 부분 Tooltip 텍스트
        ,minDate: "-1M" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
        ,maxDate: "+1Y" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)   
	});
	// 수정 폼에서 할 일 작성
	$("#modifyTodo").on("click",function(){
		//console.log("클릭");
		var aJson = new Object();
		
		var tstatus =$("#todoMenuButton").text();
		var end_dt =$("#startDay").val();
		var start_dt = $("#endDay").val();
		var clubMember =$("#MemberSelect").text();
		var tname = $("#tname").val();
		var cno = $("#hidecno").val();
		var dno = $("#modihidedno").val();
		
		aJson.tstatus = tstatus;
		aJson.end_dt = end_dt;
		aJson.start_dt = start_dt;
		aJson.clubMember = clubMember;
		aJson.tname = tname;
		aJson.cno = cno;
		aJson.cnt = cnt;
		cnt++;
		
		var ajson = JSON.stringify(aJson);
		
		$.ajax({
				url : '../todo/modifytodo',
				type : 'post',
				data : {
					ajson : ajson,
					dno : dno
				},
				success : function(list) {  
					/*console.log("성공");*/
					var data = "";
					data += "<table class='table' id='modifytodoTable' width='100%' cellspacing='0'>"
					for(var i =0; i < list.todoList.length; i++){
						data += "<tr>";
					data += "<th>";
					data += "<div class='dropdown mb-4'>";
					data += "<button class='btn btn-light dropdown-toggle' type='button'";
				    data += " data-toggle='dropdown' aria-haspopup='true'";
				    data += "aria-expanded='false'>"+list.todoList[i].name+"</button>";
				    data += "<div class='dropdown-menu animated--fade-in'";
				    data += "aria-labelledby='dropdownMenuButton' >";
					for(var j = 0; j < list.cmlist.length; j++){ 
					    data += "<option class='dropdown-item' value='"+list.cmlist[j].cldto.cno+"' value2='"+list.todoList[i].tododto.tno+"'>"+list.cmlist[j].name+"</option>";
					}
				    data += "</div>";
				    data += "</div>";
					data += "</th>";
					
					data += "<td width='185px'>";
					data += "<input type='text' class='form-control form-control' value='"+list.todoList[i].tododto.tname+"' />"
					data += "</td>";
					
					data +=	"<td>"
                	data += "<a href='#' class='btn btn-success btn-circle btn-sm'>"
                	data += "<input type='hidden' class='modi' value='"+list.todoList[i].tododto.tno+"' />"
                    data += "<i class='fas fa-check'></i>"
                    data += "</a>"
                	data += "</td>"
					
					
					data += "<td>";
					data += "<div class='dropdown mb-4'>"
					data +=	"<button class='btn btn-primary dropdown-toggle' type='button'"
					data += " data-toggle='dropdown' aria-haspopup='true'"
					data += "aria-expanded='false'>"+list.todoList[i].tododto.tstatus+"</button>";
					data += "<div class='dropdown-menu animated--fade-in'"
					data += "aria-labelledby='dropdownMenuButton'>"
					data += "<option class='dropdown-item' value='"+list.todoList[i].tododto.tno+"' >일시중지</option>"
					data += "<option class='dropdown-item' value='"+list.todoList[i].tododto.tno+"'>진행중</option>"
					data += "<option class='dropdown-item' value='"+list.todoList[i].tododto.tno+"'>완료</option>"
					data += "</div>"
					data += "</div>"         
					data += "</td>"
					
					data += "<td><span class='btn btn-danger btn-circle btn-sm' value='"+list.todoList[i].tododto.tno+"'>";
					data += "<input type='hidden' value='"+list.todoList[i].tododto.tno+"'/>";
					data += "<i class='fas fa-trash'></i></span></td>";
					data += "</tr>";
					}
					data += "</table>"
					
					$("#modifyDiv").html(data);
											
				},
				error : function() {
					alert("에러입니다.");
				}
			});
		

		
	})
		var aJsonArray = new Array();
		var cnt = 0;
	$("#writeTodo").on("click",function(){
		//console.log("클릭");
		var aJson = new Object();
		
		var tstatus =$("#todoMenuButton").text();
		var end_dt =$("#startDay").val();
		var start_dt = $("#endDay").val();
		var clubMember =$("#MemberSelect").text();
		var tname = $("#tname").val();
		var cno = $("#hidecno").val();
		
		
		aJson.tstatus = tstatus;
		aJson.end_dt = end_dt;
		aJson.start_dt = start_dt;
		aJson.clubMember = clubMember;
		aJson.tname = tname;
		aJson.cno = cno;
		aJson.cnt = cnt;
		cnt++;
		aJsonArray.push(aJson);
		
//		var data = $("#todobody").html();
		var data = $("#todobody table").html();
		var todolist = "<tr><td>"+ clubMember+
						"</td><td width='177px'>"+ aJson.tname+
						"</td><td>"+aJson.tstatus+
						"</td><td>"+
						"<span class='btn btn-danger btn-circle btn-sm'><i class='fas fa-trash'></i>"+
						"<input type='hidden'  value='"+aJson.cnt+"' />"+"</span >"+
						"</td></tr>";
		
		
		$("#todobody table").html(data + todolist);
		
		$("#ajson").val(JSON.stringify(aJsonArray));
		
		$("#tname").val("");
		$("#startDay").val("");
		$("#endDay").val("");
		$("#todoMenuButton").text("진행중");
		//console.log(data);
		//console.log(aJsonArray);
		//console.log($("#ajson").val());
	})
	
	$(document).on("click","#writeTable > tr > td:nth-child(4) > span",function(){
		console.log("시작");
		var jsonIndex = $(this).find("input").val();
		aJsonArray.splice(jsonIndex,1);
		var todolist = "";
		cnt = 0;
		for(var i = 0; i < aJsonArray.length; i++){
			/*var a = aJsonArray[i].clubMember;
			console.log(a);*/
			console.log(aJsonArray.length);
			aJsonArray[i].cnt = cnt;
			todolist += "<tr>";
			todolist += "<td>"+ aJsonArray[i].clubMember+"</td>";
			todolist += "<td width='177px'>"+ aJsonArray[i].tname+"</td>";
			todolist += "<td>"+aJsonArray[i].tstatus+ "</td>";
			todolist += "<td><span class='btn btn-danger btn-circle btn-sm'><i class='fas fa-trash'></i>"+
						"<input type='hidden' value='"+aJsonArray[i].cnt+"' /></span ></td>";
			todolist += "</tr>";
			cnt ++;
		}
		$("#writeTable").html(todolist);
		
		$("#ajson").val(JSON.stringify(aJsonArray));
		
		
		console.log(JSON.stringify(aJsonArray));
	})
	$(document).on("click","#detailTodoList button",function(){
		
//		console.log("클릭	");
		var tno = $(this).val();
//		console.log("tno:" + tno);
		
		
		var dno = $("#hidedno").val();
//		console.log("dno:" + dno);
		
		$.ajax({
					url : '../todo/deletetodo',
					type : 'post',
					data : {
						tno : tno,
						dno : dno
					},
					success : function(list) {  
						/*console.log("성공");*/
						var data = "";
						data += "<table class='table' id='dtailtodoTable' width='100%' cellspacing='0'>"
						for(var i =0; i < list.length; i++){
							data += "<tr>"
							data += "<th>"+list[i].name+"</th>"
							data += "<td width='185px'>"+list[i].tododto.tname+"</td>"
							data += "<td>"+list[i].tododto.tstatus+"</td>"
							data += "<td><button class='btn btn-danger btn-circle btn-sm' value='"+list[i].tododto.tno+"'><i class='fas fa-trash'></i></button></td>"
							data += "</tr>"
						}
						data += "</table>"
						
						$("#detailTodoList").html(data);
												
					},
					error : function() {
						alert("에러입니다.");
					}
				});
	})
	
	// 수정폼에서 삭제
	$(document).on("click","#modifytodoTable > tbody > tr > td > span",function(){
		
//		console.log("클릭	");
		var tno = $(this).find("input").val();
		console.log("tno:" + tno);
		
		var dno = $("#modihidedno").val();
		console.log("dno:" + dno);
		
		$.ajax({
					url : '../todo/modifydelete',
					type : 'post',
					data : {
						tno : tno,
						dno : dno
					},
					success : function(list) {  
						/*console.log("성공");*/
						var data = "";
						data += "<table class='table' id='modifytodoTable' width='100%' cellspacing='0'>";
						for(var i =0; i < list.todoList.length; i++){
							data += "<tr>";
							data += "<th>";
							data += "<div class='dropdown mb-4'>";
							data += "<button class='btn btn-light dropdown-toggle' type='button'";
						    data += " data-toggle='dropdown' aria-haspopup='true'";
						    data += "aria-expanded='false'>"+list.todoList[i].name+"</button>";
						    data += "<div class='dropdown-menu animated--fade-in'";
						    data += "aria-labelledby='dropdownMenuButton' >";
							for(var j = 0; j < list.cmlist.length; j++){ 
							    data += "<option class='dropdown-item' value='"+list.cmlist[j].cldto.cno+"' value2='"+list.todoList[i].tododto.tno+"'>"+list.cmlist[j].name+"</option>";
							}
						    data += "</div>";
						    data += "</div>";
							data += "</th>";
							data += "<td width='185px'>";
							data += "<input type='text' class='form-control form-control' value='"+list.todoList[i].tododto.tname+"' />"
							data += "</td>";
							
							
							data +=	"<td>"
		                	data += "<a href='#' class='btn btn-success btn-circle btn-sm'>"
		                	data += "<input type='hidden' class='modi' value='"+list.todoList[i].tododto.tno+"' />"
		                    data += "<i class='fas fa-check'></i>"
		                    data += "</a>"
		                	data += "</td>"
							data += "<td>";
							data += "<div class='dropdown mb-4'>"
							data +=	"<button class='btn btn-primary dropdown-toggle' type='button'"
							data += " data-toggle='dropdown' aria-haspopup='true'"
							data += "aria-expanded='false'>"+list.todoList[i].tododto.tstatus+"</button>";
							data += "<div class='dropdown-menu animated--fade-in'"
							data += "aria-labelledby='dropdownMenuButton'>"
							data += "<option class='dropdown-item' value='"+list.todoList[i].tododto.tno+"' >일시중지</option>"
							data += "<option class='dropdown-item' value='"+list.todoList[i].tododto.tno+"'>진행중</option>"
							data += "<option class='dropdown-item' value='"+list.todoList[i].tododto.tno+"'>완료</option>"
							data += "</div>"
							data += "</div>"         
							data += "</td>"
							
							data += "<td><span class='btn btn-danger btn-circle btn-sm' value='"+list.todoList[i].tododto.tno+"'>";
							data += "<input type='hidden' value='"+list.todoList[i].tododto.tno+"'/>";
							data += "<i class='fas fa-trash'></i></span></td>";
							data += "</tr>";
						}
						data += "</table>";
						
						$("#modifyDiv").html(data);
												
					},
					error : function() {
						alert("에러입니다.");
					}
				});
	})
		
	
	
})