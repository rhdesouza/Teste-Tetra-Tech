package com.tetraTech.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tetraTech.service.IbgeService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/tetratech")
public class IbgeController {

	@Autowired
	IbgeService ibgeService;

	@ApiOperation(value = "Serviço de cálculo populacional")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna o cálculo realizado."),
			@ApiResponse(code = 401, message = "Não autorizado."),
			@ApiResponse(code = 403, message = "Acesso proibido."),
			@ApiResponse(code = 404, message = "API IBGE indisponivel."),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção.") })
	@GetMapping("/ibge/projecao/{dataHoraProj}")
	private ResponseEntity<?> calculoPopulacional(@PathVariable(name = "dataHoraProj") String dataHoraProj) {
		return ibgeService.projecoesPopulacao(dataHoraProj);
	}

	@ApiOperation(value = "Serviço de busca logs IBGE")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna as 10 ultimas chamadas."),
			@ApiResponse(code = 401, message = "Não autorizado."),
			@ApiResponse(code = 403, message = "Acesso proibido."),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção.") })
	@GetMapping("/ibge/getLogs")
	private ResponseEntity<?> getLogs() throws IOException {
		return ibgeService.lerArquivo();
	}
	
}
