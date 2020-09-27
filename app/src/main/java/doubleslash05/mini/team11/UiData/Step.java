package doubleslash05.mini.team11.UiData;

public class Step extends Recipe_Base {
    public String recipeStep;
    public int recipeImage;

    public Step(String recipeStep, int recipeImage) {
        super(null,null,null,null,recipeStep,recipeImage,null);
        this.recipeStep = recipeStep;
        this.recipeImage = recipeImage;
    }

}
