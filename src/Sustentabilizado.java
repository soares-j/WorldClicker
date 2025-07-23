// -- IMPORT
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.FlowLayout;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

// -- PROGRAMA
public class Sustentabilizado extends JFrame {
    // -- VARIÁVEIS GLOBAIS
    // -- GERAL
    static int points;
    static double pointsDouble;
    static JFrame frame;
    ImageIcon gifIcon;

    // -- TITULO APS
    static Color defaultButtonColor = new Color(34, 139, 34);
    static Color defaultSubheaderColor = new Color(20, 66, 4);

    // -- DECORAÇÕES
    static JLabel pointsLabel, timerPointsLabel;
    static Font font1, font2, font3, font4;
    static JTextArea descriptionText;

    // -- VARIÁVEIS PARA GANHO DE PONTOS POR SEGUNDO
    static JButton buildButton1, buildButton2, buildButton3, buildButton4;
    static int timerSpeed, build1Counter, build1Price, build2Counter, build2Price, build3Counter, build3Price, build4Counter, build4Price;
    static double perSecond, pointSecond, pointConversor;
    static boolean timerSet;
    static Timer timer;

    // -- VARIÁVEIS PARA GANHO DE PONTOS POR CLIQUE
    static JButton clickButton1, clickButton2, clickButton3, clickButton4;
    static int clickPoints, click1Price, click1Counter, click2Price, click2Counter, click3Price, click3Counter, click4Price, click4Counter;
    
    // -- HANDLERS DE BOTÃO
    static pointsHandler pHandler = new pointsHandler();
    static mouseHandler mHandler = new mouseHandler();

    // -- INICIO
    public static void main(String[] args) {
        // -- SET VARIÁVEIS
        // -- SET VARIÁVEIS PONTUAÇÃO
        timerSet = false;
        perSecond = 0;
        clickPoints = 1000000;

        // -- SET VARIÁVEIS PREÇO E CONTADOR DE CONSTRUÇÕES
        build1Counter = 0;
        build1Price = 10;
        build2Counter = 0;
        build2Price = 100;
        build3Counter = 0;
        build3Price = 1000;
        build4Counter = 0;
        build4Price = 10000;

        // -- SET VARIÁVEIS PREÇO E CONTADOR DE CLIQUES
        click1Counter = 0;
        click1Price = 250;
        click2Counter = 0;
        click2Price = 2500;
        click3Counter = 0;
        click3Price = 25000;
        click4Counter = 0;
        click4Price = 1000000;

        // -- INICIA JOGO
        createFont();
        SwingUtilities.invokeLater(() -> {
            Sustentabilizado app = new Sustentabilizado();
            app.setVisible(true);
        });
    }

    // -- CRIA A FONTE DO JOGO
    private static void createFont() {
        font1 = new Font("Comic Sans MS", Font.PLAIN, 32);
        font2 = new Font("Comic Sans MS", Font.PLAIN, 20);
        font3 = new Font("SansSerif", Font.BOLD, 15);
        font4 = new Font("SansSerif", Font.BOLD, 25);
    }

    // -- CRIA A UI DO JOGO
    public Sustentabilizado() {
        // -- TELA DO JOGO
        frame = new JFrame("Sustentabilizado");
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.black);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // POP-UP do tutorial
        JFrame jFrame = new JFrame();
        jFrame.setSize(3500, 200);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JDialog jdialog = new JDialog(jFrame, true);
        jdialog.setSize(1000, 150);
        jdialog.setLocationRelativeTo(null);
        jdialog.setLayout(new FlowLayout());
        jdialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        jdialog.getContentPane().setBackground(defaultSubheaderColor);

        // ESCRITO NO POP UP
        JLabel aviso = new JLabel("O objetivo do jogo é comprar o Restaurador, assim que comprar o jogo termina.");
        aviso.setFont(font4);
        aviso.setForeground(Color.white);

        JLabel aviso2 = new JLabel("Aviso: Ao comprar o Restaurador, o jogo fechará automaticamente! (Feche esta janela para continuar)");
        aviso2.setFont(font3);
        aviso2.setForeground(Color.white);

        jdialog.add(aviso);
        jdialog.add(aviso2);
        
