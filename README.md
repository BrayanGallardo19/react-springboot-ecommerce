# 🛒 React + Spring Boot E-commerce

Aplicación **Full Stack de comercio electrónico** desarrollada con **React + TypeScript** en el frontend y **Java + Spring Boot** en el backend. El proyecto implementa autenticación con JWT, gestión de productos, pedidos, pagos, promociones y distintos perfiles de usuario, con persistencia en MySQL y entorno de ejecución mediante Docker Compose.

---

## 🎯 Objetivo del proyecto

Construir una plataforma e-commerce completa separando claramente la interfaz de usuario, la lógica de negocio y la persistencia de datos.

El proyecto permite practicar y demostrar un flujo Full Stack moderno:

```text
React + TypeScript
        │
        │ HTTP / REST
        ▼
Spring Boot API
        │
        │ JPA / Hibernate
        ▼
      MySQL
```

Además, la aplicación contempla distintos contextos de uso mediante vistas específicas para usuarios públicos, clientes, trabajadores y administradores.

---

## ✨ Funcionalidades principales

- Registro e inicio de sesión de usuarios
- Autenticación y autorización mediante **JWT**
- Gestión de productos
- Gestión de pedidos
- Registro y seguimiento de pagos asociados a pedidos
- Gestión de promociones
- Administración de usuarios y recursos del sistema
- Interfaces diferenciadas según tipo de usuario
- Consumo de la API REST desde React mediante Axios
- Manejo de estado y consultas asíncronas en el frontend
- Persistencia de datos con MySQL
- Documentación de API mediante Swagger / OpenAPI
- Ejecución del backend y la base de datos mediante Docker Compose

> El módulo de pagos registra la información del pago y su referencia externa dentro de la aplicación. No representa una integración directa con una pasarela de pagos externa.

---

## 🏗️ Arquitectura

```text
┌──────────────────────────────────────────────┐
│              React Frontend                  │
│                                              │
│  Public · Client · Worker · Admin            │
│                                              │
│  React Router · Axios · React Query · Zustand│
└──────────────────────┬───────────────────────┘
                       │
                       │ REST API
                       ▼
┌──────────────────────────────────────────────┐
│             Spring Boot Backend              │
│                                              │
│ Auth · Products · Orders · Payments          │
│ Promotions · Users · Administration          │
│                                              │
│ Spring Security · JWT · JPA · Validation     │
└──────────────────────┬───────────────────────┘
                       │
                       │ JPA / Hibernate
                       ▼
                ┌─────────────┐
                │    MySQL    │
                └─────────────┘
```

---

## 🛠️ Stack tecnológico

### Frontend

| Tecnología | Uso |
|---|---|
| React 19 | Construcción de la interfaz |
| TypeScript | Tipado estático |
| Vite | Desarrollo y build |
| React Router | Navegación y rutas |
| Axios | Consumo de la API REST |
| TanStack React Query | Gestión de consultas y estado remoto |
| Zustand | Estado global del cliente |
| Lucide React | Iconografía |

### Backend

| Tecnología | Uso |
|---|---|
| Java 21 | Lenguaje principal |
| Spring Boot 3.5 | Framework backend |
| Spring Web | API REST |
| Spring Security | Seguridad y autorización |
| JWT | Autenticación basada en tokens |
| Spring Data JPA | Persistencia |
| Hibernate | ORM |
| Bean Validation | Validación de datos |
| Spring Boot Actuator | Endpoints de monitoreo |
| Swagger / OpenAPI | Documentación de la API |
| Lombok | Reducción de código boilerplate |

### Datos e infraestructura

| Tecnología | Uso |
|---|---|
| MySQL 8 | Base de datos principal |
| H2 | Base de datos auxiliar disponible en runtime |
| Docker | Contenedorización del backend |
| Docker Compose | Orquestación de backend + MySQL |
| Maven | Gestión de dependencias y build backend |

---

## 📂 Estructura del repositorio

