# Vehicle Rental System

## Capa e Identificação

**Título do Projeto:** Sistema de Gerenciamento de Aluguel de Veículos  
**Integrantes da Equipe:** [Insira os nomes aqui]  
**Repositório do Código Fonte:** [Insira o link do repositório aqui]  

## Introdução

O Vehicle Rental System é uma aplicação Java desenvolvida para gerenciar o cadastro e aluguel de diferentes tipos de veículos. O sistema foi concebido para atender a necessidade de empresas de locação de veículos que precisam manter um controle eficiente de sua frota, incluindo diferentes categorias de veículos, cada um com suas características específicas.

O problema abordado envolve a gestão de um catálogo diversificado de veículos disponíveis para aluguel, onde cada tipo de veículo possui atributos comuns (como placa e capacidade) e atributos específicos de sua categoria. A solução proposta permite cadastrar, excluir e visualizar os diferentes veículos, mantendo todas as informações organizadas em um banco de dados relacional.

O sistema atende três categorias principais de veículos:
- **Carros**: com tipos específicos como SUV, Sedan e Hatch
- **Motos**: incluindo variantes como Street e Scooter
- **Coletivos**: abrangendo Van, Mini Van e Ônibus

Esta abordagem facilita a expansão futura do sistema para incluir novas categorias ou atributos, mantendo a organização e a integridade dos dados.

## Modelagem do Problema

A solução implementada segue os princípios da Programação Orientada a Objetos, estruturada da seguinte forma:

1. **Hierarquia de Classes**:
   - Uma classe base abstrata `Veiculo` que define os atributos e comportamentos comuns
   - Subclasses específicas `Carro`, `Moto` e `Coletivo` que herdam da classe base e implementam características específicas

2. **Estrutura UML Simplificada**:
```
Veiculo (Abstrata)
|
|-- Carro
| |-- atributos: portas, tipoCarro
|
|-- Moto
| |-- atributos: bau, tipoMoto
|
|-- Coletivo
|-- atributos: portas, banheiro, tipoColetivo
```

3. **Encapsulamento**: Todas as classes implementam encapsulamento adequado com métodos getters e setters para acesso controlado aos atributos.

4. **Polimorfismo**: 
- Polimorfismo estático: através de construtores e métodos sobrecarregados
- Polimorfismo dinâmico: através da implementação específica do método `salvarNoBanco()` em cada subclasse

5. **Interfaces e Classes Abstratas**: A classe base `Veiculo` implementa comportamentos abstratos que são especializados nas subclasses.

6. **Collections**: Uso de estruturas de dados como ArrayList para manipular conjuntos de veículos.

7. **Tratamento de Exceções**: Implementação de blocos try-catch para tratar erros de entrada de dados e falhas na comunicação com o banco de dados.

## Ferramentas Utilizadas

### Ambiente de Desenvolvimento
- IDE: [Eclipse/IntelliJ IDEA/NetBeans] (especifique a IDE utilizada)
- JDK: Java Development Kit 17 ou superior
- Sistema de Gerenciamento de Banco de Dados: PostgreSQL

### Bibliotecas e Frameworks
- JDBC (Java Database Connectivity) para comunicação com o banco de dados
- Driver JDBC para PostgreSQL

### Estrutura de Pacotes
```
src/
├── main/
│ ├── java/
│ │ ├── Main.java
│ │ ├── models/
│ │ │ ├── Veiculo.java
│ │ │ ├── Carro.java
│ │ │ ├── Moto.java
│ │ │ ├── Coletivo.java
│ │ │ └── Database.java
```

O projeto está organizado em pacotes que separam a lógica principal da aplicação (Main.java) dos modelos de dados (package models). Esta estruturação facilita a manutenção e a escalabilidade do projeto, permitindo adicionar novos tipos de veículos ou funcionalidades sem grandes alterações no código existente.

## Resultados e Considerações Finais

O Sistema de Gerenciamento de Aluguel de Veículos desenvolvido atende aos requisitos iniciais propostos, oferecendo uma interface de terminal intuitiva para o controle da frota de veículos disponíveis para locação. A aplicação permite:

1. Cadastrar novos veículos com seus atributos específicos
2. Excluir veículos do sistema por ID
3. Visualizar todos os veículos cadastrados, organizados por categoria

A integração com o banco de dados PostgreSQL garante a persistência das informações e possibilita a expansão futura do sistema para incluir novas funcionalidades como controle de clientes, gestão de contratos de aluguel e histórico de manutenções.

### Dificuldades Encontradas

Durante o desenvolvimento, enfrentamos alguns desafios:

1. Implementação da conexão com o banco de dados e o tratamento adequado de exceções
2. Modelagem da hierarquia de classes para evitar duplicação de código
3. Validação adequada das entradas do usuário para prevenir erros

### Aprendizagens e Reflexões

O desenvolvimento deste projeto proporcionou um aprofundamento prático dos conceitos de Programação Orientada a Objetos, especialmente no que se refere à aplicação de herança, polimorfismo e encapsulamento. A experiência de trabalhar com persistência de dados utilizando JDBC também foi enriquecedora, demonstrando a importância de um bom design de banco de dados para suportar a estrutura de classes do sistema.

A utilização do paradigma OO mostrou-se especialmente adequada para este problema, uma vez que a natureza hierárquica dos diferentes tipos de veículos se traduz de forma natural em uma estrutura de herança de classes.

### Sugestões para Melhorias Futuras

1. Implementação de uma interface gráfica utilizando JavaFX ou Swing
2. Adição de funcionalidades de busca e filtragem de veículos
3. Implementação de um módulo de relatórios para análise da frota
4. Desenvolvimento de um sistema de autenticação para controle de acesso

### Feedback sobre a Disciplina

A disciplina de Programação Orientada a Objetos proporcionou uma base sólida para o desenvolvimento deste projeto, oferecendo os conceitos teóricos e práticos necessários para a implementação de um sistema com múltiplas classes e relacionamentos. As discussões em aula sobre boas práticas de programação e design de software foram fundamentais para a estruturação adequada do código.
