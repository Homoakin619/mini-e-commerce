package com.blog.app.utilities;

import java.security.SecureRandom;
import java.util.Objects;
import java.util.Random;

public class ReferenceCodeGenerator {
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = UPPER.toLowerCase();
    private static final String DIGIT = "0123456789";
    private static final String ALPHANUM = UPPER + LOWER + DIGIT;
    private final Random random;
    private final char[] characters;
    private final char[] buff;

    public ReferenceCodeGenerator(int length, Random random, String characters) {
        this.random = Objects.requireNonNull(random);
        this.characters = characters.toCharArray();
        this.buff = new char[length];
    }

    public ReferenceCodeGenerator(int length, Random random) {
        // call method with length, random and string
        this(length, random, ALPHANUM);
    }

    public ReferenceCodeGenerator(int length) {
        // call method with length and random
        this(length, new SecureRandom());
    }

    public ReferenceCodeGenerator() {
        this(20);
    }

    public String generateRefCode() {
        for (int i = 0; i < buff.length; i++) {
            buff[i] = characters[random.nextInt(characters.length)];
        }

        return new String(buff);
    }

}
