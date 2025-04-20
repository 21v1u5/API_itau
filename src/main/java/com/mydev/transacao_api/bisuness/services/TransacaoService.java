package com.mydev.transacao_api.bisuness.services;

import com.mydev.transacao_api.controller.dtos.TransacaoRequestDTO;
import com.mydev.transacao_api.infrastructure.exceptions.UnprocessableEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransacaoService {


    private final List<TransacaoRequestDTO> listaTransacoes = new ArrayList<>();

    public void adicionarTransacoes(TransacaoRequestDTO dto) {

        log.info("Iniciada o processamento de gravar transacoes " + dto);

        if (dto.dataHora().isAfter(OffsetDateTime.now())) {
            log.error("Data e hora maiores que a data atual");
            throw new UnprocessableEntity("Data e hora maiores que a data e hora atuais");
        }
        if(dto.valor() < 0){
            log.error("Valor nao pode ser menor que 0");
            throw new UnprocessableEntity("Valor nao pode ser menor que 0");
        }

        listaTransacoes.add(dto);
        log.info("Transacoes adicionadas com sucesso");
    }

    public void limparTransacoes(){
        log.info("Iniciado o processamento para deletar transacoes");
        listaTransacoes.clear();
        log.info("Transacoes deletadas com sucesso");
    }

    public List<TransacaoRequestDTO> buscarTransacoes(Integer intervaloBusca){
        log.info("Iniciadas buscar de transacoes por tempo " + intervaloBusca);
        OffsetDateTime dataHoraIntervalo = OffsetDateTime.now().minusSeconds(intervaloBusca);

        log.info("Retorno de transacoes com sucesso");
        return listaTransacoes.stream()
                .filter(transacao -> transacao.dataHora().isAfter(dataHoraIntervalo)).toList();
    }
}
