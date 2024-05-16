<?php
require_once "inc/Herramientas.inc";
require_once "Metodos/Get.inc";
require_once "Metodos/Post.inc";
require_once "Metodos/Put.inc";
require_once "Metodos/Delete.inc";


$query = "SELECT * FROM facturas WHERE id=3";
$registro = Base::Select_one($query);
foreach ($registro as $k=>$v)
    echo "$k = $v\n<br/>";

