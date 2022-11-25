import acm.graphics.*;
import acm.program.GraphicsProgram;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main extends GraphicsProgram {

    static GCanvas canvas;
    static JFrame myProgram = new JFrame();

    static final ArrayList<String> SHAPES = new ArrayList<>(
            List.of("line", "square", "circle")
    );

    static final ArrayList<String> COLORS = new ArrayList<>(
            List.of("red", "blue", "green")
    );

    static final ArrayList<String> SIZES = new ArrayList<>(
            List.of("little", "medium", "big")
    );


    public static void main(String[] args) {

        String shapeWanted = getInput("shape", "Select which shape you want to draw: line, square or circle");
        String colorWanted = getInput("color", "Select which color you want to fill your shape with: red, green or blue");
        String sizeWanted = getInput("size", "Select which size you want your shape to be: little, medium or big");
        draw(shapeWanted, colorWanted, sizeWanted);
    }

    private static String getInput(String mode, String message) {
        Scanner sc = new Scanner(System.in);
        System.out.println(message);
        String inputReceived = sc.nextLine();
        while (checkInputReceived(mode, inputReceived)) {
            System.out.println("You misspelled it. Please, write it correctly.");
            System.out.println(message);
            inputReceived = sc.nextLine();
        }
        return inputReceived;
    }

    private static boolean checkInputReceived(String mode, String inputReceived) {
        return switch (mode) {
            case "shape" -> !SHAPES.contains(inputReceived);
            case "color" -> !COLORS.contains(inputReceived);
            default -> !SIZES.contains(inputReceived);
        };
    }

    private static void draw(String shapeWanted, String colorWanted, String sizeWanted) {
        GObject figure = drawShape(shapeWanted);
        GObject paintedFigure = paintFigure(colorWanted, figure);
        GObject resizedFigure = resizeFigure(paintedFigure, sizeWanted);
        setup(resizedFigure);
    }


    private static GObject drawShape(String shapeWanted) {
        return switch (shapeWanted) {
            case "square" -> new GRect(5.0, 5.0);
            case "circle" -> new GOval(5.0, 5.0);
            default -> new GLine(1.0, 1.0, 2.0, 2.0);
        };
    }

    private static GObject paintFigure(String colorWanted, GObject figure) {
        switch (colorWanted) {
            case "red" -> figure.setColor(Color.RED);
            case "green" -> figure.setColor(Color.GREEN);
            default -> figure.setColor(Color.BLUE);
        }
        return figure;
    }
    private static GObject resizeFigure(GObject figure, String sizeWanted) {
        if(figure instanceof GLine) {
            return resizeLine((GLine) figure, sizeWanted);
        } else if (figure instanceof GRect) {
            return resizeSquare((GRect) figure, sizeWanted);
        } else {
            return resizeCircle((GOval) figure, sizeWanted);
        }
    }


    private static GObject resizeCircle(GOval circle, String sizeWanted) {
        switch (sizeWanted) {
            case "little" -> {
                circle.setSize(50.0, 50.0);
                return circle;
            }
            case "medium" -> {
                circle.setSize(100.0, 100.0);
                return circle;
            }
            default -> {
                circle.setSize(150.0, 150.0);
                return circle;
            }
        }
    }

    private static GObject resizeSquare(GRect square, String sizeWanted) {
        switch (sizeWanted) {
            case "little" -> {
                square.setSize(50.0, 50.0);
                return square;
            }
            case "medium" -> {
                square.setSize(100.0, 100.0);
                return square;
            }
            default -> {
                square.setSize(150.0, 150.0);
                return square;
            }
        }
    }

    private static GObject resizeLine(GLine line, String sizeWanted) {
        switch (sizeWanted) {
            case "little" -> {
                line.setEndPoint(50.0, 50.0);
                return line;
            }
            case "medium" -> {
                line.setEndPoint(100.0, 100.0);
                return line;
            }
            default -> {
                line.setEndPoint(150.0, 150.0);
                return line;
            }
        }
    }

    public static void setup(GObject resizedFigure) {
        myProgram.setDefaultCloseOperation(EXIT_ON_CLOSE);
        myProgram.setVisible(true);
        myProgram.setTitle("Your figure");
        myProgram.setSize(600, 400);
        canvas = new GCanvas();
        myProgram.add(canvas);
        canvas.add(resizedFigure);
    }

}