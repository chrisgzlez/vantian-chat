# Vantian-chat
Aplicación P2P empleando Java RMI

## Estructura App
- GUI
  Paquete de gui con Java Swing
- server.java
- cliente.java
- api.java
  Interfaz de conexión entre la _gui_ y el _core_. Podría considerarse el _ClientInterface_
- bd
  Paquete de la base de datos con su implementación e interfaz
- core
  Paquete con las funcionalidades y clases principales de la app. Aquí se lleva a cabo la lógica de la app

## Lógica Aplicación
- Básica:
  - Conectarse al server
  - Desconectarse al server (manejo de errores)
  - Envío/Recp Mssgs
    - Realizado de Cliente a cliente
    - El servidor no puede interferir en nada aquí
- Auth
  - Login
  - Signup
  - Server Auth
    - Validación del usuario en la bd
    - Generación de un *auth_token*
- Gestión Amigos
  - Busqueda de Amgios
  - Envío Solicitud
  - Aceptación de Solicitud
  - Eliminar Amigo
- Grupos de Amgios/Notificaciones
  - Notificar cuando amigo está online
  - Notificar cuando amigo está offline
## Base de Datos
- Usuario
  - user_name
  - password_hash
  - created_at
- Amistades
  - solicitante -> ref Usuario.user_name     
  - solicitado -> ref Usuario.user_name
  - activa -> 0 pendiente y 1 activa
     
