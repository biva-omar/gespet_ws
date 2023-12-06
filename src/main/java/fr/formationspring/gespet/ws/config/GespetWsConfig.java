package fr.formationspring.gespet.ws.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import fr.formationspring.gespet.business.config.GespetBusinessConfig;

@Import({ GespetBusinessConfig.class })

@ComponentScan(basePackages = { "fr.formationspring.gespet.ws.controller" })
public class GespetWsConfig {

}
