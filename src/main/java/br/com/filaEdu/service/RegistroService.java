package br.com.filaEdu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public String update(Registro registro, Long id) {
		registro.setId(id);
		this.registroRepository.save(registro);
		return "Registro alterado com sucesso!";
	}
	
	public String delete(Long id) {
		this.registroRepository.deleteById(id);
		return "Registro deletado com sucesso!";
	}
	
	public List<Registro> findAll(){
		List<Registro> lista = this.registroRepository.findAll();
		return lista;
	}
	
	public Registro findById(Long id) {
		Registro registro = this.registroRepository.findById(id).get();
		return registro;
	}

}
