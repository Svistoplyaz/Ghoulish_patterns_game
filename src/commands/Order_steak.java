package commands;

import java.util.ArrayList;

/**
 * Created by Alexandr on 16.10.2017.
 */
public class Order_steak implements IAction{
    public void perform_action(ArrayList<String> args){
        System.out.print("Your salad is ready\n");
    }

}
