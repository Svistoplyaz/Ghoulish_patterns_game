package ghoulish.util;

import ghoulish.creatures.*;
import ghoulish.labyrinth.Layer0;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class MonsterPlacer implements IMonsterGiver {
    private ArrayList<Monster> creatures = new ArrayList<>();
    private Layer0 layer0 = Layer0.getInstance();
    private int n = layer0.getN(), m = layer0.getM();
    private boolean[][] free = new boolean[n][m];
    private int[][] possibleMoves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private Random random = new Random();
    private ArrayList<IMonsterFactory> factories = new ArrayList<>();

    public MonsterPlacer() {
        fillWallsAndDoors();

        factories.add(new BlindMonsterFactory());
        factories.add(new CleverMonsterFactory());
        factories.add(new StaticMonsterFactory());

        for (int i = 1; i < n - 1; i++)
            for (int j = 1; j < m - 1; j++) {
                if (free[i][j]) {
                    ArrayList<Pair<Integer, Integer>> variants = BFS(i, j);
                    Pair<Integer, Integer> chose = variants.get(random.nextInt(variants.size()));
                    creatures.add(factories.get(random.nextInt(factories.size())).createMonster(chose.getKey(), chose.getValue()));
                }
            }
    }

    public void fillWallsAndDoors() {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                free[i][j] = layer0.canMoveHere(i, j);
            }
    }

    public ArrayList<Pair<Integer, Integer>> BFS(int y, int x) {
        ArrayList<Pair<Integer, Integer>> variants = new ArrayList<>();

        LinkedList<Pair<Integer, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(y, x));

        while (!queue.isEmpty()) {
            Pair<Integer, Integer> cur = queue.poll();
            variants.add(cur);
            int cy = cur.getKey();
            int cx = cur.getValue();
            free[cy][cx] = false;
            for (int i = 0; i < 4; i++) {
                int ny = cy + possibleMoves[i][0];
                int nx = cx + possibleMoves[i][1];
                if (free[ny][nx])
                    queue.add(new Pair<>(ny, nx));
            }
        }

        return variants;
    }

    @Override
    public ArrayList<Monster> readMonsters() {
        return creatures;
    }
}