        jdialog.setVisible(true);

        // -- TITULO APS
        JLabel header = new JLabel("APS", SwingConstants.CENTER);
        header.setOpaque(true);
        header.setBackground(defaultButtonColor);
        header.setForeground(Color.WHITE);
        header.setFont(font4);
        header.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        frame.add(header, BorderLayout.WEST);

        // -- TITULO OBJETIVO PANEL
        JPanel subheaderPanel = new JPanel();
        subheaderPanel.setBounds(0, 0, 430, 50);
        subheaderPanel.setBackground(defaultSubheaderColor);

        frame.add(subheaderPanel, BorderLayout.NORTH);

        // -- TITULO OBJETIVO TEXT
        JTextArea subheaderText = new JTextArea();
        subheaderText.setBounds(0, 0, 430, 50);
        subheaderText.setForeground(Color.white);
        subheaderText.setBackground(defaultSubheaderColor);
        subheaderText.setFont(font4);
        subheaderText.setLineWrap(true);
        subheaderText.setWrapStyleWord(true);
        subheaderText.setEditable(false);
        subheaderText.setText("Objetivo: Compre o 'Restaurador'");

        subheaderPanel.add(subheaderText);

        // -- PAINEL DO MUNDO
        JPanel worldPanel = new JPanel();
        worldPanel.setBounds(335, 340, 400, 400);
        worldPanel.setBackground(Color.black);

        // -- IMAGEM DO MUNDO
        ImageIcon worldIconColor = new ImageIcon(getClass().getResource("/world.png"));
        Image image = worldIconColor.getImage();
        int newWidth = 400;
        int newHeight = 400;
        Image scaledImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        // -- BOTÃO DO MUNDO
        JButton worldButton = new JButton();
        worldButton.setBackground(Color.black);
        worldButton.setFocusPainted(false);
        worldButton.setBorder(null);
        worldButton.setIcon(scaledIcon);
        worldButton.addActionListener(pHandler);
        worldButton.setActionCommand("point");
        worldPanel.add(worldButton);

        frame.add(worldPanel);
        
        // -- PAINEL DO CONTADOR DE PONTOS
        JPanel counterPanel = new JPanel();
        counterPanel.setBounds(385, 215, 300, 100);
        counterPanel.setBackground(Color.black);
        counterPanel.setLayout(new GridLayout(2,1));

        frame.add(counterPanel);

        // -- PONTUAÇÃO CONTADOR DE PONTOS
        pointsLabel = new JLabel(points + " Ecologia");
        pointsLabel.setForeground(Color.white);
        pointsLabel.setFont(font1);

        counterPanel.add(pointsLabel);

        // -- PONTUAÇÃO POR SEGUNDO CONTADOR DE PONTOS
        timerPointsLabel = new JLabel();
        timerPointsLabel.setForeground(Color.white);
        timerPointsLabel.setFont(font2);

        counterPanel.add(timerPointsLabel);

        // -- PAINEL DE CONSTRUÇÕES
        JPanel buildPanel = new JPanel();
        buildPanel.setBounds(835, 290, 500, 500);
        buildPanel.setBackground(Color.black);
        buildPanel.setLayout(new GridLayout(4,1));

        frame.add(buildPanel);

        // -- BOTÕES DE CONSTRUÇÕES
        // -- BOTÃO 1
        buildButton1 = new JButton("CANUDOS DE PAPEL");
        buildButton1.setFont(font1);
        buildButton1.setFocusPainted(false);
        buildButton1.addActionListener(pHandler);
        buildButton1.setActionCommand("build1");
        buildButton1.addMouseListener(mHandler);
        // -- BOTÃO 2
        buildButton2 = new JButton("PLANTAR ÁRVORES");
        buildButton2.setFont(font1);
        buildButton2.setFocusPainted(false);
        buildButton2.addActionListener(pHandler);
        buildButton2.setActionCommand("build2");
        buildButton2.addMouseListener(mHandler);
        // -- BOTÃO 3
        buildButton3 = new JButton("ENERGIA LIMPA");
        buildButton3.setFont(font1);
        buildButton3.setFocusPainted(false);
        buildButton3.addActionListener(pHandler);
        buildButton3.setActionCommand("build3");
        buildButton3.addMouseListener(mHandler);
        // -- BOTÃO 4
        buildButton4 = new JButton("CAPTURA CARBONO");
        buildButton4.setFont(font1);
        buildButton4.setFocusPainted(false);
        buildButton4.addActionListener(pHandler);
        buildButton4.setActionCommand("build4");
        buildButton4.addMouseListener(mHandler);

