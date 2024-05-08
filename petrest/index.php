<?php
require_once "inc/Herramientas.inc";
require_once "Metodos/Get.inc";
require_once "Metodos/Post.inc";
require_once "Metodos/Put.inc";
require_once "Metodos/Delete.inc";

$metodo = $_SERVER['REQUEST_METHOD'];

switch ($metodo)
    {
    case "GET": Get(); break;
    case "POST":Post(); break;
    case "PUT": Put(); break;
    case "DELETE":Delete(); break;
    default: Retornar(405); break;
    }

