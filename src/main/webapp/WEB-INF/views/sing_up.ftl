<#assign  sf=JspTaglibs["http://www.springframework.org/tags/form"]>

<head>

    <title>Sing_up</title>

</head>
<body>
     <@sf.form action="/gogo_war_exploded/sing_up" method="post" modelAttribute="user">


        <div>
        <@sf.label path="name">Name</@sf.label>
        <@sf.input path="name"/>
        <@sf.errors path="name"/>
        </div>
         <br>


         <div>
             <@sf.label path="email">Email</@sf.label>
             <@sf.input path="email" />
             <@sf.errors path="email"/>
         </div>
         <br>


         <div>
             <@sf.label path="age">Age</@sf.label>
             <@sf.input path="age"/>
             <@sf.errors path="age"/>
         </div>
         <br>

         <div>
             <@sf.label path="password">Password</@sf.label>
             <@sf.input path="password"/>
             <@sf.errors path="password"/>
         </div>
         <br>
         <br>

         <input type="submit" value="registration">
     </@sf.form>
 <br>
<a href="/gogo_war_exploded/users">Back</a>
</body>
</html>