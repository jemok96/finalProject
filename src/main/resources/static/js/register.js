/**
 * 
 */

 $(function() {
		var code = "";
		
		var ckEmail = false;
		var eConFirm= false;
		var ckName= false;
		var ckNickname= false;
		var ckPw= false;
		var ckTel= false;
		let passwordOk = false;
		
		// 이름 유효성
		$("#name").on("blur", function() {
			var name = $("#name").val();
			console.log("name : " + name);
			if(name != ""){
				ckName = true;
			}else{
				ckName = false;
			}
			
		});
		// 휴대폰 번호 유효성
		$("#tel").on("blur", function() {
			var tel = $("#tel").val();
			console.log("tel : " + tel);
			if(tel != ""){
				ckTel = true;
			}else{
				ckTel = false;
			}
			console.log("ckTel : " + ckTel);
		});
		
		$("#pw").on("keyup", function(){
	    	let pw = $("#pw").val();
	    	let num = pw.search(/[0-9]/g); // 숫자
	        let eng = pw.search(/[a-z]/ig); // 영문
	        let spece = pw.search(/[~!@#$%^&*()_+|<>?:{}`]/gi); // 특수문자
	    	if (pw.length < 8 || pw.length > 20) {
	            $(".ckeckPw").addClass("errorEmail")
	                      .html("8자리 ~ 20자리 이내로 입력해주세요.");
	        }else if(pw.search(/\s/) != -1){
	        	$(".ckeckPw").addClass("errorEmail")
	                      .html("공백없이 입력해주세요.");
	        }else if(num < 0 || eng < 0 || spece < 0){
	        	$(".ckeckPw").addClass("errorEmail")
	                      .html("영문, 숫자, 특수문자를 혼합하여 입력해주세요.");
	        }else{
	        	$(".ckeckPw").addClass("success")
	                      .html("사용가능한 비밀번호입니다.");
	        	passwordOk = true;
	        }
	    })
		
		$("#repw").on("keyup", function() {
			var pw = $("#pw").val();
			var repw = $("#repw").val();
			
			if(repw != ""){
				if (pw != repw) {
					$(".pw_Ok").css("display", "none");
					$(".pw_Already").css("display", "inline-block");
					// 비번 유효성 검사
					ckPw = false;
					
				} else {
					$(".pw_Ok").css("display", "inline-block");
					$(".pw_Already").css("display", "none");
					// 비번 유효성 검사
					ckPw = true;
				}
				
			}

		});

		$("#emailList").on("change", function() {
			$("#email2").val($("#emailList").val());
		});
		// 이메일 중복 검사
		$("#emailCk").on("click", function() {
			var email1 = $('#email1').val();
			var email2 = $('#email2').val();
			var email = email1 + "@" + email2;
			if (email1 == "" || email2 == "") {
				alert("이메일 형식에 맞게 기재해주세요")
			} else {
				console.log(email);

				$.ajax({
					url : '../member/emailCheck',
					type : 'post',
					data : {
						email : email
					},
					success : function(cnt) { //컨트롤러에서 넘어온 cnt값을 받는다 
						if (cnt == 0) { //cnt가 1이 아니면(=0일 경우) -> 사용 가능한 아이디 
							$(".email_Ok").css("display", "inline-block");
							$(".email_Already").css("display", "none");
							$("#emailspan").css("display", "inline-block");
							$(".email").val(email);
							$("#numOk").css("display", "inline-block");
							$("#hiddenEmail").css("display", "inline-block");
							
							// 이메일 유효성 검사
							ckEmail = true;
						} else { // cnt가 1일 경우 -> 이미 존재하는 아이디
							$(".email_Ok").css("display", "none");
							$(".email_Already").css("display", "inline-block");
							$("#email1").val('');
							$("#email2").val('');
							$("#hiddenEmail").css("display", "none");
							alert("아이디를 다시 입력해주세요.");
							// 이메일 유효성 검사
							ckEmail = false;
						}
					},
					error : function() {
						alert("에러입니다.");
					}
				});
			}
		});
		
		

		$("#nickname").on("blur", function() {
			var nickname = $("#nickname").val();
			if(nickname != ""){
				
				$.ajax({
					url : '/member/nicknameCheck',
					type : 'post',
					data : {
						nickname : nickname
					},
					success : function(cnt) {
						if (cnt == 0) { // 0이면 사용 가능
							// 닉네임 유효성 검사
							ckNickname = true;
							$(".nickname_Ok").css("display", "inline-block");
							$(".nickname_Already").css("display", "none");
						} else {
							// 닉네임 유효성 검사
							ckNickname = false;
							$(".nickname_Ok").css("display", "none");
							$(".nickname_Already").css("display", "inline-block");
							$("#nickname").val('');
						}
					},
					error : function() {
						alert("에러입니다.");
					}
				});
			
			}
		});

		$("#checknum").on("click", function() {
			var email = $("#email").val();
			if (email != "") {
				/* console.log("클릭"); */
				$.ajax({
					type : 'post',
					url : '/account/mailConfirm',
					data : {
						"email" : email
					},
					success : function(data) {
						console.log("data : " + data);
						code = data;
					}
				});
			}
		});

		$("#numOkButton").on(
				"click",
				function() {
					var email = $("#email").val();
					console.log("클릭");
					var memailconfirm = $("#memailconfirm").val();
					if(memailconfirm != ""){
						
					
						if (code != $("#memailconfirm").val()) {
							emconfirmchk = false;
							// 유효성 검사
							eConFirm = false;
							$("#memailconfirmTxt").html(
									"<span id='emconfirmchk'>인증번호가 잘못되었습니다</span>")
							$("#emconfirmchk").css({
								"color" : "#FA3E3E",
								"font-weight" : "bold",
								"font-size" : "10px"
	
							})
							//console.log("중복아이디");
						} else { // 아니면 중복아님
							emconfirmchk = true;
							// 이메일 인증 유효성 검사
							eConFirm = true;
							$("#memailconfirmTxt").html(
									"<span id='emconfirmchk'>인증번호 확인 완료</span>")
	
							$("#emconfirmchk").css({
								"color" : "#0D6EFD",
								"font-weight" : "bold",
								"font-size" : "10px"
	
							})
						}
					}
				});
		$('#btnRegister').on('click', function(event) {
            
            
            if(ckEmail == false){
            	alert("이메일 중복 확인 하세요.");
            	event.preventDefault()
                event.stopPropagation()
            }else if(ckName == false){
            	alert("이름 입력해주세요.");
            	event.preventDefault()
                event.stopPropagation()
            }else if(ckNickname == false){
            	alert("닉네임 입력해주세요.");
            	event.preventDefault()
                event.stopPropagation()
            }else if(ckPw == false){
            	alert("비밀번호 입력해주세요");
            	event.preventDefault()
                event.stopPropagation()
            }else if(ckTel == false){
            	alert("핸드폰 번호 입력해주세요.");
            	event.preventDefault()
                event.stopPropagation()
            }else if(eConFirm == false){
            	alert("이메일 인증 해야합니다.");
            	event.preventDefault()
                event.stopPropagation()
            }
            
        })

	});