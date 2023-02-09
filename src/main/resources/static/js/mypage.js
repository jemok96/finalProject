$(function(){
    $(".pw-btn").on("click", function () {
        console.log("버튼 클릭");
        let pw = $("#password").val();
        console.log(pw);
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
})