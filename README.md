# 🩺 SINAN API - Sistema de Notificação de Agravos

Projeto desenvolvido como atividade prática para a disciplina de **Programação para a Web I** do curso de **Análise e Desenvolvimento de Sistemas** (IFPB — Campus Cajazeiras), sob orientação do Prof. Renê Douglas Nobre de Morais.

O sistema consiste em uma API REST desenvolvida em **Spring Boot** integrada a um front-end em **HTML/CSS/JavaScript puro** (Vanilla JS) para o gerenciamento de Fichas de Notificação Compulsória do SINAN (Ministério da Saúde).

---

## 🚀 Tecnologias Utilizadas

- **Java 17 / Spring Boot 3+**
- **Spring Data JPA & Hibernate**
- **H2 Database** (Banco de dados em memória)
- **Jakarta Validation** (Validação de entrada de dados)
- **Lombok**
- **HTML5, CSS3 & JavaScript (Vanilla JS / Fetch API)**
- **Maven**

---

## 📌 Funcionalidades e Regras de Negócio Implementadas

### 1. Modelo de Dados Completo (Ficha SINAN NET)
A entidade `Agravo` mapeia integralmente todos os atributos exigidos na **Ficha de Notificação Compulsória do SINAN NET**, cobrindo dados gerais da notificação, dados pessoais do paciente, dados de residência, dados de investigação e identificação do responsável.

### 2. Tratamento de Erros no Padrão RFC 9457 (Problem Detail)
Respostas de erro da API seguem rigorosamente a especificação **RFC 9457**, utilizando a classe `ProblemDetail` do Spring Boot no `GlobalExceptionHandler` para retornar payloads padronizados em exceções de validação (`@Valid`), erros de formato JSON e violações de regras de negócio (`IllegalArgumentException`).

### 3. Regras de Negócio (RN)
- **RN01 - Filtro de Duplicidades (`GET /agravos?duplicadas=true`):** Identifica e lista notificações que possuem mesmo Agravo/Doença, mesmo Nome de Paciente, mesmo Nome da Mãe, mesma Data de Nascimento e com intervalo de Data de Notificação de até 3 dias (diferença <= 3 dias).
- **RN02 - Obrigatoriedade Condicional de Gestante:** Pacientes do sexo feminino (>= 7 anos) exigem o preenchimento do período gestacional. Para pacientes do sexo masculino ou crianças menores de 7 anos, o sistema define automaticamente como `NAO_SE_APLICA`.
- **RN03 - Regras de Residência:** Se o País de Residência for "Brasil", os campos `ufResidencia` e `municipioResidencia` tornam-se obrigatórios. Caso o paciente resida no exterior, a UF não é exigida.

---

## 🤖 Uso de Inteligência Artificial (IA)

Conforme as diretrizes de desenvolvimento da atividade, o uso de Inteligência Artificial Generativa (IA) foi empregado como ferramenta de apoio e aceleração para as seguintes tarefas específicas:

1. **Modelagem de Dados Completa (`Agravo.java`):** Mapeamento JPA, validações e Enums correspondentes a todos os atributos da Ficha Oficial de Preenchimento do SINAN NET.
2. **Tratamento de Exceções (`GlobalExceptionHandler`):** Estruturação dos handlers de exceção adaptados ao padrão de resposta **RFC 9457** (`ProblemDetail`).
3. **Desenvolvimento do Front-End (Vanilla JS):** Arquivo `index.html` estático contendo formulário de cadastro, validações em tempo real no cliente, tabela dinâmica e integração com a Fetch API.

