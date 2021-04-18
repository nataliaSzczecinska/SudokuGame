package com.sudoku.io;

import java.util.Scanner;
import java.util.logging.Logger;

public class Input {
    private final Scanner scanner = new Scanner(System.in);
    private final Logger logger = Logger.getLogger(getClass().getName());

    public String readInput(){
        System.out.println(TextFactor.playerOption());
        return scanner.nextLine();
    }

    public boolean checkCorrectLoadData(String text, boolean isSolve) {
        if (1 == text.length()) {
            switch (text.charAt(0)) {
                case 's': {
                    if (isSolve) {
                        System.out.println(TextFactor.hasBeenSolved());
                        return false;
                    }
                    return true;
                }
                case 'e':
                case 'p': {
                    return true;
                } default: {
                    return false;
                }
            }
        } else {
            for (int i = 0 ; !isSolve && i < text.length() ; i++) {
                if(i % 2 == 0) {
                    if (!(47 <= text.charAt(i) && 57 >= text.charAt(i))) {
                        return false;
                    }
                } else {
                    if (44 != text.charAt(i)) {
                        return false;
                    }
                }
            }
            if(',' == text.charAt(text.length() - 1)) {
                if (text.length() % 6 == 0) {
                    return true;
                }
            } else {
                if ((text.length() + 1) % 6 == 0) {
                    return true;
                }
            }
        }
        logger.warning(TextFactor.mistakeChoice());
        return false;
    }
}