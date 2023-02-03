

$(function(){
	$(".dropdown-menu a").on("click",function(){
		var dropvalue = $(this).text();
		
		$("#dropdownMenuButton").text(dropvalue);
		$("#hidestatus").val(dropvalue);
		
		console.log(dropvalue)
		console.log($("#hidestatus").val())
		
			
	})
	
})