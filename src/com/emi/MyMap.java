package com.emi;

import java.util.HashMap;
import java.util.Map;

public class MyMap {
    private Map<String, Terrain> gameMap;
    private static MyMap instance = new MyMap();

    private MyMap() {
        gameMap = new HashMap<>();
    }

    public static MyMap getInstance() {
        return instance;
    }

    public void addElements(String key, Terrain value) {
        gameMap.put(key, value);
    }

    public Terrain getTerrain(int x, int y) {
        return gameMap.get(x + "," + y);
    }
}
