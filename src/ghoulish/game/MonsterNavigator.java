package ghoulish.game;

import ghoulish.labyrinth.Layer0;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class MonsterNavigator {
    private MoveAnswer moveAnswer;
    //Возможные направления для шага
    private int[][] possibleMoves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private Random random = new Random();
    //Путь до героя
    private int[][] path;
    //Позиция игрока
    public int px, py;

    MonsterNavigator(MoveAnswer _moveAnswer) {
        path = new int[Layer0.getInstance().getN()][Layer0.getInstance().getM()];
        moveAnswer = _moveAnswer;
    }

    private void clearPath() {
        int n = path.length;
        int m = path[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++)
                path[i][j] = -1;
        }
    }

    void calculatePath(int _py, int _px) {
        px = _px;
        py = _py;
        clearPath();

        LinkedList<Trio> queue = new LinkedList<>();

        boolean[] used = new boolean[path.length * path[0].length];
        Arrays.fill(used, false);

        queue.add(new Trio(py, px, -2));

        while (!queue.isEmpty()) {
            Trio cur = queue.poll();

            path[cur.y][cur.x] = cur.dir;
            used[cur.y * path[0].length + cur.x] = true;

            for (int i = 0; i < 4; i++) {
                int y = cur.y - possibleMoves[i][0];
                int x = cur.x - possibleMoves[i][1];
                if (moveAnswer.noWallOrClosedDoor(y, x) && !used[y * path[0].length + x]) {
                    queue.add(new Trio(y, x, i));
                }
            }
        }
    }

//    public Pair<Integer, Integer> monsterMove(Monster monster, int angerRange) {
//        int my = monster.getY();
//        int mx = monster.getX();
//        if (!monster.dynamic())
//            return new Pair<>(0, 0);
//        if (monster.blind() || path[my][mx] == -1 || Math.sqrt((px - mx) * (px - mx) + (py - my) * (py - my)) > angerRange ||
//                !moveAnswer.canMoveMonster(my + possibleMoves[path[my][mx]][0], mx + possibleMoves[path[my][mx]][1], false))
//            return randomMove(monster);
//
//        return new Pair<>(possibleMoves[path[my][mx]][0], possibleMoves[path[my][mx]][1]);
//    }
//
//    private Pair<Integer, Integer> randomMove(Monster monster) {
//        int my = monster.getY();
//        int mx = monster.getX();
//        ArrayList<Integer> possibilities = new ArrayList<>();
//        for (int i = 0; i < 4; i++) {
//            if (moveAnswer.canMoveMonster(my + possibleMoves[i][0], mx + possibleMoves[i][1], monster.smart())) {
//                possibilities.add(i);
//            }
//        }
//
//        int len = possibilities.size();
//        if (len == 0)
//            return new Pair<>(0, 0);
//
//        int index = possibilities.get(random.nextInt(len));
//
//        return new Pair<>(possibleMoves[index][0], possibleMoves[index][1]);
//    }

    Pair<Integer, Integer> birthPlace(int my, int mx) {
        ArrayList<Integer> possibilities = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            if (moveAnswer.canMoveMonster(my + possibleMoves[i][0], mx + possibleMoves[i][1], true) && moveAnswer.noPlayer(my + possibleMoves[i][0], mx + possibleMoves[i][1])) {
                possibilities.add(i);
            }
        }

        int len = possibilities.size();
        if (len == 0)
            return null;

        int index = possibilities.get(random.nextInt(len));

        return new Pair<>(possibleMoves[index][0], possibleMoves[index][1]);
    }

    public int[][] getPath() {
        return path;
    }

    private static class Trio {
        int y, x, dir;

        Trio(int _y, int _x, int _dir) {
            y = _y;
            x = _x;
            dir = _dir;
        }
    }
}
