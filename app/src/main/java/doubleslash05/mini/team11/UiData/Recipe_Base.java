package doubleslash05.mini.team11.UiData;

public abstract class Recipe_Base {

    public String recipeExplain;
    public String recipeName;
    public String recipeTime;
    public String recipeLevel;
    public String recipeStep;
    public int recipeImage;
    public String text;

    public Recipe_Base(String recipeExplain, String recipeName, String recipeTime, String recipeLevel,String recipeStep, int recipeImage,String text) {
        this.recipeExplain = recipeExplain;
        this.recipeName = recipeName;
        this.recipeTime = recipeTime;
        this.recipeLevel = recipeLevel;
        this.recipeStep=recipeStep;
        this.recipeImage=recipeImage;
        this.text=text;
    }
}
