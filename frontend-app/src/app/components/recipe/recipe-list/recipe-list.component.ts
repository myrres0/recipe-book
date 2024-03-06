import { Component } from '@angular/core';
import { Recipe } from '../../../recipe_settings/recipe';
import { RecipeService } from '../../../recipe_settings/recipe.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-recipe-list',
  templateUrl: './recipe-list.component.html',
  styleUrl: './recipe-list.component.css'
})
export class RecipeListComponent {
  //check if filter was applied


  recipes!: Recipe[];
  constructor(private recipeService: RecipeService, private activatedRoute: ActivatedRoute) {
    this.activatedRoute.queryParams.subscribe(params => {
      if (params['search'] || params['categories'] || params['mine'] || params['image']) {
        this.recipeService.filterRecipes(params['search'], params['categories'], params['mine'], params['image']).subscribe((recipes: Recipe[]) => {
          this.recipes = recipes;
        });
      } else {
        this.recipeService.getAllRecipes().subscribe((recipes: Recipe[]) => {
          this.recipes = recipes;
        });
      }
    });

  }
}
