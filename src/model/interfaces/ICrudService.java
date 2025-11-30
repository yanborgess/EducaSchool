package model.interfaces;

import java.util.List;

public interface ICrudService<T> {
    void cadastrar(T objeto);
    List<T> listar();
    void deletar(String identificador);
    T buscar(String identificador);
}