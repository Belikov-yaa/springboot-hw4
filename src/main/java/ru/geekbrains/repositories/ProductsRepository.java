package ru.geekbrains.repositories;


import org.springframework.stereotype.Component;
import ru.geekbrains.entities.Product;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductsRepository {

    private final List<Product> products = new ArrayList<>();
    private int count = 0;

    //    @Autowired
    public ProductsRepository() {
        products.add(new Product(++count, "Potato", 3.40));
        products.add(new Product(++count, "Tomato", 5.60));
        products.add(new Product(++count, "Milk", 2.15));
        products.add(new Product(++count, "Bread", 1.60));
    }

    public List<Product> getProducts() {
        return products;
    }

    public Product getProductByID(int id) {
        Product result = null;
        for (Product product : products) {
            if (product.getId() == id) {
                result = product;
                break;
            }
        }
        return result;
    }

    public void addProductToList(Product product) {
        if (product.getId() == 0) {
            product.setId(++count);
            products.add(product);
        } else {
            for (Product productInList : products) {
                if(product.getId() == productInList.getId()) {
                    productInList.setTitle(product.getTitle());
                    productInList.setCost(product.getCost());
                }
            }
        }
    }
}
