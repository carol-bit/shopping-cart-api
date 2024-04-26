# shopping-cart-api

- [Description](#descrição)
- [Quick start](#início-rápido)


## Description

Allows you to manage the entire life cycle of **Shopping Cart**, in a standardized way.

This project is coded in Java and uses the Spring Boot framework.

## Quick start

💡 To run the project, execute the commands within the directory created for the project.

The first part of the command changes the script's permission to allow execution and the second part executes the script.

```bash
chmod +x ./run/run.sh
./run/run.sh
```

Run the command below:

```bash
./run/run.sh
```

## End-points exposed by the service

| **End-point** | **Type** | **Description** |
| --- | --- | --- |
| /api/shopping-cart/v1/products | GET | List all products |
| /api/shopping-cart/v1/basket/item | POST | Add new item to cart |
| /api/shopping-cart/v1/basket/details | POST | Displays cart details |
| /api/shopping-cart/v1/clear | POST | Cleans hashmap used as temporary storage |
