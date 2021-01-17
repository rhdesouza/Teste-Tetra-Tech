package com.tetraTech;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.tetraTech.model.Populacao;
import com.tetraTech.service.IbgeService;

@SpringBootTest
class TetraTechApplicationTests {

	@Autowired
	IbgeService ibgeService;

	@Test
	void contextLoads() {
	}

	@Test
	public void testeDisponibilidadeServicoIbge() {
		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Populacao> response = restTemplate.getForEntity(IbgeService.servicoIbge, Populacao.class);
			assertEquals(200, response.getStatusCode().value());
			
		} catch (HttpStatusCodeException ex) {
			assertEquals(200, ex.getStatusCode().value());
			fail("Servico IBGE fora do ar!");
		}

	}

}
