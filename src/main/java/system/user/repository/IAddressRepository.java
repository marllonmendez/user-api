package system.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import system.user.model.AddressModel;

import java.util.UUID;
public interface IAddressRepository extends JpaRepository<AddressModel, Integer> {
    AddressModel findById(UUID id);
}
