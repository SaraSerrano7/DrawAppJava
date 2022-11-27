import acm.graphics.*;
import acm.program.GraphicsProgram;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main extends GraphicsProgram {
    /**
     * Java class to ask the user to draw a figure and show it.
     */

    static GCanvas canvas;
    static JFrame myProgram = new JFrame();

    static final ResourceBundle i18n
            = ResourceBundle.getBundle("i18n.translations");

    static final ArrayList<String> SHAPES = new ArrayList<>(
            List.of(t(("line")), t(("square")), t(("circle")))
    );

    static final ArrayList<String> COLORS = new ArrayList<>(
            List.of(t(("red")), t(("blue")), t(("green")))
    );

    static final ArrayList<String> SIZES = new ArrayList<>(
            List.of(t(("little")), t(("medium")), t(("big")))
    );

    /**
     * Method to do the translations of the hardcoded Strings.
     *
     * @param message String to translate
     * @return internationalization of the String message.
     */
    private static String t(String message) {
        return i18n.getString(message);
    }

    /**
     * Program to ask how the user wants to draw a shape and then the figure is shown.
     * Here, the strings "mode" for getInput() are not internationalized because they are not printed nor scanned.
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        String shapeWanted = getInput("shape", t("SELECT_SHAPE"));
        String colorWanted = getInput("color", t(("SELECT_COLOR")));
        String sizeWanted = getInput("size", t(("SELECT_SIZE")));
        draw(shapeWanted, colorWanted, sizeWanted);
    }

    /**
     * Method to get either the shape, color or size desired by the user from the keyboard input.
     * In case it is misspelled, we ask the user to input the answer again.
     * @param mode String that indicates which question we are getting the answer to
     * @param message String containing the question we want to ask
     * @return String input received from the user.
     */
    private static String getInput(String mode, String message) {
        Scanner sc = new Scanner(System.in);
        System.out.println(message);
        String inputReceived = sc.nextLine().toLowerCase();
        while (checkInputReceived(mode, inputReceived)) {
            System.out.println(t(("MISSPELL")));
            System.out.println(message);
            inputReceived = sc.nextLine();
        }
        return inputReceived;
    }

    /**
     * Method that checks if the input received by user is in a correct format or not.
     * @param mode String that indicates which is the question.
     * @param inputReceived String that indicates what is the user's answer
     * @return Boolean indicating the correctness of the user's answer.
     */
    private static boolean checkInputReceived(String mode, String inputReceived) {
        return switch (mode) {
            case "shape" -> !SHAPES.contains(inputReceived);
            case "color" -> !COLORS.contains(inputReceived);
            default -> !SIZES.contains(inputReceived);
        };
    }

    /**
     * Method to draw, color and resize the figure desired by the user and setting up the canvas needed.
     * @param shapeWanted String that contains which is the shape desired by user.
     * @param colorWanted String that contains which is the color desired by user.
     * @param sizeWanted String that contains which is the size desired by user.
     */
    private static void draw(String shapeWanted, String colorWanted, String sizeWanted) {
        GObject figure = drawShape(shapeWanted);
        GObject paintedFigure = paintFigure(colorWanted, figure);
        GObject resizedFigure = resizeFigure(paintedFigure, sizeWanted);
        setup(resizedFigure);
    }


    /**
     * Method to draw the shape.
     * @param shapeWanted String that indicates the shape desired by user.
     * @return a GObject that depending on shapeWanted content, can be a line, square or circle.
     */
    private static GObject drawShape(String shapeWanted) {
        
        if(shapeWanted.equals(t("square"))) {
            return new GRect(5.0, 5.0);
        } else if(shapeWanted.equals(t("circle"))) {
            return new GOval(5.0, 5.0);
        } else {
            return new GLine(1.0, 1.0, 2.0, 2.0);
        }
    }

    /**
     * Method to paint the figure drawn for the user.
     * @param colorWanted String that indicates which is the color for the figure.
     * @param figure The GObject that will be drawn and shown in the canvas, ready to be colored.
     * @return The GObject painted with the desired color.
     */
    private static GObject paintFigure(String colorWanted, GObject figure) {
        
        if(colorWanted.equals(t("red"))) {
            figure.setColor(Color.RED);
        } else if(colorWanted.equals(t("green"))) {
            figure.setColor(Color.GREEN);
        } else {
            figure.setColor(Color.BLUE);
        }
        
        return figure;
    }

    /**
     * Method to change the size of the figure drawn for the user, depending on the type of figure we are dealing with.
     * @param figure GObject that is about to be resized.
     * @param sizeWanted String containing the size the figure will be.
     * @return GObject as the resized figure.
     */
    private static GObject resizeFigure(GObject figure, String sizeWanted) {
        if(figure instanceof GLine) {
            return resizeLine((GLine) figure, sizeWanted);
        } else if (figure instanceof GRect) {
            return resizeSquare((GRect) figure, sizeWanted);
        } else {
            return resizeCircle((GOval) figure, sizeWanted);
        }
    }


    /**
     * Method to change the size of a circle.
     * @param circle GOval object as the figure desired by the user to be drawn and displayed.
     * @param sizeWanted String that indicates which will the size of the figure be.
     * @return The circle resized and treated with an uppercast.
     */
    private static GObject resizeCircle(GOval circle, String sizeWanted) {
        
        if(sizeWanted.equals(t("little"))) {
            circle.setSize(50.0, 50.0);
            return circle;
        } else if(sizeWanted.equals(t("medium"))) {
            circle.setSize(100.0, 100.0);
            return circle;
        } else {
            circle.setSize(150.0, 150.0);
            return circle;
        }
    }

    /**
     * Method to change the size of a square.
     * @param square GOval object as the figure desired by the user to be drawn and displayed.
     * @param sizeWanted String that indicates which will the size of the figure be.
     * @return The square resized and treated with an uppercast.
     */
    private static GObject resizeSquare(GRect square, String sizeWanted) {

        if(sizeWanted.equals(t("little"))) {
            square.setSize(50.0, 50.0);
            return square;
        } else if(sizeWanted.equals(t("medium"))) {
            square.setSize(100.0, 100.0);
            return square;
        } else {
            square.setSize(150.0, 150.0);
            return square;
        }
    }

    /**
     * Method to change the size of a line.
     * @param line GOval object as the figure desired by the user to be drawn and displayed.
     * @param sizeWanted String that indicates which will the size of the figure be.
     * @return The line resized and treated with an uppercast.
     */
    private static GObject resizeLine(GLine line, String sizeWanted) {
        
        if(sizeWanted.equals(t("little"))) {
            line.setEndPoint(50.0, 50.0);
            return line;
        } else if (sizeWanted.equals(t("medium"))) {
            line.setEndPoint(100.0, 100.0);
            return line;
        } else {
            line.setEndPoint(150.0, 150.0);
            return line;
        }
    }

    /**
     * Method to set up and configure the canvas where the figure will be displayed.
     * @param resizedFigure GObject as the figure to be displayed.
     */
    public static void setup(GObject resizedFigure) {
        myProgram.setDefaultCloseOperation(EXIT_ON_CLOSE);
        myProgram.setVisible(true);
        myProgram.setTitle(t(("TITLE")));
        myProgram.setSize(600, 400);
        canvas = new GCanvas();
        myProgram.add(canvas);
        canvas.add(resizedFigure);
    }

}