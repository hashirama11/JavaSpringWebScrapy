package com.example.DemoServiceSpring.views;

import com.example.DemoServiceSpring.Service.FarmaService.FarmabienService;
import com.example.DemoServiceSpring.Service.FarmaService.FarmaciasasService;
import com.example.DemoServiceSpring.Service.FarmaService.FarmahorroService;
import com.example.DemoServiceSpring.Service.FarmaService.FarmatodoService;
import com.example.DemoServiceSpring.Service.FarmaService.LocatelService;
import com.example.DemoServiceSpring.views.Layout.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;



@Route(value = "search-product", layout = MainLayout.class)
@PageTitle("Buscar producto")
public class SearchProductFarmatodoView extends Composite<FormLayout> {

    // Vista para busqueda de productos farmaceuticos en Farmacias de Venezuela
    // Inyeccion de dependencias de los servicios de farmacias
    private final FarmatodoService farmatodoService;
    private final FarmahorroService farmahorroService;
    private final LocatelService locatelService;
    private final FarmaciasasService farmaciasasService;
    private final FarmabienService farmabienService;


    public SearchProductFarmatodoView(FarmatodoService farmatodoService, FarmahorroService farmahorroService, LocatelService locatelService, FarmaciasasService farmaciasasService, FarmabienService farmabienService) {
        this.farmatodoService = farmatodoService;
        this.farmahorroService = farmahorroService;
        this.locatelService = locatelService;
        this.farmaciasasService = farmaciasasService;
        this.farmabienService = farmabienService;

        // Iniciar el componente principal
        FormLayout formLayout = getContent();

        // Create a text field for product name
        TextField productNameField = new TextField("Nombre del producto");

        // Create a submit button
        Button submitButton = new Button("Enviar", event -> {
            String productName;
            productName = productNameField.getValue();
            // Enviando el nombre al servicio existencia por nombre
            enviarProductoATodasLasFarmacias(productName);

            Notification.show("Producto enviado: " + productName);
        });

        // Add components to the form layout
        formLayout.add(productNameField, submitButton);
    }
    
    // Método para enviar el nombre del producto a todas las farmacias
    private void enviarProductoATodasLasFarmacias(String productName) {
        farmatodoService.existenciaPorNombre(productName);
        farmabienService.existenciaPorNombre(productName);
        farmahorroService.existenciaPorNombre(productName);
        locatelService.existenciaPorNombre(productName);
        farmaciasasService.existenciaPorNombre(productName);
    }
    

    
}
