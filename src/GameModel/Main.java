package GameModel;

/* Demo of a simple Blinker (period 2) in the Game of Life
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {



    public static void main (String[] args) {
        final GameGUI game = new GameGUI (800, 800);
        game.setup(game);

    }
}
