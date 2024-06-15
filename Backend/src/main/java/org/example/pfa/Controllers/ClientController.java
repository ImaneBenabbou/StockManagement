package org.example.pfa.Controllers;

import org.example.pfa.Services.ClientService;
import org.example.pfa.models.Commande;
import org.example.pfa.models.Produit;
import org.example.pfa.models.Utilisateur;
import org.example.pfa.payload.request.CommandeRequest;
import org.example.pfa.payload.response.UserInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;


    @GetMapping("/products")
    public ResponseEntity<List<Produit>> getAllProducts() {
        List<Produit> products = clientService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/product/{idProduit}")
    public ResponseEntity<Optional<Produit>> getProduct(@PathVariable Long idProduit) {
        Optional<Produit> product = clientService.getProduct(idProduit);
        return ResponseEntity.ok(product);
    }

    @PostMapping("/order")
    public ResponseEntity<Commande> createOrder(@RequestBody CommandeRequest commandeRequest, @RequestParam Long clientId) {
        Optional<Utilisateur> clientOpt = clientService.findById(clientId);
        if (clientOpt.isPresent()) {
            Utilisateur client = clientOpt.get();
            Commande newOrder = clientService.createOrder(commandeRequest, client);
            return ResponseEntity.ok(newOrder);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/order/{idCommande}")
    public ResponseEntity<Commande> updateOrder(@PathVariable Long idCommande, @RequestBody CommandeRequest commandeRequest) {
        Commande updatedOrder = clientService.updateOrder(idCommande, commandeRequest);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/order/{idCommande}")
    public ResponseEntity<String> cancelOrder(@PathVariable Long idCommande) {
        clientService.cancelOrder(idCommande);
        return ResponseEntity.ok("Commande annulée avec succès");
    }

    @PutMapping("/profile/{userId}")
    public ResponseEntity<Utilisateur> updateProfile(@PathVariable Long userId, @RequestBody UserInfoResponse userInfoResponse) {
        Utilisateur updatedUser = clientService.updateProfile(userId, userInfoResponse);
        return ResponseEntity.ok(updatedUser);
    }



}
