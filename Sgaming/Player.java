import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Player extends Sprite {
   
    public Player(){
        w = 200;
        h = 200;
        x = 200;
        y = 600;
        image = new ImageIcon(Player.class.getResource("run2.gif"));
    }
    public void move()
{
    x = x + speed;
}   
public boolean outOfScreen(){
    return x>1500;

}
}
