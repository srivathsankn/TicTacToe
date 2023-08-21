package models;

import java.util.Map;
import java.util.HashMap;

public enum DifficultyLevel {
    EASY(0),MEDIUM(1),HARD(2);

    private int diffLevel;
    public static Map<Integer, DifficultyLevel> difficultyLevelMap = new HashMap<>();

    DifficultyLevel(int diffLevel)
    {
        this.diffLevel = diffLevel;
    }

    static
    {
        for (DifficultyLevel d : values())
        {
            difficultyLevelMap.put(d.diffLevel, d);
        }
    }
//    public Map<Integer, DifficultyLevel> getDifficultyLevelMap()
//    {
//        for (DifficultyLevel d : values())
//        {
//            difficultyLevelMap.put(d.diffLevel, d);
//        }
//        return difficultyLevelMap;
//    }
}
