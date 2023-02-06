$(function(){
    $(".add-btn").on("click", function(){
        console.log("멤버 초대 버튼 클릭");
        
        let index = $(this).attr("id");
        console.log(index);
        
        let clno = $(".clno").eq(index).val();
        let email = $(".email").eq(index).val();
        console.log(clno, email);
        
        if(clno != "" && email != ""){
            $.ajax({
                url: "/addClubMember",
                type: "post",
                data: {
                    "clno": clno,
                    "email": email
                },
                success: function(){
                       console.log("멤버 초대 성공");
                       alert("초대하였습니다.");
                       location.reload();
                },
                error: function(){
                    console.log("요청 실패");
                }
            })
        }
    })
})