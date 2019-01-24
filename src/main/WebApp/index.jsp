<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<link href="css.css" type="text/css" rel="stylesheet"/>   
    <title>Weather Forecasted Maps</title>
    <meta name="viewport" content="initial-scale=1.0">
</head>
<body>
<header>
  <h1>Weather Forecasted Maps</h1>
</header> 
  <div class="header">Enter the source and destination</div><br>
  <form action="apirequest">
              Source:<br>
              <input type="text" name="source" id="source">
              <br><br>
         	  Destination:<br>
         	  <input type="text" name="destination" id="destination">
              <br><br>
              <input type="submit" name ="submit" value="submit">
  </form>          
</body>
</html>