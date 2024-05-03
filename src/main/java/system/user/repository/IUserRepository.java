package system.user.repository;

import system.user.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IUserRepository extends JpaRepository<UserModel, Integer> {
    UserModel findById(UUID id);
}
