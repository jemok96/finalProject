/**
 * 
 */
$(function(){
	

 // 진행상태 변경
	$(document).on("click","#todoTable > tbody > tr > td > div > div > option",function(){
		
		var tstatus = $(this).text();
		var tno = $(this).val();
		
		$.ajax({
			url : '/todo/statusupdate',
			type : 'post',
			data : {
				tstatus : tstatus,
				tno : tno
			},
			success : function(list) { 
				var data = "";
				for(var i =0; i < list.length; i++){
					
					
					data += "<tr>";
					data += "<td><a href='/club/"+list[i].clubdto.clno+"/document/list'>"+list[i].clubdto.clname+"</a></td>";
					data += "<td>";
					data += "<div class='dropdown mb-4'>";
					data += "<button class='btn btn-success dropdown-toggle' type='button'";
				    data += " data-toggle='dropdown' aria-haspopup='true'";
				    data += "aria-expanded='false'>"+list[i].tododto.tstatus+"</button>";
				    data += "<div class='dropdown-menu animated--fade-in'";
				    data += "aria-labelledby='dropdownMenuButton' >";
					
					data += "<option class='dropdown-item' value='"+list[i].tododto.tno+"' >일시중지</option>";
					data += "<option class='dropdown-item' value='"+list[i].tododto.tno+"' >진행중</option>";
					data += "<option class='dropdown-item' value='"+list[i].tododto.tno+"' >완료</option>";
				    data += "</div>";
				    data += "</div>";
					data += "</td>";
			
					data += "<th scope='row'>"+list[i].tododto.tname+"</th>";
					data += "<td>"+list[i].tododto.tstart_dt+"</td>";
					data += "<td>"+list[i].tododto.tend_dt+"</td>";
					data += "</tr>";
					
					
				}
				
				$("#todolistBody").html(data);
				console.log("성공")		
			},
			error : function() {
				alert("에러입니다.");
			}
		});
		
			
	})
	
})