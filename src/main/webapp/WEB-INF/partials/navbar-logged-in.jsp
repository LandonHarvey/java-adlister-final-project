<%--
  Created by IntelliJ IDEA.
  User: landonharvey
  Date: 2/5/19
  Time: 4:40 PM
  To change this template use File | Settings | File Templates.
--%>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <a class="navbar-brand" href="/ads">Adlister</a>
            <a class="navbar-brand" href="/ads/create">Create Ad</a>
            <form action="/search" method="get">Search By Ad Title:
                <input type="search" name="Search">
            </form>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="/profile">Profile</a></li>
            <li><a href="/logout">Logout</a></li>
        </ul>
    </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
