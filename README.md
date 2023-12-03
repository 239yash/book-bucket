# book-bucket

A book buy-sell platform for college students(backend side).
This platform features separate authentication (used JWT auth) mechanisms for users and administrators, ensuring secure
access (role-based access).
Students can easily list their books for sale, providing details such as book title, author, condition, and pricing (
CRUD operations).
Additionally, an admin section allows for efficient order management and real-time inventory control.

### Features/Flows -

1. Login/Sign Up available for the users and admins too.
2. Listing of all the books which are available in the inventory + showing out of stock books.
3. Order creation for book buying in draft mode i.e. order draft is always preserved in database until it is submitted
   or deleted.
4. Order creation for book selling in draft mode i.e. order draft is always preserved in database until it is submitted
   or deleted.
5. Admin controls for changing order status, validating the orders, etc.
6. RBAC implemented for safeguarding the roles, For example - Only admins can change the order status not the user.

### API Documentation -

- API documentation is done via openApi and Swagger UI.
- Link for Swagger UI - {base url}/swagger-ui/index.html.

### Tech Used -

JAVA, SpringBoot, Swagger, OpenApi

