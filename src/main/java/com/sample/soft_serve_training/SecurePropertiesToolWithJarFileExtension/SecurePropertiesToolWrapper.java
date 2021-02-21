package com.sample.soft_serve_training.SecurePropertiesToolWithJarFileExtension;

import com.mulesoft.modules.configuration.properties.api.EncryptionAlgorithm;
import com.mulesoft.modules.configuration.properties.api.EncryptionMode;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.mule.encryption.Encrypter;
import org.mule.encryption.exception.MuleEncryptionException;

public class SecurePropertiesToolWrapper {
    public static final String ENCRYPTION_ACTION = "encrypt";
    public static final String DECRYPTION_ACTION = "decrypt";
    private static final String HOLE_FILE_LEVEL_TYPE = "file-level";
    private static final String FILE_TYPE = "file";
    private static final String WORD_TYPE = "string";
    private static final String YAML_FILE = ".yaml";
    private static final String PROPERTIES_FILE_SEPARATOR = "=";
    private static final String YAML_SEPARATOR = ":";
    private static final String PROPERTIES_FILE = ".properties";
    private static final char COMMENTS = '#';
    private static final char VALUE_DELIMITER = '"';
    private static final Pattern encryptPattern = Pattern.compile("!\\[(.*)\\]");
    private static final String USE_RANDOM_IV = "--use-random-iv";

    public SecurePropertiesToolWrapper() {
    }

    private static Encrypter createEncrypter(String algorithm, String mode, String key, boolean useRandomIVs) {
        return EncryptionAlgorithm.valueOf(algorithm).getBuilder().forKey(key).using(EncryptionMode.valueOf(mode)).useRandomIVs(useRandomIVs).build();
    }

    private static String decrypt(String value, String algorithm, String mode, String key, boolean useRandomIVs) throws MuleEncryptionException {
        Encrypter encrypter = createEncrypter(algorithm, mode, key, useRandomIVs);
        return new String(encrypter.decrypt(Base64.getDecoder().decode(value)));
    }

    private static String encrypt(String value, String algorithm, String mode, String key, boolean useRandomIVs) throws MuleEncryptionException {
        Encrypter encrypter = createEncrypter(algorithm, mode, key, useRandomIVs);
        return new String(Base64.getEncoder().encode(encrypter.encrypt(value.getBytes())));
    }

    private static byte[] decrypt(byte[] value, String algorithm, String mode, String key, boolean useRandomIVs) throws MuleEncryptionException {
        Encrypter encrypter = createEncrypter(algorithm, mode, key, useRandomIVs);
        return encrypter.decrypt(Base64.getDecoder().decode(value));
    }

    private static byte[] encrypt(byte[] value, String algorithm, String mode, String key, boolean useRandomIVs) throws MuleEncryptionException {
        Encrypter encrypter = createEncrypter(algorithm, mode, key, useRandomIVs);
        return Base64.getEncoder().encode(encrypter.encrypt(value));
    }

    public static String applyOverString(String action, String algorithm, String mode, String key, boolean useRandomIVs, String value) throws MuleEncryptionException {
        return action.equals("encrypt") ? encrypt(value, algorithm, mode, key, useRandomIVs) : decrypt(value, algorithm, mode, key, useRandomIVs);
    }

    private static String applyOverFileValue(String type, String action, String algorithm, String mode, String key, boolean useRandomIVs, String value) throws MuleEncryptionException {
        if (action.equals("encrypt")) {

            if (type.equals("marked-file") && !StringUtils.startsWith(value, "![") && !StringUtils.endsWith(value, "]")) {
                return value;
            } else if (type.equals("marked-file") && StringUtils.startsWith(value, "![") && StringUtils.endsWith(value, "]")) {
                value =  StringUtils.substringBetween(value, "![","]");
            }

            return "![" + encrypt(value, algorithm, mode, key, useRandomIVs) + "]";
        } else {
            Matcher m = encryptPattern.matcher(value);

            if (type.equals("marked-file")) {
                return m.find() ? "![" + decrypt(m.group(1), algorithm, mode, key, useRandomIVs) + "]" : value;
            }

            return m.find() ? decrypt(m.group(1), algorithm, mode, key, useRandomIVs) : value;
        }
    }

