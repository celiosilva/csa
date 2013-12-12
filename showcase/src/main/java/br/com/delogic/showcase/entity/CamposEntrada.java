package br.com.delogic.showcase.entity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import br.com.delogic.showcase.entity.enums.UnidadeFederativa;

public class CamposEntrada {

    private String                 texto;

    private Integer                numerico;

    @NumberFormat(pattern = "#,###.###")
    private Double                 decimal;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date                   data;

    private String                 mascara;

    @NumberFormat(pattern = "#,##0.00")
    private BigDecimal             monetario;

    private Boolean                checkbox;

    private String                 senha;

    private String                 autocompletar;

    private String                 textArea;

    private UnidadeFederativa      selecao          = UnidadeFederativa.RJ;

    private UnidadeFederativa[]    checkboxMultiplo = { UnidadeFederativa.SP, UnidadeFederativa.MG };

    private UnidadeFederativa      lista;

    private Set<UnidadeFederativa> listaMultipla    = new HashSet<UnidadeFederativa>(Arrays.asList(UnidadeFederativa.MG,
                                                        UnidadeFederativa.PR));
    private String                 areaTexto;
    
    private String                 imagem;

    private String                 imagem2;

    private String                 arquivo1;

    private String                 arquivo2;

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Integer getNumerico() {
        return numerico;
    }

    public void setNumerico(Integer numerico) {
        this.numerico = numerico;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getMascara() {
        return mascara;
    }

    public void setMascara(String mascara) {
        this.mascara = mascara;
    }

    public BigDecimal getMonetario() {
        return monetario;
    }

    public void setMonetario(BigDecimal monetario) {
        this.monetario = monetario;
    }

    public Boolean getCheckbox() {
        return checkbox;
    }

    public void setCheckbox(Boolean checkBox) {
        this.checkbox = checkBox;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getAutocompletar() {
        return autocompletar;
    }

    public void setAutocompletar(String autocompletar) {
        this.autocompletar = autocompletar;
    }

    public String getTextArea() {
        return textArea;
    }

    public void setTextArea(String textArea) {
        this.textArea = textArea;
    }

    public Double getDecimal() {
        return decimal;
    }

    public void setDecimal(Double decimal) {
        this.decimal = decimal;
    }

    public UnidadeFederativa getSelecao() {
        return selecao;
    }

    public void setSelecao(UnidadeFederativa selecao) {
        this.selecao = selecao;
    }

    public UnidadeFederativa[] getCheckboxMultiplo() {
        return checkboxMultiplo;
    }

    public void setCheckboxMultiplo(UnidadeFederativa[] checkboxMultiplo) {
        this.checkboxMultiplo = checkboxMultiplo;
    }

    public Set<UnidadeFederativa> getListaMultipla() {
        return listaMultipla;
    }

    public void setListaMultipla(Set<UnidadeFederativa> listaMultipla) {
        this.listaMultipla = listaMultipla;
    }

    public UnidadeFederativa getLista() {
        return lista;
    }

    public void setLista(UnidadeFederativa lista) {
        this.lista = lista;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getImagem2() {
        return imagem2;
    }

    public void setImagem2(String imagem2) {
        this.imagem2 = imagem2;
    }

    public String getArquivo1() {
        return arquivo1;
    }

    public void setArquivo1(String arquivo1) {
        this.arquivo1 = arquivo1;
    }

    public String getArquivo2() {
        return arquivo2;
    }

    public void setArquivo2(String arquivo2) {
        this.arquivo2 = arquivo2;
    }

    public String getAreaTexto() {
        return areaTexto;
    }

    public void setAreaTexto(String areaTexto) {
        this.areaTexto = areaTexto;
    }

}
