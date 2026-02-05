package br.com.filaEdu.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Registro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long lojaId;
	private Date dataHora;
	private int totalAcumulado;
	
	public Registro() {
		
	}
	
	public Registro(Long id, Long lojaId, Date dataHora, int totalAcumulado) {
		this.id = id;
		this.lojaId = lojaId;
		this.dataHora = dataHora;
		this.totalAcumulado = totalAcumulado;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getLojaId() {
		return lojaId;
	}
	public void setLojaId(Long lojaId) {
		this.lojaId = lojaId;
	}
	public Date getDataHora() {
		return dataHora;
	}
	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}
	public int getTotalAcumulado() {
		return totalAcumulado;
	}
	public void setTotalAcumulado(int totalAcumulado) {
		this.totalAcumulado = totalAcumulado;
	}

}
