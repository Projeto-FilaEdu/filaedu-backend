package br.com.filaEdu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.filaEdu.entity.Registro;

public interface RegistroRepository extends JpaRepository<Registro, Long> {

}
