/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheusmarkies.spaghettibridge.objects.bar;

import javafx.scene.paint.Color;
import com.matheusmarkies.spaghettibridge.objects.node.Node;

/**
 *
 * @author Matheus Markies

 * A classe Bar representa uma barra em uma estrutura treliçada,
 * composta por dois nós (ponto de início e fim) e outros atributos que caracterizam
 * as propriedades físicas e visuais da barra, tais como a cor, força interna e o número de fios de macarrão.
 */
public class Bar {

    // Nó de início da barra na estrutura treliçada.
    Node nodeStart;
    // Nó de término da barra na estrutura treliçada.
    Node nodeEnd;

    // Nome identificador da barra, usado para identificação ou depuração.
    String barName;

    // Cor atribuída à barra para representação gráfica; padrão: DARKCYAN.
    Color barColor = Color.DARKCYAN;

    // Valor da força interna na barra, que pode representar tração ou compressão.
    double barForce;
    // Número de fios de macarrão que compõem esta barra da treliça.
    int numberOfWires;

    /**
     * Construtor que inicializa uma instância de Bar com os nós de início e fim, além do nome da barra.
     * A configuração dos demais atributos (como barForce, barColor e numberOfWires) pode ser realizada posteriormente
     * por meio de setters.
     *
     * @param nodeStart Nó de início da barra.
     * @param nodeEnd Nó de término da barra.
     * @param barName Nome da barra.
     */
    public Bar(Node nodeStart, Node nodeEnd, String barName) {
        this.nodeStart = nodeStart;
        this.nodeEnd = nodeEnd;
        this.barName = barName;
    }

    /**
     * Construtor padrão sem parâmetros.
     * Permite a criação de uma instância de Bar para posterior configuração dos atributos.
     */
    public Bar() {
    }

    /**
     * Retorna o nó de início da barra.
     *
     * @return o nó de início (nodeStart).
     */
    public Node getNodeStart() {
        return nodeStart;
    }

    /**
     * Define o nó de início da barra.
     *
     * @param nodeStart o nó a ser definido como início.
     */
    public void setNodeStart(Node nodeStart) {
        this.nodeStart = nodeStart;
    }

    /**
     * Retorna o nó de término da barra.
     *
     * @return o nó de término (nodeEnd).
     */
    public Node getNodeEnd() {
        return nodeEnd;
    }

    /**
     * Define o nó de término da barra.
     *
     * @param nodeEnd o nó a ser definido como término.
     */
    public void setNodeEnd(Node nodeEnd) {
        this.nodeEnd = nodeEnd;
    }

    /**
     * Retorna o nome da barra.
     *
     * @return o nome da barra (barName).
     */
    public String getBarName() {
        return barName;
    }

    /**
     * Define o nome da barra.
     *
     * @param barName o nome a ser atribuído à barra.
     */
    public void setBarName(String barName) {
        this.barName = barName;
    }

    /**
     * Retorna a força interna presente na barra.
     * Esse valor pode representar tração ou compressão, dependendo do contexto da análise estrutural.
     *
     * @return o valor da força (barForce).
     */
    public double getBarForce() {
        return barForce;
    }

    /**
     * Define a força interna da barra.
     *
     * @param barForce o valor da força a ser atribuído.
     */
    public void setBarForce(double barForce) {
        this.barForce = barForce;
    }

    /**
     * Retorna o número de fios de macarrão que compõem a barra.
     * Esse atributo é fundamental para análises que consideram a distribuição de carga ou propriedades
     * estruturais relacionadas à quantidade de material utilizado.
     *
     * @return o número de fios (numberOfWires).
     */
    public int getNumberOfWires() {
        return numberOfWires;
    }

    /**
     * Define o número de fios de macarrão na barra.
     *
     * @param numberOfWires o número de fios a ser atribuído.
     */
    public void setNumberOfWires(int numberOfWires) {
        this.numberOfWires = numberOfWires;
    }

    /**
     * Retorna a cor atribuída à barra.
     *
     * @return a cor da barra (barColor).
     */
    public Color getBarColor() {
        return barColor;
    }

    /**
     * Define a cor da barra.
     *
     * @param barColor a cor a ser atribuída.
     */
    public void setBarColor(Color barColor) {
        this.barColor = barColor;
    }

    /**
     * Converte um objeto do tipo BarSerializable em uma instância de Bar.
     * Este método é útil para converter dados serializados (possivelmente lidos de um arquivo ou de uma rede)
     * para o objeto Bar utilizado na análise estrutural.
     *
     * O processo envolve:
     * 1. Criação de novos nós (nodeStart e nodeEnd) a partir dos nomes contidos em BarSerializable.
     * 2. Criação de uma nova instância de Bar com os nós e o nome da barra.
     *
     * @param barSerializable objeto serializável contendo dados de uma barra.
     * @return uma instância de Bar construída a partir dos dados de barSerializable.
     */
    public static Bar barSerializableToBar(BarSerializable barSerializable) {
        // Cria um novo nó de início e atribui o nome contido no objeto serializável.
        Node nodeStart = new Node();
        nodeStart.setNodeName(barSerializable.getNodeStart());
        // Cria um novo nó de término e atribui o nome contido no objeto serializável.
        Node nodeEnd = new Node();
        nodeEnd.setNodeName(barSerializable.getNodeEnd());

        // Cria e retorna uma nova instância de Bar utilizando os nós e o nome extraído.
        return new Bar(
                nodeStart,
                nodeEnd,
                barSerializable.getBarName()
        );
    }

}

/*
Comentários Gerais:

A classe Bar encapsula os atributos e métodos relacionados a uma barra de uma treliça, onde cada barra conecta dois nós (nodeStart e nodeEnd).

O atributo barName é utilizado para identificar a barra, enquanto barColor define sua cor para fins gráficos.

O atributo barForce armazena a força interna (tensão ou compressão) que a barra suporta, e numberOfWires representa o número de fios de macarrão que compõem a barra, sendo este último crucial para análises que consideram a resistência e a distribuição de carga na estrutura.

Os métodos getters e setters permitem o acesso e a modificação dos atributos, promovendo o encapsulamento e a flexibilidade na manipulação dos dados.

O método estático barSerializableToBar converte uma representação serializada de uma barra (BarSerializable) em um objeto Bar, facilitando a integração entre armazenamento/transferência de dados e a lógica de análise estrutural. Cada método e atributo foi cuidadosamente implementado para garantir que a classe Bar possa ser utilizada de forma robusta e intuitiva em aplicações de análise estática de estruturas treliçadas.
*/