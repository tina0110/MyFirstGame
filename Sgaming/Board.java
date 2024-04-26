import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Board extends JPanel{
    Timer timer;
    BufferedImage backgroundImage;
    Player player;
    Enemy enemies[] = new Enemy[7];
    public Board() {
        setSize(1800, 920);
        loadBackgroundImage();
        player = new Player();
        
        loadEnemies();
        gameLoop();
        
        bindEvents();
        setFocusable(true);

        

    }
    private void gameOver(Graphics pen){
        
            if(player.outOfScreen()){
            pen.setFont(new Font("times", Font.BOLD,30));
            pen.setColor(Color.GREEN);
            pen.drawString("Game Win", 1700/2, 920/2);
            timer.stop();
            return;
        }
    



        for(Enemy enemy:enemies){
            if(isCollide(enemy)){
            pen.setFont(new Font("times", Font.BOLD,30));
            pen.setColor(Color.RED);
            pen.drawString("Game Over", 1700/2, 920/2);
            timer.stop();
        }
    }
}
    private boolean isCollide(Enemy enemy){
      int xDistance = Math.abs(player.x - enemy.x);
      int yDistance = Math.abs(player.y - enemy.y);
        int maxH = Math.max(player.h, enemy.h);
        int maxW = Math.max(player.w, enemy.w);
        return xDistance <= maxW-150 && yDistance <= maxH-150;

    }
    private void bindEvents(){
        addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_RIGHT){
                player.speed = 5;}
                else if(e.getKeyCode()==KeyEvent.VK_LEFT){
                    player.speed = -5;}
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'keyPressed'");
            }

            @Override
            public void keyReleased(KeyEvent e) {
                player.speed = 0;
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
            }
            
        });

        }
    
    private void loadEnemies(){
        int x = 400;
        int gap = 400;
        int speed = 5;
        for(int i=0; i<enemies.length;i++){
            enemies[i] = new Enemy(x, speed);
            x = x + gap;
            speed = speed +5;
        }
    }
    private void gameLoop(){
        timer = new Timer(50, (e)->repaint());
        timer.start();
    }
    private void loadBackgroundImage(){
        try{
            backgroundImage = ImageIO.read(Board.class.getResource("Starry night Image.png"));
    }
    catch (IOException e){
        System.out.println("background image not found");
        System.exit(1);
        e.printStackTrace();
    }}
    private void printEnemies(Graphics pen){
        for(Enemy enemy:enemies){
            enemy.draw(pen);
            enemy.move();
        }
    }
    public void paintComponent(Graphics pen) {
        //all printing logic will be here
        super.paintComponent(pen);//
        pen.drawImage(backgroundImage,0,0,1800,920,null);
        player.draw(pen);
        player.move();
        printEnemies(pen);
        gameOver(pen);
    }
    
}
