/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.desenvolver60;

public class TesteEmail {

    public static void main(String[] args) {

      String email = System.getenv("EMAIL_REMETENTE");
      String senha = System.getenv("SENHA_APP");

        System.out.println("EMAIL: " + email);
        System.out.println("SENHA EXISTE: " + (senha != null));
        System.out.println("TAMANHO: " + (senha == null ? 0 : senha.length()));

        try {
            EnviarEmail.enviarCodigo(
                    "SEU_EMAIL_DE_TESTE@gmail.com",
                    "123456"
            );

            System.out.println("ENVIO OK");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}