/* 
 * Developed by SDU OOP E18 SE/ST grp 21
 * Frederik Alexander Hounsvad, Andreas Kaer Lauritzen,  Patrick Nielsen, Oliver Lind Nordestgaard, Benjamin Eichler Staugaard
 * The use of this work is limited to educational purposes
 */
package semesterprojektf19.presentation;

import javafx.scene.input.MouseEvent;
import semesterprojektf19.domain.InteractionHandler;
import semesterprojektf19.domain.InteractionHandlerImpl;

/**
 * The class in charge of communicating the click- and keypress events to the
 * domain layer. This is the only class that accesses the domain layer which
 * results in a clear separation of our layers.
 *
 */
public class InteractionCommunicator {

    private final InteractionHandler interactionHandler = new InteractionHandlerImpl();

    public void mouseClickedEvent() {
        interactionHandler.start("");
    }
}
