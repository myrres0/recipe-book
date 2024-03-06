import { Component } from '@angular/core';
import { RecipeService } from '../../../recipe_settings/recipe.service';
import { ActivatedRoute } from '@angular/router';
import { Recipe, RecipeCategory } from '../../../recipe_settings/recipe';

@Component({
  selector: 'app-recipe-info',
  templateUrl: './recipe-info.component.html',
  styleUrl: './recipe-info.component.css'
})
export class RecipeInfoComponent {
  recipe!: Recipe;
  id!: number;
  constructor(private recipeService: RecipeService, private route: ActivatedRoute) {
    this.id = this.route.snapshot.params['id'];
    this.recipeService.getRecipe(this.id).subscribe(data => {
      this.recipe = data;
    });
  }
}
