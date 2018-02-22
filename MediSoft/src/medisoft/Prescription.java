
package medisoft;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Prescription extends JFrame{
    
    JLabel patientNameL,patientAgeL,problemDescriptionL,prescriptionL,genderL,genderLabel;
    JTextArea problemDescriptionT,prescriptionT;
    JTextField patientNameT,patientAgeT;
    //JComboBox<String> gender;
    JRadioButton male,female;
    
    
    
    JButton submit,back;
    Prescription()
    {
        //setting the background image
        super("Prescription");
        try
        {
            setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("src/prescription.jpg")))));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        
        
        //including the label prompts
        patientNameL=new JLabel("Patient Name:");
        patientNameL.setBounds(10,15,200,50);
        patientNameL.setFont(new Font("serif", Font.BOLD, 20));
        add(patientNameL);
        
        patientAgeL=new JLabel("Age:");
        patientAgeL.setBounds(10,65,200,50);
        patientAgeL.setFont(new Font("serif", Font.BOLD, 20));
        add(patientAgeL);
        
        genderLabel=new JLabel("Gender:");
        genderLabel.setBounds(10, 110, 200, 50);
        genderLabel.setFont(new Font("serif",Font.BOLD,20));
        add(genderLabel);
        
        problemDescriptionL=new JLabel("Description:");
        problemDescriptionL.setBounds(10,145,200,50);
        problemDescriptionL.setFont(new Font("serif", Font.BOLD, 20));
        add(problemDescriptionL);
        
        prescriptionL=new JLabel("Prescribe:");
        prescriptionL.setBounds(10,285,200,50);
        prescriptionL.setFont(new Font("serif", Font.BOLD, 20));
        add(prescriptionL);
        
        //Gender radiobutton
        male=new JRadioButton("Male");
        male.setBounds(150, 120, 100, 30);
        female=new JRadioButton("Female");
        female.setBounds(250, 120, 100, 30);
        ButtonGroup group=new ButtonGroup();
        group.add(male);
        group.add(female);
        add(male);
        add(female);
        
        
        //creating the text fields
        patientNameT=new JTextField();
        patientNameT.setBounds(150,20,400,40);
        add(patientNameT);
        
        patientAgeT=new JTextField();
        patientAgeT.setBounds(150, 70, 400, 40);
        add(patientAgeT);
        
        problemDescriptionT=new JTextArea();
        problemDescriptionT.setBounds(150, 160, 400, 120);
        add(problemDescriptionT);
        
        prescriptionT=new JTextArea();
        prescriptionT.setBounds(150, 300, 400, 200);
        add(prescriptionT);
        
        
        
        
        
       submit=new JButton(new ImageIcon("src/saveandprint.jpg"));
       submit.setBounds(560, 467, 200, 33);
       submit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                String name=patientNameT.getText();
                String age=patientAgeT.getText();
                String disease=problemDescriptionT.getText();
                String cure=prescriptionT.getText();
                
                writePrescription(name, age, disease, cure);
                
                PrintFile printFile=new PrintFile(name, age, disease, cure);
            }
       });
       add(submit);
        
       back=new JButton(new ImageIcon("src/back.png"));
       back.setBounds(770, 467, 72, 33);
       back.addActionListener(new ActionListener(){
          

            @Override
            public void actionPerformed(ActionEvent ae) {
                 System.exit(0);
            }
       });
       add(back);
        
        

        setSize(900, 557);
        setResizable(false);
        setVisible(true);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    
    
    public void writePrescription(String name,String age,String disease,String cure)
    {
        try
        {
            File file=new File("src/Files/prescription/"+name+".txt");
            BufferedWriter bw=new BufferedWriter(new FileWriter(file));
            bw.write("Name: "+name);
            bw.newLine();
            bw.newLine();
            bw.write("Age : "+age);
            bw.newLine();
            bw.newLine();
            
            if(male.isSelected())
            {
                bw.write("Gender : "+male);
            }
            else
            {
                bw.write("Gender : "+female);
            }
            
            bw.newLine();
            bw.newLine();
            bw.write("Problem:. "+disease);
            bw.newLine();
            bw.newLine();
            bw.write("Prescription: "+cure);
            bw.newLine();
            bw.newLine();
            bw.newLine();
            bw.close();
        }
        
        catch(IOException ae)
        {
            ae.printStackTrace();
        }
    }
    
    
//    public static void main(String[] args) {
//        new Prescription();
//    }
}

