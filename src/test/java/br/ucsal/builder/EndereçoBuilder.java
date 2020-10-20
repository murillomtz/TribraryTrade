package br.ucsal.builder;

import br.ucsal.model.Endereco;

public class EndereçoBuilder {

    private Endereco endereco;

    private EndereçoBuilder() {
    }

    public static EndereçoBuilder umEndereco() {
        EndereçoBuilder builder = new EndereçoBuilder();

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
