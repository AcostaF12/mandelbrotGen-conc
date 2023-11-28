# mandelbrotGen-conc

## Trabajo Practico Final Programacion Concurrente

El proyecto genera conjuntos de mandelbrot, utilizando threads (worker) y monitores (buffer) de forma concurrente, como el siguiente:

![image](https://github.com/AcostaF12/mandelbrotGen-conc/assets/107961278/43010d43-4f7d-47b5-a655-1076c7d8eab3)

## Documentación de uso:

Requisitos:

- JDK 11/+11.

Pasos:

1. Estando en la carpeta root del proyecto, moverse a la carpeta **out** y a su vez a la out\artifacts\PConc_TP_2023s2_jar ejecutando:
      - `cd out\artifacts\PConc_TP_2023s2_jar`  (En Windows)
      - `cd out/artifacts/PConc_TP_2023s2_jar`  (En Linux)
        
2. Ejecutar el programa utilizando:  (ver orden parametros)
      - `java -jar PConc-TP-2023s2.jar arg1 arg2 arg3 arg4 arg5 arg6 arg7 arg8 arg9`

4. Revisar resultado, la imagen resultante se creara en un archivo con nombre **salida.png** en la misma carpeta del .jar.


### Orden de parametros:

  1. alto de la imagen en pixeles (int).
  2. ancho de la imagen en pixeles (int).
  3. x_inicial (double).
  4. y_inicial (double).
  5. x_rango (double).
  6. y_rango (double).
  7. cantidad de iteraciones (int).
  8. cantidad de threads (int).
  9. tamaño del buffer (int).
