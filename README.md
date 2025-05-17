# Conversor de Monedas

Este es un conversor de monedas hecho en Java, utilizando Programación Orientada a Objetos (POO), Maven y consumo de una API de tipos de cambio en tiempo real.

## Características

- Conversión entre las siguientes monedas:
  - USD (Dólar estadounidense)
  - ARS (Peso argentino)
  - BOB (Boliviano)
  - BRL (Real brasileño)
  - CLP (Peso chileno)
  - COP (Peso colombiano)
- Interfaz de consola interactiva.
- Tipos de cambio actualizados en tiempo real mediante [ExchangeRate-API](https://www.exchangerate-api.com/).
- Código organizado en paquetes siguiendo buenas prácticas de POO.

## Estructura del Proyecto

src/
└── main/
└── java/
└── conversormoneda/
├── exception/
│ └── CurrencyConversionException.java
├── model/
│ ├── Currency.java
│ ├── USD.java
│ ├── ARS.java
│ ├── BOB.java
│ ├── BRL.java
│ ├── CLP.java
│ └── COP.java
├── service/
│ ├── CurrencyConverterService.java
│ └── ExchangeRateConverterService.java
├── ui/
│ └── CurrencyConverterMenu.java
└── Main.java
pom.xml


## Requisitos

- Java 17 o superior
- Maven
- Conexión a Internet
- Una API Key gratuita de [ExchangeRate-API](https://www.exchangerate-api.com/)

## Instalación y Ejecución

1. **Clona el repositorio o descarga el código.**

2. **Agrega tu API Key:**
   - Abre el archivo `ExchangeRateConverterService.java` en `src/main/java/conversormoneda/service/`.
   - Reemplaza `"tu_api_key"` por tu clave real en la línea:
     ```java
     private static final String API_KEY = "tu_api_key";
     ```

3. **Compila el proyecto con Maven:**
   ```bash
   mvn clean package
Ejecuta el programa:
Si usas IntelliJ, puedes correr la clase Main.java directamente.
O desde terminal, ejecuta:
bash
Copy Code
java -jar target/ConversorMonedas-1.0-SNAPSHOT-jar-with-dependencies.jar
Uso
Elige la moneda base y la moneda destino desde el menú.
Ingresa el monto a convertir.
El programa mostrará el resultado usando el tipo de cambio más reciente.
Créditos
ExchangeRate-API por el servicio de tipos de cambio.
Licencia
Este proyecto es de uso libre para fines educativos.
