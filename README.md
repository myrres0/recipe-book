# Recipe Book application using Spring boot and Angular

_Application is still in development._

Application for managing recipes. The user can add, edit, delete and view recipes.
The application has a user authentication system. The user can register, log in and log out.

## Technologies used

List of technologies used in the project:

### Backend

- Java
- Spring framework
- Spring Security
- Spring Data JPA
- Spring MVC
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


## Recent updates ![update](./src/assets/img/update.png)


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