<!DOCTYPE html>
<html lang="en">
<head>

    <title>Login</title>
</head>
<body>
<form action="/gogo_war_exploded/login/process" method="post">
    <div>
        Email: <input name="email" type="email">
    </div>
    <div>
        Password: <input name="password" type="password">
    </div>
    <div>
        <input id="remember_me" name="_spring_security_remember_me"
               type="checkbox"/>
        <label for="remember_me" class="inline">Remember me</label>

    </div>
    <input type="submit">
</form>

<#if error??>

    <p>Bad credentials</p>

</#if>

<br>
<a href="/gogo_war_exploded/sing_up">Create new user</a>
<br>

</body>
</html>