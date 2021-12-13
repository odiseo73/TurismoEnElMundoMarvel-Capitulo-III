<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="mb-3">
	<label for="name" class="col-form-label">Username:</label> <input
		type="text" class="form-control" id="name" name="username"
		required value="${tmp_user.getUsername()}">
</div>
<div class="mb-3">
	<label for="coins"
		class='col-form-label ${tmp_user.getErrores().get("coins") != null ? "is-invalid" : "" }'>Monedas:</label>
	<input class="form-control" type="number" id="coins" name="coins"
		required value="${tmp_user.getDinero()}"></input>
	<div class="invalid-feedback">
		<c:out value='${tmp_user.getErrores().get("coins")}'></c:out>
	</div>
</div>

<div class="mb-3">
	<label for="time"
		class='col-form-label ${tmp_user.getErrores().get("time") != null ? "is-invalid" : "" }'>Tiempo:</label>
	<input class="form-control" type="number" id="time" name="time"
		required value="${tmp_user.getTiempoEnHoras()}"></input>
	<div class="invalid-feedback">
		<c:out value='${tmp_user.getErrores().get("time")}'></c:out>
	</div>
</div>

<div class="mb-3">
	<label for="password"
		class='col-form-label ${tmp_user.getErrores().get("password") != null ? "is-invalid" : "" }'>Contraseņa:</label>
	<input class="form-control" id="password" name="password"
		required value="${tmp_user.getPassword()}"></input>
	<div class="invalid-feedback">
		<c:out value='${tmp_user.getErrores().get("password")}'></c:out>
	</div>
</div>

<%-- Hacer una lista desplegable en vez del input en el div de "admin" que tenga dos opciones: "true" o "false" como String. --%>

<div class="mb-3">
	<label for="admin"
		class='col-form-label ${tmp_user.getErrores().get("admin") != null ? "is-invalid" : "" }'>Admin:</label>
		<input class="form-control" id="admin" name="admin"
		required value="${tmp_user.esAdmin()}"></input>
	<div class="invalid-feedback">
		<c:out value='${tmp_user.getErrores().get("admin")}'></c:out>
	</div>
</div>


<div>
	<button type="submit" class="btn btn-primary">Guardar</button>
	<a onclick="window.history.back();" class="btn btn-secondary"
		role="button">Cancelar</a>
</div>
