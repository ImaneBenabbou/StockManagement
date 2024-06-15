package org.example.pfa.Controllers;

import org.example.pfa.Services.RespoStockService;
import org.example.pfa.models.Entrepot;
import org.example.pfa.models.Seuil_produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/respoStock")
public class RespoStockController {

    @Autowired
    private RespoStockService respoStockService;

    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        List<Seuil_produit> stockStatus=respoStockService.getStockStatus();
        return ResponseEntity.ok(stockStatus.toString());
    }

    @PutMapping("/update-quantity/{idEntrepot}")
    public ResponseEntity<Entrepot> updateProductQuantity(@PathVariable Long idEntrepot, @RequestParam long newQuantity){
        Entrepot updatedEntrepot= respoStockService.updateProductQuantity(idEntrepot,newQuantity);
        return ResponseEntity.ok(updatedEntrepot);

    }
}
