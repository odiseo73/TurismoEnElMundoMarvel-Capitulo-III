<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="preconnect" href="https://fonts.gstatic.com">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;500;600&display=swap" rel="stylesheet">
  <jsp:include page="partials/head.jsp"></jsp:include>
<link rel="stylesheet" href="loginStyle.css">
 <title>Log in</title>
</head>
<body>
		<main>
			
			<c:if test="${flash != null}">
				<div class="alert alert-danger">
					<p>
						<c:out value="${flash}" />
					</p>
				</div>
			</c:if>

			<c:if test="${success != null}">
				<div class="alert alert-success">
					<p>
						<c:out value="${success}" />
					</p>
				</div>
			</c:if>

			<form action="login" method="post">
  <h3>Iniciar sesión</h3>
				<div>
					<label for="username" class="form-label">Usuario</label> 
					<input class="form-control" type="text" name="username" placeholder="Usuario">
				</div>

				<div>
					<label for="password" class="form-label">Contraseña</label> 
					<input type="password" class="form-control" name="password" placeholder="Contraseña" >
				</div>

				<div>
					<button type="submit" class="btn btn-danger">Ingresar</button>
				</div>
			</form>

		</main>
</body>
</html>