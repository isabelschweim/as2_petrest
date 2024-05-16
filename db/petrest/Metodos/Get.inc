<?php
require_once "inc/Resultados.inc";
require_once "inc/Consultas.inc";
require_once "inc/Base.inc";

function Get()
    {
    $uri = $_SERVER['REQUEST_URI'];
    $path = parse_url($uri, PHP_URL_PATH);
    $piezas = explode( '/', $path );
    $l = count($piezas);
    
    $tabla = ($l>=3) ? $piezas[2] : null;
    $id = ($l>=4) ? $piezas[3] : null;
    
    if (empty($tabla)) Help();

    switch($tabla)    
        {
        case "facturas":           
            Get_facturas($piezas);
            break;
            
        case "articulos":
        case "clientes":
        case "items":
            if (empty($id)) 
                {
                $indices = Todos_los_indices($tabla);
                Retornar(200, $indices);
                }
            else 
                {
                $registro = Un_registro($tabla, intval($id));
                Retornar(200, $registro);
                }
            break;            
            
        case "pedidos":
            $key = ($l>=5) ? $piezas[4] : null;
            if (empty($id)) 
                {
                $indices = Todos_los_indices($tabla);
                Retornar(200, $indices);
                }
            else if (empty($key)) 
                {
                $registro = Un_registro($tabla, intval($id));
                Retornar(200, $registro);
                }
            else if ($key == "items") 
                {
                $registros = Todos_los_items(intval($id));
                Retornar(200, $registros);
                }
            else  Retornar_(400);
            break;
        default:
            Retornar(404); 
            break;
        }
    }
    
function Get_facturas($piezas)
    {

    if (!isset($_SERVER['PHP_AUTH_USER'])) Retornar(401);
    $usr = $_SERVER["PHP_AUTH_USER"];
    $psw = $_SERVER["PHP_AUTH_PW"];
    $usuario = Credenciales($usr,$psw);

    $l = count($piezas);
    $id = ($l>=4) ? $piezas[3] : null;

    if (empty($id)) 
        {
        $registros = Todas_las_facturas($usuario->id);
        Retornar(200,$registros);
        }
    else
        {     
        $registro = Una_factura($usuario->id,intval($id));
        Retornar(200,$registro);
        }
    }