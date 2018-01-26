/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package component;

import javafx.scene.control.TextField;

/**
 *
 * @author Jirka_
 * Třída slouží pro TextField s číselným vstupem
 */
public class NumberTextField extends TextField
{
    /**
    * Metoda slouží změnu textu při vstupu.
    * 
    *@Override
    *
    */
    public void replaceText(int start, int end, String text)
    {
        if (validate(text))
        {
            super.replaceText(start, end, text);
        }
    }

    /**
    * Metoda slouží pro replaceování selekce. 
    * @Override
    */
    public void replaceSelection(String text)
    {
        if (validate(text))
        {
            super.replaceSelection(text);
        }
    }

    /**
    * Metoda slouží pro ověření textu;
    * @Override
    */
    private boolean validate(String text)
    {
        return (getText() + text).matches("^([0-9]|([0-9][0-9]|)|100)$");
    }
}