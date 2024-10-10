# academic-events-crud

# Descrição do Projeto

Este é um projeto de gerenciamento de eventos que permite a criação e organização de eventos de forma flexível e escalável. O sistema suporta a estruturação de eventos complexos, incluindo:

- **Subeventos**: Cada evento pode conter subeventos, permitindo uma melhor categorização e gerenciamento de atividades relacionadas.
- **Seções**: Dentro de cada subevento, é possível definir seções específicas que podem agrupar atividades ou trilhas temáticas.
- **Trilhas**: As trilhas são caminhos de participação que podem incluir várias atividades, ajudando os participantes a escolherem suas experiências de acordo com seus interesses.
- **Atividades**: Cada trilha ou seção pode incluir diversas atividades (Palestras, Mesas-Redondas, Workshops etc.).

Este sistema foi desenvolvido com o intuito de facilitar o planejamento e a execução de eventos, oferecendo uma interface intuitiva e funcionalidades robustas para gerenciar todos os aspectos envolvidos.

## Tecnologias Utilizadas

- **Java**: Linguagem de programação principal.
- **Maven**: Gerenciador de dependências e build.
- **SonarQube**: Análise de qualidade de código.
- **JUnit**: Framework de testes.
- **Mockito**: Biblioteca de testes para Java.

## Estrutura do Projeto

```plaintext
C:.
├───.github
│   └───workflows          # Configurações para GitHub Actions
├───.idea                  # Configurações do IntelliJ IDEA
├───.vscode                # Configurações do Visual Studio Code
├───src                    # Código fonte
│   ├───main
│   │   └───java           # Código Java principal
│   │       ├───app       # Aplicação principal
│   │       ├───controllers # Controladores
│   │       ├───exception  # Tratamento de exceções
│   │       ├───interfaces  # Interfaces
│   │       ├───models     # Modelos de dados
│   │       ├───persistence # Persistência de dados
│   │       └───ui        # Interface do usuário
│   └───test               # Testes
│       └───java           # Código de teste em Java
│           ├───controllers # Testes para controladores
│           ├───persistence # Testes para persistência
│           └───ui         # Testes para UI
└───target                  # Diretório de saída do Maven
    ├───classes            # Classes compiladas
    ├───generated-sources  # Fontes geradas
    ├───generated-test-sources # Fontes de teste geradas
    ├───maven-status        # Status do Maven
    ├───surefire-reports    # Relatórios do Surefire
    └───test-classes       # Classes de teste compiladas
```
## Instalação

Siga as etapas abaixo para instalar e configurar o projeto em sua máquina local.

1. **Pré-requisitos**: Certifique-se de ter o [Java JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) e [Maven](https://maven.apache.org/download.cgi) instalados.

2. Clone o repositório:
   ```bash
   git clone https://github.com/usuario/nome-do-repositorio.git

3. Navegue para o diretório do projeto:
   ```bash
   cd nome-do-repositorio

4. Compile o projeto com Maven:
   ```bash
   mvn clean install
