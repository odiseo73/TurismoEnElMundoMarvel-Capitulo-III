<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal-body">
	<div class="mb-3">
		<label for="name" class="col-form-label">Nombre:</label> <input
			type="text" class="form-control" id="name" name="name"
			required value="${atraccion.name}">
	</div>
	<div class="mb-3">
		<label for="cost"
			class='col-form-label ${atraccion.errors.get("precio") != null ? "is-invalid" : "" }'>Costo:</label>
		<input class="form-control" type="number" id="cost" name="cost"
			required value="${atraccion.precio}"></input>
		<div class="invalid-feedback">
			<c:out value='${atraccion.errors.get("precio")}'></c:out>
		</div>
	</div>
	<div class="mb-3">
		<label for="duration"
			class='col-form-label ${atraccion.errors.get("tiempoEnHoras") != null ? "is-invalid" : "" }'>Duration:</label>
		<input class="form-control" type="number" id="duration" name="duration"
			required value="${atraccion.tiempoEnHoras}"></input>
		<div class="invalid-feedback">
			<c:out value='${atraccion.errors.get("tiempoEnHoras")}'></c:out>
		</div>
	</div>
	<div class="mb-3">
		<label for="capacity"
			class='col-form-label ${atraccion.errores.get("cupoDisponible") != null ? "is-invalid" : "" }'>Capacity:</label>
		<input class="form-control" type="number" id="capacity" name="capacity"
			required value="${atraccion.cupoDisponible}"></input>
		<div class="invalid-feedback">
			<c:out value='${atraccion.errores.get("cupoDisponible")}'></c:out>
		</div>
	</div>
</div>
<div>
	<button type="submit" class="btn btn-primary">Guardar</button>
	<a onclick="window.history.back();" class="btn btn-secondary"
		role="button">Cancelar</a>
</div>
