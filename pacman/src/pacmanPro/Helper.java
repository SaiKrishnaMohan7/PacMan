package pacmanPro;

import java.util.Random;

public class Helper {

	public static Boolean gnerateRandomNumber()
	{

		int randomNum = new Random().nextInt((2 - 0) + 1);

		if(randomNum >= 1)
			return true;
		else
			return false;
	}

	public static int generateRandomNumber(int size)
	{
		// for generating n different numbers enter n -1
		return new Random().nextInt((size - 0) + 1);
	}

}
