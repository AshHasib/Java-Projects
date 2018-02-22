
package medisoft;




import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;


public class PrintFile extends JFrame{
    public PrintFile()
    {
        
    }
    
    
    JTextArea printArea;
    JButton print,cancel;
    
    public PrintFile(String name,String age,String problem, String cure)
    {
        printArea=new JTextArea();
        printArea.setBounds(10, 10, 665, 500);
        add(printArea);
        
        
        String finalText=new String("Name :" +name);
        finalText=finalText.concat("\n");
        finalText=finalText.concat("\n");
        finalText=finalText.concat("\n");
        finalText=finalText.concat("Age : "+age);
        finalText=finalText.concat("\n");
        finalText=finalText.concat("\n");
        finalText=finalText.concat("\n");
        finalText=finalText.concat("Problem : "+ problem);
        finalText=finalText.concat("\n");
        finalText=finalText.concat("\n");
        finalText=finalText.concat("\n");
        finalText=finalText.concat("Prescription : "+cure);
        
        printArea.setText(finalText);
        
        
        print=new JButton("Print");
        cancel=new JButton("Cancel");
        
        print.setBounds(100, 520, 100, 30);
        add(print);
        
        print.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                try
                {
                    boolean complete=printArea.print();
                    
                    if(complete)
                    {
                        JOptionPane.showMessageDialog(null, "Printing Successful");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Printing","Printer",JOptionPane.ERROR_MESSAGE);
                    }
                }
                catch(PrinterException e)
                {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
            
        });
        
        cancel.setBounds(230, 520, 100, 30);
        add(cancel);
        cancel.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                 System.exit(0);
            }
        });
     
        setBounds(200,300,700,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
    }
    
}

