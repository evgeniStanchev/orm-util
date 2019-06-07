package com.egtinteractive.orm;

import java.util.List;

public interface ORM {
    <E> List<E> findAll(final Class<E> theClass);
    <E> Object find(final Object primaryKey, final Class<E> theClass);
}