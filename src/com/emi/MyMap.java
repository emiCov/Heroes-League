package com.emi;

import java.util.HashMap;
import java.util.Map;

public class MyMap {
    private Map<String, Terrain> gameMap;

    public MyMap() {
        gameMap = new HashMap<>();
    }

    public void addElements(String key, Terrain value) {
        gameMap.put(key, value);
    }

    public Map<String, Terrain> getGameMap() {
        return gameMap;
    }

    public Terrain getTerrain(int x, int y) {
        return gameMap.get(x + "," + y);
    }
}
