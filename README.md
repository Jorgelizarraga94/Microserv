# 🛒 Arquitectura de Microservicios - Backend

Sistema basado en una arquitectura de microservicios desarrollado en **Java** y **Spring Boot**, diseñado para gestionar productos, carritos de compras y ventas de forma escalable y desacoplada.

## 🌐 Acceso y Decisiones de Despliegue

* **Frontend en Producción:** Para ver el proyecto funcionando, se puede ingresar a https://micro-serv-front-end-react.vercel.app.
* **Nota sobre la infraestructura:** Originalmente se planificó levantar el proyecto utilizando una máquina virtual en **Oracle Cloud Infrastructure (OCI)** para luego configurar el servidor y llevarlo a producción por ese medio. Sin embargo, los recursos de la capa gratuita ofrecían muy poca memoria RAM, lo que impedía que los microservicios arrancaran correctamente debido a problemas de falta de memoria (*Out of Memory*). Por este motivo, se optó por una arquitectura híbrida para la demostración: los microservicios se ejecutan localmente mediante **Docker** y se conectan con la web utilizando un túnel seguro con **Ngrok**.

---

## 🚀 Tecnologías y Herramientas Utilizadas

* **Java** (Versión 17+)
* **Spring Boot**
* **Spring Cloud** (Netflix Eureka Server & Spring Cloud Gateway)
* **Spring Security & OAuth2 Resource Server** (Autenticación mediante JWT con Auth0)
* **MySQL** (Base de datos relacional)
* **Docker & Docker Compose** (Contenedorización y orquestación local)

---

## 🏗️ Estructura de los Microservicios

El sistema se compone de los siguientes módulos:

1. **`eureka-server`** (Puerto 8761): Servidor de descubrimiento de servicios. Permite que los microservicios se registren y se descubran dinámicamente.
2. **`api-gateway`** (Puerto 8080): Puerta de entrada única (Proxy Reverso). Maneja el enrutamiento inteligente, las políticas globales de **CORS** y la validación de seguridad mediante tokens JWT.
3. **`product-service`**: Microservicio encargado de la gestión y exposición del catálogo de productos.
4. **`cart-service`**: Microservicio para el manejo de los carritos de compras de los usuarios.
5. **`sales-service`**: Microservicio para el procesamiento y registro de ventas/pedidos.

---

## ⚙️ Configuración y Ejecución Local con Docker

Asegurate de tener instalado **Docker** y **Docker Compose** en tu equipo.

1. Cloná este repositorio:
   git clone <URL_DE_TU_REPOSITORIO>
   cd <carpeta-del-proyecto>

2. Configura las variables de entorno necesarias (crea un archivo .env basado en las credenciales correspondientes para la base de datos y Auth0).
3. Levanta toda la infraestructura utilizando Docker Compose:
  docker compose up --build -d

3.Verificación:
  Eureka Dashboard: http://localhost:8761
  API Gateway: http://localhost:8080   
