[//]: # (# My Personal Project)

[//]: # ()
[//]: # (## A subtitle)

[//]: # ()
[//]: # (A *bulleted* list:)

[//]: # (- item 1)

[//]: # (- item 2)

[//]: # (- item 3)

[//]: # ()
[//]: # (An example of text with **bold** and *italic* fonts.)

# Restaurant Table Manager

## Project Description

The *Restaurant Table Manager* is an application which aims to help waiters and owners keep track of tables and customer
orders at a restaurant. Restaurants should be able to upload their table layout, menus, and pricing to track current 
orders, even storing previous sessions. It allows users to create unique tables while monitoring customers' orders and 
spending. Users will be able to track the state of the table from dirty, open, occupied, to ready to pay, and assign 
the number of customers sitting at the table, storing the respective person's order and what they owe. Another feature
is its ability to store restaurant layouts and previous sessions, allowing for a continuous and fluid experience.

My interest in this project stems from observing my family's previous restaurant. They would use pen, paper, and memory
to keep track of orders and manage the business's operations. Though this was not an ineffective method, there were
evident issues with their methodology. For one, it relied too heavily on inter-worker communication, which can be
challenging when the restaurant is experiencing a rush. The fast handwritten notes also introduces difficulties
bookkeeping as they can often be lost, damaged, or thrown away.This application aims to mitigate these issues and 
provide a solution to future staff. It establishes a central system which every worker can rely on that is less prone
to organizational or communication issues between works. By storing previous sessions, businesses can have an easier
time tracking supplies, expenses, and even trends in their establishment. Using this app I hope to optimize and organize
the job of restaurant waiters, such as my family.

## User Stories

- as a user, I want to be able to add multiple/an arbitrary amount of tables to a restaurant 
- as a user, I want to be able to see and change the state/availability of tables
- as a user, I want to be able to assign an arbitrary number of items to a table and track the orders
- as a user, I want to be able to see a menu with prices and assign ordered items to a table based on it
- as a user, I want to be able to view how much each table owes

- as a user, I want to be able to save the restaurant setup (number of tables and their state) and previous orders to file (if I choose to)
- as a user, I want to be able to load the restaurant setup from file (if I choose to)

*uses JsonSerializationDemo-master provided by CPSC 210 edX as reference for the JSON writing/reading functionality*