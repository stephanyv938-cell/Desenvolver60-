package com.mycompany.desenvolver60;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

public class Janela1 extends javax.swing.JFrame {

    private boolean falando = false;
    private int nivelFonte = 0;
    private javax.swing.JPopupMenu menuPrincipal;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Janela1.class.getName());

    //ler a tela
    private void falar(String texto) {

        if (falando) {
            return;
        }

        falando = true;
        btnAudio.setEnabled(false);

        new Thread(() -> {
            try {
                String textoSeguro = texto.replace("'", "''");

                String comando
                        = "Add-Type -AssemblyName System.Speech; "
                        + "$voz = New-Object "
                        + "System.Speech.Synthesis.SpeechSynthesizer; "
                        + "$voz.Speak('" + textoSeguro + "');";

                Process processo = new ProcessBuilder(
                        "powershell",
                        "-NoProfile",
                        "-Command",
                        comando
                ).start();

                processo.waitFor();

            } catch (Exception e) {
                e.printStackTrace();

                javax.swing.SwingUtilities.invokeLater(() -> {
                    javax.swing.JOptionPane.showMessageDialog(
                            this,
                            "Não foi possível iniciar o áudio."
                    );
                });

            } finally {
                falando = false;

                javax.swing.SwingUtilities.invokeLater(() -> {
                    btnAudio.setEnabled(true);
                });
            }
        }).start();
    }

    private String obterTextoDaTela() {
        return "Seja bem-vindo. "
                + "Aprenda a usar celular e internet com facilidade. "
                + "Clique em começar agora para iniciar as atividades. "
                + "Você também pode criar uma conta "
                + "ou entrar em uma conta existente.";
    }

    private void criarMenu() {

        menuPrincipal = new javax.swing.JPopupMenu();
        menuPrincipal.setBorder(null);

        javax.swing.JPanel painelMenu = new javax.swing.JPanel();

        painelMenu.setBackground(new java.awt.Color(94, 164, 166));
        painelMenu.setPreferredSize(new java.awt.Dimension(230, 330));
        painelMenu.setMinimumSize(new java.awt.Dimension(230, 330));
        painelMenu.setMaximumSize(new java.awt.Dimension(230, 330));
        menuPrincipal.setPreferredSize(new java.awt.Dimension(230, 330));

        painelMenu.setLayout(
                new javax.swing.BoxLayout(
                        painelMenu,
                        javax.swing.BoxLayout.Y_AXIS
                )
        );

        javax.swing.JButton btnInicio
                = criarBotaoMenu("INÍCIO");

        javax.swing.JButton btnAtividades
                = criarBotaoMenu("ATIVIDADES");

        javax.swing.JButton btnAjuda
                = criarBotaoMenu("AJUDA");

        javax.swing.JButton btnSair
                = criarBotaoMenu("SAIR");

        btnInicio.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        btnAtividades.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        btnAjuda.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        btnSair.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);

        // Deixa INÍCIO destacado
        btnInicio.setEnabled(false);
        btnInicio.setBackground(new java.awt.Color(246, 211, 71));
        btnInicio.setForeground(new java.awt.Color(20, 82, 84));
        btnInicio.setContentAreaFilled(true);
        btnInicio.setOpaque(true);
        btnInicio.setBorderPainted(false);

        btnInicio.setBorder(
                javax.swing.BorderFactory.createLineBorder(
                        new java.awt.Color(246, 211, 71),
                        3,
                        true
                )
        );

        painelMenu.add(javax.swing.Box.createVerticalStrut(35));

        painelMenu.add(btnInicio);
        painelMenu.add(javax.swing.Box.createVerticalStrut(12));

        painelMenu.add(btnAtividades);
        painelMenu.add(javax.swing.Box.createVerticalStrut(12));

        painelMenu.add(btnAjuda);
        painelMenu.add(javax.swing.Box.createVerticalStrut(15));

        javax.swing.JSeparator separador
                = new javax.swing.JSeparator();

        separador.setMaximumSize(
                new java.awt.Dimension(190, 3)
        );

        painelMenu.add(separador);
        painelMenu.add(javax.swing.Box.createVerticalStrut(15));

        painelMenu.add(btnSair);

        // Ações
        btnInicio.addActionListener(e -> {
            menuPrincipal.setVisible(false);
        });

        btnAtividades.addActionListener(e -> {
            menuPrincipal.setVisible(false);

            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "A tela de atividades será criada depois."
            );
        });

        btnAjuda.addActionListener(e -> {
            menuPrincipal.setVisible(false);

            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Aqui ficará a área de ajuda."
            );
        });

        btnSair.addActionListener(e -> {

            int resposta
                    = javax.swing.JOptionPane.showConfirmDialog(
                            this,
                            "Deseja realmente sair do aplicativo?",
                            "Sair",
                            javax.swing.JOptionPane.YES_NO_OPTION
                    );

            if (resposta == javax.swing.JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        menuPrincipal.add(painelMenu);
    }

    private javax.swing.JButton criarBotaoMenu(String texto) {

        javax.swing.JButton botaoMenu
                = new javax.swing.JButton(texto);

        botaoMenu.setMaximumSize(
                new java.awt.Dimension(190, 48)
        );

        botaoMenu.setPreferredSize(
                new java.awt.Dimension(190, 48)
        );

        botaoMenu.setFont(
                new java.awt.Font(
                        "Times New Roman",
                        java.awt.Font.BOLD,
                        22
                )
        );

        botaoMenu.setForeground(java.awt.Color.WHITE);

        botaoMenu.setBackground(
                new java.awt.Color(94, 164, 166)
        );

        botaoMenu.setBorderPainted(false);
        botaoMenu.setFocusPainted(false);
        botaoMenu.setContentAreaFilled(false);

        botaoMenu.setCursor(
                java.awt.Cursor.getPredefinedCursor(
                        java.awt.Cursor.HAND_CURSOR
                )
        );

        return botaoMenu;
    }

    private void alterarTamanhoFontes(
            java.awt.Container container,
            float aumento) {

        for (java.awt.Component componente : container.getComponents()) {

            java.awt.Font fonteAtual = componente.getFont();

            if (fonteAtual != null) {
                float novoTamanho
                        = fonteAtual.getSize2D() + aumento;

                componente.setFont(
                        fonteAtual.deriveFont(novoTamanho)
                );
            }

            if (componente instanceof java.awt.Container) {
                alterarTamanhoFontes(
                        (java.awt.Container) componente,
                        aumento
                );
            }
        }

        container.revalidate();
        container.repaint();
    }

    public Janela1() {
        initComponents();
        setResizable(false);
        criarMenu();
        getContentPane().setBackground(new Color(244, 232, 184));
        botao.setToolTipText("Clique aqui para iniciar as atividades");
        jLabelTitle.setFont(FonteUtil.carregarFonte("AbrilFatface-Regular.ttf", 44f));
        jlabel2.setFont(FonteUtil.carregarFonte("LeagueSpartan-VariableFont_wght.ttf", 18f));

        jlabel2.setText("<html><b>APRENDA A USAR CELULAR <br> E INTERNET COM FACILIDADE</b></html>");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabelTitle = new javax.swing.JLabel();
        jlabel2 = new javax.swing.JLabel();
        botao = new com.mycompany.desenvolver60.RoundedButton();
        botao1 = new com.mycompany.desenvolver60.RoundedButton();
        jLabel1 = new javax.swing.JLabel();
        botao3 = new com.mycompany.desenvolver60.RoundedButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btnMenu = new javax.swing.JButton();
        btnAumentarFonte = new javax.swing.JButton();
        btnDiminuirFonte = new javax.swing.JButton();
        btnAudio = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(244, 232, 184));

        jLabelTitle.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N
        jLabelTitle.setForeground(new java.awt.Color(20, 82, 84));
        jLabelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitle.setText("Seja bem vindo!");

        jlabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jlabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlabel2.setText("APRENDA A USAR CELULAR E INTERNET COM FACILIDADE");

        botao.setBackground(new java.awt.Color(246, 211, 71));
        botao.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 153, 153), new java.awt.Color(0, 102, 102)));
        botao.setForeground(new java.awt.Color(20, 82, 84));
        botao.setText("COMEÇAR AGORA");
        botao.setToolTipText("Clique aqui para iniciar as atividades");
        botao.setBorderPainted(true);
        botao.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        botao.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        botao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botaoMouseClicked(evt);
            }
        });
        botao.addActionListener(this::botaoActionPerformed);

        botao1.setBackground(new java.awt.Color(246, 211, 71));
        botao1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 153, 153), new java.awt.Color(0, 102, 102)));
        botao1.setForeground(new java.awt.Color(20, 82, 84));
        botao1.setText("CRIAR CONTA");
        botao1.setToolTipText("");
        botao1.setBorderPainted(true);
        botao1.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        botao1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        botao1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botao1MouseClicked(evt);
            }
        });
        botao1.addActionListener(this::botao1ActionPerformed);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(20, 82, 84));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("OU");

        botao3.setBackground(new java.awt.Color(246, 211, 71));
        botao3.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 153, 153), new java.awt.Color(0, 102, 102)));
        botao3.setForeground(new java.awt.Color(20, 82, 84));
        botao3.setText("JÁ POSSUO CONTA");
        botao3.setToolTipText("");
        botao3.setBorderPainted(true);
        botao3.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        botao3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        botao3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botao3MouseClicked(evt);
            }
        });
        botao3.addActionListener(this::botao3ActionPerformed);

        jPanel2.setBackground(new java.awt.Color(106, 195, 198));
        jPanel2.setForeground(new java.awt.Color(106, 195, 198));

        btnMenu.setBackground(new java.awt.Color(106, 195, 198));
        btnMenu.setForeground(new java.awt.Color(255, 255, 255));
        btnMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menu_30dp_FFFFFF_FILL0_wght400_GRAD0_opsz24.png"))); // NOI18N
        btnMenu.setBorderPainted(false);
        btnMenu.setContentAreaFilled(false);
        btnMenu.setFocusPainted(false);
        btnMenu.addActionListener(this::btnMenuActionPerformed);

        btnAumentarFonte.setBackground(new java.awt.Color(0, 204, 204));
        btnAumentarFonte.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnAumentarFonte.setForeground(new java.awt.Color(255, 255, 255));
        btnAumentarFonte.setText("A+");
        btnAumentarFonte.setToolTipText("Aumentar texto");
        btnAumentarFonte.setBorderPainted(false);
        btnAumentarFonte.setContentAreaFilled(false);
        btnAumentarFonte.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAumentarFonte.setFocusPainted(false);
        btnAumentarFonte.addActionListener(this::btnAumentarFonteActionPerformed);

        btnDiminuirFonte.setBackground(new java.awt.Color(0, 204, 204));
        btnDiminuirFonte.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnDiminuirFonte.setForeground(new java.awt.Color(255, 255, 255));
        btnDiminuirFonte.setText("A-");
        btnDiminuirFonte.setToolTipText("Diminuir texto");
        btnDiminuirFonte.setBorderPainted(false);
        btnDiminuirFonte.setContentAreaFilled(false);
        btnDiminuirFonte.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDiminuirFonte.setFocusPainted(false);
        btnDiminuirFonte.addActionListener(this::btnDiminuirFonteActionPerformed);

        btnAudio.setBackground(new java.awt.Color(0, 204, 204));
        btnAudio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/select_to_speak_24dp_FFFFFF_FILL0_wght400_GRAD0_opsz24.png"))); // NOI18N
        btnAudio.setToolTipText("Ler a página");
        btnAudio.setBorder(null);
        btnAudio.setBorderPainted(false);
        btnAudio.setContentAreaFilled(false);
        btnAudio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAudio.setFocusPainted(false);
        btnAudio.addActionListener(this::btnAudioActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAudio, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(btnDiminuirFonte)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAumentarFonte)
                .addGap(14, 14, 14))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDiminuirFonte, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                            .addComponent(btnAumentarFonte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAudio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Design sem nome (4) (2) (1).png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jlabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(botao1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(botao, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botao3, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(126, 126, 126))
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jLabelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlabel2)
                .addGap(34, 34, 34)
                .addComponent(botao, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(botao1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botao3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        setSize(new java.awt.Dimension(563, 744));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoActionPerformed
        System.out.println("Botão clicado!");
    }//GEN-LAST:event_botaoActionPerformed

    private void botaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoMouseClicked

    }//GEN-LAST:event_botaoMouseClicked

    private void botao1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botao1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_botao1MouseClicked

    private void botao1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao1ActionPerformed
        setVisible(false);
        CriarConta janela = new CriarConta();
        janela.setVisible(true);
    }//GEN-LAST:event_botao1ActionPerformed

    private void botao3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botao3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_botao3MouseClicked

    private void botao3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botao3ActionPerformed

    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuActionPerformed
        menuPrincipal.show(
                btnMenu,
                0,
                btnMenu.getHeight()
        );
    }//GEN-LAST:event_btnMenuActionPerformed

    private void btnAumentarFonteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAumentarFonteActionPerformed
        if (nivelFonte < 3) {
            alterarTamanhoFontes(getContentPane(), 2f);
            nivelFonte++;
        };
    }//GEN-LAST:event_btnAumentarFonteActionPerformed

    private void btnDiminuirFonteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiminuirFonteActionPerformed
        if (nivelFonte > 0) {
            alterarTamanhoFontes(getContentPane(), -2f);
            nivelFonte--;
        }
    }//GEN-LAST:event_btnDiminuirFonteActionPerformed

    private void btnAudioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAudioActionPerformed
        falar(obterTextoDaTela());
    }//GEN-LAST:event_btnAudioActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new Janela1().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mycompany.desenvolver60.RoundedButton botao;
    private com.mycompany.desenvolver60.RoundedButton botao1;
    private com.mycompany.desenvolver60.RoundedButton botao3;
    private javax.swing.JButton btnAudio;
    private javax.swing.JButton btnAumentarFonte;
    private javax.swing.JButton btnDiminuirFonte;
    private javax.swing.JButton btnMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel jlabel2;
    // End of variables declaration//GEN-END:variables
}
