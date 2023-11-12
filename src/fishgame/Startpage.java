/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fishgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Startpage extends JPanel{
    private Timer gameTimer;
    URL bg = this.getClass().getResource("/BGlevel1.png");
    Image bgi = new ImageIcon(bg).getImage();
    URL name = this.getClass().getResource("/name.png");
    Image namei = new ImageIcon(name).getImage();
    URL fish = this.getClass().getResource("/fish.png");
    Image fishi = new ImageIcon(fish).getImage();
    ImageIcon bs = new ImageIcon("src/start.png"); 
    JButton buttonstart = new JButton(bs);
    
    public Startpage(){
        setLayout(null);
    loadImageResources();

    buttonstart = createStartButton();
    buttonstart.setVisible(true);
    add(buttonstart);

    }
    
    private void loadImageResources() {
        ImageIcon bgIcon = new ImageIcon(getClass().getResource("/BGlevel1.png"));
        bgi = bgIcon.getImage();

        ImageIcon nameIcon = new ImageIcon(getClass().getResource("/name.png"));
        namei = nameIcon.getImage();
        ImageIcon fishIcon = new ImageIcon(getClass().getResource("/fish.png"));
        fishi = fishIcon.getImage();
    }

    
    private JButton createStartButton() {
        ImageIcon startIcon = new ImageIcon("src/start.png");
        JButton buttonstart = new JButton(startIcon);
        buttonstart.setBounds(835, 400, 400, 55);
        buttonstart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });
        return buttonstart;
    
    }
    
    
   private void startGame() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.getContentPane().remove(this);
        GamePageLevel1 gamePageLevel1 = new GamePageLevel1();
        frame.add(gamePageLevel1);
        frame.validate();
        frame.repaint();
        gamePageLevel1.requestFocusInWindow();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(bgi, 0, 0, getWidth(), getHeight(), this);
        g.drawImage(namei, 380,15,800,205,this);
        g.drawImage(fishi, 320,280,400,286,this);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Please Save Us!");
            frame.setSize(1920, 1080);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            Startpage Startpage = new Startpage();
            frame.add(Startpage);
            frame.setVisible(true);
        });
    }

}

