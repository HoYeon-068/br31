<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Admin</title>

    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    
    <link rel="stylesheet" href="/css/admin.css">
</head>
<body>

<%@ include file="admin_header.jsp" %>

<div class="container-fluid">
  <div class="row">

    <%@ include file="admin_sidebar.jsp" %>

    <main class="col-md-10 p-4">
      <jsp:include page="${contentPage}" />
    </main>

  </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>



</body>
</html>
