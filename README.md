Dependências entre módulos:

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