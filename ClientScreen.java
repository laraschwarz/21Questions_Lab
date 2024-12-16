import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.imageio.*;
import javax.sound.sampled.*;

public class ClientScreen extends JPanel implements ActionListener {//10.210.83.131
	private ObjectOutputStream out;

	private Image background;
	private Clip clip;
	private Timer timer;
	private Thread tim;

	private JTextField wordField;
	private JTextField questionField;

	private JTextArea textArea;
	private JScrollPane scrollPane;
	private JTextArea textArea1;
	private JScrollPane scrollPane1;

	private JButton startButton;
	private JButton restartButton;
	private JButton yes;
	private JButton no;
	private JButton maybe;
	private JButton youGuessedIt;
	private JButton setWordButton;
	private JButton sendButton;

	private int fieldSize;
	private int xCoor;
	private int count;
	private String turn;
	private String word;
	private String question;
	private String instructions;
	private String answer;
	private String text;
	private int time;
	private boolean inScreen;
	private MyArrayList<String> list;
	private MyArrayList<String> questionList;
	private MyArrayList<String> answerList;

	public ClientScreen() {
		setLayout(null);
		timer = new Timer(300);

		count = 20;
		fieldSize = 140;
		xCoor = 300;
		word = "";
		question = "";
		answer = "";
		text = "";
		time = timer.getTimer();
		inScreen = true;
		turn = "false";
		instructions = "20 questions: \nThe game 20 questions will ask player A to come up with a word (usually a person) for \nperson B to guess. \nPerson B can ask 20 questions/try guessing 20 times. If they guess correctly, they win. If \nthey run out of guesses or time, they lose. \nPerson A is only allowed to respond in Yes, No, and Maybe answers. Once person B \nguesses correctly, person A can select a You guessed it! button.";
		questionList = new MyArrayList<String>();
		answerList = new MyArrayList<String>();
		list = new MyArrayList<String>();
		list.add(word);
		list.add(question);
		list.add(Integer.toString(count));
		list.add(answer);
		list.add(text);
		// Buttons
		startButton = new JButton("Start game");
		startButton.setBounds(xCoor, 450, fieldSize, 30);
		startButton.addActionListener(this);
		this.add(startButton);
		startButton.setVisible(inScreen);

		restartButton = new JButton("Restart");
		restartButton.setBounds(xCoor, 500, fieldSize + 20, 50);
		restartButton.addActionListener(this);
		this.add(restartButton);
		restartButton.setVisible(inScreen);

		yes = new JButton("Yes");
		yes.setBounds(xCoor - 150, 250, fieldSize, 30);
		yes.addActionListener(this);
		this.add(yes);
		yes.setVisible(false);

		no = new JButton("No");
		no.setBounds(xCoor, 250, fieldSize, 30);
		no.addActionListener(this);
		this.add(no);
		no.setVisible(!inScreen);

		maybe = new JButton("Maybe");
		maybe.setBounds(xCoor + 150, 250, fieldSize, 30);
		maybe.addActionListener(this);
		this.add(maybe);
		maybe.setVisible(!inScreen);

		youGuessedIt = new JButton("You Guessed It!");
		youGuessedIt.setBounds(xCoor, 300, fieldSize, 30);
		youGuessedIt.addActionListener(this);
		this.add(youGuessedIt);
		youGuessedIt.setVisible(!inScreen);

		setWordButton = new JButton("Set Secret Word");
		setWordButton.setBounds(xCoor, 200, fieldSize, 30);
		setWordButton.addActionListener(this);
		this.add(setWordButton);
		setWordButton.setVisible(!inScreen);

		sendButton = new JButton("Send Question");
		sendButton.setBounds(150, 200, fieldSize, 30);
		sendButton.addActionListener(this);
		this.add(sendButton);
		sendButton.setVisible(!inScreen);

		// TextField
		wordField = new JTextField(20);
		wordField.setBounds(xCoor, 150, fieldSize, 30);
		this.add(wordField);
		wordField.setVisible(!inScreen);

		questionField = new JTextField(20);
		questionField.setBounds(150, 150, fieldSize, 30);
		this.add(questionField);
		questionField.setVisible(!inScreen);

		// TextArea
		textArea = new JTextArea(); // sets the location and size
		textArea.setBounds(100, 50, 550, 250);
		textArea.setEditable(false);
		textArea.setFont(new Font("Arial", Font.PLAIN, 13));
		textArea.setText(instructions);
		this.add(textArea);
		textArea.setVisible(inScreen);

		// JScrollPane
		scrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(100, 50, 550, 250);
		this.add(scrollPane);
		textArea.setVisible(inScreen);

		// TextArea1
		textArea1 = new JTextArea(); // sets the location and size
		textArea1.setBounds(100, 450, 500, 150);
		textArea1.setEditable(false);
		textArea1.setFont(new Font("Arial", Font.PLAIN, 13));
		textArea1.setText(text);
		this.add(textArea1);
		textArea1.setVisible(!inScreen);

		// JScrollPane1
		scrollPane1 = new JScrollPane(textArea1);
		scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane1.setBounds(100, 450, 500, 100);
		this.add(scrollPane1);
		textArea.setVisible(!inScreen);

		this.setFocusable(true);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 800, 600);

