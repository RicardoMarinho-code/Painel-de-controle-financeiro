package com.meuprojeto.dao;

import com.meuprojeto.modelo.Ativo;
import java.util.List;

/**
 * Interface para operações de acesso a dados para a entidade Ativo.
 */
public interface AtivoDAO {
    void adicionar(Ativo ativo);
    Ativo buscarPorId(String id);
    List<Ativo> listarPorUsuario(String idUsuario);
    void atualizar(Ativo ativo);
    void deletar(String id);
}