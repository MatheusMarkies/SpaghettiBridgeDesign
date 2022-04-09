package com.matheusmarkies.spaghettibridge.utilities;

import com.matheusmarkies.spaghettibridge.main.view.ShowBridge;
import javafx.scene.paint.Color;

public class BarColorController {

    public static Color STANDARD_NEUTRAL = Color.DARKSLATEBLUE;
    public static Color STANDARD_TENSION = Color.DARKSLATEBLUE;
    public static Color STANDARD_COMPRESSION = Color.DARKSLATEBLUE;

    public static Color FORCES_NEUTRAL = Color.DARKSLATEGRAY;
    public static Color FORCES_TENSION = Color.GREENYELLOW;
    public static Color FORCES_COMPRESSION = Color.MEDIUMPURPLE;

    public static Color VECTORS_NEUTRAL = Color.DARKMAGENTA;
    public static Color VECTORS_TENSION = Color.DODGERBLUE;
    public static Color VECTORS_COMPRESSION = Color.CORAL;

    public static Color FREEBODY_NEUTRAL = Color.MEDIUMVIOLETRED;
    public static Color FREEBODY_TENSION = Color.MEDIUMVIOLETRED;
    public static Color FREEBODY_COMPRESSION = Color.MEDIUMVIOLETRED;

    public static Color EXPLODEDVIEW_NEUTRAL = Color.DARKSLATEGRAY;
    public static Color EXPLODEDVIEW_TENSION = Color.GREENYELLOW;
    public static Color EXPLODEDVIEW_COMPRESSION = Color.MEDIUMPURPLE;

    public static Color SELECTED = Color.CRIMSON;

    public static Color getTensionColor(ShowBridge.BarView viewMode){
        switch (viewMode){
            case Standard:
                return STANDARD_TENSION;
            case Forces:
                return FORCES_TENSION;
            case Vectors:
                return VECTORS_TENSION;
            case FreeBody:
                return FREEBODY_TENSION;
            case ExplodedView:
                return EXPLODEDVIEW_TENSION;
        }
        return Color.BLUE;
    }
    public static Color getCompressionColor(ShowBridge.BarView viewMode){
        switch (viewMode){
            case Standard:
                return STANDARD_COMPRESSION;
            case Forces:
                return FORCES_COMPRESSION;
            case Vectors:
                return VECTORS_COMPRESSION;
            case FreeBody:
                return FREEBODY_COMPRESSION;
            case ExplodedView:
                return EXPLODEDVIEW_COMPRESSION;
        }
        return Color.BLUE;
    }
    public static Color getNeutralColor(ShowBridge.BarView viewMode){
        switch (viewMode){
            case Standard:
                return STANDARD_NEUTRAL;
            case Forces:
                return FORCES_NEUTRAL;
            case Vectors:
                return VECTORS_NEUTRAL;
            case FreeBody:
                return FREEBODY_NEUTRAL;
            case ExplodedView:
                return EXPLODEDVIEW_NEUTRAL;
        }
        return Color.BLUE;
    }
}
