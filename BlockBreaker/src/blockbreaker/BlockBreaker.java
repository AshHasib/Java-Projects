package blockbreaker;
import javax.swing.JFrame;


public class BlockBreaker {

    
    public static void main(String[] args) {
        JFrame myFrame=new JFrame("Block Breaker");
        
        myFrame.setBounds(10,10,700,600);
        myFrame.setResizable(false);
        myFrame.setVisible(true);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GamePlay game=new GamePlay();
        myFrame.add(game);
        
    }
    
}
