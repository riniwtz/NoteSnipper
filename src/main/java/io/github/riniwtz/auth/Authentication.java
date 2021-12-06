package io.github.riniwtz.auth;

import io.github.riniwtz.Main;
import io.github.riniwtz.audio.Audio;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Authentication {
    public static final File FOLDER_LOCATION = new File(System.getProperty("user.home") + "/Documents/" + Main.WINDOW_TITLE);
    private static final File TOKEN_FILE = new File(FOLDER_LOCATION + "/" + "token");
    public static JDA jda;

    public static boolean keepSignedIn = false;
    private boolean botStatus = false;

    // Checks and read existing token else ignores
    public void initializeStart() {
        if (FOLDER_LOCATION.exists())
            if (TOKEN_FILE.exists())
                initializeToken();
    }

    // Checks and read existing token else ignores
    public void initializeToken() {
        try {
            Scanner reader = new Scanner(TOKEN_FILE);
            String TOKEN_ID = reader.nextLine();
            if (TOKEN_ID.length() == 59)
                build(TOKEN_ID);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void build(String TOKEN_ID) {
        try {
            int TOKEN_LENGTH = 59;
            if (TOKEN_ID.length() == TOKEN_LENGTH) {
                jda = JDABuilder.createDefault(TOKEN_ID).build();
                if (jda.getStatus().isInit()) {
                    jda.awaitReady();

                    botStatus = true;
                    System.out.println("Token is connected!");
                    new Audio().play("auth_success", false);
                    new Audio().play("chinese_music", true);

                    if (keepSignedIn) {
                        createTokenDirectory(FOLDER_LOCATION);
                        createTokenFile(TOKEN_ID);
                    }
                }
            } else {
                botStatus = false;
                System.out.println("Invalid Token");
                new Audio().play("invalid_token", false);
            }
        } catch (LoginException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void createTokenDirectory(File folder) {
        if (folder.mkdir()) System.out.println("Folder created: " + folder);
        else System.out.println("Folder could not created");
    }

    public void createTokenFile(String text) {
        try {
            final FileWriter WRITER = new FileWriter(FOLDER_LOCATION + "/" + "token");
            WRITER.write(text);
            WRITER.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean getBotStatus() {
        return botStatus;
    }
}
