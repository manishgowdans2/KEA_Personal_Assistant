import java.awt.*;  
import javax.swing.*;  
public class Main {  
     Main()  
        {  
        JFrame f= new JFrame();    
        JPanel panel=new JPanel();  
        panel.setBounds(0,0,400,70);    
        panel.setBackground(Color.gray);  
        
        JPanel panel2=new JPanel();  
        panel2.setBounds(0,80,400,70);    
        panel2.setBackground(Color.gray);
        JButton b1=new JButton("Button 1");     
        b1.setBounds(50,100,80,30);    
        b1.setBackground(Color.yellow);   
        JButton b2=new JButton("Button 2");   
        b2.setBounds(100,100,80,30);    
        b2.setBackground(Color.green);   
        panel.add(b1); panel.add(b2);  
        f.add(panel);  
                f.setSize(400,400);    
                f.setLayout(null);    
                f.setVisible(true); 
                
                
         f.add(panel2);  
                f.setSize(400,400);    
                f.setLayout(null);    
                f.setVisible(true);   
                
                
                
        }  
     
        public static void main(String args[])  
        {  
        new Main();  
        }  
        
}