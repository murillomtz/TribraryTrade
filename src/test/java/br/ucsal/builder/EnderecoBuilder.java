package br.ucsal.builder;

import br.ucsal.model.Endereco;

public class EnderecoBuilder {

    private Endereco endereco;

    private EnderecoBuilder() {
    }

    public static EnderecoBuilder umEndereco() {
        EnderecoBuilder builder = new EnderecoBuilder();

        builder.endereco = new Endereco();
        builder.endereco.setIdEndereco(null);
        builder.endereco.setCep("46589-000");
        builder.endereco.setCidade("Cidade Builder");
        builder.endereco.setBairro("Bairro builder");
        builder.endereco.setRua("Rua builder");
        builder.endereco.setNumero("291d");

        return builder;
    }

    public Endereco agora() {
        return endereco;
    }


}
