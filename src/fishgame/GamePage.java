/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fishgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public abstract class GamePage extends JPanel {
    protected Timer gameTimer;
    protected Timer playerTimer;
    protected int timeLeft;
    protected int chancesLeft;
    protected boolean gameOver;
    protected int playerImageWidth;
    protected int playerImageHeight;
    protected int currentQuestion;
    protected boolean[] answeredCorrectly;
    protected Image[] questionImages;
    protected Player player;

    public GamePage() {
    }

    protected abstract void loadImageResources();

    protected abstract void setupButtons();

    protected void handleAnswer(boolean isCorrect) {
    }

    protected void handleGameOver() {
        if (!gameOver) {
            gameOver = true; 
            showLoseScreen();
        }
    }

    protected abstract void showLoseScreen();

    protected void restartGame() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.getContentPane().remove(this);
        frame.add(new GamePageLevel1());
        frame.validate();
    }

    protected void returnToStartPage() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.getContentPane().remove(this);
        frame.add(new Startpage());
        frame.validate();
    }

    protected Image LoadNextQuestionImage() {
        int nextQuestionIndex = currentQuestion + 1;
    if (nextQuestionIndex < questionImages.length) {
        return questionImages[nextQuestionIndex];
    } else {
        return null;
    }
    }

    protected Image LoadImageForQuestionIndex(int index) {
        return null;
    }

    protected boolean allQuestionsAnsweredCorrectly() {
        for (boolean answered : answeredCorrectly) {
        if (!answered) {
            return false;
        }
    }
        return true;
    }

    protected void checkForLevelTransition() {
        if (player.getX() >= getWidth() - playerImageWidth) {
        // Player reached the right border, transition to Level 2
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.getContentPane().remove(this);
        frame.add(new GamePageLevel2(chancesLeft));
        frame.validate();
    }
    }

    @Override
    protected void paintComponent(Graphics g){}
}

/*
    protected void handleKeyPress() {
        player.resetDirBooleans();
        if (player.isLeft()) {
            player.setLeft(true);
        }
        if (player.isRight()) {
            player.setRight(true);
        }
        if (player.isUp()) {
            player.setUp(true);
        }
        if (player.isDown()) {
            player.setDown(true);
        }
    }

    protected void handleKeyRelease() {
        if (player.isLeft()) {
            player.setLeft(false);
        }
        if (player.isRight()) {
            player.setRight(false);
        }
        if (player.isUp()) {
            player.setUp(false);
        }
        if (player.isDown()) {
            player.setDown(false);
        }
    }


    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

    switch (keyCode) {
        case KeyEvent.VK_UP:
            player.moveUp();
            break;
        case KeyEvent.VK_DOWN:
            player.moveDown();
            break;
        case KeyEvent.VK_LEFT:
            player.moveLeft();
            break;
        case KeyEvent.VK_RIGHT:
            player.moveRight();
            break;
        }
        handleKeyPress();
    }

    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

    switch (keyCode) {
        case KeyEvent.VK_UP:
            player.stopMovingUp();
            break;
        case KeyEvent.VK_DOWN:
            player.stopMovingDown();
            break;
        case KeyEvent.VK_LEFT:
            player.stopMovingLeft();
            break;
        case KeyEvent.VK_RIGHT:
            player.stopMovingRight();
            break;
        }
        handleKeyRelease();
    }
*/