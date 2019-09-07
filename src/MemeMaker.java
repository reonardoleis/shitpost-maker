// 
// Decompiled by Procyon v0.5.36
// 

package mememaker;

import java.awt.Graphics2D;
import java.awt.image.RenderedImage;
import java.io.File;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.net.URL;
import java.util.concurrent.ThreadLocalRandom;
import java.net.MalformedURLException;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.Jsoup;
import java.util.Iterator;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.Container;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.util.ArrayList;
import javax.swing.JFrame;

public class MemeMaker extends JFrame
{
    static ArrayList<String> imagens;
    static JTextField arquivo;
    JTextField numberOf;
    JButton confirmar;
    JButton pegarImagem;
    static String frase;
    static String frase2;
    
    public MemeMaker() {
        this.numberOf = new JTextField("N\u00famero de shitposts");
        this.confirmar = new JButton("Confirmar");
        this.pegarImagem = new JButton("Carregar Imagens");
        final JPanel panel = new JPanel(new GridLayout(4, 4, 4, 4));
        MemeMaker.arquivo.setSize(150, 92);
        this.confirmar.setSize(150, 92);
        this.numberOf.setSize(150, 92);
        new JFrame("MemeMaker v0.0.1");
        this.setContentPane(panel);
        this.pack();
        this.setDefaultCloseOperation(3);
        this.setResizable(false);
        this.setSize(MemeMaker.arquivo.getWidth(), MemeMaker.arquivo.getHeight() + this.confirmar.getHeight());
        MemeMaker.arquivo.setSize(100, 50);
        this.pegarImagem.setSize(100, 50);
        panel.add(this.pegarImagem);
        panel.add(MemeMaker.arquivo);
        panel.add(this.numberOf);
        panel.add(this.confirmar);
        this.setVisible(true);
        this.pegarImagem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    MemeMaker.imagens = new ArrayList<String>();
                    MemeMaker.im();
                    MemeMaker.listImages();
                    MemeMaker.this.pegarImagem.setText("Imagens resgatadas");
                }
                catch (IOException ex) {
                    Logger.getLogger(MemeMaker.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        this.confirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    try {
                        MemeMaker.this.createImage();
                    }
                    catch (InterruptedException ex) {
                        Logger.getLogger(MemeMaker.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                catch (IOException ex2) {
                    Logger.getLogger(MemeMaker.class.getName()).log(Level.SEVERE, null, ex2);
                }
            }
        });
    }
    
    public static void main(final String[] args) throws IOException, InterruptedException {
        new MemeMaker();
        createFrase();
    }
    
    public static void listImages() {
        for (final String i : MemeMaker.imagens) {
            System.out.println(i);
        }
    }
    
    public static void im() throws MalformedURLException, IOException {
        final String bb = MemeMaker.arquivo.getText();
        final Document doc = Jsoup.connect("https://imgur.com/search?q=" + bb).header("Accept-Encoding", "gzip, deflate").userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0").maxBodySize(0).timeout(600000).get();
        final Elements img = doc.getElementsByTag("img");
        for (final Element el : img) {
            if (el.hasAttr("alt")) {
                System.out.println("tem!");
                String u = el.toString().substring(19);
                u = u.substring(0, u.indexOf(34));
                System.out.println(u);
                if (u.indexOf("imgur") == -1) {
                    continue;
                }
                MemeMaker.imagens.add(u);
            }
        }
    }
    
