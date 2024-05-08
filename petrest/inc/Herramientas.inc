<?php
function Traza($m="?")
    {
    $t = debug_backtrace();
    $t = $t[0];
    $f = basename($t["file"]);
    $l = $t["line"];
    echo "$f($l): $m\n";
    }

 