package br.com.filaEdu.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.filaEdu.dto.MovimentoHoraDTO;
import br.com.filaEdu.dto.MovimentoSemanaDTO;
import br.com.filaEdu.dto.PicoDiaDTO;
import br.com.filaEdu.entity.Registro;
import br.com.filaEdu.repository.RegistroRepository;


@Service
public class RegistroService {

	@Autowired
	RegistroRepository registroRepository;

	public String save(Registro registro){
		this.registroRepository.save(registro);
		return "Registro salvo com sucesso!";
	}

	public List<Registro> findAll(){
		List<Registro> lista = this.registroRepository.findAll();
		return lista;
	}

	public MovimentoSemanaDTO buscarMovimentoSemana() {
	    List<Object[]> atual = registroRepository.movimentoSemanaAtual();
	    List<Object[]> anterior = registroRepository.movimentoSemanaAnterior();

	    List<String> labels = Arrays.asList(
	        "Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado"
	    );

	    // Criar arrays com 7 posições já inicializados com 0
	    Integer[] semanaAtual = new Integer[7];
	    Integer[] semanaAnterior = new Integer[7];
	    Arrays.fill(semanaAtual, 0);
	    Arrays.fill(semanaAnterior, 0);

	    // Preencher com os valores do banco
	    for (Object[] r : atual) {
	        Integer dia = ((Number) r[0]).intValue();
	        Integer total = ((Number) r[1]).intValue();
	        semanaAtual[dia] = total;
	    }

	    for (Object[] r : anterior) {
	        Integer dia = ((Number) r[0]).intValue();
	        Integer total = ((Number) r[1]).intValue();
	        semanaAnterior[dia] = total;
	    }

	    return new MovimentoSemanaDTO(
	        labels, 
	        Arrays.asList(semanaAtual), 
	        Arrays.asList(semanaAnterior)
	    );
	}


	public List<MovimentoHoraDTO> movimentoPorHoraHoje() {

		List<Object[]> resultado = registroRepository.movimentoPorHoraHoje();
		List<MovimentoHoraDTO> resposta = new ArrayList<>();

		for (Object[] obj : resultado) {
			Number horaNumero = (Number) obj[0];
			Number totalNumero = (Number) obj[1];

			int hora = horaNumero.intValue();
			long total = totalNumero.longValue();

			String horaFormatada = String.format("%02dh", hora);
			resposta.add(new MovimentoHoraDTO(horaFormatada, total));
		}

		return resposta;
	}

	public PicoDiaDTO buscarPicoDia() {

	    Object resultado = registroRepository.picoDoDia();

	    if (resultado == null) {
	        return null;
	    }

	    Object[] linha = (Object[]) resultado;

	    Integer hora = ((Number) linha[0]).intValue();
	    Integer total = ((Number) linha[1]).intValue();

	    String intervalo = String.format("%02dh - %02dh", hora, hora + 1);

	    return new PicoDiaDTO(intervalo, total);
	}

}
