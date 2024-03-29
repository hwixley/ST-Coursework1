package st;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Parser {
	public static final int INTEGER = 1;
	public static final int BOOLEAN = 2;
	public static final int STRING = 3;
	public static final int CHAR = 4;
	public static final int CHARLIST =5;//TDD
	
	private OptionMap optionMap;
	
	public Parser() {
		optionMap = new OptionMap();
	}
	
	public void add(String option_name, String shortcut, int value_type) {
		optionMap.store(option_name, shortcut, value_type);
	}
	
	public void add(String option_name, int value_type) {
		optionMap.store(option_name, "", value_type);
	}

	public int getInteger(String option) {
		String value = getString(option);
		int type = getType(option);
		int result;
		switch (type) {
		case INTEGER:
			try {
				result = Integer.parseInt(value);
			} catch (Exception e) {
		        try {
		            new BigInteger(value);
		        } catch (Exception e1) {
		            result = 0;
		        }
		        result = 0;
		    }
			break;
		case BOOLEAN:
			if (getBoolean(option) == false) {
				result = 0;
			} else {
				result = 1;
			}
			break;
		case STRING:
		    int power = 1;
		    result = 0;
		    char c;
		    for (int i = value.length() - 1; i >= 0; --i){
		        c = value.charAt(i);
		        if (!Character.isDigit(c)) return 0;
		        result = result + power * (c - '0');
		        power *= 10;
		    }
		    break;
		case CHAR:
			result = (int)getChar(option);
			break;
		default:
			result = 0;
		}
		return result;
	}
	
	
	public boolean getBoolean(String option) {
		String value = getString(option);
		boolean result;
		if (value.toLowerCase().equals("false") || value.equals("0") || value.equals("")) {
			result = false;
		} else {
			result = true;
		}
		return result;
	}
	
	public String getString(String option) {
		String result = optionMap.getValue(option);
		return result;
	}
	
	public char getChar(String option) {
		String value = getString(option);
		char result;
		if (value.equals("")) {
			result = '\0';
		} else {
			result = value.charAt(0);
		}
		return result;
	}
	
	public List<Character> getCharacterList(String option) {
		//Set value string to lowercase so only lowercase chars are returned
		String value = getString(option).toLowerCase();
		
		ArrayList<Character> result = new ArrayList<Character>();
		
		//Check if the charList should not be empty
		if (value != "" && value.charAt(0) != '-') {
			Character lastChar = null;
			Character startInterval = null;
			Boolean lastCharWasInterval = false;
			
			//Loop through the characters in the 'value' string
			for (int i = 0; i < value.length(); i++) {
				Character charI = value.charAt(i); 
				
				//Check the given char is valid
				if (Character.isLetter(charI) || Character.isDigit(charI) || charI == '.') {
					
					//Check if there is an interval
					if (lastChar != null && lastChar == '-' && charI != '.') {
						lastCharWasInterval = true;
							
						//Change chars to ASCII values for easy manipulation
						int start = (int) startInterval;
						int end = (int) charI;
						
						//Check if this interval is between same char types (both letter or both digit)
						if ((Character.isDigit(startInterval) && Character.isDigit(charI)) || 
							(Character.isLetter(startInterval) && Character.isLetter(charI))) {
							
							//Check if interval start value is less than the end
							if (start < end) {
								for (int k = start+1; k <= end; k++) {
									result.add((char) k);
								}
								
							//Else interval start value is greater than the end
							} else {
								for (int k = start-1; k >= end; k--) {
									result.add((char) k);
								}
							}
						
						//Interval between different char types (letter & digit)
						} else {
							//Checks if the start of the interval is a digit
							if (start < end) {
								//Adds the digits
								for (int k = start+1; k <= ((int) '9'); k++) {										
									result.add((char) k);
								}
								//Adds the chars
								for (int j = ((int) 'a'); j <= ((int) end); j++) {
									result.add((char) j);
								}
							
							//Else: the start of the interval is a char
							} else {
								//Adds the chars
								for (int j = ((int) 'a'); j <= ((int) end); j++) {
									result.add((char) j);
								}
								//Adds the digits
								for (int k = start+1; k <= ((int) '9'); k++) {
									result.add((char) k);
								}
							}
						}
						
					//There is no interval so we add the valid char
					} else {
						result.add(charI);
						lastCharWasInterval = false;
					}
					
					lastChar = charI;
				
				//Check if the given char is a hyphen (and prepare interval)
				} else if (charI == '-') {
					
					//Check if this is a chained interval ie. a-g-z
					if (lastCharWasInterval) {
						result.add(lastChar); //If so we add the middle char again (ie. g)
					}
					
					//If the last char was valid prepare the interval
					if (lastChar != null && lastChar != '-') {
						startInterval = lastChar;
						lastChar = '-';
						
					//Else do not account for this hyphen char
					} else {
						lastChar = null;
					}
					lastCharWasInterval = false;
					
				//Else do not account for this char (it is not a hyphen, digit, letter, or full-stop)
				} else {
					lastChar = null;
					lastCharWasInterval = false;
				}
			}
		}

		return result;
	}
	
	public int parse(String command_line_options) {
		if (command_line_options == null) {
			return -1;
		}
		int length = command_line_options.length();
		if (length == 0) {
			return -2;
		}
		
		int char_index = 0;
		while (char_index < length) {
			char current_char = command_line_options.charAt(char_index);

			while (char_index < length) {
				current_char = command_line_options.charAt(char_index);
				if (current_char != ' ') {
					break;
				}
				char_index++;
			}
			
			boolean isShortcut = true;
			String option_name = "";
			String option_value = "";
			if (current_char == '-') {
				char_index++;
				current_char = command_line_options.charAt(char_index);
				if (current_char == '-') {
					isShortcut = false;
					char_index++;
				}
			} else {
				return -3;
			}
			current_char = command_line_options.charAt(char_index);
			
			while (char_index < length) {
				current_char = command_line_options.charAt(char_index);
				if (Character.isLetterOrDigit(current_char) || current_char == '_') {
					option_name += current_char;
					char_index++;
				} else {
					break;
				}
			}
			
			boolean hasSpace = false;
			if (current_char == ' ') {
				hasSpace = true;
				while (char_index < length) {				
					current_char = command_line_options.charAt(char_index);
					if (current_char != ' ') {
						break;
					}
					char_index++;
				}
			}

			if (current_char == '=') {
				char_index++;
				current_char = command_line_options.charAt(char_index);
			}
			if ((current_char == '-'  && hasSpace==true ) || char_index == length) {
				if (getType(option_name) == BOOLEAN) {
					option_value = "true";
					if (isShortcut) {
						optionMap.setValueWithOptioShortcut(option_name, option_value);
					} else {
						optionMap.setValueWithOptionName(option_name, option_value);
					}
				} else {
					return -3;
				}
				continue;
			} else {
				char end_symbol = ' ';
				current_char = command_line_options.charAt(char_index);
				if (current_char == '\'' || current_char == '\"') {
					end_symbol = current_char;
					char_index++;
				}
				while (char_index < length) {
					current_char = command_line_options.charAt(char_index);
					if (current_char != end_symbol) {
						option_value = option_value + current_char;
						char_index++;
					} else {
						break;
					}
				}
			}
			
			if (isShortcut) {
				optionMap.setValueWithOptioShortcut(option_name, option_value);
			} else {
				optionMap.setValueWithOptionName(option_name, option_value);
			}
			char_index++;
		}
		return 0;
	}
	
	private int getType(String option) {
		int type = optionMap.getType(option);
		return type;
	}
	
	@Override
	public String toString() {
		return optionMap.toString();
	}

}

