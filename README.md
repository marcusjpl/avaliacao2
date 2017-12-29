# avaliacao2
API REST + Twitter + Hibernate + Junit


## Configuracoes de Banco -standalone.xml WildFly 10

```xml
<?xml version="1.0" encoding="UTF-8"?>
<datasource jndi-name="java:jboss/datasources/oracleApiDS" pool-name="apiDS" enabled="true">
   <connection-url>jdbc:oracle:thin:@localhost:1521:xe</connection-url>
   <driver>oracle</driver>
   <pool>
      <min-pool-size>1</min-pool-size>
      <max-pool-size>5</max-pool-size>
      <prefill>true</prefill>
   </pool>
   <security>
      <user-name>api</user-name>
      <password>root</password>
   </security>
</datasource>
<drivers>
   <driver name="oracle" module="com.oracle">
      <driver-class>oracle.jdbc.driver.OracleDriver</driver-class>
   </driver>
</drivers>
```

## persistence.xml

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<persistence xmlns="http://xmlns.jcp.org/xml/ns/persistence"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    version="2.1" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence
    http://xmlns.jcp.org/xml/ns/persistence/persistence_2_1.xsd">
    <persistence-unit name="api-dev" transaction-type="JTA">
        <description>Dev persistence unit</description>
        <provider>org.hibernate.ejb.HibernatePersistence</provider>
        <!-- java transaction api || JNDI -->
        <jta-data-source>java:jboss/datasources/oracleApiDS</jta-data-source>
        <properties>
            <property name="hibernate.hbm2ddl.auto" value="update"/>
            <property name="hibernate.show_sql" value="true" />
            <property name="hibernate.format_sql" value="false" />
            <property name="hibernate.dialect" value="org.hibernate.dialect.OracleDialect"/>
        </properties>
    </persistence-unit>
<persistence>
```
## Servicos

### POST ..api/rest/tweet/procurar
Busca simples por Like
```json
[{"busca" : "valor"}]
```

### POST ..api/rest/tweet/captar
```json
[{"busca" : "valor"]
```

### GET ..api/rest/tweet/{idTweet}/estatisticas

### GET ..api/rest/tweet/{idTweet}/mais_comum
TODO obs: nao deu tempo de desenvolver

### GET ..api/rest/tweet/mais_longa

### GET ..api/rest/tweet/exatamente_em/{numeroRepeticoes}
TODO obs: nao deu tempo de desenvolver

## Modelo Relacional
![Link](https://github.com/marcusjpl/avaliacao2/blob/master/modelo.png)

## Script Tabelas
Link para o arquivo: [Script](https://github.com/marcusjpl/avaliacao2/blob/master/scriptAPI.sql)
