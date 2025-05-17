package com.aktie.sale.domain.entities.enums;

import com.aktie.sale.domain.utils.EnumUtil;

/**
 *
 * @author SRamos
 */
public enum EnumErrorCode implements IEnum {

    // Internal errors
    CAMPO_OBRIGATORIO("001", "O campo {0} é obrigatório!", 400),
    CAMPO_INVALIDO("002", "{0} informado inválido!", 404),
    USUARIO_NAO_ENCONTRADO("003", "Usuário não encontrado!", 400),
    PEDIDO_NAO_ENCONTRADO("004", "Pedido não encontrado para o filtro!", 404),
    // External errors
    ERRO_COMUNICACAO("050", "A requisição enviada ao parceiro retornou com erro!", 502);

    private final String key;

    private final String erro;

    private final int httpStatus;

    private EnumErrorCode(String key, String error, int httpStatus) {
        this.key = key;
        this.erro = error;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getKey() {
        return key;
    }

    public String getErro() {
        return erro;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    @Override
    public boolean containsInEnum(String key) {
        return parseByKey(key) != null;
    }

    public static EnumErrorCode parseByKey(String key) {
        return (EnumErrorCode) EnumUtil.parseByKey(EnumErrorCode.class, key);
    }

}
