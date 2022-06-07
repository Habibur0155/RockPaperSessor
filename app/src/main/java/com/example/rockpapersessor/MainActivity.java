package com.example.rockpapersessor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView human, computer;
    private TextView humanScore, computerScore;
    private Button rock, paper, scissor;
    private Random random;
    private int HUMAN = 0;
    private int COMPUTER = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        human = findViewById(R.id.human);
        computer = findViewById(R.id.computer);

        humanScore = findViewById(R.id.humanScore);
        computerScore = findViewById(R.id.computerScore);

        rock = findViewById(R.id.rock);
        paper = findViewById(R.id.paper);
        scissor = findViewById(R.id.scissor);

        random = new Random();

        rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int c = getComputer();
                int h = 0;
                perClick(h, c);
            }
        });

        paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int c = getComputer();
                int h = 1;
                perClick(h, c);
            }
        });

        scissor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int c = getComputer();
                int h = 2;
                perClick(h, c);
            }
        });
    }

    private void perClick(int h, int c) {
        setImage(c, computer);
        setImage(h, human);

        int whoWine = whoWine(c, h);
        if (whoWine == HUMAN) {
            String s = humanScore.getText().toString();
            int x = Integer.parseInt(s) + 1;

            humanScore.setText(String.valueOf(x));
        } else if (whoWine == COMPUTER) {
            String s = computerScore.getText().toString();
            int x = Integer.parseInt(s) + 1;

            computerScore.setText(String.valueOf(x));
        }
        disPlayToast(whoWine);
    }

    void disPlayToast(int winner) {
        String s = "";
        if (winner == HUMAN) s = "YOU WINE!";
        else if (winner == COMPUTER) s = "COMPUTER WINE!";
        else s = "NO ONE WINE!";

        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    private int whoWine(int computer, int human) {
        int ROCK = 0, PAPER = 1, SCISSOR = 2;
        if (human == ROCK && computer == PAPER) return COMPUTER;
        if (human == PAPER && computer == ROCK) return HUMAN;
        if (human == SCISSOR && computer == PAPER) return HUMAN;
        if (human == PAPER && computer == SCISSOR) return COMPUTER;
        if (human == ROCK && computer == SCISSOR) return HUMAN;
        if (human == SCISSOR && computer == ROCK) return COMPUTER;

        return -1;
    }

    private int getComputer() {
        int x = random.nextInt(2);
        if (x < 0) x *= -1;

        return x;
    }

    private void setImage(int x, ImageView view) {
        switch (x) {
            case 0: view.setImageResource(R.drawable.rock); break;
            case 1: view.setImageResource(R.drawable.paper); break;
            default: view.setImageResource(R.drawable.sessor);
        }
    }
}