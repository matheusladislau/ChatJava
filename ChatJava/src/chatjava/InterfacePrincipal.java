package chatjava;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;
public class InterfacePrincipal extends javax.swing.JFrame {
    boolean clienteIniciado=false;
    boolean servidorIniciado=false;
    ArrayList<Usuario> arrayUsuario=new ArrayList<Usuario>();
    ServidorNegociador servidor;
//
    public InterfacePrincipal(){
        initComponents();
    }
//
    public void enviarparaTodos(String mensagem)throws IOException{
        
        for(int i=0; i<arrayUsuario.size(); i++){
            PrintStream saida=new PrintStream(arrayUsuario.get(i).cliente.getOutputStream());
            saida.print(mensagem);
        }
    }
    public void verConexoes(){
        if(servidorIniciado){
            String mensagem="";
            if(arrayUsuario.size()<=0){
                mensagem="Nenhum usuário conectado no momento.";
            }else{
                mensagem+=arrayUsuario.size()+" usuários conectados no momento\n";
                for(int i=0; i<arrayUsuario.size(); i++){
                    mensagem+=("Porta Entrada: "+arrayUsuario.get(i).getPorta()+
                    "  ||  Porta Saída: "+(arrayUsuario.get(i).getPorta()+1)+
                    "  ||  IP: "+arrayUsuario.get(i).getIp()+
                    "  ||  Nome: "+arrayUsuario.get(i).getNickName()+"\n");
                }
            }
            JOptionPane.showMessageDialog(null,mensagem);
        }else{
            JOptionPane.showMessageDialog(null,"Necessário iniciar como servidor para ver estas informações.");
        }
    }
    public void iniciarServidor(){
        if(servidorIniciado){
            JOptionPane.showMessageDialog(null,"Servidor já iniciado.");
        }else{
            servidor=new ServidorNegociador(new ControleInterface(txt_chat,cmp_recebeMensagem),arrayUsuario);
            servidor.start();
            this.servidorIniciado=true;
            new ControleInterface(txt_chat,cmp_recebeMensagem).atualizarChat(
                "Servidor iniciado as "+new Tempo().getHoraMinutoAtual()+".");  
        }
    }
    public void conectarServidor(){
        if(clienteIniciado){
            JOptionPane.showMessageDialog(null,"Já conectado como cliente.");
        }else{
            String ip=cmp_recebeIPServidor.getText();
            String nome=cmp_recebeNome.getText();
            new ClienteNegociador(new ControleInterface(txt_chat,cmp_recebeMensagem),ip,nome).start();
            clienteIniciado=true;
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_iniciarServidor = new javax.swing.JButton();
        btn_encerrarConexoes = new javax.swing.JButton();
        btn_mostrarConexões = new javax.swing.JButton();
        txt_titulo = new javax.swing.JLabel();
        cmp_recebeMensagem = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_chat = new javax.swing.JTextArea();
        btn_enviar = new javax.swing.JButton();
        txt_ipServidor = new javax.swing.JLabel();
        cmp_recebeIPServidor = new javax.swing.JTextField();
        btn_conectar = new javax.swing.JButton();
        cmp_recebeNome = new javax.swing.JTextField();
        txt_nickname = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(600, 230));

        btn_iniciarServidor.setText("Iniciar como Servidor");
        btn_iniciarServidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_iniciarServidorActionPerformed(evt);
            }
        });

        btn_encerrarConexoes.setText("Encerrar Conexões");
        btn_encerrarConexoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_encerrarConexoesActionPerformed(evt);
            }
        });

        btn_mostrarConexões.setText("Ver Conexões");
        btn_mostrarConexões.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_mostrarConexõesActionPerformed(evt);
            }
        });

        txt_titulo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_titulo.setForeground(new java.awt.Color(255, 255, 255));
        txt_titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_titulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/background_blue.jpg"))); // NOI18N
        txt_titulo.setText("CHAT DO SUCESSO");
        txt_titulo.setToolTipText("");
        txt_titulo.setFocusable(false);
        txt_titulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jScrollPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        txt_chat.setColumns(20);
        txt_chat.setRows(5);
        jScrollPane1.setViewportView(txt_chat);

        btn_enviar.setText("Enviar");
        btn_enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_enviarActionPerformed(evt);
            }
        });

        txt_ipServidor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_ipServidor.setText("Endereço IP - Servidor:");

        cmp_recebeIPServidor.setText("127.0.0.1");
        cmp_recebeIPServidor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmp_recebeIPServidorMouseClicked(evt);
            }
        });

        btn_conectar.setText("Conectar como Anônimo");
        btn_conectar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_conectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_conectarActionPerformed(evt);
            }
        });

        cmp_recebeNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmp_recebeNomeActionPerformed(evt);
            }
        });
        cmp_recebeNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cmp_recebeNomeKeyTyped(evt);
            }
        });

        txt_nickname.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_nickname.setText("Insira seu apelido:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(cmp_recebeMensagem)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_ipServidor)
                            .addComponent(cmp_recebeIPServidor, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmp_recebeNome)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txt_nickname, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_conectar)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btn_enviar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_encerrarConexoes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_mostrarConexões, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_iniciarServidor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addComponent(txt_titulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(txt_titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmp_recebeNome, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_conectar))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_ipServidor)
                            .addComponent(txt_nickname))
                        .addGap(1, 1, 1)
                        .addComponent(cmp_recebeIPServidor, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(cmp_recebeMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btn_iniciarServidor, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(5, 5, 5)
                            .addComponent(btn_mostrarConexões)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn_encerrarConexoes)
                            .addGap(35, 35, 35))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_enviar))))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_iniciarServidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_iniciarServidorActionPerformed
        iniciarServidor();
        cmp_recebeNome.setText("Servidor");
        btn_conectar.setText("              Conectar             ");
    }//GEN-LAST:event_btn_iniciarServidorActionPerformed

    private void btn_encerrarConexoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_encerrarConexoesActionPerformed

    }//GEN-LAST:event_btn_encerrarConexoesActionPerformed

    private void btn_mostrarConexõesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_mostrarConexõesActionPerformed
        verConexoes();
    }//GEN-LAST:event_btn_mostrarConexõesActionPerformed

    private void btn_enviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_enviarActionPerformed
        String mensagem=cmp_recebeMensagem.getText();
        cmp_recebeMensagem.setText("");
    }//GEN-LAST:event_btn_enviarActionPerformed

    private void btn_conectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_conectarActionPerformed
        conectarServidor();
        cmp_recebeIPServidor.setEditable(false);
        cmp_recebeNome.setEditable(false);
    }//GEN-LAST:event_btn_conectarActionPerformed

    private void cmp_recebeIPServidorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmp_recebeIPServidorMouseClicked
        if(!clienteIniciado)
            cmp_recebeIPServidor.setText("");
    }//GEN-LAST:event_cmp_recebeIPServidorMouseClicked

    private void cmp_recebeNomeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmp_recebeNomeKeyTyped
        btn_conectar.setText("              Conectar             ");
    }//GEN-LAST:event_cmp_recebeNomeKeyTyped

    private void cmp_recebeNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmp_recebeNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmp_recebeNomeActionPerformed
    public static void main(String args[]){
        try{
            for(javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }catch(ClassNotFoundException ex){
            java.util.logging.Logger.getLogger(InterfacePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }catch(InstantiationException ex){
            java.util.logging.Logger.getLogger(InterfacePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex){
            java.util.logging.Logger.getLogger(InterfacePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex){
            java.util.logging.Logger.getLogger(InterfacePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfacePrincipal().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_conectar;
    private javax.swing.JButton btn_encerrarConexoes;
    private javax.swing.JButton btn_enviar;
    private javax.swing.JButton btn_iniciarServidor;
    private javax.swing.JButton btn_mostrarConexões;
    private javax.swing.JTextField cmp_recebeIPServidor;
    private javax.swing.JTextField cmp_recebeMensagem;
    private javax.swing.JTextField cmp_recebeNome;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txt_chat;
    private javax.swing.JLabel txt_ipServidor;
    private javax.swing.JLabel txt_nickname;
    private javax.swing.JLabel txt_titulo;
    // End of variables declaration//GEN-END:variables
}
