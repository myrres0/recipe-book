import { Component } from '@angular/core';
import { RecipeCategory } from '../../../recipe_settings/recipe';
import { RecipeService } from '../../../recipe_settings/recipe.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-offcanvas-filter',
  templateUrl: './offcanvas-filter.component.html',
  styleUrl: './offcanvas-filter.component.css'
})
export class OffcanvasFilterComponent {
  search!: string
  mine!: boolean
  image!: boolean
  categories = Object.values(RecipeCategory).filter(value => typeof value === 'string');
  selectedCategories!: number[]

  constructor(private recipeService: RecipeService, private router: Router) {
  }


  onSubmit() {
    this.selectedCategories = [];
    //get selected categories
    this.categories.forEach((category, index) => {
      const checkbox = document.getElementById(`check-${category}`) as HTMLInputElement;
      if (checkbox.checked) {
        this.selectedCategories.push(index);
      }
    });
    console.log(this.selectedCategories);
    if (this.search === undefined) {
      this.search = '';
    }
    if(this.mine === undefined) {
      this.mine = false;
    }
    if(this.image === undefined) {
      this.image = false;
    }
    if(this.selectedCategories === undefined) {
      this.selectedCategories = [];
    }

    this.router.navigate(['/recipes'], { queryParams: { search: this.search, categories: this.selectedCategories, mine: this.mine, image: this.image }});
    

  }
}
