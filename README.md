# Bikeshare | Programação 2

Sistema de gerenciamento de bicicletas compartilhadas desenvolvido para a disciplina de Programação 2 (IC/UFAL).
Só a lógica de negócio é implementada. Ela é acessada por uma **Facade** e validada por testes de aceitação com o **EasyAccept**, sem interface gráfica e sem banco de dados.

## Escopo desta entrega (US1 a US9)

| US | Funcionalidade | O que cobre |
|---|---|---|
| US1 | Cadastro de usuário | Pessoa física (CPF) e pessoa jurídica (CNPJ e razão social), com plano avulso por padrão |
| US2 | Consulta de usuário | Leitura dos atributos de um usuário pelo identificador |
| US3 | Alteração de usuário | Alteração de dados cadastrais, plano e método de pagamento (cartão, Pix ou saldo pré-carregado) |
| US4 | Remoção de usuário | Remoção de usuários, bloqueada se houver aluguel em andamento |
| US5 | Cadastro de estação | Estação com nome, endereço e capacidade de vagas |
| US6 | Gestão de bicicletas | Bicicletas convencionais e elétricas (com nível de bateria) adicionadas a uma estação |
| US7 | Consulta de disponibilidade | Bicicletas disponíveis e vagas livres em cada estação |
| US8 | Retirada de bicicleta | Início do aluguel, com bloqueio de bicicleta indisponível, bateria insuficiente e aluguel simultâneo |
| US9 | Devolução de bicicleta | Fim do aluguel, devolução em estação com vaga e cálculo da tarifa conforme o plano |

## Tarifação

Os valores ficam em `parametros.properties`, fora do código:

- **Avulso:** cobra por minuto de uso.
- **Assinante:** paga mensalidade e tem uma franquia de minutos gratuitos. O excedente é cobrado por minuto.
- **Bicicleta elétrica:** adicional por minuto e nível mínimo de bateria para a retirada.
- **Carência:** aluguéis muito curtos não são cobrados.

## Estrutura

```
src/br/ufal/ic/p2/bikeshare/
├── Facade.java      ponto de entrada usado pelos testes
├── modelo/          usuário, estação, bicicleta, aluguel, plano, pagamento
├── servico/         regras de negócio de cada entidade
├── fabrica/         criação de usuários, bicicletas e métodos de pagamento
├── repositorio/     armazenamento em memória e geração de ids
├── sistema/         estado do sistema e persistência
├── config/          leitura do parametros.properties
├── comando/         execução de comandos
├── excecao/         exceções do domínio, agrupadas por categoria
└── util/            datas, valores monetários e texto
tests/               scripts de aceitação (usN.txt e usN_1.txt)
lib/easyaccept.jar   biblioteca de testes
```

## Como executar

1. Abra o projeto no IntelliJ (ou outra IDE) e adicione `lib/easyaccept.jar` como biblioteca.
2. Rode `src/Main.java` a partir da raiz do projeto. Ele executa os testes das US1 a US9 na Facade.
