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
spending. Users will be able to track the state of the table from available, occupied, ready to pay, and needs cleaning. 
Another feature is its ability to store restaurant layouts and previous sessions, allowing for a continuous and fluid experience.

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

## Instructions for Grader

- you can generate the first required action related to adding Xs to a Y by pressing "add a table" and "remove a table" buttons in the toolbar on the left side of the screen to do their respective actions, which is displayed on the "Restaurant View" on the centre of the screen
- you can generate the second required action related to adding Xs to a Y by selecting a table by pressing on it, then selecting an item in the "Menu" pane in the "Table View" on the right side of the screen, then pressing "Order Item" to add the selected item to the table's order, you can remove an item by selecting it in the "Table Order" pane below the menu and clicking "Remove Item"
- you can locate my visual component by looking the toolbar on the left, where the buttons are indicated by a corresponding icon image which is stored in the \data\icons folder
- you can save the state of my application at startup by pressing "yes" to the "Do you want to load the saved restaurant" prompt or by pressing the "save" button in the toolbar on the left, which appears as an arrow pointing down
- you can reload the state of my application by pressing the "load" button in the toolbar on the left, which appears as an arrow pointing up

## Phase 4: Task 2

Mon Aug 07 01:09:54 PDT 2023
Added Table 1 to the restaurant

Mon Aug 07 01:09:59 PDT 2023
Added Fried Rice to Table 1 order

Mon Aug 07 01:10:01 PDT 2023
Added Chow Mein to Table 1 order

Mon Aug 07 01:10:03 PDT 2023
Removed Fried Rice from Table 1 order

Mon Aug 07 01:10:04 PDT 2023
Removed Table 1 from the restaurant

## Phase 4: Task 3

After creating and viewing the UML class diagram of my application, to improve the design I would try refactoring the Menu 
class to utilize the singleton pattern. A restaurant only has one menu, at it is generally universal for the entire restaurant
making it a viable candidate for the singleton pattern. It is reasonable to only allow one Menu object to exist and simply call 
it when necessary. This would also mean the Menu would only need to be instantiated once through the singleton method, 
which could reduce memory usage and complexity especially when the menu becomes larger. This would also remove the need to 
instantiate the Menu whenever a class needs to access it, reducing the number of steps when scaling up the project and
adding more classes or functionality.

## References
- JsonSerializationDemo-master provided by CPSC 210 edX as reference for the JSON writing/reading functionality
- icons from *Softy: Squaries 64px* by Gregor Cresnar accessed at https://thenounproject.com/grega.cresnar/