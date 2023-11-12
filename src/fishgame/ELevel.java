/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fishgame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;
import utilz.Constants;

public class ELevel extends JComponent {
    protected BufferedImage[][] animations;
    protected int aniTick, aniIndex, aniSpeed = 30;
    protected int playerAction = Constants.PlayerConstants.IDLE;
    protected float playerSpeed = 3.0f;


    protected float x;
    protected float y;
    protected int width;
    protected int height;

    public ELevel(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        loadAnimations();
    }

    public void update() {
        updateAnimationTick();
    }

    public void render(Graphics g) {}

    protected void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex > Constants.PlayerConstants.GetSpriteAmount(playerAction)) {
                aniIndex = 0;
            }
        }
    }

    protected void loadAnimations() {}
}

class ELevel1 extends ELevel {
    public ELevel1(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(animations[playerAction][aniIndex], (int) x, (int) y, 277, 310, null);
    }

    @Override
    protected void loadAnimations() {
        BufferedImage img = LoadSave.GetFishAtlas(LoadSave.LEVEL1);
        animations = new BufferedImage[1][4];
        for (int j = 0; j < animations.length; j++)
            for (int i = 0; i < animations[j].length; i++)
                animations[j][i] = img.getSubimage(i * 277, j * 310, 277, 310);
    }
}

class ELevel2 extends ELevel {
    public ELevel2(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(animations[playerAction][aniIndex], (int) x, (int) y, 404, 310, null);
    }

    @Override
    protected void loadAnimations() {
        BufferedImage img = LoadSave.GetFishAtlas(LoadSave.LEVEL2);
        animations = new BufferedImage[1][4];
        for (int j = 0; j < animations.length; j++)
            for (int i = 0; i < animations[j].length; i++)
                animations[j][i] = img.getSubimage(i * 404, j * 310, 404, 310);
    }
}

class ELevel3 extends ELevel {
    public ELevel3(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void render(Graphics g){
        g.drawImage(animations[playerAction][aniIndex], (int)x, (int)y,320,272, null);
    }
    
       
    @Override
        protected void loadAnimations() {
            BufferedImage img = LoadSave.GetFishAtlas(LoadSave.LEVEL3);
         
            animations = new BufferedImage[1][3];        
                for(int j = 0; j < animations.length; j++)
                    for(int i = 0; i < animations[j].length; i++)
                        animations[j][i] = img.getSubimage(i*320, j*272, 320, 272);

    }
}


