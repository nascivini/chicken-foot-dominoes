/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ImageUtils;
import controller.MainController;

import java.awt.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.Border;

import model.dominiointerface.Posicao;
import model.dominiointerface.StatusConexao;
import model.dominioproblema.Jogador;
import model.dominioproblema.Peca;

/**
 * @author vinicius
 */
public class MainScreen extends javax.swing.JFrame {

  private MainController mainController;
  private ImageUtils imageUtils;
  private List<JLabel> labelsMinhasPecas = new ArrayList<JLabel>();
  private List<JLabel> labelsTabuleiro = new ArrayList<>();
  JPanel panelTabuleiro = new JPanel();

  /**
   * Creates new form MainScreen
   */
  public MainScreen(MainController mainController) {
    this.mainController = mainController;
    imageUtils = new ImageUtils();
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    
    setTitle("Chicken-Foot Domino");
    initComponents();
    tornarTelaResponsiva();
    preencherLabelsMinhasPecas();
    preencherTabuleiroComJLabels();
  }

  public void tornarTelaResponsiva() {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    this.setPreferredSize(screenSize);

    //Barra de informações
    jPanel1.setPreferredSize(new Dimension(screenSize.width, (int) (screenSize.height * 0.05)));

    //Painel outro jogador
    jPanel3.setPreferredSize(new Dimension(screenSize.width, (int) (screenSize.height * 0.08)));

    jPanel6.setPreferredSize(new Dimension(screenSize.width, (int) (screenSize.height * 0.67)));
//    jPanel7.setPreferredSize(getSizeBasedOnComponent(jPanel6, 1, 1));

    //Painel minhas peças
    jPanel2.setPreferredSize(new Dimension(screenSize.width, (int) (screenSize.height * 0.20)));
    jScrollPane1.setPreferredSize(getSizeBasedOnComponent(jPanel2, 0.8, 1));
  }

  private Dimension getSizeBasedOnComponent(Component jPanel, double percentWidth, double percentHeight) {
    Dimension screenSize = jPanel.getSize();

    return new Dimension((int) (screenSize.width * percentWidth), (int) (screenSize.height * percentHeight));
  }

  public void alterarStatusConexao(StatusConexao novoStatus, String mensagemAdicional) {
    jLabel4.setText(novoStatus.getMessage() + mensagemAdicional);
    jButton1.setBackground(novoStatus.getColor());
  }

  public void atualizarJogadorDestaInstancia(String nomeJogador) {
    jLabel5.setText("Jogador: " + nomeJogador);
  }

  public void atualizarJogadorDaOutraInstancia(String nomeJogador) {
    jLabel5.setText("Jogador: " + nomeJogador);
  }

  public void atualizarVez(String vez){
    jLabel2.setText(vez);
  }

  public void atualizarStatusDaPartida(String novoStatus){
    jLabel3.setText(novoStatus);
  }

  public void atualizarGalinheiro(int novaQuantidade){
    jLabel7.setText("Galinheiro: " + novaQuantidade);
  }

  public void limparTodosOsCampos() {
    jLabel5.setText("");
    jLabel3.setText("Nenhum outro jogador conectado");
  }

  public void adicionarPosicaoNaTela(Posicao posicao){
    JLabel label = labelsTabuleiro.stream()
        .filter(e -> e.getName().equals(posicao.getPositionX() + "_" + posicao.getPositionY()))
        .findFirst()
        .orElse(null);

    panelTabuleiro.removeAll();

    labelsTabuleiro.forEach((e) -> {
          if(e.equals(label)){
            panelTabuleiro.add(posicao.getjLabel());
          }
          else{
            panelTabuleiro.add(e);
          }
        }
    );
    panelTabuleiro.revalidate();
    panelTabuleiro.repaint();
  }

