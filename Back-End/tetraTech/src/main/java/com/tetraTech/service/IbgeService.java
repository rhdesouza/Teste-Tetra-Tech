package com.tetraTech.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.tetraTech.model.Populacao;

@Service
public class IbgeService {
	public static final String servicoIbge = "https://servicodados.ibge.gov.br/api/v1/projecoes/populacao";
	public static final String pathArquivo = "LogsIBGE.txt";

	public ResponseEntity<?> projecoesPopulacao(String dataHoraProj) {
		ResponseEntity<?> responseIbge = null;
		RestTemplate restTemplate = new RestTemplate();
		String dataHoraReq = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss"));

		try {
			responseIbge = restTemplate.getForEntity(servicoIbge, Populacao.class);
			Long projecaoPopulacional = calculaProjecao(dataHoraProj, (Populacao) responseIbge.getBody());
			this.escreverArquivo(dataHoraReq, dataHoraProj, (Populacao) responseIbge.getBody(), projecaoPopulacional);

			return new ResponseEntity<Long>(projecaoPopulacional, HttpStatus.OK);

		} catch (HttpStatusCodeException ex) {
			ex.printStackTrace();
			return new ResponseEntity<String>(ex.getResponseBodyAsString(), ex.getResponseHeaders(),
					ex.getStatusCode());

		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (DateTimeParseException ex) {
			ex.printStackTrace();
			return new ResponseEntity<String>("Campo dataHoraProj formato inválido (dd-MM-aaaa HH:mm:ss)",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public void criarArquivo() throws IOException {
		if (!new File(pathArquivo).exists()) {
			File f = new File(pathArquivo);
			f.createNewFile();
		}
	}

	private void escreverArquivo(String dataHoraReq, String dataHoraProj, Populacao populacao,
			Long projecaoPopulacional) throws IOException {
		this.criarArquivo();

		StringBuilder dados = new StringBuilder();
		dados.append(dataHoraReq).append(" ").append(dataHoraProj.replaceAll("-", "/")).append(" ")
				.append(populacao.getProjecao().getPopulacao()).append(" ").append(projecaoPopulacional);

		File arquivo = new File(pathArquivo);

		FileWriter fw = new FileWriter(arquivo, true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(dados.toString());
		bw.newLine();

		bw.close();
		fw.close();
	}

	public ResponseEntity<?> lerArquivo() throws IOException {
		List<String> logs = new ArrayList<String>();
		this.criarArquivo();

		BufferedReader buffRead = new BufferedReader(new FileReader(pathArquivo));
		String linha = "";
		while (true) {
			if (linha != null) {
				System.out.println(linha);
				if (linha != "")
					logs.add(linha);
			} else
				break;
			linha = buffRead.readLine();
		}
		buffRead.close();
		Collections.reverse(logs);
		if (logs.size() > 9)
			return new ResponseEntity<List<String>>(logs.subList(0, 10), HttpStatus.OK);
		else
			return new ResponseEntity<List<String>>(logs, HttpStatus.OK);

	}

	private Long calculaProjecao(String dataHoraProj, Populacao populacaoIbge) {
		long incremento = 0;
		long projecaoPopulacional;

		LocalDateTime horarioServicoIbge = LocalDateTime.parse(populacaoIbge.getHorario(),
				DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

		LocalDateTime dataHoraProjFormatado = LocalDateTime.parse(dataHoraProj,
				DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

		Duration duration = Duration.between(horarioServicoIbge, dataHoraProjFormatado);
		long mili = duration.getSeconds() * 1000;
		if (mili > 0)
			incremento = Math.round(mili / populacaoIbge.getProjecao().getPeriodoMedio().getIncrementoPopulacional());
		projecaoPopulacional = populacaoIbge.getProjecao().getPopulacao() + incremento;

		return projecaoPopulacional;

	}

}
