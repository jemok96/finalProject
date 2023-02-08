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
    
    $(".delete-btn").on("click", function(){
        console.log("멤버 삭제 버튼 클릭");
        
        if(confirm("정말 삭제하시겠습니까?") == true){
            alert("삭제되었습니다");
            let index = $(this).attr("id");
            console.log(index);
            
            let cno = $(".cno").eq(index).val();
            console.log(cno);
        
            if(cno != ""){
                $.ajax({
                    url: "/deleteClubMember",
                    type: "post",
                    data: {
                        "cno": cno
                    },
                    success: function(){
                           console.log("멤버 삭제 완료");
                           location.reload();
                    },
                    error: function(){
                        console.log("요청 실패");
                    }
                })
            }
        }else{
            return ;
        }
    })
})