  public void carregarPecasJogadorAtual(List<Peca> pecas) {
    for (Peca peca : pecas) {
      JLabel labelPeca = getNextAvaliableJLabel();
      labelPeca.setText("");
      labelPeca.setLayout(new BoxLayout(labelPeca, BoxLayout.Y_AXIS));

      Dimension tamanhoPeca = getSizeBasedOnComponent(labelPeca, 1, 0.45);
      JLabel pecaCima = new JLabel();
      pecaCima.setPreferredSize(tamanhoPeca);
      pecaCima.setIcon(imageUtils.getImage(peca.getLadoA(), tamanhoPeca.width, tamanhoPeca.height));

      Dimension tamanhoLinha = getSizeBasedOnComponent(labelPeca, 1, 0.1);
      JLabel linhaMeio = new JLabel();
      linhaMeio.setPreferredSize(tamanhoLinha);
      linhaMeio.setIcon(imageUtils.getMiddleImage(tamanhoLinha.width, tamanhoLinha.height));

      JLabel pecaBaixo = new JLabel();
      pecaBaixo.setPreferredSize(tamanhoPeca);
      pecaBaixo.setIcon(imageUtils.getImage(peca.getLadoB(), tamanhoPeca.width, tamanhoPeca.height));

      labelPeca.add(pecaCima);
      labelPeca.add(linhaMeio);
      labelPeca.add(pecaBaixo);

      peca.setjLabel(labelPeca);
      labelPeca.setName(peca.getLadoA() + "_" + peca.getLadoB());
    }
    jPanel5.setVisible(true);
  }
  
  public JLabel getNextAvaliableJLabel(){
    JLabel labelFound = labelsMinhasPecas
        .stream()
        .filter(e -> e.getIcon() == null)
        .findFirst()
        .orElse(null);
    if(labelFound != null){
      labelsMinhasPecas.remove(labelFound);
    }
    return labelFound;
  }

  private void preencherLabelsMinhasPecas(){
    labelsMinhasPecas.add(jLabel12);
    labelsMinhasPecas.add(jLabel20);
    labelsMinhasPecas.add(jLabel21);
    labelsMinhasPecas.add(jLabel22);
    labelsMinhasPecas.add(jLabel23);
    labelsMinhasPecas.add(jLabel24);
    labelsMinhasPecas.add(jLabel25);
    labelsMinhasPecas.add(jLabel26);
    labelsMinhasPecas.add(jLabel27);
    labelsMinhasPecas.add(jLabel28);
    labelsMinhasPecas.add(jLabel29);
    labelsMinhasPecas.add(jLabel30);
    labelsMinhasPecas.add(jLabel31);
    labelsMinhasPecas.add(jLabel32);
    labelsMinhasPecas.add(jLabel33);
    labelsMinhasPecas.add(jLabel34);
    labelsMinhasPecas.add(jLabel35);
    labelsMinhasPecas.add(jLabel36);
    labelsMinhasPecas.add(jLabel37);
    labelsMinhasPecas.add(jLabel38);
    labelsMinhasPecas.add(jLabel40);

    labelsMinhasPecas.forEach(e -> e.addMouseListener(minhasPecasListener()));
    jPanel5.setVisible(false);
    jScrollPane1.getViewport().setBackground(new Color(80,133,165));
  }

