
package reproductor_de_musica;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.images.Artwork;

public class reproductorMain extends javax.swing.JFrame {
    private AudioFile audioFile;
    private File mp3File;
    private lista list = new lista();
    private nodo actual = null;
    private Zplayer player;
    private Short x = 0;
    private String artist;
    private String album;
    private long durationInSeconds;

    private DefaultListModel lista_modelo = new DefaultListModel();
    protected boolean detenido = false;

    public reproductorMain() {
        setResizable(false);
        initComponents();
        nombre_can.setEditable(false);
        jSlider1.setEnabled(false);
         jTextField1.setEditable(false);
         jTextField2.setEditable(false);
         jTextField3.setEditable(false);
        lista_can.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JList lista = (JList) evt.getSource();
                if (evt.getClickCount() == 2) {
                    int index = lista.locationToIndex(evt.getPoint());
                    if (index != -1) {
                        actual = list.get_cancion(index);
                        x = 0;
                        playActionPerformed(null);
                    }
                }
            }
        });
         nombre_can.setEditable(false);
        player = new Zplayer(this);
    }

    public void cargarLista(String ruta) {
        try {
            FileInputStream fis = new FileInputStream(new File(ruta));
            BufferedReader tec = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
            String input[];
            tec.readLine();

            while (tec.ready()) {
                input = tec.readLine().split("<");
                System.out.println(input[0] + " , " + input[1]);
                list.insertar(input[0], input[1]);
                lista_modelo.addElement(input[0]);
            }
         
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error\nal cargar la lista!!!", "alerta", 1);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error!!!", "alerta", 1);
        }
        lista_can.setModel(lista_modelo);
    }

    public void guardarLista(String dir) {
        try {
            BufferedWriter tec = new BufferedWriter(new FileWriter(dir));
            tec.write("\r\n");

            nodo aux = list.first;
            while (aux != null) {
                tec.append(aux.nombre + "<" + aux.direccion + "\r\n");
                aux = aux.siguiente;
            }

            tec.close();
            
        } catch (Exception e) {
        }
    }

    public String crearArchivoLista() {
        String n = JOptionPane.showInputDialog("digite el nombre de la lista");
        if (n == null || n.isEmpty()) {
            return null;
        }
        
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int seleccion = chooser.showOpenDialog(this);
        File ruta;

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            ruta = chooser.getSelectedFile();
        } else {
            return null;
        }
        File save = new File(ruta.getAbsolutePath() + "\\" + n + ".lis");
        if (save.exists()) {
            save.delete();
        }
        return save.getAbsolutePath();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSlider1 = new javax.swing.JSlider();
        agregar = new javax.swing.JButton();
        eliminar = new javax.swing.JButton();
        anterior = new javax.swing.JButton();
        siguiente = new javax.swing.JButton();
        detener = new javax.swing.JButton();
        play = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        lista_can = new javax.swing.JList();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        nombre_can = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        MarcoFoto = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSlider1.setValue(100);
        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }
        });
        getContentPane().add(jSlider1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 316, -1, 20));

        agregar.setText("Agregar caciones");
        agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarActionPerformed(evt);
            }
        });
        getContentPane().add(agregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 292, 150, -1));

        eliminar.setText("Eliminar canciones");
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });
        getContentPane().add(eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 290, 150, -1));

        anterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/anterior.png"))); // NOI18N
        anterior.setBorderPainted(false);
        anterior.setContentAreaFilled(false);
        anterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anteriorActionPerformed(evt);
            }
        });
        getContentPane().add(anterior, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 260, -1, -1));

        siguiente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/siguiente.png"))); // NOI18N
        siguiente.setToolTipText("");
        siguiente.setBorderPainted(false);
        siguiente.setContentAreaFilled(false);
        siguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siguienteActionPerformed(evt);
            }
        });
        getContentPane().add(siguiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 260, -1, -1));

        detener.setText("STOP");
        detener.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detenerActionPerformed(evt);
            }
        });
        getContentPane().add(detener, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 310, -1, -1));

        play.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/play.png"))); // NOI18N
        play.setBorderPainted(false);
        play.setContentAreaFilled(false);
        play.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playActionPerformed(evt);
            }
        });
        getContentPane().add(play, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 260, -1, -1));

        jScrollPane1.setViewportView(lista_can);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 48, 440, 238));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/sonido.png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 310, -1, -1));

        jLabel1.setText("Nombre Cancion");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 20, 100, 22));

        jLabel2.setText("Duracion");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 110, 50, 20));

        jLabel5.setText("Artista");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 50, -1, 20));

        jLabel6.setText("Album");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 70, 40, 40));

        nombre_can.setText("...");
        nombre_can.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        nombre_can.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombre_canActionPerformed(evt);
            }
        });
        getContentPane().add(nombre_can, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 20, 270, -1));
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 50, 270, -1));

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 80, 270, -1));

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 110, 270, -1));

        jLabel4.setText("Doble clic para seleccionar");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 150, -1));
        getContentPane().add(MarcoFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 150, 150, 100));
        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider1StateChanged
        try {
            player.control.setGain((double) jSlider1.getValue() / 100);
        } catch (BasicPlayerException ex) {

        }
    }//GEN-LAST:event_jSlider1StateChanged

    private void agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivo MP3", "mp3", "mp3"));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setMultiSelectionEnabled(true);
        int seleccion = fileChooser.showOpenDialog(this);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File files[] = fileChooser.getSelectedFiles();
            boolean noMp3 = false, repetidos = false;

            for (File file : files) {
                try {
                    String name = file.getName();
                    if (name.length() < 4 || !name.substring(name.length() - 4, name.length()).equalsIgnoreCase(".mp3")) {
                        noMp3 = true;
                        continue;
                    }
                    if (list.buscar(file.getName(), file.getPath())) {
                        repetidos = true;
                        continue;
                    }
                    String names = file.getName()+ponerdatos(file);
                    list.insertar(names, file.getPath());
                    System.out.println(names);
                    System.out.println(file.getPath());
                    lista_modelo.addElement(file.getName());
                    lista_can.setModel(lista_modelo);
                    
                } catch (CannotReadException ex) {
                    Logger.getLogger(reproductorMain.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(reproductorMain.class.getName()).log(Level.SEVERE, null, ex);
                } catch (TagException ex) {
                    Logger.getLogger(reproductorMain.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ReadOnlyFileException ex) {
                    Logger.getLogger(reproductorMain.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvalidAudioFrameException ex) {
                    Logger.getLogger(reproductorMain.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (noMp3) {
                JOptionPane.showMessageDialog(null, "Se encontro archivo(s) no mp3", "alerta", 0);
            }
            if (repetidos) {
                JOptionPane.showMessageDialog(null, "Se encontraron repetidos", "alerta", 0);
            }
        }
    }//GEN-LAST:event_agregarActionPerformed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
        Logger.getLogger("javazoom.jlgui.basicplayer").setLevel(Level.OFF);
        if (list.IsEmpety()) {
            return;
        }
        int q = list.index(actual);
        if (q == -1) {
            JOptionPane.showMessageDialog(null, "ha ocurrido un\nerror inesperado!!!", "alerta", 1);
        } else {
            lista_modelo.remove(q);
            list.borrar(actual);
            detenerActionPerformed(evt);
            if (list.IsEmpety()) {
                actual = null;
                nombre_can.setText("...");
            } else {
                if (list.tam == 1) {
                    actual = list.first;
                } else {
                    if (actual.siguiente == null) {
                        actual = actual.anterior;
                    } else {
                        actual = actual.siguiente;
                    }
                }
                nombre_can.setText(actual.nombre);
            }
        }

    }//GEN-LAST:event_eliminarActionPerformed

    private void anteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anteriorActionPerformed
        Logger.getLogger("javazoom.jlgui.basicplayer").setLevel(Level.OFF);
        if (actual == null) {
            return;
        }

        x = 0;
        playActionPerformed(evt);
    }//GEN-LAST:event_anteriorActionPerformed

    private void siguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siguienteActionPerformed
        Logger.getLogger("javazoom.jlgui.basicplayer").setLevel(Level.OFF);
        if (actual == null) {
            return;
        }
                if (actual.siguiente == null) {
                    return;
                }
                actual = actual.siguiente;

        x = 0;
        playActionPerformed(evt);
    }//GEN-LAST:event_siguienteActionPerformed

    private void detenerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detenerActionPerformed
        Logger.getLogger("javazoom.jlgui.basicplayer").setLevel(Level.OFF);
      detenido = true;
        play.setIcon(new ImageIcon(getClass().getResource("/iconos/play.png")));
        try {
            player.control.stop();
            x = 0;
            jSlider1.setEnabled(false);
        } catch (BasicPlayerException ex) {
            Logger.getLogger(reproductorMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        detenido = false;
    }//GEN-LAST:event_detenerActionPerformed

    private void playActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playActionPerformed
        Logger.getLogger("javazoom.jlgui.basicplayer").setLevel(Level.OFF);
        detenido = true;
        if (list.IsEmpety()) {
            JOptionPane.showMessageDialog(null, "no hay canciones", "alerta", 1);
        } else {
            try {
                if (actual == null) {
                    actual = list.first;
                }
                mp3File = new File(actual.direccion);
                leerdatos();
                try {
                    if (x == 0) {
                        player.control.open(new URL("file:///" + actual.direccion));
                        player.control.play();
                        nombre_can.setText(actual.nombre);
                        jSlider1.setEnabled(true);
                        x = 1;
                        play.setIcon(new ImageIcon(getClass().getResource("/iconos/pausa.png")));
                    } else {
                        if (x == 1) {
                            player.control.pause();
                            x = 2;
                            play.setIcon(new ImageIcon(getClass().getResource("/iconos/play.png")));
                        } else {
                            player.control.resume();
                            x = 1;
                            play.setIcon(new ImageIcon(getClass().getResource("/iconos/pausa.png")));
                        }
                    }
                } catch (BasicPlayerException ex) {
                    JOptionPane.showMessageDialog(null, "error al abrir\nla cancion!!!", "alerta", 1);
                    x = 0;
                } catch (MalformedURLException ex) {
                    Logger.getLogger(reproductorMain.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "error al abrir la direccion\nde la cancion", "alerta", 1);
                    x = 0;
                }
            } catch (CannotReadException ex) {
                Logger.getLogger(reproductorMain.class.getName()).log(Level.SEVERE,null, ex);
            } catch (IOException ex) {
                Logger.getLogger(reproductorMain.class.getName()).log(Level.SEVERE, null, ex);
            } catch (TagException ex) {
                Logger.getLogger(reproductorMain.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ReadOnlyFileException ex) {
                Logger.getLogger(reproductorMain.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidAudioFrameException ex) {
                Logger.getLogger(reproductorMain.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    }//GEN-LAST:event_playActionPerformed

    private void nombre_canActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombre_canActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombre_canActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed
    private void leerdatos() throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException{
        audioFile = AudioFileIO.read(mp3File);

            Tag tag = audioFile.getTag();

           artist = tag.getFirst(FieldKey.ARTIST);
           album = tag.getFirst(FieldKey.ALBUM);
           System.out.println("hola"+album+artist);
           if(!"".equals(artist)){
           jTextField1.setText(artist);
           }else{
           jTextField2.setText("Artista desconocido");       
           }
           if(!"".equals(album)){
           jTextField2.setText(album);
           }else{
           jTextField2.setText("Album desconocido");   
           }
           durationInSeconds = audioFile.getAudioHeader().getTrackLength();
           long minutos;
           long segundos;
           if(durationInSeconds>60){
               minutos=durationInSeconds/60;
               segundos=durationInSeconds%60;
               jTextField3.setText(minutos+":"+segundos);
           }else{
               jTextField3.setText(durationInSeconds+"");
           }
           Artwork artwork = tag.getFirstArtwork();
            // Guarda la imagen en un archivo
            if (artwork != null) {
                byte[] binaryData = artwork.getBinaryData();
                ByteArrayInputStream stream = new ByteArrayInputStream(binaryData);
                BufferedImage image = ImageIO.read(stream);
                ImageIcon icon = new ImageIcon(image);
                Image images = icon.getImage();
                ImageIcon img2=new ImageIcon(images.getScaledInstance(150, 100, Image.SCALE_SMOOTH));
                MarcoFoto.setIcon(img2);
            } else {
                MarcoFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/sinfotodefecto.jpg")));
            }
    }
    
    private String ponerdatos(File file) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException{
        audioFile = AudioFileIO.read(file);

            Tag tag = audioFile.getTag();

           artist = tag.getFirst(FieldKey.ARTIST);
           album = tag.getFirst(FieldKey.ALBUM);

           if("".equals(artist)){
           artist="Artista desconocido";
           }
           if("".equals(album)){
           album="Album desconocido";
           }
           durationInSeconds = audioFile.getAudioHeader().getTrackLength();
           String tiempo;
           long minutos;
           long segundos;
           if(durationInSeconds>60){
               minutos=durationInSeconds/60;
               segundos=durationInSeconds%60;
               tiempo=minutos+":"+segundos;
           }else{
               tiempo=durationInSeconds+"";
           }

            return artist+" - "+album+" - "+tiempo;
    }
    
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
            java.util.logging.Logger.getLogger(reproductorMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(reproductorMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(reproductorMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(reproductorMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new reproductorMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel MarcoFoto;
    private javax.swing.JButton agregar;
    private javax.swing.JButton anterior;
    private javax.swing.JButton detener;
    private javax.swing.JButton eliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JList lista_can;
    private javax.swing.JTextField nombre_can;
    private javax.swing.JButton play;
    private javax.swing.JButton siguiente;
    // End of variables declaration//GEN-END:variables
}
