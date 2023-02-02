$(function () {
    $("#findBtn").on("click", function () {
        let name = $("#name").val();
        let tel = $("#tel").val();
        if (name == ""){
            $(".errorMsg").html("이름을 입력해주세요.");
            $("#name").focus();  
            return false;
        }else if (tel == ""){
            $(".errorMsg").html("전화번호를 입력해주세요.");
            $("#tel").focus();
            return false;
        }else {
            $("#findBox").attr("hidden", "hidden");
            $("#resultBox").removeAttr("hidden");
            $.ajax({
                url: "../findEmail",
                type: "post",
                data: {
                    name: name,
                    tel: tel
                },
               success: function(result) {
                   if(result == ""){
                       $("#backBtn").removeAttr("hidden");
                       $(".resultMsg").html("<h6 id='notFound'>입력하신 정보와 일치하는 이메일이 없습니다.</h6>");            
                   }else{
                       $("#backBtn").attr("hidden", true);
                       $("#loginpageBtn").removeAttr("hidden");
                       $(".resultMsg").html("<h6>입력하신 정보와 일치하는 이메일</h6>");          
                       $(".resultMsg").append("<h5 id='resultEmail'>" + result + "</h5>");     
                   }
               }
            })
        }
    });
    $("#backBtn").on("click", function(){
        $("#findBox").removeAttr("hidden");
        $("#resultBox").attr("hidden", true);
        $("#backBtn").attr("hidden", true);
        $("#loginpageBtn").attr("hidden", "hidden");
        $(".errorMsg").html("&nbsp;");
        $("#name").val("");  
        $("#tel").val("");  
    })
});