  public MouseListener boardClickedListener() {
    return new MouseListener() {
      @Override
      public void mouseClicked(MouseEvent arg0) {

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

  public MouseListener minhasPecasListener() {
    return new MouseListener() {
      @Override
      public void mouseClicked(MouseEvent arg0) {
        JLabel label  = (JLabel) arg0.getSource();
        mainController.pecaEscolhidaPeloJogador(label);
      }

      @Override
      public void mousePressed(MouseEvent arg0) {
        JLabel label  = (JLabel) arg0.getSource();
        mainController.pecaEscolhidaPeloJogador(label);
        System.out.println("Clicou em " + label.getName());
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

  private void preencherTabuleiroComJLabels(){
    jScrollPane3.getViewport().setBackground(new Color(49,112,142));
    panelTabuleiro.setLayout(new GridLayout(55,55));
    for(int i = 0; i < 55; i++){
      for(int j = 0; j < 55; j++){
        JLabel label = new JLabel();
        label.setName(i + "_" + j);
        label.setPreferredSize(new Dimension(120,120));
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        label.setBorder(border);
        label.addMouseListener(boardClickedListener());

        labelsTabuleiro.add(label);
	      panelTabuleiro.add(label);
      }
    }
    jScrollPane3.setViewportView(panelTabuleiro);
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jLabel39 = new javax.swing.JLabel();
    jComboBox1 = new javax.swing.JComboBox<>();
    jPanel3 = new javax.swing.JPanel();
    jLabel3 = new javax.swing.JLabel();
    jLabel7 = new javax.swing.JLabel();
    jButton2 = new javax.swing.JButton();
    jPanel1 = new javax.swing.JPanel();
    jLabel1 = new javax.swing.JLabel();
    jLabel4 = new javax.swing.JLabel();
    jButton1 = new javax.swing.JButton();
    jLabel2 = new javax.swing.JLabel();
    jPanel2 = new javax.swing.JPanel();
    jLabel6 = new javax.swing.JLabel();
    jLabel5 = new javax.swing.JLabel();
    jScrollPane1 = new javax.swing.JScrollPane();
    jPanel5 = new javax.swing.JPanel();
    jLabel12 = new javax.swing.JLabel();
    jLabel20 = new javax.swing.JLabel();
    jLabel21 = new javax.swing.JLabel();
    jLabel22 = new javax.swing.JLabel();
    jLabel23 = new javax.swing.JLabel();
    jLabel24 = new javax.swing.JLabel();
    jLabel25 = new javax.swing.JLabel();
    jLabel26 = new javax.swing.JLabel();
    jLabel27 = new javax.swing.JLabel();
    jLabel28 = new javax.swing.JLabel();
    jLabel29 = new javax.swing.JLabel();
    jLabel30 = new javax.swing.JLabel();
    jLabel31 = new javax.swing.JLabel();
    jLabel32 = new javax.swing.JLabel();
    jLabel33 = new javax.swing.JLabel();
    jLabel34 = new javax.swing.JLabel();
    jLabel35 = new javax.swing.JLabel();
    jLabel36 = new javax.swing.JLabel();
    jLabel37 = new javax.swing.JLabel();
    jLabel38 = new javax.swing.JLabel();
    jLabel40 = new javax.swing.JLabel();
    jPanel6 = new javax.swing.JPanel();
    jScrollPane3 = new javax.swing.JScrollPane();
    jMenuBar1 = new javax.swing.JMenuBar();
    jMenu1 = new javax.swing.JMenu();
    jMenuItem1 = new javax.swing.JMenuItem();
    jMenuItem2 = new javax.swing.JMenuItem();
    jMenuItem3 = new javax.swing.JMenuItem();

    jLabel39.setForeground(new java.awt.Color(10, 0, 0));
    jLabel39.setText("jLabel12");
    jLabel39.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    jLabel39.setMaximumSize(new java.awt.Dimension(200, 100));
    jLabel39.setMinimumSize(new java.awt.Dimension(200, 100));
    jLabel39.setName(""); // NOI18N
    jLabel39.setPreferredSize(new java.awt.Dimension(200, 100));

    jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setBackground(new java.awt.Color(143, 193, 227));

    jPanel3.setBackground(new java.awt.Color(80, 133, 165));

    jLabel3.setBackground(new java.awt.Color(254, 254, 254));
    jLabel3.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
    jLabel3.setForeground(new java.awt.Color(254, 254, 254));
    jLabel3.setText("Nenhum outro jogador conectado");

    jLabel7.setBackground(new java.awt.Color(254, 254, 254));
    jLabel7.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
    jLabel7.setForeground(new java.awt.Color(254, 254, 254));
    jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel7.setText("Galinheiro: 0");

    jButton2.setText("Comprar");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton2ActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
    jPanel3.setLayout(jPanel3Layout);
    jPanel3Layout.setHorizontalGroup(
      jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel3Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(jPanel3Layout.createSequentialGroup()
            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, Short.MAX_VALUE))
          .addGroup(jPanel3Layout.createSequentialGroup()
            .addComponent(jLabel7)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel3)))
        .addContainerGap())
    );
    jPanel3Layout.setVerticalGroup(
      jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel3Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel7)
          .addComponent(jLabel3))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jButton2)
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

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
        .addGap(12, 12, 12)
        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(12, 12, 12)
        .addComponent(jLabel1)
        .addGap(6, 6, 6)
        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(jLabel2)
        .addContainerGap())
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addGap(12, 12, 12)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel1)
          .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
            .addComponent(jLabel4)
            .addComponent(jLabel2)))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jPanel2.setBackground(new java.awt.Color(80, 133, 165));
    jPanel2.setForeground(new java.awt.Color(80, 133, 165));

    jLabel6.setText("Peças ->");

    jLabel5.setText("Jogador");

    jScrollPane1.setBackground(new java.awt.Color(80, 133, 165));
    jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
    jScrollPane1.setForeground(new java.awt.Color(80, 133, 165));
    jScrollPane1.setOpaque(false);

    jPanel5.setBackground(new java.awt.Color(80, 133, 165));
    jPanel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

    jLabel12.setForeground(new java.awt.Color(10, 0, 0));
    jLabel12.setText("jLabel12");
    jLabel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    jLabel12.setMaximumSize(new java.awt.Dimension(200, 100));
    jLabel12.setMinimumSize(new java.awt.Dimension(200, 100));
    jLabel12.setName(""); // NOI18N
    jLabel12.setPreferredSize(new java.awt.Dimension(200, 100));

    jLabel20.setForeground(new java.awt.Color(10, 0, 0));
    jLabel20.setText("jLabel12");
    jLabel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    jLabel20.setMaximumSize(new java.awt.Dimension(200, 100));
    jLabel20.setMinimumSize(new java.awt.Dimension(200, 100));
    jLabel20.setName(""); // NOI18N
    jLabel20.setPreferredSize(new java.awt.Dimension(200, 100));

    jLabel21.setForeground(new java.awt.Color(10, 0, 0));
    jLabel21.setText("jLabel12");
    jLabel21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    jLabel21.setMaximumSize(new java.awt.Dimension(200, 100));
    jLabel21.setMinimumSize(new java.awt.Dimension(200, 100));
    jLabel21.setName(""); // NOI18N
    jLabel21.setPreferredSize(new java.awt.Dimension(200, 100));

    jLabel22.setForeground(new java.awt.Color(10, 0, 0));
    jLabel22.setText("jLabel12");
    jLabel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    jLabel22.setMaximumSize(new java.awt.Dimension(200, 100));
    jLabel22.setMinimumSize(new java.awt.Dimension(200, 100));
    jLabel22.setName(""); // NOI18N
    jLabel22.setPreferredSize(new java.awt.Dimension(200, 100));

    jLabel23.setForeground(new java.awt.Color(10, 0, 0));
    jLabel23.setText("jLabel12");
    jLabel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    jLabel23.setMaximumSize(new java.awt.Dimension(200, 100));
    jLabel23.setMinimumSize(new java.awt.Dimension(200, 100));
    jLabel23.setName(""); // NOI18N
    jLabel23.setPreferredSize(new java.awt.Dimension(200, 100));

    jLabel24.setForeground(new java.awt.Color(10, 0, 0));
    jLabel24.setText("jLabel12");
    jLabel24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    jLabel24.setMaximumSize(new java.awt.Dimension(200, 100));
    jLabel24.setMinimumSize(new java.awt.Dimension(200, 100));
    jLabel24.setName(""); // NOI18N
    jLabel24.setPreferredSize(new java.awt.Dimension(200, 100));

    jLabel25.setForeground(new java.awt.Color(10, 0, 0));
    jLabel25.setText("jLabel12");
    jLabel25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    jLabel25.setMaximumSize(new java.awt.Dimension(200, 100));
    jLabel25.setMinimumSize(new java.awt.Dimension(200, 100));
    jLabel25.setName(""); // NOI18N
    jLabel25.setPreferredSize(new java.awt.Dimension(200, 100));

    jLabel26.setForeground(new java.awt.Color(10, 0, 0));
    jLabel26.setText("jLabel12");
    jLabel26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    jLabel26.setMaximumSize(new java.awt.Dimension(200, 100));
    jLabel26.setMinimumSize(new java.awt.Dimension(200, 100));
    jLabel26.setName(""); // NOI18N
    jLabel26.setPreferredSize(new java.awt.Dimension(200, 100));

    jLabel27.setForeground(new java.awt.Color(10, 0, 0));
    jLabel27.setText("jLabel12");
    jLabel27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    jLabel27.setMaximumSize(new java.awt.Dimension(200, 100));
    jLabel27.setMinimumSize(new java.awt.Dimension(200, 100));
    jLabel27.setName(""); // NOI18N
    jLabel27.setPreferredSize(new java.awt.Dimension(200, 100));

    jLabel28.setForeground(new java.awt.Color(10, 0, 0));
    jLabel28.setText("jLabel12");
    jLabel28.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    jLabel28.setMaximumSize(new java.awt.Dimension(200, 100));
    jLabel28.setMinimumSize(new java.awt.Dimension(200, 100));
    jLabel28.setName(""); // NOI18N
    jLabel28.setPreferredSize(new java.awt.Dimension(200, 100));

    jLabel29.setForeground(new java.awt.Color(10, 0, 0));
    jLabel29.setText("jLabel12");
    jLabel29.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    jLabel29.setMaximumSize(new java.awt.Dimension(200, 100));
    jLabel29.setMinimumSize(new java.awt.Dimension(200, 100));
    jLabel29.setName(""); // NOI18N
    jLabel29.setPreferredSize(new java.awt.Dimension(200, 100));

    jLabel30.setForeground(new java.awt.Color(10, 0, 0));
    jLabel30.setText("jLabel12");
    jLabel30.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    jLabel30.setMaximumSize(new java.awt.Dimension(200, 100));
    jLabel30.setMinimumSize(new java.awt.Dimension(200, 100));
    jLabel30.setName(""); // NOI18N
    jLabel30.setPreferredSize(new java.awt.Dimension(200, 100));

    jLabel31.setForeground(new java.awt.Color(10, 0, 0));
    jLabel31.setText("jLabel12");
    jLabel31.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    jLabel31.setMaximumSize(new java.awt.Dimension(200, 100));
    jLabel31.setMinimumSize(new java.awt.Dimension(200, 100));
    jLabel31.setName(""); // NOI18N
    jLabel31.setPreferredSize(new java.awt.Dimension(200, 100));

    jLabel32.setForeground(new java.awt.Color(10, 0, 0));
    jLabel32.setText("jLabel12");
    jLabel32.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    jLabel32.setMaximumSize(new java.awt.Dimension(200, 100));
    jLabel32.setMinimumSize(new java.awt.Dimension(200, 100));
    jLabel32.setName(""); // NOI18N
    jLabel32.setPreferredSize(new java.awt.Dimension(200, 100));

    jLabel33.setForeground(new java.awt.Color(10, 0, 0));
    jLabel33.setText("jLabel12");
    jLabel33.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    jLabel33.setMaximumSize(new java.awt.Dimension(200, 100));
    jLabel33.setMinimumSize(new java.awt.Dimension(200, 100));
    jLabel33.setName(""); // NOI18N
    jLabel33.setPreferredSize(new java.awt.Dimension(200, 100));

    jLabel34.setForeground(new java.awt.Color(10, 0, 0));
    jLabel34.setText("jLabel12");
    jLabel34.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    jLabel34.setMaximumSize(new java.awt.Dimension(200, 100));
    jLabel34.setMinimumSize(new java.awt.Dimension(200, 100));
    jLabel34.setName(""); // NOI18N
    jLabel34.setPreferredSize(new java.awt.Dimension(200, 100));

    jLabel35.setForeground(new java.awt.Color(10, 0, 0));
    jLabel35.setText("jLabel12");
    jLabel35.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    jLabel35.setMaximumSize(new java.awt.Dimension(200, 100));
    jLabel35.setMinimumSize(new java.awt.Dimension(200, 100));
    jLabel35.setName(""); // NOI18N
    jLabel35.setPreferredSize(new java.awt.Dimension(200, 100));

    jLabel36.setForeground(new java.awt.Color(10, 0, 0));
    jLabel36.setText("jLabel12");
    jLabel36.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    jLabel36.setMaximumSize(new java.awt.Dimension(200, 100));
    jLabel36.setMinimumSize(new java.awt.Dimension(200, 100));
    jLabel36.setName(""); // NOI18N
    jLabel36.setPreferredSize(new java.awt.Dimension(200, 100));

    jLabel37.setForeground(new java.awt.Color(10, 0, 0));
    jLabel37.setText("jLabel12");
    jLabel37.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    jLabel37.setMaximumSize(new java.awt.Dimension(200, 100));
    jLabel37.setMinimumSize(new java.awt.Dimension(200, 100));
    jLabel37.setName(""); // NOI18N
    jLabel37.setPreferredSize(new java.awt.Dimension(200, 100));

    jLabel38.setForeground(new java.awt.Color(10, 0, 0));
    jLabel38.setText("jLabel12");
    jLabel38.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    jLabel38.setMaximumSize(new java.awt.Dimension(200, 100));
    jLabel38.setMinimumSize(new java.awt.Dimension(200, 100));
    jLabel38.setName(""); // NOI18N
    jLabel38.setPreferredSize(new java.awt.Dimension(200, 100));

    jLabel40.setForeground(new java.awt.Color(10, 0, 0));
    jLabel40.setText("jLabel12");
    jLabel40.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    jLabel40.setMaximumSize(new java.awt.Dimension(200, 100));
    jLabel40.setMinimumSize(new java.awt.Dimension(200, 100));
    jLabel40.setName(""); // NOI18N
    jLabel40.setPreferredSize(new java.awt.Dimension(200, 100));

    javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
    jPanel5.setLayout(jPanel5Layout);
    jPanel5Layout.setHorizontalGroup(
      jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel5Layout.createSequentialGroup()
        .addGap(22, 22, 22)
        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(jPanel5Layout.createSequentialGroup()
            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addGroup(jPanel5Layout.createSequentialGroup()
            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jPanel5Layout.setVerticalGroup(
      jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel5Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(22, 22, 22)
        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jScrollPane1.setViewportView(jPanel5);

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel2Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jLabel6)
          .addComponent(jLabel5))
        .addGap(30, 30, 30)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
    );
    jPanel2Layout.setVerticalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel2Layout.createSequentialGroup()
        .addGap(48, 48, 48)
        .addComponent(jLabel5)
        .addGap(18, 18, 18)
        .addComponent(jLabel6)
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
      .addGroup(jPanel2Layout.createSequentialGroup()
        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(0, 0, Short.MAX_VALUE))
    );

    jPanel6.setBackground(new java.awt.Color(49, 112, 142));

    jScrollPane3.setBackground(new java.awt.Color(49, 112, 142));

    javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
    jPanel6.setLayout(jPanel6Layout);
    jPanel6Layout.setHorizontalGroup(
      jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jScrollPane3)
    );
    jPanel6Layout.setVerticalGroup(
      jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jScrollPane3)
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
      .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(0, 0, 0)
        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(0, 0, 0)
        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGap(0, 0, 0)
        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
    );

    getAccessibleContext().setAccessibleName("Chicken Foot Dominoes");
    getAccessibleContext().setAccessibleDescription("Dominó \"pé de galinha\"");

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
    mainController.partidaIniciadaPorJogadorAtual();
  }//GEN-LAST:event_jMenuItem1ActionPerformed

  private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
    String servidor = JOptionPane.showInputDialog("Insira o IP do servidor", "localhost");
    if (servidor == null || servidor.isEmpty()) {
      JOptionPane.showMessageDialog(null, "É necessário informar um ip válido.");
      return;
    }
    String nomeJogador = JOptionPane.showInputDialog("Nome do Jogador");
    if (nomeJogador == null || nomeJogador.isEmpty()) {
      JOptionPane.showMessageDialog(null, "É necessário informar um nome válido.");
      return;
    }
    boolean connected = mainController.conectar(servidor, nomeJogador);
    if (connected) {
      JOptionPane.showMessageDialog(null, "Conectado com sucesso a " + servidor);
      return;
    }
    JOptionPane.showMessageDialog(null, "Não foi possível conectar");
  }//GEN-LAST:event_jMenuItem2ActionPerformed

  private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
    boolean desconectado = mainController.desconectar();
    if (!desconectado) {
      JOptionPane.showMessageDialog(null, "Não foi possível desconectar");
    }
    limparTodosOsCampos();
    JOptionPane.showMessageDialog(null, "Desconectado com sucesso!");
  }//GEN-LAST:event_jMenuItem3ActionPerformed

  private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_jButton1ActionPerformed

  private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_jButton2ActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton jButton1;
  private javax.swing.JButton jButton2;
  private javax.swing.JComboBox<String> jComboBox1;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel12;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel20;
  private javax.swing.JLabel jLabel21;
  private javax.swing.JLabel jLabel22;
  private javax.swing.JLabel jLabel23;
  private javax.swing.JLabel jLabel24;
  private javax.swing.JLabel jLabel25;
  private javax.swing.JLabel jLabel26;
  private javax.swing.JLabel jLabel27;
  private javax.swing.JLabel jLabel28;
  private javax.swing.JLabel jLabel29;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel30;
  private javax.swing.JLabel jLabel31;
  private javax.swing.JLabel jLabel32;
  private javax.swing.JLabel jLabel33;
  private javax.swing.JLabel jLabel34;
  private javax.swing.JLabel jLabel35;
  private javax.swing.JLabel jLabel36;
  private javax.swing.JLabel jLabel37;
  private javax.swing.JLabel jLabel38;
  private javax.swing.JLabel jLabel39;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel40;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JLabel jLabel6;
  private javax.swing.JLabel jLabel7;
  private javax.swing.JMenu jMenu1;
  private javax.swing.JMenuBar jMenuBar1;
  private javax.swing.JMenuItem jMenuItem1;
  private javax.swing.JMenuItem jMenuItem2;
  private javax.swing.JMenuItem jMenuItem3;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JPanel jPanel3;
  private javax.swing.JPanel jPanel5;
  private javax.swing.JPanel jPanel6;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JScrollPane jScrollPane3;
  // End of variables declaration//GEN-END:variables
}