    public static void createFrase() throws InterruptedException {
        final String[] comeco = { "Eis que", "Quando", "Da\u00ed", "Quando enfim", "Da\u00ed um dia", "Aquele momento que", "No dia em que", "Lembra aquela vez que", "Infelizmente", "Sem querer", "Desespero quando" };
        final String[] pessoa = { "o amor da sua vida", "voc\u00ea", "seu amigo", "sua m\u00e3e", "seu pai", "sua namorada", "sua tia", "sua prima", "seu primo", "seu irm\u00e3o", "sua irm\u00e3", "seu v\u00f4", "sua v\u00f3", "seu cachorro", "seu gato", "um esquerdista", "um fascista", "bolsonaro", "pabllo vittar", "a 10/10", "a pitanga", "sua mina", "o papa", "o policial", "o soldado", "a 10/10 gordinha", "a 1/10", "a 3/10", "a 6/10", "o maromba", "a \u00e1rvore", "Shrek", "Batman", "o corno", "seu namorado", "a corna", "a cozinheira", "sua professora", "o arquiteto", "o prefeito", "o Lula", "Temer", "Dilma", "Nando Moura", "Felipe Neto", "o embuste", "a feminista", "o direitista" };
        final String[] verbo = { "canta", "estuda", "se fode", "corre", "termina", "pula", "se torna", "reprova", "vai", "transa", "dan\u00e7a", "\u00e9 eleito", "\u00e9 demitido", "morre", "fica triste e", "caga", "desenha", "se muda", "d\u00e1 o cu", "come\u00e7a", "faz", "tenta", "se suicida", "perde", "ama", "cai", "se torna cuck", "chupa", "quebra o bra\u00e7o", "vence", "lacra", "milita", "chora" };
        final String[] fim = { "muito", "ao tentar trollar o professor", "o namoro", "do pr\u00e9dio", "de paraquedas", "na escola", "na faculdade", "maconheiro", "no trabalho", "e entope o vaso", "legal", "e escuta rap", "e escuta rock", "e escuta funk", "e bate punheta", "e acha a tampa", "e come seu cu", "e escuta sweet dreams", "e apanha", "e vira gay", "e vai preso", "e passa mal", "e cola na prova", "e mita", "e vira cuck", "no seu malaquias" };
        final int a = comeco.length;
        final int b = pessoa.length;
        final int c = verbo.length;
        final int d = fim.length;
        final int random1 = ThreadLocalRandom.current().nextInt(0, a);
        Thread.sleep(1L);
        final int random2 = ThreadLocalRandom.current().nextInt(0, b);
        Thread.sleep(1L);
        final int random3 = ThreadLocalRandom.current().nextInt(0, c);
        Thread.sleep(1L);
        final int random4 = ThreadLocalRandom.current().nextInt(0, d);
        Thread.sleep(1L);
        MemeMaker.frase = comeco[random1] + " " + pessoa[random2];
        MemeMaker.frase2 = " " + verbo[random3] + " " + fim[random4] + ".";
        System.out.println("fase:" + MemeMaker.frase);
    }
    
    public void createImage() throws IOException, InterruptedException {
        for (int i = 1; i <= Integer.parseInt(this.numberOf.getText()); ++i) {
            createFrase();
            final int random5 = ThreadLocalRandom.current().nextInt(0, MemeMaker.imagens.size());
            final String url = MemeMaker.imagens.get(random5);
            final URL url2 = new URL("http://" + url);
            final BufferedImage img = ImageIO.read(url2);
            final int width = img.getWidth();
            final int height = img.getHeight();
            final BufferedImage bufferedImage = new BufferedImage(width * 5, height * 5, 2);
            final Graphics2D g2d = bufferedImage.createGraphics();
            double a = 5 * width + 5 * height;
            a /= 42.0;
            a = Math.ceil(a);
            System.out.println(a);
            final float b = (float)(a * 1.0);
            final Font currentFont = g2d.getFont();
            final Font newFont = currentFont.deriveFont(b);
            int cc = 0;
            cc += (int)b;
            g2d.setColor(Color.RED);
            g2d.setFont(new Font("default", 1, cc));
            g2d.drawImage(img, 0, 0, width * 5, height * 5, null);
            g2d.drawString(MemeMaker.frase, width / 2 - width / 2, height * 5 - 100);
            g2d.drawString(MemeMaker.frase2, width / 2 - width / 2, height * 5 - 50);
            g2d.setFont(new Font("default", 1, cc / 2));
            g2d.drawString("     LQCGS ", width * 5 - 100, height * 5);
            g2d.dispose();
            try {
                final File file = new File("shitpost" + i + ".png");
                ImageIO.write(bufferedImage, "png", file);
            }
            catch (IOException ex) {}
        }
    }
    
    static {
        MemeMaker.arquivo = new JTextField("Termo pesquisa imgur");
        MemeMaker.frase = "";
        MemeMaker.frase2 = "";
    }
}
