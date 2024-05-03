package system.user.controller;

import system.user.model.UserModel;
import system.user.model.AddressModel;
import system.user.repository.IUserRepository;
import system.user.repository.IAddressRepository;
import system.user.requestDTO.UserRequestDTO;
import system.user.responseDTO.UserResponseDTO;
import system.user.service.AddressService;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserRepository iUserRepository;

    @Autowired
    private IAddressRepository iAddressRepository;

    @Autowired
    private AddressService addressService;

    @PostMapping("/create")
    public ResponseEntity userCreate(@RequestBody UserRequestDTO data, @RequestParam String zipcode, @RequestParam String number, @RequestParam String typeAddress) {
        UserModel userData = new UserModel(data);
        UserModel saveUser = iUserRepository.save(userData);
        if (saveUser.getId() == null) {
            return ResponseEntity.badRequest().body("Falha ao criar o usuário");
        }

        AddressModel address = addressService.getAddressByZipCode(zipcode);
        if (address == null) {
            return ResponseEntity.badRequest().body("Endereço fornecido não encontrado");
        }

        address.setNumber(number);
        address.setTypeAddress(typeAddress);
        address.setIdUser(saveUser.getId());

        AddressModel saveAddress = iAddressRepository.save(address);
        List<AddressModel> arrayList = new ArrayList<>();
        arrayList.add(saveAddress);
        saveUser.setAddresses(arrayList);

        return ResponseEntity.ok(iUserRepository.save(saveUser));
    }


    @RequestMapping("/list")
    public List<UserResponseDTO> getAll() {
        return iUserRepository.findAll().stream().map(UserResponseDTO::new).toList();
    }

    @RequestMapping("/search/{id}")
    public ResponseEntity userSearch(@PathVariable UUID id) {
        var user = this.iUserRepository.findById(id);
        if (user == null || !user.getId().equals(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não encontrado!");
        }

        return ResponseEntity.ok(user);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity userUpdate(@RequestBody UserRequestDTO data, @PathVariable UUID id) {
        var user = this.iUserRepository.findById(id);
        if (user == null || !user.getId().equals(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não encontrado!");
        }

        user.setName(data.name());
        user.setBirthday(data.birthday());

        var userUpdate = this.iUserRepository.save(user);
        return ResponseEntity.ok(userUpdate);
    }
}
