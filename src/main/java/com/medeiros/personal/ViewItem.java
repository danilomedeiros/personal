package com.medeiros.personal;

import com.medeiros.personal.views.AlunosView;
import com.medeiros.personal.views.CalendarioView;
import com.medeiros.personal.views.DashboardView;
import com.medeiros.personal.views.RelatoriosView;
import com.medeiros.personal.views.TreinosView;
import com.vaadin.navigator.View;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;

public enum ViewItem  {
    DASHBOARD("dashboard", DashboardView.class, FontAwesome.HOME, true), 
    TREINOS("treinos", TreinosView.class, FontAwesome.SOCCER_BALL_O, false), 
    ALUNOS("alunos", AlunosView.class, FontAwesome.MALE, false),
    RELATORIOS("reports", RelatoriosView.class, FontAwesome.FILE_TEXT_O, true),
    CALENDARIO("schedule", CalendarioView.class, FontAwesome.CALENDAR_O, false);

    private final String viewName;
    private final Class<? extends View> viewClass;
    private final Resource icon;
    private final boolean stateful;

    private ViewItem(final String viewName,
            final Class<? extends View> viewClass, final Resource icon,
            final boolean stateful) {
        this.viewName = viewName;
        this.viewClass = viewClass;
        this.icon = icon;
        this.stateful = stateful;
    }

    public boolean isStateful() {
        return stateful;
    }

    public String getViewName() {
        return viewName;
    }

    public Class<? extends View> getViewClass() {
        return viewClass;
    }

    public Resource getIcon() {
        return icon;
    }

    public static ViewItem getByViewName(final String viewName) {
        ViewItem result = null;
        for (ViewItem viewType : values()) {
            if (viewType.getViewName().equals(viewName)) {
                result = viewType;
                break;
            }
        }
        return result;
    }

}
