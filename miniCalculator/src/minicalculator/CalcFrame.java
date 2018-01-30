
package minicalculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 *
 * @author hasib
 */
public class CalcFrame {
       public CalcFrame()
       {
           JFrame frame=new JFrame("Calculator");
           JTextField f1,f2,f3;
           JButton b1,b2;
           
           
           
           f1=new JTextField();
           f2=new JTextField();
           f3=new JTextField();
           f1.setBounds(100, 100, 50, 50);
           f2.setBounds(100, 200, 50, 50);
           f3.setBounds(100,400,50,50);
           
           b1=new JButton("+");
           b2=new JButton("-");
           
           b1.setBounds(200,400,100,50);
           b2.setBounds(200,500,100,50);
           
           
           
           frame.add(f1);
           frame.add(f2);
           frame.add(f3);
           frame.add(b1);
           frame.add(b2);
           
           
           frame.setSize(500, 400);
           frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           frame.setLayout(null);
           frame.setVisible(true);
           
           b1.addActionListener((ActionEvent e) -> {
               String n1=f1.getText();
           String n2=f2.getText();
           
           int a=Integer.parseInt(n1);
           int b=Integer.parseInt(n2);
               
               int res=a+b;
              
               String r=String.valueOf(res);
               f3.setText(r);
           });
           b2.addActionListener((ActionEvent e) -> {
               String n1=f1.getText();
                String n2=f2.getText();

                int a=Integer.parseInt(n1);
                int b=Integer.parseInt(n2);
               
               int res=a-b;
               String r=String.valueOf(res);
               f3.setText(r);
           });
           
           
           
       }
}
