import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GUI extends JFrame {

    SimulationManager simulationManager;
    private JTextArea sectiuneText;
    private JPanel contentPanel;
    private JTextField nrClienti;
    private JTextField nrCozi;
    private JTextField timpMinSosire;
    private JTextField timpMaxSosire;
    private JTextField timpMinServire;
    private JTextField timpMaxServire;
    private JTextField timpLimita;
    private JButton butonStart;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GUI frame = new GUI();
                    frame.setVisible(true);
  //                  frame.addStartListener(new StartListener());
  //                  sectiuneText.setText(Logg.stringFinal);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public GUI() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1220, 950);
        contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(6, 6, 6, 6));
        setContentPane(contentPanel);
        contentPanel.setLayout(null);

        JLabel titlu = new JLabel("Gestionare cozi");
        titlu.setFont(new Font("Times New Roman", Font.PLAIN, 26));
        titlu.setBounds(503, 20, 179, 30);
        contentPanel.add(titlu);

        JLabel labelNrClienti = new JLabel("Numar clienti");
        labelNrClienti.setFont(new Font("Times New Roman", Font.PLAIN, 26));
        labelNrClienti.setBounds(12, 60, 179, 30);
        contentPanel.add(labelNrClienti);

        nrClienti = new JTextField();
        nrClienti.setBounds(243, 60, 116, 30);
        contentPanel.add(nrClienti);
        nrClienti.setColumns(10);

        JLabel labelNrCozi = new JLabel("Numar cozi");
        labelNrCozi.setFont(new Font("Times New Roman", Font.PLAIN, 26));
        labelNrCozi.setBounds(12, 150, 179, 30);
        contentPanel.add(labelNrCozi);

        JLabel labelTimpMinSosire = new JLabel("Timp min sosire");
        labelTimpMinSosire.setFont(new Font("Times New Roman", Font.PLAIN, 26));
        labelTimpMinSosire.setBounds(12, 240, 179, 30);
        contentPanel.add(labelTimpMinSosire);

        JLabel labelTimpMaxSosire = new JLabel("Timp max sosire");
        labelTimpMaxSosire.setFont(new Font("Times New Roman", Font.PLAIN, 26));
        labelTimpMaxSosire.setBounds(12, 330, 179, 30);
        contentPanel.add(labelTimpMaxSosire);

        JLabel labelTimpMinServire = new JLabel("Timp min servire");
        labelTimpMinServire.setFont(new Font("Times New Roman", Font.PLAIN, 26));
        labelTimpMinServire.setBounds(12, 420, 179, 30);
        contentPanel.add(labelTimpMinServire);

        JLabel labelTimpMaxServire = new JLabel("Timp max servire");
        labelTimpMaxServire.setFont(new Font("Times New Roman", Font.PLAIN, 26));
        labelTimpMaxServire.setBounds(12, 510, 185, 30);
        contentPanel.add(labelTimpMaxServire);

        JLabel labelTimpLimita = new JLabel("Limita timp");
        labelTimpLimita.setFont(new Font("Times New Roman", Font.PLAIN, 26));
        labelTimpLimita.setBounds(12, 600, 130, 30);
        contentPanel.add(labelTimpLimita);

        nrCozi = new JTextField();
        nrCozi.setBounds(243, 150, 116, 30);
        contentPanel.add(nrCozi);
        nrCozi.setColumns(10);

        timpMinSosire = new JTextField();
        timpMinSosire.setBounds(243, 240, 116, 30);
        contentPanel.add(timpMinSosire);
        timpMinSosire.setColumns(10);

        timpMaxSosire = new JTextField();
        timpMaxSosire.setBounds(243, 330, 116, 30);
        contentPanel.add(timpMaxSosire);
        timpMaxSosire.setColumns(10);

        timpMinServire = new JTextField();
        timpMinServire.setBounds(243, 420, 116, 30);
        contentPanel.add(timpMinServire);
        timpMinServire.setColumns(10);

        timpMaxServire = new JTextField();
        timpMaxServire.setBounds(243, 510, 116, 30);
        contentPanel.add(timpMaxServire);
        timpMaxServire.setColumns(10);

        timpLimita = new JTextField();
        timpLimita.setBounds(243, 600, 116, 30);
        contentPanel.add(timpLimita);
        timpLimita.setColumns(10);

        sectiuneText = new JTextArea();
        sectiuneText.setBounds(392, 479, 744, 227);

        JScrollPane sPanelSectiuneText=new JScrollPane(sectiuneText);
        sPanelSectiuneText.setBounds(392, 479, 744, 227);
        contentPanel.add(sPanelSectiuneText);

        butonStart = new JButton("START");
        butonStart.setFont(new Font("Times New Roman", Font.PLAIN, 40));
        butonStart.setBounds(555, 750, 179, 40);
        contentPanel.add(butonStart);
        butonStart.setVisible(true);
        butonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nrClienti="";
                String nrCozi="";
                String timpMinSosire="";
                String timpMaxSosire="";
                String timpMinServire="";
                String timpMaxServire="";
                String timpLimita="";

                nrClienti=GUI.this.getStringNrClienti();
                nrCozi=GUI.this.getStringNrCozi();
                timpMinSosire=GUI.this.getStringTimpMinSosire();
                timpMaxSosire=GUI.this.getStringTimpMaxSosire();
                timpMinServire=GUI.this.getStringTimpMinServire();
                timpMaxServire=GUI.this.getStringTimpMaxServire();
                timpLimita=GUI.this.getStringTimpLimita();
                try {
                    int nrClientiInt=Integer.parseInt(nrClienti);
                    int timpLimitaInt=Integer.parseInt(timpLimita);
                    int nrCoziInt=Integer.parseInt(nrCozi);
                    int timpMinSosireInt=Integer.parseInt(timpMinSosire);
                    int timpMaxSosireInt=Integer.parseInt(timpMaxSosire);
                    int timpMinServireInt=Integer.parseInt(timpMinServire);
                    int timpMaxServireInt=Integer.parseInt(timpMaxServire);
                    simulationManager=new SimulationManager(timpLimitaInt, timpMaxServireInt, timpMinServireInt, nrCoziInt,
                            nrClientiInt, 20, timpMinSosireInt, timpMaxSosireInt);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                int timpCurent = 0;
                                while (timpCurent < timpLimitaInt+1) {
                                    Thread.sleep(1000);
                                    GUI.this.actualizare();
                                    timpCurent+=1;
                                }
                            } catch (InterruptedException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }).start();
                } catch(NumberFormatException exception) {
                    aruncaEroare(exception.getMessage());
                }
            }
        });
    }

    public void aruncaEroare(String s) {
        JOptionPane.showMessageDialog(null, "Invalid format! " + s);
    }

    public void actualizare() {
        GUI.this.sectiuneText.setText(Logg.stringFinal);
    }

    public void addStartListener(ActionListener startAL){
        butonStart.addActionListener(startAL);
    }


    public JTextArea getSectiuneText() {
        return sectiuneText;
    }

    public void setSectiuneText(JTextArea sectiuneText) {
        this.sectiuneText = sectiuneText;
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public void setContentPanel(JPanel contentPanel) {
        this.contentPanel = contentPanel;
    }

    public JTextField getNrClienti() {
        return nrClienti;
    }

    public void setNrClienti(JTextField nrClienti) {
        this.nrClienti = nrClienti;
    }

    public JTextField getNrCozi() {
        return nrCozi;
    }

    public void setNrCozi(JTextField nrCozi) {
        this.nrCozi = nrCozi;
    }

    public JTextField getTimpMinSosire() {
        return timpMinSosire;
    }

    public void setTimpMinSosire(JTextField timpMinSosire) {
        this.timpMinSosire = timpMinSosire;
    }

    public JTextField getTimpMaxSosire() {
        return timpMaxSosire;
    }

    public void setTimpMaxSosire(JTextField timpMaxSosire) {
        this.timpMaxSosire = timpMaxSosire;
    }

    public JTextField getTimpMinServire() {
        return timpMinServire;
    }

    public void setTimpMinServire(JTextField timpMinServire) {
        this.timpMinServire = timpMinServire;
    }

    public JTextField getTimpMaxServire() {
        return timpMaxServire;
    }

    public void setTimpMaxServire(JTextField timpMaxServire) {
        this.timpMaxServire = timpMaxServire;
    }
    public void eroare(String s) {
        JOptionPane.showMessageDialog(null, s);
    }

    String getStringNrClienti() {
        return nrClienti.getText();
    }

    String getStringNrCozi() {
        return nrCozi.getText();
    }
    String getStringTimpMinServire() {
        return timpMinServire.getText();
    }
    String getStringTimpMaxServire() {
        return timpMaxServire.getText();
    }
    String getStringTimpMinSosire() {
        return timpMinSosire.getText();
    }
    String getStringTimpMaxSosire() {
        return timpMaxSosire.getText();
    }
    String getStringTimpLimita() {
        return timpLimita.getText();
    }
    static class Logg
    {
        static String stringFinal="";
        public synchronized static void addString(String string)
        {
            stringFinal=stringFinal+string+"\n";
        }
    }
}
