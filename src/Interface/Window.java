package Interface;
import javax.swing.JFrame;
import Controller.Controller;
import Templates.Colors;
public class Window extends JFrame {
    Controller controller;
    IDE ide;
    public Window(Controller controller) {
        super("Mini J");
        this.controller = controller;
        init();
        initComponents();
    }
    void initComponents() {
        ide = new IDE(this);
        this.getContentPane().setBackground(Colors.LIGHTVSCODE);
        this.getContentPane().add(ide);
        controller.deserialize(ide);
        ide.lookPJFiles();
    }
    void init() {
        //this.setUndecorated(true);
        //this.setResizable(false);
        this.setBounds(0,0,1380,790);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
