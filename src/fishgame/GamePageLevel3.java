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

public class GamePageLevel3 extends GamePage {
    private int timeLeft = 50;
    private int chancesLeft;
    private boolean gameOver = false;
    private Player player;
    private ELevel3 elevel3;

    URL bg = this.getClass().getResource("/BGlevel3.png");
    Image bgi = new ImageIcon(bg).getImage();
    URL box6 = this.getClass().getResource("/box6.png");
    Image box6i = new ImageIcon(box6).getImage();
    URL box7 = this.getClass().getResource("/box7.png");
    Image box7i = new ImageIcon(box7).getImage();
    URL box8 = this.getClass().getResource("/box8.png");
    Image box8i = new ImageIcon(box8).getImage();
    URL box9 = this.getClass().getResource("/box9.png");
    Image box9i = new ImageIcon(box9).getImage();
    URL box10 = this.getClass().getResource("/box10.png");
    Image box10i = new ImageIcon(box10).getImage();
    URL boxp = this.getClass().getResource("/boxplay.png");
    Image boxpi = new ImageIcon(boxp).getImage();
    
    int initialX = (getWidth() - playerImageWidth) / 2; 
    int initialY = (getHeight() - playerImageHeight) / 2; 
    private char[] answers = {
    'Y', 
    'Y',
    'N',
    'N',
    'Y'  
    };
    private int currentQuestion = 0;  // Track the current question
    private boolean[] answeredCorrectly = new boolean[answers.length];
    private Image[] questionImages = new Image[answers.length];


    public GamePageLevel3(int chancesLeft) {
        setLayout(null);
        this.chancesLeft = chancesLeft;
        // Load background and box images
        loadImageResources();

        player = new Player(initialX+260, initialY+290, 400, 286);
        add(player);
        elevel3 = new ELevel3(900,300 , 300, 272);
        add(elevel3);
        setupButtons();
        questionImages[0] = LoadImageForQuestionIndex(0);
        questionImages[1] = LoadImageForQuestionIndex(1);
        questionImages[2] = LoadImageForQuestionIndex(2);
        questionImages[3] = LoadImageForQuestionIndex(3);
        questionImages[4] = LoadImageForQuestionIndex(4);

        gameTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!gameOver) {
                timeLeft--;
                }
            if (timeLeft <= 0) {
                handleGameOver();
            }
            if (chancesLeft <= 0) {
                    handleGameOver();
                }
                /*if (levelCompleted()) {
                    showWinScreen();
                }*/
            }
        });

        gameTimer.start();

        setFocusable(true);
        requestFocus();
        //addKeyListener(new KeyboardInputs(this));

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
                elevel3.update();
            }
        });

        playerTimer.start();
    }
    @Override
    protected void loadImageResources() {
        // Load background image
        bgi = LoadSave.GetFishAtlas(LoadSave.BG_LEVEL_3);

        // Load box images
        box6i = LoadSave.GetFishAtlas(LoadSave.BOX_6);
        box7i = LoadSave.GetFishAtlas(LoadSave.BOX_7);
        box8i = LoadSave.GetFishAtlas(LoadSave.BOX_8);
        box9i = LoadSave.GetFishAtlas(LoadSave.BOX_9);
        box10i = LoadSave.GetFishAtlas(LoadSave.BOX_10);
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
            return LoadSave.GetFishAtlas(LoadSave.BOX_6);
        } else if (index == 1) {
            return LoadSave.GetFishAtlas(LoadSave.BOX_7);
        } else if (index == 2) {
            return LoadSave.GetFishAtlas(LoadSave.BOX_8);
        }else if (index == 3) {
            return LoadSave.GetFishAtlas(LoadSave.BOX_9);
        } else if (index == 4) {
            return LoadSave.GetFishAtlas(LoadSave.BOX_10);
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
        showWinScreen();
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
            showWinScreen();
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
    private void showWinScreen() {
    if(!gameOver){
    JDialog WinDialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(this), "You Win", true);
    JPanel contentPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon(getClass().getResource("/win_screen_bg.png"));
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
            WinDialog.dispose();
            restartGame();
        }
    });

    returnToHomeButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            WinDialog.dispose();
            returnToStartPage();
        }
    });

    buttonPanel.add(restartButton);
    buttonPanel.add(returnToHomeButton);
    
    WinDialog.add(contentPanel, BorderLayout.CENTER);
    WinDialog.add(buttonPanel, BorderLayout.SOUTH);
    
    // Set the dialog size and make it visible
    WinDialog.setSize(600, 400);
    WinDialog.setLocationRelativeTo(null); // Center the dialog
    WinDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    WinDialog.setVisible(true);
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
        elevel3.update();
        elevel3.render(g);
        checkForLevelTransition();
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 36));
        g.drawString("Time: " + timeLeft, 20, 40);
        g.drawString("Chances: " + chancesLeft, 20, 80);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("LEVEL 3" , 1300, 60);
    
    }

    public Player getPlayer(){
        return player;
    }

   
}
