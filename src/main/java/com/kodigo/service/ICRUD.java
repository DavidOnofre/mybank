package com.kodigo.service;

import java.util.List;

public interface ICRUD<T, ID> {

	T registrar(T persona) throws Exception;

	T modificar(T persona) throws Exception;

	List<T> listar() throws Exception;

	T listarPorId(ID id) throws Exception;

	void eliminar(ID id) throws Exception;
}
