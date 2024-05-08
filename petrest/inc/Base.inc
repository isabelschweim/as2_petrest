<?php
require_once "Configuracion.inc";
class Base
    {
    private static function Conectar()
        {
        $conexion = new mysqli(DB_SERVER_NAME, DB_USER_NAME, DB_USER_PSW, DB_NAME);
        return($conexion);
        }

    public static function Select($query)
        {
        $conexion = self::Conectar();
        $filas = $conexion->query($query);
        $n = $filas->num_rows;

        $lista = array();
        if ($n > 0)
            {
            $columnas = $filas->fetch_fields();
            while ($fila = $filas->fetch_row())
                $lista[] = self::Registro($columnas,$fila);
            }
        $filas->close();
        $conexion->close();
        return $lista;
        }
        
    public static function Select_one($query)
        {
        $registros = self::Select($query);
        if (count($registros)== 0) return(null);
        return ($registros[0]);
        }
        
    public static function Execute($query)
        {
        $conexion = static::Conectar();
        $conexion->query($query);
        $conexion->close();
        }
        
    public static function Insert($query)
        {
        $conexion = static::Conectar();
        $conexion->query($query);
        $id = mysqli_insert_id($conexion);
        $conexion->close();
        return($id);
        }
        
    private static function Registro($columnas,$fila)
        {
        $nc = count($columnas);
        $registro = new stdClass();
        for ($i = 0; $i < $nc; $i++)
            {
            $campo = $columnas[$i]->name;
            $tipo = $columnas[$i]->type;

            $valor = self::Valor($fila[$i],$tipo);
            
            $registro->$campo = $valor;
            }
        return($registro);
        }
    private static function Valor($v,$tipo)
        {
        switch($tipo)
            {
            case 3: return(intval($v)); 
            case 4: return (floatval($v));
            case 10: return (date($v));
            case 253: return($v);
            default: 
                echo ("Tipo desconocido tipo=$tipo , valor=$v<br>");
            }
        }

    }
