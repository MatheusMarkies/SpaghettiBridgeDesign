/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheusmarkies.spaghettibridge.objects.node;

import java.io.Serializable;

import com.matheusmarkies.spaghettibridge.utilities.Vector2D;

/**
 *
 * @author Matheus Markies

 * A classe ReactionForces representa uma força de reação aplicada a um nó específico da estrutura treliçada.
 *
 * Em uma análise estrutural, as forças de reação são aquelas que surgem nos apoios em resposta às cargas externas aplicadas.
 * Esta classe encapsula as informações relacionadas a essas reações, como:
 * - O nó onde a reação ocorre;
 * - A direção da força de reação, representada por um vetor 2D;
 * - Um nome identificador da reação (útil para depuração ou interface gráfica).
 */
public class ReactionForces implements Serializable {

    // Nó da estrutura onde a força de reação está aplicada.
    private Node ReactionNode;

    // Direção da força de reação, representada como vetor 2D.
    private Vector2D forceDirection;

    // Nome identificador da força de reação (ex: "Rx", "Ry", "R1", etc.).
    private String reactionName;

    /**
     * Construtor completo que inicializa a força de reação com o nó, direção e nome especificados.
     *
     * @param ReactionNode   Nó onde a força de reação é aplicada.
     * @param forceDirection Vetor que representa a direção e sentido da força de reação.
     * @param reactionName   Nome da força de reação.
     */
    public ReactionForces(Node ReactionNode, Vector2D forceDirection, String reactionName) {
        this.ReactionNode = ReactionNode;
        this.forceDirection = forceDirection;
        this.reactionName = reactionName;
    }

    /**
     * Retorna o nó associado à força de reação.
     *
     * @return Nó da reação.
     */
    public Node getReactionNode() {
        return ReactionNode;
    }

    /**
     * Define o nó onde a força de reação está aplicada.
     *
     * @param ReactionNode Nó da estrutura.
     */
    public void setReactionNode(Node ReactionNode) {
        this.ReactionNode = ReactionNode;
    }

    /**
     * Retorna o vetor direção da força de reação.
     * Esse vetor determina a orientação e o sentido da reação (ex: vertical, horizontal, inclinada).
     *
     * @return Vetor 2D da força de reação.
     */
    public Vector2D getForceDirection() {
        return forceDirection;
    }

    /**
     * Define o vetor de direção da força de reação.
     *
     * @param forceDirection Vetor 2D com direção e sentido da reação.
     */
    public void setForceDirection(Vector2D forceDirection) {
        this.forceDirection = forceDirection;
    }

    /**
     * Retorna o nome identificador da força de reação.
     *
     * @return Nome da reação (reactionName).
     */
    public String getReactionName() {
        return reactionName;
    }

    /**
     * Define o nome da força de reação.
     *
     * @param reactionName Nome identificador a ser atribuído.
     */
    public void setReactionName(String reactionName) {
        this.reactionName = reactionName;
    }

}

/*
Comentários Gerais:

A classe ReactionForces encapsula as informações necessárias para definir uma reação de apoio em uma estrutura, sendo fundamental para a análise de equilíbrio e estabilidade.

Os atributos ReactionNode, forceDirection e reactionName representam, respectivamente, o nó onde a reação ocorre, a direção da força aplicada e o identificador da reação.

O construtor possibilita a criação de instâncias de ReactionForces com todos os atributos definidos, facilitando a inicialização completa do objeto.

Os métodos getters e setters promovem o encapsulamento, permitindo acesso e modificação controlada dos atributos da classe.

A implementação de Serializable possibilita a persistência e a transferência dos objetos ReactionForces, que é útil em simulações e análises estruturais. Cada método foi implementado para assegurar que a representação das reações em uma estrutura seja precisa e de fácil manipulação na aplicação.
*/
