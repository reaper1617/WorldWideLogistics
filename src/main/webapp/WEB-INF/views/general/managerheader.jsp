<%--
  Created by IntelliJ IDEA.
  User: Reaper
  Date: 23.10.2018
  Time: 0:46
  To change this template use File | Settings | File Templates.
--%>
<div class = "container-fluid">
    <h2>Fixed-top container</h2>
    <nav class="nav nav-tabs bg-primary bg-dark navbar-dark fixed-top">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/adminmainpage/0">Manager account</a>
        <!-- Nav tabs -->
        <ul class="nav nav-tabs bg-primary bg-light navbar-light" role="tablist">
            <li class="nav-item">
                <a class="nav-link active bg-dark" data-toggle="tab" href="#home">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link bg-dark" data-toggle="tab" href="#managetrucks">Manage trucks</a>
            </li>
            <li class="nav-item">
                <a class="nav-link bg-dark" data-toggle="tab" href="#managedrivers">Manage drivers</a>
            </li>
            <li class="nav-item">
                <a class="nav-link bg-dark" data-toggle="tab" href="#managecargos">Manage cargos</a>
            </li>
            <form action="${pageContext.request.contextPath}/logout" method="get">
                <li class="nav-item  bg-dark">
                    <button type="submit" class="btn btn-primary" >Log out</button>
                </li>
            </form>
        </ul>

    </nav>


</div>