package com.acme.wrapper.infra.security;

/**
 *
 * @author jose.diegues
 */
public enum Roles {

    ADMIN(1, "ROLE_ADMIN"),
    CLIENTE(2, "ROLE_CLIENTE");

    private int cod;
    private String desc;

    private Roles(int cod, String desc) {
        this.cod = cod;
        this.desc = desc;
    }

    public int getCod() {
        return this.cod;
    }

    public String getDescricao() {
        return this.desc;
    }

    public static Roles toEnum(Integer cod) {

        if (cod == null) {
            return null;
        }

        for (Roles x : Roles.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Invalid id: " + cod);
    }

}