        // -- ADICIONAR BOTÕES AO PAINEL
        buildPanel.add(buildButton1);
        buildPanel.add(buildButton2);
        buildPanel.add(buildButton3);
        buildPanel.add(buildButton4);

        // -- PAINEL DE CLICKS
        JPanel clickPanel = new JPanel();
        clickPanel.setBounds(1385, 290, 500, 500);
        clickPanel.setBackground(Color.green);
        clickPanel.setLayout(new GridLayout(4,1));

        frame.add(clickPanel);

        // -- BOTÕES DE CLICKS
        // -- BOTÃO 1
        clickButton1 = new JButton("SEMEADOR");
        clickButton1.setFont(font1);
        clickButton1.setFocusPainted(false);
        clickButton1.addActionListener(pHandler);
        clickButton1.setActionCommand("click1");
        clickButton1.addMouseListener(mHandler);
        // -- BOTÃO 2
        clickButton2 = new JButton("RECICLADOR");
        clickButton2.setFont(font1);
        clickButton2.setFocusPainted(false);
        clickButton2.addActionListener(pHandler);
        clickButton2.setActionCommand("click2");
        clickButton2.addMouseListener(mHandler);
        // -- BOTÃO 3
        clickButton3 = new JButton("ENERGIZADOR");
        clickButton3.setFont(font1);
        clickButton3.setFocusPainted(false);
        clickButton3.addActionListener(pHandler);
        clickButton3.setActionCommand("click3");
        clickButton3.addMouseListener(mHandler);
        // -- BOTÃO 4
        clickButton4 = new JButton("RESTAURADOR");
        clickButton4.setFont(font1);
        clickButton4.setFocusPainted(false);
        clickButton4.addActionListener(pHandler);
        clickButton4.setActionCommand("click4");
        clickButton4.addMouseListener(mHandler);

        // -- ADICIONAR BOTÕES AO PAINEL
        clickPanel.add(clickButton1);
        clickPanel.add(clickButton2);
        clickPanel.add(clickButton3);
        clickPanel.add(clickButton4);

        // -- PAINEL DE DESCRIÇÃO
        JPanel descriptionPanel = new JPanel();
        descriptionPanel.setBounds(885, 90, 400, 200);
        descriptionPanel.setBackground(Color.black);

        frame.add(descriptionPanel);

        // -- TEXTO DE DESCRIÇÃO
        descriptionText = new JTextArea();
        descriptionText.setBounds(885, 40, 400, 250);
        descriptionText.setForeground(Color.white);
        descriptionText.setBackground(Color.black);
        descriptionText.setFont(font2);
        descriptionText.setLineWrap(true);
        descriptionText.setWrapStyleWord(true);
        descriptionText.setEditable(false);
        
        descriptionPanel.add(descriptionText);