    private static String removeComment(String line) {
        StringBuilder result = new StringBuilder();
        boolean opened = false;
        char[] var3 = line.toCharArray();
        int var4 = var3.length;

        for (int var5 = 0; var5 < var4; ++var5) {
            char c = var3[var5];
            if (c == '#' && !opened) {
                return result.toString();
            }

            result.append(c);
            if (c == '"') {
                if (opened) {
                    return result.toString();
                }

                opened = true;
            }
        }

        return result.toString();
    }

    private static String getComment(String line) {
        String result = "";
        boolean startedComment = false;
        boolean openedValue = false;
        char[] var4 = line.toCharArray();
        int var5 = var4.length;

        for (int var6 = 0; var6 < var5; ++var6) {
            char c = var4[var6];
            if (c == '#' && !openedValue) {
                startedComment = true;
            }

            if (c == '"') {
                openedValue = !openedValue;
            }

            if (startedComment) {
                result = result + c;
            }
        }

        return result;
    }

    private static void processFileLine(String type, String line, BufferedWriter writer, String action, String algorithm, String mode, String key, boolean useRandomIVs, String separator, boolean space) throws IOException, MuleEncryptionException {
        String comments = getComment(line);
        line = removeComment(line);
        if (!line.contains(separator)) {
            writer.write(comments);
        } else {
            String in = line.split(separator)[0];
            writer.write(in + separator);
            String value = line.substring(in.length() + 1).trim();
            if (value.length() > 0) {
                if (space) {
                    writer.write(" ");
                }

                String[] splitted = value.split("\"");
                if (splitted.length == 0) {
                    writer.write("\"" + applyOverFileValue(type, action, algorithm, mode, key, useRandomIVs, "") + '"');
                } else if (splitted.length == 1) {
                    writer.write(applyOverFileValue(type, action, algorithm, mode, key, useRandomIVs, value));
                } else {
                    writer.write("\"" + applyOverFileValue(type, action, algorithm, mode, key, useRandomIVs, splitted[1]) + '"');
                }
            }

            if (comments.length() > 0) {
                writer.write(" " + comments);
            }

        }
    }

