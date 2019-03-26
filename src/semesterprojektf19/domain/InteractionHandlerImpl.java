/* 
 * Developed by SDU OOP E18 SE/ST grp 21
 * Frederik Alexander Hounsvad, Andreas Kaer Lauritzen,  Patrick Nielsen, Oliver Lind Nordestgaard, Benjamin Eichler Staugaard
 * The use of this work is limited to educational purposes
 */
package semesterprojektf19.domain;

import java.io.IOException;
import java.util.List;
import semesterprojektf19.persistence.Storage;
import semesterprojektf19.persistence.StorageImpl;

/**
 *
 * A handler for the domain layer of world of safety
 *
 */
public class InteractionHandlerImpl implements InteractionHandler {

    private Storage dataAccess;

    public InteractionHandlerImpl() {
        try {
            this.dataAccess = new StorageImpl("storage");
        } catch (IOException ex) {
        }
    }

    @Override
    public List<String[]> start(String playerName) {
        return null;
    }

    @Override
    public List<String> getStoredHighscores() {
        return null;
    }

    @Override
    public List<String[]> update(String keyPressed) {
        return null;
    }

    @Override
    public List<String[]> update(String clickedNode, int[] position) {
        return null;
    }

    @Override
    public int storeHighscore(int correctQuizAnswers) {
        return 0;
    }

}
