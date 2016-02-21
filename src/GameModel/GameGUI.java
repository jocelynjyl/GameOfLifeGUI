package GameModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameGUI extends JComponent {
    private Grid life;
    private JFrame frame;
    private JPanel panel;
    private JPanel buttons;
    private Timer timer;
    private final int GRID_SIZE = 6;


    public GameGUI (int rows, int columns) {
        this.life = new Grid(rows, columns);

    }

    public void update() {
        life.nextGeneration();
    }

    public void paint (Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i< life.getRows(); i++) {
            for (int j = 0; j < life.getColumns(); j++){
                if (life.getCellState(i, j)) {
                    g.setColor (Color.blue);
                    g.fillRect(i*GRID_SIZE, j*GRID_SIZE, GRID_SIZE, GRID_SIZE);
                }
                else {
                    g.setColor (Color.blue);
                    g.drawRect(i*6, j*6, 6, 6);
                }

            }
        }
    }

    public void setup(GameGUI game) {
        this.frame = new JFrame("Game of Life");
        this.panel = new JPanel (new BorderLayout());
        panel.setPreferredSize(new Dimension(400, 400));
        setupButtons();
        setLive(game);

        panel.add(game, BorderLayout.CENTER);
        panel.add(buttons, BorderLayout.SOUTH);
        frame.getContentPane().add(panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void setLive(GameGUI game) {
        game.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int x = e.getX()/GRID_SIZE;
                int y = e.getY()/GRID_SIZE;
                life.setInitial(x, y);
                repaint();
            }
        });
    }

    private void setupButtons() {
        this.buttons = new JPanel (new GridLayout(1, 2));

        JButton start = new JButton ("Start");
        start.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                setupTimer();
                timer.start();
            }
        });
        buttons.add(start);

        JButton stop = new JButton("Stop");
        stop.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                timer.stop();
            }
        });
        buttons.add(stop);
    }

    private void setupTimer() {
        timer = new javax.swing.Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
                repaint();
            }
        });
    }
}
