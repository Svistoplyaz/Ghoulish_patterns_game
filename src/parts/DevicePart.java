package parts;

import commands.IAction;

import java.util.ArrayList;

/**
 * Created by Alexandr on 16.10.2017.
 */
public abstract class DevicePart {
    ArrayList<IAction> commands = new ArrayList<>();

    void add_com(IAction adding){
        commands.add(adding);
    }

    void execute_com(int ind, ArrayList<String> args){
        commands.get(ind).perform_action(args);
    }


}
