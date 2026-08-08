/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.desenvolver60;

import java.sql.Connection;

/**
 *
 * @author steph
 */
public class Desenvolver60 {

    public static void main(String[] args) {
        Janela1 f = new Janela1();
        f.setVisible(true);
        
         try {
            Connection conexao = Conexao.conectar();

            System.out.println("Conectou no banco!");

            conexao.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
