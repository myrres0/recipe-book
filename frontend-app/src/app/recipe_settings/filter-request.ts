import { RecipeCategory } from "./recipe";



export class FilterRequest {
    search:string;
    categories: RecipeCategory[];
    mine:boolean;
    image:boolean;

    constructor();

    constructor(search:string, categories:RecipeCategory[], mine:boolean, image:boolean);

    constructor(search?:string, categories?:RecipeCategory[], mine?:boolean, image?:boolean){
        this.search = search || "";
        this.categories = categories || [];
        this.mine = mine || false;
        this.image = image || false;
    }

}
