package br.com.filaEdu.dto;

import java.util.List;

public record MovimentoSemanaDTO(
    List<String> labels,
    List<Integer> semanaAtual,
    List<Integer> semanaAnterior
) {}
