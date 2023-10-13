import java.util.Scanner;  // Import the Scanner class

public class Exercise19_05 {

    public static void main(String[] args) {

        Integer[] thang = new Integer[10];
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter 10 integers: ");
        for (int i = 0; i < thang.length; i++) {
            thang[i] = Integer.parseInt(myObj.next());  // Read user input
        }

        System.out.println("The max number is " + max(thang));
    }

    public static <E extends Comparable<E>> E max(E[] list) {

        E max = list[0];
        for (int i = 1; i < list.length; i++) {
            E element = list[i];
            if (element.compareTo(max) > 0) {
                max = element;
            }
        }

        return max;
    }
}

///////////////////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;
import java.util.Scanner;  // Import the Scanner class
public class Exercise19_09 {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter 10 integers: ");
        
        for (int i = 0; i < 10; i++) {
            list.add(Integer.parseInt(myObj.next())); // Read user input
        }
        list = sort(list);
        System.out.println("The sorted numbers are");
        for (int i = 0; i < 10; i++) {
            System.out.print(" " + list.get(i));
        }

    }

    public static <E extends Comparable<E>> ArrayList<E> sort(ArrayList<E> list) {


        for (int i = 0; i < list.size() - 1; i++) {
            E currentMin = list.get(i);
            int min = i;
            for (int j = i + 1; j < list.size(); j++) {

                if (list.get(j).compareTo(currentMin) < 0) {
                    currentMin = list.get(j);
                    min = j;
                }
            }

            if (min != i) {
                list.set(min, list.get(i));
                list.set(i, currentMin);
            }
        }
     return list;
    }
    
}




/////////////////////////////////////////////////////////////////////////////////////





// Look for WRITE YOUR CODE to write your code
import java.math.*;
import java.util.Scanner;

public class Exercise13_15 {
  public static void main(String[] args) {
    // Prompt the user to enter two RationalUsingBigIntegernumbers
    Scanner input = new Scanner(System.in);
    System.out.print("Enter rational r1 with numerator and denominator seperated by a space: ");
    String n1 = input.next();
    String d1 = input.next();

    System.out.print("Enter rational r2 with numerator and denominator seperated by a space: ");
    String n2 = input.next();
    String d2 = input.next();

    RationalUsingBigInteger r1 = new RationalUsingBigInteger(
      new BigInteger(n1), new BigInteger(d1));
    RationalUsingBigInteger r2 = new RationalUsingBigInteger(
      new BigInteger(n2), new BigInteger(d2));

    // Display results
    System.out.println(r1 + " + " + r2 + " = " + r1.add(r2));
    System.out.println(r1 + " - " + r2 + " = " + r1.subtract(r2));
    System.out.println(r1 + " * " + r2 + " = " + r1.multiply(r2));
    System.out.println(r1 + " / " + r2 + " = " + r1.divide(r2));
    System.out.println(r2 + " is " + r2.doubleValue());
  }
}

