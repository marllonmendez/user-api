package br.com.marllonmendez.user.repository;

import br.com.marllonmendez.user.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IUserRepository extends JpaRepository<UserModel, Integer> {
    UserModel findById(UUID id);
}
