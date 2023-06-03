package chat;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;

public class Work
{
    public static void main(String[] args)
    {
        Runnable r = new Runnable()
        {
            @Override
            public void run()
            {
                JFrame f = new JFrame();
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setPreferredSize(new Dimension(300, 300));
                JPanel contentPane = new JPanel();
                BoxLayout bl = new BoxLayout(contentPane, BoxLayout.PAGE_AXIS);
                contentPane.setLayout(bl);

                final JTextField jTextField1 = new JTextField();
                final JPasswordField jPasswordField1 = new JPasswordField();
                final JPasswordField jPasswordField2 = new JPasswordField();
                final JPasswordField jPasswordField3 = new JPasswordField();

                Box b = new Box(BoxLayout.LINE_AXIS);
                b.add(new JLabel("Compte"));
                b.add(jTextField1);
                contentPane.add(b);

                b = new Box(BoxLayout.LINE_AXIS);
                b.add(new JLabel("Cle précédent"));
                b.add(jPasswordField1);
                contentPane.add(b);

                b = new Box(BoxLayout.LINE_AXIS);
                b.add(new JLabel("Cle nouveau"));
                b.add(jPasswordField2);
                contentPane.add(b);

                b = new Box(BoxLayout.LINE_AXIS);
                b.add(new JLabel("Cle nouveau (répété)"));
                b.add(jPasswordField3);
                contentPane.add(b);

                JButton but = new JButton("Changer cle");
                contentPane.add(but);
                but.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent ae)
                    {
                        try
                        {
                            Connection con = null;
                            Statement stmt = null;
                            ResultSet rs = null;
                            int m = 0;
                             String url = "jdbc:mysql://localhost:3306/phone";
            String uname ="root";
            String password = "manumani";
            
           
            
             con = DriverManager.getConnection(url,uname,password);
                            stmt = con.createStatement();

                            rs = stmt.executeQuery("select * from compte");

                            while (rs.next())
                            {
                                String hh = jPasswordField2.getText();
                                String hh2 = jPasswordField3.getText();
                                if (jTextField1.getText().equals(rs.getString("Ncompte"))
                                        && jPasswordField1.getText().equals(rs.getString("Cle"))
                                        && jPasswordField2.getText().length() > 0
                                        && jPasswordField3.getText().length() > 0
                                        && hh.equals(hh2))
                                {
                                    m = 1;
                                    try
                                    {
                                        Connection con2 = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;integratedSecurity=true;databaseName=fiche_de_poste2");

                                        String reqq = "UPDATE compte SET Cle = ? WHERE Ncompte LIKE ?";
                                        PreparedStatement ps = con2.prepareStatement(reqq);
                                        ps.setString(1, jPasswordField3.getText());
                                        ps.setString(2, jTextField1.getText());

                                        int rowsUpdated = ps.executeUpdate();
                                        String msg = String.format("%d rangées mise à jour", rowsUpdated);
                                        if (0 < rowsUpdated)
                                        {
                                            msg = msg + " Mot de passe modifié avec succès.";
                                        }
                                        else
                                        {
                                            msg = msg + " Mot de passe ne modifié pas.";
                                        }
                                        JOptionPane.showMessageDialog(null, msg);
                                    }
                                    catch (SQLException sqlex)
                                    {
                                        System.out.println("SQL exception message " + sqlex.getMessage());
                                    }
                                }
                            }
                            if (m == 0)
                            {
                                JOptionPane.showMessageDialog(null, "ID ou mot de passe non valide,ou bien un des champs es vide");
                            }
                        }
                        catch (SQLException es)
                        {
                            es.printStackTrace();
                        }
                        catch (Exception ex)
                        {
                            JOptionPane.showMessageDialog(null, "Ops , Impossible de se connecter à la base de données !");
                        } 
                    }
                });

                f.setContentPane(contentPane);

                f.pack();
                f.setVisible(true);
            }
        };
        EventQueue.invokeLater(r);
    }
}