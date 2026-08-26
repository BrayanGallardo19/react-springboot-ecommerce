package ecommerce.ecommerce.data;

import ecommerce.ecommerce.entity.Category;
import ecommerce.ecommerce.entity.Permission;
import ecommerce.ecommerce.entity.Product;
import ecommerce.ecommerce.entity.Promotion;
import ecommerce.ecommerce.entity.StoreSetting;
import ecommerce.ecommerce.entity.User;
import ecommerce.ecommerce.enums.PermissionName;
import ecommerce.ecommerce.enums.UserRole;
import ecommerce.ecommerce.repository.CategoryRepository;
import ecommerce.ecommerce.repository.PermissionRepository;
import ecommerce.ecommerce.repository.ProductRepository;
import ecommerce.ecommerce.repository.PromotionRepository;
import ecommerce.ecommerce.repository.StoreSettingRepository;
import ecommerce.ecommerce.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.HashSet;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final PermissionRepository permissionRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final PromotionRepository promotionRepository;
    private final StoreSettingRepository storeSettingRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        createPermissions();
        createPromotions();
        createStoreSettings();
        createAdminUser();
        createSampleCategories();
        createSampleProducts();
    }

    private void createPermissions() {
        if (permissionRepository.count() > 0) {
            return;
        }

        for (PermissionName name : PermissionName.values()) {
            Permission permission = new Permission();
            permission.setName(name);
            permission.setDescription(switch (name) {
                case MANAGE_PRODUCTS -> "Gestión del catálogo de productos";
                case MANAGE_USERS -> "Gestión de usuarios y permisos";
                case MANAGE_DISCOUNTS -> "Gestión de descuentos y promociones";
                case MANAGE_ORDERS -> "Gestión de pedidos";
                case VIEW_ORDERS -> "Consulta de pedidos";
                case VIEW_SELF_ORDERS -> "Consulta de pedidos propios";
                case MANAGE_STORE_SETTINGS -> "Configuración de la tienda";
                case MANAGE_CUSTOMER_SUPPORT -> "Soporte al cliente";
            });
            permissionRepository.save(permission);
        }
    }

    private void createPromotions() {
        if (promotionRepository.count() > 0) {
            return;
        }

        Promotion launch = new Promotion();
        launch.setName("Lanzamiento");
        launch.setCode("BIENVENIDO10");
        launch.setDiscountPercent(10.0);
        launch.setActive(true);
        launch.setStartsAt(LocalDateTime.now().minusDays(1));
        launch.setEndsAt(LocalDateTime.now().plusMonths(2));
        promotionRepository.save(launch);
    }

    private void createAdminUser() {
        if (userRepository.findByEmail("admin@ecommerce.cl").isPresent()) {
            return;
        }

        User admin = new User();
        admin.setFirstName("Admin");
        admin.setLastName("Principal");
        admin.setEmail("admin@ecommerce.cl");
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setRole(UserRole.ADMIN);
        admin.setEnabled(true);
        admin.setPermissions(new HashSet<>(permissionRepository.findAll()));
        userRepository.save(admin);
    }

    private void createSampleCategories() {
        if (categoryRepository.count() > 0) {
            return;
        }

        Category tecnologia = new Category();
        tecnologia.setName("Tecnología");
        tecnologia.setSlug("tecnologia");
        categoryRepository.save(tecnologia);

        Category hogar = new Category();
        hogar.setName("Hogar");
        hogar.setSlug("hogar");
        categoryRepository.save(hogar);

        Category moda = new Category();
        moda.setName("Moda");
        moda.setSlug("moda");
        categoryRepository.save(moda);
    }

    private void createSampleProducts() {
        if (productRepository.count() > 0) {
            return;
        }

        Category tecnologia = categoryRepository.findBySlug("tecnologia").orElseThrow();
        Category hogar = categoryRepository.findBySlug("hogar").orElseThrow();
        Category moda = categoryRepository.findBySlug("moda").orElseThrow();

        Product laptop = new Product();
        laptop.setName("Laptop Pro 14");
        laptop.setDescription("Notebook premium para trabajo y diseño.");
        laptop.setPrice(1299000.0);
        laptop.setStock(12);
        laptop.setActive(true);
        laptop.setFeatured(true);
        laptop.setImageUrl("https://images.unsplash.com/..." );
        laptop.setDiscountPercent(12.0);
        laptop.setCategory(tecnologia);
        productRepository.save(laptop);

        Product sofa = new Product();
        sofa.setName("Sofá Modular");
        sofa.setDescription("Solución moderna y cómoda para salas pequeñas.");
        sofa.setPrice(699000.0);
        sofa.setStock(7);
        sofa.setActive(true);
        sofa.setFeatured(true);
        sofa.setImageUrl("https://images.unsplash.com/..." );
        sofa.setDiscountPercent(8.0);
        sofa.setCategory(hogar);
        productRepository.save(sofa);

        Product mochila = new Product();
        mochila.setName("Mochila Urban");
        mochila.setDescription("Mochila funcional para trabajo diario.");
        mochila.setPrice(149990.0);
        mochila.setStock(20);
        mochila.setActive(true);
        mochila.setFeatured(false);
        mochila.setImageUrl("https://images.unsplash.com/..." );
        mochila.setDiscountPercent(5.0);
        mochila.setCategory(moda);
        productRepository.save(mochila);
    }

    private void createStoreSettings() {
        if (storeSettingRepository.count() > 0) {
            return;
        }

        StoreSetting setting = new StoreSetting();
        setting.setCompanyName("Ecommerce Chile");
        setting.setPhone("+56912345678");
        setting.setEmail("contacto@ecommercechile.cl");
        setting.setAddress("Santiago, Chile");
        setting.setContactMessage("Estamos para ayudarte con tus pedidos y soporte.");
        setting.setFooterLinks("[{\"label\":\"Inicio\",\"href\":\"/\"},{\"label\":\"Catálogo\",\"href\":\"/catalogo\"},{\"label\":\"Contacto\",\"href\":\"/contacto\"}]");
        storeSettingRepository.save(setting);
    }
}
