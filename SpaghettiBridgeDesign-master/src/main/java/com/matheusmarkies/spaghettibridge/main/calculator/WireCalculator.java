/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheusmarkies.spaghettibridge.main.calculator;

import java.util.ArrayList;

import com.matheusmarkies.spaghettibridge.material.Material;
import com.matheusmarkies.spaghettibridge.objects.bar.Bar;
import com.matheusmarkies.spaghettibridge.utilities.Matrix;
import com.matheusmarkies.spaghettibridge.utilities.Vector2D;

/**
 *
 * @author Matheus Markies

 * Classe responsável por realizar todos os cálculos de engenharia estrutural
 * relacionados à determinação das forças nas barras de uma treliça e ao cálculo
 * da quantidade mínima de fios de macarrão necessários para suportar os esforços.
 *
 * Essa classe trabalha a partir de um sistema de equações lineares montado com base
 * nas forças externas aplicadas e nas características geométricas da estrutura.
 */
public class WireCalculator {

    /**
     * Classe auxiliar que representa um componente do sistema de equações.
     * Pode ser uma barra da treliça ou uma força de reação em um nó.
     */
    static public class ComponentTemplate {
        String componentName; // Nome identificador do componente (ex: "F1", "Rx")
        int index; // Índice correspondente na matriz do sistema

        public ComponentTemplate(String componentName, int index) {
            this.componentName = componentName;
            this.index = index;
        }

        public String getComponentName() {
            return componentName;
        }

        public void setConponentName(String conponentName) {
            this.componentName = conponentName;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }

    /**
     * Gera a lista de componentes do sistema de equações (barras + forças de reação),
     * associando a cada componente um índice único.
     *
     * @param bars Lista de barras da estrutura
     * @param externalForces Lista de forças externas aplicadas nos nós
     * @return Lista de componentes do sistema
     */
    static ArrayList<ComponentTemplate> createComponentsTemplate(
            ArrayList<Bar> bars,
            ArrayList<com.matheusmarkies.spaghettibridge.objects.node.ReactionForces> externalForces
    ) {
        ArrayList<ComponentTemplate> componentTemplates = new ArrayList<>();
        int i = 0;

        // Adiciona cada barra como um componente
        for (Bar bar : bars) {
            componentTemplates.add(new ComponentTemplate(bar.getBarName(), i));
            i++;
        }

        // Adiciona cada força de reação como componente
        for (com.matheusmarkies.spaghettibridge.objects.node.ReactionForces force : externalForces) {
            componentTemplates.add(new ComponentTemplate(force.getReactionName(), i));
            i++;
        }

        return componentTemplates;
    }

    /**
     * Limpa e formata uma string de equação, removendo nomes de variáveis e funções trigonométricas,
     * retornando apenas o coeficiente numérico do componente na equação.
     *
     * @param equationComponent Parte da equação contendo o componente
     * @param component Nome do componente a ser removido
     * @return Coeficiente da equação como string pura
     */
    static String formatEquationComponent(String equationComponent, String component) {
        String formatted = equationComponent.replaceAll(component, "")
                .replaceAll("cos", "")
                .replaceAll("sin", "")
                .replaceAll("\\)", "")
                .replaceAll("\\(", "")
                .replaceAll(" ", "");

        if (formatted.contains("=")) {
            formatted = formatted.split("=")[0];
        }

        return formatted;
    }

    /**
     * Classe que encapsula o resultado dos cálculos da estrutura:
     * - As matrizes envolvidas
     * - A lista de componentes associados aos índices do sistema
     */
    public static class CalculationComponents {
        ArrayList<double[][]> matrixs = new ArrayList<>();
        ArrayList<ComponentTemplate> componentTemplates = new ArrayList<>();

        public CalculationComponents(ArrayList<ComponentTemplate> componentTemplates, ArrayList<double[][]> matrixs) {
            this.componentTemplates = componentTemplates;
            this.matrixs = matrixs;
        }

        public void setMatrixs(ArrayList<double[][]> matrixs) {
            this.matrixs = matrixs;
        }

        public ArrayList<double[][]> getMatrixs() {
            return matrixs;
        }

        public void setComponentTemplates(ArrayList<ComponentTemplate> componentTemplates) {
            this.componentTemplates = componentTemplates;
        }

        public ArrayList<ComponentTemplate> getComponentTemplates() {
            return componentTemplates;
        }
    }

