# Cinema Ciencias Registry Service

**Eureka Server** — el servicio de registro y descubrimiento de los microservicios de
Cinema Ciencias. Es la "agenda telefónica" del sistema: los microservicios se
registran aquí y se descubren entre sí.

## Stack

| Capa | Tecnología |
|---|---|
| Lenguaje | Kotlin 2.2.21 |
| Framework | Spring Boot 4.0.3 + Spring Cloud Netflix Eureka Server |
| Build | Gradle 9.3.1 (wrapper) |
| Base de datos | Ninguna |
| Puerto | **8761** |

## Prerequisitos

- **JDK 21** instalado y disponible en `JAVA_HOME`

## Puesta en marcha

```bash
./gradlew bootRun
```

El servidor queda escuchando en **http://localhost:8761**.

## Verificación

Abrí en el navegador (o con curl):

```bash
curl http://localhost:8761/eureka/
```

Deberías ver la **consola web de Eureka** con la lista de instancias registradas
(por ejemplo `GATEWAY-SERVICE` cuando el gateway esté arriba).

Los endpoints REST de Eureka viven en `http://localhost:8761/eureka/...`.

## Configuración

`src/main/resources/application.yaml`:

```yaml
server:
  port: 8761
eureka:
  client:
    register-with-eureka: false   # no se registra a sí mismo
    fetch-registry: false         # no descarga registros (modo standalone)
```

## Integración con el ecosistema

**Consumidores** (services que se registran acá):

| Servicio | Config |
|---|---|
| Gateway (9000) | `GATEWAY-SERVICE` |
| Auth (8082) | `CINEMA_CIENCIAS_AUTH_SERVICE` |
| Cinema Ciencias (8089) | `CINEMA-CIENCIAS` |
| Audit (8081) | `AUDIT-SERVICE` |

La URL de Eureka (`http://localhost:8761/eureka/`) la sirve el Config Server a todos los clientes.

**Orden de arranque sugerido** (de la tesis):

1. Config Service (8888)
2. **Registry Service (este, 8761)**
3. Gateway Service (9000)
4. Resto de microservicios

## Tests

```bash
./gradlew test
```

(Solo hay un smoke test de contexto.)

## Notas

- Corre en modo **standalone**: no se registra con sí mismo ni consulta otros
  registries.
- Es un servicio sin estado: no requiere base de datos ni infraestructura externa.