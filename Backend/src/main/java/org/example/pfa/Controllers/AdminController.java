package org.example.pfa.Controllers;

import org.example.pfa.models.Seuil_produit;
import org.example.pfa.models.Produit;
import org.example.pfa.Repository.ProduitRepo;
import org.example.pfa.Services.AdminService;
import org.example.pfa.models.Utilisateur;
import org.example.pfa.payload.request.ProduitRequest;
import org.example.pfa.payload.response.UserInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private ProduitRepo produitRepo;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/create-respovente")
    public ResponseEntity<String> createRespovente(@RequestBody UserInfoResponse userInfoResponse) {
        try {
            Utilisateur responsableVente = new Utilisateur();
            responsableVente.setNom(userInfoResponse.getNom());
            responsableVente.setPhone(userInfoResponse.getPhone());
            responsableVente.setEmail(userInfoResponse.getEmail());
            responsableVente.setRole("RESPONSABLE_VENTE");
            responsableVente.setPassword(passwordEncoder.encode(userInfoResponse.getPassword()));
            adminService.createResponsableVente(responsableVente);

            return ResponseEntity.status(HttpStatus.CREATED).body("Responsable Vente created successfully");
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create Responsable Vente");
        }
    }

    @PostMapping("/create-respostock")
    public ResponseEntity<String> createRespostock(@RequestBody UserInfoResponse userInfoResponse) {
        try {
            Utilisateur responsableStock = new Utilisateur();
            responsableStock.setNom(userInfoResponse.getNom());
            responsableStock.setPhone(userInfoResponse.getPhone());
            responsableStock.setEmail(userInfoResponse.getEmail());
            responsableStock.setRole("RESPONSABLE_STOCK");
            responsableStock.setPassword(passwordEncoder.encode(userInfoResponse.getPassword()));
            adminService.createResponsableStock(responsableStock);

            return ResponseEntity.status(HttpStatus.CREATED).body("Responsable Stock created successfully");
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create Responsable Stock");
        }


    }

    @PostMapping("/update-account-respovente")
    public Utilisateur updateAccountRespoVente(@RequestBody UserInfoResponse userInfoResponse) {
        Utilisateur respoVente = new Utilisateur();
        respoVente.setNom(userInfoResponse.getNom());
        respoVente.setPhone(userInfoResponse.getPhone());
        respoVente.setEmail(userInfoResponse.getEmail());
        respoVente.setRole("RESPONSABLE_VENTE");
        respoVente.setPassword(passwordEncoder.encode(userInfoResponse.getPassword()));
        return adminService.updateAccount(respoVente);
    }

    @PostMapping("/update-account-respostock")
    public Utilisateur updateAccountRespoStock(@RequestBody UserInfoResponse userInfoResponse) {
        Utilisateur respoStock = new Utilisateur();
        respoStock.setNom(userInfoResponse.getNom());
        respoStock.setPhone(userInfoResponse.getPhone());
        respoStock.setEmail(userInfoResponse.getEmail());
        respoStock.setRole("RESPONSABLE_STOCK");
        respoStock.setPassword(passwordEncoder.encode(userInfoResponse.getPassword()));
        return adminService.updateAccount(respoStock);
    }


    @GetMapping("/clients")
    public List<Utilisateur> getAllClients() {
        return adminService.getAllClients();
    }

    @GetMapping("/responsable-vente")
    public List<Utilisateur> getResponsableVente() {
        return adminService.getResponsableVente();
    }

    @GetMapping("/responsable-stock")
    public List<Utilisateur> getResponsableStock() {
        return adminService.getResponsableStock();
    }

    @GetMapping("/responsables")
    public List<Utilisateur> getResponsables() {
        List<Utilisateur> responsables = new ArrayList<>();
        responsables.addAll(adminService.getResponsableVente());
        responsables.addAll(adminService.getResponsableStock());
        return responsables;
    }

   /* @GetMapping("/niveaux-stock")
    public ResponseEntity<List<Seuil_produit>> consulterNiveauStock(){
        List<Seuil_produit> niveauxStock=adminService.consulterNiveauxStock();
        return ResponseEntity.ok(niveauxStock);
    }*/

    @GetMapping("/niveaux-stock/{warehouseId}")
    public ResponseEntity<List<Seuil_produit>> getProductsByWarehouseId(@PathVariable Long warehouseId) {
        List<Seuil_produit> products = adminService.getProductsByWarehouseId(warehouseId);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/products")
    public List<Produit> getAllProduits() {
        return adminService.getAllProduits();
    }

    @PostMapping("/update-product")
    public Produit updateProduct(long idProduit, ProduitRequest produitRequest) {
        Optional<Produit> produit= produitRepo.findById(idProduit);
        Produit pdt = produit.get();
        pdt.setNom(produitRequest.getNom());
        pdt.setCategorie(produitRequest.getCategorie());
        pdt.setPrix_Unitaire(produitRequest.getPrixUnitaire());
        pdt.setFournisseur(produitRequest.getFournisseur());
        return adminService.updateProduit(pdt);

    }
    @PostMapping("/add-product")
    public ResponseEntity<Produit> addProduct(@RequestBody ProduitRequest produitRequest) {
        Produit produit = new Produit();
        produit.setNom(produitRequest.getNom());
        produit.setCategorie(produitRequest.getCategorie());
        produit.setPrix_Unitaire(produitRequest.getPrixUnitaire());
        produit.setFournisseur(produitRequest.getFournisseur());

        Produit savedProduit = adminService.addProduit(
                produit,
                produitRequest.getQuantite(),
                produitRequest.getQuantiteMax(),
                produitRequest.getQuantiteMin(),
                produitRequest.getEntrepotNom()
        );

        return ResponseEntity.ok(savedProduit);
    }

    @DeleteMapping("/delete-product/{idProduit}")
    public void deleteProduct(@PathVariable Long idProduit) {
        adminService.deleteProduit(idProduit);
    }


    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        try {
            adminService.deleteUserById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to delete user");
        }
    }


}