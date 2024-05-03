package system.user.responseDTO;

import system.user.model.AddressModel;

import java.util.UUID;

public record AddressResponseDTO(UUID id, String zipcode, String street, String city, String neighborhood, String state, String number, String typeAddress, UUID idUser) {
    public AddressResponseDTO(AddressModel addressModel) {
        this(
                addressModel.getId(),
                addressModel.getZipcode(),
                addressModel.getStreet(),
                addressModel.getNeighborhood(),
                addressModel.getCity(),
                addressModel.getState(),
                addressModel.getNumber(),
                addressModel.getTypeAddress(),
                addressModel.getIdUser()
        );
    }
}
