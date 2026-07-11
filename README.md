<<<<<<< HEAD
# Inventory & Order Management System
=======
# inventory-management
>>>>>>> b2af4f3dc6ee8715dc84880a48e070f2f9be5699

A simple backend project built with **Java, Spring Boot, and REST APIs** to manage
product inventory and customer orders. Built using Spring JDBC (JdbcTemplate) to
interact directly with MySQL — no Hibernate/JPA used, so all SQL is visible and easy
to explain.

## What it does
- Add and view products in inventory
- Place an order for a product (automatically reduces stock)
- Cancel an order (automatically restores stock)
- View all orders / a single order

## Tech Stack
- Java 17
- Spring Boot (Web + JDBC)
- MySQL
- Swagger / OpenAPI (for public API documentation)
- Postman (for testing)

## Project Structure
```
com.inventory
├── model         -> Product.java, Order.java (plain Java classes)
├── dao           -> ProductDao.java, OrderDao.java (SQL queries using JdbcTemplate)
├── service       -> ProductService.java, OrderService.java (business logic)
├── controller    -> ProductController.java, OrderController.java (REST endpoints)
└── exception     -> InsufficientStockException.java (custom exception)
```

## How to run
1. Create a MySQL database named `inventory_db`
2. Update `application.properties` with your MySQL username/password
3. Run `InventoryManagementApplication.java`
4. Tables are created automatically from `schema.sql` on startup
5. Server runs at `http://localhost:8080`
6. API docs available at `http://localhost:8080/swagger-ui.html`

## API Endpoints

| Method | Endpoint            | Description                     |
|--------|----------------------|----------------------------------|
| POST   | /products            | Add a new product                |
| GET    | /products            | Get all products                 |
| GET    | /products/{id}       | Get a product by id              |
| POST   | /orders              | Place a new order                |
| PUT    | /orders/{id}/cancel  | Cancel an order (restores stock) |
| GET    | /orders              | Get all orders                   |
| GET    | /orders/{id}         | Get an order by id               |

## Sample Request - Add Product
```
POST /products
{
  "name": "Blue T-Shirt",
  "price": 499.0,
  "quantity": 50
}
```

## Sample Request - Place Order
```
POST /orders
{
  "productId": 1,
  "quantity": 2
}
```

## How to explain this project in an interview (60-90 seconds)
"I built a small backend system to manage inventory and orders, similar to what
an e-commerce or retail platform needs. It has two main entities — Product and
Order. When a customer places an order, the system checks if enough stock is
available, then reduces the product quantity automatically. If the order is
cancelled, the stock is restored. I used Spring Boot to expose REST APIs for
these operations, and Spring JDBC to write and manage the SQL queries directly
against MySQL. I also documented the APIs using Swagger so anyone could see and
test the endpoints without reading the code."

## Key things you should be ready to explain
- Why service layer is separate from DAO layer (separation of concerns)
- What JdbcTemplate does and why it's simpler than raw JDBC
- How stock deduction and restoration works (OrderService.java)
- What the custom exception (InsufficientStockException) does and why it's useful
- The flow of a single request: Controller -> Service -> DAO -> Database