    /**
     * Método principal que realiza o cálculo das forças nas barras da treliça
     * e determina a quantidade de fios de macarrão necessários para resistir aos esforços.
     *
     * @param equations Sistema de equações simbólico
     * @param bars Lista de barras da treliça
     * @param externalForces Lista de forças externas (reação nos apoios)
     * @param material Propriedades físicas do material utilizado (macarrão)
     * @param Force Valor da carga aplicada na estrutura
     * @return Objeto CalculationComponents contendo os resultados do sistema
     */
    public static CalculationComponents calculateBarsForces(
            String[] equations,
            ArrayList<Bar> bars,
            ArrayList<com.matheusmarkies.spaghettibridge.objects.node.ReactionForces> externalForces,
            Material material,
            double Force
    ) {
        ArrayList<double[][]> matrixs = new ArrayList<>();

        // Vetor de resultados (forças externas aplicadas nos nós)
        double[][] F = new double[equations.length][1];
        // Matriz de coeficientes do sistema (barras + reações)
        double[][] C = new double[bars.size() + externalForces.size()][equations.length];

        // Inicializa as matrizes com zeros
        C = Matrix.zero(C);
        F = Matrix.zero(F);

        // Cria os componentes do sistema (barras + reações)
        ArrayList<ComponentTemplate> componentTemplates = createComponentsTemplate(bars, externalForces);

        // Para cada equação do sistema simbólico
        for (int i = 0; i < equations.length; i++) {
            // Divide cada equação nas somas de termos
            String[] splitPlus = equations[i].split("\\+");

            for (String equationComponent : splitPlus) {
                for (ComponentTemplate ct : componentTemplates) {
                    // Se o componente atual aparece na equação, extrai seu coeficiente
                    if (equationComponent.contains(ct.getComponentName())) {
                        double matrixComponent = Double.parseDouble(
                                formatEquationComponent(equationComponent, ct.getComponentName())
                        );
                        C[i][ct.index] = matrixComponent;
                    }
                }
            }

            // Define os valores do vetor F com base nas cargas aplicadas
            String rightSide = equations[i].split("=")[1].replaceAll(" ", "");
            if (rightSide.equals("P/2")) {
                F[i][0] = Force / 2;
            } else {
                F[i][0] = 0;
            }
        }

        // Resolve o sistema de equações: C⁻¹ * F = N (forças nas barras e reações)
        double[][] N = Matrix.multiply(Matrix.invert(C), F);

        // Armazena as matrizes para análise
        matrixs.add(F);
        matrixs.add(C);
        matrixs.add(N);

        // Atribui a força calculada a cada barra e calcula a quantidade de fios
        for (ComponentTemplate componentTemplate : componentTemplates) {
            for (Bar bar : bars) {
                if (componentTemplate.componentName.equals(bar.getBarName())) {
                    bar.setBarForce(N[componentTemplate.index][0]);
                    bar.setNumberOfWires(numberWiresPerBar(bar, material));
                    break;
                }
            }
        }

        return new CalculationComponents(componentTemplates, matrixs);
    }

    /**
     * Método que determina a quantidade mínima de fios de macarrão necessários
     * para resistir ao esforço aplicado em uma barra específica.
     *
     * @param bar A barra a ser analisada
     * @param material As propriedades físicas do macarrão utilizado
     * @return Quantidade mínima de fios a serem utilizados
     */
    public static int numberWiresPerBar(Bar bar, Material material) {
        int WiresPerBar = 0;

        // Parâmetros físicos do material
        double Tr = material.getTensileStrengthMaximumLoad(); // Carga máxima de tração
        long Me = (long)(material.getElasticityModulus() * 1_000_000); // Módulo de elasticidade em Pa
        double d = material.getDiameter() / 1000.0; // Diâmetro em metros
        double Cs = material.getSafetyCoefficient(); // Coeficiente de segurança

        // Caso a barra esteja em tração
        if (bar.getBarForce() > 0) {
            WiresPerBar = (int) Math.ceil((bar.getBarForce() * Cs) / Tr);
        } else {
            // Caso a barra esteja em compressão (flambagem)
            double barSize = Vector2D.distance(bar.getNodeStart().getPosition(), bar.getNodeEnd().getPosition()) / 100.0;

            // Fórmula da flambagem de Euler adaptada para determinação de diâmetro crítico
            WiresPerBar = (int) Math.ceil(
                    Math.sqrt((64 * Math.abs(bar.getBarForce() * Cs) * Math.pow(barSize, 2)) /
                            (Math.pow(Math.PI, 3) * Me * Math.pow(d, 4)))
            );
        }

        // Garante pelo menos 3 fios por barra, por segurança estrutural mínima
        if (WiresPerBar == 0) {
            WiresPerBar = 3;
        }

        return WiresPerBar;
    }
}