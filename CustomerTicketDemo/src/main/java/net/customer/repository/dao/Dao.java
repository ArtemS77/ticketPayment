package net.customer.repository.dao;

public interface Dao {

    <T>T getById(Long id);

    <T> void save(T t);

    <T> void update(T t);
}
