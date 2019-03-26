/* 
 * Developed by SDU OOP E18 SE/ST grp 21
 * Frederik Alexander Hounsvad, Andreas Kaer Lauritzen,  Patrick Nielsen, Oliver Lind Nordestgaard, Benjamin Eichler Staugaard
 * The use of this work is limited to educational purposes
 */
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
