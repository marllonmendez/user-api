package br.com.marllonmendez.user.repository;

import br.com.marllonmendez.user.model.AddressModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
public interface IAddressRepository extends JpaRepository<AddressModel, Integer> {
    AddressModel findById(UUID id);
}
