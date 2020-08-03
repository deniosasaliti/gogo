<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Users list</title>

</head>
<body>
<h1>Users list</h1>
<table>
    <tr>
        <th>id</th>
        <th>Name</th>
        <th>Email</th>
        <th>age</th>
        <th>Status</th>
    </tr>


        <#list users as user>
    <tr>
        <td><a href="/gogo_war_exploded/user/${user.id}">${user.id}</a></td>
        <td>${user.name}</td>
        <td>${user.email}</td>
        <td>${user.age}</td>
        <td>${user.status}</td>


        <td>
            <form>
                <button formaction="users" name="id"   formmethod="post" value="${user.id}">
                    Delete
                </button>
            </form>
        </td>
        <td>
            <form>
                <button formaction="users" name="id"   formmethod="post" value="${user.id}">
                    set_status
                </button>
            </form>
        </td>


    </tr>
        </#list>

</table>
<br>
<a href="logout">Logout</a>

<br>
<a href="hello">Back</a>


</body>
</html>