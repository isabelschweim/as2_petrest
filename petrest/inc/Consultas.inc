
<?php
require_once "inc/Base.inc";

function Credenciales($usr, $psw)
    {
    $query = "SELECT * FROM usuarios WHERE usuario='$usr' AND clave='$psw'";
    $usuario = Base::Select_one($query);
    return($usuario);
    }

function Todos_los_items($id_pedido)
    {
    $query = "SELECT * FROM items WHERE id_pedido=$id_pedido";
    $registros = Base::Select($query);
    return($registros);
    }
    
function Todos_los_indices($tabla)
    {
    $query = "SELECT id FROM $tabla";
    $registros = Base::Select($query);
    $indices = array();
    foreach ($registros as $registro)
        $indices[] = $registro->id;
    return($indices);
    }

function Un_registro($tabla,$id)
    {
    $query = "SELECT * FROM $tabla WHERE id=$id";
    $registro = Base::Select_one($query);
    if ($registro == null) $registro = new stdClass();
    return($registro);
    }

function Todas_las_facturas($id_usuario)
    {
    $query = "SELECT id FROM facturas WHERE id_usuario=$id_usuario";
    $registros = Base::Select($query);
    
    $indices = array();
    foreach ($registros as $registro)
        $indices[] = $registro->id;
    return($indices);
    }
    
 function Una_factura($id_usuario,$id)
    {
    $query = "SELECT id,id_usuario,id_pedido,importe,descuento,base,iva,total"
        ." FROM facturas "
        ." WHERE id =$id";
    $factura = Base::Select_one($query);
    if ($factura==null) Retornar(404);    
    if ($factura->id_usuario != $id_usuario) Retornar(403);
    unset($factura->id_usuario);
    return($factura);
    }