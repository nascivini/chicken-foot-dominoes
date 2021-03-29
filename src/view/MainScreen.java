/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.MainController;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import model.Jogador;
import model.Move;
import model.Piece;
import model.PosicaoSend;
import model.Position;
import model.Tabuleiro;

/**
 *
 * @author vinicius
 */
public class MainScreen extends javax.swing.JFrame {
    private MainController mainController;
    private Tabuleiro tabuleiro;
    private Piece pecaEscolhida;
    private boolean partidaIniciada;
    private boolean minhaVez;
    private boolean pecasDistribuidas;
    private HashMap<JLabel, Position> pecasNoTabuleiro = new HashMap<>();
    
    /**
     * Creates new form MainScreen
     */
    public MainScreen() {
        setTitle("Chicken-Foot Domino");
        mainController = new MainController(this);
        initComponents();
        preencherLabelsNaTela();
    }
    
    public void preencherLabelsNaTela(){
        GridBagLayout gbl=new GridBagLayout();
        jPanel7.setLayout(gbl);
        
        for(int col = 0; col < 7; col++){
            for(int row = 0; row < 4; row++){
                Position position = new Position(col, row);
                
                GridBagConstraints gbc=new GridBagConstraints();
                
                JLabel label = new JLabel();
                label.setName(col + "_" + row);
                label.addMouseListener(boardClickedListener());
                position.setLabel(label);
                label.setPreferredSize(new Dimension(70,100));
                pecasNoTabuleiro.put(label, position);
                
                gbc.gridy = row;
                gbc.gridx = col;
                jPanel7.add(label, gbc);
            }
        }
        
        jLabel10.setText("");
        jLabel11.setText("");
        jLabel12.setText("");
        jLabel13.setText("");
        jLabel14.setText("");
        jLabel15.setText("");
        jLabel16.setText("");
        jLabel17.setText("");
        jLabel18.setText("");
    }
    
