package ghoulish.util;

import ghoulish.labyrinth.*;

public class FloorBuilder implements IBuilder{
    private String textureFloor = "resources/Labyrinth/Floor/";
    String instruction;
    Part result;
    int y,x;
    FloorBuilder(int _y, int _x,String inst){
        y = _y;
        x = _x;
        instruction = inst;
    }

    public void createNew(){
        result = new Floor(y, x, textureFloor + "Floor.png");
    }

    @Override
    public void buildLayer0() {
        if(instruction.contains("T")){
            result = new Trap(y,x,result,textureFloor + "Trap.png");
        }
    }

    @Override
    public void buildLayer1() {
        if(instruction.contains("S")){
            result = new Bones(y,x,result,textureFloor + "Bones.png");
        }
    }

    @Override
    public void buildLayer2() {
        if(instruction.contains("D")){
            result = new Door(y,x,result,textureFloor + "DoorClosed.png");
        }
    }

    public Part getResult(){
        return result;
    }
}
