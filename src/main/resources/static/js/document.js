

$(function(){
	$("#dropdownWrite a").on("click",function(){
		var dropvalue = $(this).text();
		
		$("#dropdownMenuButton").text(dropvalue);
		$("#hidestatus").val(dropvalue);
		
		console.log(dropvalue)
		console.log($("#hidestatus").val())
		
			
	})
	
	$("#todostatusMenu a").on("click",function(){
		var dropvalue = $(this).text();
		
		$("#todoMenuButton").text(dropvalue);
		$("#hidetodostatus").val(dropvalue);
		
		console.log(dropvalue)
		console.log($("#hidetodostatus").val())
		
			
	})
	
	$("#dropdownMember a").on("click",function(){
		var dropvalue = $(this).text();
		
		$("#MemberSelect").text(dropvalue);
		$("#hidetname").val(dropvalue);
		
		console.log(dropvalue)
		console.log($("#hidetname").val())
		
			
	})
	
	
})