#  API transacao

Este projeto é uma API rest para gerencimanto de transações e calcular estatísticas das transações realizadas.
API foi desenvolvida em Java e Spring boot.

## Documentação da API

#### Receber Transações

```http
  POST /transacao
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `valor` | `BigDecimal` | **Obrigatório**. O valor da transação 
| `dataHora` | `OffsetDateTime` | **Obrigatório**. O horário que a transação ocorreu

#### Limpar Transações

```http
  DELETE /transacao
```

#### Calcular Estatísticas

```http
  GET /estatistica
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `intervaloSegundos` | `integer` | **Não Obrigatório** O padrão default é 60s  |

# Diferenciais

### Logs
### Tratamento de erros
### Documentação da API (swagger)
### Documentação do Sistema
### Configuração > Você consegue alterar os segundos da relação dos dados estatísticos (padrão 60s)
