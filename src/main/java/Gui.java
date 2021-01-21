
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.*;
import java.util.Properties;

public class Gui extends JFrame {
    public static  String value;
    public static String name;
    public static String pass;
    private static int ActivationCode;
    private static int tries=0;
    public Gui()  {
        super("Login  & Register");
        JFrame jFrame = new JFrame();
        jFrame.pack();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setSize(600,600);
        jFrame.setLocation(700,300);
        jFrame.setVisible(true);

        JMenu menu, submenu;
        JMenuItem i1, i2, i3, i4;



// Adds menu Bar
        JMenuBar mb=new JMenuBar();
        menu=new JMenu("Menu");
        submenu=new JMenu("Menu");
        i1=new JMenuItem("Forgot Password");
        i2=new JMenuItem("Exit");
        i3=new JMenuItem("Refresh");
        i4=new JMenuItem("Register");
        menu.add(i1);
        menu.add(i2);
        menu.add(i3);
        menu.add(i4);
        menu.add(submenu);
        mb.add(menu);
        jFrame.setJMenuBar(mb);




        /*      PAUZA PROGRAMU

        System.out.println("SIEMA");

        Thread.sleep(5000);
        System.out.println("SIEMAAAA");


         */

        JPanel panel = new JPanel();
        JPanel panelLabel = new JPanel();




//Adds labels, passwords


        panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
        panelLabel.setLayout(new BoxLayout(panelLabel, BoxLayout.PAGE_AXIS));


        JTextField UserField = new JTextField(14);
        JTextField PasswordField = new JTextField(14);


        JButton butto2 = new JButton("Login");

        JLabel UserNameLabel = new JLabel(" Username");
        JLabel PasswordLabel = new JLabel("Password");

        UserField.setMaximumSize( UserField.getPreferredSize() );
        PasswordField.setMaximumSize( PasswordField.getPreferredSize() );
        jFrame.add(panel);


        panel.add(UserNameLabel);
        panel.add(Box.createRigidArea(new Dimension(0,4)));

        panel.add(UserField);

        panel.setBorder(new EmptyBorder(new Insets(180,250,90,200)));

        panel.add(Box.createRigidArea(new Dimension(0,60)));

        panel.add(PasswordLabel);

        panel.add(Box.createRigidArea(new Dimension(0,4)));
        panel.add(PasswordField);


        panel.add(Box.createRigidArea(new Dimension(20,20)));
        panel.add(butto2);

        // jFrame.add(panelLabel);

//button to loggin
        butto2.addActionListener(e -> {

            String nameLogin = UserField.getText();
            String passLogin = PasswordField.getText();
            Connection con = null;
            PreparedStatement st = null;
            try {

                String sql = "select *from info where name='"+nameLogin+"' and password='"+passLogin+"'";
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/peop?serverTimezone=UTC", "root", "");
                st = con.prepareStatement(sql);
                ResultSet rs =st.executeQuery(sql);
                if(rs.next())
                {
                    dispose();

                    jFrame.setVisible(false);
                    System.out.println("Udane logowanie");
                    LoginScreen loginScreen = new LoginScreen();


                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Wrong username or password!");

                    ++tries;
                    System.out.println(tries);
                    if(tries==3){

                        butto2.setEnabled(false);


                        if(!butto2.isEnabled()){

                            System.out.println("JEST ZABLOKOWANE");

                        }
                    }
                }
            } catch (SQLException | HeadlessException ex) {


                JOptionPane.showMessageDialog(null, ex);

            }
        });
        //PANELE
        i1.addActionListener(e -> {

            Random r = new Random(System.currentTimeMillis());
            ActivationCode = ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));


            String myOptions = "Input your email adress";
            value = JOptionPane.showInputDialog(
                    null, myOptions, "Forgot password", 1);

            System.out.println(value);
            String recipient;
//TODO Sending emails works

            Properties properties = new Properties();

            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");

            String myAccountEmail = "reminderproject02@gmail.com";
            String passwordEmail = "Rem!nderProjectJava665";

            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(myAccountEmail, passwordEmail);
                }
            });

            Message message = prepareMessage(session, myAccountEmail);


            try {
                Transport.send(message);
            } catch (MessagingException messagingException) {
                messagingException.printStackTrace();
            }
            JTextField nameForgot = new JTextField(14);
            JTextField codeForgot = new JTextField(14);
            JTextField emailForgot = new JTextField(14);
            JPanel myPanel = new JPanel();
            myPanel.add(new JLabel("Username:"));
            myPanel.add(nameForgot);
            myPanel.add(Box.createHorizontalStrut(15)); // a spacer
            myPanel.add(new JLabel("Email: "));
            myPanel.add(emailForgot);
            myPanel.add(new JLabel("Activiation code:"));
            myPanel.add(codeForgot);


            System.out.println(ActivationCode);

            int result = JOptionPane.showConfirmDialog(null, myPanel,
                    "Enter UserName and password", JOptionPane.OK_CANCEL_OPTION);


            if (result == JOptionPane.OK_OPTION) {


                String namee = nameForgot.getText();
                String email = emailForgot.getText();
                int code = Integer.parseInt(codeForgot.getText());


                Connection con = null;
                PreparedStatement st = null;
                try {

                    String sql = "select *from info where name='" + namee + "' and email='" + email + "'";
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/peop?serverTimezone=UTC", "root", "");
                    st = con.prepareStatement(sql);
                    ResultSet rs = st.executeQuery(sql);
                    if (rs.next()) {
                        dispose();
                        if(code ==ActivationCode) {

                            //NIE DZIALA
                            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/peop?serverTimezone=UTC", "root", "");
                            st = con.prepareStatement(sql);
                            String newPass= JOptionPane.showInputDialog(null, "Input your new password");
                            sql = "select *from info where email='"+email+"'";
                            rs = st.executeQuery(sql);
                            System.out.println(rs);

//TODO Repair "change password"
                        }
                        else
                        {
                            System.out.println("Nie udało sie zalogować");
                        }

                    }
                    else
                    {
                        System.out.println("NIE UDALO SIE");
                    }


                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }


            }
        });
        i4.addActionListener(e -> {

                    //TODO Registration to MYSQL
                    JTextField xField = new JTextField(14);
                    JTextField eField = new JTextField(14);
                    JTextField yField = new JTextField(14);
                    JPanel myPanel = new JPanel();
                    myPanel.add(new JLabel("Nazwa uzytkownika:"));
                    myPanel.add(xField);
                    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
                    myPanel.add(new JLabel("Haslo:"));
                    myPanel.add(yField);
                    myPanel.add(new JLabel("Email"));
                    myPanel.add(eField);

                    int result = JOptionPane.showConfirmDialog(null, myPanel,
                            "Enter UserName and password", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
                        Connection con = null;
                        PreparedStatement st = null;

                        name = xField.getText();
                        pass = yField.getText();
                        try {



                            String sql = "insert into info (name, password, email) values(?, ?, ?)";
                            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/peop?serverTimezone=UTC", "root", "");
                            st = con.prepareStatement(sql);
                            st.setString(1, xField.getText());
                            st.setString(2, yField.getText());
                            st.setString(3,eField.getText());
                            st.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Zarejestrowano!");

                        } catch (SQLException | HeadlessException ex) {


                            JOptionPane.showMessageDialog(null, ex);

                        }
                    }
                }
        );



        i3.addActionListener(e -> {


            SwingUtilities.updateComponentTreeUI(jFrame);


        });

    }

    private static Message prepareMessage(Session session, String myAccountEmail) {

//TODO Sending emails content
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(value));
            message.setSubject("Activation code");
            message.setText("Your activation code is: "+ActivationCode);
            return message;

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }




}
