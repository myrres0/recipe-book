import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterComponent } from './components/public/register/register.component';
import { HomeComponent } from './components/public/home/home.component';
import { UserHomeComponent } from './components/user/user-home/user-home.component';
import { UserSettingsComponent } from './components/user/user-settings/user-settings.component';
import { LoginComponent } from './components/public/login/login.component';
import { ChangePasswordComponent } from './components/user/change-password/change-password.component';
import { LogoutComponent } from './components/user/logout/logout.component';
import { CreateRecipeComponent } from './components/recipe/create-recipe/create-recipe.component';
import { RecipesComponent } from './components/recipe/recipes/recipes.component';
import { authGuard } from './user_settings/authenticate/auth.guard';
import { RecipeInfoComponent } from './components/recipe/recipe-info/recipe-info.component';
import { EditRecipeComponent } from './components/recipe/edit-recipe/edit-recipe.component';

const routes: Routes = [
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  {path: 'user-home', component: RecipesComponent, canActivate: [authGuard]},
  {path: 'home', component: HomeComponent},
  {path: '../register', component: RegisterComponent},
  {path: 'user-settings', component: UserSettingsComponent, canActivate: [authGuard]},
  {path: 'user-settings/change-password', component: ChangePasswordComponent, canActivate: [authGuard]},
  {path: 'logout', component: LogoutComponent, canActivate: [authGuard]},
  {path: 'create-recipe', component: CreateRecipeComponent, canActivate: [authGuard]},
  {path: 'recipes', component: RecipesComponent, canActivate: [authGuard]},
  {path: 'recipe-info/:id', component: RecipeInfoComponent, canActivate: [authGuard]},
  {path: 'edit-recipe/:id', component: EditRecipeComponent, canActivate: [authGuard]},
  {path: 'recipes/filter?search=:search&categories=:categories&mine=:mine&image=:image', component: RecipesComponent, canActivate: [authGuard]},
  //redirect to home if mistakes
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: '**', redirectTo: '/home', pathMatch: 'full' } // Redirect to home if route not found
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
