package ghoulish.creatures;

import ghoulish.labyrinth.Labyrinth;
import ghoulish.util.MoveAnswer;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class AI {
    private MoveAnswer moveAnswer = MoveAnswer.getInstance();
    //Возможные направления для шага
    private int[][] possibleMoves = {{-1,0},{1,0},{0,-1},{0,1}};
    private Random random = new Random();
    //Путь до героя
    private int[][] path;
    //Позиция игрока
    private int px, py;

    public AI(){
        path = new int[Labyrinth.getInstance().getN()][Labyrinth.getInstance().getM()];
    }

    private void clearPath(){
        int n = path.length;
        int m = path[0].length;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++)
                path[i][j] = -1;
        }
    }

    public void calculatePath(int _py, int _px){
        px = _px;
        py = _py;
        clearPath();

        LinkedList<Trio> queue = new LinkedList<>();

        boolean[] used = new boolean[path.length * path[0].length];
        Arrays.fill(used, false);

        queue.add(new Trio(py, px, -2));

        while(!queue.isEmpty()) {
            Trio cur = queue.poll();

            path[cur.y][cur.x] = cur.dir;
            used[cur.y * path[0].length + cur.x] = true;

            for(int i = 0; i < 4; i++){
                int y = cur.y - possibleMoves[i][0];
                int x = cur.x - possibleMoves[i][1];
                if(moveAnswer.noWallOrClosedDoor(y,x) && !used[y * path[0].length + x]){
                    queue.add(new Trio(y,x,i));
                }
            }
        }

        int lol = 1;
    }

    public Pair<Integer, Integer> monsterMove(int my, int mx, int angerRange){
        if(path[my][mx] == -2)
            return new Pair<>(0,0);
        if(path[my][mx] == -1 || Math.sqrt((px-mx)*(px-mx) + (py-my)*(py-my)) > angerRange || !moveAnswer.canMoveMonster(my + possibleMoves[path[my][mx]][0], mx+ possibleMoves[path[my][mx]][1]))
            return randomMove(my, mx);


        return new Pair<>(possibleMoves[path[my][mx]][0], possibleMoves[path[my][mx]][1]);
    }

    private Pair<Integer, Integer> randomMove(int my, int mx){
        ArrayList<Integer> possibilities = new ArrayList<>();
        for(int i = 0; i < 4; i++) {
            if(moveAnswer.canMoveMonster(my + possibleMoves[i][0],mx + possibleMoves[i][1])){
                possibilities.add(i);
            }
        }

        int len = possibilities.size();
        if(len == 0)
            return new Pair<>(0,0);

        int index = possibilities.get(random.nextInt(len));

        return new Pair<>(possibleMoves[index][0], possibleMoves[index][1]);
    }

    public static class Trio{
        int y,x,dir;

        Trio(int _y, int _x, int _dir){
            y = _y;
            x = _x;
            dir = _dir;
        }
    }
}
