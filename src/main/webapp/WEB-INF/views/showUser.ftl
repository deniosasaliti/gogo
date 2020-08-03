<#assign  sf=JspTaglibs["http://www.springframework.org/tags/form"]>
<head>

    <title>User Info</title>
</head>
<body>

<table>
    <h1>User info</h1>
    <tr>
        <td>ID</td>
        <td>${user1.id}</td>
    </tr>

    <tr>
        <td>NAME</td>
        <td>${user1.name}</td>
    </tr>

    <tr>
        <td>AGE</td>
        <td>${user1.age}</td>
    </tr>

    <tr>
        <td>EMAIL</td>
        <td>${user1.email}</td>
    </tr>

</table>
<br>
<a href="/gogo_war_exploded/users">Back</a>

</body>
</html>