import java.awt.EventQueue;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ButtonGroup;

import player.Player;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;


public class ELOCalculatorGUI {

	private JFrame frame;
	private JTextField playerRating;
	private JTextField playerWin;
	private JTextField playerLoss;
	private JTextField playerDraw;
	private JTextField opponentRating1;
	private JTextField opponentRating2;
	private JTextField opponentRating3;
	private JTextField opponentRating4;
	private JTextField opponentRating5;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private final ButtonGroup buttonGroup_2 = new ButtonGroup();
	private final ButtonGroup buttonGroup_3 = new ButtonGroup();
	private final ButtonGroup buttonGroup_4 = new ButtonGroup();
	private final ButtonGroup buttonGroup_5 = new ButtonGroup();
	private final Action action = new SwingAction();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ELOCalculatorGUI window = new ELOCalculatorGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ELOCalculatorGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("ELO Rating Calculator");
		frame.setBounds(100, 100, 521, 258);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{40, 0, 0, 0, 0, 50, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblCurrentInfo = DefaultComponentFactory.getInstance().createTitle("Current Info");
		GridBagConstraints gbc_lblCurrentInfo = new GridBagConstraints();
		gbc_lblCurrentInfo.insets = new Insets(0, 0, 5, 5);
		gbc_lblCurrentInfo.gridx = 1;
		gbc_lblCurrentInfo.gridy = 0;
		frame.getContentPane().add(lblCurrentInfo, gbc_lblCurrentInfo);
		
		JLabel lblNewInfo = DefaultComponentFactory.getInstance().createTitle("New Info");
		GridBagConstraints gbc_lblNewInfo = new GridBagConstraints();
		gbc_lblNewInfo.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewInfo.gridx = 3;
		gbc_lblNewInfo.gridy = 0;
		frame.getContentPane().add(lblNewInfo, gbc_lblNewInfo);
		
		JLabel lblOpponents = DefaultComponentFactory.getInstance().createTitle("Opponents");
		GridBagConstraints gbc_lblOpponents = new GridBagConstraints();
		gbc_lblOpponents.insets = new Insets(0, 0, 5, 5);
		gbc_lblOpponents.gridx = 5;
		gbc_lblOpponents.gridy = 0;
		frame.getContentPane().add(lblOpponents, gbc_lblOpponents);
		
		JLabel lblW = DefaultComponentFactory.getInstance().createTitle("W");
		GridBagConstraints gbc_lblW = new GridBagConstraints();
		gbc_lblW.insets = new Insets(0, 0, 5, 5);
		gbc_lblW.gridx = 6;
		gbc_lblW.gridy = 0; 
		frame.getContentPane().add(lblW, gbc_lblW);
		
		JLabel lblD = DefaultComponentFactory.getInstance().createTitle("D");
		GridBagConstraints gbc_lblD = new GridBagConstraints();
		gbc_lblD.insets = new Insets(0, 0, 5, 5);
		gbc_lblD.gridx = 7;
		gbc_lblD.gridy = 0;
		frame.getContentPane().add(lblD, gbc_lblD);
		
		JLabel lblL = DefaultComponentFactory.getInstance().createTitle("L");
		GridBagConstraints gbc_lblL = new GridBagConstraints();
		gbc_lblL.insets = new Insets(0, 0, 5, 0);
		gbc_lblL.gridx = 8;
		gbc_lblL.gridy = 0;
		frame.getContentPane().add(lblL, gbc_lblL);
		
		playerRating = new JTextField();
		playerRating.setToolTipText("Current Player Rating");
		playerRating.setHorizontalAlignment(SwingConstants.CENTER);
		playerRating.setText("0");
		GridBagConstraints gbc_playerRating = new GridBagConstraints();
		gbc_playerRating.fill = GridBagConstraints.HORIZONTAL;
		gbc_playerRating.insets = new Insets(0, 0, 5, 5);
		gbc_playerRating.gridx = 1;
		gbc_playerRating.gridy = 1;
		frame.getContentPane().add(playerRating, gbc_playerRating);
		playerRating.setColumns(5);
		
		JLabel lblPlayerRating = DefaultComponentFactory.getInstance().createLabel("Player Rating");
		lblPlayerRating.setLabelFor(playerRating);
		GridBagConstraints gbc_lblPlayerRating = new GridBagConstraints();
		gbc_lblPlayerRating.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlayerRating.gridx = 2;
		gbc_lblPlayerRating.gridy = 1;
		frame.getContentPane().add(lblPlayerRating, gbc_lblPlayerRating);
		
		final JLabel newPlayerRating = DefaultComponentFactory.getInstance().createLabel("0");
		newPlayerRating.setToolTipText("New Rating");
		GridBagConstraints gbc_newPlayerRating = new GridBagConstraints();
		gbc_newPlayerRating.insets = new Insets(0, 0, 5, 5);
		gbc_newPlayerRating.gridx = 3;
		gbc_newPlayerRating.gridy = 1;
		frame.getContentPane().add(newPlayerRating, gbc_newPlayerRating);
		
		opponentRating1 = new JTextField();
		opponentRating1.setHorizontalAlignment(SwingConstants.CENTER);
		opponentRating1.setToolTipText("Opponent Rating");
		GridBagConstraints gbc_opponentRating1 = new GridBagConstraints();
		gbc_opponentRating1.insets = new Insets(0, 0, 5, 5);
		gbc_opponentRating1.gridx = 5;
		gbc_opponentRating1.gridy = 1;
		frame.getContentPane().add(opponentRating1, gbc_opponentRating1);
		opponentRating1.setColumns(5);
		
		final JRadioButton gameOutcome1Win = new JRadioButton("");
		gameOutcome1Win.setActionCommand("1");
		buttonGroup_1.add(gameOutcome1Win);
		GridBagConstraints gbc_rdbtnGameoutcome = new GridBagConstraints();
		gbc_rdbtnGameoutcome.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnGameoutcome.gridx = 6;
		gbc_rdbtnGameoutcome.gridy = 1;
		frame.getContentPane().add(gameOutcome1Win, gbc_rdbtnGameoutcome);
		
		JRadioButton gameOutcome1Draw = new JRadioButton("");
		gameOutcome1Draw.setActionCommand("1");
		buttonGroup_1.add(gameOutcome1Draw);
		GridBagConstraints gbc_gameOutcome1 = new GridBagConstraints();
		gbc_gameOutcome1.insets = new Insets(0, 0, 5, 5);
		gbc_gameOutcome1.gridx = 7;
		gbc_gameOutcome1.gridy = 1;
		frame.getContentPane().add(gameOutcome1Draw, gbc_gameOutcome1);
		
		final JRadioButton gameOutcome1Loss = new JRadioButton("");
		gameOutcome1Loss.setActionCommand("1");
		buttonGroup_1.add(gameOutcome1Loss);
		GridBagConstraints gbc_rdbtnNewRadioButton = new GridBagConstraints();
		gbc_rdbtnNewRadioButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_rdbtnNewRadioButton.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnNewRadioButton.gridx = 8;
		gbc_rdbtnNewRadioButton.gridy = 1;
		frame.getContentPane().add(gameOutcome1Loss, gbc_rdbtnNewRadioButton);
		
		playerWin = new JTextField();
		playerWin.setHorizontalAlignment(SwingConstants.CENTER);
		playerWin.setToolTipText("Current Win Total");
		playerWin.setText("0");
		GridBagConstraints gbc_playerWin = new GridBagConstraints();
		gbc_playerWin.insets = new Insets(0, 0, 5, 5);
		gbc_playerWin.fill = GridBagConstraints.HORIZONTAL;
		gbc_playerWin.gridx = 1;
		gbc_playerWin.gridy = 2;
		frame.getContentPane().add(playerWin, gbc_playerWin);
		playerWin.setColumns(5);
		
		JLabel lblWin = DefaultComponentFactory.getInstance().createLabel("Win");
		lblWin.setLabelFor(playerWin);
		GridBagConstraints gbc_lblWin = new GridBagConstraints();
		gbc_lblWin.insets = new Insets(0, 0, 5, 5);
		gbc_lblWin.gridx = 2;
		gbc_lblWin.gridy = 2;
		frame.getContentPane().add(lblWin, gbc_lblWin);
		
		final JLabel newPlayerWin = DefaultComponentFactory.getInstance().createLabel("0");
		newPlayerWin.setToolTipText("New Player Win Total");
		GridBagConstraints gbc_newPlayerWin = new GridBagConstraints();
		gbc_newPlayerWin.insets = new Insets(0, 0, 5, 5);
		gbc_newPlayerWin.gridx = 3;
		gbc_newPlayerWin.gridy = 2;
		frame.getContentPane().add(newPlayerWin, gbc_newPlayerWin);
		
		opponentRating2 = new JTextField();
		opponentRating2.setToolTipText("Opponent Rating");
		opponentRating2.setHorizontalAlignment(SwingConstants.CENTER);
		opponentRating2.setColumns(5);
		GridBagConstraints gbc_opponentRating2 = new GridBagConstraints();
		gbc_opponentRating2.insets = new Insets(0, 0, 5, 5);
		gbc_opponentRating2.gridx = 5;
		gbc_opponentRating2.gridy = 2;
		frame.getContentPane().add(opponentRating2, gbc_opponentRating2);
		
		final JRadioButton gameOutcome2Win = new JRadioButton("");
		gameOutcome2Win.setActionCommand("1");
		buttonGroup_2.add(gameOutcome2Win);
		GridBagConstraints gbc_rdbtnNewRadioButton_1 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNewRadioButton_1.gridx = 6;
		gbc_rdbtnNewRadioButton_1.gridy = 2;
		frame.getContentPane().add(gameOutcome2Win, gbc_rdbtnNewRadioButton_1);
		
		JRadioButton gameOutcome2Draw = new JRadioButton("");
		gameOutcome2Draw.setActionCommand("1");
		buttonGroup_2.add(gameOutcome2Draw);
		GridBagConstraints gbc_radioButton = new GridBagConstraints();
		gbc_radioButton.insets = new Insets(0, 0, 5, 5);
		gbc_radioButton.gridx = 7;
		gbc_radioButton.gridy = 2;
		frame.getContentPane().add(gameOutcome2Draw, gbc_radioButton);
		
		final JRadioButton gameOutcome2Loss = new JRadioButton("");
		gameOutcome2Loss.setActionCommand("1");
		buttonGroup_2.add(gameOutcome2Loss);
		GridBagConstraints gbc_rdbtnNewRadioButton_2 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_2.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnNewRadioButton_2.gridx = 8;
		gbc_rdbtnNewRadioButton_2.gridy = 2;
		frame.getContentPane().add(gameOutcome2Loss, gbc_rdbtnNewRadioButton_2);
		
		playerLoss = new JTextField();
		playerLoss.setHorizontalAlignment(SwingConstants.CENTER);
		playerLoss.setToolTipText("Current Total Loss");
		playerLoss.setText("0");
		playerLoss.setColumns(5);
		GridBagConstraints gbc_playerLoss = new GridBagConstraints();
		gbc_playerLoss.insets = new Insets(0, 0, 5, 5);
		gbc_playerLoss.fill = GridBagConstraints.HORIZONTAL;
		gbc_playerLoss.gridx = 1;
		gbc_playerLoss.gridy = 3;
		frame.getContentPane().add(playerLoss, gbc_playerLoss);
		
		JLabel lblLoss = DefaultComponentFactory.getInstance().createLabel("Loss");
		lblLoss.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoss.setLabelFor(playerLoss);
		GridBagConstraints gbc_lblLoss = new GridBagConstraints();
		gbc_lblLoss.insets = new Insets(0, 0, 5, 5);
		gbc_lblLoss.gridx = 2;
		gbc_lblLoss.gridy = 3;
		frame.getContentPane().add(lblLoss, gbc_lblLoss);
		
		final JLabel newPlayerLoss = DefaultComponentFactory.getInstance().createLabel("0");
		newPlayerLoss.setToolTipText("New Loss Total");
		GridBagConstraints gbc_newPlayerLoss = new GridBagConstraints();
		gbc_newPlayerLoss.insets = new Insets(0, 0, 5, 5);
		gbc_newPlayerLoss.gridx = 3;
		gbc_newPlayerLoss.gridy = 3;
		frame.getContentPane().add(newPlayerLoss, gbc_newPlayerLoss);
		
		opponentRating3 = new JTextField();
		opponentRating3.setToolTipText("Opponent Rating");
		opponentRating3.setHorizontalAlignment(SwingConstants.CENTER);
		opponentRating3.setColumns(5);
		GridBagConstraints gbc_opponentRating3 = new GridBagConstraints();
		gbc_opponentRating3.insets = new Insets(0, 0, 5, 5);
		gbc_opponentRating3.gridx = 5;
		gbc_opponentRating3.gridy = 3;
		frame.getContentPane().add(opponentRating3, gbc_opponentRating3);
		
		final JRadioButton gameOutcome3Win = new JRadioButton("");
		gameOutcome3Win.setActionCommand("1");
		buttonGroup_3.add(gameOutcome3Win);
		GridBagConstraints gbc_radioButton_1 = new GridBagConstraints();
		gbc_radioButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_radioButton_1.gridx = 6;
		gbc_radioButton_1.gridy = 3;
		frame.getContentPane().add(gameOutcome3Win, gbc_radioButton_1);
		
		JRadioButton gameOutcome3Draw = new JRadioButton("");
		gameOutcome3Draw.setActionCommand("1");
		buttonGroup_3.add(gameOutcome3Draw);
		GridBagConstraints gbc_radioButton_2 = new GridBagConstraints();
		gbc_radioButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_radioButton_2.gridx = 7;
		gbc_radioButton_2.gridy = 3;
		frame.getContentPane().add(gameOutcome3Draw, gbc_radioButton_2);
		
		final JRadioButton gameOutcome3Loss = new JRadioButton("");
		gameOutcome3Loss.setActionCommand("1");
		buttonGroup_3.add(gameOutcome3Loss);
		GridBagConstraints gbc_radioButton_3 = new GridBagConstraints();
		gbc_radioButton_3.insets = new Insets(0, 0, 5, 0);
		gbc_radioButton_3.gridx = 8;
		gbc_radioButton_3.gridy = 3;
		frame.getContentPane().add(gameOutcome3Loss, gbc_radioButton_3);
		
		playerDraw = new JTextField();
		playerDraw.setHorizontalAlignment(SwingConstants.CENTER);
		playerDraw.setToolTipText("Current Total Draws");
		playerDraw.setText("0");
		GridBagConstraints gbc_playerDraw = new GridBagConstraints();
		gbc_playerDraw.insets = new Insets(0, 0, 5, 5);
		gbc_playerDraw.fill = GridBagConstraints.HORIZONTAL;
		gbc_playerDraw.gridx = 1;
		gbc_playerDraw.gridy = 4;
		frame.getContentPane().add(playerDraw, gbc_playerDraw);
		playerDraw.setColumns(5);
		
		JLabel lblDraw = DefaultComponentFactory.getInstance().createLabel("Draw");
		lblDraw.setLabelFor(playerDraw);
		GridBagConstraints gbc_lblDraw = new GridBagConstraints();
		gbc_lblDraw.insets = new Insets(0, 0, 5, 5);
		gbc_lblDraw.gridx = 2;
		gbc_lblDraw.gridy = 4;
		frame.getContentPane().add(lblDraw, gbc_lblDraw);
		
		final JLabel newPlayerDraw = DefaultComponentFactory.getInstance().createLabel("0");
		newPlayerDraw.setToolTipText("New Draw Total");
		GridBagConstraints gbc_newPlayerDraw = new GridBagConstraints();
		gbc_newPlayerDraw.insets = new Insets(0, 0, 5, 5);
		gbc_newPlayerDraw.gridx = 3;
		gbc_newPlayerDraw.gridy = 4;
		frame.getContentPane().add(newPlayerDraw, gbc_newPlayerDraw);
		
		opponentRating4 = new JTextField();
		opponentRating4.setToolTipText("Opponent Rating");
		opponentRating4.setHorizontalAlignment(SwingConstants.CENTER);
		opponentRating4.setColumns(5);
		GridBagConstraints gbc_opponentRating4 = new GridBagConstraints();
		gbc_opponentRating4.insets = new Insets(0, 0, 5, 5);
		gbc_opponentRating4.gridx = 5;
		gbc_opponentRating4.gridy = 4;
		frame.getContentPane().add(opponentRating4, gbc_opponentRating4);
		
		final JRadioButton gameOutcome4Win = new JRadioButton("");
		gameOutcome4Win.setActionCommand("1");
		buttonGroup_4.add(gameOutcome4Win);
		GridBagConstraints gbc_radioButton_4 = new GridBagConstraints();
		gbc_radioButton_4.insets = new Insets(0, 0, 5, 5);
		gbc_radioButton_4.gridx = 6;
		gbc_radioButton_4.gridy = 4;
		frame.getContentPane().add(gameOutcome4Win, gbc_radioButton_4);
		
		JRadioButton gameOutcome4Draw = new JRadioButton("");
		gameOutcome4Draw.setActionCommand("1");
		buttonGroup_4.add(gameOutcome4Draw);
		GridBagConstraints gbc_radioButton_5 = new GridBagConstraints();
		gbc_radioButton_5.insets = new Insets(0, 0, 5, 5);
		gbc_radioButton_5.gridx = 7;
		gbc_radioButton_5.gridy = 4;
		frame.getContentPane().add(gameOutcome4Draw, gbc_radioButton_5);
		
		final JRadioButton gameOutcome4Loss = new JRadioButton("");
		gameOutcome4Loss.setActionCommand("1");
		buttonGroup_4.add(gameOutcome4Loss);
		GridBagConstraints gbc_radioButton_6 = new GridBagConstraints();
		gbc_radioButton_6.insets = new Insets(0, 0, 5, 0);
		gbc_radioButton_6.gridx = 8;
		gbc_radioButton_6.gridy = 4;
		frame.getContentPane().add(gameOutcome4Loss, gbc_radioButton_6);
		
//		final JLabel playerTotalGames = DefaultComponentFactory.getInstance().createLabel("0");
//		GridBagConstraints gbc_playerTotalGames = new GridBagConstraints();
//		gbc_playerTotalGames.insets = new Insets(0, 0, 5, 5);
//		gbc_playerTotalGames.gridx = 1;
//		gbc_playerTotalGames.gridy = 5;
//		frame.getContentPane().add(playerTotalGames, gbc_playerTotalGames);
		
		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("Total Games");
		GridBagConstraints gbc_lblNewJgoodiesLabel = new GridBagConstraints();
		gbc_lblNewJgoodiesLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewJgoodiesLabel.gridx = 2;
		gbc_lblNewJgoodiesLabel.gridy = 5;
		frame.getContentPane().add(lblNewJgoodiesLabel, gbc_lblNewJgoodiesLabel);
		
		final JLabel newPlayerTotalGames = DefaultComponentFactory.getInstance().createLabel("0");
		newPlayerTotalGames.setToolTipText("New Total Games Played");
		GridBagConstraints gbc_newPlayerTotalGames = new GridBagConstraints();
		gbc_newPlayerTotalGames.insets = new Insets(0, 0, 5, 5);
		gbc_newPlayerTotalGames.gridx = 3;
		gbc_newPlayerTotalGames.gridy = 5;
		frame.getContentPane().add(newPlayerTotalGames, gbc_newPlayerTotalGames);
		
		opponentRating5 = new JTextField();
		opponentRating5.setToolTipText("Opponent Rating");
		opponentRating5.setHorizontalAlignment(SwingConstants.CENTER);
		opponentRating5.setColumns(5);
		GridBagConstraints gbc_opponentRating5 = new GridBagConstraints();
		gbc_opponentRating5.insets = new Insets(0, 0, 5, 5);
		gbc_opponentRating5.gridx = 5;
		gbc_opponentRating5.gridy = 5;
		frame.getContentPane().add(opponentRating5, gbc_opponentRating5);
		
		final JRadioButton gameOutcome5Win = new JRadioButton("");
		gameOutcome5Win.setActionCommand("1");
		buttonGroup_5.add(gameOutcome5Win);
		GridBagConstraints gbc_radioButton_7 = new GridBagConstraints();
		gbc_radioButton_7.insets = new Insets(0, 0, 5, 5);
		gbc_radioButton_7.gridx = 6;
		gbc_radioButton_7.gridy = 5;
		frame.getContentPane().add(gameOutcome5Win, gbc_radioButton_7);
		
		JRadioButton gameOutcome5Draw = new JRadioButton("");
		gameOutcome5Draw.setActionCommand("1");
		buttonGroup_5.add(gameOutcome5Draw);
		GridBagConstraints gbc_radioButton_8 = new GridBagConstraints();
		gbc_radioButton_8.insets = new Insets(0, 0, 5, 5);
		gbc_radioButton_8.gridx = 7;
		gbc_radioButton_8.gridy = 5;
		frame.getContentPane().add(gameOutcome5Draw, gbc_radioButton_8);
		
		final JRadioButton gameOutcome5Loss = new JRadioButton("");
		gameOutcome5Loss.setActionCommand("1");
		buttonGroup_5.add(gameOutcome5Loss);
		GridBagConstraints gbc_radioButton_9 = new GridBagConstraints();
		gbc_radioButton_9.insets = new Insets(0, 0, 5, 0);
		gbc_radioButton_9.gridx = 8;
		gbc_radioButton_9.gridy = 5;
		frame.getContentPane().add(gameOutcome5Loss, gbc_radioButton_9);
		
		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Player player = new Player(
						Integer.parseInt(playerRating.getText())
						, Integer.parseInt(playerWin.getText())
						, Integer.parseInt(playerLoss.getText())
						, Integer.parseInt(playerDraw.getText())
				);
				
				// Process Opponents
				// TODO whole section should loop instead
				List<Integer> ratingsList = new ArrayList<Integer>();
				if (opponentRating1.getText().trim().length() != 0) 
					ratingsList.add(Integer.parseInt(opponentRating1.getText())); 
				if (opponentRating2.getText().trim().length() != 0) 
					ratingsList.add(Integer.parseInt(opponentRating2.getText()));
				if (opponentRating3.getText().trim().length() != 0) 
					ratingsList.add(Integer.parseInt(opponentRating3.getText()));
				if (opponentRating4.getText().trim().length() != 0) 
					ratingsList.add(Integer.parseInt(opponentRating4.getText()));
				if (opponentRating5.getText().trim().length() != 0) 
					ratingsList.add(Integer.parseInt(opponentRating5.getText()));
				int[] opponentRatings = new int[ratingsList.size()];
				for (int i = 0; i < opponentRatings.length; i++) {
					opponentRatings[i] = ratingsList.get(i).intValue();
				}
				
				int newWin = 0;
				if (gameOutcome1Win == getSelection(buttonGroup_1)) newWin++;
				if (gameOutcome2Win == getSelection(buttonGroup_2)) newWin++;
				if (gameOutcome3Win == getSelection(buttonGroup_3)) newWin++;
				if (gameOutcome4Win == getSelection(buttonGroup_4)) newWin++;
				if (gameOutcome5Win == getSelection(buttonGroup_5)) newWin++;
				
				int newLoss = 0;
				if (gameOutcome1Loss == getSelection(buttonGroup_1)) newLoss++;
				if (gameOutcome2Loss == getSelection(buttonGroup_2)) newLoss++;
				if (gameOutcome3Loss == getSelection(buttonGroup_3)) newLoss++;
				if (gameOutcome4Loss == getSelection(buttonGroup_4)) newLoss++;
				if (gameOutcome5Loss == getSelection(buttonGroup_5)) newLoss++;
				
				player.applyResults(opponentRatings, newWin, newLoss);
				
				newPlayerRating.setText(Integer.toString(player.getRating()));
				newPlayerWin.setText(Integer.toString(player.getWin()));
				newPlayerLoss.setText(Integer.toString(player.getLoss()));
				newPlayerDraw.setText(Integer.toString(player.getDraw()));
				newPlayerTotalGames.setText(Integer.toString(player.getTotalGames()));
			}
		});
		GridBagConstraints gbc_btnCalculate = new GridBagConstraints();
		gbc_btnCalculate.insets = new Insets(0, 0, 0, 5);
		gbc_btnCalculate.gridx = 2;
		gbc_btnCalculate.gridy = 7;
		frame.getContentPane().add(btnCalculate, gbc_btnCalculate);
		
		JButton btnReset = new JButton("Reset");
		GridBagConstraints gbc_btnReset = new GridBagConstraints();
		gbc_btnReset.insets = new Insets(0, 0, 0, 5);
		gbc_btnReset.gridx = 5;
		gbc_btnReset.gridy = 7;
		frame.getContentPane().add(btnReset, gbc_btnReset);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
	
	private static JRadioButton getSelection(ButtonGroup group) {
		for (Enumeration e=group.getElements(); e.hasMoreElements();) {
			JRadioButton b = (JRadioButton)e.nextElement();
			if (b.getModel() == group.getSelection()) {
				return b;
			}
		}
		return null;
	}
	
}
