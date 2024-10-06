# Prueba Técnica Inditex Core Platform

## 📌 Resumen
Este proyecto implementa una API REST que expone un endpoint para, dado un `product_id`, un `brand_id` y una `date` concreta, recuperar la información del precio de ese producto en ese momento.

## 💡 Enfoque de la solución
Para resolver este desafío:
- se ha aplicado arquitectura hexagonal, vertical slicing y DDD como buenas prácticas de patrones de diseño.
- la aplicación se construye usando **Java 21** y **Spring Boot 3.3.4**. Y Maven para la gestión de dependencias y automatización de la build.
- se adopta JUnit 5 para implementar integration tests y unit tests.
- se asegura la cobertura de las pruebas solicitadas mediante quality assurance tests.
- se gestiona la base de datos con un modelo de persistencia en memoria H2.
- se incluye un GitHub workflow básico para validar que las Pull Requests tengan una build estable.

### 📁 Estructura del proyecto
```
src  
 ├── main  
 │   ├── java  
 │   │   └── com.inditex.challenge.coreplatform  
 │   │       ├── prices  
 │   │       │   ├── application    # Business logic  
 │   │       │   ├── domain         # Domain models  
 │   │       │   ├── infrastructure # Repositories and controllers  
 │   │       └── shared  
 │   │           └── exceptions     # Logic for custom exceptions  
 │   └── resources    # Database schema & data
 └── test  
     ├── unittests  
     ├── integrationtests  
     └── qa    # Use case testing
```

## 🚀 Ejecutando la Aplicación
0. Requisitos: Java 21, Maven, Git
1. Clonar el repositorio
2. Build el proyecto
   ```
   mvn clean install
   ```
3. Lanzar la aplicación
   ```
   mvn spring-boot:run
   ```
4. La aplicación estará disponible en `http://localhost:8080`

### 📝 Endpoint
El único endpoint que implementa la aplicación es:
* GET '/prices'
  - Con tres parámetros:
    * 'productId' (Integer): id del producto
    * 'brandId' (Integer): id de la marca
    * 'date' (LocalDateTime): la fecha en la que se consulta el precio del artículo

Ejemplo de request:
```
curl "http://localhost:8080/prices?productId=35455&brandId=1&date=2020-06-14T10:00:00"
```
### 🧪 Casos de prueba
El enunciado esperaba que se validaran cinco casos de prueba, estos casos se han probado tanto manualmente como automatizados mediante una batería de tests de integración en la clase `PriceQATests.java`.
Estos tests se ejecutan cada vez que se lanza la aplicación o se hace una build del proyecto (también en la action `Maven Build Check`, automática en cada PR).
