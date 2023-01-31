$(function () {
    $("#loginBtn").on("click", function () {
        if ($("#email").val() == ""){
            $(".errorMsg2").html(" ");
            $(".errorMsg").html("이메일을 입력해주세요.");
            $("#email").focus();
            $("#wrongInfo").hide();  
            return false;
        }
        if ($("#pw").val() == ""){
            $(".errorMsg2").html(" ");
            $(".errorMsg").html("비밀번호를 입력해주세요.");
            $("#pw").focus();
            $("#wrongInfo").hide();
            return false;
        }
        form.submit();
    });
});