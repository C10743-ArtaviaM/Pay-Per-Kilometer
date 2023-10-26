# Pay Per Kilometer - EXAMEN PARCIAL | 15 Octubre, 2022

| **_Instrucciones Generales_** <li> El examen debe resolverse individualmente y en un máximo de 180 minutos. <li> Durante el tiempo de examen no se permite el uso de aparatos electrónicos, tales como celulares, tablets, relojes inteligentes, etc. <li> Debe resolverse el examen utilizando lenguaje de programacion JAVA. Plantee la solución en papel como si se programara en un IDE o procesador de texto. <li> Para plantear su solución, utilice únicamente los temas desarrollados en el curso. <li> Se recomienda que lea todo el enunciado antes de comenzar a resolverlo. <li> La solución implementada debe tener como mínimo los métodos que se solicitan, pero usted esta en la libertad de realizar mas si lo prefiere. |
| :---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |

Una nueva aerolínea **"Pay Per Kilometer"** - en adelante **PPK** - ha decidido implementar un sistema de cotizacion para viajes, cuyos precios de introducción dependerán exclusivamente de las distancias recorridas, y de una suscripción de las personas. Para ello, se quiere implementar un programa que sea capaz de mostrarle al usuario el costo de su viaje, basado en los puntos geográficos por los que pasa en su itinerario deseado.

El uso de mapas terrestres virtuales es útil en varias disciplinas para emular el mundo físico real. Los mapas se componen de puntos geográficos. Estos puntos pueden ser definidos por coordenadas de la forma (_x_, _y_), donde _x_ es un valor real en grados que representa la latitud desde la linea del Ecuador y _y_ es uno de los grados que representa la longitud desde el medidiano de Greenwich.

Para definir operaciones, debe definir y diseñar la clase **Aerolinea [5%]**.

## Parte A. Distancia entre dos puntos.

La superficie de la Tierra es curva y la distancia entre grados de longitud varia con la latitud. Como resultado, encontrar la distancia entre dos puntos en la superficie de la Tierra es mas complicado que simplemente usar el teorema de Pitágoras. Sean (_x<sub>1</sub>_, _y<sub>1</sub>_) y (_x<sub>2</sub>_, _y<sub>2</sub>_) la latitud y la longitud de dos puntos en la superficie de la Tierra, en grados. La distancia entre estos puntos, en kilómentos, y siguiendo la superficie de la Tierra<sup>1</sup> es:

`Distancia = 6371.01 x arccos(sen(x<sub>1</sub>) x sen(x<sub>2</sub>) + cos(x<sub>1</sub>) x cos(x<sub>2</sub>) x cos(y<sub>1</sub> - y<sub>2</sub>))`

Escriba un método que, a partir de dos coordenadas de un mapa representadas por cuatro valores numéricos reales x<sub>1</sub>, y<sub>1</sub>, x<sub>2</sub> y y<sub>2</sub> en grados, determine y retorne el valor de la distancia en kilómetros que hay entre esos puntos. Estos valores entran de parametro, por lo que no hay interacción con el usuario. El valor resultante debe ser redondeado al entero mas proximo.

> **Métodos útiles de la clase Math**
>
> <li> Math.toRadians(x) retorna el resultado de convertir x grados a radianes.
> <li> Math.acos(x) retorna el arcocoseno de x, en radianes. El resultado esta entre 0 y pi.
> <li> Math.cos(x) retorna el coseno de un ángulo, dado en x radianes.
> <li> Math.sin(x) retorna el seno de un ángulo, dado en x radianes.
> <li> Math.round(x) retorna un valor de tipo entero, resultado de redondear el flotante x al entero mas próximo.

## Parte B. Precio de un recorrido.

Los precios de los pasajes de PPK se definen según el tipo de suscripción, así:
|Parámetro|Regular|Premium|
|:-------:|:-----:|:-----:|
|Del kilómetro 1 al 1000|$0.6 cada kilómetro|$0.55 cada kilómetro|
|Del kilómetro 1001 al 2000|$0.4 cada kilómetro|$0.30 cada kilómetro|
|Los kilómetros 2001 en adelante|$0.3 cada kilómetro|$0.25 cada kilómetro|

Haga un método que determine y retorne el costo de un boleto de avión de PPK a partir de la cantidad de kilómetros por recorrer, y el tipo de cliente ("REG" o "PREM"). Estos 2 valores son parámetros, por lo que no hay interacción con el usuario. Tome en cuenta que el precio de cada kilómetro no depende solo de un valor total del recorrido. Por ejemplo, si el recorrido es de 1500 kilómetros, los primeros 1000km tienen un precio, mientras que los 500 restantes se cobran a otro precio por exceder el límite de 1000km establecido.

