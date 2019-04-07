# Invillia-Challenge
Invillia Challenge

A arquitetura desenvolvida para esse desafio é a arquitetura de microserviços que está dividida em:

* invillia-Wrapper: API responsável por receber as requisições externas a aplicação ou até mesmo de front-end, gerindo todas as requisições para os serviços que possuem a regra de negócio;  
* invillia-Security: API responsável pela segurança da aplicação onde possui os usuários, domínios e regras de acesso. Quando o Wrapper solicita o login, é gerado um token (JWT) para dar acesso aos outros microserviços;  
* invillia-Store: API responsável por gerenciar as Stores;
* invillia-Order: API responsável por gerenciar as orders e items;
* invillia-Billing: API responsável por gerenciar os payments;
    
Itens contemplados:

* Database (H2, MYSQL, Liquibase)
* Docker (Criado script para containers de banco e microserviço, desenvolvido em um servidor Ubuntu-Server (simulado ambiente de produção)
* Security (JWT, AuthenticationFilter nas API's)
* Swagger (Documentação realizada na API Wrapper, única API externa)
* Clean Code

* WireMockServer (Configurado no wrapper para simular as requisições aos microserviços)
* Groovy (Teste funcional de exemplo, apenas realizado no microservice invillia-Store, Rest asurred, Framework spock)
* Gradle (Gerenciamento de pacotes) - Não utilizei Maven

Item não contemplado:
Devido ao tempo para realização do desafio não consegui implementar os testes em todas as API's;
Não realizada a funcionalidade de reembolso;
