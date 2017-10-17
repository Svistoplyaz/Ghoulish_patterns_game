package storage;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Alexander on 16.10.2017.
 */
public class Recipe {
    HashMap<Product, Double> cont;
    RType type;
    String name;

    public Recipe(String n, HashMap<Product, Double> c, RType rt){
        name = n;
        cont = c;
        type = rt;
    }

    public HashMap<Product, Double> getCont(){
        return cont;
    }

    @Override
    public String toString(){
        return name;
    }
}
