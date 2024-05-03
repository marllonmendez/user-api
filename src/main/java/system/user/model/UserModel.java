package system.user.model;

import system.user.requestDTO.UserRequestDTO;
import lombok.*;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "tb_users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String birthday;

    @OneToMany(mappedBy = "idUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AddressModel> addresses;

    public UserModel(UserRequestDTO data) {
        this.name = data.name();
        this.birthday = data.birthday();
        this.addresses = data.addresses();
    }
}
