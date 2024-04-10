package com.icodeap.ecommerce.infrastructure.adapter;

import com.icodeap.ecommerce.application.repository.UserRepository;
import com.icodeap.ecommerce.domain.User;
import com.icodeap.ecommerce.infrastructure.mapper.UserMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserCrudRepository crud;
    private final UserMapper mapper;

    public UserRepositoryImpl(UserCrudRepository crud, UserMapper mapper) {
        this.crud = crud;
        this.mapper = mapper;
    }

    @Override
    public User createUser(User user) {
        return mapper.toUser(crud.save(mapper.toUserEntity(user)));
    }

    @Override
    public User findByEmail(String email) {
        return mapper.toUser(crud.findByEmail(email).get());
    }

    @Override
    public User findById(Integer id) {
        return mapper.toUser(crud.findById(id).get());
    }
}
