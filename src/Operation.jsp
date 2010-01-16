<html>
<head>
<title>Edit</title>
<script type="text/javascript" src="jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="jquery.latest.js"></script>
<script type="text/javascript" src="jquery-1.3.2.js"></script>
<script type="text/javascript" src="jquery.validate.js"></script>
<script>                                  
 $(document).ready(function() {	  
	 
		$("#val").validate();
	  });
 
/* $(document).ready(function() {

	 $.validator.addMethod("noSpecialChars", function(value, element) {
		 	      return this.optional(element) || /^[a-z0-9\_\.]+$/.test(value);
		   }, "Username must contain only letters, numbers, underscore or dot.");
	 alert("rituraj");
	 	  
	 $("#MainForm").validate();
 });*/
 
 
 </script>

<script type="text/javascript">
function validate(form)
{
	   var valid = true;

	    if ( form.first_name.value == "" )
	    {
	        alert ( "Please fill in the 'First Name' box." );
	        valid = false;
		
	    }
	    else if ( form.second_name.value == "" )
	    {
	        alert ( "Please fill in the 'Last Name' box." );
	        valid = false;
		
	    }
		else if ( form.pass.value == "" )
	    {
	        alert ( "Please fill in the 'Password' box." );
	        valid = false;
	    }
		else if ( form.email.value == "" )
	    {
	        alert ( "Please fill in the 'Email' box." );
	        valid = false;
	    }
		else if ( form.second_pass.value == "" )
	    { <p style="display: none">You have to accept agreement</p>
	        alert ( "Please fill in the 'Second Password' box." );
	        valid = false;
	    }
		else if ( form.pass.value != form.second_pass.value )
	    {
	        alert ( "Password and Re-enter Password doesn't matches" );
	        valid = false;
	    }
	    return valid;
	    //
}
</script>


</head>
<body>
<h1>Getting started with BoreMail.com</h1>

<div style="border: 5px solid red">

<form name="val" id="val" method="post"
	onsubmit="return validate(this);"
	action="http://localhost:8090/LoginServApp/DeleteServlet">First
Name: <input type="text" align="center" class="required"
	name="first_name" value=<%=request.getAttribute("fname")%>
	id="first_name" size="10" /><br />

Second Name: <input type="text" class="required" name="second_name"
	value=<%=request.getAttribute("lname")%> size="10" /><br />

<input type="hidden" name="login_name" id="login_name"
	value=<%=request.getAttribute("log_name")%> /> Secondary Email: <input
	type="text" name="email" class="email"
	value=<%=request.getAttribute("email")%> size="10" /><br />

New Password: <input type="password" name="pass" class="required"
	size="10" minlength="5" /><br />

Re-enter New Password: <input type="password" name="second_pass"
	class="required" size="10" /><br />

Security Question:<select id="ques" class="required" name="ques"
	onchange="javascript:onLocChange()">

	<option value=<%=request.getAttribute("ques")%>
		style="font-style: italic;">Choose a question ...</option>

	<option value="What is your primary frequent flyer number">What
	is your primary frequent flyer number</option>

	<option value="What is your library card number">What is your
	library card number</option>

	<option value="What was your first phone number">What was your
	first phone number</option>

	<option value="What was your first teacher&#39;s name">What
	was your first teacher&#39;s name</option>

	<option value="What is your father&#39;s middle name">What is
	your father&#39;s middle name</option>
</select><br>

Answer<input class="required" type="text"
	value=<%=request.getAttribute("ans")%> name="ans" id="ans" size="53"
	value=""><br>

Location<select id="loc" name="country"
	value=<%=request.getAttribute("country")%>
	onchange="javascript:onLocChange()">

	<option value="Ind">India</option>

	<option value="sr">Srilanka</option>

	<option value="Ban">Bangladesh</option>
</select><br>


<input type="submit" value="Submit" id="subid" /></form>

<form name="del" id="del" method="post"
	action="http://localhost:8090/LoginServApp/DeleteServlet"><input
	type="hidden" name="login_name" id="login_name"
	value=<%=request.getAttribute("log_name")%> /> <input type="submit"
	value="Delete" id="subid" /></form>
</div>
</body>
</html>
