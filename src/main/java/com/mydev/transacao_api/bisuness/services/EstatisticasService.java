package com.mydev.transacao_api.bisuness.services;

import com.mydev.transacao_api.controller.dtos.EstatisticasResponseDTO;
import com.mydev.transacao_api.controller.dtos.TransacaoRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EstatisticasService {

    public final TransacaoService transacaoService;

    public EstatisticasResponseDTO calcularEstatisticasTransacoes(Integer intervaloBusca){

        log.info("Iniciada a busca de estatisticas de transacoes pelo tempo " + intervaloBusca);
        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(intervaloBusca);

        if(transacoes.isEmpty()){
            return new EstatisticasResponseDTO(0L, 0.0, 0.0, 0.0, 0.0);
        }

        DoubleSummaryStatistics estatisticasTransacoes = transacoes.stream()
                .mapToDouble(TransacaoRequestDTO::valor).summaryStatistics();

        log.info("Estatisticas retornadas com sucesso");

        return new EstatisticasResponseDTO(estatisticasTransacoes.getCount(),
                estatisticasTransacoes.getSum(),
                estatisticasTransacoes.getAverage(),
                estatisticasTransacoes.getMin(),
                estatisticasTransacoes.getMax());
    }
}
