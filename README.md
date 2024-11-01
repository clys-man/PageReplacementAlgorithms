# Page Replacement Algorithms Simulator

## Descrição

Este projeto é uma simulação de algoritmos de substituição de páginas, que é uma parte fundamental da gestão de memória em sistemas operacionais. O simulador compara a eficiência de diferentes algoritmos de substituição de páginas, como FIFO, LRU, MRU, Clock e Ótimo, exibindo o número de falhas de página (page faults) ocorridas em cada algoritmo.

Além disso, o projeto utiliza a biblioteca JFreeChart para visualizar graficamente a comparação de desempenho dos algoritmos.

## Algoritmos Implementados

- **FIFO (First-In-First-Out)**: O algoritmo mais simples, que substitui a página que entrou primeiro na memória.
- **LRU (Least Recently Used)**: Substitui a página que não foi utilizada por mais tempo.
- **MRU (Most Recently Used)**: Substitui a página que foi usada mais recentemente.
- **Clock**: Uma versão do algoritmo FIFO que utiliza uma abordagem circular com bits de referência.
- **Ótimo**: Substitui a página que não será utilizada pelo maior período de tempo no futuro.

## Requisitos

- **Java 8 ou superior**
- **Maven**: Para gerenciar dependências e construir o projeto.

## Instalação

1. **Clone o repositório:**

   ```bash
   git clone https://github.com/clys-man/page-replacement-algorithms.git
   cd page-replacement-algorithms
   ```

2. **Compile e execute o projeto usando Maven:**

   Se você já possui o Maven instalado, você pode compilar e executar o projeto com o seguinte comando:

   ```bash
   mvn clean install
   ```

3. **Execute a simulação:**

   Após compilar o projeto, execute o seguinte comando para iniciar a simulação:

   ```bash
   mvn exec:java -Dexec.mainClass="me.clysman.unifor.simulator.PageReplacementSimulator"
   ```

   Certifique-se de que o `exec-maven-plugin` está configurado no seu `pom.xml` para permitir a execução da classe principal.