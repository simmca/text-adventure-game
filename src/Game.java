import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Game {

    JFrame window;
    Container con;
    JPanel titleNamePanel, startButtonPanel, mainTextPanel, choiceButtonPanel, playerPanel;
    JLabel titleNameLabel, hpLabel, hpLabelNumber, weaponLabel, weaponLabelName;
    Font titleFont = new Font("Helvetica", Font.PLAIN, 50);
    Font normalFont = new Font("Helvetica", Font.PLAIN, 25);
    JButton startButton, choice1, choice2, choice3, choice4;
    JTextArea mainTextArea;
    int playerHP, goblinHP, goblinHead;
    String weapon, position;

    TitleScreenHandler tsHandler = new TitleScreenHandler();
    ChoiceHandler choiceHandler = new ChoiceHandler();

    ImageIcon logo = new ImageIcon(".//res//jackfrost.jpg");



    public static void main(String[] args) {

        new Game();
    }

    public Game(){

        window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);
        window.setIconImage(logo.getImage());
        con = window.getContentPane();

        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(100, 100, 600, 150);
        titleNamePanel.setBackground(Color.black);
        titleNameLabel = new JLabel("SLAY THE GOBLIN");
        titleNameLabel.setForeground(Color.green);
        titleNameLabel.setFont(titleFont);

        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(300, 400, 200, 100);
        startButtonPanel.setBackground(Color.black);

        startButton = new JButton("START");
        startButton.setBackground(Color.black);
        startButton.setForeground(Color.green);
        startButton.setFont(normalFont);
        startButton.addActionListener(tsHandler);
        startButton.setFocusPainted(false);

        titleNamePanel.add(titleNameLabel);
        startButtonPanel.add(startButton);

        con.add(titleNamePanel);
        con.add(startButtonPanel);

        window.setVisible(true);
    }

    public void createGameScreen(){
        titleNamePanel.setVisible(false);
        startButtonPanel.setVisible(false);

        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(100, 100, 600, 250);
        mainTextPanel.setBackground(Color.black);
        con.add(mainTextPanel);
        mainTextArea = new JTextArea();
        mainTextArea.setBounds(100, 100, 600, 250);
        mainTextArea.setBackground(Color.black);
        mainTextArea.setForeground(Color.green);
        mainTextArea.setFont(normalFont);
        mainTextArea.setLineWrap(true);
        mainTextArea.setWrapStyleWord(true);
        mainTextArea.setEditable(false);

        mainTextPanel.add(mainTextArea);

        choiceButtonPanel = new JPanel();
        choiceButtonPanel.setBounds(250, 350, 300, 150);
        choiceButtonPanel.setBackground(Color.black);
        choiceButtonPanel.setLayout(new GridLayout(4,1));
        con.add(choiceButtonPanel);
        choice1 = new JButton("Choice 1");
        choice1.setBackground(Color.black);
        choice1.setForeground(Color.green);
        choice1.setFont(normalFont);
        choice1.setFocusPainted(false);
        choice1.addActionListener(choiceHandler);
        choice1.setActionCommand("c1");
        choiceButtonPanel.add(choice1);
        choice2 = new JButton("Choice 2");
        choice2.setBackground(Color.black);
        choice2.setForeground(Color.green);
        choice2.setFont(normalFont);
        choice2.setFocusPainted(false);
        choice2.addActionListener(choiceHandler);
        choice2.setActionCommand("c2");
        choiceButtonPanel.add(choice2);
        choice3 = new JButton("Choice 3");
        choice3.setBackground(Color.black);
        choice3.setForeground(Color.green);
        choice3.setFont(normalFont);
        choice3.setFocusPainted(false);
        choice3.addActionListener(choiceHandler);
        choice3.setActionCommand("c3");
        choiceButtonPanel.add(choice3);
        choice4 = new JButton("Choice 4");
        choice4.setBackground(Color.black);
        choice4.setForeground(Color.green);
        choice4.setFont(normalFont);
        choice4.setFocusPainted(false);
        choice4.addActionListener(choiceHandler);
        choice4.setActionCommand("c4");
        choiceButtonPanel.add(choice4);

//		choice4.setContentAreaFilled(false);  // Disable highlighting on press!!!


        playerPanel = new JPanel();
        playerPanel.setBounds(100, 15, 600, 50);
        playerPanel.setBackground(Color.black);
        playerPanel.setLayout(new GridLayout(1,4));
        con.add(playerPanel);
        hpLabel = new JLabel("HP:");
        hpLabel.setFont(normalFont);
        hpLabel.setForeground(Color.green);
        playerPanel.add(hpLabel);
        hpLabelNumber = new JLabel();
        hpLabelNumber.setFont(normalFont);
        hpLabelNumber.setForeground(Color.green);
        playerPanel.add(hpLabelNumber);
        weaponLabel = new JLabel("Weapon:");
        weaponLabel.setFont(normalFont);
        weaponLabel.setForeground(Color.green);
        weaponLabel.setBackground(Color.red);
        playerPanel.add(weaponLabel);
        weaponLabelName = new JLabel();
        weaponLabelName.setFont(normalFont);
        weaponLabelName.setForeground(Color.green);
        playerPanel.add(weaponLabelName);

        playerSetup();

    }
    public void playerSetup(){

        playerHP = 15;
        goblinHP = 20;
        weapon = "Knife";
        weaponLabelName.setText(weapon);
        hpLabelNumber.setText("" + playerHP);

        townGate();
    }

    public void townGate(){
        position = "townGate";
        mainTextArea.setText("You stand at the town gate. A guard is standing watch over the gate. What do you want to do?");
        choice1.setText("Talk to the guard");
        choice2.setText("Attack the guard");
        choice3.setText("Leave");
        choice4.setText("");
    }
    public void talkGuard(){
        position = "talkGuard";
        mainTextArea.setText("Guard: Greetings adventurer. I can not leave my post. A goblin is terrorizing our small town. Will you slay it for us?");
        choice1.setText("Yes");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }
    public void attackGuard(){
        position = "attackGuard";
        mainTextArea.setText("Guard: Stop at once! \nThe guard strikes you with the blunt edge of his blade.\n(You receive 3 damage)");
        //playerHP = playerHP -3;
        playerHP -=3;
        if (playerHP <= 0){
            lose();
        }
        hpLabelNumber.setText(""+playerHP);
        choice1.setText("You consider your next action.");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }
    public void crossRoad(){
        position = "crossRoad";
        mainTextArea.setText("You stand at a crossroad. If you go south, you will go back to the town.");
        choice1.setText("Go North");
        choice2.setText("Go East");
        choice3.setText("Go South");
        choice4.setText("Go West");
    }
    public void north(){
        position = "north";
        mainTextArea.setText("After much walking, you find a peaceful river. You rest at the riverside. \n(Your HP is recovered by 2)");
        playerHP = playerHP + 2;
        hpLabelNumber.setText(""+playerHP);
        choice1.setText("Go South");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }
    public void east(){
        position = "east";
        mainTextArea.setText("You venture into the forest. You eventually find a discarded long sword. \n(You obtained a Long Sword)");
        weapon = "Long Sword";
        weaponLabelName.setText(weapon);
        choice1.setText("Go West");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }
    public void west(){
        position = "west";
        mainTextArea.setText("As you walk near some bushes, a goblin leaps out! This must be the goblin you need to slay.");
        choice1.setText("Fight");
        choice2.setText("Run");
        choice3.setText("");
        choice4.setText("");
    }
    public void fight(){
        position = "fight";
        mainTextArea.setText("Goblin HP: " + goblinHP + "\n\nWhat do you do?");
        choice1.setText("Attack");
        choice2.setText("Run");
    }
    public void playerAttack(){
        position = "playerAttack";

        int playerDamage = 0;

        if(weapon.equals("Knife")){
            playerDamage = new java.util.Random().nextInt(3);
        }
        else if(weapon.equals("Long Sword")){
            playerDamage = new java.util.Random().nextInt(12);
        }

        mainTextArea.setText("You attacked the goblin for " + playerDamage + " damage!");

        goblinHP = goblinHP - playerDamage;

        choice1.setText("You consider your next action.");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }
    public void monsterAttack(){
        position = "monsterAttack";

        int monsterDamage = 0;

        monsterDamage = new java.util.Random().nextInt(6);

        mainTextArea.setText("The goblin attacked you for " + monsterDamage + " damage!");

        playerHP = playerHP - monsterDamage;
        hpLabelNumber.setText(""+playerHP);

        choice1.setText("You consider your next action.");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }
    public void win(){
        position = "win";

        mainTextArea.setText("You defeated the goblin. You take it's head as proof for the guard. \n(You obtained the goblin head)");

        goblinHead = 1;

        choice1.setText("Go East");

    }
    public void lose(){
        position = "lose";

        mainTextArea.setText("You have died!\n\nGAME OVER");

        choice1.setText("");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
        choice1.setVisible(false);
        choice2.setVisible(false);
        choice3.setVisible(false);
        choice4.setVisible(false);
    }
    public void ending(){
        position = "ending";

        mainTextArea.setText("Guard: You have the goblin's head? Thank you adventurer, here is your well earned gold.\n\nTHE END");

        choice1.setText("");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
        choice1.setVisible(false);
        choice2.setVisible(false);
        choice3.setVisible(false);
        choice4.setVisible(false);
    }

    public class TitleScreenHandler implements ActionListener{

        public void actionPerformed(ActionEvent event){

            createGameScreen();
        }
    }


    public class ChoiceHandler implements ActionListener{

        public void actionPerformed(ActionEvent event){

            String yourChoice = event.getActionCommand();

            switch(position){
                case "townGate":
                    switch(yourChoice){
                        case "c1":
                            if(goblinHead ==1){
                                ending();
                            }
                            else{
                                talkGuard();
                            }
                            break;
                        case "c2": attackGuard();break;
                        case "c3": crossRoad();break;
                    }
                    break;
                case "talkGuard", "attackGuard":
                    if (yourChoice.equals("c1")) {
                        townGate();
                    }
                    break;
                case "crossRoad":
                    switch(yourChoice){
                        case "c1": north(); break;
                        case "c2": east();break;
                        case "c3": townGate(); break;
                        case "c4": west();break;
                    }
                    break;
                case "north", "east", "win":
                    if (yourChoice.equals("c1")) {
                        crossRoad();
                    }
                    break;
                case "west":
                    switch(yourChoice){
                        case "c1": fight(); break;
                        case "c2": crossRoad(); break;
                    }
                    break;
                case "fight":
                    switch(yourChoice){
                        case "c1": playerAttack();break;
                        case "c2": crossRoad(); break;
                    }
                    break;
                case "playerAttack":
                    if (yourChoice.equals("c1")) {
                        if (goblinHP < 1) {
                            win();
                        } else {
                            monsterAttack();
                        }
                    }
                    break;
                case "monsterAttack":
                    if (yourChoice.equals("c1")) {
                        if (playerHP < 1) {
                            lose();
                        } else {
                            fight();
                        }
                    }
                    break;
            }
        }
    }
}