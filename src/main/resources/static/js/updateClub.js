function updateClub() {
    console.log("협업공간 정보 수정");
    var name = $("#editClname").val().trim(); // 스페이스바만 있는것 방지 
    var length = $("#editClname").val().length; 
    var explain = $("#editClexplain").val();
    console.log(name);
    console.log(explain);
    console.log("length " +length);
    if (name == "") {
        alert("협업공간 이름을 작성하십시오");
        return false;
    } else if (length > 25) {
        alert("협업공간 25자까지 입력 가능합니다.");
        return false; 
    }
    $("#editForm").submit(); 
}
function checkLength() {
    checkEnter();
    var content = $('#editClexplain').val();
    console.log(content);
    $('#editCounter').html("("+content.length+" / 최대 100자)");

    if (content.length > 100){
        $(this).val(content.substring(0, 100));
        $('#editCounter').html("(100 / 최대 100자)");
        alert("최대 100자까지 입력 가능합니다.");
        return false;  
    }
}

function checkEnter() { 
     var rows = $('#editClexplain').val().split('\n').length;
            var maxRows = 1; 
            if( rows > maxRows){
                alert('엔터는 불가합니다');
                modifiedText = $('#editClexplain').val().split("\n").slice(0, maxRows);
                $('#editClexplain').val(modifiedText.join("\n"));
                return false; 
            }     
}

