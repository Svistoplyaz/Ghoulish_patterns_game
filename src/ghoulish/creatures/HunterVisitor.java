package ghoulish.creatures;

/**
 * Created by Alexander on 10.12.2017.
 */
public class HunterVisitor implements IVisitor{

    @Override
    public void visit(CleverMonster monster) {
        int y = monster.getY() - Player.getInstance().getY();
        int x = monster.getX() - Player.getInstance().getX();

        int distance = (int) Math.sqrt(y * y + x * x);
        if(distance <= Monster.angerRange)
            System.out.println("Monster on square " + (monster.getY() + 1) + " " + (monster.getX() + 1) + " is hunting you");
        else
            System.out.println("Monster on square " + (monster.getY() + 1) + " " + (monster.getX() + 1) + " isn't hunting you");
    }

    @Override
    public void visit(BlindMonster monster) {
        int y = monster.getY() - Player.getInstance().getY();
        int x = monster.getX() - Player.getInstance().getX();

        int distance = (int) Math.sqrt(y * y + x * x);
        if(distance <= 2)
            System.out.println("Monster on square " + (monster.getY() + 1) + " " + (monster.getX() + 1) + " can suddenly hit you");
    }

    @Override
    public void visit(StaticMonster monster) {

    }
}
