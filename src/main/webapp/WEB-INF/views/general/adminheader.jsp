<%--
  Created by IntelliJ IDEA.
  User: Reaper
  Date: 23.10.2018
  Time: 0:17
  To change this template use File | Settings | File Templates.
--%>
<div class = "container-fluid">
    <h2>Fixed-top container</h2>
    <nav class="nav nav-tabs bg-dark navbar-dark fixed-top">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/adminmainpage/0">WorldWideLogistics</a>
        <!-- Nav tabs -->
        <ul class="nav nav-tabs " role="tablist">
            <li class="nav-item">
                <a class="nav-link active bg-dark" data-toggle="tab" href="#manageorders">Manage orders</a>
            </li>
            <li class="nav-item">
                <a class="nav-link bg-dark" data-toggle="tab" href="#managetrucks">Manage trucks</a>
            </li>
            <li class="nav-item">
                <a class="nav-link bg-dark" data-toggle="tab" href="#manageusers">Manage users</a>
            </li>
            <li class="nav-item">
                <a class="nav-link bg-dark" data-toggle="tab" href="#managecargos">Manage cargos</a>
            </li>
            <li class="nav-item">
                <a class="nav-link bg-dark" data-toggle="tab" href="#managecities">Manage cities</a>
            </li>
            <li class="nav-item">
                <a class="nav-link bg-dark" data-toggle="tab" href="#manageroutes">Manage routes</a>
            </li>
            <form action="/logout" method="get">
                <li class="nav-item  bg-dark">
                    <button type="submit" class="btn btn-primary">Log out</button>
                </li>
            </form>
        </ul>

    </nav>


</div>