package com.medeiros.personal.views;


import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Function;

import com.google.gwt.thirdparty.guava.common.eventbus.Subscribe;
import com.medeiros.personal.HomeUI;
import com.medeiros.personal.domain.Aluno;
import com.medeiros.personal.event.PersonalEvent.BrowserResizeEvent;
import com.medeiros.personal.event.PersonalEventBus;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.Responsive;
import com.vaadin.server.data.ListDataSource;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.Grid.ItemClick;
import com.vaadin.ui.Grid.ItemClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings({ "serial"})
public final class AlunosView extends VerticalLayout implements View {

    private final Grid<Aluno> grid;
    private static final Set<Column<Aluno, ?>> collapsibleColumns = new LinkedHashSet<>();

    public AlunosView() {
        setSizeFull();
        addStyleName("transactions");
        PersonalEventBus.register(this);
        addComponent(buildToolbar());
        grid = buildGrid();
        addComponent(grid);
        setExpandRatio(grid, 1);
    }

    @Override
    public void detach() {
        super.detach();
        // A new instance of TransactionsView is created every time it's
        // navigated to so we'll need to clean up references to it on detach.
        PersonalEventBus.unregister(this);
    }

    private Component buildToolbar() {
        HorizontalLayout header = new HorizontalLayout();
        header.addStyleName("viewheader");
        header.setSpacing(true);
        Responsive.makeResponsive(header);

        Label title = new Label("Alunos");
        title.setSizeUndefined();
        title.addStyleName(ValoTheme.LABEL_H1);
        title.addStyleName(ValoTheme.LABEL_NO_MARGIN);
        header.addComponent(title);
        return header;
    }

    private Grid<Aluno> buildGrid() {
        final Grid<Aluno> grid = new Grid<Aluno>();
        grid.setSizeFull();

        collapsibleColumns.add(grid.addColumn("Nome", new Function<Aluno, String>() {
			@Override
			public String apply(Aluno t) {
				return t.getFirstName() + " "+t.getLastName();
			}
		}));

        collapsibleColumns.add(grid.addColumn("Sexo", new Function<Aluno, String>() {
			@Override
			public String apply(Aluno t) {
				return t.isMale()?"Masculino":"Feminino";
			}
		}));
		
        collapsibleColumns.add(grid.addColumn("Telefone", new Function<Aluno, String>() {
			@Override
			public String apply(Aluno t) {
				return t.getPhone();
			}
		}));

        collapsibleColumns.add(grid.addColumn("Email", new Function<Aluno, String>() {
			@Override
			public String apply(Aluno t) {
				return t.getEmail();
			}
		}));

        grid.setColumnReorderingAllowed(true);

        grid.addItemClickListener(new ItemClickListener<Aluno>() {

			@Override
			public void accept(ItemClick<Aluno> event) {
				Aluno aluno = event.getItem();
				AlunoDetailWindow.open(true, aluno);
			}
        	
		});
        ListDataSource<Aluno> dataSource = new ListDataSource<Aluno>(HomeUI.dataProvider.getAlunos());
        grid.setDataSource(dataSource.sortingBy(new Comparator<Aluno>() {
			@Override
			public int compare(Aluno o1, Aluno o2) {
				return o1.getFirstName().compareTo(o2.getFirstName());
			}
		}));
        return grid;
    }

    private boolean defaultColumnsVisible() {
        boolean result = true;
        for (Column<Aluno, ?> column : collapsibleColumns) {
            if (column.isHidden() == Page.getCurrent()
                    .getBrowserWindowWidth() < 800) {
                result = false;
            }
        }
        return result;
    }

    @Subscribe
    public void browserResized(final BrowserResizeEvent event) {
        // Some columns are collapsed when browser window width gets small
        // enough to make the table fit better.

        if (defaultColumnsVisible()) {
            for (Column<Aluno, ?> column : collapsibleColumns) {
                column.setHidden(Page.getCurrent().getBrowserWindowWidth() < 800);
            }
        }
    }

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
}
