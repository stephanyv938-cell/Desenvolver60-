/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.desenvolver60;

import java.security.SecureRandom;


public class CodigoVerificacao {
    private static final SecureRandom random = new SecureRandom();
    public static String gerarCodigo(){
        int numero = 100000 + random.nextInt(900000);
        return String.valueOf(numero);
    }
}