```text
react-springboot-ecommerce/
├── docker-compose.yml
│
├── ecommerce-backend/
│   ├── Dockerfile
│   ├── pom.xml
│   └── src/
│       ├── main/java/.../
│       │   ├── config/
│       │   ├── controller/
│       │   ├── data/
│       │   ├── dto/
│       │   ├── entity/
│       │   ├── enums/
│       │   ├── repository/
│       │   ├── security/
│       │   └── service/
│       └── main/resources/
│
└── ecommerce-front/
    ├── package.json
    ├── vite.config.ts
    └── src/
        ├── components/
        ├── contexts/
        ├── lib/
        ├── pages/
        │   ├── admin/
        │   ├── client/
        │   ├── public/
        │   └── worker/
        └── types.ts
```

---

## 🔐 Seguridad

El backend utiliza **Spring Security + JWT** para proteger los endpoints de la API.

El flujo general de autenticación es:

```text
Usuario
   │
   ▼
Login
   │
   ▼
Spring Security
   │
   ▼
JWT generado
   │
   ▼
Frontend almacena/utiliza el token
   │
   ▼
Requests autenticadas a la API
```

Las distintas áreas del frontend permiten representar diferentes perfiles de acceso, como cliente, trabajador y administrador.

---

## 📦 Módulos principales del backend

La API está organizada en controladores y servicios por dominio de negocio:

- **Auth** — autenticación de usuarios
- **Products** — catálogo y gestión de productos
- **Orders** — creación y gestión de pedidos
- **Payments** — registro de pagos asociados a pedidos
- **Promotions** — gestión de promociones
- **Users** — gestión de usuarios
- **Admin** — operaciones administrativas

---

## ▶️ Cómo ejecutar el proyecto

### Requisitos

Para ejecutar todo el proyecto necesitas:

- Docker + Docker Compose
- Node.js y npm para el frontend

Si prefieres ejecutar el backend fuera de Docker también necesitarás Java 21.

### 1. Clonar el repositorio

```bash
git clone https://github.com/BrayanGallardo19/react-springboot-ecommerce.git
cd react-springboot-ecommerce
```

### 2. Levantar MySQL y el backend

Desde la raíz del proyecto:

```bash
docker compose up --build
```

Docker Compose levanta:

```text
MySQL   → localhost:3306
Backend → localhost:8081
```

El contenedor de MySQL incluye un `healthcheck`, por lo que el backend espera a que la base de datos esté disponible antes de iniciar.

### 3. Levantar el frontend

En otra terminal:

```bash
cd ecommerce-front
npm install
npm run dev
```

Vite mostrará en consola la URL local del frontend.

---

## 💻 Ejecución del backend sin Docker

Si tienes MySQL disponible localmente puedes ejecutar el backend directamente con Maven:

```bash
cd ecommerce-backend
./mvnw spring-boot:run
```

En Windows:

```powershell
mvnw.cmd spring-boot:run
```

Debes configurar previamente la conexión a la base de datos y el secreto utilizado para firmar los JWT.

---

## 📖 Documentación de la API

El backend incluye **springdoc OpenAPI / Swagger UI**.

Con el backend ejecutándose, la documentación puede consultarse normalmente en:

```text
http://localhost:8081/swagger-ui/index.html
```

Si ejecutas Spring Boot directamente sin el mapeo de puertos de Docker Compose, utiliza el puerto configurado por la aplicación.

---

## 🐳 Docker Compose

El archivo `docker-compose.yml` orquesta actualmente:

- una instancia **MySQL 8**
- el **backend Spring Boot**
- almacenamiento persistente mediante un volumen Docker
- healthcheck de MySQL
- configuración de datasource y JWT mediante variables de entorno

El frontend se ejecuta de forma independiente mediante Vite durante el desarrollo.

---

## 📌 Posibles mejoras

Algunas evoluciones posibles del proyecto:

- Integrar una pasarela de pagos real
- Dockerizar también el frontend
- Incorporar pruebas de integración y E2E
- Agregar CI/CD con GitHub Actions
- Incorporar refresh tokens
- Añadir almacenamiento externo para imágenes de productos
- Desplegar frontend, backend y base de datos en infraestructura cloud

---

## 👤 Autor

**Brayan Gallardo**

Analista Programador · Full Stack · Data & Automation

GitHub: [@BrayanGallardo19](https://github.com/BrayanGallardo19)
