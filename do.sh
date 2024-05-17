#!/bin/bash

# Compile script for p3
# Isabel Schweim - 100460211
# Rodrigo De Lama - 100451775

# Limpiar terminal
clear

# # Comprobar que el archivo JAR este presente
# if [ ! -f "json-simple-1.1.1.jar" ]; then
#     echo "Error: json-simple-1.1.1.jar no se encuentra."
#     exit 1
# else
#     echo "json-simple-1.1.1.jar encontrado!"
# fi

# Compilar Facturar.java
javac -cp .:json-simple-1.1.1.jar Facturar.java 2> /dev/null # Esconder las "Note:... al compilar"

# Si no hay errores de compilación, ejecutar el programa
if [ $? -eq 0 ]; then
    java -cp .:json-simple-1.1.1.jar Facturar

    # Limpiar archivos .class después de ejecutar
    rm *.class
else
    echo "Error: La compilación de Facturar ha fallado."
    rm *.class  # Limpiar archivos .class si la compilación falla
fi
