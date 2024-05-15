package br.com.marllonmendez.user.responseDTO;

import br.com.marllonmendez.user.model.AddressModel;
import br.com.marllonmendez.user.model.UserModel;

import java.util.List;
import java.util.UUID;

public record UserResponseDTO(UUID id, String name, String birthday, List<AddressModel> addresses) {
    public UserResponseDTO(UserModel userModel) {
        this(
            userModel.getId(),
            userModel.getName(),
            userModel.getBirthday(),
            userModel.getAddresses()
        );
    }

}
