package semesterprojektf19.presentation;

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
