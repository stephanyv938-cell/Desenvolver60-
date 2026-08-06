package com.mycompany.desenvolver60;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
public class FonteUtil {
    public static Font carregarFonte(String nomeFonte, float tamanho) {
        try {
            File arquivo = new File("src/main/resources/fonts/" + nomeFonte);
            return Font.createFont(Font.TRUETYPE_FONT, arquivo).deriveFont(tamanho);
        } catch (Exception e) {
            e.printStackTrace();
            return new Font("Arial", Font.PLAIN, (int) tamanho);
        }
    }
}

