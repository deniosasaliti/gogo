<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>registration_form</title>
</head>
<body>
<table>
    <tr>

        <th>Name</th>
        <th>Email</th>
        <th>age</th>
    </tr>


    <#list users as user>
        <tr>
            <td>${user.name}</td>
            <td>${user.email}</td>
            <td>${user.age}</td>
        </tr>
    </#list>

</table>

</table>
</body>
</html>