package com.github.wesleyLeocadio.pagamentos_simplificados.domain.user;

public enum UserType {
    COMMON("Comum"),
    MERCHANT("Logista");

    private final String descricao;

    UserType(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static UserType fromDescricao(String descricao) {
        for (UserType type : UserType.values()) {
            if (type.getDescricao().equalsIgnoreCase(descricao)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Tipo de usuário inválido: " + descricao);
    }
}
