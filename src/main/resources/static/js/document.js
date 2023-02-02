

$(function(){
	$(".dropdown-menu li button").on("click",function(){
		var dropvalue = $(this).text();
		
		$("#statusbutton").text(dropvalue);
		$("#hidestatus").val(dropvalue);
		
		console.log(dropvalue)
		console.log($("#hidestatus").val())
		
			
	})
	
})