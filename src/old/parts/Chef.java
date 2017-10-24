package old.parts;

import javafx.util.Pair;
import old.storage.Product;
import old.storage.Recipe;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Alexandr on 16.10.2017.
 */
public class Chef extends DevicePart {
    Storage storage;
    ArrayList<Recipe> menu;

    public void initStorage(Storage st){
        storage = st;
    }

    public void addRecipe(Recipe r){
        menu.add(r);
    }

    public boolean chooseFromMenu(Recipe cur){
        ArrayList <Pair<Product, Double>> decrease = new ArrayList<>();

        boolean success = true;
        for(Map.Entry<Product, Double> entry : cur.getCont().entrySet()){
            if(!storage.checkAmount(entry.getKey(), entry.getValue())){
                success = false;
                break;
            }
        }

        if(success){
            for(Map.Entry<Product, Double> entry : cur.getCont().entrySet()){
                storage.decreaseAmount(entry.getKey(), entry.getValue());
            }
            return true;
        }else
            return false;
    }

    public void order(int i){
        Recipe r = menu.get(i);

        if(chooseFromMenu(r)){
            System.out.println("Your" + r.toString() + "will be ready soon");
        }else{
            System.out.println("We don't have enough ingredients for" + r.toString());
        }
    }
}
