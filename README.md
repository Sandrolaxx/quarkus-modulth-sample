## Branch estrutura básica

Pontos importantes das definições para implementação do modulith.

### Root pom.xml

Iniciando os principais pontos, precisamos compreender a necessidade do pom.xml pai (root POM) em um projeto Maven multi-módulo, pois ele cumpre um papel estratégico e centralizador no gerenciamento de um monólito modular com Quarkus.

Abaixo iremos compreender cada tag desse [arquivo](pom.xml).

**Organização modular**

A seção `<modules>` define explicitamente quais são os submódulos que compõem o projeto. Isso:
* Permite compilar e construir todos os módulos com um único comando (mvn clean install na raiz);
* Garante o controle de ordem de build (por dependência) entre os módulos;
* Favorece a modularidade lógica dentro de um único artefato (o monólito);
* Reflete a separação conceitual dos bounded contexts.

**Gerenciamento centralizado de dependências**

A seção `<dependencyManagement>` define a versão do BOM (Bill Of Materials) do Quarkus. Esse BOM:
* Garante versões compatíveis e testadas entre os vários componentes do ecossistema Quarkus;
* Evita repetição de versões nos módulos filhos (eles herdam automaticamente);
* Reduz o risco de conflito de dependências transitivas;
* Centraliza a governança de dependências no POM raiz (seguindo boas práticas de versionamento e segurança).

**Configuração de plugins e propriedades**

As `<properties>` definem:
* Versão do Java (maven.compiler.release=17);
* Versão dos plugins (ex: maven-compiler-plugin, surefire-plugin);
* Codificação (UTF-8);
* São herdadas por todos os módulos;
* Garantem uniformidade de compilação e execução;
* Reduzem configuração duplicada nos POMs filhos.

**O PONTO MAIS IMPORTANTE - Configuração do jandex-maven-plugin**

A configuração do jandex é um dos pontos mais importantes que temos no root pom, sem ele o Quarkus não conseguiria realizar a descoberta de beans em outros módulos. Podemos observar esse ponto na documentação oficial do Quarkus sobre.