		g.setColor(Color.BLACK);

		try {
			background = ImageIO.read(new File("questionBackground.jpeg"));
		} catch (IOException e) {
			System.out.println(e);
		}
		g.drawImage(background, 0, 0, 900, 900, null);

		g.setColor(Color.black);

		word = list.get(0);
		question = list.get(1);
		count = Integer.parseInt(list.get(2));
		answer = list.get(3);
		text = list.get(4);

		textArea1.setText(text);

		if (inScreen) {
			startButton.setVisible(true);
			textArea.setVisible(true);
			scrollPane.setVisible(true);

			textArea1.setVisible(false);
			scrollPane1.setVisible(false);

			yes.setVisible(false);
			no.setVisible(false);
			maybe.setVisible(false);
			youGuessedIt.setVisible(false);
			setWordButton.setVisible(false);
			sendButton.setVisible(false);
			restartButton.setVisible(false);

			wordField.setVisible(false);
			questionField.setVisible(false);
		} else {
			g.setColor(new Color(203, 247, 204));
			g.fillRect(100, 40, 600, 300);
			g.setColor(Color.black);

			g.drawString("Current Question: " + question, 150, 110);
			g.drawString("Tries Left: " + count, 20, 20);
			g.drawString("Time Remaining: " + timer.getTimer(), 620, 20);

			startButton.setVisible(false);
			textArea.setVisible(false);
			scrollPane.setVisible(false);
			restartButton.setVisible(false);

			textArea1.setVisible(true);
			scrollPane1.setVisible(true);

			if (turn.equals("true")) {
				g.drawString("Current Word: " + word, 300, 60);

				yes.setVisible(true);
				no.setVisible(true);
				maybe.setVisible(true);
				youGuessedIt.setVisible(true);
				setWordButton.setVisible(true);

				wordField.setVisible(true);
				System.out.println("answe: " + answer);
				if (answer.equalsIgnoreCase("You guessed it!")) {
					// tim.interrupt();

					restartButton.setVisible(true);
					this.playSound("youLost.wav");
					paintLoseScreen(g);
				} else if (count == 0 || timer.getTimer() == 0) {
					// tim.interrupt();

					restartButton.setVisible(true);
					this.playSound("trumpet_x.wav");
					paintWinScreen(g);
				}
			} else {
				if (word.equals("")) {
					g.drawString("Waiting for secret word...", 150, 60);
				} else {
					g.drawString("The secret word is set, start guessing!", 150, 60);
				}
				g.drawString("Answer: " + answer, 150, 300);

				sendButton.setVisible(true);

				questionField.setVisible(true);
				// System.out.println("timer: " + timer.getTimer());
				// System.out.println("count: " + count);
				System.out.println("answe: " + answer);

				if (answer.equalsIgnoreCase("You guessed it!")) {
					// tim.interrupt();
					restartButton.setVisible(true);
					this.playSound("trumpet_x.wav");
					paintWinScreen(g);
				} else if (count == 0 || timer.getTimer() == 0) {

					// tim.interrupt();
					restartButton.setVisible(true);
					this.playSound("youLost.wav");
					paintLoseScreen(g);
				}
			}
		}
		repaint();
	}

	public void paintWinScreen(Graphics g) {
		g.setColor(new Color(101, 204, 96));
		g.fillRect(0, 0, 800, 600);
		g.setColor(Color.BLACK);

		Font font = new Font("Calibri", Font.PLAIN, 28);
		g.setFont(font);
		g.drawString("Congrats, you won!", 100, 100);
		g.drawString("Secret Word: " + word, 100, 150);

		startButton.setVisible(false);
		textArea.setVisible(false);
		scrollPane.setVisible(false);
		textArea1.setVisible(false);
		scrollPane1.setVisible(false);
		yes.setVisible(false);
		no.setVisible(false);
		maybe.setVisible(false);
		youGuessedIt.setVisible(false);
		setWordButton.setVisible(false);
		sendButton.setVisible(false);
		wordField.setVisible(false);
		questionField.setVisible(false);

	}

	public void paintLoseScreen(Graphics g) {
		g.setColor(new Color(224, 119, 63));
		g.fillRect(0, 0, 800, 600);
		g.setColor(Color.black);

		Font font = new Font("Calibri", Font.PLAIN, 28);
		g.setFont(font);
		g.drawString("Uh oh, you lost...", 100, 100);
		g.drawString("Secret Word: " + word, 100, 150);

		startButton.setVisible(false);
		textArea.setVisible(false);
		scrollPane.setVisible(false);
		textArea1.setVisible(false);
		scrollPane1.setVisible(false);
		yes.setVisible(false);
		no.setVisible(false);
		maybe.setVisible(false);
		youGuessedIt.setVisible(false);
		setWordButton.setVisible(false);
		sendButton.setVisible(false);
		wordField.setVisible(false);
		questionField.setVisible(false);

	}

	public Dimension getPreferredSize() {
		return new Dimension(800, 600);
	}

	public void playSound(String soundName) {
		try {
			clip = AudioSystem.getClip();
			URL url = this.getClass().getClassLoader().getResource(soundName);
			clip.open(AudioSystem.getAudioInputStream(url));
			clip.start();
		} catch (Exception exc) {
			exc.printStackTrace(System.out);
		}
	}

	public String history() {
		String text = "";
		for (int i = 0; i < questionList.size(); i++) {
			if (!questionList.get(i).equals("")) {
				text += questionList.get(i) + " - " + answerList.get(i) + "\n";
			}
		}
		return text;
	}

	public void sendAnswer() {
		questionList.add(question);
		answerList.add(answer);

		list.set(0, word);
		list.set(1, question);
		list.set(2, Integer.toString(count));
		list.set(3, answer);
		if (turn.equals("true")) {
			text = history();
		}
		list.set(4, text);
		textArea1.setText(text); // atli

		try {
			System.out.println("sending list");
			out.reset();
			out.writeObject(list);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void poll() throws IOException {
		String hostName = "localhost";
		int portNumber = 1024;
		Socket serverSocket = new Socket(hostName, portNumber);

		out = new ObjectOutputStream(serverSocket.getOutputStream());
		ObjectInputStream in = new ObjectInputStream(serverSocket.getInputStream());

		// listens for inputs
		try {
			while (true) {
				Object o = new Object();
				o = in.readObject();

				if (o instanceof String) {
					if (((String) o).equals("true")) {
						turn = (String) o;
					} else if (((String) o).equals("restart")) {
						// System.out.println("receive restart");
						inScreen = true;
						word = "";
						question = "";
						count = 20;
						answer = "";
						questionList.clear();
						answerList.clear();
						text = "";
						inScreen = true;
						timer.setTimer(300);

						list.set(0, word);
						list.set(1, question);
						list.set(2, Integer.toString(count));
						list.set(3, answer);
						list.set(4, text);
					} else if (((String) o).equals("time")) {
						System.out.println("starting time");
						answer = "";
						time = timer.getTimer();
						tim = new Thread(timer);
						tim.start();
					}
				} else if (o instanceof MyArrayList) {
					list = (MyArrayList) o;
				}

				repaint();
			}
		} catch (UnknownHostException e) {
			System.err.println("Host unkown: " + hostName);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to " + hostName);
			System.exit(1);
		} catch (ClassNotFoundException ex) {
			System.err.println("Class not found exception");
			System.exit(1);
		}
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == setWordButton) {
			word = wordField.getText();
			wordField.setText("");
			sendAnswer();
			// int c1 = Integer.parseInt(connect1Field.getText());
		}
		if (e.getSource() == startButton) {
			inScreen = false;
			if (turn.equals("false")) {
				try {
					// System.out.println("starting time");
					out.reset();
					out.writeObject("time");
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			// tim.start();
			// time = timer.getTimer();
		}
		if (e.getSource() == sendButton) {
			question = questionField.getText();
			questionField.setText("");
			answer = "";
			count--;
			sendAnswer();
		}
		if (e.getSource() == yes) {
			answer = "Yes";
			sendAnswer();
		}
		if (e.getSource() == no) {
			answer = "No";
			sendAnswer();
		}
		if (e.getSource() == maybe) {
			answer = "Maybe";
			sendAnswer();
		}
		if (e.getSource() == youGuessedIt) {
			answer = "You guessed it!";
			sendAnswer();

		}
		if (e.getSource() == restartButton) {

			// word = "";
			// question = "";
			// count = 20;
			// answer = "";
			// questionList.clear();
			// answerList.clear();
			// text = "";
			// inScreen = true;
			// timer.setTimer(10);
			try {
				System.out.println("sending list");
				out.reset();
				out.writeObject("restart");
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		// questionList.add(question);
		// answerList.add(answer);

		// list.set(0, word);
		// list.set(1, question);
		// list.set(2, Integer.toString(count));
		// list.set(3, answer);
		// if (turn.equals("true")) {
		// text = history();
		// }
		// list.set(4, text);
		// textArea1.setText(text); // atli

		// try {
		// System.out.println("sending list");
		// out.reset();
		// out.writeObject(list);
		// } catch (IOException ex) {
		// ex.printStackTrace();
		// }
		repaint();
	}

}