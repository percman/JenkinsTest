<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Home</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" 
        integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="../ReimbursementCss/bootstrap.css/">
</head>
<body>
            <div class="container">
                <div class="jumbotron">
                    <h1 class="display-4">View your Reimburstments</h1>
                    <hr class="my-4">
                </div>
            </div>

            <div class="container">
                    <nav class="navbar navbar-inverse">
                            <div class="navbar-header col-md-2">
                               <a href="./employeeHome.jsp" class="navbar-brand">Home</a>
                            </div>
                            <ul class="navbar-nav nav col-md-6">
                                <li><a href="./employeeInfo.jsp">Info</a></li>
                                <li class="active"><a href="./viewEmployeeReimburstment.jsp">View</a></li>
                                <li><a href="./submitEmployeeReimburstment.jsp">Submit</a></li>
                            </ul>
                            <ul class="navbar-nav nav navbar-right col-md-2 offset-md-2">
                                <li><a href="logout.do">Log out <span class="glyphicon glyphicon-log-out"></span></a></li> 
                            </ul>
                        
                    </nav>
                </div>
                <div class="container">
                <div class="page-header">
                    <h2>Your Reimbursments</h2>
                </div>
                <table class="table table-responsive table-stripped">
                    <thead class="thead-dark">
                        <tr>
                            <th>ID</th>
                            <th>Amount</th>
                            <th>Date Submitted</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody id = "EmployeeReimbursments"></tbody>
                </table>
            </div>
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" 
    integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</body>
</html>