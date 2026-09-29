package br.edu.ifpb.sinan.enums;

public enum TipoNotificacaoEnum {
    NEGATIVA(1),
    INDIVIDUAL(2),
    SURTO(3),
    TRACOME(4);
    private final int codigo;

    TipoNotificacaoEnum(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }
}
