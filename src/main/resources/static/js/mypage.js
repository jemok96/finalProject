$(function(){
    $(".pw-btn").on("click", function () {
        console.log("버튼 클릭");
        let pw = $("#password").val();
        if(pw == ""){
            $(".errorMsg").text("비밀번호를 입력해주세요.");
        }else {
            $(".errorMsg").text("");
            $.ajax({
                url: "/mypage/password",
                type: "post",
                data: {password : pw},
                success: function(result){
                    if(result == "correspond"){                        
                        $("form").removeAttr("onsubmit");
                        $("form").submit();
                    }else{
                        alert("비밀번호가 일치하지 않습니다.");
                        return false;
                    }
                },
                fail: function(){
                    alert("인증 실패")
                }
            })
        }
    })
    
    let passwordOk = false;
    $("#pw").on("keyup", function(){
        let pw = $("#pw").val();
        let num = pw.search(/[0-9]/g);
        let eng = pw.search(/[a-z]/ig);
        let spece = pw.search(/[~!@#$%^&*()_+|<>?:{}`]/gi);
        if (pw.length < 8 || pw.length > 20) {
            $("#pw-error").css("color", "red")
                      .html("8자리 ~ 20자리 이내로 입력해주세요.");
        }else if(pw.search(/\s/) != -1){
            $("#pw-error").css("color", "red")
                      .html("공백없이 입력해주세요.");
        }else if(num < 0 || eng < 0 || spece < 0){
            $("#pw-error").css("color", "red")
                      .html("영문, 숫자, 특수문자를 혼합하여 입력해주세요.");
        }else{
            $("#pw-error").css("color", "#13c242")
                      .html("사용가능한 비밀번호입니다.");
            passwordOk = true;
        }
    })
    
    $(".update-btn").on("click",function(){
        console.log("수정 버튼 클릭");
        let name = $("#name").val();
        let nickname = $("#nickname").val();
        let tel = $("#tel").val();
        let pw = $("#pw").val();
        let checkpw = $("#checkpw").val();
        if(name == ""){
            $("#name-error").css("color", "red")
                            .text("이름을 입력해주세요.");
            $("#name").focus();
        }else if(nickname == ""){
            $("#nickname-error").css("color", "red")
                                .text("별명을 입력해주세요.");
            $("#nickname").focus();
        }else if(tel == ""){
            $("#tel-error").css("color", "red")
                           .text("전화번호를 입력해주세요.");
            $("#tel").focus();
        }else if(pw == ""){
            $("#pw-error").css("color", "red")
                          .text("비밀번호를 입력해주세요.");
            $("#pw").focus();
        }else if(pw != "" || checkpw != ""){
            if(pw != checkpw){
                $("#checkpw-error").css("color", "red")
                            .text("비밀번호가 일치하지 않습니다.");
                return false;
            }else if(pw == checkpw && passwordOk == true){
                alert("회원정보 수정이 완료되었습니다.");
                $("form").submit();
            }
        }
    })
})