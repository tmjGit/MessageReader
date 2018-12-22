package de.oio.tmj.addressbook.shared.model;

import java.util.function.Function;

public class Phone extends EntityBase {
	// Plus Klammer-auf/zu Divis Punkt Slash Space 0-9 A-Z a-z
	private static final String ALPHABET_MATCHER="[\\+\\(\\)\\-\\./ \\w]*";
	private static final String VALIDATION_MATCHER="^[\\(\\)\\-\\./ ]*(\\+\\w)?[\\(\\)\\-\\./ \\w]*$";
//	^				Anfang
//	[\(\)\-\\./ ]*		Zeichen aus "()/- " beliebig oft
//	(\+\w)?			+ gefolgt entweder von Ziffer oder Buchstabe, 0-1x
//                  \w  bedeutet [0-9A-Za-z]
//	[\(\)\-\./ \w]*	"()/- " Ziffern Buchstaben, beliebig oft
//	$						Ende
			
	private static Function<Phone, String> calculateDisplayStringFunction=phone->("".equals(phone.label)?"":phone.label+": ")+phone.getNumber();
	private int id;
	private String label;
	private String number;
//	private AppMessages messages=GWT.create(AppMessages.class);// Destroys the serializabitily!
	
	protected Phone() { }
	protected Phone(GWTguid guid) {
		super(guid);
	}
	/**
	 * @param number - String representating a phone number.
	 * @throws IllegalArgumentException - if this String is not a valid number number. 
	 * 				This can be tested using {@link passesValidation} and {@link validationFailResult}
	 */
	public Phone(GWTguid guid,String number) throws NullPointerException,IllegalArgumentException{
		this(guid,"",number);
	}
	public Phone(GWTguid guid,String label,String number) throws NullPointerException,IllegalArgumentException{
		this(guid);
		setNumber(number);
		setLabel(label);
	}

	public static boolean passesValidation(String number) {
		if(null==number) {return false;}
		return number.matches(VALIDATION_MATCHER);
	}
	
	/**
	 * @param number – A potentially phone number to test.
	 * @return – The characters of {@code number} not embodies by the phone alphabet.
	 * @see {@link ALPHABET_MATCHER}
	 */
	public static String validationFailResult(String number) {
		if(null==number) {return number;}
		return number.replaceAll(ALPHABET_MATCHER, "");
	}
	
	/**
	 * @return The relevant parts of the phone number which is the digits without
	 * any separator. Vanity numbers will be converted to its numeric counterpart.
	 */
	public String signifikat() {
		return decodeVanityNumber(
			number
			.replace(" ", "")
			.replace("/","")
			.replace("-", "")
			.replace(".", "")
			);
	}
	
	public static String decodeVanityNumber(String codedNumber) throws NullPointerException {
		String upperCase=codedNumber.toUpperCase();
		String decodedNumber="";
		for (int i=0; i<upperCase.length(); i++) {
			decodedNumber=decodedNumber + decodeVanityDigit(upperCase.charAt(i));
		}
		return decodedNumber;
	}

	private static char decodeVanityDigit(char codedChar) {
		switch(codedChar){
			case 'A': case 'B': case 'C': return '2';
			case 'D': case 'E': case 'F': return '3';
			case 'G': case 'H': case 'I': return '4';
			case 'J': case 'K': case 'L': return '5';
			case 'M': case 'N': case 'O': return '6';
			case 'P': case 'Q': case 'R': case 'S': return '7';
			case 'T': case 'U': case 'V': return '8';
			case 'W': case 'X': case 'Y': case 'Z': return '9';
//			default: return codedChar;
		}
		return codedChar;
	}
	public static void setCalculateDisplayString(Function<Phone,String> calculateDisplayString) {
		calculateDisplayStringFunction=calculateDisplayString;
	}
	public String getDisplayString() {
		return calculateDisplayStringFunction.apply(this);
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) throws NullPointerException {
		if(null==label) {
			throw new NullPointerException();//messages.phoneLabelValidationErrorMessage());
		}
		this.label = label;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) throws NullPointerException,IllegalArgumentException {
		if(null==number) {
			throw new NullPointerException();//messages.phoneNumberValidationErrorMessage());
		}else if(!passesValidation(number)) {
			throw new IllegalArgumentException();//messages.phoneNumberValidationErrorMessage());
		}
		this.number=number;
	}
	public int getId() {
		return id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + id;
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Phone other = (Phone) obj;
		if (id != other.id)
			return false;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Phone [id=" + id + ", label=" + label + ", number=" + number + "]";
	}
	

}
