package br.com.Gabriel;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.Gabriel.exceptions.UnsuportedMathOperationException;

@RestController
public class MathController {
	
	private final AtomicLong counter = new AtomicLong();
	
	@RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method=RequestMethod.GET) // PASSANDO PARAMETROS OBRIGATORIOS
	public Double sum(@PathVariable(value = "numberOne")String numberOne,
					  @PathVariable(value = "numberTwo")String numberTwo
			) throws Exception {
		
		
		if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
			throw new UnsuportedMathOperationException("Please set a numeric value");
		}
		
		return convertToDouble(numberOne) + convertToDouble(numberTwo);
	}

	private Double convertToDouble(String strNumber) {
		if(strNumber == null) return 0D;
		// BR 10,50 US 10.50
		String number = strNumber.replaceAll(",", "."); //VAI TROCAR SEMPRE , POR . 
		if(isNumeric(number)) return Double.parseDouble(number);
		return 0D;
	}

	private boolean isNumeric(String strNumber) {
		
		if(strNumber == null) return false;
		String number = strNumber.replaceAll(",", ".");
		return number.matches("[-+]?[0-9]*\\.?[0-9]+"); // NUMERO POSITIVO OU NEGATIVO DE 0 A 9 . NUMERO DE 0 A 9
		
		
	}

}
