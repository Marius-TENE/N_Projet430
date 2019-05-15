<!DOCTYPE html>
<html xmls:th="http://www.thymleaf.org"
	xmlns:layout="http://www.ultraq.net.nz/thymeLeaf/Layout">
<head>
<meta charset="UTF-8">
<title><h1>Page de connexion</h1></title>
</head>
<body>
	<form th:action="@{/connexion}" method="post">
		<p>Remplissez avec vos informations de connexion</p>
		<label>Matricule</label><br>
		<input type="text" name="matricule"/><class th:text="${msg}"></class><br>
		<label>password</label><br>
		<input type="password" name="password">
		<button type="submit">connexion</button>
	</form>
</body>
</html>