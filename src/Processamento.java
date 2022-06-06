public class Processamento {
    private String dataExtracao;
    private String dataObito;
    private String dataNascimento;
    private String sexo;
    private String raca;
    private String obitoPrematuro;
    private String tipoObito;

    public Processamento(String dataExtracao, String dataObito, String dataNascimento, String sexo, String raca,
            String obitoPrematuro, String tipoObito) {
        this.dataExtracao = dataExtracao;
        this.dataObito = dataObito;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.raca = raca;
        this.obitoPrematuro = obitoPrematuro;
        this.tipoObito = tipoObito;
    }
    

    public String getDataExtracao() {
        return dataExtracao;
    }


    public String getDataObito() {
        return dataObito;
    }

    
    public String getDataNascimento() {
        return dataNascimento;
    }

   
    public String getSexo() {
        return sexo;
    }

    
    public String getRaca() {
        return raca;
    }

    
    public String getObitoPrematuro() {
        return obitoPrematuro;
    }

    
    public String getTipoObito() {
        return tipoObito;
    }

    @Override
    public String toString() {
        return "\nÓbito [dataExtracao= " + dataExtracao + " Data do nascimento= " + dataNascimento + " Data do óbito= "+ dataObito + "\nObito prematuro= " + obitoPrematuro + "\nRaça= " + raca + " Sexo= " + sexo+ " Tipo do obito= " + tipoObito + "]";
    }


    
    

}
