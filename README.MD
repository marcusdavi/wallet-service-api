## Stack
- Java 17
- Spring Boot 3.0.2
- Spring JPA
- Spring Doc
- H2 database (memory database)

## How to Run
- clone repository
- open in your favourite IDE
- run as spring boot app

## Important
One user is inserted when the app run. You can use it for the create a wallet, but if you prefer I created a service to add users:
  ```
  {
    "id": 1,
    "name": "Marcus"
    "email": "marcus@testes.com"    
    }
  ``` 

## Swagger
After run the app, you can see the endpoints on link:
- http://localhost:8080/api/swagger-ui/index.html

## Details about implementation
### 1 - Entities
 - User - Wallet owners. It didn't implemented authentication and autorization
 - Wallet - For manager the Wallet data 
 - Transaction - For manager the transactions from Deposit, withdraw and transfers.
### 2 - Resources
 - User Resource - It has some services about users:
   - List all users;
   - Create a user;
 - Wallet Resource - It has all requested functional requirements:
   - Create Wallet - Create a wallet for a user; 
   - Retrieve Balance and Historic - Unique Service for that. If the date is provided, it will take the end-of-day balance for that date. If it is not provided, it will take the current balance;
   - Deposit - Enable users to deposit money into their wallets;
   - Withdraw - Enable users to withdraw money from their wallet;
   - Transfer - Facilitate the transfer of money between user wallets. It didn't create a entity for that. A transfer was mapped like one debit transaction on destination wallet and one credit transaction on the origin wallet.
   
### 4 - Validations
   - Verify if User exists before of the create a User or before of the create Wallet; 
   - Verify if Wallet exists before of the some transaction;
   - Verify if Wallet Balance is enough before of the debit;
   - Bean Validations on the requests;
   - Create a Controller Advice and two simple exceptions.