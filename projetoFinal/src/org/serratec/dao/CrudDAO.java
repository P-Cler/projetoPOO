package org.serratec.dao;

import java.sql.SQLException;

public interface CrudDAO<T> {

	void inserir(T entidade) throws SQLException;

	void atualizar(T entidade) throws SQLException;

	void excluir(Integer id) throws SQLException;

	void getAll() throws SQLException;

	T getById(Integer id) throws SQLException;

}
