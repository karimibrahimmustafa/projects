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
import javax.swing.border.LineBorder;
import javax.swing.JColorChooser;
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

import javax.swing.SwingConstants;

public class NewGui {

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
	int pickSelected =0;
	private JFrame frmPaint;
	private JTextField txtEnterR;
	JPanel selectedColor = new JPanel();
	Map<String, Double> properties = new HashMap<>();
	Color color1= new Color(0);
	Color color2= new Color(0);
	Color color3= new Color(0);
	Color color4= new Color(0);


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewGui window = new NewGui();
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
	public NewGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPaint = new JFrame();
		frmPaint.setTitle("Paint");
		frmPaint.setResizable(false);
		frmPaint.setBounds(100, 100, 747, 571);
		frmPaint.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 153, 153));
		panel.setToolTipText("");
		frmPaint.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JButton btnNewButton = new JButton("line");
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
		btnNewButton.setBounds(10, 11, 95, 23);
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
		
		
		
		
				
		
		
		
		panel_1.setBounds(115, 55, 616, 425);
		panel.add(panel_1);

		JButton btnCancel = new JButton("Clear");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				paint.refresh(g);
				
				line = null;

			}
		});
		btnCancel.setBounds(155, 490, 65, 23);
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
		btnTriangle.setBounds(115, 11, 95, 23);
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
		btnCircle.setBounds(216, 11, 97, 23);
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
		btnEllipse.setBounds(320, 11, 97, 23);
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
		btnSquare.setBounds(427, 11, 95, 23);
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
		btnRectangle.setBounds(532, 11, 95, 23);
		panel.add(btnRectangle);

		JButton btnOther = new JButton("other..");
		btnOther.setForeground(new Color(255, 255, 255));
		btnOther.setFont(new Font("BankGothic Md BT", Font.PLAIN, 11));
		btnOther.setBackground(new Color(153, 0, 0));
		btnOther.setBounds(636, 11, 95, 23);
		panel.add(btnOther);

		txtEnterR = new JTextField();
		txtEnterR.setBounds(227, 491, 86, 20);
		panel.add(txtEnterR);
		txtEnterR.setColumns(10);
		
		JButton noColor = new JButton("");
		noColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 selectedColor.setBackground(noColor.getBackground()); 

			}
		});
		noColor.setBackground(Color.BLUE);
		noColor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		noColor.setBounds(10, 89, 41, 23);
		panel.add(noColor);
				
				JButton button = new JButton("");
				button.setBackground(Color.BLACK);
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						 selectedColor.setBackground(button.getBackground()); 

					}
				});
				button.setFont(new Font("Tahoma", Font.PLAIN, 13));
				button.setBounds(64, 56, 41, 23);
				panel.add(button);
				
				JButton button_1 = new JButton("");
				button_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						 selectedColor.setBackground(button_1.getBackground()); 

					}
				});
				button_1.setBackground(Color.WHITE);
				button_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
				button_1.setBounds(10, 55, 41, 23);
				panel.add(button_1);
				
				JButton button_2 = new JButton("");
				button_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						 selectedColor.setBackground(button_2.getBackground()); 

					}
				});
				button_2.setBackground(Color.RED);
				button_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
				button_2.setBounds(64, 90, 41, 23);
				panel.add(button_2);
						
						JButton button_3 = new JButton("");
						button_3.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								 selectedColor.setBackground(button_3.getBackground()); 

							}
						});
						button_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
						button_3.setBackground(Color.PINK);
						button_3.setBounds(10, 123, 41, 23);
						panel.add(button_3);
						
						JButton button_4 = new JButton("");
						button_4.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								 selectedColor.setBackground(button_4.getBackground()); 

							}
						});
						button_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
						button_4.setBackground(Color.ORANGE);
						button_4.setBounds(64, 124, 41, 23);
						panel.add(button_4);
						
						JButton button_5 = new JButton("");
						button_5.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								 selectedColor.setBackground(button_5.getBackground()); 

							}
						});
						button_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
						button_5.setBackground(Color.YELLOW);
						button_5.setBounds(10, 160, 41, 23);
						panel.add(button_5);
						
						JButton button_6 = new JButton("");
						button_6.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								 selectedColor.setBackground(button_6.getBackground()); 
							}
						});
						button_6.setFont(new Font("Tahoma", Font.PLAIN, 13));
						button_6.setBackground(Color.GREEN);
						button_6.setBounds(64, 160, 41, 23);
						panel.add(button_6);
						
						JButton button_7 = new JButton("");
						button_7.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								 selectedColor.setBackground(button_7.getBackground()); 
							}
						});
						button_7.setFont(new Font("Tahoma", Font.PLAIN, 13));
						button_7.setBackground(Color.MAGENTA);
						button_7.setBounds(10, 194, 41, 23);
						panel.add(button_7);
						
						JButton button_8 = new JButton("");
						button_8.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								 selectedColor.setBackground(button_8.getBackground()); 

							}
						});
						button_8.setFont(new Font("Tahoma", Font.PLAIN, 13));
						button_8.setBackground(Color.CYAN);
						button_8.setBounds(64, 194, 41, 23);
						panel.add(button_8);
								
								JButton pick1 = new JButton("");
					            pick1.setBorderPainted(false);
								
								pick1.setForeground(Color.BLUE);
								pick1.setFont(new Font("Tahoma", Font.PLAIN, 13));
								pick1.setBackground(Color.LIGHT_GRAY);
								pick1.setBounds(10, 262, 41, 23);
								panel.add(pick1);
								
								JButton pick2 = new JButton("");

					            pick2.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
									}
								});
					            pick2.setFont(new Font("Tahoma", Font.PLAIN, 13));
					            pick2.setBackground(Color.LIGHT_GRAY);
					            pick2.setBounds(64, 262, 41, 23);
								panel.add(pick2);
								
								JButton pick3 = new JButton("");
								pick3.setFont(new Font("Tahoma", Font.PLAIN, 13));
								pick3.setBackground(Color.LIGHT_GRAY);
								pick3.setBounds(10, 296, 41, 23);
								panel.add(pick3);
								
								JButton pick4 = new JButton("");
								pick4.setFont(new Font("Tahoma", Font.PLAIN, 13));
								pick4.setBackground(Color.LIGHT_GRAY);
								pick4.setBounds(64, 296, 41, 23);
								panel.add(pick4);
								JLabel error = new JLabel("");
										JButton btnPick = new JButton("pick");
										btnPick.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent e) {
												if(pickSelected!=0){
													
													 Color c = JColorChooser.showDialog(null, "Choose a Color", pick1.getForeground());
												      if (c != null)
												      {
												    	  if(pickSelected==1){
														        pick1.setBackground(c);
color1=c;
												    		  
												    	  }
												    	  else if(pickSelected==2){
														        pick2.setBackground(c);
color2=c;
												    		  
												    	  } else if(pickSelected==3){
														        pick3.setBackground(c);
color3=c;
													    		  
												    	  } else if(pickSelected==4){
														        pick4.setBackground(c);

													    		  color4=c;
												    	  }
												    	  
												    	  
												      }
												      
												      
													
												}
												else {error.setText("You Should Select Color Area !!");
											
												}}
										});
										btnPick.setForeground(Color.WHITE);
										btnPick.setFont(new Font("Dialog", Font.BOLD, 11));
										btnPick.setBackground(new Color(153, 0, 0));
										btnPick.setBounds(10, 228, 95, 23);
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
																selectedColor.setBounds(37, 422, 41, 37);
																panel.add(selectedColor);
																
																		JLabel selectedColor = new JLabel("");
																		selectedColor.setLabelFor(btnNewButton);
																		selectedColor.setIcon(new ImageIcon("800px_COLOURBOX27554697.jpg"));
																		selectedColor.setBounds(0, 0, 753, 522);
																		panel.add(selectedColor);
												pick1.addActionListener(new ActionListener() {
													public void actionPerformed(ActionEvent e) {
														if(pickSelected==1)
														{pickSelected =0;
											            pick1.setBorderPainted(false);
														}
														else 
														{  pick1.setBorderPainted(true);
														pickSelected =1;}
											            pick1.setBorder(new LineBorder(Color.BLACK));
											            pick2.setBorderPainted(false);
											            pick3.setBorderPainted(false);
											            pick4.setBorderPainted(false);
														 selectedColor.setBackground(color1); 
													}

												});
												pick2.addActionListener(new ActionListener() {
													public void actionPerformed(ActionEvent e) {
														if(pickSelected==2)
														{pickSelected =0;
											            pick2.setBorderPainted(false);
														}
														else 
													{pick2.setBorderPainted(true);
														pickSelected =2;
														selectedColor.setBackground(pick2.getBackground());}
											            pick2.setBorder(new LineBorder(Color.BLACK));
											            pick1.setBorderPainted(false);
											            pick3.setBorderPainted(false);
											            pick4.setBorderPainted(false);

													}
												});pick3.addActionListener(new ActionListener() {
													public void actionPerformed(ActionEvent e) {
														if(pickSelected==3)
														{pickSelected =0;
											            pick3.setBorderPainted(false);
														}
														else 											            pick3.setBorderPainted(true);
											            {pick3.setBorderPainted(true);
														pickSelected =3;
														selectedColor.setBackground(pick3.getBackground());
											            }
											            pick3.setBorder(new LineBorder(Color.BLACK));
											            pick2.setBorderPainted(false);
											            pick1.setBorderPainted(false);
											            pick4.setBorderPainted(false);

													}
												});pick4.addActionListener(new ActionListener() {
													public void actionPerformed(ActionEvent e) {
														if(pickSelected==4)
														{pickSelected =0;
											            pick4.setBorderPainted(false);
														}
														else 											            pick3.setBorderPainted(true);
											            {pick4.setBorderPainted(true);
														pickSelected =4;
											            }
											            pick4.setBorder(new LineBorder(Color.BLACK));
											            pick2.setBorderPainted(false);
											            pick3.setBorderPainted(false);
											            pick1.setBorderPainted(false);

													}
												});
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
