package Model;

public class Carros {
    //Atributos 
    private String marca;
    private String modelo;
    private String ano;
     private String preco;
    private String cor;
    private String placa;
   
   

    //métodos
     public Carros(String marca, String modelo, String ano, String preco, String cor, String placa) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.preco = preco;
        this.cor = cor;
        this.placa = placa;
    }



    public String getMarca() {
        return marca;
    }



    public void setMarca(String marca) {
        this.marca = marca;
    }



    public String getModelo() {
        return modelo;
    }



    public void setModelo(String modelo) {
        this.modelo = modelo;
    }



    public String getAno() {
        return ano;
    }



    public void setAno(String ano) {
        this.ano = ano;
    }



    public String getPreco() {
        return preco;
    }



    public void setPreco(String preco) {
        this.preco = preco;
    }



    public String getCor() {
        return cor;
    }



    public void setCor(String cor) {
        this.cor = cor;
    }



    public String getPlaca() {
        return placa;
    }



    public void setPlaca(String placa) {
        this.placa = placa;
    }
   

    

  

    
}
