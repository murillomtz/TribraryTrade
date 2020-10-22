package br.ucsal.builder;

import br.ucsal.model.Endereco;

public class EnderecoBuilderModeNeiva {


    private static final Integer ID_DEFAULT = null;
    private static final String CEP_DEFAULT = "46589-000";
    private static final String CIDADE_DEFAULT = "Cidade Builder";
    private static final String BAIRRO_DEFAULT = "Bairro builder";
    private static final String RUA_DEFAULT = "Rua builder";
    private static final String NUMERO_DEFAULT = "291d";

    private Integer id = ID_DEFAULT;
    private String cep = CEP_DEFAULT;
    private String cidade = CIDADE_DEFAULT;
    private String bairro = BAIRRO_DEFAULT;
    private String rua = RUA_DEFAULT;
    private String numero = NUMERO_DEFAULT;


    public EnderecoBuilderModeNeiva() {
    }

    public EnderecoBuilderModeNeiva comId(Integer id) {
        this.id = id;
        return this;
    }

    public EnderecoBuilderModeNeiva comCep(String cep) {
        this.cep = cep;
        return this;
    }

    public EnderecoBuilderModeNeiva comCidade(String cidade) {
        this.cidade = cidade;
        return this;
    }

    public EnderecoBuilderModeNeiva comBairro(String bairro) {
        this.bairro = bairro;
        return this;
    }

    public EnderecoBuilderModeNeiva comRua(String rua) {
        this.rua = rua;
        return this;
    }

    public EnderecoBuilderModeNeiva comNumero(String numero) {
        this.numero = numero;
        return this;
    }

    public EnderecoBuilderModeNeiva mas() {
        return new EnderecoBuilderModeNeiva().comId(id).comCep(cep).comCidade(cidade)
                .comBairro(bairro).comRua(rua).comNumero(numero);
    }

    public Endereco build() {
        Endereco endereco = new Endereco();
        endereco.setIdEndereco(id);
        endereco.setCep(cep);
        endereco.setCidade(cidade);
        endereco.setBairro(bairro);
        endereco.setRua(rua);
        endereco.setNumero(numero);

        return endereco;
    }


}
