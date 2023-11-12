/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fishgame;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class LoadSave {
    
    public static final String PLAYER = "fish1.png";
    public static final String PLAYERBG = "fish.png";
    public static final String BG_LEVEL_1 = "BGlevel1.png";
    public static final String BG_LEVEL_2 = "BGlevel2.png";
    public static final String BG_LEVEL_3 = "BGlevel3.png";
    public static final String BOX_1 = "box1.png";
    public static final String BOX_2 = "box2.png";
    public static final String BOX_3 = "box3.png";
    public static final String BOX_4 = "box4.png";
    public static final String BOX_5 = "box5.png";
    public static final String BOX_6 = "box6.png";
    public static final String BOX_7 = "box7.png";
    public static final String BOX_8 = "box8.png";
    public static final String BOX_9 = "box9.png";
    public static final String BOX_10 = "box10.png";
    public static final String BOX_PLAY = "boxplay.png";
    public static final String LEVEL1 = "level1.png";//300 337
    public static final String LEVEL2 = "level2.png";
     public static final String LEVEL3 = "level3.png";
    
    public static BufferedImage GetFishAtlas(String fileName){
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                is.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        return img;
    }
}