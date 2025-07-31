package vn.edu.eiu.lab5;

import vn.edu.eiu.lab5.config.AppConfig;
import vn.edu.eiu.lab5.entity.Customer;
import vn.edu.eiu.lab5.entity.Invoice;
import vn.edu.eiu.lab5.entity.Product;
import vn.edu.eiu.lab5.service.CustomerService;
import vn.edu.eiu.lab5.service.InvoiceService;
import vn.edu.eiu.lab5.service.ProductService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

            CustomerService customerService = context.getBean(CustomerService.class);
            ProductService productService = context.getBean(ProductService.class);
            InvoiceService invoiceService = context.getBean(InvoiceService.class);

            System.out.println("=".repeat(60));
            System.out.println("    ELECTRONIC SALES MANAGEMENT SYSTEM DEMO");
            System.out.println("=".repeat(60));

            // Demo 1: Create multiple customers
            System.out.println("\n1. CREATING CUSTOMERS");
            System.out.println("-".repeat(30));
            Customer customer1 = customerService.createCustomer("John Doe", "john.doe@email.com");
            Customer customer2 = customerService.createCustomer("Jane Smith", "jane.smith@email.com");
            Customer customer3 = customerService.createCustomer("Bob Johnson", "bob.johnson@email.com");

            System.out.println("✓ Customer 1: " + customer1.getName() + " (" + customer1.getEmail() + ")");
            System.out.println("✓ Customer 2: " + customer2.getName() + " (" + customer2.getEmail() + ")");
            System.out.println("✓ Customer 3: " + customer3.getName() + " (" + customer3.getEmail() + ")");

            // Demo 2: Create and display products
            System.out.println("\n2. CREATING PRODUCT CATALOG");
            System.out.println("-".repeat(30));
            List<Product> allProducts = productService.createHardcodedProducts();
            System.out.println("✓ Created " + allProducts.size() + " products:");

            for (Product product : allProducts) {
                System.out.printf("  - %-15s: $%.2f%n", product.getName(), product.getPrice());
            }

            // Demo 3: Display all available products
            System.out.println("\n3. PRODUCT INVENTORY");
            System.out.println("-".repeat(30));
            List<Product> products = productService.getAllProducts();
            System.out.printf("%-5s %-15s %-10s%n", "ID", "Product Name", "Price");
            System.out.println("-".repeat(30));
            for (Product product : products) {
                System.out.printf("%-5d %-15s $%-9.2f%n",
                    product.getId(), product.getName(), product.getPrice());
            }

            // Demo 4: Create invoice with all products for customer 1
            System.out.println("\n4. GENERATING INVOICE #1 (All Products)");
            System.out.println("-".repeat(45));
            Invoice invoice1 = invoiceService.createInvoice(customer1, products);
            System.out.println("✓ Invoice created for: " + customer1.getName());
            invoiceService.printInvoice(invoice1);

            // Demo 5: Create invoice with selected products for customer 2
            System.out.println("\n5. GENERATING INVOICE #2 (Selected Products)");
            System.out.println("-".repeat(45));
            List<Product> selectedProducts = products.subList(0, 2); // First 2 products
            Invoice invoice2 = invoiceService.createInvoice(customer2, selectedProducts);
            System.out.println("✓ Invoice created for: " + customer2.getName());
            invoiceService.printInvoice(invoice2);

            // Demo 6: Create invoice with single product for customer 3
            System.out.println("\n6. GENERATING INVOICE #3 (Single Product)");
            System.out.println("-".repeat(45));
            List<Product> singleProduct = products.subList(2, 3); // Third product only
            Invoice invoice3 = invoiceService.createInvoice(customer3, singleProduct);
            System.out.println("✓ Invoice created for: " + customer3.getName());
            invoiceService.printInvoice(invoice3);

            // Demo 7: Summary
            System.out.println("\n7. SYSTEM SUMMARY");
            System.out.println("-".repeat(30));
            System.out.println("✓ Total Customers Created: 3");
            System.out.println("✓ Total Products Available: " + products.size());
            System.out.println("✓ Total Invoices Generated: 3");
            System.out.println("✓ Invoice #1 Total: $" + invoice1.getTotalPrice());
            System.out.println("✓ Invoice #2 Total: $" + invoice2.getTotalPrice());
            System.out.println("✓ Invoice #3 Total: $" + invoice3.getTotalPrice());

            System.out.println("\n" + "=".repeat(60));
            System.out.println("    DEMO COMPLETED SUCCESSFULLY!");
            System.out.println("    All invoices have been printed (simulating PDF export)");
            System.out.println("=".repeat(60));

        } catch (Exception e) {
            System.err.println("Application error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
