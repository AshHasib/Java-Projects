
package blockbreaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;


public class GamePlay extends JPanel implements KeyListener, ActionListener{
    private boolean play=false; //initializing the game state
    private int score=0; //game score
    private int totalBricks=21;
    private Timer timer;
    private int delay=8;
    
    private int playerX=310;
    private int ballPositionX=120;
    private int ballPositionY=350;
    private int ballXDir=-1;
    private int ballYDir=-2;
    private MapGenerator map; //bricks
    
    
    public GamePlay()
    {
        map=new MapGenerator(3, 7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer=new Timer(delay,this);
        timer.start();
        
    }
    public void paint(Graphics g)
    {
        //this is the background
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);
        
        
        //these are maps
        map.draw((Graphics2D)g);
        
        //these are the borders
        g.setColor(Color.red);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691,0,3, 592);
        
        
        //this is a simple prompt
        g.setColor(Color.white);
        g.setFont(new Font("MONOSPACE",Font.BOLD,25));
        g.drawString("Press any time ESC to exit", 20, 30);
        
        //this is for the score
        g.setColor(Color.white);
        g.setFont(new Font("MONOSPACE",Font.BOLD,25));
        g.drawString("Score : "+score, 500, 30);
        
        
        //this is the pedal
        g.setColor(Color.yellow);
        g.fillRect(playerX,550,100,10);
        
        //this is the ball
        g.setColor(Color.red);
        g.fillOval(ballPositionX,ballPositionY,20,20);
        
        if(totalBricks<=0)
        {
            play=false;
            ballXDir=0;
            ballYDir=0;
            g.setColor(Color.RED);
            
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("YOU WON. Score : "+score, 190, 300);
            
            g.setFont(new Font("serif",Font.BOLD,20));
            g.drawString("Press ENTER to restart/ESC to exit", 230, 350);
            
        }
        
        if(ballPositionY>570)
        {
            play=false;
            ballXDir=0;
            ballYDir=0;
            g.setColor(Color.RED);
            
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("GAME OVER.", 190, 300);
            
            g.setFont(new Font("serif",Font.BOLD,20));
            g.drawString("Press ENTER to restart/ESC to exit", 230, 350);
            
            
        }
        
    }
    //abstract methods of KeyListener
    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    @Override
    public void keyReleased(KeyEvent e) {
       
    }
    
    
    @Override
    public void keyPressed(KeyEvent e) {
        if((e.getKeyCode()==KeyEvent.VK_RIGHT)||(e.getKeyCode()==KeyEvent.VK_D))
        {
            if(playerX>=600)
            {
                playerX=600;
            }
            else
            {
                //move right
                moveRight();
            }
        }
        if((e.getKeyCode()==KeyEvent.VK_LEFT)||(e.getKeyCode()==KeyEvent.VK_A))
        {
            if(playerX<10)
            {
                playerX=10;
            }
            else
            {
                //move left
                moveLeft();
            }
        }
        
        if(e.getKeyCode()==KeyEvent.VK_ENTER)
        {
            if(!play)
            {
                play=true;
                ballPositionX=120;
                ballPositionY=350;
                ballXDir=-1;
                ballYDir=-2;
                playerX=310;
                score=0;
                totalBricks=21;
                map=new MapGenerator(3, 7);
                
                
                repaint();
            }
        }
        
        //anytime exit
        if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
        {
            System.exit(0);
        }
    }

    

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        
        //the game will start if any of the arrow keys is pressed
        if(play)
        {
            //code for the intersection between ball and the pedal
            if(new Rectangle(ballPositionX,ballPositionY,20,20).intersects(new Rectangle(playerX,550,100,8)))
            {
                ballYDir=-ballYDir;
            }
            
            A:for(int i=0;i<map.map.length;i++)
            {
                for(int j=0;j<map.map[0].length;j++)
                {
                    if(map.map[i][j]>0)
                    {
                        int brickX=j*map.brickWidth+80;
                        int brickY=i*map.brickHeight+50;
                        int brickWidth=map.brickWidth;
                        int brickHeight=map.brickHeight;
                        
                        Rectangle rect=new Rectangle(brickX,brickY,brickWidth,brickHeight);
                        Rectangle ballRect=new Rectangle(ballPositionX,ballPositionY,20,20);
                        
                        Rectangle brickRect=rect;
                        
                        if(ballRect.intersects(brickRect))
                        {
                            map.setBrickValue(0,i,j);
                            totalBricks--;
                            score+=5; //if ball hits a brick
                            
                            if(ballPositionX+19<=brickRect.x||ballPositionX+1>=brickRect.x+brickRect.width)
                            {
                                ballXDir=-ballXDir;
                            }
                            else
                            {
                                ballYDir=-ballYDir;
                            }
                            break A;
                        }
                    }
                }
            }
            
            //normal movement of the ball
            ballPositionX+=ballXDir;
            ballPositionY+=ballYDir;
            
            //conditions if the ball touches the border
            if(ballPositionX<0)
            {
                ballXDir=-ballXDir; //reverse the direction
            }
            if(ballPositionY<0)
            {
                ballYDir=-ballYDir;//reverse the direction
            }
            if(ballPositionX>670)
            {
                ballXDir=-ballXDir;//reverse the direction
            }
        }
        
        repaint();//calling the paint function
        //in other words, moiving the objects, like a loop
    }
    
    //code for moving the pedal right
    public void moveRight()
    {
        play=true;
        playerX+=20;
    }
    //code for moving the pedal left
    public void moveLeft()
    {
        play=true;
        playerX-=20;
    }
    
}
