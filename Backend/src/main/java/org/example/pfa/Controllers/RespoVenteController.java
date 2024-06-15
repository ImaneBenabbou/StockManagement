package org.example.pfa.Controllers;



import org.example.pfa.Repository.CommandeRepo;
import org.example.pfa.Services.RespoVenteService;
import org.example.pfa.models.Commande;
import org.example.pfa.models.Seuil_produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/respovente")
public class RespoVenteController {

    @Autowired
    private RespoVenteService respoVenteService;
    @Autowired
    private CommandeRepo commandeRepo;

    /*
    @PutMapping("/valider-commande/{idCommande}")
    public ResponseEntity<Commande> validerCommande(@PathVariable Long idCommande) {
        Commande commendeValidée=respoVenteService.validerCommande(idCommande);
        return ResponseEntity.ok(commandeValidée);
    }

     */
    @GetMapping("/niveaux-stock")
    public ResponseEntity<List<Seuil_produit>> consulterNiveauStock(){
       List<Seuil_produit> niveauxStock=respoVenteService.consulterNiveauxStock();
       return ResponseEntity.ok(niveauxStock);
    }
    /*@GetMapping("/commandes/date")
    public ResponseEntity<List<Commande>> getCommandesByDate(@RequestParam Date date) {
        List<Commande> commandes = respoVenteService.getCommandesByDate(date);
        return ResponseEntity.ok(commandes);
    }
    */
    @GetMapping("/commandes/date")
    public ResponseEntity<List<Commande>> getCommandesByDate(@RequestParam String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parsedDate = dateFormat.parse(date);
            List<Commande> commandes = respoVenteService.getCommandesByDate(parsedDate);
            return ResponseEntity.ok(commandes);
        } catch (ParseException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    /*
    @PutMapping("/cloturer-commande/{idCommande}")
    public ResponseEntity<Commande> cloturerCommande(@PathVariable int idCommande){
        Commande commandeCloturee=respoVenteService.cloturerCommande(idCommande);
        return ResponseEntity.ok(commandeCloturee);
    }
     */

    @PutMapping("/updatestatut-commande")
    public ResponseEntity<Commande> updateStatutCommande(@PathVariable Long idCommande){
        Commande commandeprogress= respoVenteService.updateCommande(idCommande);
        return ResponseEntity.ok(commandeprogress);

    }
}
