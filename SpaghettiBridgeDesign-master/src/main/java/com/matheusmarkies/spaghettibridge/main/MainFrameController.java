/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheusmarkies.spaghettibridge.main;

import java.io.File;
import java.io.IOException;
import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.matheusmarkies.spaghettibridge.main.features.FileTypeFilter;
import com.matheusmarkies.spaghettibridge.main.features.StringFeatures;
import com.matheusmarkies.spaghettibridge.main.manager.BridgeManager;
import com.matheusmarkies.spaghettibridge.main.tables.AngleTable;
import com.matheusmarkies.spaghettibridge.material.Material;
import com.matheusmarkies.spaghettibridge.popup.*;
import com.matheusmarkies.spaghettibridge.utilities.Vector2D;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import com.matheusmarkies.spaghettibridge.main.calculator.EquationAssembler;
import com.matheusmarkies.spaghettibridge.main.calculator.WireCalculator.ComponentTemplate;
import com.matheusmarkies.spaghettibridge.main.features.Bridge;
import com.matheusmarkies.spaghettibridge.main.features.Save;
import com.matheusmarkies.spaghettibridge.main.grid.Grid;
import com.matheusmarkies.spaghettibridge.main.manager.ConsoleManager;
import com.matheusmarkies.spaghettibridge.main.tables.BarTable;
import com.matheusmarkies.spaghettibridge.main.view.ShowBridge;
import com.matheusmarkies.spaghettibridge.objects.bar.Bar;
import com.matheusmarkies.spaghettibridge.objects.bar.BarSerializable;
import com.matheusmarkies.spaghettibridge.objects.node.NodeSerializable;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;

/**
 * FXML Controller class
 *
 * @author MATHEUSDACOSTACAFFER
 */
public class MainFrameController implements Initializable {

    @FXML
    private Button assembler_equations;

    @FXML
    private TableColumn<AngleTable, Double> bridge_angle_table_angle;

    @FXML
    private TableColumn<AngleTable, String> bridge_angle_table_bars;

    @FXML
    private TableColumn<AngleTable, String> bridge_angle_table_secondsbars;

    @FXML
    private TableView<AngleTable> bridge_table_angle_view;

    @FXML
    private TableView<BarTable> bridge_table_view;

    @FXML
    private TableColumn<BarTable, String> bridge_table_view_bar;

    @FXML
    private TableColumn<BarTable, Double> bridge_table_view_size;

    @FXML
    private TableColumn<BarTable, Double> bridge_table_view_force;

    @FXML
    private TableColumn<BarTable, Integer> bridge_table_view_wires;

    @FXML
    private Button calculate_wires;

    @FXML
    private AnchorPane canvas_plane;

    @FXML
    private Button create_bar_button;

    @FXML
    private Button create_node_button;

    @FXML
    private Label details_toplabel;

    @FXML
    private Label details_toplabel1;

    @FXML
    private Label down_status;

    @FXML
    private AnchorPane log_console_plane;

    @FXML
    private AnchorPane log_plane;

    @FXML
    private ScrollPane log_scroll_plane;

    @FXML
    private MenuBar menu_bar;

    @FXML
    private MenuItem menu_display_forcesbarsview;

    @FXML
    private MenuItem menu_display_freebodyview;

    @FXML
    private MenuItem menu_display_explodedview;

    @FXML
    private MenuItem menu_display_standardbarsview;

    @FXML
    private MenuItem menu_display_vectorbarsview;

    @FXML
    private MenuItem menu_setmaterial;

    @FXML
    private MenuItem menu_testload;

    @FXML
    private MenuItem menu_testloadnode;

    @FXML
    private Label menu_zoom_label;

    @FXML
    private Slider menu_zoom_slider;

    @FXML
    private Button set_measure;

    @FXML
    private Font x1;

    @FXML
    private Font x11;

    @FXML
    private Color x2;

    @FXML
    private Color x21;

    @FXML
    private Font x3;

    @FXML
    private Color x4;

    @FXML
    private Button zoom_add_button;

    @FXML
    private Button zoom_decrease_button;

