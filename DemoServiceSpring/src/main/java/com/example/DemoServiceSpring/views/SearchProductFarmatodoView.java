package com.example.DemoServiceSpring.views;

import com.example.DemoServiceSpring.Service.FarmatodoService;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;



@Route("search-product")
@PageTitle("Buscar producto")
public class SearchProductFarmatodoView extends Composite<FormLayout> {

    public SearchProductFarmatodoView(FarmatodoService farmatodoService) {
        // Iniciar el componente principal
        FormLayout formLayout = getContent();

        // Create a text field for product name
        TextField productNameField = new TextField("Nombre del producto");

        // Create a submit button
        Button submitButton = new Button("Enviar", event -> {
            String productName;
            productName = productNameField.getValue();
            // Enviando el nombre al servicio existencia por nombre
            farmatodoService.existenciaPorNombre(productName);
            Notification.show("Producto enviado: " + productName);
        });

        // Add components to the form layout
        formLayout.add(productNameField, submitButton);
    }

    
}
