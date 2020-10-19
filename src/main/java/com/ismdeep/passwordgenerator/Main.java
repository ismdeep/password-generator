package com.ismdeep.passwordgenerator;

import java.util.Random;


/**
 * Password Generator CLI
 * <p>
 * Author: Del Cooper
 * <p>
 * Date: 2020-10-18
 * <p>
 * -----------------------------
 * <p>
 * Usage: genpass [-d on|off] [-a on|off] [-A on|off] [--append ".-+=?"] [len]
 * <p>
 * -d        Digital, default: on
 * -a        Lower case characters, default: on
 * -A        Upper case characters, default: on
 * --fuzzy   Fuzzy for 0,o,O, 1,I,l , default: off
 * --append  Append characters
 * len       Generate length
 */
public class Main {

    public static String genPass(String base, int len) {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(base.charAt(rand.nextInt(base.length())));
        }
        return sb.toString();
    }

    public static String getArgument(String[] args, String key) {
        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].equals(key)) {
                return args[i + 1];
            }
        }

        return "";
    }

    public static void printHelpMsg() {
        System.out.println(
                "Usage: genpass [-d on|off] [-a on|off] [-A on|off] [--fuzzy on|off] [--append \".-+=?\"] [len]\n" +
                        "\n" +
                        "    -d        Digital, default: on\n" +
                        "    -a        Lower case characters, default: on\n" +
                        "    -A        Upper case characters, default: on\n" +
                        "    --fuzzy   Fuzzy for 0,o,O, 1,I,l , default: off\n" +
                        "    --append  Append characters\n" +
                        "    len       Generate length\n");
    }

    public static void main(String[] args) {

        if (args.length >= 1 && args[0].equals("--help")) {
            printHelpMsg();
            return;
        }

        int len = 8;
        boolean digital = true;
        boolean lowerCase = true;
        boolean upperCase = true;
        boolean fuzzy = false;
        String appendCharacters;

        /* Load arguments */
        if (getArgument(args, "-d").equals("off")) {
            digital = false;
        }
        if (getArgument(args, "-a").equals("off")) {
            lowerCase = false;
        }
        if (getArgument(args, "-A").equals("off")) {
            upperCase = false;
        }
        if (getArgument(args, "--fuzzy").equals("on")) {
            fuzzy = true;
        }
        appendCharacters = getArgument(args, "--append");
        if (args.length >= 1) {
            try {
                len = Integer.parseInt(args[args.length - 1]);
            } catch (Exception ignored) {
            }
        }

        StringBuilder baseBuilder = new StringBuilder();
        if (digital) {
            if (fuzzy) {
                baseBuilder.append("23456789");
            } else {
                baseBuilder.append("0123456789");
            }
        }

        if (lowerCase) {
            if (fuzzy) {
                baseBuilder.append("abcdefghijkmnpqrstuvwxyz");
            } else {
                baseBuilder.append("abcdefghijklmnopqrstuvwxyz");
            }
        }

        if (upperCase) {
            if (fuzzy) {
                baseBuilder.append("ABCDEFGHIJKLMNPQRSTUVWXYZ");
            } else {
                baseBuilder.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
            }
        }
        baseBuilder.append(appendCharacters);

        System.out.println(genPass(baseBuilder.toString(), len));
    }
}
