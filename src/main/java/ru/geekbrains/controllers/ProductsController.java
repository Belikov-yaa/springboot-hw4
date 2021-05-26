package ru.geekbrains.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.service.ProductsService;
import ru.geekbrains.entities.Product;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductsController {
    private ProductsService productsService;

    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/")
    public String showAllProducts(Model model) {
        List<Product> products = productsService.getProductsList();
        model.addAttribute("products", products);
        return "index";
    }

    @GetMapping("/{id}")
    public String showProductById(Model uiModel, @PathVariable(value = "id") int id) {
        Product product = productsService.getProductById(id);
        uiModel.addAttribute("product", product);
        return "product";
    }

    @RequestMapping("/showForm")
    public String showSimpleForm(Model uiModel) {
        Product product = new Product();
        uiModel.addAttribute("product", product);
        return "product-form";
    }

    @PostMapping("/processForm")
    public String processForm(@ModelAttribute("product") Product product) {
        productsService.addProductToRepository(product);
        return "product-form-result";
    }
}
