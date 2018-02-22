package medisoft;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;



public class SignUpForm  extends JFrame{
    
    
    static int idTrack;
    JLabel nameL,nameF,phoneL,emailL,passwordL,rePasF;
    JTextField nameFT,nameLT,phoneT,emailT,passwordT;
    JLabel license;
    JTextField dobF;
    JLabel dobL,privacy;
    JCheckBox agree;
    JButton submit,exit;
    
    String tempPass,currPass;
    
    
    //ArrayList loaded for checking whether there is an overlapping situation in terms of unique code
    ArrayList<String> tempCodeCheck=new ArrayList();
    
    public final int FONT_SIZE=20;
    JTextField rePasT,privacyMessage;
    
    
    //Constructor
    SignUpForm()
    {
        super("Sign Up");
        
        setBounds(40,50,1440,900);
        setResizable(false);
        setLayout(null);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try
        {
            setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("src/signupbackground.jpg")))));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        
        
        
        
        //Prompt Labels
        nameF=new JLabel("First name : ");
        nameF.setBounds(800, 100, 150, 50);
        nameF.setFont(new Font("monospace",Font.ITALIC,FONT_SIZE));
        nameF.setForeground(Color.WHITE);
        add(nameF);
        
        nameL=new JLabel("Last name : ");
        nameL.setBounds(800, 170, 150, 50);
        nameL.setFont(new Font("monospace",Font.ITALIC,FONT_SIZE));
        nameL.setForeground(Color.WHITE);
        add(nameL);
        
        phoneL=new JLabel("Phone : ");
        phoneL.setBounds(800, 240, 150, 50);
        phoneL.setFont(new Font("monospace",Font.ITALIC,FONT_SIZE));
        phoneL.setForeground(Color.WHITE);
        add(phoneL);
        
        emailL=new JLabel("Email : ");
        emailL.setBounds(800, 310, 150, 50);
        emailL.setFont(new Font("monospace",Font.ITALIC,FONT_SIZE));
        emailL.setForeground(Color.WHITE);
        add(emailL);
        
        dobL=new JLabel("Date of Birth : ");
        dobL.setBounds(800, 380, 150, 50);
        dobL.setFont(new Font("monospace",Font.ITALIC,FONT_SIZE));
        dobL.setForeground(Color.WHITE);
        add(dobL);
        
        passwordL=new JLabel("Password : ");
        passwordL.setBounds(800, 450, 150, 50);
        passwordL.setFont(new Font("monospace",Font.ITALIC,FONT_SIZE));
        passwordL.setForeground(Color.white);
        add(passwordL);
        
        rePasF = new JLabel("Re-type Password:");
        rePasF.setBounds(800, 520, 150, 50);
        rePasF.setFont(new Font("monospace",Font.ITALIC,FONT_SIZE));
        rePasF.setForeground(Color.white);
        add(rePasF);
        
        
        
        //Text fields
        
        nameFT=new JTextField();
        nameFT.setBounds(1000, 110, 250, 30);
        add(nameFT);
        
        nameLT=new JTextField();
        nameLT.setBounds(1000, 180, 250, 30);
        add(nameLT);
        
        phoneT=new JTextField();
        phoneT.setBounds(1000, 250, 250, 30);
        add(phoneT);
        
        emailT=new JTextField();
        emailT.setBounds(1000, 320, 250, 30);
        add(emailT);
        
        dobF=new JTextField("DD/MM/YYYY");
        dobF.setBounds(1000, 390, 250, 30);
        add(dobF);
        
        passwordT=new JTextField();
        passwordT.setBounds(1000, 460, 250, 30);
        add(passwordT);
        
        rePasT=new JTextField();
        rePasT.setBounds(1000, 530, 250, 30);
        add(rePasT);
      
        
        //Checkbox
        
        agree=new JCheckBox("I agree to the terms and servies of this application");
        agree.setBounds(800, 600, 370, 30);
        add(agree);
        
        
        //Button submit
        ImageIcon oka=new ImageIcon("src/signup.png");
        submit=new JButton(oka);
        submit.setBounds(800,680,200,48);
        submit.addActionListener((ActionEvent e) -> {
            String fName=nameFT.getText();
            String lName=nameLT.getText();
            String dob=dobF.getText();
            String phone=phoneT.getText();
            String email=emailT.getText();
            String password=passwordT.getText();
            String rePassword=rePasT.getText();
           
            if(isChecked(fName,lName,dob,phone,email,password,rePassword))
            {
                //Generating a unique code for the account
                String uniqueCode=checkWhetherUnique();

                
                //writing the information in File
               writeInFile(fName,lName,dob,phone,email,password,uniqueCode);
                
                //keeping track of the number of ids with the help of the loaded ArrayList
                idTrack=tempCodeCheck.size()+1;
                
                //setting this window invisible
                setVisible(false);
               JOptionPane.showMessageDialog(null, "Sign up successful!Your Unique ID is "+uniqueCode+""
                       + " and Serial Number is "+idTrack+". Please remember these two information. You can Log in now.");
                try {
                    LoginForm f=new LoginForm();
                    f.setVisible(true);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        });
        add(submit);
        
        

        
        //Button exit
        ImageIcon ex=new ImageIcon("src/cancel.png");
        exit=new JButton(ex);
        exit.setBounds(1100, 680, 200, 48);
        add(exit);
        exit.addActionListener((ActionEvent ae) -> {
            setVisible(false);
            try {
                new LoginForm().setVisible(true);
            } catch (IOException ex1) {
                ex1.printStackTrace();
            }
        });
    }
    
    public String checkWhetherUnique()
    {
        String temp=new String(generateUniqueCode());
        
        try
        {
            File file=new File("src/Files/uniqueCode.txt");
            Scanner scan=new Scanner(file);
            while(scan.hasNext())
            {
                tempCodeCheck.add(scan.next());
            }
            if(tempCodeCheck.contains(temp))
            {
                checkWhetherUnique(); //RECURSIVE CALL TO THIS METHOD UNTIL IT GENERATES AN UNIQUE CODE
            }
        }
        
        catch(IOException e)
        {
            e.printStackTrace();
        }
        
        return temp;
    }
    

    
    
    
    //isChecked(fName,lName,dob,phone,email,password,rePassword))
    //checking whether the given information is correct. Variable length argument used
    public boolean isChecked(String... args)
    {
        String firstName=args[0];
        String lastName=args[1];
        String dob=args[2];
        String phone=args[3];
        String email=args[4];
        String password=args[5];
        String rePassword=args[6];
        
        boolean flag=true;
        if(firstName.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Please fill up First Name");
            return false;
        }
        
        if(lastName.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Please fill up Last Name");
            return false;
        }
        
        if(phone.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Enter phone number");
            return false;
        }
        
        if(email.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Please Enter email");
            return false;
        }
        
        if((dob.equals("DD/MM/YYYY"))||(dob.isEmpty()))
        {
            JOptionPane.showMessageDialog(null, "Please enter date of birth in the correct format");
            return false;
        }
        
        if(password.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Password box empty");
            return false;
        }
        
        if(password.length()<4)
        {
            JOptionPane.showMessageDialog(null, "Password must be at least 4 characters long");
            return false;
        }
        
        if(!password.equals(rePassword))
        {
            JOptionPane.showMessageDialog(null, "Passwords do not match");
            return false;
        }

        if(!agree.isSelected())
        {
            JOptionPane.showMessageDialog(null, "Please agree to the terms of the authority");
            return false;
        }
        return flag;
    }
    
    
    //writeInFile(fName,lName,dob,phone,email,password);
    
    //writing all the information in File
    private void writeInFile(String... args)
    {
        String fName=args[0];
        String lName=args[1];
        String dob=args[2];
        String phone=args[3];
        String email=args[4];
        String password=args[5];
        String uniqueId=args[6];      
        
        try
        {
            File file=new File("src/Files/username.txt");
            BufferedWriter bw=new BufferedWriter(new FileWriter(file,true));
            bw.write(email);
            bw.newLine();
            bw.close();
        }
        
        catch(IOException ae)
        {
            ae.printStackTrace();
        }

        try
        {
            File file=new File("src/Files/password.txt");
            BufferedWriter bw=new BufferedWriter(new FileWriter(file,true));
                   
            bw.write(password);
            bw.newLine();
            bw.close();
        }   
        
        catch(IOException ae)
        {
            ae.printStackTrace();
        }
        
        try
        {
            File file=new File("src/Files/dateOfBirth.txt");
            BufferedWriter bw=new BufferedWriter(new FileWriter(file,true));
                   
            bw.write(dob);
            bw.newLine();
            bw.close();
        }   
        
        catch(IOException ae)
        {
            ae.printStackTrace();
        }
        
        try
        {
            File file=new File("src/Files/phoneNumber.txt");
            BufferedWriter bw=new BufferedWriter(new FileWriter(file,true));
                   
            bw.write(phone);
            bw.newLine();
            bw.close();
        }
        
        catch(IOException ae)
        {
            ae.printStackTrace();
        }
        
        try
        {
            File file=new File("src/Files/email.txt");
            BufferedWriter bw=new BufferedWriter(new FileWriter(file,true));
                   
            bw.write(email);
            bw.newLine();
            bw.close();
        }   
        
        catch(IOException ae)
        {
            ae.printStackTrace();
        }
        
        try
        {
            File file=new File("src/Files/name.txt");
            BufferedWriter bw=new BufferedWriter(new FileWriter(file,true));
                   
            bw.write(fName+" "+lName);
            bw.newLine();
            bw.close();
        }              
        catch(IOException ae)
        {
            ae.printStackTrace();
        }
        
        try
        {
            File file=new File("src/Files/uniqueCode.txt");
            BufferedWriter bw=new BufferedWriter(new FileWriter(file,true));
            
            bw.write(uniqueId);
            bw.newLine();
            bw.close();
        } 
        
        catch(IOException ae)
        {
            ae.printStackTrace();
        }
       
    } 
    
    public String generateUniqueCode()
    {
        Random rand=new Random();
                
        int temp=rand.nextInt()%9999;
                
        if(temp<0) 
        {
            temp=-temp;
        }
                
        Integer in=temp;
        
        String code=new String(in.toString());
        return code;
    }
}