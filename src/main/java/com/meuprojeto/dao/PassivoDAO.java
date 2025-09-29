package com.meuprojeto.dao;

import com.meuprojeto.modelo.Passivo;
import java.util.List;

/**
 * Interface para operações de acesso a dados para a entidade Passivo.
 */
public interface PassivoDAO {
    void adicionar(Passivo passivo);
    Passivo buscarPorId(String id);
    List<Passivo> listarPorUsuario(String idUsuario);
    void atualizar(Passivo passivo);
    void deletar(String id);
}