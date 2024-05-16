<?php
require_once "inc/Resultados.inc";
require_once "inc/Consultas.inc";
require_once "inc/Base.inc";

function Delete()
    {
    $uri = $_SERVER['REQUEST_URI'];
    $path = parse_url($uri, PHP_URL_PATH);
    $piezas = explode( '/', $path );
    $tabla = $piezas[2];

    switch($tabla)
        {
        case "facturas": Delete_facturas(); break;
        default: Retornar(405);
        }
    }

function Delete_facturas()
    {
    if (!isset($_SERVER['PHP_AUTH_USER'])) Retornar(401);
    
    $usr = $_SERVER["PHP_AUTH_USER"];
    $psw = $_SERVER["PHP_AUTH_PW"];
        
    $usuario = Credenciales($usr,$psw);
    if ($usuario==null) Retornar(401);
    $id = $usuario->id;
    $query = "DELETE FROM facturas WHERE id_usuario =$id";
    
    Base::Execute($query);
    Retornar(204,"Registros eliminados");
    }
    
 