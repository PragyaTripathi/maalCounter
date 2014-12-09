/**
 * 
 */
package com.maalapp;

/**
 * @author Pragya
 *
 */
public class Maal {
//		SINGLE_TIPLU ("singleTiplu", 3),
//	   SINGLE_JHIPLU   ("singleJhiplu", 2),
//	   SINGLE_POPLU   ("singlePoplu", 2),
//	   SINGLE_MARRIAGE    ("singleMarriage", 10),
//	   ORDINARY_CARD_TUNNELLA ("ordinaryCardTunnella",   5),
//	   ORDINARY_JOKER_TUNNELLA  ("ordinaryJokerTunnella", 10),
//	   POPLU_OR_JHIPLU_TUNNELLA  ("popluJhipluTunnella", 20),
//	   DOUBLE_TIPLU ("doubleTiplu", 7),
//	   DOUBLE_JHIPLU   ("doubleJhiplu", 5),
//	   DOUBLE_POPLU   ("doublePoplu", 5),
//	   DOUBLE_MARRIAGE    ("doubleMarriage", 30),
//	   TRIPLE_JHIPLU   ("tripleJhiplu", 10),
//	   TRIPLE_POPLU   ("triplePoplu", 10),
//	   NONE("noMaal",0);
		
		

	    private String name;   // name of the maal
	    private int value; // the value of that maal
	    //initialize it
	    Maal(String name, int value) {
	        this.name = name;
	        this.value = value;
	    }
	    /**
	     * Returns the maal name
	     * @return String maal name
	     * */
	    public String getMaalName() { return name; }
	    /**
	     * Returns the value of maal
	     * @return int maal value
	     * */
	    public int getValue() { return value; }
	    /**
	     * Set the maal Value
	     * */
	    public void setValue(int newValue) { value=newValue; }
		

}
