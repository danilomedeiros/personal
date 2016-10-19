package com.medeiros.personal.event;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.SubscriberExceptionContext;
import com.google.common.eventbus.SubscriberExceptionHandler;
import com.medeiros.personal.HomeUI;

/**
 * A simple wrapper for Guava event bus. Defines static convenience methods for
 * relevant actions.
 */
public class PersonalEventBus implements SubscriberExceptionHandler {

    private final EventBus eventBus = new EventBus(this);

    public static void post(final Object event) {
        HomeUI.getDashboardEventbus().eventBus.post(event);
    }

    public static void register(final Object object) {
        HomeUI.getDashboardEventbus().eventBus.register(object);
    }

    public static void unregister(final Object object) {
        HomeUI.getDashboardEventbus().eventBus.unregister(object);
    }

    @Override
    public final void handleException(final Throwable exception,
            final SubscriberExceptionContext context) {
        exception.printStackTrace();
    }
}