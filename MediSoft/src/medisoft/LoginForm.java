
package medisoft;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class LoginForm extends JFrame{
    
    public static int idTrack;
    
    JButton ok,createId;
    JLabel namePrompt,passPrompt,uniqueIdL;
    JLabel message;
    JTextField name,pass,idT,uniqueIdT;
    JLabel idL;
    String inputEmail,inputPass,id,uniqueCode;
    
    ArrayList <String> userNameList=new ArrayList();
    ArrayList <String> passWordList=new ArrayList();
    ArrayList <String> uniqueIdList=new ArrayList();
    LoginForm() throws IOException
    {   
        super("Login Form");
        
        
        
        try
        {
            setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("src/medi.jpg")))));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        
       
        
        //These are the labels prompting for username and password
        namePrompt=new JLabel("Email:");
        passPrompt=new JLabel("Password:");
        idL=new JLabel("Serial No:");
        uniqueIdL=new JLabel("Unique ID:");
        namePrompt.setBounds(320, 280, 100, 50);
        namePrompt.setFont(new Font("monospace",Font.BOLD, 18));
        add(namePrompt);
        
        passPrompt.setBounds(320, 320, 100, 50);
        passPrompt.setFont(new Font("monospace",Font.BOLD, 18));
        add(passPrompt);
        
        idL.setBounds(320, 360, 100, 50);
        idL.setFont(new Font("monospace",Font.BOLD, 18));
        add(idL);
        
        uniqueIdL.setBounds(320, 400, 100, 50);
        uniqueIdL.setFont(new Font("monospace",Font.BOLD,18));
        add(uniqueIdL);
        
        
        
        //These are the textfields for input
        name=new JTextField();
        name.setBounds(480, 290, 300, 30);
        add(name);
        
        pass=new JTextField();
        pass.setBounds(480, 330, 300, 30);
        add(pass);
        
        idT=new JTextField();
        idT.setBounds(480, 370, 300, 30);
        add(idT);
        
        uniqueIdT=new JTextField();
        uniqueIdT.setBounds(480, 410, 300, 30);
        add(uniqueIdT);
        
        //This is the information of the doctor loaded from the arraylist to a file
        getInfo();
        
        
        //Register button setup
        ImageIcon register=new ImageIcon("src/register.png");
        createId=new JButton(register);
        createId.setBounds(680, 20, 200, 80);
        add(createId);
        createId.addActionListener((ActionEvent ae) -> {
            setVisible(false);
            SignUpForm signup=new SignUpForm();
        });
        
        //Submit button setup
        ImageIcon sub=new ImageIcon("src/submit2.png");
        
        ok=new JButton(sub);
        ok.setBounds(640,460,137,43);
        add(ok);
        ok.addActionListener((ActionEvent ae) -> {
            
            
            
            inputEmail=name.getText();
            inputPass=pass.getText();
            uniqueCode=uniqueIdT.getText();
            if((inputEmail.isEmpty())&&(inputPass.isEmpty())&&(uniqueCode.isEmpty()))
            {
                JOptionPane.showMessageDialog(null, "Please Fill up the form first");
            }
            
            //Function call for checking if the login info is ok
            checkIfOK();         
        });
        
        
        //Boundaries and other infos for this form
        setBounds(100,100,900, 557);
        setResizable(false);
        setVisible(true);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    
    //Checking whether the entered information is okay
    public void checkIfOK()
    {
        int id;
        id = Integer.parseInt(idT.getText());
        
        String mainEmail=userNameList.get(id-1);
        String mainPass=passWordList.get(id-1);
        String mainUniqueCode=uniqueIdList.get(id-1);
        
        if(mainUniqueCode.isEmpty()||(mainEmail.isEmpty())||(mainPass.isEmpty()))
        {
            JOptionPane.showMessageDialog(null, "Create ID First");
        }
        
        if((inputEmail.equals(mainEmail))&&(inputPass.equals(mainPass))&&(uniqueCode.equals(mainUniqueCode)))
        {
            JOptionPane.showMessageDialog(null, "Login Successful");
            setVisible(false);
            Prescription prescription=new Prescription();
        }
        
        else if((inputEmail.isEmpty())&&(!inputPass.equals(mainPass))&(!uniqueCode.equals(mainUniqueCode)))
        {
            JOptionPane.showMessageDialog(null,"Wrong information. Try again");
        }
        
        else if((inputEmail.isEmpty())&&(!inputPass.equals(mainPass))&(!uniqueCode.equals(mainUniqueCode)))
        {
            JOptionPane.showMessageDialog(null,"Wrong information. Try again");
        }
        
        else if((!inputEmail.equals(mainEmail))&&(!inputPass.isEmpty())&(!uniqueCode.equals(mainUniqueCode)))
        {
            JOptionPane.showMessageDialog(null,"Wrong information. Try again");
        }
        
        else if((!inputEmail.equals(mainEmail))&&(!inputPass.equals(mainPass))&(!uniqueCode.isEmpty()))
        {
            JOptionPane.showMessageDialog(null,"Wrong information. Try again");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Wrong information. Try again");
        }
    }
    
    
    //Loading the username/email, the corresponding password of the doctor from file and the unique ID 
    //in an ArrayList
    public void getInfo() throws FileNotFoundException
    {
        File usrFile=new File("src/Files/username.txt");
        File passFile=new File("src/Files/password.txt");
        File uniqueIdFile=new File("src/Files/uniqueCode.txt");
        
        
        Scanner usrScan=new Scanner(usrFile);
        Scanner passScan=new Scanner(passFile);
        Scanner uniqueIdScan=new Scanner(uniqueIdFile);
        
        while(usrScan.hasNext())
        {
            userNameList.add(usrScan.next());
        }
        
        while(passScan.hasNext())
        {
            passWordList.add(passScan.next());
        }
        
        while(uniqueIdScan.hasNext())
        {
            uniqueIdList.add(uniqueIdScan.next());
        }
        
        idTrack=userNameList.size();

    } 
}