// Name the revised Rational class RationalUsingBigInteger 
class RationalUsingBigInteger extends Number 
    implements Comparable<RationalUsingBigInteger> {
  // Data fields for numerator and denominator
  private BigInteger numerator = BigInteger.ZERO;
  private BigInteger denominator = BigInteger.ONE;

  // WRITE YOUR CODE
  // Data fields for numerator and denominator
	private BigInteger[] r = new BigInteger[2];

	/** Construct a ratinoal with default properties */
	public RationalUsingBigInteger() {
		this(new BigInteger("0"), new BigInteger("1"));
	}

	/** Construct a rational with specifiec numerator and denominator */
	public RationalUsingBigInteger(BigInteger numerator, BigInteger denominator) {
		BigInteger gcd = gcd(numerator, denominator);
		r[0] = (denominator.compareTo(BigInteger.ZERO) > 0
			? BigInteger.ONE : 
			new BigInteger("-1")).multiply(numerator.divide(gcd));
		r[1] = denominator.divide(gcd);
	}

	/** Find GCD of two numbers */
	private static BigInteger gcd(BigInteger n, BigInteger d) {
		BigInteger n1 = n;
		BigInteger n2 = d;
		BigInteger gcd = BigInteger.ONE;

		for (BigInteger k = BigInteger.ONE; 
			k.compareTo(n1) <= 0 && k.compareTo(n2) <= 0; 
			k = k.add(BigInteger.ONE)) {
			if (n1.remainder(k).compareTo(BigInteger.ZERO) == 0 && 
				n2.remainder(k).compareTo(BigInteger.ZERO) == 0)
				gcd = k;
		}

		return gcd;
	}

	/** Return numerator */
	public BigInteger getNumerator() {
		return r[0];
	}

	/** Return denominator */
	public BigInteger getDenominator() {
		return r[1];
	}

	/** Add a rational number to this rational */
	public RationalUsingBigInteger add(RationalUsingBigInteger secondRational) {
		BigInteger n = (r[0].multiply(secondRational.getDenominator())).add(
			r[1].multiply(secondRational.getNumerator()));
		BigInteger d = r[1].multiply(secondRational.getDenominator());
		return new RationalUsingBigInteger(n, d);
	}

	/** Subtract a RationalUsingBigIntegernumber from this RationalUsingBigInteger*/
	public RationalUsingBigInteger subtract(RationalUsingBigInteger secondRational) {
		BigInteger n = (r[0].multiply(secondRational.getDenominator())).subtract(
			r[1].multiply(secondRational.getNumerator()));
		BigInteger d = r[1].multiply(secondRational.getDenominator());
		return new RationalUsingBigInteger(n, d);
	}

	/** Mulitply a RationalUsingBigIntegernumber by this RationalUsingBigInteger*/
	public RationalUsingBigInteger multiply(RationalUsingBigInteger secondRational) {
		BigInteger n = r[0].multiply(secondRational.getNumerator());
		BigInteger d = r[1].multiply(secondRational.getDenominator());
		return new RationalUsingBigInteger(n, d);
	}

	/** Divide a RationalUsingBigIntegernumber by this RationalUsingBigInteger*/
	public RationalUsingBigInteger divide(RationalUsingBigInteger secondRational) {
		BigInteger n = r[0].multiply(secondRational.getDenominator());
		BigInteger d = r[1].multiply(secondRational.getNumerator());
		return new RationalUsingBigInteger(n, d);
	}

	@Override
	public String toString() {
		if (r[1].compareTo(BigInteger.ONE) == 0)
			return r[0] + "";
		else
			return r[0] + "/" + r[1];
	}

	@Override // Override the equals method in the Object class
	public boolean equals(Object other) {
		if (((this.subtract((RationalUsingBigInteger)(other))).getNumerator()).compareTo(
			BigInteger.ZERO) == 0)
			return true;
		else
			return false;
	}

	@Override // Implement the abstract intValue method in Number
	public int intValue() {
		return (int)doubleValue();
	}

	@Override // Implement the abstract floatValue method in Number
	public float floatValue() {
		return (float)doubleValue();
	}

	@Override // Implement the doubleValue method in Number
	public double doubleValue() {
		return this.getNumerator().doubleValue() / 
			this.getDenominator().doubleValue();
	}

	@Override // Implement the abstract longValue method in Number
	public long longValue() {
		return (long)doubleValue();
	}

	@Override
	public int compareTo(RationalUsingBigInteger o) {
		BigInteger zero = BigInteger.ZERO;
		BigInteger n = this.subtract((RationalUsingBigInteger)o).getNumerator();
		if (n.compareTo(zero) > 0)
			return 1;
		else if (n.compareTo(zero) < 0)
			return -1;
		else
			return 0;
	}
}


/////////////////////////////////////////////////////////////////////////////////


