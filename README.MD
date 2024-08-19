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
- Access swagger and run the services

### Important
Some data is inserted when the app run to help tests about historic. Since all operations will be performed on the same day, I adjusted it to have a wallet with ID 1 created on 2024/08/15 with a balance of 1000.00. Having a deposit at 2024/08/17 of 100.00 and a withdraw at 2024/08/19 of 200.00.  
- User
```
  {
    "id": 1,
    "name": "Marcus"
    "email": "marcus@testes.com"    
    }
  ``` 
- Wallet
```
  {
    "id": 1,
    "userId": 1
    "balance": 1000.00    
    }
  ```

- Transactions
  - Deposit 100.00 at 2024/08/17: Balance in this date ==> 1100.00
  - Withdraw 200.00 at 2024/08/19: Balance in this date ==> 900.00


## Swagger
After run the app, you can see the endpoints on link:
- http://localhost:8080/api/swagger-ui/index.html

## Details about implementation and Design
### 1 - Entities
 - User - Wallet owners. It didn't implemented authentication and authorization
 - Wallet - For manager the Wallet data 
 - Transaction - For manager the transactions like deposit, withdraw and transfers.
### 2 - Resources
 - User Resource - It has some services about users:
   - 1 List all users;
   - Create a user;
 - Wallet Resource - It has all requested functional requirements:
   - Create Wallet - Create a wallet for a user; 
   - Retrieve Balance and Historic - Unique Service for that. If the date (format YYYY-MM-DD) is provided, it will take the end-of-day balance for that date. If it is not provided, it will take the current balance;
   - Deposit - Enable users to deposit money into their wallets;
   - Withdraw - Enable users to withdraw money from their wallet;
   - Transfer - Facilitate the transfer of money between user wallets. 
   
### 4 - Validations
   - Verify if User exists before of the create a User or before of the create Wallet; 
   - Verify if Wallet exists before of the some transaction;
   - Verify if Wallet Balance is enough before of the debit;
   - Bean Validations on the requests;
   - Create a Controller Advice and two simple exceptions.

## Compromises or trade-offs made due to time constraints.
   - It didn't create a entity for Transfer. A transfer was mapped like one debit transaction on destination wallet and one credit transaction on the origin wallet.
   - Controler Advice could create a more detailed error pattern for the user.
   - Unit Tests
   - Some Database other than H2
   - Authentication and Authorization
   - Non-functional requirements

## Time Tracking
| Task Name                                | Spent time (hours) |
|------------------------------------------|--------------------|
| Create Spring Boot Project               | 0.5h               |
| Map the Entities and create Repositories | 1.5h               |
| Create User Resource and your services   | 1h                 |
| Create Wallet Resource and your services | 2.5h               |
| Create Controller Advice and Exceptions  | 0.5h               |
| Create Class to load data on database    | 0.5h               |
| Configure Swagger                        | 0.5h               |
| Explorer Tests on Swagger                | 1h                 |
| Publish code on github and write Readme  | 1h                 |