    @FXML
    void wireCalculatorButtonAction(ActionEvent event) {
        calculateBarForces();

        DecimalFormat df = new DecimalFormat("#.#");
        df.setRoundingMode(RoundingMode.CEILING);

        consoleManager.printLog("");
        for (ComponentTemplate componentTemplate : bridgeMain.bridgeManager.getCalculationComponents().getComponentTemplates()) {
            consoleManager.printLog(componentTemplate.getComponentName() + ": " + df.format(bridgeMain.bridgeManager.getCalculationComponents().getMatrixs().get(2)[componentTemplate.getIndex()][0]) + "N");
        }

        consoleManager.printLog("");
        for (Bar bar : bridgeMain.bridgeManager.getBars()) {
            consoleManager.printLog(bar.getBarName() + ": " + bar.getNumberOfWires() + " Fios");
        }
    }

    void calculateBarForces() {
        bridgeMain.bridgeManager.setEquations(new ArrayList<>());

        for (com.matheusmarkies.spaghettibridge.objects.node.Node entry : bridgeMain.bridgeManager.getNodes()) {
            bridgeMain.bridgeManager.addEquation(EquationAssembler.getForcePerNodeWithAngles(entry, entry.isCargoReciver())[0]);
            bridgeMain.bridgeManager.addEquation(EquationAssembler.getForcePerNodeWithAngles(entry, entry.isCargoReciver())[1]);
        }

        String[] equations = bridgeMain.bridgeManager.getEquations().toArray(new String[bridgeMain.bridgeManager.getEquations().size()]);
        try {
            bridgeMain.bridgeManager.setCalculationComponents(
                    com.matheusmarkies.spaghettibridge.main.calculator.WireCalculator.calculateBarsForces(
                            equations,
                            bridgeMain.bridgeManager.getBars(),
                            bridgeMain.bridgeManager.getReactions(),
                            bridgeMain.bridgeManager.getMaterial(),
                            bridgeMain.bridgeManager.getTestLoadInAction()
                    ));
        } catch (Exception e) {
            consoleManager.printLog("[Calcular fios] Impossivel calcular fios");
        }

        bridgeMain.bridgeManager.updateBarsColors();
        //for (Bar bar : bridgeMain.bridgeManager.getBars())
        //System.out.println("Linha elastica: " + Deformation.getElasticLineIntegral(Vector2D.distance(bar.getNodeStart().getPosition(), bar.getNodeEnd().getPosition()) / 2, bar.getBarForce(), bar));
        showBridge.showBars();
    }

