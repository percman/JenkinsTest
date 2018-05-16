window.onload=function(){
	document.getElementById("update").addEventListener("click", updateUser);
	document.getElementById("enter").addEventListener("click", enterReimbursement);
}

updateUser=function(){
	let xhr=new XMLHttpRequest();
	
	xhr.onreadystatechange=function(){
		if(xhr.readyState===4 && xhr.status===200){
			document.getElementById("ajax").innerHTML=xhr.responseText;
		}
	};
	
	xhr.open("GET", "./update.jsp");
	xhr.send();
}

enterReimbursement=function(){
	let xhr=new XMLHttpRequest();
	
	xhr.onreadystatechange=function(){
		 if(xhr.readyState===4&&xhr.status===200){
			 document.getElementById("ajax").innerHTML=xhr.responseText;
		 }
	};
	
	xhr.open("GET", "./submit.jsp");
	xhr.send();
}