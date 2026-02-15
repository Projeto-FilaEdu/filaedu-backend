package br.com.filaEdu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.filaEdu.entity.Registro;

public interface RegistroRepository extends JpaRepository<Registro, Long> {

    @Query(value = """
        SELECT
          EXTRACT(DOW FROM (r.data_hora AT TIME ZONE 'America/Sao_Paulo')::date) AS dia,
          SUM(r.total_acumulado) AS total
        FROM registro r
        WHERE
          (r.data_hora AT TIME ZONE 'America/Sao_Paulo')::date >=
          date_trunc(
            'week',
            (NOW() AT TIME ZONE 'America/Sao_Paulo')::date
          )
        GROUP BY dia
        ORDER BY dia
    """, nativeQuery = true)
    List<Object[]> movimentoSemanaAtual();

    @Query(value = """
        SELECT
          EXTRACT(DOW FROM (r.data_hora AT TIME ZONE 'America/Sao_Paulo')::date) AS dia,
          SUM(r.total_acumulado) AS total
        FROM registro r
        WHERE
          (r.data_hora AT TIME ZONE 'America/Sao_Paulo')::date >=
            date_trunc(
              'week',
              (NOW() AT TIME ZONE 'America/Sao_Paulo')::date
            ) - INTERVAL '7 days'
          AND
          (r.data_hora AT TIME ZONE 'America/Sao_Paulo')::date <
            date_trunc(
              'week',
              (NOW() AT TIME ZONE 'America/Sao_Paulo')::date
            )
        GROUP BY dia
        ORDER BY dia
    """, nativeQuery = true)
    List<Object[]> movimentoSemanaAnterior();

    @Query(value = """
        SELECT
          EXTRACT(HOUR FROM (r.data_hora AT TIME ZONE 'America/Sao_Paulo')) AS hora,
          SUM(r.total_acumulado) AS total
        FROM registro r
        WHERE
          (r.data_hora AT TIME ZONE 'America/Sao_Paulo')::date =
          (NOW() AT TIME ZONE 'America/Sao_Paulo')::date
        GROUP BY hora
        ORDER BY hora
    """, nativeQuery = true)
    List<Object[]> movimentoPorHoraHoje();

    @Query(value = """
        SELECT
          EXTRACT(HOUR FROM (r.data_hora AT TIME ZONE 'America/Sao_Paulo')) AS hora,
          SUM(r.total_acumulado) AS total
        FROM registro r
        WHERE
          (r.data_hora AT TIME ZONE 'America/Sao_Paulo')::date =
          (NOW() AT TIME ZONE 'America/Sao_Paulo')::date
        GROUP BY hora
        ORDER BY total DESC
        LIMIT 1
    """, nativeQuery = true)
    Object picoDoDia();

    @Query(value = """
        SELECT COALESCE(SUM(r.total_acumulado), 0)
        FROM registro r
        WHERE
          (r.data_hora AT TIME ZONE 'America/Sao_Paulo')::date =
          (NOW() AT TIME ZONE 'America/Sao_Paulo')::date
    """, nativeQuery = true)
    Long totalHoje();

    @Query(value = """
        SELECT
          CASE
            WHEN ontem.total = 0 THEN 0
            ELSE ROUND(((hoje.total - ontem.total) * 100.0) / ontem.total, 2)
          END
        FROM
        (
          SELECT COALESCE(SUM(total_acumulado), 0) AS total
          FROM registro
          WHERE
            (data_hora AT TIME ZONE 'America/Sao_Paulo')::date =
            (NOW() AT TIME ZONE 'America/Sao_Paulo')::date
        ) hoje,
        (
          SELECT COALESCE(SUM(total_acumulado), 0) AS total
          FROM registro
          WHERE
            (data_hora AT TIME ZONE 'America/Sao_Paulo')::date =
            (NOW() AT TIME ZONE 'America/Sao_Paulo')::date - INTERVAL '1 day'
        ) ontem
    """, nativeQuery = true)
    Double hojeVsOntemPercentual();

}
