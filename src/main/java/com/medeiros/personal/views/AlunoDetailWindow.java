package com.medeiros.personal.views;

import java.util.Arrays;

import com.medeiros.personal.domain.Aluno;
import com.medeiros.personal.event.PersonalEvent.CloseOpenWindowsEvent;
import com.medeiros.personal.event.PersonalEventBus;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.ItemCaptionGenerator;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;


public class AlunoDetailWindow extends Window {

	private static final long serialVersionUID = 1L;
	private Aluno aluno;
	public static final String ID = "alunowindow";
	//    private final BeanFieldGroup<Aluno> fieldGroup;
	private TextField firstNameField;
	private TextField lastNameField;
	private ComboBox<String> titleField;
	private RadioButtonGroup<Boolean> sexField;
	private Component detail;
	private Component form;
	private TabSheet abaPrincipal;
	
	private Button btnSalvar;
	private Button btnSair;
	private Button btnCancelar;
	private Button btnEditar;
	private AbstractLayout footer;

	public AlunoDetailWindow(Aluno aluno, final boolean treinosTabOpen) {
		this.aluno = aluno;
		addStyleName("profile-window");
		setId(ID);
		Responsive.makeResponsive(this);
		setModal(true);
		setCloseShortcut(KeyCode.ESCAPE, null);
		setResizable(false);
		setClosable(false);
		setHeight(90.0f, Unit.PERCENTAGE);

		VerticalLayout content = new VerticalLayout();
		content.setSizeFull();
		content.setMargin(new MarginInfo(true, false, false, false));
		setContent(content);
		//
		abaPrincipal = new TabSheet();
		abaPrincipal.setSizeFull();
		abaPrincipal.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
		abaPrincipal.addStyleName(ValoTheme.TABSHEET_ICONS_ON_TOP);
		abaPrincipal.addStyleName(ValoTheme.TABSHEET_CENTERED_TABS);
		content.addComponent(abaPrincipal);
		content.setExpandRatio(abaPrincipal, 1f);
		abaPrincipal.addComponent(buildAlunoTab());
		abaPrincipal.addComponent(buildTreinosTab());
		footer = buildFooter();
		content.addComponent(footer);

	}

	private Component buildDetail() {
		HorizontalLayout detalhe = new HorizontalLayout();
		detalhe.setWidth(100.0f, Unit.PERCENTAGE);
		detalhe.addStyleName(ValoTheme.LAYOUT_HORIZONTAL_WRAPPING);
		detalhe.setMargin(true);
		detalhe.setSpacing(true);

		final Image coverImage = new Image(null, new ExternalResource(""));
		coverImage.addStyleName("cover");
		detalhe.addComponent(coverImage);

		Component detailsForm = buildDetailsForm(aluno);
		detalhe.addComponent(detailsForm);
		detalhe.setExpandRatio(detailsForm, 1);

		detail = new Panel(detalhe);
		detail.setCaption("Detalhe Aluno");
		detail.setSizeFull();
		detail.addStyleName(ValoTheme.PANEL_BORDERLESS);
		detail.addStyleName("scroll-divider");
		return detail;
	}

