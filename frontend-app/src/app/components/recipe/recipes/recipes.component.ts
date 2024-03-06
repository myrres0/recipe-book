import { Component } from '@angular/core';
import { RecipeService } from '../../../recipe_settings/recipe.service';
import { ActivatedRoute, Router } from '@angular/router';
import { RecursiveAstVisitor } from '@angular/compiler';
import { RecipeCategory } from '../../../recipe_settings/recipe';

@Component({
  selector: 'app-recipes',
  templateUrl: './recipes.component.html',
  styleUrl: './recipes.component.css'
})
export class RecipesComponent {

  search!: string;

  search_params = this.activatedRoute.snapshot.queryParamMap.get('search');
  default_categories_params: number[];

  constructor(private activatedRoute: ActivatedRoute, private router: Router){ 
    this.default_categories_params = Object.keys(RecipeCategory)
      .filter(value => !isNaN(Number(value)))
      .map(value => Number(value));
  }

  search_func() {
    console.log(this.search);

    if (this.search_params !== null && this.search_params !== undefined){
      this.router.navigate([], {
      relativeTo: this.activatedRoute,
      queryParams: { search: this.search },
      queryParamsHandling: 'merge' // Merge with existing query parameters
    });
    }
    else{
      this.router.navigate(['/recipes'], { queryParams: { search: this.search, categories: this.default_categories_params, mine: false, image: false }});
    }
    

  }

}
