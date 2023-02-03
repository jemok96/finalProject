$(function () {
    let authCode = "";
    let passwordOk = false;
    $("#sendBtn").on("click", function () {
        console.log("메일 전송 버튼 클릭");
        let email = $("#email").val();
        let regEmail = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
        if(email == ""){
            $("#errorEmail").addClass("fail")
                            .html("이메일을 입력해주세요.");
        }else if(!regEmail.test(email)) {
             $("#errorEmail").addClass("fail")
                             .html("올바른 형식이 아닙니다.");
        }else {
            $("#errorEmail").html("&nbsp;");
            $("#authBtn").removeAttr("disabled");
            $.ajax({
                url: "../account/mailConfirm",
                type: "post",
                data: {email: email},
                success: function(result) {
                    authCode = result;
                    alert('인증번호가 전송되었습니다.');
                }
            })
        }
    })
    
    $("#authBtn").on("click", function(){
        console.log("인증 확인 버튼 클릭")
        let auth = $("#auth").val();
        if(authCode == auth){
            $("#errorAuth").removeClass("fail")
                           .addClass("success")
                           .html("인증이 완료되었습니다.");
            $("#resetPw").removeAttr("disabled");
        }else {
            $("#errorAuth").removeClass("success")
                           .addClass("fail")
                           .html("인증번호가 일치하지 않습니다.");
        }
    })
    
    $("#resetPw").on("click", function(){
        $(".resetBox").removeAttr("hidden")
        $(".authBox").attr("hidden", "hidden");
    })
    
    $("#checkPw").on("focus", function(){
        $("#infoPw").html("&nbsp;");
    })
    
    $("#pw").on("keyup", function(){
        let pw = $("#pw").val();
        let num = pw.search(/[0-9]/g);
        let eng = pw.search(/[a-z]/ig);
        let spece = pw.search(/[~!@#$%^&*()_+|<>?:{}`]/gi);
        if (pw.length < 8 || pw.length > 20) {
            $("#ckPw").removeClass("success")
                      .addClass("fail")
                      .html("8자리 ~ 20자리 이내로 입력해주세요.");
        }else if(pw.search(/\s/) != -1){
            $("#ckPw").removeClass("success")
                      .addClass("fail")
                      .html("공백없이 입력해주세요.");
        }else if(num < 0 || eng < 0 || spece < 0){
            $("#ckPw").removeClass("success")
                      .addClass("fail")
                      .html("영문, 숫자, 특수문자를 혼합하여 입력해주세요.");
        }else{
            $("#ckPw").removeClass("fail")
                       .addClass("success")
                      .html("사용가능한 비밀번호입니다.");
            passwordOk = true;
        }
    })
    
    $("#resetOkBtn").on("click", function(){
        let pw = $("#pw").val();
        let checkPw = $("#checkPw").val();
        if(pw != "" || checkPw != ""){
            if(pw != checkPw){
                $("#infoPw").removeClass("success")
                            .addClass("fail")
                            .html("비밀번호가 일치하지 않습니다.");
                return false;
            }else if(pw == checkPw && passwordOk == true){
                alert("비밀번호 재설정이 완료되었습니다.");
                form.submit();
            }
        }
    })
});