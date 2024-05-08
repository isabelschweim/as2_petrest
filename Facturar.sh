#!/bin/bash

# Compile script for p3
# Isabel Schweim - 100460211
# Rodrigo De Lama - 100451775

# Limpiar terminal
clear

# Compilamos nuestra clase principal Facturar.java
javac Facturar.java

# Si no hay errores, ejecutamos el programa
if [ $? -eq 0 ]; then
    # Ejecutar Facturar si no hubo errores
    java Facturar &

    # Esperar a que termine la ejecucion (good practice)
    wait

    rm *.class # Limpiar archivos .class después de ejecutar
else
    echo "Error: La compilación de Facturar ha fallado."
    rm *.class # Limpiar archivos .class después de error
fi

# to kill all processes with the name "java Facturar"
# pkill -f "java Facturar"
