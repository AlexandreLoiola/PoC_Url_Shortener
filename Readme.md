Short-URL-Service

# 1. Descrição
O serviço de encurtamento de URLs permite aos usuários gerar links curtos a partir de URLs longas, com a funcionalidade de redirecionamento para o destino original ao acessar o link curto. Além disso, o sistema registra o número de cliques em cada URL curta e oferece a possibilidade de configurar um tempo de expiração para os links.

# 2. Requisitos do Sistema
## 2.1 Requisitos Funcionais
- Encurtar Links: O sistema deve permitir que o usuário forneça uma URL longa e gere uma URL curta única.


- Redirecionar Links: O sistema deve redirecionar automaticamente o usuário para a URL original quando ele acessar a URL curta.


- Tracing (Rastreamento de Cliques): O sistema deve contar e registrar o número de cliques em cada link curto.


- Expiração: O sistema deve permitir configurar um tempo de expiração para os links curtos. Após esse período, o link não deverá redirecionar mais para a URL original.
## 2.2 Requisitos Não Funcionais
- **Confiabilidade:** Links curtos devem ser únicos e válidos.

# 3. Regras de Negócio
- **Unicidade do Link Curto:** Cada URL curta gerada deve ser única.


- **Expiração do Link Curto:** A URL curta deve ser automaticamente desativada após o tempo estipulado e não deverá redirecionar mais para a URL original.
Caso o tempo de expiração não seja configurado, a URL curta será válida indefinidamente.


- **Contagem de Cliques:** O número de cliques deve ser registrado toda vez que a URL curta for acessada, e o contador de cliques deve ser atualizado de forma atômica.
Não deve haver um limite máximo para o número de cliques, desde que o link esteja ativo (não expirado).


- **Limitações de Tamanho para a URL Curta:** O código da URL curta gerada deve ter um comprimento fixo e não pode ultrapassar o tamanho máximo de 50 caracteres.


- **Redirecionamento:** Quando um usuário acessa uma URL curta, o sistema deve realizar um redirecionamento HTTP (301 ou 302) para a URL original.


- **Logs de Acesso:** O sistema deve manter logs de cada acesso a uma URL curta, incluindo o número de cliques e dados como a data e hora do acesso. Esses logs podem ser usados para análise futura ou auditoria.