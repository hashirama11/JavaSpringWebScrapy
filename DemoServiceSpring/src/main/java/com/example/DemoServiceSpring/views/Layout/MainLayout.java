package com.example.DemoServiceSpring.views.Layout;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;

public class MainLayout extends AppLayout {
    public MainLayout() {
        // Barra de navegación superior
        H1 title = new H1("FarmaScraper");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");
        addToNavbar(title);

        // Menú lateral con navegación
        SideNav nav = new SideNav();
        Scroller scroller = new Scroller(nav);
        scroller.setClassName("padding-small");
        addToDrawer(scroller);
    }
}
