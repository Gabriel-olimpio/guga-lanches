# Sistema de Pedidos Guga Lanches

## Equipe:
- Andrey Carvalho de Lemos
- Gabriel André Barreto Olimpio dos Santos
- Hellen Neves Barbosa

 ## Problema:
A lanchonete **Guga Lanches** precisa de uma interface gráfica para que seus clientes possam realizar pedidos de forma prática e intuitiva. Essa interface deve exibir todos os produtos disponíveis, mostrando o **nome** e o **valor unitário** de cada item. Ao lado de cada produto, deve haver um botão que permita ao cliente **adicionar o item ao carrinho de compras**.
Na área do **carrinho**, o cliente poderá visualizar:
		- Os produtos selecionados
		- A quantidade de cada item    
		- O **subtotal por produto**
		- O **valor total do pedido**
Além disso, o cliente deverá selecionar a **forma de pagamento** (como dinheiro, cartão ou Pix) e o **tipo de consumo** (consumir no local ou retirada).
Ao confirmar o pedido, será exibida uma tela solicitando o **nome** e o **telefone** do cliente.
Todas as informações: cliente, produtos, forma de pagamento e tipo de consumo deverão ser **armazenadas no banco de dados** da lanchonete.

## Uso do programa:

1) **Baixe o arquivo zip do programa**
	Github do projeto: https://github.com/Gabriel-olimpio/guga-lanches
![[Captura de Tela 2025-07-23 às 18.14.33.png]]

2) **Crie o banco de dados no pgAdmin (PostgreSQL)**

```postgresql
CREATE SCHEMA gugapd;

-- Cria a tabela de produtos do cardápio
CREATE TABLE gugapd.produtos (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL UNIQUE,
    preco NUMERIC(10, 2) NOT NULL
);

-- Cria a tabela para armazenar os dados dos clientes
CREATE TABLE gugapd.clientes (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    telefone VARCHAR(20) UNIQUE NOT NULL
);

-- Cria a tabela principal dos pedidos
CREATE TABLE gugapd.pedidos (
    id SERIAL PRIMARY KEY,
    cliente_id INTEGER NOT NULL REFERENCES gugapd.clientes(id),
    forma_pagamento VARCHAR(50) NOT NULL,
    tipo_consumo VARCHAR(50) NOT NULL,
    valor_total NUMERIC(10, 2) NOT NULL,
    data_pedido date
);

-- Cria uma tabela para ligar os produtos aos pedidos (um pedido pode ter vários produtos)
CREATE TABLE gugapd.itens_pedido (
    id SERIAL PRIMARY KEY,
    pedido_id INTEGER NOT NULL REFERENCES gugapd.pedidos(id),
    produto_id INTEGER NOT NULL REFERENCES gugapd.produtos(id),
    quantidade INTEGER NOT NULL,
    subtotal NUMERIC(10, 2) NOT NULL
);

-- Inserindo alguns produtos de exemplo para teste
INSERT INTO gugapd.produtos (nome, preco) VALUES
('Pastel de Carne', 15.00),
('Pastel de Marguerita', 15.00),
('Pastel de Camarão', 15.00),
('Pastel de Queijo', 15.00),
('Pastel de Frango', 15.00),
('Refrigerante', 5.00),
('Suco', 6.00)


select * from gugapd.produtos;
select * from gugapd.clientes;
select * from gugapd.pedidos;
select * from gugapd.itens_pedido;
```

3) Insira a URL, User e Password do seu banco de dados nos locais marcados na classe `ConnectionFactory.java` 

```java
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.SQLException;  
  
public class ConnectionFactory {  
    //  Verificar se a URL, usuário e senha estão corretos  
    private static final String URL = "jdbc:postgresql://localhost:5432/guga";  
    private static final String USER = "postgres";  
    private static final String PASSWORD = "backend"; // <-- Trocar pela senha do banco  
  
    public static Connection getConnection() {  
        try {  
            return DriverManager.getConnection(URL, USER, PASSWORD);  
        } catch (SQLException e) {  
            throw new RuntimeException("Erro na conexão com o banco de dados: " + e.getMessage(), e);  
        }  
    }  
}
```

4) inicialize o código na classe `Main.java`

	![[Captura de Tela 2025-07-23 às 18.10.38.png]]

**Telas da Interface**:

**TELA DE CARDÁPIO**

![[PHOTO-2025-07-23-16-00-16.jpg]]

**TELA DO CARRINHO**

![[PHOTO-2025-07-23-16-01-56.jpg]]

**TELA DE CADASTRO DO CLIENTE**

![[Captura de Tela 2025-07-23 às 18.19.07.png]]

**TELA DE RESUMO DO PEDIDO**

![[PHOTO-2025-07-23-16-03-31.jpg]]
