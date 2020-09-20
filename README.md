# OMA - 2nd
OMA - Order Management Application. OMA is B2B application that improve cooperation between companies. Basic concept is to make orders, add new delivery points, edit company and clinets data, import list of product from xml, import pricelists for clients, order confiration with pdf and xml file.
## Basic functionality
1. Register company :
  * input and edit company data
  * create admin users
  * register email for orders (sending confirmations)
2. Register clients :
  * add and edit clients data
  * create user with role : manager, operator
    * operator - list of owns orders, makes order, creates delivery points, sends questions
    * manager - confirm order from operator, create and manage all orders, create and manage all delivery points, sends question, create user with operator role
3. Separate views for admin and clients :
  * admin :
    * main view - 3 sections : last orders (delivery point, summary value, creation date), activ users(username, mobile phone, company), new users (mobile, username, company), new delivery points (address, company)
    * user view - user name, name, last name, mobile, edit button
    * orders view - company, delivery point, created by, confirmed by, summary value, edit button, details button
    * delivery points view - company, address, edit button
    * clients view - name , identification number / tax identificator, address, edit button, details button
  * user view : 
    * view for operator : orders and delivery points created by user
    * view for manager : view of all orders and delivery points for company, company data edition, operators edition, activ operators
    * views for orders and delivery points are the same like for admin
4. Filtering 
  * filter orders by : company, delivery point, creation date
  * filter clients by : name
  * filter delivery points by : company, address
5. Details views :
  * each company : company data, users with roles, delivery points, orders
  * each order : list of products, delivery point, created by, confirmed by, creation date
6. Makeing orders : 
  * orders path : created by operator -> confirmed by manager -> xml and pdf generation -> order confirmation via email engine
  * add products
  * choose delivery points
  * add "additiona info with block text"
## Technology
1. Backend : Java 11, Spring MVC with Hibernate / Spring Boot With Hibernate, MySQL, Junit and Mockito for Testing, Logging to file and console with SLF4J / Log4J, Maven for project building, server : JBoss or Tomcat
2. Frontend : Thymeleaf with CSS, Bootstrap and JQuery
## Convention 
Tasks should be writen with basic programming rules like SOLID, KISS and DRY. Each contributor should wirte good and clean code with testing. Pull Request should be accepted with two confirmations
