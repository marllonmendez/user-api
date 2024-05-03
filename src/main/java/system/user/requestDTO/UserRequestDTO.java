package system.user.requestDTO;

import system.user.model.AddressModel;

import java.util.List;
import java.util.UUID;

public record UserRequestDTO(UUID id, String name, String birthday, List<AddressModel> addresses) {}
