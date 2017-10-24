package old.proxy;

import old.parts.Console;
import old.parts.DevicePart;
import old.storage.Recipe;

/**
 * Created by Alexander on 16.10.2017.
 */
public class ProxyConsole extends DevicePart {
    Console cn;

    public ProxyConsole(Console _cn){
        cn = _cn;
    }

    public void addRecipe(int path, Recipe r){
        if(path == 1){
            cn.addRecipe(r);
        }else{
            System.out.println("Don't have enough permissions");
        }
    }
}
