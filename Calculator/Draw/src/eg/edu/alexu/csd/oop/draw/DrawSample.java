package eg.edu.alexu.csd.oop.draw;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JButton;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;

import javax.swing.JTextField;

import javax.swing.JLabel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.BevelBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.Timer;

import java.awt.event.MouseMotionAdapter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;

public class DrawSample {

	Shape line;
	Shape shape;
	boolean isCreated = false;
	boolean m1 = false;
	boolean m2 = false;
	// ellipse & circle
	int numOfClicks = 0;
	double yR = 0;
	double xR = 0;
	DrawingEngine paint = new Controller();
	List instantShapes = new ArrayList<>();
	int Shape = 0;
	int circleR = 0;
	Point center = new Point(0, 0);
	Point r = new Point(0, 0);
	int px = 0;
	int py = 0;
	int x = 0;
	int y = 0;
	int x2 = 0;
	int y2 = 0;
	Timer timer;
	private JFrame frmPaint;
	private JTextField txtEnterR;
	Map<String, Double> properties = new HashMap<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DrawSample window = new DrawSample();
					window.frmPaint.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @wbp.parser.entryPoint
	 */
	public DrawSample() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPaint = new JFrame();
		frmPaint.setTitle("Paint");
		frmPaint.setResizable(false);
		frmPaint.setBounds(100, 100, 714, 491);
		frmPaint.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 153, 153));
		panel.setToolTipText("");
		frmPaint.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JButton btnNewButton = new JButton("Line");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBackground(new Color(153, 0, 0));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				shape = new LineSegment();
				Shape = 1;

			}
		});
		btnNewButton.setFont(new Font("BankGothic Md BT", Font.BOLD, 11));
		btnNewButton.setBounds(10, 42, 95, 23);
		panel.add(btnNewButton);
		JPanel panel_1 = new JPanel();
		
		

		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(153, 51, 51), new Color(153, 51, 51),
				new Color(153, 51, 51), new Color(153, 51, 51)));
		panel_1.setBackground(Color.WHITE);
		Graphics g = panel_1.getGraphics();

		
		MouseAdapter m1 = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
						reDraw(panel_1);
				}
				

			
			
		};
	
		panel_1.addMouseListener(m1);
		
		
		
		
				
		
		
		
		panel_1.setBounds(115, 11, 576, 382);
		panel.add(panel_1);

		JButton btnCancel = new JButton("Clear");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				paint.refresh(g);
				
				line = null;

			}
		});
		btnCancel.setBounds(23, 356, 65, 23);
		panel.add(btnCancel);

		JButton btnTriangle = new JButton("Triangle");
		btnTriangle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Shape = 2;
				shape = new Triangle();
				
			}
		});
		btnTriangle.setForeground(new Color(255, 255, 255));
		btnTriangle.setFont(new Font("BankGothic Md BT", Font.BOLD, 11));
		btnTriangle.setBackground(new Color(153, 0, 0));
		btnTriangle.setBounds(10, 76, 95, 23);
		panel.add(btnTriangle);

		JButton btnCircle = new JButton("Circle");

		btnCircle.addMouseListener(new MouseAdapter() {
			@Override

			public void mouseClicked(MouseEvent arg0) {

				shape = new Circle();

			}
		});
		btnCircle.setForeground(new Color(255, 255, 255));
		btnCircle.setFont(new Font("BankGothic Md BT", Font.BOLD, 11));
		btnCircle.setBackground(new Color(153, 0, 0));
		btnCircle.setBounds(10, 110, 97, 23);
		panel.add(btnCircle);

		JButton btnEllipse = new JButton("Ellipse");
		btnEllipse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				shape = new Ellipse();
				Shape = 4;

			}
		});
		btnEllipse.setForeground(new Color(255, 255, 255));
		btnEllipse.setFont(new Font("BankGothic Md BT", Font.BOLD, 11));
		btnEllipse.setBackground(new Color(153, 0, 0));
		btnEllipse.setBounds(10, 144, 97, 23);
		panel.add(btnEllipse);

		JButton btnSquare = new JButton("Square");
		btnSquare.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				shape = new Square();
				Shape = 5;
			}
		});
		btnSquare.setForeground(new Color(255, 255, 255));
		btnSquare.setFont(new Font("BankGothic Md BT", Font.BOLD, 11));
		btnSquare.setBackground(new Color(153, 0, 0));
		btnSquare.setBounds(10, 178, 95, 23);
		panel.add(btnSquare);

		JButton btnRectangle = new JButton("rectangle");
		btnRectangle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				shape = new Rectangle();
				Shape = 6;
			}

			@Override
			public void mousePressed(MouseEvent e) {
				shape = new Rectangle();
				Shape = 6;

			}
		});
		btnRectangle.setForeground(new Color(255, 255, 255));
		btnRectangle.setFont(new Font("BankGothic Md BT", Font.BOLD, 11));
		btnRectangle.setBackground(new Color(153, 0, 0));
		btnRectangle.setBounds(10, 212, 95, 23);
		panel.add(btnRectangle);

		JButton btnOther = new JButton("other..");
		btnOther.setForeground(new Color(255, 255, 255));
		btnOther.setFont(new Font("BankGothic Md BT", Font.PLAIN, 11));
		btnOther.setBackground(new Color(153, 0, 0));
		btnOther.setBounds(10, 270, 95, 23);
		panel.add(btnOther);

		txtEnterR = new JTextField();
		txtEnterR.setBounds(220, 410, 86, 20);
		panel.add(txtEnterR);
		txtEnterR.setColumns(10);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("800px_COLOURBOX27554697.jpg"));
		label.setBounds(0, 0, 708, 441);
		panel.add(label);

		JMenuBar menuBar = new JMenuBar();
		frmPaint.setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("File");
		mnNewMenu.setFont(new Font("BankGothic Md BT", Font.PLAIN, 12));
		menuBar.add(mnNewMenu);

		JMenuItem mntmLoad = new JMenuItem("Load");
		mntmLoad.setFont(new Font("BankGothic Md BT", Font.PLAIN, 12));
		mnNewMenu.add(mntmLoad);

		JMenuItem mntmSaveAs = new JMenuItem("Save As..");
		mntmSaveAs.setFont(new Font("BankGothic Md BT", Font.PLAIN, 12));
		mnNewMenu.add(mntmSaveAs);

	}

	public void reDraw(JPanel panel_1) {
		
			
		MouseMotionListener m = new MouseMotionAdapter() {

			@Override
			public void mouseDragged(MouseEvent e) {
				if (Shape == 1) {
					if (numOfClicks == 1) {
						r.x = e.getX();
						r.y = e.getY();
						properties.put("endPointx", (double) r.x);
						properties.put("endPointy", (double) r.y);
						shape.setProperties(properties);
						Graphics g = panel_1.getGraphics();
						paint.addShape(shape);
						paint.refresh(g);

					}
				}
				if (Shape == 2) {
					if (numOfClicks == 1) {
						r.x = e.getX();
						r.y = e.getY();
						properties.put("secondPointx", (double) r.x);
						properties.put("secondPointy", (double) r.y);
						properties.put("endPointx", (double) r.x);
						properties.put("endPointy", (double) r.y);
						shape.setProperties(properties);
						Graphics g = panel_1.getGraphics();
						paint.addShape(shape);
						paint.refresh(g);

					}
				}
				if (Shape == 4) {
					if (numOfClicks == 1) {
						r.x = e.getX();
						r.y = e.getY();
						xR = r.distance(center.x, center.y);
						properties.put("xAxis", (double) r.x);
						properties.put("yAxis", (double) r.y);
						shape.setProperties(properties);
						Graphics g = panel_1.getGraphics();
						paint.addShape(shape);
						paint.refresh(g);

					}

				}
				if (Shape == 5) {
					if (numOfClicks == 1) {
						r.x = e.getX();
						r.y = e.getY();
						xR = r.distance(center.x, center.y);
						properties.put("endx", (double) r.x);
						properties.put("endy", (double) r.y);
						shape.setProperties(properties);
						Graphics g = panel_1.getGraphics();
						paint.addShape(shape);
						paint.refresh(g);
					}
				}
				if (Shape == 6) {
					if (numOfClicks == 1) {
						r.x = e.getX();
						r.y = e.getY();
						xR = r.distance(center.x, center.y);
						properties.put("endx", (double) r.x);
						properties.put("endy", (double) r.y);
						shape.setProperties(properties);
						Graphics g = panel_1.getGraphics();
						paint.addShape(shape);
						paint.refresh(g);
					}
				}
			}
		};
		
		
		
		
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg1) {
				paint.addShape(shape);
				if (Shape == 1)
					shape = new LineSegment();
				else if (Shape == 2) {
					MouseMotionListener m1 = new MouseMotionAdapter() {
						@Override
						public void mouseMoved(MouseEvent e) {
							
							r.x = e.getX();
							r.y = e.getY();
							properties.put("endPointx", (double) r.x);
							properties.put("endPointy", (double) r.y);
							shape.setProperties(properties);
							Graphics g = panel_1.getGraphics();
							paint.addShape(shape);
							paint.refresh(g);
					
						}
					};
					panel_1.addMouseMotionListener(m1);
					
					
				}
				else if (Shape == 4) {
					shape = new Ellipse();
					center = new Point(0, 0);
				}
				else if (Shape == 5) {
					shape = new Square();
					center = new Point(0, 0);
				}else if (Shape == 6) {
					shape = new Rectangle();
					center = new Point(0, 0);
				}
				
				numOfClicks = 0;
				panel_1.removeMouseMotionListener(m);
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				panel_1.setBackground(Color.WHITE);
				if (Shape == 1) {
					if (numOfClicks == 0) {

						r.x = arg0.getX();
						r.y = arg0.getY();

						numOfClicks = 1;
						properties = new HashMap<>();
						properties.put("startPointx", (double) r.x);
						properties.put("startPointy", (double) r.y);

						panel_1.addMouseMotionListener(m);
					}

				}
				if (Shape == 2) {
					if (numOfClicks == 0) {

						r.x = arg0.getX();
						r.y = arg0.getY();

						numOfClicks = 1;
						properties = new HashMap<>();
						properties.put("startPointx", (double) r.x);
						properties.put("startPointy", (double) r.y);

						panel_1.addMouseMotionListener(m);
					}

					
				}
				if (Shape == 3) {

				}
				if (Shape == 4) {
					if (numOfClicks == 0) {

						numOfClicks = 1;

						center.x = arg0.getX();
						center.y = arg0.getY();
						yR = 5;

						shape.setPosition(center);

						panel_1.addMouseMotionListener(m);

					}
				}

				if (Shape == 5) {
					if (numOfClicks == 0) {
						
						numOfClicks = 1;
						center.x = arg0.getX();
						center.y = arg0.getY();

						shape.setPosition(center);
						panel_1.addMouseMotionListener(m);

					}

				}

				if (Shape == 6) {
					if (numOfClicks == 0) {
						numOfClicks = 1;
						center.x = arg0.getX();
						center.y = arg0.getY();
						yR = 5;

						shape.setPosition(center);
						panel_1.addMouseMotionListener(m);

					}

				}
				if (Shape == 7) {

				}

			}
		});
	}

}
