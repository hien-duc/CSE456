package vn.edu.eiu.lab5.service;

import vn.edu.eiu.lab5.entity.Customer;
import vn.edu.eiu.lab5.entity.Invoice;
import vn.edu.eiu.lab5.entity.Product;
import vn.edu.eiu.lab5.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public Invoice createInvoice(Customer customer, List<Product> products) {
        Invoice invoice = new Invoice(customer, products);
        return invoiceRepository.save(invoice);
    }

    public void printInvoice(Invoice invoice) {
        System.out.println("=== INVOICE ===");
        System.out.println("Customer: " + invoice.getCustomer().getName());
        System.out.println("Email: " + invoice.getCustomer().getEmail());
        System.out.println("Products:");
        for (Product product : invoice.getProducts()) {
            System.out.println("- " + product.getName() + ": $" + product.getPrice());
        }
        System.out.println("Total Price: $" + invoice.getTotalPrice());
        System.out.println("===============");
    }
}
