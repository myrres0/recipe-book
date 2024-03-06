
export enum RecipeCategory {
    MAIN_DISH,
    DESSERT,
    SALAD,
    SOUP,
    DRINK,
    OTHER
}

export class Recipe {
    id!: number;
    name !: string;
    description!: string;
    ingredients!: string;
    date!: Date;
    category!: RecipeCategory;
    published!: boolean;
    likes!: number;
    image!: string;

    constructor();
    constructor(id: number, name: string, description: string, ingredients: string, date: Date, category: RecipeCategory, published: boolean, likes: number, image: string);

    constructor(id?: number, name?: string, description?: string, ingredients?: string, date?: Date, category?: RecipeCategory, published?: boolean, likes?: number, image?: string) {
        this.id = id!;
        this.name = name!;
        this.description = description!;
        this.ingredients = ingredients!;
        this.date = date!;
        this.category = category!;
        this.published = published!;
        this.likes = likes!;
        this.image = image!;
    }
}
