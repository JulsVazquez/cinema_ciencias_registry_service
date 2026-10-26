package mx.unam.fciencias.cinema_ciencias.registry_service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@SpringBootApplication
@EnableEurekaServer
class CinemaCienciasRegistryServiceApplication

fun main(args: Array<String>) {
	runApplication<CinemaCienciasRegistryServiceApplication>(*args)
}
