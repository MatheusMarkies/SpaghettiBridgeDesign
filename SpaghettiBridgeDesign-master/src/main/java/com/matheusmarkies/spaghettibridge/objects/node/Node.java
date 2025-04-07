/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheusmarkies.spaghettibridge.objects.node;

import java.util.ArrayList;
import java.util.Objects;
import com.matheusmarkies.spaghettibridge.objects.bar.Bar;
import com.matheusmarkies.spaghettibridge.utilities.Vector2D;

/**
 *
 * @author Matheus Markies

 * A classe Node representa um nó em uma estrutura treliçada.
 * Cada nó possui uma posição definida por um vetor 2D, um nome identificador,
 * um índice e pode armazenar informações sobre forças externas e barras conectadas.
 * A classe também permite identificar se o nó atua como receptor de carga (cargo receiver).
 */
public class Node {

    // Posição do nó no plano, representada por um objeto Vector2D.
    Vector2D position;
    // Nome identificador do nó, útil para depuração e referência.
    String nodeName;

    // Flag que indica se o nó é um receptor de carga.
    boolean isCargoReciver = false;

    // Índice do nó, possivelmente utilizado para identificação em algoritmos de análise.
    int index = 0;

    // Lista de forças externas aplicadas ao nó, representadas por objetos ReactionForces.
    ArrayList<ReactionForces> externalForces = new ArrayList<>();

    // Lista de barras conectadas a este nó.
    ArrayList<Bar> connectedBars = new ArrayList<>();

    /**
     * Construtor que inicializa um nó com posição, nome e índice.
     *
     * @param position posição do nó no plano, representada por Vector2D.
     * @param nodeName nome identificador do nó.
     * @param index índice do nó.
     */
    public Node(Vector2D position, String nodeName, int index) {
        this.position = position;
        this.nodeName = nodeName;
        this.index = index;
    }

    /**
     * Construtor padrão sem parâmetros.
     * Permite a criação de um nó para posterior configuração de seus atributos.
     */
    public Node() {
    }

    /**
     * Retorna a posição do nó.
     *
     * @return a posição do nó, representada por um objeto Vector2D.
     */
    public Vector2D getPosition() {
        return position;
    }

    /**
     * Define a posição do nó.
     *
     * @param position o novo vetor de posição para o nó.
     */
    public void setPosition(Vector2D position) {
        this.position = position;
    }

    /**
     * Retorna o nome do nó.
     *
     * @return o nome identificador do nó.
     */
    public String getNodeName() {
        return nodeName;
    }

    /**
     * Define o nome do nó.
     *
     * @param nodeName o novo nome a ser atribuído ao nó.
     */
    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    /**
     * Retorna o índice do nó.
     *
     * @return o índice do nó.
     */
    public int getIndex() {
        return index;
    }

    /**
     * Define o índice do nó.
     *
     * @param index o novo índice a ser atribuído ao nó.
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Retorna a lista de forças externas aplicadas ao nó.
     *
     * @return uma ArrayList de ReactionForces representando as forças externas.
     */
    public ArrayList<ReactionForces> getExternalForces() {
        return externalForces;
    }

    /**
     * Define a lista de forças externas aplicadas ao nó.
     *
     * @param externalForces uma ArrayList de ReactionForces a ser atribuída ao nó.
     */
    public void setExternalForces(ArrayList<ReactionForces> externalForces) {
        this.externalForces = externalForces;
    }

    /**
     * Adiciona uma força externa à lista de forças aplicadas ao nó.
     *
     * @param e um objeto ReactionForces que representa uma força externa a ser adicionada.
     */
    public void addExternalForce(ReactionForces e) {
        this.externalForces.add(e);
    }

    /**
     * Retorna a lista de barras conectadas a este nó.
     *
     * @return uma ArrayList de Bar representando as barras conectadas.
     */
    public ArrayList<Bar> getConnectedBars() {
        return connectedBars;
    }

    /**
     * Define a lista de barras conectadas ao nó.
     *
     * @param connectedBars uma ArrayList de Bar a ser atribuída ao nó.
     */
    public void setConnectedBars(ArrayList<Bar> connectedBars) {
        this.connectedBars = connectedBars;
    }

    /**
     * Adiciona uma barra à lista de barras conectadas ao nó.
     *
     * @param bar um objeto Bar que será adicionado à lista de barras conectadas.
     */
    public void addConnectedBar(Bar bar) {
        connectedBars.add(bar);
    }

    /**
     * Verifica se o nó atua como receptor de carga.
     *
     * @return true se o nó é um receptor de carga, false caso contrário.
     */
    public boolean isCargoReciver() {
        return isCargoReciver;
    }

    /**
     * Define se o nó é ou não um receptor de carga.
     *
     * @param isCargoReciver true para indicar que o nó é receptor de carga, false caso contrário.
     */
    public void setIsCargoReciver(boolean isCargoReciver) {
        this.isCargoReciver = isCargoReciver;
    }

    /**
     * Calcula o hashCode do nó combinando os atributos relevantes.
     * Isso inclui a posição, o nome, o indicador de receptor de carga, o índice,
     * bem como as listas de forças externas e barras conectadas.
     *
     * @return o valor hashCode calculado para este nó.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.position);
        hash = 41 * hash + Objects.hashCode(this.nodeName);
        hash = 41 * hash + (this.isCargoReciver ? 1 : 0);
        hash = 41 * hash + this.index;
        hash = 41 * hash + Objects.hashCode(this.externalForces);
        hash = 41 * hash + Objects.hashCode(this.connectedBars);
        return hash;
    }

    /**
     * Compara este nó com outro objeto para determinar igualdade.
     * Dois nós são considerados iguais se tiverem o mesmo nome, a mesma posição
     * e o mesmo indicador de receptor de carga.
     *
     * @param obj o objeto a ser comparado com este nó.
     * @return true se os nós forem considerados iguais, false caso contrário.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Node other = (Node) obj;
        if (this.isCargoReciver != other.isCargoReciver)
            return false;
        if (!Objects.equals(this.nodeName, other.nodeName))
            return false;
        return Objects.equals(this.position, other.position);
    }
}

/*
Comentários Gerais:

A classe Node encapsula a representação de um nó em uma estrutura treliçada, armazenando sua posição (Vector2D), nome, índice e status de receptor de carga.

As listas externalForces e connectedBars permitem associar, respectivamente, as forças externas aplicadas e as barras conectadas a este nó, essenciais para a análise de equilíbrio e integridade estrutural.

Os métodos getters e setters garantem o encapsulamento dos atributos, possibilitando acesso e modificação controlada.

Os métodos addExternalForce e addConnectedBar facilitam a adição incremental de forças e barras, permitindo a construção dinâmica da estrutura.

Os métodos hashCode e equals são implementados para garantir a correta comparação e o funcionamento do nó em coleções que utilizam hashing, levando em consideração os atributos essenciais do nó. Cada método e atributo é implementado para assegurar que a classe Node seja robusta e flexível na modelagem e análise de estruturas treliçadas.
*/