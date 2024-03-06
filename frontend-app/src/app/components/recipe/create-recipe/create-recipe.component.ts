import { Component } from '@angular/core';
import { RecipeCategory, Recipe } from '../../../recipe_settings/recipe';
import { RecipeService } from '../../../recipe_settings/recipe.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-recipe',
  templateUrl: './create-recipe.component.html',
  styleUrl: './create-recipe.component.css'
})
export class CreateRecipeComponent {
  recipe!: Recipe;

  constructor(private recipeService: RecipeService, private router: Router) {
    this.recipe = new Recipe();
  }

  categories = Object.values(RecipeCategory).filter(value => typeof value === 'string');
  selectedCategory!: RecipeCategory;
  




  onSubmit(){
    this.recipe.category = this.selectedCategory;
    if (this.recipe.image == null) {
      this.recipe.image ="https://via.placeholder.com/150"
    }
    this.recipeService.createRecipe(this.recipe).subscribe({
      next: (response:any)=> {
        console.log('Recipe created successfully');
        this.router.navigate(['/user-home']);
      },
      error: (err:any) => {
        console.log(err);
      }
    });
    console.log(this.recipe);
  }

}
