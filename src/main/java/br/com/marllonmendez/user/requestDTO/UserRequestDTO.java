package br.com.marllonmendez.user.requestDTO;

import br.com.marllonmendez.user.model.AddressModel;

import java.util.List;
import java.util.UUID;

public record UserRequestDTO(UUID id, String name, String birthday, List<AddressModel> addresses) {}
