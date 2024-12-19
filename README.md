# Recipe Book application using Spring boot and Angular

_Application is still in development._

RecipeBook is a web application that allows users to create, view, and search for recipes. Users can create an account, log in, and create their own recipes. They can also view other users' recipes, search for recipes by name and description, and filter them. Users can also edit their profile information and change their password.

## Technologies used

List of technologies used in the project:

### Backend

- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- Spring Web
- Lombok
- Maven
- JWT (JSON Web Token)
- MySQL database
- Hibernate

### Frontend

- Angular framework v. 17.2.0
- HTML
- CSS
- Bootstrap  v.5.3
- TypeScript

## Short overview of the project

Users can register and log in to the application, with their personal information securely stored in a MySQL database. The system leverages Spring Security with JWT tokens for authentication and ensures password protection through encryption.

![Screenshot 2024-04-02 100913](https://github.com/user-attachments/assets/387e730c-775c-4c6d-8c30-74968fca5548)

![Screenshot 2024-04-02 100725](https://github.com/user-attachments/assets/bdc4c802-9e74-4f00-9f0c-d972c4d36857)

Once logged in, users can explore recipes or search specific ones or create their own.
![Screenshot 2024-04-02 104554](https://github.com/user-attachments/assets/153f892e-63f9-423b-b99d-f942f3beac7b)
![Screenshot 2024-04-02 105340](https://github.com/user-attachments/assets/84baae80-170a-4e8a-8a6a-d135b9ac13b3)
![Screenshot 2024-04-02 105156](https://github.com/user-attachments/assets/28596949-fe82-4394-97f9-e49a2e12624f)





## Recent updates


- 3.4.24 initial commit


## Structure of the project

Repository contains two parts of the application: backend and frontend.


- `README.md` - contains the description of the project.
- `backend` - contains the backend part of the application.
    - `pom.xml` - contains all the dependencies needed for the project.
    - `src/main/java/myrres/foodOrganizer` - contains the source code of the backend part of the application.
        - `BackendApplication.java` - contains the main class of the application.
        - `recipeSettings` - module for managing recipes.
            - `controller/RecipeController.java` - contains the REST API for managing recipes.
            - `model` - module for recipe model.
                - `Recipe.java` - contains the model of the recipe.
                - `RecipeRepository.java` - interface that contains the repository for the recipe.
                - `RecipeCategory.java` - enum that contains the model of the recipe category.
        - `userSettings` - module for managing users.
            - `auth` - nodule for managing authentication
                - `AuthenticationRequest.java` - contains the model of the authentication request.
                - `AuthenticationResponse.java` - contains the model of the authentication response.
                - `AuthenticationController.java` - contains the REST API for managing authentication.
                - `AuthenticationService.java` - contains the service for managing authentication.
                - `RegisterRequest.java` - contains the model of the registration request.
            - `config` - module for managing the configuration of the application jwt authentication
              and athorization.
                - `SecurityConfiguration.java` - contains the configuration of the security of the application.
                - `ApplicationConfig.java` - contains the configuration of the application for authentication and password encoder.
                - `JwtAuthenticationFilter.java` - contains the filter for jwt authentication.
                - `JwtService.java` - contains the service for jwt authentication.
            - `user` - module for managing users.
                - `model` - module for user model.
                    - `User.java` - contains the model of the user.
                    - `UserRole.java` - enum that contains the model of the user role.
                    - `UserRepository.java` - interface that contains the repository for the user.
                - `ChangePasswordRequest.java` - contains the model of the change password request.
                - `UserController.java` - contains the REST API for managing users.
                - `UserService.java` - contains the service for managing users.
                - `ChangeUserInfoRequest.java` - contains the model of the change user info request.
                - `UserInfoResponse.java` - contains the model of the user info response.
                  -`.gitignore` - contains the list of files and directories that should be ignored by git.
    - `mvnw` - contains the maven wrapper for the project.
- `frontend-app` - contains the frontend part of the application.
    - `.angular` - contains the configuration of the angular application.
    - `node_modules` - contains the dependencies of the angular application.
    - `src/app` - module for source code of angular app
        - `components` - module for components of the application.
            - `public` - module to components avaible to everyone
                - `home` - module for home component.
                    - `home.component.html` - contains the html code of the home component.
                    - `home.component.ts` - contains the typescript code of the home component.
                    - `home.component.css` - contains the css code of the home component.
                - `login` - module for login component.
                - `register` - module for register component.
                - `nav` - module for a navbar component.
            - `user` - module to components avaible only to logged users to manage their profile
                - `user-settings` - module for user settings component.
                - `change-password` - module for change password component.
                - `logout` - module for logout component.
                - `user-nav` - module for a navbar component for logged users.
                - `user-home` - module for home component for logged users.
            - `recipe` - module for managing components of the recipe
                - `recipes` - module for recipes components.
                - `recipe-list` - module for recipe list component.
                - `recipe-info` - module for recipe details component.
                - `edit-recipe` - module for recipe edit component.
                - `create-recipe` - module for recipe add component.
                - `recipe-nav` - module for a navbar component for recipe components.
        - `recipe_settings` - module for managing recipe settings.
            - `recipe.service.ts` - contains the service for managing recipes.
            - `recipe.ts` - contains the model of the recipe.
            - `filter-request.ts` - contains the model of the filter request.
        - `user_settings` - module for managing user settings.
            - `authenticate` - module for managing authentication.
                - `authenticate.service.ts` - contains the service for managing authentication.
                - `authenticate-user.ts` - contains the model of the authentication request.
                - `jws-interceptor.ts` - contains the interceptor for jwt authentication.
                - `auth.guard.ts` - contains the guard for jwt authentication.
            - `user-settings.service.ts` - contains the service for managing users.
            - `user.ts` - contains the model of the user.
            - `change-password-request.ts` - contains the model of the change password request.
            - `user-info.response.ts` - contains the model of the user info response.
