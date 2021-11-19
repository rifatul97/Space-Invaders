package main.Events;

import java.io.*;

public class ScoreHandler {

    public void saveData() {
        DataOutputStream output = null;
        try {
            output = new DataOutputStream(new FileOutputStream(
                    "data.dat"));

            for (int i = 0; i < 100; i++) {
                output.writeInt(i);
                System.out.println(i);
            }

            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readData() {
        try {
            DataInputStream input = new DataInputStream(new FileInputStream(
                    "data.dat"));

            while (input.available() > 0) {
                int x = input.readInt();
                System.out.println(x);
            }

            input.close();
        } catch (IOException e) {

        }

    }


}
