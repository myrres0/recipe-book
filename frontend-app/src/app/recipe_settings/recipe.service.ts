import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Recipe, RecipeCategory } from './recipe';
import { ActivatedRoute } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class RecipeService {


  private baseUrl = 'http://localhost:8080/api/v1/recipe';


  constructor(private httpClient: HttpClient, private route: ActivatedRoute) { }

  getRecipe(id: number): Observable<Recipe> {
    return this.httpClient.get<Recipe>(`${this.baseUrl}/details/${id}`);
  }

  //update recipe
  updateRecipe(id: number, recipe: Recipe): Observable<any> {
    return this.httpClient.put(`${this.baseUrl}/${id}`, recipe);
  }

  //post recipe
  createRecipe(recipe: Recipe): Observable<any> {
    return this.httpClient.post(`${this.baseUrl}`, recipe);
  }

  //get all recipes (public and users only)
  getAllRecipes(): Observable<Recipe[]> {
    return this.httpClient.get<Recipe[]>(`${this.baseUrl}/filter?mine=false&image=false`);
  }

  //filter recipes
  filterRecipes(search: string, categories: number[], mine: boolean, image: boolean): Observable<Recipe[]> {
    return this.httpClient.get<Recipe[]>(`${this.baseUrl}/filter?search=${search}&categories=${categories}&mine=${mine}&image=${image}`);
  }



}
