
<html>

<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #4d0404;
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }
    form {
        background-color: #9a0000;
        padding: 20px;
        border-radius: 5px;
        box-shadow: 0px 2px 5px rgba(128, 0, 0, 0.1);
        width: 300px;
    }
    h1 {
        text-align: center;
        margin-bottom: 20px;
    }
    label {
        display: block;
        margin-bottom: 5px;
    }
    input[type="text"],
    input[type="password"],
    input[type="submit"] {
        width: 100%;
        padding: 10px;
        margin-bottom: 15px;
        border: 1px solid #ccc;
        border-radius: 3px;
        box-sizing: border-box;
    }
    input[type="submit"] {
        background-color: #ff0000;
        color: white;
        border: none;
        cursor: pointer;
    }
    input[type="submit"]:hover {
        background-color: #d90000;
    }
</style>

<head>
    <title>Log In</title>

</head>
<body>
<br>
<form action="login" method="post">
    <h1>Login</h1>
    <label>User Name:</label><input type="text" name="userName" required/>
    <br/>
    <label>Password:</label><input type="password" name="password" required/>
    <br/>
    <input type="submit" value="Log in">
</form>
</body>
</html>