    public static void applyOverFile(String type, String action, String algorithm, String mode, String key, boolean useRandomIVs, String inputFilePath, String outputFilePath) throws IOException {
        StringBuilder errorsFound = new StringBuilder();
        BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFilePath));
        Throwable var9 = null;

        try {
            Files.lines(Paths.get(inputFilePath)).forEach((line) -> {
                try {
                    if (line.trim().length() == 0) {
                        writer.write("\n");
                        return;
                    }

                    if (inputFilePath.endsWith(".yaml")) {
                        processFileLine(type, line, writer, action, algorithm, mode, key, useRandomIVs, ":", true);
                    } else {
                        processFileLine(type, line, writer, action, algorithm, mode, key, useRandomIVs, "=", false);
                    }

                    writer.write("\n");
                } catch (MuleEncryptionException var10) {
                    errorsFound.append(var10.getCause().getMessage()).append(System.lineSeparator());
                } catch (IOException var11) {
                    errorsFound.append(var11.getMessage()).append(System.lineSeparator());
                }

            });
        } catch (Throwable var18) {
            var9 = var18;
            throw var18;
        } finally {
            if (writer != null) {
                if (var9 != null) {
                    try {
                        writer.close();
                    } catch (Throwable var17) {
                        var9.addSuppressed(var17);
                    }
                } else {
                    writer.close();
                }
            }

        }

        if (errorsFound.length() != 0) {
            throw new IOException(errorsFound.toString());
        }
    }

    public static void applyHoleFile(String action, String algorithm, String mode, String key, boolean useRandomIVs, String inputFilePath, String outputFilePath) throws IOException, MuleEncryptionException {
        File inputFile = new File(inputFilePath);
        InputStream stream = new FileInputStream(inputFile);
        byte[] bytes = IOUtils.toByteArray(stream);
        byte[] result;
        if (action.equals("encrypt")) {
            result = encrypt(bytes, algorithm, mode, key, useRandomIVs);
        } else {
            result = decrypt(bytes, algorithm, mode, key, useRandomIVs);
        }

        FileUtils.writeByteArrayToFile(new File(outputFilePath), result);
    }

    private static void usage() {
        System.err.println("Usage:");
        System.err.println("\tjava -cp secure-properties-tool.jar com.mulesoft.tools.SecurePropertiesTool string <encrypt|decrypt> <algorithm> <mode> <key> <value> --use-random-iv [optional]");
        System.err.println("\tor");
        System.err.println("\tjava -cp secure-properties-tool.jar com.mulesoft.tools.SecurePropertiesTool file <encrypt|decrypt> <algorithm> <mode> <key> <input file> <output file> --use-random-iv [optional]");
        System.err.println("\tor");
        System.err.println("\tjava -cp secure-properties-tool.jar com.mulesoft.tools.SecurePropertiesTool file-level <encrypt|decrypt> <algorithm> <mode> <key> <input file> <output file> --use-random-iv [optional]");
    }

    private static boolean extractUseRandomIVArgument(String[] args) {
        return (args != null && args.length != 0) && "--use-random-iv".equals(args[args.length - 1]);
    }

    private static boolean validateArguments(String[] args) {
        if (args != null && args.length != 0) {
            boolean useRandomIVs = extractUseRandomIVArgument(args);
            int minArguments = useRandomIVs ? 7 : 6;
            if (args.length < minArguments) {
                return false;
            } else {
                String type = args[0];
                String action = args[1];
                String algorithm = args[2];
                String mode = args[3];
                if (!type.equals("string") && !type.equals("file") && !type.equals("file-level") && !type.equals("marked-file")) {
                    return false;
                } else if (!action.equals("encrypt") && !action.equals("decrypt")) {
                    return false;
                } else {
                    try {
                        EncryptionAlgorithm.valueOf(algorithm);
                    } catch (IllegalArgumentException var11) {
                        return false;
                    }

                    try {
                        EncryptionMode.valueOf(mode);
                    } catch (IllegalArgumentException var10) {
                        return false;
                    }

                    int maxArgumentsLength;
                    if (!type.equals("file") && !type.equals("file-level") && !type.equals("marked-file")) {
                        maxArgumentsLength = useRandomIVs ? 7 : 6;
                        return args.length == maxArgumentsLength;
                    } else {
                        maxArgumentsLength = useRandomIVs ? 8 : 7;
                        if (args.length != maxArgumentsLength) {
                            return false;
                        } else {
                            String input = args[5];
                            String output = args[6];
                            return input.endsWith(".yaml") && output.endsWith(".yaml") || input.endsWith(".properties") && output.endsWith(".properties");
                        }
                    }
                }
            }
        } else {
            return false;
        }
    }

    public static void main(String[] args) {

//        https://docs.mulesoft.com/mule-runtime/4.3/secure-configuration-properties#parameter-reference

//        String[] args = {"string", "encrypt", "Blowfish", "CBC", "mulesoft", "some value to encrypt"};

//        String[] args = {"string", "decrypt", "Blowfish", "CBC", "mulesoft", "8q5e1+jy0cND2iV2WPThahmz6XsDwB6Z"};

//        marked-file encrypt Blowfish CBC mulesoft C:\Users\vfihu\Desktop\securePropertiesTool\marked-local-properties.yaml marked-local-properties_out.yaml

        if (!validateArguments(args)) {
            System.err.println("Invalid arguments");
            usage();
            System.exit(1);
        }

        try {
            boolean useRandomIVs = extractUseRandomIVArgument(args);
            String type = args[0];
            String action = args[1];
            String algorithm = args[2];
            String mode = args[3];
            String key = args[4];
            if (type.equals("string")) {
                System.out.println(applyOverString(action, algorithm, mode, key, useRandomIVs, args[5]));
            } else {
                String input = args[5];
                String output = args[6];
                if (type.equals("file") || type.equals("marked-file")) {
                    applyOverFile(type, action, algorithm, mode, key, useRandomIVs, input, output);
                } else {
                    applyHoleFile(action, algorithm, mode, key, useRandomIVs, input, output);
                }
            }
        } catch (MuleEncryptionException var9) {
            System.err.println(var9.getCause().getMessage());
            System.exit(2);
        } catch (IOException var10) {
            System.err.println(var10.getMessage());
            System.exit(3);
        }
    }
}

