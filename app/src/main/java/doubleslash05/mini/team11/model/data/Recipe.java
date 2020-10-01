package doubleslash05.mini.team11.model.data;

public class Recipe extends Recipe_Base {

    public String recipeExplain;
    public String recipeName;
    public String recipeTime;
    public String recipeLevel;

    public Recipe(String recipeExplain,String recipeName,String recipeTime,String recipeLevel){
        super(recipeExplain,recipeName,recipeTime,recipeLevel,null,0,null);
        this.recipeExplain=recipeExplain;
        this.recipeName=recipeName;
        this.recipeTime=recipeTime;
        this.recipeLevel=recipeLevel;

    }
}
