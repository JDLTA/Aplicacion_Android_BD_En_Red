<?php 
 if (!($conexion = mysqli_connect('127.0.0.1', 'root', '','escuela_web'))) {
    die("Falló la conexión!!, ERROR: ".mysqli_connect_error());
  }
  if ($_SERVER['REQUEST_METHOD']=='POST') {
  	$cadena_json = file_get_contents('php://input');
                                    //Recibe inforación por HTTP, en este caso una cadena JsonSerializable
    $datos = json_decode($cadena_json, true);
    $u = $datos['usuario'];
    $c = $datos['clave'];
    $sql = "SELECT * FROM login WHERE usuario ='$u' AND contra = '$c'";
    $consulta = mysqli_query($conexion, $sql);
    if (mysqli_num_rows($consulta) > 0) {
		$respuesta["exito"] = 1;
		$respuesta["msj"] = "Login exitoso";
		echo json_encode($respuesta);
	}else{
		$respuesta["exito"] = 0;
		$respuesta["msj"] = "No se encontro el usuario...";
		echo json_encode($respuesta);
	}
  }
 ?>