package OOP1.TechSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;


/**
 * The responder class represents a response generator object.
 * It is used to generate an automatic response, based on specified input.
 * Input is presented to the responder as a set of words, and based on those
 * words the responder will generate a String that represents the response.
 *
 * Internally, the reponder uses a HashMap to associate words with response
 * strings and a list of default responses. If any of the input words is found
 * in the HashMap, the corresponding response is returned. If none of the input
 * words is recognized, one of the default responses is randomly chosen.
 * 
 * @author     Michael Kölling and David J. Barnes
 * @version    1.0 (2011.07.31)
 */
public class Responder
{
    // Used to map key words to responses.
    private HashMap<String, String> responseMap;
    // Used to make sure variations of the same word are mapped to the same response.
    private HashMap<String, String> synonymMap;
    // Default responses to use if we don't recognise a word.
    private ArrayList<String> defaultResponses;
    private Random randomGenerator;
    // The following integer holds the index of the index of the last
    // default response that was used.
    private int lastDefaultResponseIndex;

    /**
     * Construct a Responder
     */
    public Responder()
    {
        responseMap = new HashMap<String, String>();
        synonymMap = new HashMap<String, String>();
        defaultResponses = new ArrayList<String>();
        fillResponseMap();
        fillSynonymMap();
        fillDefaultResponses();
        randomGenerator = new Random();
    }

    /**
     * Generate a response from a given set of input words.
     * 
     * @param words  A set of words entered by the user
     * @return       A string that should be displayed as the response
     */
    public String generateResponse(HashSet<String> words)
    {
        for (String word : words) {
        	// The response returned is one that first sends "word" through
        	// synonymMap, which will return a code based on which synonym
        	// of which "word" was sent in. It will then send that code into
        	// responseMap, which will return the response that is matched to
        	// the code.
            String response = responseMap.get(synonymMap.get(word));
            if(response != null) {
                return response;
            }
        }
        
        // If we get here, none of the words from the input line was recognized.
        // In this case we pick one of our default responses (what we say when
        // we cannot think of anything else to say...)
        return pickDefaultResponse();
    }
    
    /**
     * Enters all known synonyms and their associated codes
     * into our synonym map.
     */
    private void fillSynonymMap()
    {
    	synonymMap.put("crash", "001");
    	synonymMap.put("crashes", "001");
    	synonymMap.put("slow", "002");
    	synonymMap.put("slowly", "002");
    	synonymMap.put("performance", "003");
    	synonymMap.put("bug", "004");
    	synonymMap.put("buggy", "004");
    	synonymMap.put("glitch", "004");
    	synonymMap.put("windows", "005");
    	synonymMap.put("mac", "006");
    	synonymMap.put("osx", "006");
    	synonymMap.put("expensive", "007");
    	synonymMap.put("cost", "007");
    	synonymMap.put("installation", "008");
    	synonymMap.put("install", "008");
    	synonymMap.put("memory", "009");
    	synonymMap.put("ram", "009");
    	synonymMap.put("linux", "010");
    	synonymMap.put("bluej", "011");
    	synonymMap.put("hello", "012");
    	synonymMap.put("joke", "013");
    	synonymMap.put("nightshift", "014");
    }
    
    /**
     * Enters all the known codes and their associated responses
     * into our response map.
     */
    private void fillResponseMap()
    {
        responseMap.put("001", 
                        "Well, it never crashes on our system. It must have something\n" +
                        "to do with your system. Tell me more about your configuration.");
        responseMap.put("002", 
                        "I think this has to do with your hardware. Upgrading your processor\n" +
                        "should solve all performance problems. Have you got a problem with\n" +
                        "our software?");
        responseMap.put("003", 
                        "Performance was quite adequate in all our tests. Are you running\n" +
                        "any other processes in the background?");
        responseMap.put("004", 
                        "Well, you know, all software has some bugs. But our software engineers\n" +
                        "are working very hard to fix them. Can you describe the problem a bit\n" +
                        "further?");
        responseMap.put("005", 
                        "This is a known bug to do with the Windows operating system. Please\n" +
                        "report it to Microsoft. There is nothing we can do about this.");
        responseMap.put("006", 
                        "This is a known bug to do with the Mac operating system. Please\n" +
                        "report it to Apple. There is nothing we can do about this.");
        responseMap.put("007", 
                        "The cost of our product is quite competitive. Have you looked around\n" +
                        "and really compared our features?");
        responseMap.put("008", 
                        "The installation is really quite straight forward. We have tons of\n" +
                        "wizards that do all the work for you. Have you read the installation\n" +
                        "instructions?");
        responseMap.put("009", 
                        "If you read the system requirements carefully, you will see that the\n" +
                        "specified memory requirements are 1.5 giga byte. You really should\n" +
                        "upgrade your memory. Anything else you want to know?");
        responseMap.put("010", 
                        "We take Linux support very seriously. But there are some problems.\n" +
                        "Most have to do with incompatible glibc versions. Can you be a bit\n" +
                        "more precise?");
        responseMap.put("011", 
                        "Ahhh, BlueJ, yes. We tried to buy out those guys long ago, but\n" +
                        "they simply won't sell... Stubborn people they are. Nothing we can\n" +
                        "do about it, I'm afraid.");
        responseMap.put("012", 
                		"Hi! Welcome to our tech support platform. We hope you enjoy your stay!");
        responseMap.put("013", 
                		"A computer scientist goes for a night out... /n" +
                		"... /n" +
                		"Yep. That's the joke."); // Awful joke.
        responseMap.put("014", 
        				"The animatronic characters here do get a bit quirky at night."); // Bad Five Nights at Freddy's reference.
        
    }

    /**
     * Build up a list of default responses from which we can pick one
     * if we don't know what else to say.
     */
    private void fillDefaultResponses()
    {
        defaultResponses.add("That sounds odd. Could you describe that problem in more detail?");
        defaultResponses.add("No other customer has ever complained about this before. \n" +
                             "What is your system configuration?");
        defaultResponses.add("That sounds interesting. Tell me more...");
        defaultResponses.add("I need a bit more information on that.");
        defaultResponses.add("Have you checked that you do not have a dll conflict?");
        defaultResponses.add("That is explained in the manual. Have you read the manual?");
        defaultResponses.add("Your description is a bit wishy-washy. Have you got an expert\n" +
                             "there with you who could describe this more precisely?");
        defaultResponses.add("That's not a bug, it's a feature!");
        defaultResponses.add("Could you elaborate on that?");
    }

    /**
     * Randomly select and return one of the default responses.
     * @return     A random default response
     */
    private String pickDefaultResponse()
    {
    	int index; // (declared outside loop for purposes of scope).
        // Pick a random number for the index in the default response list.
        // The number will be between 0 (inclusive) and the size of the list (exclusive).
    	// The number cannot be the same number that was used last iteration of this method
    	// to avoid the same response 2x in a row. Therefore, a do while loop was written to make 
    	// sure that index does not equal lastDefaultResponseIndex at this point of the method.
    	do {
    		index = randomGenerator.nextInt(defaultResponses.size());
    	} while(index == lastDefaultResponseIndex);
        
        // Finally, the int lastDefaultResponseIndex was made to equal index so that the next time
    	// the method is called, it does not repeat the response that was returned this time the method
    	// was called.
        lastDefaultResponseIndex = index;
        
        return defaultResponses.get(index);
    }
}
