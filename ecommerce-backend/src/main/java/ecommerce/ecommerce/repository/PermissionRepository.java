package ecommerce.ecommerce.repository;

import ecommerce.ecommerce.entity.Permission;
import ecommerce.ecommerce.enums.PermissionName;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Optional<Permission> findByName(PermissionName name);
}
