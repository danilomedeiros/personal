
package com.medeiros.personal;
import java.util.Locale;

import com.medeiros.personal.data.DataProvider;
import com.medeiros.personal.data.fake.FakeDataProvider;
import com.medeiros.personal.event.PersonalEvent.BrowserResizeEvent;
import com.medeiros.personal.event.PersonalEventBus;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.Page;
import com.vaadin.server.Page.BrowserWindowResizeEvent;
import com.vaadin.server.Page.BrowserWindowResizeListener;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
@Theme("dashboard")
@Title("Personal")
public class HomeUI extends UI {

    private final static PersonalEventBus eventbus = new PersonalEventBus();
    public final static DataProvider dataProvider = new FakeDataProvider();
	
	public static class Servlet extends VaadinServlet {
		
	}

	@Override
	protected void init(final VaadinRequest request) {
		setLocale(Locale.getDefault());
		PersonalEventBus.register(this);
		Responsive.makeResponsive(this);
		addStyleName(ValoTheme.UI_WITH_MENU);
//		TODO verificar usuario logado aqui: vide dashboard
		updateContent();
		
        Page.getCurrent().addBrowserWindowResizeListener(
                new BrowserWindowResizeListener() {
                    @Override
                    public void browserWindowResized(final BrowserWindowResizeEvent event) {
                        PersonalEventBus.post(new BrowserResizeEvent());
                    }
                });
	}

	private void updateContent() {
		setContent(new MainView());
		
	}

	public static PersonalEventBus getDashboardEventbus() {
		return eventbus;
	}

	
	
}