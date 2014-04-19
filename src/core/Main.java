package core;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import javax.swing.JApplet;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.StringWriter;

import javax.swing.JTextArea;

import utilities.Converter;
import core.CPU.CPUStatus;

public class Main extends JApplet {
	static CPU cpu;
	static Memory mem;
	public final static Logger log = Logger.getLogger(Main.class.getName());
	public final static Logger memLog = Logger.getLogger("MemLog");
	private static final int WIDTH = 800;
	private static final int HEIGHT = 1200;
	static JFrame f = null;
	public static JTextArea textArea;
	public static JTextArea memoryArea;
    public static JTextField input;
    public static JTextArea output;
    public static JButton runSingle;
    public static JButton runAll;
    
	public Main() {
		log.addHandler(new RegisterLogging());
		memLog.addHandler(new MemoryLogging());
		
        guiLayout customLayout = new guiLayout();

        //getContentPane().setFont(new Font("Helvetica", Font.PLAIN, 12));
        getContentPane().setLayout(customLayout);

		textArea = new JTextArea();
		JScrollPane jScrollPane1 = new JScrollPane(textArea);
        getContentPane().add(jScrollPane1);

		JButton open = new JButton("Open File");
		open.addActionListener(new OpenL());
        getContentPane().add(open);

		runSingle = new JButton("Next Step");
        getContentPane().add(runSingle);
		runSingle.addActionListener(new RunSingleStep());
		


        memoryArea = new JTextArea();
        JScrollPane jScrollMemory = new JScrollPane(memoryArea);
        getContentPane().add(jScrollMemory);

        JButton dumpMem = new JButton("Mem Dump");
        getContentPane().add(dumpMem);
        dumpMem.addActionListener(new MemoryDumper());
        
        input = new JTextField("");
        input.setEditable(true);
        input.setVisible(false);
        getContentPane().add(input);
        
        output = new JTextArea();
        JScrollPane outputPane = new JScrollPane(output);
        getContentPane().add(outputPane);
        
        JLabel inputLabel = new JLabel("Input");
        inputLabel.setVisible(false);
        getContentPane().add(inputLabel);
        
        JLabel outputLabel = new JLabel("Output");
        getContentPane().add(outputLabel);
        
		runAll = new JButton("Run All");
        getContentPane().add(runAll);
        runAll.addActionListener(new RunAllSteps());
        
        setSize(getPreferredSize());
        
	}

	public static void main(String[] args) {
		Main m = new Main();
        //gui applet = new gui();
        JFrame window = new JFrame("gui");

        window.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        //applet.init();
        m.init();
        window.getContentPane().add("Center", m);
        window.pack();
        window.setVisible(true);
		
	}

	public void init() {
		log.info("Initializing...");
		cpu = CPU.getInstance();
		cpu.setStatus(CPU.CPUStatus.READY);
		log.info("Waiting for new instructions...");
	}
	
	public static void RunStep() {
		cpu.step();
		if (cpu.getStatus() == CPUStatus.HALTED) {
			log.info("---- PROGRAM HALTED ----");
			return;
		}
		log.info("---- NEXT STEP ----");
		for (Register gpr : cpu.getRegisters()) {
			log.info("GPR " + gpr.getName() + " => " + gpr.getValue());
		}
		log.info("FR0 => "+ cpu.FR0.get32Value()+ "<=>"+cpu.FR0.get32BinString());
		log.info("FR1 => "+ cpu.FR1.get32Value()+ "<=>"+cpu.FR1.get32BinString());
		log.info("PC => " + cpu.PC.getValue());
		log.info("CC => " + cpu.CC.getValue());
		log.info("IR => " + cpu.IR.getValue());
		log.info("MAR => " + cpu.MAR.getValue());
		log.info("MBR => " + cpu.MBR.getValue());
		log.info("MSR => " + cpu.MSR.getValue());
		log.info("MFR => " + cpu.MFR.getValue());
		log.info("X0 => " + cpu.X0.getValue());
	}
	
