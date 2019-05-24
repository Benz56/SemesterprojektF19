package semesterprojektf19.presentation;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.util.Duration;

/**
 * 
 * @author Gruppe 22 på SE/ST E19, MMMI, Syddansk Universitet
 */
public class IdleMonitor {

    private final Timeline idleTimeline;

    private final EventHandler<Event> userEventHandler;

    public IdleMonitor(Duration idleTime, Runnable notifier, boolean startMonitoring) {
        idleTimeline = new Timeline(new KeyFrame(idleTime, e -> notifier.run()));
        idleTimeline.setCycleCount(Animation.INDEFINITE);

        userEventHandler = e -> notIdle();

        if (startMonitoring) {
            startMonitoring();
        }
    }

    public IdleMonitor(Duration idleTime, Runnable notifier) {
        this(idleTime, notifier, false);
    }

    public void register(Scene scene, EventType<? extends Event> eventType) {
        scene.addEventFilter(eventType, userEventHandler);
    }

    public void notIdle() {
        if (idleTimeline.getStatus() == Animation.Status.RUNNING) {
            idleTimeline.playFromStart();
        }
    }

    public void startMonitoring() {
        idleTimeline.playFromStart();
    }

    public void stopMonitoring() {
        idleTimeline.stop();
    }
}
