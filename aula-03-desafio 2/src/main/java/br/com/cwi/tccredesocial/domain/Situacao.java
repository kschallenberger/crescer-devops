package br.com.cwi.tccredesocial.domain;

public enum Situacao {

    SOLICITADO, PENDENTE, ACEITO;

    @Override
    public String toString() {
        return "Situacao{} " + super.toString();
    }
}
