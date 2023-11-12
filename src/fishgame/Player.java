/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fishgame;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import utilz.Constants;
import static utilz.Constants.PlayerConstants.GetSpriteAmount;
import static utilz.Constants.PlayerConstants.IDLE;

public class Player extends ELevel {
    private int aniSpeed = 100;

    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void update() {
        updateAnimationTick();

    }
    
    @Override
    public void render(Graphics g){
        g.drawImage(animations[playerAction][aniIndex], (int)x, (int)y,400,286, null);
    }
  
    @Override
    protected void loadAnimations() {
            BufferedImage img = LoadSave.GetFishAtlas(LoadSave.PLAYER);
         
            animations = new BufferedImage[1][2];        
                for(int j = 0; j < animations.length; j++)
                    for(int i = 0; i < animations[j].length; i++)
                        animations[j][i] = img.getSubimage(i*400, j*286, 400, 286);

    }
    
    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
  /*
    public void resetDirBooleans(){
        left = false;
        right = false;
        up = false;
        down = false;
    
    }
    
    private boolean movingUp = false;
    private boolean movingDown = false;
    private boolean movingLeft = false;
    private boolean movingRight = false;

    public void moveUp() {
        movingUp = true;
    }

    public void stopMovingUp() {
        movingUp = false;
    }

    public void moveDown() {
        movingDown = true;
    }

    public void stopMovingDown() {
        movingDown = false;
    }

    public void moveLeft() {
        movingLeft = true;
    }

    public void stopMovingLeft() {
        movingLeft = false;
    }

    public void moveRight() {
        movingRight = true;
    }

    public void stopMovingRight() {
        movingRight = false;
    }
    
    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }
   */
    
}