## Parte C. Distancia total de un viaje.

Para procesar los datos de los puntos donde la aerolínea puede hacer paradas de vuelos, la información se ha organizado en 3 vectores diferentes, del mismo tamaño:

<li> un vector contiene todos los codigos indentificadores de los puntos,
<li> un vector contiene los valores de las latitudes de los puntos, y
<li> un vector contiene los valores de las longitudes de los puntos.

Estos vectores conservan una correspondencia por posición, lo que significa que los elementos indexados en la misma posición están relacionados. A continuacion, se muestra un ejemplo de dicha correspondencia.

###### Vector de ID

| SJO | EWR | LAP | CDG |
| :-: | :-: | :-: | :-: |

###### Vector de ID

| 9.9981 | 40.6896 | -12.0240 | 49.0097 |
| :----: | :-----: | :------: | :-----: |

###### Vector de ID

| -84.2040 | -74.1744 | -77.1119 | 2.5479 |
| :------: | :------: | :------: | :----: |

_De este ejemplo, se sustrae que el punto llamado EWR tiene de latitud 40.6896 y de longitud -74.1744._

Los vuelos de PPK pueden pasar por varios puntos. En el caso de los vuelos directos, solo hay un punto de partida y un punto de destino, pero en los vuelos con escalas se contemplan además paradas intermedias.

Haga un método que reciba de parámetros los 3 vectores: uno con los id, uno con latitudes y uno con longitudes. Además, recibe de parámetro otro vector. Este cuarto especifica los ID de los puntos por los cuales va a pasar el vuelo. El método debe calcular y retornar la distancia total de recorrido.

Por ejemplo, si el vector contiene los valores,
|SJO|EWR|CDG|LAP|
|:-:|:-:|:-:|:-:|

, entonces el costo total sera la suma de las distancias SJO -> EWR, EWR -> CDG, CDG -> LAP.

## Parte D. Menu interactivo.

Realice un método interactivo que utilice llamados a los métodos implementados, para calculos y cotizacioes de PPK. Este método recibirá de parámetro 3 vectores con los datos de todos los puntos de la aerolínea: los id, las latitudes y las longitudes.

Estas deben ser las opciones del menú:

<ol> 
    <li> Determinar la distancia recorrida en un vuelo directo. En este caso, deberá solicitar cuál es el ID del punto de partida y del punto de destino, y luego imprimir la distancia en km entre esos dos puntos, redondeado a 2 decimales. Puede suponer que la persona siempre digita IDs que existen dentro de los vectores.
    <li> Determinar el valor del viaje a partir de conocer el punto de partida, las escalas, y el destino. En este caso, deberá solicitar primero el tipo de cuenta (REGULAR o PREMIUM), luego la cantidad de escalas que va a realizar y luego, se solicitarán todos los puntos, incluyendo el de partida y el de destino. Finalmente se imprime el costo total del pasaje en dólares. Puede suponer que la persona siempre digita IDs que existen dentro de los vectores.
    <li> Terminar y salir.
</ol>

Considere que, si en una entrada numérica la persona ingresa un valor de formato incorrecto, el programa debe imprimir el mensaje: "Ocurrio un error." y volver al menú principal. No debe caerse abruptamente.

## Parte E. Llamado principal.

Incorpore un método principal que muestre el menu al ejecutar el programa. Se enviaran de parámetro valores de la aerolínea en forma de 3 vectores: uno con los id, uno con las latitudes, y uno con las longitudes.

###### Ejemplo de ejecucion

<code>Bienvenid@ al sistema de su nueva aerolinea, PPK<br>
MENU. 1= Ver distancia de vuelo, 2= Cotizar recorrido, 3= Salir: **1**<br>
Ingrese punto de partida: **1**<br>
Ingrese punto de destino: **SJO**<br>
El vuelo tiene una distancia de 2571 km.<br>
MENU. 1= Ver distancia de vuelo, 2= Cotizar recorrido, 3= Salir: **2**<br>
Ingrese tipo de suscripcion [REG o PREM]: **REG**<br>
Ingrese cantidad de escalas: **2**<br>
Ingrese punto de partida: **SJO**<br>
Ingrese punto de escala 1: **EWR**<br>
Ingrese punto de escala 2: **CDG**<br>
Ingrese punto de destino: **LAP**<br>
El vuelo tiene un costo de 6305.80 dolares.<br>
MENU. 1= Ver distancia de vuelo, 2= Cotizar recorrido, 3= Salir: **3**<br>
Gracias por usar el programa.
</code>

###### Opcional

Obtendrá un 5% adicional si implementa una solucion que contemple que cada vez que la persona digite un ID de un punto que no existe en los vectores muestre el mensaje "el punto no existe y devuelva al menú principal.
