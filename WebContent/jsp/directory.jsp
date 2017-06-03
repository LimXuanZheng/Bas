<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<link rel="stylesheet" href="../css/directoryStyle.css">
		<!-- Font Mono-Sans -->
		<link href='http://fonts.googleapis.com/css?family=Noto+Sans:400,700,400italic,700italic&subset=latin,latin-ext' rel='stylesheet' type='text/css'>
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
		<!-- Optional theme -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
		<title>Directory</title>
	</head>
	<body>
		<div class="container-fluid" id="Container">
			<!-- Navigation -->
			<nav class="navbar navbar-default">
				<div class="container-fluid">
					<div class="navbar-header">
						<a class="navbar-brand" href="homePage.html">Bas?</a>
					</div>
					<ul class="nav navbar-nav">
						<li><a href="../html/homePage.html">Home</a></li>
						<li class="active"><a href="#">Directory</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li id="loginBtn"><a href="#">Welcome, Bob.</a></li>
					</ul>
				</div>
			</nav>
			<div id="Container-Container">
				<div class="container-fluid" id="Left-Container">
					<form action="${pageContext.request.contextPath}/Directory" method="post">
						<input type="text" name="search" id="searchBox" placeholder="Name">
						<select id="schoolClass" name="schoolClass">
							<option value="1E1">1E1</option>
							<option value="1E2">1E2</option>
							<option value="1E3">1E3</option>
							<option value="1E4">1E4</option>
							<option value="1E5">1E5</option>
						</select>
						<select id="subject" name="subject">
							<option value="English">English</option>
							<option value="Chinese">Chinese</option>
							<option value="Math">Math</option>
							<option value="Science">Science</option>
							<option value="History">History</option>
							<option value="Art">Art</option>
							<option value="Music">Music</option>
						</select>
						<input type="submit" name="submit">
					</form>
				</div>
				<div class="container-fluid" id="Right-Container">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>
									<p>Name</p>
								</th>
								<th>
									<p>Form Class</p>
								</th>
								<th>
									<p>Contact Number</p>
								</th>
								<th>
									<p>Email</p>
								</th>
							</tr>
						</thead>
						<tbody name="returnResult">
						</tbody>
					</table>
				</div>
			</div>
		</div>
	
		<!-- jQuery library -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<!-- Latest compiled and minified JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
	</body>
</html>