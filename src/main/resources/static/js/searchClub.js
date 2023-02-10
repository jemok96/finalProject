function clubSearch(){
	let searchValue = document.getElementById('searchValue').value.trim();
	console.log(searchValue); 
	    if (searchValue.length > 0) {
	       document.frmSearch.submit(); 
	    } else {
	        alert('검색어가 너무 짧습니다.');
	        location.reload();
	        return false;
	    }
}