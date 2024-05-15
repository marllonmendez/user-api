package br.com.marllonmendez.user.controller;

import br.com.marllonmendez.user.model.AddressModel;
import br.com.marllonmendez.user.service.AddressService;
import br.com.marllonmendez.user.repository.IAddressRepository;
import br.com.marllonmendez.user.repository.IUserRepository;
import br.com.marllonmendez.user.responseDTO.AddressResponseDTO;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private IAddressRepository iAddressRepository;

    @Autowired
    private IUserRepository iUserRepository;

    @Autowired
    private AddressService addressService;

    @GetMapping("/list")
    public List<AddressResponseDTO> getAll() {
        return iAddressRepository.findAll().stream().map(AddressResponseDTO::new).toList();
    }

    @GetMapping("/search/{id}")
    public ResponseEntity addressSearch(@PathVariable UUID id) {
        var address = this.iAddressRepository.findById(id);
        if (address == null || !address.getId().equals(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Endereço não encontrado!");
        }

        return ResponseEntity.ok(address);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity addressUpdate(@RequestParam String zipcode, @RequestParam String number, @PathVariable UUID id) {
        var address = this.iAddressRepository.findById(id);
        if (address == null || !address.getId().equals(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Endereço não encontrado no banco de dados!");
        }

        AddressModel newAddress = addressService.getAddressByZipCode(zipcode);
        if (newAddress == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Endereço fornecido não encontrado!");
        }
        address.setZipcode(newAddress.getZipcode());
        address.setStreet(newAddress.getStreet());
        address.setNeighborhood(newAddress.getNeighborhood());
        address.setCity(newAddress.getCity());
        address.setState(newAddress.getState());
        address.setNumber(number);

        AddressModel saveNewAddreess = iAddressRepository.save(address);
        return ResponseEntity.ok(this.iAddressRepository.save(saveNewAddreess));
    }

    @PostMapping("/addAddress/{id}")
    public ResponseEntity addAddress(@RequestParam String zipcode, @RequestParam String number, @RequestParam String typeAddress, @PathVariable UUID id) {
        var user = this.iUserRepository.findById(id);
        if (user == null || !user.getId().equals(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não encontrado no banco de dados!");
        }

        AddressModel newAddress = addressService.getAddressByZipCode(zipcode);
        if (newAddress == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Endereço fornecido não encontrado!");
        }

        AddressModel addressData = new AddressModel();
        addressData.setZipcode(newAddress.getZipcode());
        addressData.setStreet(newAddress.getStreet());
        addressData.setNeighborhood(newAddress.getNeighborhood());
        addressData.setCity(newAddress.getCity());
        addressData.setState(newAddress.getState());
        addressData.setTypeAddress(typeAddress);
        addressData.setNumber(number);
        addressData.setIdUser(user.getId());

        user.getAddresses().add(addressData);

        var addAddressToUser = this.iUserRepository.save(user);
        return ResponseEntity.ok(addAddressToUser);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteAddress(@PathVariable UUID id) {
        var address = this.iAddressRepository.findById(id);
        if (address == null || !address.getId().equals(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereço não encontrado!");
        }

        this.iAddressRepository.delete(address);

        return ResponseEntity.ok("Endereço removido com sucesso!");
    }

}


