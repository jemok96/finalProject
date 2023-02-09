var check = 0;

function partnerInsert() {
	var partnerEmail = $("#searchPartner").val().trim();
	if (partnerEmail == '') {
		alert("추가 파트너 이메일을 입력하십시오");
		$("#searchPartner").focus();
		return false;
	} else {
		$.ajax({
			url: '../partner/partnerCK',
			type: 'post',
			data: {
				"partnerEmail": partnerEmail
			},
			success: function(result) {
				if (result == 1) {
					check = 1;
				} else if (result == 2) {
					check = 2;
					$("#idCk").html("내 파트너에 이미 추가된 회원입니다.").css('color', 'red');
				} else if (result == 3) {
					check = 3;
					$("#idCk").html("해당 회원이 존재하지않습니다.").css('color', 'red');
				} else {
					check = 4;
					$("#idCk").html("자기자신은 파트너 추가가 불가합니다.").css('color', 'purple');
				}
				if (check == 1) {
					document.frm1.submit();
					
				} else {
					return false;
				}

			},
			error: function() {
				alert("서버요청실패");
			}
		})
	}
}

function moveText(e){
	var recEmail = e.innerHTML;
	$("#searchPartner").val(recEmail);
}

function deleteCk(email){
	console.log(email);
	$("#deletePartnerCk").text(email);
}

function deletePartner(){
	var deleteEmail = $("#deletePartnerCk").text();
	console.log(deleteEmail);
	$.ajax({
			url: '../partner/deletePartner',
			type: 'post',
			data: {
				"deleteEmail": deleteEmail
			},
			success: function() {
				console.log("성공");
				location.href = '/partner' 
			},
			error: function() {
				alert("서버요청실패");
			}
		})
}