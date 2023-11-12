/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fishgame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class GamePageLevel2 extends GamePage{
    private int timeLeft = 30;  
    private int chancesLeft;
    private boolean gameOver = false;
    private Player player;
    private ELevel2 elevel2;

    URL bg = this.getClass().getResource("/BGlevel2.png");
    Image bgi = new ImageIcon(bg).getImage();
    URL box3 = this.getClass().getResource("/box3.png");
    Image box3i = new ImageIcon(box3).getImage();
    URL box4 = this.getClass().getResource("/box4.png");
    Image box4i = new ImageIcon(box4).getImage();
    URL box5 = this.getClass().getResource("/box5.png");
    Image box5i = new ImageIcon(box5).getImage();
    URL boxp = this.getClass().getResource("/boxplay.png");
    Image boxpi = new ImageIcon(boxp).getImage();
    
    int initialX = (getWidth() - playerImageWidth) / 2; 
    int initialY = (getHeight() - playerImageHeight) / 2; 
    private char[] answers = {
    'Y', 
    'N',
    'Y'
    };
    private int currentQuestion = 0;  // Track the current question
    private boolean[] answeredCorrectly = new boolean[answers.length];
    private Image[] questionImages = new Image[answers.length];


    public GamePageLevel2(int chancesLeft) {
        setLayout(null);
        this.chancesLeft = chancesLeft;
        loadImageResources();

        player = new Player(initialX+260, initialY+290, 400, 286);
        add(player);
        elevel2 = new ELevel2(810,290 , 300, 231);
        add(elevel2);
        setupButtons();
        questionImages[0] = LoadImageForQuestionIndex(0);
        questionImages[1] = LoadImageForQuestionIndex(1);
        questionImages[2] = LoadImageForQuestionIndex(2);

        gameTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!gameOver) {
                timeLeft--;}
            if (timeLeft <= 0) {
                handleGameOver();
            }
            if (chancesLeft <= 0) {
                    handleGameOver();
                }
               
            }
        });

        gameTimer.start();

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                requestFocusInWindow();
            }
        });

        playerTimer = new Timer(1000/60, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
                player.update();
                elevel2.update();
            }
        });

        playerTimer.start();
        setFocusable(true);
        requestFocus();
        //addKeyListener(new KeyboardInputs(this));
    }
    @Override
    protected void loadImageResources() {
        bgi = LoadSave.GetFishAtlas(LoadSave.BG_LEVEL_2);

        box3i = LoadSave.GetFishAtlas(LoadSave.BOX_3);
        box4i = LoadSave.GetFishAtlas(LoadSave.BOX_4);
        box5i = LoadSave.GetFishAtlas(LoadSave.BOX_5);
        boxpi = LoadSave.GetFishAtlas(LoadSave.BOX_PLAY);
    }

    @Override
        protected void setupButtons() {
        // Create and configure the "Yes" button
        ImageIcon by = new ImageIcon("src/by.png");
        JButton buttonYes = new JButton(by);
        buttonYes.setBounds(390, 100, 145, 84);
        buttonYes.setContentAreaFilled(false);
        buttonYes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAnswer(answers[currentQuestion] == 'Y');
            }
        });
        add(buttonYes);
        
        ImageIcon bn = new ImageIcon("src/bn.png");
        JButton buttonNo = new JButton(bn);
        buttonNo.setBounds(580, 100, 95, 82);
        buttonNo.setContentAreaFilled(false);
        buttonNo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAnswer(answers[currentQuestion] == 'N');
            }
        });
        add(buttonNo);
        }
        
    @Override
    protected Image LoadImageForQuestionIndex(int index) {
    if (index == 0) {
            return LoadSave.GetFishAtlas(LoadSave.BOX_3);
        } else if (index == 1) {
            return LoadSave.GetFishAtlas(LoadSave.BOX_4);
        } else if (index == 2) {
            return LoadSave.GetFishAtlas(LoadSave.BOX_5);
        }else {
            return null;
        }
}
    @Override
    protected boolean allQuestionsAnsweredCorrectly() {
    for (boolean answered : answeredCorrectly) {
        if (!answered) {
            return false;
        }
    }
    return true;
    }
    
    @Override
    protected void checkForLevelTransition() {
    if (player.getX() >= getWidth() - playerImageWidth) {
        gameTimer.stop();
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.getContentPane().remove(this);
        frame.add(new GamePageLevel3(chancesLeft));
        frame.validate();
        }
    }
    
    @Override
    protected void handleAnswer(boolean isCorrect) {
        if (isCorrect) {
        answeredCorrectly[currentQuestion] = true;

        if (currentQuestion < answers.length - 1) {
            currentQuestion++;

            questionImages[currentQuestion] = LoadImageForQuestionIndex(currentQuestion);

            repaint();
        }else if (allQuestionsAnsweredCorrectly() && !gameOver) {
            gameTimer.stop();
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(GamePageLevel2.this);
            frame.getContentPane().remove(GamePageLevel2.this);
            frame.add(new GamePageLevel3(chancesLeft));
            frame.validate();
        }
    } else {
        chancesLeft--;
        if (chancesLeft <= 0) {
            handleGameOver();
        }
    }
}
   
    @Override
    protected void handleGameOver() {
        if (!gameOver) {
            gameOver = true; 
            showLoseScreen();
        
        }
    }
    @Override
    protected void showLoseScreen() {
    if(gameOver){
    JDialog loseDialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(this), "You Lose", true);
    JPanel contentPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon(getClass().getResource("/lose_screen_bg.png"));
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        contentPanel.setLayout(new BorderLayout());
    JPanel buttonPanel = new JPanel(){
        @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon(getClass().getResource("/below.png"));
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
    ImageIcon br = new ImageIcon(getClass().getResource("/restart.png"));
    ImageIcon bb = new ImageIcon(getClass().getResource("/exit.png"));
    JButton restartButton = new JButton(br);
    JButton returnToHomeButton = new JButton(bb);
    restartButton.setBounds(835, 400, 145, 73);
    returnToHomeButton.setBounds(835, 400, 145, 73);
    restartButton.setContentAreaFilled(false);
    returnToHomeButton.setContentAreaFilled(false);

    restartButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            loseDialog.dispose();
            restartGame();
        }
    });
    returnToHomeButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            loseDialog.dispose();
            returnToStartPage();
        }
    });
    buttonPanel.add(restartButton);
    buttonPanel.add(returnToHomeButton);
    
    loseDialog.add(contentPanel, BorderLayout.CENTER);
    loseDialog.add(buttonPanel, BorderLayout.SOUTH);
    
    // Set the dialog size and make it visible
    loseDialog.setSize(600, 400);
    loseDialog.setLocationRelativeTo(null); // Center the dialog
    loseDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    loseDialog.setVisible(true);
    }
}
    
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgi, 0, 0, getWidth(), getHeight(), this);
        g.drawImage(boxpi, 350,50, 400, 250, this);
        //g.drawImage(box1i, 790,50, 500, 250, this);
        
        if (questionImages[currentQuestion] != null) {
        g.drawImage(questionImages[currentQuestion], 790,50, 500, 250,  this);
    }
        
        player.update();
        player.render(g);
        elevel2.update();
        elevel2.render(g);
        checkForLevelTransition();
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 36));
        g.drawString("Time: " + timeLeft, 20, 40);
        g.drawString("Chances: " + chancesLeft, 20, 80);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("LEVEL 2" , 1300, 60);
    
    }

    public Player getPlayer(){
        return player;
    }

   
}
