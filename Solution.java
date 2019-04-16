package br.com.accenture.CrossWords;

import java.util.*;

public class Solution {
    
    static String[] crosswordPuzzle(String[] crossword, String hints) {
        String[] splitHints = hints.split(";");
        char[][] charArray = arrayStringToMatrixChar(crossword);
        fillWords(charArray, splitHints, new HashSet<String>());
        return matrixCharToArrayString(charArray);
    }
    
    //converte um vetor de string em uma matriz de char facilitando a manipulacao de caracteres
    public static char[][] arrayStringToMatrixChar(String[] crosswordString){
    	char[][] crosswordChar =  new char[crosswordString.length][crosswordString.length];
    	for(int i = 0; i < crosswordString.length; i++){
    		crosswordChar[i] = crosswordString[i].toCharArray();
    	}
    	return crosswordChar;
    }
    
    //converte uma matriz de caracteres em um vetor de string
    public static String[] matrixCharToArrayString(char[][] charArray){
    	String [] matrix = new String[charArray.length];
    	for(int i = 0; i < matrix.length; i++){
    		matrix[i] = String.valueOf(charArray[i]);
    	}
    	return matrix;
    }
    
    /*Metodo faz o loop entre as palavras, buscando as que ainda nao foram preenchidas e localiza espacos vazios
      para entao chamar o metodo fillCharacters que ira tentar preencher esta palavra no espaco vazio */
    public static boolean fillWords(char[][] crossword, String[] hints,  Set<String> set ){
    	
    	/*condicao de parada, caso o set esteja com o mesmo tamanho do vetor hints significa que todas as palavras
    	foram inseridas*/
    	if(hints.length == set.size()){ 
    		return true;
    	}else{	
    		boolean filled = false;
	    	//loop no vetor de palavras
	    	for(String hint: hints){
	    		
	    		if(!set.contains(hint)){//caso a palavra ja esteja inserida no set ela e ignorada
	    			
		    		set.add(hint);
		    		int i,j = 0;
		    		boolean verticalAlign = false, possibleMatch = false;
		    		
		    		//busca por um hifen ou alguma letra que possa ser utilizada para a palavra em questao
		    		for(i = 0; i < crossword.length && !possibleMatch; i++){
		    			for(j = 0; j < crossword.length && !possibleMatch; j++){
		    				if( ( crossword[i][j] == '-' || (crossword[i][j] == hint.charAt(0) && 
		    						( (i < crossword.length && crossword[i+1][j] == '-') ||
		    								(j < crossword.length && crossword[i][j+1] == '-')	 
		    						) ) //condicao que evita que uma letra sozinha (sem um hifen ao lado) seja usada como tentativa
		    					)) 
		    				{
		    					possibleMatch = true;
		    				}
		    			}
		    		}
		    		
		    		//coordenadas do hifen/letra encontrado
		    		i--; 
		    		j--;
		    		
		    		//verifica se o hifen/letra encontrado faz parte de uma sequencia vertical 
		    		if(i + 1 < crossword.length && (crossword[i+1][j] == '-' ||  crossword[i+1][j] == hint.charAt(1))){
						verticalAlign = true;
						
					}else{
						verticalAlign = false;
					}
		    		
		    		//preenche a palavra no espaco encontrado
		    		filled = fillCharacters(crossword, hints, hint, hint.length(), set, i, j , 0, verticalAlign);
		    		
		    		//caso nao seja possivel preencher a palavra entao ela e removida do set
		    		if(!filled){
		    			set.remove(hint);
		    		}else{
		    			return true; 
		    		}	
	    		}
	    	}
	    	return filled;
    	}	   	
    }
    
    //metodo preenche de forma recursiva (no espaco vazio localizado i e j) os caracteres da palavra informada
    public static boolean fillCharacters(char[][] matrix, String[] hints, String hint, int sizeHint ,  Set<String> set, 
    		int i, int j, int positionHint, boolean verticalAlign){
    	
    	//verifica se todas as letras da palavra foram preenchidas
		if(positionHint >= sizeHint){
			
			//condicao de parada, retorna true quando todas as palavras ja estiverem sido preenchidas
			if(set.size() >= hints.length){
				return true;
				
			}else{ //caso ainda restem palavras
				
				//o metodo e chamado de forma recursiva para preencher as demais palavras
				return fillWords(matrix,hints,set );
			}
		}
		
		//verifica se a palavra ultrapassou os espacos vazios
		if(i >= matrix.length || j >= matrix.length 
				|| ( matrix[i][j] != '-' && matrix[i][j] !=  hint.charAt(positionHint))){
			return false;
		}
		
		char originalCharacter = matrix[i][j];
		matrix[i][j] = hint.charAt(positionHint);
		
		//printMatrix(matrix, set);
		

		boolean preenchido = false;
		
		if(verticalAlign){
			//verifica se sobrou espaço no conjunto de hifens
			if(positionHint+1 >= sizeHint && (i + 1 < matrix[0].length) && matrix[i + 1][j] == '-'){
				preenchido = false;
			}else{
				preenchido  = fillCharacters(matrix,hints, hint , sizeHint, set, i + 1, j, positionHint + 1, verticalAlign);
			}
		}else{
			//verifica se sobrou espaço no conjunto de hifens
			if(positionHint+1 >= sizeHint && (j + 1 < matrix[0].length) && matrix[i][j + 1] == '-'){
				preenchido = false;
			}else{
				//de forma recursiva o proximo espaco e preenchido com a proxima letra
				preenchido = fillCharacters(matrix,hints, hint , sizeHint ,  set, i , j + 1, positionHint + 1, verticalAlign);
			}	
		}
		
		if(preenchido){	
			return true;
		}else{
			//caso nao seja possivel preencher toda a palavra os espacos voltam ao valor original por recursao
			matrix[i][j] = originalCharacter;
			//printMatrix(matrix, set);
			return false;
		}
		
	}
    
    //metodo auxiliar usado para impressao da matriz passo a passo
    public static void printMatrix(char[][] matrix, Set<String> set){
    	 for (int i = 0; i < matrix.length; i++) {
    		 for(int j = 0; j < matrix.length; j++)
    			 System.out.print(matrix[i][j]); 
    		 System.out.println();
         }
    	 System.out.println();
    	 printSet(set);
    	 
    }
    
  //metodo auxiliar usado para impressao do set passo a passo
    public static void printSet(Set<String> set){
    	for(String word : set){
    		System.out.print(word + " ");
    	}
    	System.out.println();
    }

    public static void main(String[] args) {
		 Scanner in = new Scanner(System.in);
	     String[] crossword = new String[10];    
         for(int crossword_i = 0; crossword_i < 10; crossword_i++){
             crossword[crossword_i] = in.next();
         }
         String hints =  in.next();

         String[] result = crosswordPuzzle(crossword, hints);
         for (int i = 0; i < result.length; i++) {
             System.out.print(result[i] + (i != result.length - 1 ? "\n" : ""));
         }
         System.out.println("");

         in.close();
    }
}
