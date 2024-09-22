package br.edu.pweb2.incruise.services;

import org.springframework.stereotype.Service;

import java.util.InputMismatchException;

@Service
public class UtilService {
    public static boolean isValidCNPJ(String cnpj) {
        cnpj = cnpj.replaceAll("\\D", "");
        if (cnpj.length() != 14) {
            return false;
        }
        try {
            char dig13, dig14;
            int sm, i, r, num, peso;
            sm = 0;
            peso = 2;
            for (i = 11; i >= 0; i--) {
                num = (int) (cnpj.charAt(i) - 48);
                sm += (num * peso);
                peso = (peso == 9) ? 2 : peso + 1;
            }
            r = sm % 11;
            dig13 = (r < 2) ? '0' : (char) ((11 - r) + 48);
            sm = 0;
            peso = 2;
            for (i = 12; i >= 0; i--) {
                num = (int) (cnpj.charAt(i) - 48);
                sm += (num * peso);
                peso = (peso == 9) ? 2 : peso + 1;
            }

            r = sm % 11;
            dig14 = (r < 2) ? '0' : (char) ((11 - r) + 48);
            return (dig13 == cnpj.charAt(12)) && (dig14 == cnpj.charAt(13));

        } catch (InputMismatchException e) {
            return false;
        }
    }
}