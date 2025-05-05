package com.example.DemoServiceSpring.views;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;

@PageTitle("DemoServiceSpring")
@Menu(title= "Inicio", icon = "la la-home", order = 1)
public class HomeView extends VerticalLayout {

    public HomeView() {

        add(new H1("Bienvenido a la aplicación DemoServiceSpring"));
        add(new Paragraph("Esta es una aplicacion web para buscar productos en la web"));

        add(new Paragraph("FarmaScrapy es una aplicación web que permite buscar productos en la web de Farmatodo. "
                + "Utiliza un servicio de Django para enviar el nombre del producto y obtener información adicional."));
        add(new Paragraph("La aplicación utiliza Spring Boot como backend y Vaadin como framework de frontend. Ademas de consumir una API django "));

        // Vista de enlace a la vista de búsqueda
        add(new Paragraph("Para buscar un producto, haga clic en el siguiente enlace:"));
        // Botón para navegar a la vista de búsqueda
        Button searchButton = new Button("Buscar producto", event -> UI.getCurrent().navigate(SearchProductFarmatodoView.class));
        add(searchButton);

    }
}