    public MouseListener boardClickedListener(){
        return new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent arg0) {
                        if(!minhaVez){
                            JOptionPane.showMessageDialog(null, "Não é a sua vez");
                            return;
                        }
                        if(partidaIniciada){
                            JLabel source = (JLabel) arg0.getSource();
                            if(pecaEscolhida != null){
                                source.setIcon(pecaEscolhida.getImage());
                                source.setVisible(true);
                                Position p = pecasNoTabuleiro.get(source);
                                
                                enviarJogada(pecaEscolhida, p);
                                pecaEscolhida = null;
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "A partida não foi iniciada");
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent arg0) {
                        
                    }

                    @Override
                    public void mouseReleased(MouseEvent arg0) {
                        
                    }

                    @Override
                    public void mouseEntered(MouseEvent arg0) {
                        
                    }

                    @Override
                    public void mouseExited(MouseEvent arg0) {
                        
                    }
                };
    }
        
    public MouseListener minhasPecasListener(){
            return new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent arg0) {
                        if(!minhaVez){
                            JOptionPane.showMessageDialog(null, "Não é a sua vez");
                            return;
                        }
                        if(partidaIniciada){
                            JLabel source = (JLabel) arg0.getSource();
                            
                            Piece p = tabuleiro.getJogador1().getPecas().get(Integer.parseInt(source.getName()));
                            if(p != null){
                                pecaEscolhida = p;
                                JOptionPane.showMessageDialog(null, "Escolha o local no tabuleiro para colocar a peça");
                            }
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent arg0) {
                        
                    }

                    @Override
                    public void mouseReleased(MouseEvent arg0) {
                        
                    }

                    @Override
                    public void mouseEntered(MouseEvent arg0) {
                        
                    }

                    @Override
                    public void mouseExited(MouseEvent arg0) {
                        
                    }
                };
    }
    
    public void updateStateAndPlayer(boolean newState, String serverName, Jogador jogador){
        Color color = Color.RED;
        if(newState){
            jLabel4.setText("Conectado a " + serverName);
            jLabel8.setText(jogador.getNome());
            color = Color.GREEN;
        }
        else{
            jLabel4.setText("NÃO CONECTADO");
        }
        jButton1.setBackground(color);   
    }
    
    public void partidaIniciada(Jogador jogadorDaVez, boolean iniciadoPorMim){
        jLabel2.setText("VEZ DE " + (iniciadoPorMim ? mainController.eu.getNome() : mainController.outroJogador.getNome()));
        jLabel3.setText(mainController.outroJogador.getNome());
        minhaVez = iniciadoPorMim;
        
        partidaIniciada = true;
        tabuleiro = new Tabuleiro(this, mainController.eu, mainController.outroJogador, iniciadoPorMim);
        Piece primeiraPeca = new Piece(6,6);
        Position posicaoPrimeiraPeca = new Position(3,2);
        if(iniciadoPorMim){
            distribuirPecas();
            enviarJogada(primeiraPeca, posicaoPrimeiraPeca);
        }
        Move moveFake = new Move();
        PosicaoSend posicao = new PosicaoSend(posicaoPrimeiraPeca.getX(), posicaoPrimeiraPeca.getY());
        posicao.setPiece(primeiraPeca);
        moveFake.setPosition(posicao);
        receberJogada(moveFake);
    }
    
    public void distribuirPecas(){
        MouseListener listener = minhasPecasListener();
        int position = 0;
        
        List<Piece> pecas = tabuleiro.getJogador1().getPecas();
        
        jLabel10.setIcon(pecas.get(0).getImage());
        jLabel10.setName("0");
        
        jLabel11.setIcon(pecas.get(1).getImage());
        jLabel11.setName("1");
        
        jLabel12.setIcon(pecas.get(2).getImage());
        jLabel12.setName("2");
        
        jLabel13.setIcon(pecas.get(3).getImage());
        jLabel13.setName("3");
        
        jLabel14.setIcon(pecas.get(4).getImage());
        jLabel14.setName("4");
        
        jLabel15.setIcon(pecas.get(5).getImage());
        jLabel15.setName("5");
        
        jLabel16.setIcon(pecas.get(6).getImage());
        jLabel16.setName("6");
        
        jLabel17.setIcon(pecas.get(7).getImage());
        jLabel17.setName("7");
        
        jLabel18.setIcon(pecas.get(8).getImage());
        jLabel18.setName("8");
        
        jLabel10.addMouseListener(listener);
        jLabel11.addMouseListener(listener);
        jLabel12.addMouseListener(listener);
        jLabel13.addMouseListener(listener);
        jLabel14.addMouseListener(listener);
        jLabel15.addMouseListener(listener);
        jLabel16.addMouseListener(listener);
        jLabel17.addMouseListener(listener);
        jLabel18.addMouseListener(listener);
        pecasDistribuidas = true;
        JOptionPane.showMessageDialog(null, "Partida Iniciada");
    }
    
    
    public void receberJogada(Move move){
        System.out.println("Jogada recebida de " + move.getJogador().getNome());
        if(move != null && move.getPosition() != null){
            JLabel label = findByPosition(new Position(move.getPosition().getX(), move.getPosition().getY()));
            if(label != null){
                label.setIcon(move.getPosition().getPiece().getImage());
            }
        }
        if(!pecasDistribuidas){
            mainController.eu.setPecas(move.getOutroJogador().getPecas());
            distribuirPecas();
        }
        this.minhaVez = true;
        jLabel2.setText("VEZ DE " + mainController.eu.getNome());
    }
    
    private JLabel findByPosition(Position position){
        Optional<JLabel> label = pecasNoTabuleiro.keySet().stream().filter(e -> e.getName().equals(position.getX() + "_" + position.getY())).findFirst();
        if(label.isPresent()){
            return label.get();
        }
        return null;
    }
    
    public void enviarJogada(Piece piece, Position position){
        Move move = new Move();
        move.setJogador(mainController.eu);
        move.setOutroJogador(mainController.outroJogador);
        
        PosicaoSend posicao = new PosicaoSend(position.getX(), position.getY());
        posicao.setPiece(piece);
        
        move.setPosition(posicao);
        
        mainController.sendMove(move);
        this.minhaVez = false;
         jLabel2.setText("VEZ DE " + mainController.outroJogador.getNome());
    }
    
    public void limparCampos(){
        jLabel10.setIcon(null);
        jLabel11.setIcon(null);
        jLabel12.setIcon(null);
        jLabel13.setIcon(null);
        jLabel14.setIcon(null);
        jLabel15.setIcon(null);
        jLabel16.setIcon(null);
        jLabel17.setIcon(null);
        jLabel18.setIcon(null);
        
        for(Position p : this.pecasNoTabuleiro.values()){
            if(p.getLabel() != null){
                p.getLabel().setIcon(null);
            }
        }
        jLabel2.setText("PARTIDA NÃO INICIADA");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(143, 193, 227));

        jPanel2.setBackground(new java.awt.Color(143, 193, 227));

        jPanel1.setBackground(new java.awt.Color(49, 112, 142));
        jPanel1.setForeground(new java.awt.Color(254, 254, 254));
        jPanel1.setToolTipText("");

        jLabel1.setBackground(new java.awt.Color(254, 254, 254));
        jLabel1.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(254, 254, 254));
        jLabel1.setText("Estado:");

        jLabel4.setBackground(new java.awt.Color(254, 254, 254));
        jLabel4.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(254, 254, 254));
        jLabel4.setText("NÃO CONECTADO");

        jButton1.setBackground(new java.awt.Color(255, 0, 0));
        jButton1.setForeground(new java.awt.Color(255, 0, 0));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(254, 254, 254));
        jLabel2.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(254, 254, 254));
        jLabel2.setText("PARTIDA NÃO INICIADA");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(80, 133, 165));

        jLabel3.setBackground(new java.awt.Color(254, 254, 254));
        jLabel3.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(254, 254, 254));
        jLabel3.setText("Nenhum outro jogador conectado");

        jLabel5.setBackground(new java.awt.Color(254, 254, 254));
        jLabel5.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(254, 254, 254));
        jLabel5.setText("Peças:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(14, 14, 14)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(104, 120, 100));

        jLabel7.setBackground(new java.awt.Color(254, 254, 254));
        jLabel7.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(254, 254, 254));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Galinheiro");

        jButton2.setText("Comprar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 322, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(30, 30, 30))
        );

        jPanel5.setBackground(new java.awt.Color(80, 133, 165));

        jLabel8.setBackground(new java.awt.Color(254, 254, 254));
        jLabel8.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(254, 254, 254));
        jLabel8.setText("Jogador:");

        jLabel9.setBackground(new java.awt.Color(254, 254, 254));
        jLabel9.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(254, 254, 254));
        jLabel9.setText("Peças:");

        jPanel8.setBackground(new java.awt.Color(80, 133, 165));

        jLabel10.setText("jLabel6");

        jLabel11.setText("jLabel6");

        jLabel12.setText("jLabel6");

        jLabel13.setText("jLabel6");

        jLabel14.setText("jLabel6");

        jLabel15.setText("jLabel6");

        jLabel16.setText("jLabel6");

        jLabel17.setText("jLabel6");

        jLabel18.setText("jLabel6");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(jLabel16)
                .addGap(25, 25, 25)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel13)
                    .addComponent(jLabel16)
                    .addComponent(jLabel18)
                    .addComponent(jLabel15)
                    .addComponent(jLabel14))
                .addContainerGap())
        );

        jPanel8Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel10, jLabel11, jLabel12, jLabel13, jLabel14, jLabel15, jLabel16, jLabel17, jLabel18});

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(35, 35, 35)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setLayout(new java.awt.GridBagLayout());

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu1.setText("Ações");

        jMenuItem1.setText("Iniciar nova partida");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Conectar");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Desconectar");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getAccessibleContext().setAccessibleName("Chicken Foot Dominoes");
        getAccessibleContext().setAccessibleDescription("Dominó \"pé de galinha\"");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        mainController.enviarInicioPartida();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        String servidor = JOptionPane.showInputDialog("Insira o IP do servidor", "localhost");
        if(servidor == null || servidor.isEmpty()){
            JOptionPane.showMessageDialog(null, "É necessário informar um ip válido.");
            return;
        }
        String nomeJogador = JOptionPane.showInputDialog("Nome do Jogador");
        if(nomeJogador == null || nomeJogador.isEmpty()){
            JOptionPane.showMessageDialog(null, "É necessário informar um nome válido.");
            return;
        }
        boolean result = mainController.conectar(servidor, nomeJogador);
        if(result){
            JOptionPane.showMessageDialog(null, "Conexão realizada com sucesso");
            return;
        }
        JOptionPane.showMessageDialog(null, "Não foi possível conectar");
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        mainController.desconectar();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    // End of variables declaration//GEN-END:variables
}
