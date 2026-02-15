package br.com.filaEdu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.filaEdu.dto.MovimentoHoraDTO;
import br.com.filaEdu.dto.MovimentoSemanaDTO;
import br.com.filaEdu.dto.PicoDiaDTO;
import br.com.filaEdu.entity.Registro;
import br.com.filaEdu.service.RegistroService;

@RestController
@RequestMapping("/api/registros")
@CrossOrigin("*")
public class RegistroController {

	@Autowired
	private RegistroService registroService;

	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Registro registro) {
		try {
			String mensagem = registroService.save(registro);
			return ResponseEntity.ok(mensagem);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<Registro>> findAll() {
		try {
			List<Registro> lista = registroService.findAll();
			return ResponseEntity.ok(lista);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/movimento-semana")
	public MovimentoSemanaDTO movimentoSemana() {
		return registroService.buscarMovimentoSemana();
	}

	@GetMapping("/movimento-hora")
	public List<MovimentoHoraDTO>movimentoHora() {
		return registroService.movimentoPorHoraHoje();
	}

	@GetMapping("/pico-dia")
	public ResponseEntity<PicoDiaDTO> picoDia() {

		PicoDiaDTO pico = registroService.buscarPicoDia();

		if (pico == null) {
			return ResponseEntity.noContent().build(); // 204
		}

		return ResponseEntity.ok(pico);
	}

	@GetMapping("/total-hoje")
	public ResponseEntity<Long> totalHoje() {
		return ResponseEntity.ok(registroService.buscarTotalHoje());
	}
	
	@GetMapping("/hoje-vs-ontem")
	public ResponseEntity<Double> hojeVsOntem() {
	    return ResponseEntity.ok(registroService.buscarHojeVsOntem());
	}


}
