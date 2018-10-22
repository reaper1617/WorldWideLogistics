<%--
  Created by IntelliJ IDEA.
  User: Reaper
  Date: 23.10.2018
  Time: 0:52
  To change this template use File | Settings | File Templates.
--%>
<div class = "container-fluid">
    <h2>Fixed-top container</h2>
    <nav class="nav nav-tabs bg-primary bg-dark navbar-dark fixed-top">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/drivermainpage/0">Driver account</a>
        <!-- Nav tabs -->
        <ul class="nav nav-tabs bg-primary bg-dark navbar-dark" role="tablist">
            <form action="${pageContext.request.contextPath}/logout" method="get">
                <li class="nav-item bg-dark">
                    <button type="submit" class="btn btn-primary">Log out</button>
                </li>
            </form>
        </ul>

    </nav>
</div>