    @FXML
    void createBarButtonAction(ActionEvent event) {
        if (bridgeMain.bridgeManager.getNodes().size() > 1) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                        "/com/matheusmarkies/spaghettibridge/CreateBar.fxml"));
                Parent root = (Parent) fxmlLoader.load();

                com.matheusmarkies.spaghettibridge.popup.CreateBarController createBarController = fxmlLoader.getController();

                createBarController.setMainFrameController(this);
                createBarController.setBridgeMain(bridgeMain);

                Stage stage = new Stage();
                stage.setTitle("Criar Barra");
                stage.setScene(new Scene(root));

                stage.show();
            } catch (IOException ex) {

            }
        }
    }

    @FXML
    void createNodeButtonAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                    "/com/matheusmarkies/spaghettibridge/CreateNode.fxml"));
            Parent root = (Parent) fxmlLoader.load();

            bridgeMain.bridgeManager.setTranslateVector(new Vector2D(0,0));
            updateBridge();

            CreateNodeController createNodeController = fxmlLoader.getController();

            createNodeController.setMainFrameController(this);
            createNodeController.setSpaghettiBridgeMain(bridgeMain);

            Stage stage = new Stage();
            stage.setTitle("Criar No");
            stage.setScene(new Scene(root));

            stage.setOnCloseRequest(ev -> {
                if (nodePointer != null) {
                    removeObjectToCanvas(nodePointer);
                    nodePointer = null;
                }
            });
            stage.show();
        } catch (IOException ex) {

        }
    }

    @FXML
    void assembleEquationsButtonAction(ActionEvent event) {
        bridgeMain.bridgeManager.setEquations(new ArrayList<>());

        for (com.matheusmarkies.spaghettibridge.objects.node.Node entry : bridgeMain.bridgeManager.getNodes()) {
            consoleManager.printLog(EquationAssembler.getForcePerNode(entry, entry.isCargoReciver())[0]);
            consoleManager.printLog(EquationAssembler.getForcePerNode(entry, entry.isCargoReciver())[1]);

            bridgeMain.bridgeManager.addEquation(EquationAssembler.getForcePerNode(entry, entry.isCargoReciver())[0]);
            bridgeMain.bridgeManager.addEquation(EquationAssembler.getForcePerNode(entry, entry.isCargoReciver())[1]);
        }
    }

    @FXML
    void deformationButtonAction(ActionEvent event) {
        consoleManager.clear();
        for (Bar bar : bridgeMain.bridgeManager.getBars())
            consoleManager.printLog(bar.getBarName() + ": " + (Math.round(com.matheusmarkies.spaghettibridge.main.calculator.Deformation.getBarDeformation(bridgeMain.bridgeManager.getMaterial(), bar) * 1000) / 1000) + "cm");
    }

    @FXML
    void standardBarsViewButtonAction(ActionEvent event) {
        showBridge.setBarView(ShowBridge.BarView.Standard);
    }

    @FXML
    void forcesBarsViewButtonAction(ActionEvent event) {
        showBridge.setBarView(ShowBridge.BarView.Forces);
    }

    @FXML
    void freeBodyViewButtonAction(ActionEvent event) {
        showBridge.setBarView(ShowBridge.BarView.FreeBody);
    }

    @FXML
    void explodedViewViewButtonAction(ActionEvent event) {showBridge.setBarView(ShowBridge.BarView.ExplodedView);}

    @FXML
    void vectorBarsViewButtonAction(ActionEvent event) {
        showBridge.setBarView(ShowBridge.BarView.Vectors);
    }

    @FXML
    void onClickedZoomAddButton(ActionEvent event) {
        bridgeMain.bridgeManager.setZoomCoefficient(bridgeMain.bridgeManager.getZoomCoefficient() + 0.2d);
        down_status.setText("Zoom: "+Math.floor(bridgeMain.bridgeManager.getZoomCoefficient()*10)/10+"x");

        updateBridge();
    }

    @FXML
    void onClickedZoomDecreaseButton(ActionEvent event) {
        bridgeMain.bridgeManager.setZoomCoefficient(bridgeMain.bridgeManager.getZoomCoefficient()-0.2d);
        down_status.setText("Zoom: "+Math.floor(bridgeMain.bridgeManager.getZoomCoefficient()*10)/10+"x");

        updateBridge();
    }

    boolean notChangeValue = false;
    @FXML
    void onMouseScrollEvent(ScrollEvent event) {
        bridgeMain.bridgeManager.setZoomCoefficient(bridgeMain.bridgeManager.getZoomCoefficient()+event.getDeltaY()/100);
        down_status.setText("Zoom: "+Math.floor(bridgeMain.bridgeManager.getZoomCoefficient()*10)/10+"x");

        notChangeValue = true;
        menu_zoom_slider.setValue((100) * bridgeMain.bridgeManager.getZoomCoefficient() / 20);

        updateBridge();
    }

    @FXML
    void closeBridgeButtonAction(ActionEvent event) {
        bridgeMain.bridgeManager.reset();
        updateBridge();
    }

    @FXML
    void onMouseDragEvent(MouseEvent event) {
        Vector2D panVector = new Vector2D(event.getX(),event.getY());
        panVector = Vector2D.add(panVector,new Vector2D(
                -canvas_plane.getWidth()/2, -canvas_plane.getHeight()/2
        ));
        down_status.setText("Pan: x: "+panVector.x()+" y: "+panVector.y());

        bridgeMain.bridgeManager.setTranslateVector(panVector);

        ArrayList<Bar> selectedBars = new ArrayList<>();
        bridgeMain.bridgeManager.setSelectedBar(selectedBars);

        updateBridge();
    }

    com.matheusmarkies.spaghettibridge.main.manager.ConsoleManager consoleManager;
    com.matheusmarkies.spaghettibridge.main.view.ShowBridge showBridge;

    Grid grid;

    void updateBridge() {
        showBridge.showNodes();
        showBridge.showReactions();
        showBridge.showBars();
        showBridge.showMeasure();

        int showZoomDimension = 10;
        if (bridgeMain.bridgeManager.getZoomCoefficient() < 1f)
            showZoomDimension = 100;

        menu_zoom_label.setText(
                Math.floor(bridgeMain.bridgeManager.getZoomCoefficient() * showZoomDimension) / showZoomDimension
                        + "x");
    }

    @FXML
    void setTestLoadNodeButtonAction(ActionEvent event) {
        if(bridgeMain.bridgeManager.getNodes().size() > 0) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                        "/com/matheusmarkies/spaghettibridge/SetLoadNode.fxml"));
                Parent root = (Parent) fxmlLoader.load();

                bridgeMain.bridgeManager.setTranslateVector(new Vector2D(0, 0));
                updateBridge();

                SetLoadNodeController setLoadNodeController = fxmlLoader.getController();

                setLoadNodeController.setMainFrameController(this);
                setLoadNodeController.setBridgeMain(bridgeMain);

                Stage stage = new Stage();
                stage.setTitle("Selecionar um no de carga");
                stage.setScene(new Scene(root));

                stage.show();
            } catch (IOException ex) {

            }
        }
    }

    Color selectedAngleFirstBarColor;
    Color selectedAngleSecondBarColor;
    int selectedIndex = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showBridge = new ShowBridge(canvas_plane, bridgeMain, this);

        consoleManager = new ConsoleManager(log_console_plane);
        grid = new Grid(this);

        File materialFolder = new File(BridgeManager.getMaterialDataFolder());
        if (materialFolder.exists()) {
            try {
                bridgeMain.bridgeManager.setMaterial(Save.openMaterial(materialFolder));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        bridge_table_view_bar.setCellValueFactory(
                new PropertyValueFactory<>("name"));
        bridge_table_view_size.setCellValueFactory(
                new PropertyValueFactory<>("size"));
        bridge_table_view_force.setCellValueFactory(
                new PropertyValueFactory<>("force"));
        bridge_table_view_wires.setCellValueFactory(
                new PropertyValueFactory<>("wires"));

        bridge_angle_table_bars.setCellValueFactory(
                new PropertyValueFactory<>("firstBar"));
        bridge_angle_table_secondsbars.setCellValueFactory(
                new PropertyValueFactory<>("secondBar"));
        bridge_angle_table_angle.setCellValueFactory(
                new PropertyValueFactory<>("arc"));

        bridge_table_view.setRowFactory(tv -> {
            TableRow<BarTable> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    BarTable rowData = row.getItem();
                    getDown_status().setText(rowData.getName() + " ( " + Math.round(rowData.getSize() * 100) / 100 + "cm )"
                            + " | Forca: " + Math.round(rowData.getForce() * 100) / 100
                            + "N | Numero de fios: " + rowData.getWires());

                    ArrayList<Bar> selectedBars = new ArrayList<>();

                    for (Bar bar : bridgeMain.bridgeManager.getBars())
                        if (bar.getBarName().equals(row.getItem().getName()))
                            selectedBars.add(bar);

                    bridgeMain.bridgeManager.setSelectedBar(selectedBars);
                    updateBridge();
                }
            });
            return row;
        });

        bridge_table_angle_view.setRowFactory(tv -> {
            TableRow<AngleTable> row = new TableRow<>();

            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    AngleTable rowData = row.getItem();
                    Bar firstBar = new Bar();
                    Bar secondBar = new Bar();

                        for (Bar bar : bridgeMain.bridgeManager.getBars()
                        ) {
                            if (bar.getBarName().equals(row.getItem().getFirstBar()) || bar.getBarName().equals(StringFeatures.invert(row.getItem().getFirstBar())))
                                firstBar = bar;
                            if (bar.getBarName().equals(row.getItem().getSecondBar()) || bar.getBarName().equals(StringFeatures.invert(row.getItem().getSecondBar())))
                                secondBar = bar;
                        }

                    ArrayList<Bar> selectedBars = new ArrayList<>();

                    selectedBars.add(firstBar);
                    selectedBars.add(secondBar);

                    bridgeMain.bridgeManager.setSelectedBar(selectedBars);

                    showBridge.showBars();
                    }
            });
            return row;
        });

        menu_zoom_slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(
                    ObservableValue<? extends Number> observableValue,
                    Number oldValue,
                    Number newValue) {

                if(!notChangeValue) {
                    if (newValue.floatValue() <= 50) {
                        bridgeMain.bridgeManager.setZoomCoefficient(
                                0.125f + ((1 - 0.125f) * (newValue.floatValue() / 50))
                        );
                    } else {
                        bridgeMain.bridgeManager.setZoomCoefficient(
                                1f + ((20f - 1f) * ((newValue.floatValue() - 50) / 50))
                        );
                    }
                }
                notChangeValue = false;
                updateBridge();
            }
        });

        URL resource = MainFrameController.class
                .getResource("/com/matheusmarkies/spaghettibridge/resources/bridge-nodes.png");
        changeButtonImage(create_node_button, resource);

        resource = MainFrameController.class
                .getResource("/com/matheusmarkies/spaghettibridge/resources/bridge-bars.png");
        changeButtonImage(create_bar_button, resource);

        resource = MainFrameController.class
                .getResource("/com/matheusmarkies/spaghettibridge/resources/calculate-wires.png");
        changeButtonImage(calculate_wires, resource);

        resource = MainFrameController.class
                .getResource("/com/matheusmarkies/spaghettibridge/resources/assembler-equations.png");
        changeButtonImage(assembler_equations, resource);

        resource = MainFrameController.class
                .getResource("/com/matheusmarkies/spaghettibridge/resources/set-measure.png");
        changeButtonImage(set_measure, resource);
    }

    void changeButtonImage(Button button, URL resource) {
        Image buttonImage = new Image(
                resource.toString()
        );

        ImageView toggleImage = new ImageView(buttonImage);
        button.setGraphic(toggleImage);
    }

    @FXML
    void onMouseClickedEvent(MouseEvent event) {
        ArrayList<Bar> selectedBars = new ArrayList<>();
        bridgeMain.bridgeManager.setSelectedBar(selectedBars);
        updateBridge();
    }

    @FXML
    void setMeasureButtonAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                    "/com/matheusmarkies/spaghettibridge/CreateMeasure.fxml"));
            Parent root = (Parent) fxmlLoader.load();

            CreateMeasureController createMeasureController = fxmlLoader.getController();

            createMeasureController.setMainFrameController(this);
            createMeasureController.setBridgeMain(bridgeMain);

            Stage stage = new Stage();
            stage.setTitle("Criar Cota");
            stage.setScene(new Scene(root));

            //URL resource = com.matheusmarkies.spaghettibridge.resources.Resources.class
            //        .getResource("set-measure.png");
            //Image stageImage = new Image(
            //        resource.toString()
            //);
            //stage.getIcons().add(stageImage);

            stage.setOnCloseRequest(ev -> {
                removeObjectToCanvas(createMeasureController.getPreview());
            });

            stage.show();
        } catch (IOException ex) {

        }
    }

    @FXML
    void setMaterialButtonAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                    "/com/matheusmarkies/spaghettibridge/ConfigMaterial.fxml"));
            Parent root = (Parent) fxmlLoader.load();

            ConfigMaterialController configMaterialController = fxmlLoader.getController();

            configMaterialController.setMainFrameController(this);
            configMaterialController.setBridgeMain(bridgeMain);

            Stage stage = new Stage();
            stage.setTitle("Material");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException ex) {

        }
    }

    @FXML
    void setTestLoadButtonAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                    "/com/matheusmarkies/spaghettibridge/SetTestLoad.fxml"));
            Parent root = (Parent) fxmlLoader.load();

            SetTestLoadController loadController = fxmlLoader.getController();

            loadController.setMainFrameController(this);
            loadController.setBridgeMain(bridgeMain);

            Stage stage = new Stage();
            stage.setTitle("Editar Carga de Teste");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {

        }
    }

    @FXML
    void menuOpitionOpenAction(ActionEvent event) {
        bridgeMain.bridgeManager.reset();

        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        FileFilter bridgeFilter = new FileTypeFilter(".bridge", "Spaghetti Bridge Design Documents");

        jfc.setFileFilter(bridgeFilter);
        int returnValue = jfc.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();

            Bridge bridge = null;
            try {
                bridge = Save.openBridge(selectedFile);

                for (BarSerializable bar : bridge.getBars()) {
                    bridgeMain.bridgeManager.addBar(Bar.barSerializableToBar(bar));
                }
                for (NodeSerializable node : bridge.getNodes()) {
                    bridgeMain.bridgeManager.addNode(node.nodeSerializableToNode(bridgeMain.bridgeManager.getBars()));
                }

                for (Bar bar : bridgeMain.bridgeManager.getBars())
                    for (com.matheusmarkies.spaghettibridge.objects.node.Node node : bridgeMain.bridgeManager.getNodes()) {
                        if (bar.getNodeStart().getNodeName().equals(node.getNodeName()))
                            bar.setNodeStart(node);

                        if (bar.getNodeEnd().getNodeName().equals(node.getNodeName()))
                            bar.setNodeEnd(node);
                    }

                Material material = Save.openMaterial(new File(BridgeManager.getMaterialDataFolder()));
                bridgeMain.bridgeManager.setMaterial(material);

                showBridge.showNodes();
                showBridge.showBars();
                showBridge.showReactions();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void menuOpitionSaveAction(ActionEvent event) {
        ArrayList<BarSerializable> barSerializables = new ArrayList<>();
        for (Bar bar : bridgeMain.bridgeManager.getBars()) {
            barSerializables.add(new BarSerializable(bar.getNodeStart().getNodeName(), bar.getNodeEnd().getNodeName(), bar.getBarName()));
        }
        ArrayList<NodeSerializable> nodeSerializables = new ArrayList<>();
        for (com.matheusmarkies.spaghettibridge.objects.node.Node node : bridgeMain.bridgeManager.getNodes()) {
            NodeSerializable nodeSerializable = new NodeSerializable();
            nodeSerializable.nodeToNodeSerializable(node);
            nodeSerializables.add(nodeSerializable);
        }
        try {
            Save.saveBridge(
                    new Bridge(barSerializables, nodeSerializables)
            );
        } catch (IOException ex) {

        }
    }

    SpaghettiBridgeMain bridgeMain;

    public SpaghettiBridgeMain getBridgeMain() {
        return bridgeMain;
    }

    public void setBridgeMain(SpaghettiBridgeMain bridgeMain) {
        this.bridgeMain = bridgeMain;
    }

    public ShowBridge getShowBridge() {
        return showBridge;
    }

    public ConsoleManager getConsoleManager() {
        return consoleManager;
    }

    public AnchorPane getCanvasPlane() {
        return canvas_plane;
    }

    public void addObjectToCanvas(Node node) {
        canvas_plane.getChildren().add(node);
    }

    public void removeObjectToCanvas(Node node) {
        canvas_plane.getChildren().remove(node);
    }

    public Grid getGrid() {
        return grid;
    }

    public Label getDown_status() {
        return down_status;
    }

    Circle nodePointer = null;

    public void setNodePointer(Circle pointer) {
        this.nodePointer = pointer;
    }
//BarTable barTable = mainFrameController.getBarTableView().getFocusModel().getFocusedItem();

    public TableView<BarTable> getBarTableView() {
        return bridge_table_view;
    }

    public TableView<AngleTable> getBridgeTableAngleView() {
        return bridge_table_angle_view;
    }
}
