package iset.master.spring.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import iset.master.spring.model.Produit;
import iset.master.spring.repository.ProduitRepository;

@RestController // Declare as a RESTful web service
@RequestMapping("/produits") // Base URL: http://localhost:8080/produits
public class ProduitRESTController {
    
    @Autowired // Dependency injection
    private ProduitRepository produitRepos;

    // Welcome message
    @GetMapping("/index")
    public String accueil() {
        return "Bienvenue au service Web REST 'produits'.....";
    }

    // Display list of products
    @GetMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public List<Produit> getAllProduits() {
        return produitRepos.findAll();
    }

    // Display a specific product by ID
    @GetMapping("/{id}")
    public Produit getProduit(@PathVariable Long id) {
        return produitRepos.findById(id).orElse(null); // Return null if not found
    }

    // Add a new product with POST
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Produit saveProduit(@RequestBody Produit p) {
        return produitRepos.save(p);
    }

    // Update an existing product with PUT
    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Produit updateProduit(@RequestBody Produit p) {
        return produitRepos.save(p);
    }

    // Delete a product by ID
    @DeleteMapping("/{id}")
    public void deleteProduit(@PathVariable Long id) {
        produitRepos.deleteById(id);
    }
    
    @JacksonXmlRootElement(localName = "Produit")
    public static class Product {
        private String designation;
        private double prix;
        private int quantite;
        private String dateAchat;
        private Categorie categorie;

        // Getters and Setters
    }

    @JacksonXmlRootElement(localName = "Categorie")
    public static class Categorie {
        private int id;
        private String code;
        private String libelle;

        // Getters and Setters
    }
}
