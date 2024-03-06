import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterComponent } from './components/public/register/register.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { HomeComponent } from './components/public/home/home.component';
import { JwtInterceptor } from './user_settings/authenticate/jwt-interceptor';
import { UserHomeComponent } from './components/user/user-home/user-home.component';
import { NavComponent } from './components/public/nav/nav.component';
import { UserNavComponent } from './components/user/user-nav/user-nav.component';
import { UserSettingsComponent } from './components/user/user-settings/user-settings.component';
import { LoginComponent } from './components/public/login/login.component';
import { ChangePasswordComponent } from './components/user/change-password/change-password.component';
import { LogoutComponent } from './components/user/logout/logout.component';
import { CreateRecipeComponent } from './components/recipe/create-recipe/create-recipe.component';
import { RecipesComponent } from './components/recipe/recipes/recipes.component';
import { RecipeListComponent } from './components/recipe/recipe-list/recipe-list.component';
import { RecipeInfoComponent } from './components/recipe/recipe-info/recipe-info.component';
import { OffcanvasFilterComponent } from './components/recipe/offcanvas-filter/offcanvas-filter.component';
import { EditRecipeComponent } from './components/recipe/edit-recipe/edit-recipe.component';

@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    LoginComponent,
    HomeComponent,
    UserHomeComponent,
    NavComponent,
    UserNavComponent,
    UserSettingsComponent,
    ChangePasswordComponent,
    LogoutComponent,
    CreateRecipeComponent,
    RecipesComponent,
    RecipeListComponent,
    RecipeInfoComponent,
    OffcanvasFilterComponent,
    EditRecipeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
