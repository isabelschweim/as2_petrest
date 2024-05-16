<?php
require_once "inc/Resultados.inc";
require_once "inc/Consultas.inc";
require_once "inc/Base.inc";

function Put()
    {

    $uri = $_SERVER['REQUEST_URI'];
    $path = parse_url($uri, PHP_URL_PATH);
    $piezas = explode( '/', $path );
    $tabla = $piezas[2];
    switch($tabla)
        {
        case "facturas": Put_factura(); break;
        default:  Retornar(405);
        }
    }

function Put_factura()
    {
    if (!isset($_SERVER['PHP_AUTH_USER'])) Retornar(401);
    $uri = $_SERVER['REQUEST_URI'];
    $usr = $_SERVER["PHP_AUTH_USER"];
    $psw = $_SERVER["PHP_AUTH_PW"];
    $json = file_get_contents('php://input');

    $usuario = Credenciales($usr,$psw);
    if ($usuario==null) Retornar(401);
    $id_usuario = $usuario->id;
             
    $path = parse_url($uri, PHP_URL_PATH);
    $piezas = explode( '/', $path );
    $id_factura = (isset($piezas[3]))
            ? intval($piezas[3])
            : -1;
    if ($id_factura <= 0) Retornar(406);    
    $factura = Una_factura($usuario->id,$id_factura);
    
    $data = json_decode($json, true);

    if (isset($data["descuento"]))$descuento = $data["descuento"];
    else Retornar(400,"Falta el descuento");

    if (isset($data["base"]))$base = $data["base"];
    else Retornar(400,"Falta la base imponible");
    
    if (isset($data["iva"])) $iva = $data["iva"];
    else Retornar(400,"Falta el IVA");
    
    if (isset($data["total"])) $total = $data["total"];
    else Retornar(400,"Falta el total");
    
    $query = "SELECT * FROM facturas WHERE id=$id_factura";
    $registro = Base::Select_one($query);
    if ($registro->id_usuario != $id_usuario) 
        Retornar(401,"Id factura erróneo");

    $query = "UPDATE facturas SET "
            . " descuento=$descuento,"
            . " base=$base,"
            . " iva=$iva,"
            . " total=$total"
            . " WHERE id = $id_factura";
    Base::Execute($query);

    Retornar(204,$query);
    Retornar(204,"Registro actualizado");
    }
    
function SHOW ($body)
    {
    foreach ($body as $k=>$v)
        echo "$k = $v\n";
    }