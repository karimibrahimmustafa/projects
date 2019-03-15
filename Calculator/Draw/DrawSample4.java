package eg.edu.alexu.csd.oop.draw;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;

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
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
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
import javax.swing.JSlider;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JRadioButton;
import javax.swing.JList;

public class DrawSample4 {

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
	boolean fill= false;
	Timer timer;
	int pickSelected =0;
	JPanel selectedColor = new JPanel();
	Color color1= new Color(0);
	Color color2= new Color(0);
	Color color3= new Color(0);
	Color color4= new Color(0);
	JButton pick1 = new JButton("");
	JButton pick2 = new JButton("");
	JButton pick3 = new JButton("");
	JButton pick4 = new JButton("");
	private JFrame frmPaint;
	Map<String, Double> properties = new HashMap<>();
	boolean fillColor = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DrawSample4 window = new DrawSample4();
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
	public DrawSample4() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPaint = new JFrame();
		frmPaint.setTitle("Paint");
		frmPaint.setResizable(false);
		frmPaint.setBounds(100, 100, 807, 572);
		frmPaint.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 153, 153));
		panel.setToolTipText("");
		frmPaint.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JButton btnNewButton = new JButton("Line");
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBackground(new Color(255, 165, 0));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				shape = new LineSegment();
				Shape = 1;

			}
		});
		btnNewButton.setFont(new Font("BankGothic Md BT", Font.BOLD, 11));
		btnNewButton.setBounds(657, 11, 95, 23);
		panel.add(btnNewButton);
		JPanel panel_1 = new JPanel();
		panel_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
			}
			
			
		});
		
		

		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 165, 0), new Color(255, 165, 0), new Color(255, 165, 0), new Color(255, 165, 0)));
		panel_1.setBackground(Color.WHITE);
		Graphics g = panel_1.getGraphics();

		
		MouseAdapter m1 = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
						reDraw(panel_1);
				}
				

			
			
		};
	
		panel_1.addMouseListener(m1);
		
		
		
		
				
		
		
		
		panel_1.setBounds(115, 81, 674, 382);
		panel.add(panel_1);

		JButton btnTriangle = new JButton("Triangle");
		btnTriangle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Shape = 2;
				shape = new Triangle();
				
			}
		});
		btnTriangle.setForeground(new Color(0, 0, 0));
		btnTriangle.setFont(new Font("BankGothic Md BT", Font.BOLD, 11));
		btnTriangle.setBackground(new Color(255, 165, 0));
		btnTriangle.setBounds(657, 45, 95, 23);
		panel.add(btnTriangle);

		JButton btnCircle = new JButton("Circle");

		btnCircle.addMouseListener(new MouseAdapter() {
			@Override

			public void mouseClicked(MouseEvent arg0) {

				shape = new Circle();

			}
		});
		btnCircle.setForeground(new Color(0, 0, 0));
		btnCircle.setFont(new Font("BankGothic Md BT", Font.BOLD, 11));
		btnCircle.setBackground(new Color(255, 165, 0));
		btnCircle.setBounds(547, 11, 97, 23);
		panel.add(btnCircle);

		JButton btnEllipse = new JButton("Ellipse");
		btnEllipse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				shape = new Ellipse();
				Shape = 4;

			}
		});
		btnEllipse.setForeground(new Color(0, 0, 0));
		btnEllipse.setFont(new Font("BankGothic Md BT", Font.BOLD, 11));
		btnEllipse.setBackground(new Color(255, 165, 0));
		btnEllipse.setBounds(547, 45, 97, 23);
		panel.add(btnEllipse);

		JButton btnSquare = new JButton("Square");
		btnSquare.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				shape = new Square();
				Shape = 5;
			}
		});
		btnSquare.setForeground(new Color(0, 0, 0));
		btnSquare.setFont(new Font("BankGothic Md BT", Font.BOLD, 11));
		btnSquare.setBackground(new Color(255, 165, 0));
		btnSquare.setBounds(429, 11, 108, 23);
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
		btnRectangle.setForeground(new Color(0, 0, 0));
		btnRectangle.setFont(new Font("BankGothic Md BT", Font.BOLD, 11));
		btnRectangle.setBackground(new Color(255, 165, 0));
		btnRectangle.setBounds(429, 45, 108, 23);
		panel.add(btnRectangle);
		JLabel label_2 = new JLabel("");
		label_2.setFont(new Font("BankGothic Md BT", Font.BOLD, 12));
		label_2.setBounds(627, 404, 46, 14);
		panel.add(label_2);
		JSlider slider = new JSlider();
		slider.setPaintLabels(true);
		slider.setBackground(new Color(30, 144, 255));
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				label_2.setText(""+ slider.getValue());

				
				
			}
		});
		slider.setBounds(577, 487, 200, 26);
		panel.add(slider);
		
		JLabel lblNewLabel = new JLabel("0");
		lblNewLabel.setFont(new Font("BankGothic Md BT", Font.BOLD, 12));
		lblNewLabel.setBounds(547, 404, 23, 14);
		panel.add(lblNewLabel);
		
		JLabel label_1 = new JLabel("100");
		label_1.setFont(new Font("BankGothic Md BT", Font.BOLD, 12));
		label_1.setBounds(703, 404, 36, 14);
		panel.add(label_1);
		
		JButton noColor = new JButton("");
		noColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 selectedColor.setBackground(noColor.getBackground()); 

			}
		});
		noColor.setBackground(Color.BLUE);
		noColor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		noColor.setBounds(22, 148, 23, 23);
		panel.add(noColor);
				
				JButton button = new JButton("");
				button.setBackground(Color.BLACK);
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						 selectedColor.setBackground(button.getBackground()); 

					}
				});
				button.setFont(new Font("Tahoma", Font.PLAIN, 13));
				button.setBounds(65, 114, 23, 23);
				panel.add(button);
				
				JButton button_1 = new JButton("");
				button_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						 selectedColor.setBackground(button_1.getBackground()); 

					}
				});
				button_1.setBackground(Color.WHITE);
				button_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
				button_1.setBounds(22, 114, 23, 23);
				panel.add(button_1);
				
				JButton button_2 = new JButton("");
				button_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						 selectedColor.setBackground(button_2.getBackground()); 

					}
				});
				button_2.setBackground(Color.RED);
				button_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
				button_2.setBounds(65, 148, 23, 23);
				panel.add(button_2);
						
						JButton button_3 = new JButton("");
						button_3.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								 selectedColor.setBackground(button_3.getBackground()); 

							}
						});
						button_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
						button_3.setBackground(Color.PINK);
						button_3.setBounds(22, 182, 23, 23);
						panel.add(button_3);
						
						JButton button_4 = new JButton("");
						button_4.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								 selectedColor.setBackground(button_4.getBackground()); 

							}
						});
						button_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
						button_4.setBackground(Color.ORANGE);
						button_4.setBounds(65, 182, 23, 23);
						panel.add(button_4);
						
						JButton button_5 = new JButton("");
						button_5.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								 selectedColor.setBackground(button_5.getBackground()); 

							}
						});
						button_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
						button_5.setBackground(Color.YELLOW);
						button_5.setBounds(22, 219, 23, 23);
						panel.add(button_5);
						
						JButton button_6 = new JButton("");
						button_6.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								 selectedColor.setBackground(button_6.getBackground()); 
							}
						});
						button_6.setFont(new Font("Tahoma", Font.PLAIN, 13));
						button_6.setBackground(Color.GREEN);
						button_6.setBounds(65, 218, 23, 23);
						panel.add(button_6);
						
						JButton button_7 = new JButton("");
						button_7.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								 selectedColor.setBackground(button_7.getBackground()); 
							}
						});
						button_7.setFont(new Font("Tahoma", Font.PLAIN, 13));
						button_7.setBackground(Color.MAGENTA);
						button_7.setBounds(22, 253, 23, 23);
						panel.add(button_7);
						
						JButton button_8 = new JButton("");
						button_8.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								 selectedColor.setBackground(button_8.getBackground()); 

							}
						});
						button_8.setFont(new Font("Tahoma", Font.PLAIN, 13));
						button_8.setBackground(Color.CYAN);
						button_8.setBounds(65, 252, 23, 23);
						panel.add(button_8);
						pick1.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
							}
						});
			            pick1.setFont(new Font("Tahoma", Font.PLAIN, 13));
			            pick1.setBackground(Color.LIGHT_GRAY);
			            pick1.setBounds(64, 262, 41, 23);
						panel.add(pick2);
						pick1.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								 selectedColor.setBackground(pick1.getBackground()); 

							}
						});
								JLabel error = new JLabel("");
										JButton btnPick = new JButton("pick");
										btnPick.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent e) {
												{
													
													 Color c = JColorChooser.showDialog(null, "Choose a Color", pick1.getForeground());
														final Graphics g = panel_1.getGraphics();
													 paint.refresh(g);
												      if (c != null)
												      {
												    	 selectedColor.setBackground(c);
												    	  
												      }
												      
												      
													
												}
												}
										});
										btnPick.setForeground(Color.WHITE);
										btnPick.setFont(new Font("BankGothic Md BT", Font.BOLD, 11));
										btnPick.setBackground(new Color(70, 130, 180));
										btnPick.setBounds(23, 287, 68, 23);
										panel.add(btnPick);
												
												error.setForeground(new Color(139, 0, 0));
												error.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
												error.setBounds(469, 493, 262, 20);
												panel.add(error);
												selectedColor.setBackground(new Color(169, 169, 169));
												selectedColor.setBorder(new LineBorder(Color.BLACK));
																selectedColor.addMouseListener(new MouseAdapter() {
																	@Override
																	public void mouseClicked(MouseEvent arg0) {
																	}
																});
																
																JRadioButton rdbtnFillColor = new JRadioButton("Fill Color");
																rdbtnFillColor.addActionListener(new ActionListener() {
																	public void actionPerformed(ActionEvent arg0) {
																		fillColor = rdbtnFillColor.isSelected();
																		if(shape != null) {
																			shape.setColor(selectedColor.getBackground());
																		}
																		 
																		
																	}
																});
																rdbtnFillColor.setBackground(new Color(255, 255, 255));
																rdbtnFillColor.addChangeListener(new ChangeListener() {
																	public void stateChanged(ChangeEvent arg0) {
																		 
																	}
																});
																rdbtnFillColor.setForeground(new Color(70, 130, 180));
																rdbtnFillColor.setFont(new Font("BankGothic Md BT", Font.PLAIN, 12));
																rdbtnFillColor.setBounds(6, 317, 103, 23);
																panel.add(rdbtnFillColor);
																selectedColor.setBounds(37, 422, 41, 37);
																panel.add(selectedColor);
																		
																				JLabel selectedColor_1 = new JLabel("");
																				selectedColor_1.setFont(new Font("BankGothic Md BT", Font.PLAIN, 11));
																				selectedColor_1.setLabelFor(btnNewButton);
																				selectedColor_1.setIcon(new ImageIcon("800px_COLOURBOX27554697.jpg"));
																				selectedColor_1.setBounds(0, 0, 814, 522);
																				panel.add(selectedColor_1);
																				
																				

																				JLabel label = new JLabel("");
																				
																				label.setIcon(new ImageIcon("800px_COLOURBOX27554697.jpg"));
																				label.setBounds(0, 0, 814, 522);
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
		
		shape.setColor(selectedColor.getBackground());

		MouseMotionListener m = new MouseMotionAdapter() {

			@Override
			public void mouseDragged(MouseEvent e) {
				 shape.setColor(selectedColor.getBackground());
				if(fillColor)
				{shape.setFillColor(selectedColor.getBackground());}
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
		MouseAdapter m2 = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg1) {
				numOfClicks = 0;
				panel_1.removeMouseMotionListener(m1);
				shape = new Triangle();
				
			}
		};
		
		
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg1) {
				paint.addShape(shape);
				if (Shape == 1) {
					shape = new LineSegment();
					
				}
				else if (Shape == 2) {
					
					panel_1.addMouseMotionListener(m1);
					panel_1.addMouseListener(m2);
					
					
					
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