![Image](https://github.com/user-attachments/assets/d8a36cb1-9281-4c18-beb8-84a1613fb981)

```xml
<build>
  <plugins>
    <plugin>
      <groupId>io.smallrye</groupId>
      <artifactId>jandex-maven-plugin</artifactId>
      <version>3.3.1</version>
      <executions>
        <execution>
          <id>make-index</id>
          <goals>
            <goal>jandex</goal>
          </goals>
        </execution>
      </executions>
    </plugin>
  </plugins>
</build> 
```

Podemos fazer essa mesma indexação dos modulos diretamente no aplication.properties do módulo `app`, como no exemplo abaixo:
```
quarkus.index-dependency.catalog.group-id=com.aktie
quarkus.index-dependency.catalog.artifact-id=catalog
```
O problema dessa segunda abordagem é lembrar a cada novo módulo de adicionar essa config, algo que o jandex já faz automáticamente.

**Papel do root pom construção do monólito modular**
* Isola a complexidade de cada módulo (por domínio ou funcionalidade);
* Torna mais fácil aplicar princípios de arquitetura modular;
* Permite que módulos como catalog, sale, stock evoluam independentemente, ainda que no mesmo artefato de deploy;
* Serve como ponto único de entrada para o build e distribuição do monólito (inclusive se for empacotado como um único JAR fat).

**Outros pontos importantes**

Mesmo o pom.xml root sendo muito importante, não é ele que inicia nossa aplicação, e sim o projeto `app`. Nesse projeto em seu pom.xml temos os `<goals>` do maven para gerar build, teste e executar o projeto.

É um setup que favorece futuramente extrair algum módulo como um microserviço ou plugin externo.

🌟**Links úteis**

[Maven pom reference](https://maven.apache.org/pom.html)

[Quarkus and Maven](https://quarkus.io/guides/maven-tooling#multi-module)

---

### Passo a passo para adicionar um módulo

Aqui temos o fluxo para adicionar mais um bounded-context a nosso modulith, nesse nosso exemplo falta o módulo delivery, então será ele que iremos adicionar.

Começamos criando um projeto chamado `delivery` e adicionamos ao nosso projeto principal:
![Create new project](https://github.com/user-attachments/assets/3e05e090-b43b-480f-8246-071430836b0f)

Então vamos criar duas classes, para nosso exemplo ficar mais rico:
![create_sample_classesmage](https://github.com/user-attachments/assets/33ee9446-2694-411d-af18-bc6e346be0dc)

A classe DeliveryModule que implementará a interface que irá permitir injetar ela no startup do módulo `app`(Não obrigatório*)
![Class delivery module](https://github.com/user-attachments/assets/9ea99212-5381-4c48-8982-22f9a2fea5be)

A classe que terá um simples end-point, mostrando que ele é carregado no contexto de `app`.
![Class delivery resource](https://github.com/user-attachments/assets/425149ef-00da-466f-a87f-776874608e7b)

Em nosso pom(delivery) precisamos adicionar as tags do **pom parent**, ou seja o pom root do projeto principal:
![Pom do nosso projeto](https://github.com/user-attachments/assets/e5e824db-e54b-4248-875c-4511d166b030)

Falando do root pom, precisamos também adicionar o nosso novo módulo nele!
![Root pom](https://github.com/user-attachments/assets/3c9080e6-86ad-4153-840d-ad52e333170c)

Também vamos adicionar ao pom(app) do nosso entry-point, o módulo `app`, ele possuí como dependência todos os módulos.
![App pomm](https://github.com/user-attachments/assets/2074825f-f72f-4af1-9ff4-4c2db71c4d8d)

Podemos no terminal da raiz do nosso projeto, executar o comando de limpar e construir do maven, ele irá realizar o clean e o build de todos os módulos, ao finalizar você terá um log no console similar ao abaixo.

Comando: `./mvnw clean install`

![Log build root](https://github.com/user-attachments/assets/4ab22b4d-e541-4029-b314-2d9c7bf5cb70)

Agora vamos entrar na pasta do módulo `app` para executar o projeto.

Comando para executar: `./mvnw quarkus:dev`

Retorno algo similar ao print abaixo:
![Execução projeto](https://github.com/user-attachments/assets/a69d95a7-4c19-4fce-a8ed-ba632041b300)

Agora podemos acessar o recurso de cada módulo sem problemas.
![End-points](https://github.com/user-attachments/assets/0132380c-9c77-4908-875a-50275bae0ebb)

Com isso temos as bases para criar nosso monolito modular e evoluir ele a medida que for necessário.💯

---

### Como essa mágica funciona?

Tudo isso é possível ao [CDI](https://dev.to/vepo/contexts-and-dependency-injection-cdi-2-0-1ae4), o CDI (Contexts and Dependency Injection) é uma especificação do Java (originalmente do Java EE, agora Jakarta EE) que o Quarkus implementa para gerenciar injeção de dependências.

> **Em termos simples**: CDI é um gerenciador de objetos inteligentes. Ele cria, configura e conecta objetos automaticamente, com base em anotações como @Inject, @ApplicationScoped, @Singleton, etc.

Como CDI permite essa "mágica" entre os módulos?

1. Descoberta automática, durante o build, o CDI (com ajuda do Jandex) escaneia todas as classes anotadas como beans(@ApplicationScoped, @Singleton). Essas classes são registradas em um contexto comum e ficam disponíveis para injeção.
2. Injeção de dependências entre módulos, mesmo que `catalog` e `sale` estejam em módulos separados, se `catalog` tiver um bean, `sale` pode realizar a injeção de dependência dele(@Inject).
3. Desacoplamento e modularidade! Você não precisa saber "quem criou o objeto" nem importar implementações concretas diretamente. Isso permite, módulos independentes, baixo acoplamento e testabilidade.
4. Descoberta em tempo de compilação. Quarkus otimiza o CDI com Jandex(índice de anotações), **Build time wiring** (conexão das dependências é feita em tempo de build, não em runtime como no Spring); Isso gera melhor desempenho e torna possível compilar para binário nativo com GraalVM.

🌟**Links úteis**

[CDI no Quarkus](https://quarkus.io/guides/cdi-reference#jandex)

[Espec CDI Jakarta](https://jakarta.ee/specifications/cdi/)

---

### Funcionamento do projeto exemplo

Por mais que temos a interação entre os módulos funcionando de maneira correta, precisamos de um `entry-point`, um ponto de entrada que realiza a execução do processo, isso é feito pelo nosso módulo `app`, nele temos a injeção dos beans de todos os projetos, por mais que os módulos não estão sendo executados por si só, suas funcionalidades estão sendo "ativadas" no módulo `app` quando executamos.

---

## ATENÇÃO⚠

O exemplo que temos é apenas para demonstrar que já é possível utilizar funcionalidades(beans)! Não é necessário fazer todo o fluxo abaixo para conseguir utilizar o Bean de um módulo em outro, poderíamos apenas injetar diretamente com **@Inject**. Nesse caso, não teria a necessidade de criar **Initializer** e **IAppModule** para ativar o CDI de todos os módulos.

Estamos fazendo a mesma injeção, porém de uma maneira mas elegante, mas ela não é mandatória =).

---

Na execução da nossa classe `Initializer.java` no módulo `app`, no evento de iniciar a aplicação, pegamos todas as classes que implementam a interface `IAppModule` e as injetamos nesse contexto comum de app.

```java
@ApplicationScoped
public class Initializer {

    @Inject
    Instance<IAppModule> modules;

    void onStart(@Observes StartupEvent ev) {
        List<IAppModule> sortedModules = modules.stream()
                .sorted(Comparator.comparingInt(IAppModule::getPriority))
                .collect(Collectors.toList());

        for (IAppModule module : sortedModules) {
            module.init();
        }

        var modules = sortedModules.stream().map(IAppModule::getName).toList();
        System.out.println("Loaded modules: ".concat(modules.toString()));
    }

    void onShutdown(@Observes ShutdownEvent ev) {
        List<IAppModule> revertedModules = modules.stream()
                .sorted(Comparator.comparingInt(IAppModule::getPriority).reversed())
                .collect(Collectors.toList());

        for (IAppModule module : revertedModules) {
            module.shutdown();
        }
    }
}
```

---

### Estrutura do projeto

Dependências entre módulos(objetivo do projeto ao finalizar*):

- Catalog: Não depende de outros módulos
- Stock: Depende do Catalog
- User: Não depende de outros módulos
- Sale: Depende de Catalog, User e Stock
- Delivery: Depende de Sale e User

---

Exemplo de interação entre modulos na processo da venda.

```mermaid
graph TD
    CP["Catálogo de Produtos"]
    GP["Gerenciamento de Pedidos"]
    GE["Gerenciamento de Estoque"]
    GC["Gerenciamento de Clientes"]
    GD["Gerenciamento de Entrega"]

    GP -->|síncrono: obter detalhes do produto| CP
    GP -->|síncrono: obter detalhes do cliente| GC
    GP -->|síncrono: verificar disponibilidade de estoque| GE
    GP -->|assíncrono: OrderPlaced| GE
    GP -->|assíncrono: OrderPlaced| GD
    GD -->|assíncrono: DeliveryCompleted| GP
```