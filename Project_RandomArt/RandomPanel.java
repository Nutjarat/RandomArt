import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
/**
 * Class to print the random color.
 * 
 * @author Nutjarat Chutiphongphimol ID53270337 
 * @version 07/11/2013
 */

public class RandomPanel extends JPanel{
    private static final int NUM_SHADES = 256;
    private Color[] grayscales;
    private boolean color;
    private RandomExpression exp;
    private RandomExpression[] colorExp;

    // indices into colorExp array
    private static final int RED = 0;
    private static final int GREEN = 1;
    private static final int BLUE = 2;
    int r, gr, b;
    int shade;

    // create 256 gray scale colors to avoid lots
    // of garbage color objects
    public RandomPanel(){
          
        setPreferredSize(new Dimension(400,400));
        //exp = new RandomExpression(); 
        //exp = new RandomExpression("xxACSSxCAyCyxASASCAyCCAyyyAAxMSxCxCAxSySMMCMCSMSCS");

        // if using 3 different functions for color
        colorExp = new RandomExpression[3];  
        setToColor();
    }

    /**public void setToGray(){
        exp = new RandomExpression();
        color = false;
    } **/

    public void setToColor(){
        for(int i = 0; i < colorExp.length; i++)
            colorExp[i] = new RandomExpression();
        color = true;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        final int MAX_X = getWidth();
        final int MAX_Y = getHeight();
        double xInc = 10.0 / MAX_X;
        double yInc = 10.0 / MAX_Y;
        double xVal = -1.0;
        double yVal = -1.0;     
        for(int x = 0; x < MAX_X; x++){
            yVal = -1.0;
            for(int y = 0; y < MAX_Y; y++){
                    r = getShade(xVal, yVal, colorExp[RED]);
                    gr = getShade(xVal, yVal, colorExp[GREEN]);
                    b = getShade(xVal, yVal, colorExp[BLUE]);
                    g2.setColor(new Color(r, gr, b));
                
                Rectangle2D pixel = new Rectangle2D.Double(x, y, 1, 1);
                g2.fill(pixel);
                yVal += yInc;
            }
            xVal += xInc;
        }
        
            System.out.println("red: " + colorExp[RED]);
            System.out.println("green: " + colorExp[GREEN]);
            System.out.println("blue: " + colorExp[BLUE]);
            System.out.println("red: " + r);
            System.out.println("green: " + gr);
            System.out.println("blue: " + b);
       
    }

    private int getShade(double x, double y, RandomExpression exp){
        double val = exp.getResult(x, y);
        int result = (int)((val + 1.0) / 2.0 * NUM_SHADES);
        result = (result == 256) ? 255 : result;
        assert 0 <= result && result < NUM_SHADES : result + " " + val;
        return result;
    }

}
