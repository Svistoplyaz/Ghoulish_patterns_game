package ghoulish.util;

public class Director {
    IBuilder builder;

    Director(IBuilder build){
        builder = build;
    }

    void construct(){
        builder.createNew();
        builder.buildLayer0();
        builder.buildLayer1();
        builder.buildLayer2();
    }
}
