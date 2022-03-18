/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheusmarkies.spaghettibridge.main.calculator;

import java.util.ArrayList;
import java.util.Iterator;
import com.matheusmarkies.spaghettibridge.material.Material;
import com.matheusmarkies.spaghettibridge.objects.bar.Bar;
import com.matheusmarkies.spaghettibridge.objects.node.Node;
import com.matheusmarkies.spaghettibridge.utilities.Matrix;
import com.matheusmarkies.spaghettibridge.utilities.Vector2D;

/**
 *
 * @author Matheus Markies
 */
public class WireCalculator {

    static public class ComponentTemplate {

        String componentName;
        int index;

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

    static ArrayList<ComponentTemplate> createComponentsTemplate(ArrayList<Bar> bars, ArrayList<com.matheusmarkies.spaghettibridge.objects.node.ReactionForces> externalForces) {
        ArrayList<ComponentTemplate> componentTemplates = new ArrayList<>();
        int i = 0;

        for (Bar bar : bars) {
            ComponentTemplate componentTemplate = new ComponentTemplate(bar.getBarName(), i);
            componentTemplates.add(componentTemplate);
            i++;
        }

        for (com.matheusmarkies.spaghettibridge.objects.node.ReactionForces forces : externalForces) {
            ComponentTemplate componentTemplate = new ComponentTemplate(forces.getReactionName(), i);
            componentTemplates.add(componentTemplate);
            i++;
        }

        return componentTemplates;
    }

    static String formatEquationComponent(String equationComponent, String component) {
        //System.out.println(equationComponent);
        String formatedString = "";
        formatedString = equationComponent.replaceAll(component, "");
        formatedString = formatedString.replaceAll("cos", "");
        formatedString = formatedString.replaceAll("sin", "");
        formatedString = formatedString.replaceAll("\\)", "");
        formatedString = formatedString.replaceAll("\\(", "");

        formatedString = formatedString.replaceAll(" ", "");

        if (formatedString.indexOf("=") != -1) {
            formatedString = formatedString.split("\\=")[0];
        }

        return formatedString;
    }

    public static class CalculationComponents{
         ArrayList<double[][]> matrixs = new ArrayList<double[][]>();
         ArrayList<ComponentTemplate> componentTemplates = new ArrayList<>();

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

        public CalculationComponents(ArrayList<ComponentTemplate> componentTemplates, ArrayList<double[][]> matrixs) {
        this.componentTemplates = componentTemplates;
        this.matrixs = matrixs;
        }
         
    }
    
    public static CalculationComponents calculateBarsForces(String[] equations, ArrayList<Bar> bars, ArrayList<com.matheusmarkies.spaghettibridge.objects.node.ReactionForces> externalForces, Material material, double Force) {
        ArrayList<double[][]> matrixs = new ArrayList<double[][]>();

        double[][] F = new double[equations.length][1];
        double[][] C = new double[bars.size() + externalForces.size()][equations.length];

        C = Matrix.zero(C);
        F = Matrix.zero(F);

        ArrayList<ComponentTemplate> componentTemplates = createComponentsTemplate(bars, externalForces);

        //System.out.println("");
        for (int i = 0; i < equations.length; i++) {
            String[] splitPlus = equations[i].split("\\+");

            for (String equationComponent : splitPlus)
                for (ComponentTemplate ct : componentTemplates) {
                    if (equationComponent.indexOf(ct.getComponentName()) != -1) {
                        double matrixComponent = Double.valueOf(formatEquationComponent(equationComponent, ct.getComponentName()));

                        //System.out.println(ct.getComponentName() +" | i: "+ct.index+ " | "+matrixComponent);
                        C[i][ct.index] = matrixComponent;
                    }
                }

            String removeSpaceString = equations[i].split("\\=")[1].replaceAll(" ", "");
            if (removeSpaceString.equals("P/2"))
                F[i][0] = Force/2;
            else
                F[i][0] = 0;
        }

        double[][] N = Matrix.multiply(Matrix.invert(C), F);

        matrixs.add(F);
        matrixs.add(C);
        matrixs.add(N);

        for (ComponentTemplate componentTemplate : componentTemplates)
            for (Bar bar : bars)
                if (componentTemplate.componentName.equals(bar.getBarName())) {
                    bar.setBarForce(N[componentTemplate.index][0]);
                    bar.setNumberOfWires(numberWiresPerBar(bar,material));
                    break;
                }

        return new CalculationComponents(componentTemplates, matrixs);
    }

    public static int numberWiresPerBar(Bar bar, Material material) {
        int WiresPerBar = 0;

        //Barilla n7
        double Tr = material.getTensileStrengthMaximumLoad();
        long Me = (long)(material.getElasticityModulus() * 1000000);
        double d = material.getDiameter()/1000;
        double Cs = material.getSafetyCoefficient();
        //Barilla n7
        
        if (bar.getBarForce() > 0)//tração
        {
            WiresPerBar = (int) Math.round((bar.getBarForce() * Cs) / Tr);
        } else {//flambagem
            double barSize = Vector2D.distance(bar.getNodeStart().getPosition(),
                    bar.getNodeEnd().getPosition())/100;

            WiresPerBar = (int)Math.round(
                    Math.sqrt((64*Math.abs(bar.getBarForce()*Cs)*barSize*barSize)/
                            (Math.pow(Math.PI,3)*Me*Math.pow(d,4)))
            );
        }
        if (WiresPerBar == 0) {
            WiresPerBar = 3;
        }

        return WiresPerBar;
    }
}
