import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.swing.*;


public class Main {
    final static int btns_count = 14;
    final static int btn_width = 60;
    final static int btn_height = 50;
    static  List<JButton>player_buttons = new LinkedList<>();
    static  List<JButton> bot_buttons = new LinkedList<>();
    static List<Integer> bot_buttons_active = new LinkedList<>();
    static Map<Integer, Integer> integers = new HashMap<>();
    static Map<Integer, Integer> bot_integers = new HashMap<>();
    static int steps = 0;
    static JLabel player_score_label = new JLabel("Очки: 0");
    static JLabel bot_score_label = new JLabel("Очки: 0");
    static int score_sum = 0;
    static int score_sum_bot = 0;
    public static void main(String[] args) {
        JFrame f = new JFrame();
        JPanel panel = new JPanel();
        JLabel player = new JLabel("Игрок");
        player.setBounds(0, 10, 200, 50);
        JLabel bot = new JLabel("Компьютер");
        bot.setBounds(400, 10, 200, 50);
        ActionListener actionListener = new TestActionListener();
        panel.add(player);
        panel.add(bot);
        player_score_label.setBounds(0, 300, 200, 10);
        bot_score_label.setBounds(400, 300, 200, 10);
        panel.add(player_score_label);
        panel.add(bot_score_label);
        player_score_label.setVisible(false);
        bot_score_label.setVisible(false);
        for (int i = 0; i < 14; i++){
            integers.put(i, 0);
            bot_integers.put(i, 0);
        }
        int value;
        while (bot_buttons_active.size() < 25){
            value = (int) (Math.random() * 25);
            if (!bot_buttons_active.contains(value)){
                bot_buttons_active.add(value);
            }
        }
        int x = 0;
        int y = 0;
        for (int i = 0; i < 25; i++){
            if (i % 5 == 0){
                x = 0;
                y++;
            }
            JButton button1 = new JButton("0");
            button1.setActionCommand(i + "");
            button1.setBounds(btn_width * x,btn_height * y,btn_width,btn_height);
            panel.add(button1, i);
            button1.addActionListener(actionListener);
            player_buttons.add(button1);
            x++;
        }
        x = 0;
        y = 0;
        for (int i = 0; i < 25; i++){
            if (i % 5 == 0){
                x = 0;
                y++;
            }
            JButton button1 = new JButton("0");
            button1.setActionCommand(i + "");
            button1.setBounds(btn_width * x + 400,btn_height * y,btn_width,btn_height);
            panel.add(button1, i);
            button1.setEnabled(false);
            bot_buttons.add(button1);
            x++;
        }

        panel.setLayout(null);
        panel.setLocation(0,0);
        f.setSize(720, 360);
        f.add(panel);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
    public static class TestActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            ((JButton) source).setText("" + rnd(btns_count, false));
            ((JButton) source).setEnabled(false);
            bot_buttons.get(bot_buttons_active.get(0)).setText("" + rnd(btns_count, true));
            bot_buttons_active.remove(0);
            if(steps == 24){
                int[] result = result(player_buttons,bot_buttons);
                player_score_label.setVisible(true);
                player_score_label.setText("Очки: " + result[0]);
                bot_score_label.setVisible(true);
                bot_score_label.setText("Очки: " + result[1]);
            }
            else{
                steps++;

            }
        }
    }
    public static int[] result( List<JButton>player_buttons, List<JButton>bot_buttons)
    {
        int[] result = new int[2];
        result[0] = 0;
        result[1] = 0;
        int x1 = 0;
        int y1 = 0;
        int[][] values = new int[5][5];
        for (int i = 0; i < 24; i++){
            if (i % 5 == 0){
                x1 = 0;
                y1++;
            }
            values[y1-1][x1] = Integer.parseInt(player_buttons.get(i).getText());
            x1++;
        }
        int score_row_sum = 0;
        int score_row = 0;
        for (int k = 0; k < 14; k++){
            for (int i = 0; i < 5; i++){
                for (int j = 0; j < 5; j++){
                    if (values[i][j] == k  && values[i][j]!=-1){
                        score_row++;
                    }
                }
                if (score_row > 1){
                    score_row_sum = score_row_sum + score_row;
                }
                score_row = 0;
            }
            if (score_row_sum == 2){
                score_sum = score_sum + 10;
            }
            if (score_row_sum >= 3){
                score_sum = score_sum + 40;
            }
            score_row_sum = 0;
        }
        result[0] = score_sum;


        x1 = 0;
        y1 = 0;
        values = new int[5][5];
        for (int i = 0; i < 24; i++){
            if (i % 5 == 0){
                x1 = 0;
                y1++;
            }
            values[y1-1][x1] = Integer.parseInt(bot_buttons.get(i).getText());
            x1++;
        }
        score_row_sum = 0;
        score_row = 0;
        for (int k = 0; k < 14; k++){
            for (int i = 0; i < 5; i++){

                for (int j = 0; j < 5; j++){
                    if (values[i][j] == k  && values[i][j]!=-1){
                        score_row++;
                    }
                }
                if (score_row > 1){
                    score_row_sum = score_row_sum + score_row;
                }
                score_row = 0;
            }
            if (score_row_sum == 2){
                score_sum_bot = score_sum_bot + 10;
            }
            if (score_row_sum >= 3){
                score_sum_bot = score_sum_bot + 40;
            }
            score_row_sum = 0;
        }
        result[1] = score_sum_bot;


        return result;
    }
    public static int rnd(final double max, boolean isBot)
    {
        int value = (int) (Math.random() * max);

        if (!isBot){
            while (integers.get(value) > 3){
                value = (int) (Math.random() * max);
            }
            integers.put(value, integers.get(value) + 1);
        }
        else {
            while (bot_integers.get(value) > 3){
                value = (int) (Math.random() * max);
            }
            bot_integers.put(value, bot_integers.get(value) + 1);
        }
        return value;
    }
}