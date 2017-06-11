package pl.naniewicz.boilerplate.domain.usecase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import pl.naniewicz.boilerplate.data.model.User;
import pl.naniewicz.boilerplate.data.repository.UsersRepository;

public class GetUsersUseCase {

    private UsersRepository userRepository;

    @Inject
    public GetUsersUseCase(UsersRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Single<List<User>> execute() {
        return userRepository.loadUsers().toList();
    }
}
