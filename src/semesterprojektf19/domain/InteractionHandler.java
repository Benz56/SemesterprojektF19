package semesterprojektf19.domain;

import java.util.List;

/**
 *
 * Contract detailing methods needed for communication between the presentation
 * and domain layer
 *
 */
public interface InteractionHandler {

    List<String[]> start(String playerName);

    List<String> getStoredHighscores();

    List<String[]> update(String keyPressed);

    List<String[]> update(String clickedNode, int[] position);

    public int storeHighscore(int correctQuizAnswers);
}
