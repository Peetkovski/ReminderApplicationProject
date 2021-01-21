import javax.swing.*;
import java.awt.*;

public class LoginScreen extends JFrame{
    static  String textt;
    static String textDate;
    public LoginScreen() {

        super("Your Calendar");
        JFrame jFrame = new JFrame();
        jFrame.pack();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setSize(600,600);
        jFrame.setLocation(700,300);
        jFrame.setVisible(true);


        JMenu menu;
        JMenuItem i1, i2, i3, i4;



        JMenuBar mb=new JMenuBar();
        menu=new JMenu("Menu");
        i1=new JMenuItem("Add Reminder");
        i2=new JMenuItem("Settings");
        i3=new JMenuItem("Refresh");
        i4=new JMenuItem("Exit");
        menu.add(i1);
        menu.add(i2);
        menu.add(i3);
        menu.add(i4);

        mb.add(menu);
        jFrame.setJMenuBar(mb);

        JPanel listPane = new JPanel();




        listPane.setLayout(new BoxLayout(listPane,BoxLayout.PAGE_AXIS));



        jFrame.add(listPane);

        listPane.setVisible(true);



        i1.addActionListener(e -> {


            //TODO Repaired


            String Joption =   JOptionPane.showInputDialog("Podaj nazwę przypomnienia");

            textt = String.format(Joption);

            System.out.println(textt);


            System.out.println("Elo");

            JButton but = new JButton(textt);
//DODAJE PRZYCISk
            listPane.add(but);


            but.setVisible(true);
            but.addActionListener(e1 ->{

                String[] options = {"Delete Reminder", "Add Date", "Exit"};

                int x = JOptionPane.showOptionDialog(null, "Choose action",
                        "Click a button",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

                System.out.println(x);

                if(x==0)
                {
                    but.setVisible(false);
                }
                if(x==1) {
                    long millis=System.currentTimeMillis();
                    java.sql.Date date=new java.sql.Date(millis);
                    System.out.println(date);

                    String a = date.toString();



                    String JoptionDate = JOptionPane.showInputDialog("Podaj Date");
                    textDate = String.format(JoptionDate);




                    if(a.equals(textDate)){

                        System.out.println("TO DZISIAJ");

                    }

                }
                SwingUtilities.updateComponentTreeUI(jFrame);

            });
            SwingUtilities.updateComponentTreeUI(jFrame);

        });




        i4.addActionListener(e -> {
//TODO Repair exiting

            jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        });


    }

    public static void Loggin(){




    }




}


