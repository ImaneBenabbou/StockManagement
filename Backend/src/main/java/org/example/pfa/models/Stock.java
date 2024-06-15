package org.example.pfa.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn( referencedColumnName = "idEntrepot")
    private Entrepot entrepot;
    private Long capacite;
    private String type;
    private String ville;
}

