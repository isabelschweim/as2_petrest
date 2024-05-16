<?php
const PRETTY = JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE | JSON_UNESCAPED_SLASHES|JSON_PRESERVE_ZERO_FRACTION;

function Help()
    {
    header("location: Help.html");
    exit();
    }
    
function Retornar($code,$data="")
    {
    if ($code != 200)
        {
        if ($code == 204) $code=200;
        $d = new stdClass();
        $d->status = String_code($code);
        if ($data != "")  $d->info = $data;
        $data = $d;
        }
        
    header('Content-Type: application/json');
    http_response_code($code);$body = json_encode($data,PRETTY);
    echo $body;
    exit();
    }
    
    
function String_code($code)
    {
    switch ($code)
        {
        case 200: return("200: OK");
        case 204: return("204: No content");
            
        case 400: return("400: Bad Request");
        case 401: return("401: Authorization Required");
        case 403: return("403: Forbidden");
        case 404: return("404: Not found");
        case 405: return("405: Method Not Allowed");
        case 406: return("406: Not Acceptable");
            
        case 300: return("300: Traza");
        default: return("");
        }
    }