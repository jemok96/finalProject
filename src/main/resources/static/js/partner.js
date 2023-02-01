var check = 0; 

function isExists(){ 	
		var partnerEmail = $("#searchPartner").val().trim();
		console.log("버튼");
		if( partnerEmail == ''){
			alert("추가 파트너 이메일을 입력하십시오"); 
			$("#searchPartner").focus();
			return false;
		}else {
			$.ajax({
				url : '../partner/partnerCK',
					type : 'post',
					data : {
						"partnerEmail" : partnerEmail 
					},
				success : function(result){ 
					if ( result == 1 ){
						check = 1; 
						$("#idCk").html("파트너 추가 가능합니다.").css('color','green'); 
					} else if (result ==2 ){
						check = 2; 					
						$("#idCk").html("내 파트너에 이미 추가된 회원입니다.").css('color','red'); 
					} else if (result == 3){
						check = 3; 
						$("#idCk").html("해당 회원이 존재하지않습니다.").css('color','red');
					} else {
						check = 4; 
						$("#idCk").html("자기자신은 파트너 추가가 불가합니다.").css('color','purple');
					}
				},
				error : function(){
					alert("서버요청실패");
				}
			})			
		}		
	}
	
function partnerIsnert(){
	var partnerEmail = $("#searchPartner").val().trim();
	console.log("추가 버튼");
	if (partnerEmail =='' ){
		alert("이메일을 입력하십시오.")
		return false;
	} else if( check == 0 ){
		console.log("검사안함");
		alert("직접 입력 파트너 추가 시 검사를 먼저 진행하십시오.");
		return false; 
	} else if( check == 2 ){
		alert("내 파트너에 이미 추가된 회원입니다.");
		return false; 
	} else if( check == 3 ){
		alert("해당 회원이 존재하지않습니다.");
		return false; 
	} else if( check == 4 ){
		alert("자기자신은 파트너 추가가 불가합니다.");
		return false; 
	} 
	document.frm1.submit(); 
}
	
	