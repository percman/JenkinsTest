window.onload=function(){
    document.getElementsByClass("btn btn-success").addEventListener("click", approve);
}

approve=function(){
	let xhr=new XMLXttpRequest();
	
	xhr.onreadystatechange=function(){
		if(xhr.readystate===4 && xhr.status===200){
			
		}
	}
	
	xhr.open("POST", "./approve_deny.do");
	document.getElement
	xhr.send('id=');
}