	private Component buildTreinosTab() {

		VerticalLayout root = new VerticalLayout();
		root.setCaption("Preferences");
		root.setIcon(FontAwesome.COGS);
		root.setSpacing(true);
		root.setMargin(true);
		root.setSizeFull();

		Label message = new Label("Not implemented in this demo");
		message.setSizeUndefined();
		message.addStyleName(ValoTheme.LABEL_LIGHT);
		root.addComponent(message);
		root.setComponentAlignment(message, Alignment.MIDDLE_CENTER);

		return root;
	}
	private Component buildDetailsForm(Aluno aluno) {
		FormLayout fields = new FormLayout();
		fields.setSpacing(false);
		fields.setMargin(false);

		Label label;

		label = new Label(aluno.getFirstName() + " " + aluno.getLastName());
		label.setSizeUndefined();
		label.setCaption("Nome");
		fields.addComponent(label);

		label = new Label(aluno.isMale()?"Masculino":"Feminino");
		label.setSizeUndefined();
		label.setCaption("Sexo");
		fields.addComponent(label);

		final Button more = new Button("More…");
		more.addStyleName(ValoTheme.BUTTON_LINK);
		fields.addComponent(more);
		more.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(final ClickEvent event) {
				event.getButton().setVisible(false);
			}
		});

		return fields;

	}

	private Component buildForm() {
		FormLayout form = new FormLayout();

		form.setCaption("Update Aluno");
		form.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);

		firstNameField = new TextField("First Name");
		form.addComponent(firstNameField);
		lastNameField = new TextField("Last Name");
		form.addComponent(lastNameField);

		titleField = new ComboBox<>("Title",
				Arrays.asList("Mr.", "Mrs.", "Ms."));
		titleField.setPlaceholder("Please specify");
		form.addComponent(titleField);

		sexField = new RadioButtonGroup<>("Sexo", Arrays.asList(true, false));
		sexField.setItemCaptionGenerator(new ItemCaptionGenerator<Boolean>() {

			@Override
			public String apply(Boolean item) {
				return item ? "Masculino" : "Feminino";
			}
		});

		sexField.addStyleName("horizontal");
		form.addComponent(sexField);

		Label section = new Label("Contact Info");
		section.addStyleName(ValoTheme.LABEL_H4);
		section.addStyleName(ValoTheme.LABEL_COLORED);
		form.addComponent(section);

		Panel formWrapper = new Panel(form);
		formWrapper.setSizeFull();
		formWrapper.addStyleName(ValoTheme.PANEL_BORDERLESS);
		formWrapper.addStyleName("scroll-divider");

		return formWrapper;
	}

	private Component buildAlunoTab() {
		detail= buildDetail();
		form = buildForm();
		return detail;
	}

	private HorizontalLayout buildFooter() {

		HorizontalLayout footer = new HorizontalLayout();
		footer.addStyleName(ValoTheme.WINDOW_BOTTOM_TOOLBAR);
		footer.setWidth(100.0f, Unit.PERCENTAGE);

		btnEditar = new Button("Editar");
		btnEditar.addStyleName(ValoTheme.BUTTON_PRIMARY);
		btnEditar.setIcon(FontAwesome.PENCIL);
		btnEditar.addStyleName(ValoTheme.BUTTON_ICON_ALIGN_RIGHT);
		btnEditar.addClickListener(e->configurarFormEdicao());

		btnSair = new Button("Sair");
		btnSair.addStyleName(ValoTheme.BUTTON_QUIET);
		btnSair.addClickListener(e->sair());
		
		btnCancelar = new Button("Cancelar");
		btnCancelar.addStyleName(ValoTheme.BUTTON_QUIET);
		btnCancelar.addClickListener(e->cancelarEdicao());
		
		btnSalvar = new Button("Salvar");
		btnSalvar.addStyleName(ValoTheme.BUTTON_QUIET);
		btnSalvar.addClickListener(e->salvar());
		
		footer.addComponent(btnEditar);
		footer.addComponent(btnSair);
		btnSair.focus();

		footer.setComponentAlignment(btnSair, Alignment.TOP_LEFT);
		footer.setComponentAlignment(btnEditar, Alignment.TOP_LEFT);
		return footer;
	}

	private Object cancelarEdicao() {
		Notification.show("Cancelando edição", Type.HUMANIZED_MESSAGE);

		abaPrincipal.replaceComponent(form, detail);
		footer.replaceComponent(btnSalvar, btnEditar);
		footer.replaceComponent(btnCancelar, btnSair);
		return null;
	}

	private Object salvar() {
		Notification.show("Bean salvo", Type.HUMANIZED_MESSAGE);

		abaPrincipal.replaceComponent(form, detail);
		footer.replaceComponent(btnSalvar, btnEditar);
		footer.replaceComponent(btnCancelar, btnSair);
		
		return null;
	}

	private Object sair() {
		close();
		return null;
	}

	private Object configurarFormEdicao() {
		footer.replaceComponent(btnEditar, btnSalvar);
		footer.replaceComponent(btnSair, btnCancelar);
    	abaPrincipal.replaceComponent(detail, form);
		return null;
	}

	public static void open(final boolean preferencesTabActivem, Aluno aluno) {
		PersonalEventBus.post(new CloseOpenWindowsEvent());
		Window w = new AlunoDetailWindow(aluno, true);
		UI.getCurrent().addWindow(w);
		w.focus();
	}


}
