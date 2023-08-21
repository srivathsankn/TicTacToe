package Strategies;

import models.DifficultyLevel;

public class StrategyDecider {
    public static BotPlayingStrategy assignStrategy(DifficultyLevel difficultyLevel)
    {
        return switch (difficultyLevel)
        {
            case EASY -> new FillNextSpotBotPlayingStrategy();
            case MEDIUM -> new FillNextSpotBotPlayingStrategy();
            case HARD -> new FillNextSpotBotPlayingStrategy();
            default -> new FillNextSpotBotPlayingStrategy();
        };
    }
}
