<?php
require_once "inc/Resultados.inc";
require_once "inc/Consultas.inc";
require_once "inc/Base.inc";

function Post()
    {
    $uri = $_SERVER['REQUEST_URI'];
    $path = parse_url($uri, PHP_URL_PATH);
    $piezas = explode( '/', $path );
    $tabla = $piezas[2];
    switch($tabla)
        {
        case "facturas": Post_factura(); break;
        default:  Retornar(405);
        }
    }

function Post_factura()
    {
    if (!isset($_SERVER['PHP_AUTH_USER'])) Retornar(401);
    $usr = $_SERVER["PHP_AUTH_USER"];
    $psw = $_SERVER["PHP_AUTH_PW"];
       
    $usuario = Credenciales($usr,$psw);
    if ($usuario==null) Retornar(401);
    $id_usuario = $usuario->id;

    $json = file_get_contents('php://input');
    $body = json_decode($json, true);
    $id_pedido = $body["id_pedido"];
    $importe = $body["importe"];
    
    $query = "INSERT INTO facturas "
            . "(id_usuario,id_pedido,importe)"
            . " VALUES ($id_usuario,$id_pedido,$importe)";
    $id = Base::Insert($query);
    
    $r= new stdClass();
    $r->id_factura = $id;
    Retornar(200,$r);
    }
    