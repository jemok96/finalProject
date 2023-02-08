$(function(){
    $(".accept-btn").on("click", function(){
        console.log("수락 버튼 클릭");
        
        let index = $(this).attr("id");
        console.log(index);
        
        let ano = $(".ano").eq(index).val();
        let clno = $(".clno").eq(index).val();
        let email = $(".email").eq(index).val();
        console.log(clno, email);
        
        if(clno != "" && email != ""){
            $.ajax({
                url: "/acceptInvitation",
                type: "post",
                data: {
                    "ano": ano,
                    "clno": clno,
                    "email": email
                },
                success: function(){
                       console.log("초대 수락");
                       alert("협업공간에 가입되었습니다.");
                       location.reload();
                },
                error: function(){
                    console.log("요청 실패");
                }
            })
        }
    })
    
    $(".reject-btn").on("click", function(){
        console.log("거절 버튼 클릭");
        
        let index = $(this).attr("id");
        console.log(index);
        
        let ano = $(".ano").eq(index).val();
        let clno = $(".clno").eq(index).val();
        let email = $(".email").eq(index).val();
        console.log(clno, email);
        
        if(clno != "" && email != ""){
            $.ajax({
                url: "/rejectInvitation",
                type: "post",
                data: {
                    "ano": ano,
                    "clno": clno,
                    "email": email
                },
                success: function(){
                       console.log("초대 거절");
                       location.reload();
                },
                error: function(){
                    console.log("요청 실패");
                }
            })
        }
    })
    
    $(".alarm-delete-btn").on("click", function(){
         console.log("알람 삭제 버튼 클릭");
        
        let index = $(this).attr("id");
        console.log(index);
        
        let ano = $(".ano").eq(index).val();
        
        if(ano != ""){
            $.ajax({
                url: "/alarm/remove",
                type: "post",
                data: {
                    "ano": ano
                },
                success: function(){
                       console.log("삭제 완료");
                       location.reload();
                },
                error: function(){
                    console.log("요청 실패");
                }
            })
        }
    })
})