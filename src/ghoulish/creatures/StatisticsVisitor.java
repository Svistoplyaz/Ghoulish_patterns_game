package ghoulish.creatures;

/**
 * Created by Alexander on 10.12.2017.
 */
public class StatisticsVisitor implements IVisitor {
    private int[][] possibleMoves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    @Override
    public void visit(CleverMonster monster) {
        int y = monster.getY() - Player.getInstance().getY();
        int x = monster.getX() - Player.getInstance().getX();

        int distance = (int) Math.sqrt(y * y + x * x);
        String ans = "Monster on square " + (monster.getY() + 1) + " " + (monster.getX() + 1) + " is " + distance + " squares from you";
        System.out.println(ans);
    }

    @Override
    public void visit(BlindMonster monster) {
        System.out.println("Monster on square " + (monster.getY() + 1) + " " + (monster.getX() + 1) + " is wandering around");
    }

    @Override
    public void visit(StaticMonster monster) {
        Layer1 layer1 = Layer1.getInstance();
        int y = monster.getY();
        int x = monster.getX();

        int count = 0;
        for (int i = 0; i < 4; i++) {
            if (layer1.hasMonster(y + possibleMoves[i][0], x + possibleMoves[i][1]))
                count++;
        }

        System.out.println("Monster on square " + (monster.getY() + 1) + " " + (monster.getX() + 1) + " has " + count + " neighbours");
    }
}