        // -- UI SE TORNA VISIVEL
        frame.setVisible(true);
    }
    

    // -- FUNÇÃO DE TIMER DE PONTUAÇÃO POR SEGUNDO
    private static void setTimer() {
        timer = new Timer(timerSpeed, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(pointSecond == 0) {
                    points++;
                } else {
                    pointsDouble = pointsDouble + pointSecond;
                    if(pointsDouble > 1) {
                        pointConversor = pointsDouble;
                        points = points + (int)pointConversor;
                        pointsDouble = (int)pointsDouble - pointsDouble;
                    } else {
                        points++;
                    }
                }
                
                pointsLabel.setText(points + " Ecologia");
            }
        });
    }

    // -- FUNÇÃO DE ADICIONAR PONTUAÇÃO POR SEGUNDO
    private static void timerUpdate() {
        if(timerSet == false) {
            timerSet = true;
        }
        else if(timerSet == true) {
            timer.stop();
        }

        double speed = 0;
        double temp = 0;
        if(perSecond > 100) {
            speed = 10;
            temp = perSecond / 100;
            pointSecond = round(temp, 4);
        }
        else {
            speed = 1/perSecond*1000;
            pointSecond = 0;
        }
        timerSpeed = (int)Math.round(speed);

        timerPointsLabel.setText("Por segundo: " + perSecond);

        setTimer();
        timer.start();
    }
    
    // -- FUNÇÕES DOS BOTÕES
    static class pointsHandler implements ActionListener{
        public void actionPerformed(ActionEvent event) {
            String action = event.getActionCommand();

            switch (action) {
                // -- FUNÇÃO DE CLICAR E GANHAR PONTOS
                case "point":
                    points = points + clickPoints;
                    pointsLabel.setText(points + " Ecologia");
                    break;
                // -- FUNÇÕES DE BUILD
                // -- BUILD 1
                case "build1":
                    if(points >= build1Price) {
                        points = points - build1Price;
                        pointsLabel.setText(points + " Ecologia");
                        build1Counter++;
                        buildButton1.setText("CANUDO DE PAPEL (" + build1Counter + ")");
                        build1Price = build1Price + 5;
                        double perSecondSet = perSecond + 0.2;
                        perSecond = round(perSecondSet, 2);
                        descriptionText.setText("CANUDO DE PAPEL \n [Preço: " + build1Price + "]\nUma forma reciclável de sugar liquídos!\n * Gera ecologia automáticamente a cada 5 segundos");
                        timerUpdate();
                    }
                    break;
                // -- BUILD 2
                case "build2":
                    if(points >= build2Price) {
                        points = points - build2Price;
                        pointsLabel.setText(points + " Ecologia");
                        build2Counter++;
                        buildButton2.setText("PLANTAR ÁRVORES (" + build2Counter + ")");
                        build2Price = build2Price + 45;
                        perSecond = perSecond + 2;
                        descriptionText.setText("PLANTAR ÁRVORES \n [Preço: " + build2Price + "]\nRepovoar a terra com árvores!\n * Gera ecologia automáticamente a cada 0,5 segundo");
                        timerUpdate();
                    }
                    break;
                // -- BUILD 3
                case "build3":
                    if(points >= build3Price) {
                        points = points - build3Price;
                        pointsLabel.setText(points + " ecologia");
                        build3Counter++;
                        buildButton3.setText("ENERGIA LIMPA (" + build3Counter + ")");
                        build3Price = build3Price + 425;
                        perSecond = perSecond + 20;
                        descriptionText.setText("ENERGIA LIMPA \n [Preço: " + build3Price + "]\nEnergia solar, eólica e hidro em todo lugar!\n * Gera ecologia automáticamente a cada 0,05 segundo");
                        timerUpdate();
                    }
                    break;
                // -- BUILD 4
                case "build4":
                    if(points >= build4Price) {
                        points = points - build4Price;
                        pointsLabel.setText(points + " Ecologia");
                        build4Counter++;
                        buildButton4.setText("CAPTURA CARBONO (" + build4Counter + ")");
                        build4Price = build4Price + 4000;
                        perSecond = perSecond + 200;
                        descriptionText.setText("CAPTURA CARBONO \n [Preço: " + build4Price + "]\nRemove carbono da onde nós colocamos!\n * Gera ecologia automáticamente a cada 0,005 segundo");
                        timerUpdate();
                    }
                    break;
                // -- FUNÇÕES DE CLICK
                // -- CLICK 1
                case "click1":
                    if(points >= click1Price) {
                        points = points - click1Price;
                        pointsLabel.setText(points + " Ecologia");
                        click1Counter++;
                        clickButton1.setText("SEMEADOR (" + click1Counter + ")");
                        click1Price = (int)(click1Price * 1.25) + 250;
                        clickPoints = clickPoints + 1;
                        descriptionText.setText("SEMEADOR \n [Preço: " + click1Price + "]\nMaior plantio de árvores!\n * Gera mais 1 de ecologia ao clicar");
                    }
                    break;
                // -- CLICK 2
                case "click2":
                    if(points >= click2Price) {
                        points = points - click2Price;
                        pointsLabel.setText(points + " Ecologia");
                        click2Counter++;
                        clickButton2.setText("RECICLADOR (" + click2Counter + ")");
                        click2Price = (int)(click2Price * 1.25) + 2500;
                        clickPoints = clickPoints + 10;
                        descriptionText.setText("RECICLADOR \n [Preço: " + click2Price + "]\nRecicla tudo inutilizado!\n * Gera mais 10 de ecologia ao clicar");
                    }
                    break;
                // -- CLICK 3
                case "click3":
                    if(points >= click3Price) {
                        points = points - click3Price;
                        pointsLabel.setText(points + " Ecologia");
                        click3Counter++;
                        clickButton3.setText("ENERGIZADOR (" + click3Counter + ")");
                        click3Price = (int)(click3Price * 1.25) + 25000;
                        clickPoints = clickPoints + 100;
                        descriptionText.setText("ENERGIZADOR \n [Preço: " + click3Price + "]\nCria energia limpa do nada!\n * Gera mais 100 de ecologia ao clicar");
                    }
                    break;
                // -- CLICK 4
                case "click4":
                    if (points >= click4Price) {
                        click4Counter++;
                        showGif show = new showGif();
                        show.showGifDialog(frame);
                        System.exit(0);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    // -- FUNÇÕES DO MOUSE
    static class mouseHandler implements MouseListener{
        @Override
        public void mouseClicked(MouseEvent e) {

        }
        public void mousePressed(MouseEvent e) {
            
        }
        public void mouseReleased(MouseEvent e) {
            
        }
        // -- FUNÇÕES "HOVER"
        public void mouseEntered(MouseEvent e) {
            JButton button = (JButton)e.getSource();

            // -- HOVER BUILD 1
            if(button == buildButton1) {
                descriptionText.setText("CANUDO DE PAPEL \n [Preço: " + build1Price + "]\nUma forma reciclável de sugar liquídos!\n * Gera ecologia automáticamente a cada 5 segundos");
            }
            // -- HOVER BUILD 2
            else if(button == buildButton2) {
                descriptionText.setText("PLANTAR ÁRVORES \n [Preço: " + build2Price + "]\nRepovoar a terra com árvores!\n * Gera ecologia automáticamente a cada 0,5 segundo");
            }
            // -- HOVER BUILD 3
            else if(button == buildButton3) {
                descriptionText.setText("ENERGIA LIMPA \n [Preço: " + build3Price + "]\nEnergia solar, eólica e hidro em todo lugar!\n * Gera ecologia automáticamente a cada 0,05 segundo");
            }
            // -- HOVER BUILD 4
            else if(button == buildButton4) {
                descriptionText.setText("CAPTURA CARBONO \n [Preço: " + build4Price + "]\nRemove carbono da onde nós colocamos!\n * Gera ecologia automáticamente a cada 0,005 segundo");
            }

            // -- HOVER CLICK 1
            else if(button == clickButton1) {
                descriptionText.setText("SEMEADOR \n [Preço: " + click1Price + "]\nMaior plantio de árvores!\n * Gera mais 1 de ecologia ao clicar");
            }
            // -- HOVER CLICK 2
            else if(button == clickButton2) {
                descriptionText.setText("RECICLADOR \n [Preço: " + click2Price + "]\nRecicla tudo inutilizado!\n * Gera mais 10 de ecologia ao clicar");
            }
            // -- HOVER CLICK 3
            else if(button == clickButton3) {
                descriptionText.setText("ENERGIZADOR \n [Preço: " + click3Price + "]\nCria energia limpa do nada!\n * Gera mais 100 de ecologia ao clicar");
            }
            // -- HOVER CLICK 4
            else if(button == clickButton4) {
                descriptionText.setText("RESTAURADOR \n [Preço: " + click4Price + "]\nTira toda poluição ja feita!");
            }
        }
        // -- FUNÇÕES "UNHOVER"
        public void mouseExited(MouseEvent e) {
            JButton button = (JButton)e.getSource();
            
            if(button == buildButton1 || button == buildButton2 || button == buildButton3 || button == buildButton4 || button == clickButton1 || button == clickButton2 || button == clickButton3 || button == clickButton4) {
                descriptionText.setText(null);
            }
        }
    }

    // -- FUNÇÃO ARREDONDAR VALOR
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}