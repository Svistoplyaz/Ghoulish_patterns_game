package parts;

import storage.Recipe;

/**
 * Created by Alexander on 16.10.2017.
 */
public class Console extends DevicePart {
    Chef ch;

    public void addRecipe(Recipe r){
        ch.addRecipe(r);
    }
}
