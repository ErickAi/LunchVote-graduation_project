package com.example.dao;

import com.example.domain.AbstractBaseEntity;
import com.example.domain.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface GenericRepository<T extends AbstractBaseEntity, ID> extends JpaRepository <T, ID>{

    @Override
    @Transactional
    <S extends T> S save(S entity);

    @Override
    @Transactional
    void delete(T entity);

    @Override
    @Transactional
    void deleteById(ID id);

    @Override
    Optional<T> findById(ID id);

    @Override
    T getOne(ID id);


    @Override
    List<T> findAll();

    @Override
    List<T> findAll(Sort sort);

    @Override
    boolean existsById(ID id);

    @Override
    <S extends T> long count(Example<S> example);

    User getByEmail(String email);
}
