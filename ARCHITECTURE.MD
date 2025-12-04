# 🏛️ Guia de Arquitetura: Clean Architecture & Hexagonal

Este projeto segue os princípios da **Clean Architecture** (Robert C. Martin) e **Arquitetura Hexagonal** (Ports and Adapters). O objetivo principal é proteger as Regras de Negócio (Domínio) de mudanças em tecnologias externas (Banco de Dados, Frameworks, UI).

## 📂 Estrutura de Pastas e Responsabilidades

### 1. `domain` (O Núcleo / Core)
> **Regra de Ouro:** Aqui só entra **Java Puro**. Proibido importar bibliotecas externas, Spring, Hibernate ou qualquer framework.

* **`model`**: As Entidades de Negócio. Diferente das entidades do banco, estas são ricas em comportamento e validam a si mesmas.
    * *Ex:* `Carteira.java` (contém a lógica de `creditar`, valida `saldo`).
* **`ports`**: As Interfaces (Contratos). Definem como o mundo externo interage com o núcleo.
    * **`input`**: O que o sistema sabe fazer (Casos de Uso). *Ex:* `CriarCarteiraUseCase`.
    * **`output`**: O que o sistema precisa que façam por ele (Acesso a dados). *Ex:* `CarteiraRepositoryPort`.
* **`usecase`**: A orquestração. Implementa os `ports.input`. Pega uma entidade, aplica regras e chama um `port.output` para salvar.

---

### 2. `infrastructure` (Os Detalhes / Adapters)
> **Regra:** Aqui vive tudo aquilo que "suja" o código: Banco de Dados, APIs Externas, Ferramentas de Log, etc.

* **`persistence`**: Tudo relacionado a Banco de Dados.
    * **`entity`**: O espelho do banco (`@Entity`, `@Table`). É anêmica (só dados).
    * **`repository`**: Interface do Spring Data (`JpaRepository`).
    * **`mapper`**: Converte `Domain Model` ↔ `Persistence Entity`. Garante que o Domínio nunca toque no JPA.
    * **`Gateway`**: Implementa o `port.output` do domínio. É a ponte real entre o Core e o Banco.

---

### 3. `application` (A Configuração e Entrada)
> **Regra:** É a "cola" que une o Spring Boot ao nosso Domínio agnóstico.

* **`controller`**: Pontos de entrada REST (`@RestController`). Recebe JSON (DTOs), converte e chama o Caso de Uso.
* **`config`**: Onde a Injeção de Dependência manual acontece (`@Configuration`).
    * Aqui instanciamos os UseCases (`new CriarCarteiraUseCaseImpl(...)`) e os entregamos ao Spring. Isso nos dá controle total sobre qual implementação usar.

---

## 🔄 Fluxo de Dados (Request Lifecycle)

Quando um usuário envia um POST para criar uma carteira, o dado viaja assim:

1.  **Mundo Externo (HTTP)**: O JSON chega no `CarteiraController`.
2.  **DTO**: O Controller converte o JSON para um `Record` Java.
3.  **Fronteira de Entrada**: O Controller chama `useCase.criar()`.
4.  **Núcleo (Domain)**:
    * O `UseCase` instancia a Entidade `Carteira`.
    * A `Carteira` valida suas regras (ex: saldo inicial zero).
    * O `UseCase` pede para salvar chamando a porta `repositoryPort.salvar()`.
5.  **Fronteira de Saída**: O `CarteiraRepositoryGateway` (Infra) intercepta a chamada.
6.  **Mapper**: O Gateway usa o Mapper para converter `Carteira` (Limpa) em `CarteiraEntity` (Suja).
7.  **Banco de Dados**: O `JpaRepository` salva a tabela.
8.  **Retorno**: O processo se inverte (Mapper → Domain → UseCase → Controller → JSON).

## 💡 Por que fazer isso?

1.  **Testabilidade**: Podemos testar o `domain` inteiro sem ligar o Spring ou o Banco de Dados.
2.  **Proteção**: Se trocarmos MySQL por MongoDB, o `domain` nem fica sabendo. Só mudamos a pasta `infrastructure`.
3.  **Manutenibilidade**: Um bug de regra de negócio SEMPRE estará no `domain`. Um bug de SQL SEMPRE estará na `infrastructure`.