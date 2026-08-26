package ecommerce.ecommerce.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "store_settings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoreSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String companyName = "Mi Comercio";

    @Column
    private String phone = "+56900000000";

    @Column
    private String email = "contacto@micomercio.cl";

    @Column
    private String address = "Santiago, Chile";

    @Lob
    @Column
    private String footerLinks = "[{\"label\":\"Inicio\",\"href\":\"/\"},{\"label\":\"Catálogo\",\"href\":\"/catalogo\"},{\"label\":\"Contacto\",\"href\":\"/contacto\"}]";

    @Column
    private String contactMessage = "Atención personalizada para tu negocio.";
}
