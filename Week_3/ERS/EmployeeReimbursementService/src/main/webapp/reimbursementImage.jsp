<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>submitReimburstment</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
</head>
<body>


<script type="text/javascript">
window.onload = function(){
        createImg();
    }

    function createImg(){
        //Step 1 Create an xmlhttprequest object

        let xhr = new XMLHttpRequest();

        //Step 2 Add a callback function to on ready state change
		function getParameterByName(name, url) {
   		 if (!url) url = window.location.href;
   		 name = name.replace(/[\[\]]/g, "\\$&");
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, " "));
}
        xhr.onreadystatechange = function(){
            //Step 5 handle the response
            if(xhr.readyState == 4 && xhr.status == 200){
    				let name = 'id';        	
    				url = window.location.href;
    				name = name.repalce(/[\[\]]/g, "\\$&");
    				let regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
    						results = regex.exec(url);
    				let rebur decodeURIComponent(resutls[2].replace(/\+g," "));		
					document.body.appendchild(image);

					let cat = rebur.cat;
                    let appId = rebur.approverId;
                    let subId = rebur.sumbitterId;
    				let id= rebur.reimburseId;
                    let timeApp = (rebur.timeApproved == null)? 'pending' : rebur.timeAprroved;
                    let timeSub = rebur.timeSubmitted;
                    let amount = rebur.amount;
                    let approved = (rebur.approved === 'true') ? "Yes" : "No";
                   
                    //Append the row

                    document.getElementById("EmployeeReimbursments").appendChild(row);
                }
            }
        }
        //Step 3 call the open method 
        xhr.open("GET","/EmployeeReimbursementService/viewEmployeeReimburstment.jsp",true);

        //Step 4 call the send method

        xhr.send();
    }

<div id="talltweets">
    The <b>quick brown fox</b> jumped over the <i>lazy dog</i>.
</div>

<!-- The PNG image will be added here -->
<div style ="background:black;padding:100px">
<div style="background:yellow;padding:10px">
  <img id="textScreenshot" src="">
</div>
</div>

<!-- Include the HTMl2Canvas library -->
<script src="//cdnjs.cloudflare.com/ajax/libs/html2canvas/0.4.1/html2canvas.min.js"></script>

<script>
  html2canvas(document.getElementById("talltweets"), {
    onrendered: function(canvas) {
      var screenshot = canvas.toDataURL("image/png");
      document.getElementById("textScreenshot").setAttribute("src", screenshot);
    }
  });
</script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
		integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
		crossorigin="anonymous"></script>
</body>
</html>