	class OpenL implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFileChooser c = new JFileChooser();
			// Demonstrate "Open" dialog:
			int rVal = c.showOpenDialog(Main.this);
			if (rVal == JFileChooser.APPROVE_OPTION) {
				File file = c.getSelectedFile();
				try {
					BufferedReader br = new BufferedReader(new FileReader(file));
					String line;
					int index = 0;
					while ((line = br.readLine()) != null) {
						Memory.getInstance().setMemory(line, index);
						index++;
					}
					br.close();
					cpu.setStatus(CPUStatus.READY);
					System.out.println(cpu.getStatus());
					log.info("Instructions successfully loaded");
				}
				catch (Exception ex) {
					log.info("Error loading file: " + file.getName());
				}
			}
		}
	}
	
	class RunSingleStep implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Main.RunStep();
		}
	}
	
	class RunAllSteps implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			while(cpu.getStatus() != CPUStatus.HALTED)
				Main.RunStep();
			cpu.setStatus(CPUStatus.HALTED);
		}
	}
	
	class MemoryDumper implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			memLog.info("---- MEMORY DUMP ----");
			memLog.info(mem.getInstance().toString());
		}
	}
	
	public class RegisterLogging extends Handler {

		@Override
		public void close() throws SecurityException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void flush() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void publish(LogRecord arg0) {
			// TODO Auto-generated method stub
			Main.textArea.setText(textArea.getText() + "\n" + arg0.getMessage());
		}
	}
	
	public class MemoryLogging extends Handler {

		@Override
		public void close() throws SecurityException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void flush() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void publish(LogRecord arg0) {
			// TODO Auto-generated method stub
			Main.memoryArea.setText(memoryArea.getText() + "\n" + arg0.getMessage());
		}
	}
	
	class guiLayout implements LayoutManager {

	    public guiLayout() {
	    }

	    public void addLayoutComponent(String name, Component comp) {
	    }

	    public void removeLayoutComponent(Component comp) {
	    }

	    public Dimension preferredLayoutSize(Container parent) {
	        Dimension dim = new Dimension(0, 0);

	        Insets insets = parent.getInsets();
	        dim.width = 858 + insets.left + insets.right;
	        dim.height = 1000 + insets.top + insets.bottom;

	        return dim;
	    }

	    public Dimension minimumLayoutSize(Container parent) {
	        Dimension dim = new Dimension(0, 0);
	        return dim;
	    }

	    public void layoutContainer(Container parent) {
	        Insets insets = parent.getInsets();

	        Component c;
	        c = parent.getComponent(1);
	        if (c.isVisible()) {c.setBounds(insets.left+8,insets.top+8,96,24);}
	        c = parent.getComponent(2);
	        if (c.isVisible()) {c.setBounds(insets.left+120,insets.top+8,96,24);}
	        c = parent.getComponent(0);
	        if (c.isVisible()) {c.setBounds(insets.left+8,insets.top+40,304,472);}
	        c = parent.getComponent(3);
	        if (c.isVisible()) {c.setBounds(insets.left+320,insets.top+40,312,472);}
	        c = parent.getComponent(4);
	        if (c.isVisible()) {c.setBounds(insets.left+320,insets.top+8,100,24);}
	        c = parent.getComponent(5);
	        if (c.isVisible()) {c.setBounds(insets.left+8, insets.top+580, 500, 20);}
	        c = parent.getComponent(6);
	        if (c.isVisible()) {c.setBounds(insets.left+8, insets.top+620, 500, 100);}
	        c = parent.getComponent(7);
	        if (c.isVisible()) {c.setBounds(insets.left+8, insets.top+562, 100, 15);}
	        c = parent.getComponent(8);
	        if (c.isVisible()) {c.setBounds(insets.left+8, insets.top+602, 100, 15);}
	        c = parent.getComponent(9);
	        if (c.isVisible()) {c.setBounds(insets.left+220, insets.top+8, 96, 24);}
	    }
	}
}
