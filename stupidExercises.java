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


// Look for WRITE YOUR CODE to write your code
import java.util.Scanner;

public class Exercise13_17 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter the first complex number: ");
    double a = input.nextDouble();
    double b = input.nextDouble();
    Complex c1 = new Complex(a, b);

    System.out.print("Enter the second complex number: ");
    double c = input.nextDouble();
    double d = input.nextDouble();
    Complex c2 = new Complex(c, d);

    System.out.println("(" + c1 + ")" + " + " + "(" + c2 + ")" + " = " + c1.add(c2));
    System.out.println("(" + c1 + ")" + " - " + "(" + c2 + ")" + " = " + c1.subtract(c2));
    System.out.println("(" + c1 + ")" + " * " + "(" + c2 + ")" + " = " + c1.multiply(c2));
    System.out.println("(" + c1 + ")" + " / " + "(" + c2 + ")" + " = " + c1.divide(c2));
    System.out.println("|" + c1 + "| = " + c1.abs());
    
    try {
        Complex c3 = (Complex)c1.clone();
        System.out.println(c1 == c3);
    System.out.println(c3.getRealPart());
    System.out.println(c3.getImaginaryPart());
    Complex c4 = new Complex(4, -0.5);
    Complex[] list = {c1, c2, c3, c4};
    java.util.Arrays.sort(list);
    System.out.println(java.util.Arrays.toString(list));
    } catch (CloneNotSupportedException e) {
        e.printStackTrace();
    } 
    
    
  }
}

class Complex implements Cloneable, Comparable<Complex>{
  private double a;
  private double b;

  
	// Constructors
	/** Creates a complex object for number 0 */
	public Complex() {
		this(0, 0);
	}

	/** Create a complex object with 0 for b */
	public Complex(double a) {
		this(a, 0);
	}

	/** Creates a complex object with specified a and b */
	public Complex(double a, double b) {
		this.a = a;
		this.b = b;
	}

	// Methods
	/** Return real part of complex number */
	public double getRealPart() {
		return a;
	}

	/** Return imaginary part of complex number */
	public double getImaginaryPart() {
		return b;
	}

	/** Add a complex number to this complex number */
	public Complex add(Complex secondComplex) {
		return new Complex(a + secondComplex.a, 
			b + secondComplex.b); 
	}

	/** Subtract a complex number from this complex number */
	public Complex subtract(Complex secondComplex) {
		return new Complex(a - secondComplex.a,
			b - secondComplex.b);
	}

	/** Multiply a complex number by this complex number */
	public Complex multiply(Complex secondComplex) {
		return new Complex(a * secondComplex.a - b * secondComplex.b,
			b * secondComplex.a + a * secondComplex.b);
	}

	/** Divide a complex number by this complex number */
	public Complex divide(Complex secondComplex) {
		return new Complex((a * secondComplex.a + b * secondComplex.b) /
			(Math.pow(secondComplex.a, 2) + Math.pow(secondComplex.b, 2)),
			(b * secondComplex.a - a * secondComplex.b) /
			(Math.pow(secondComplex.a, 2) +  Math.pow(secondComplex.b, 2)));
	}

	/** Returns the absolute value of this complex number */
	public double abs() {
		return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
	}

	@Override 
	public Complex clone() throws CloneNotSupportedException {
		return (Complex)super.clone();
	}

	@Override
	public String toString() {
		return b == 0 ? a + "" : "(" + a + " + " + b + "i)";
	}
	@Override
	public int compareTo(Complex o) {
		return 0;
	}
	
}

/////////////////////////////////////////////////////////////////////////////

import java.math.BigInteger;
import java.util.Scanner;

import java.math.*;

public class Exercise13_19 {
	/** Main method */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		// Prompt the user to enter a decimal number
		System.out.print("Enter a decimal number: ");
		String[] decimal = input.nextLine().split("[.]");

		// Create a Rational object of the integer part of the decimal number
		Rational r1 = new Rational(new BigInteger(decimal[0]), BigInteger.ONE);

		// Create a Rational object of the fractional part of the decimal number
		Rational r2 = new Rational(new BigInteger(decimal[1]), new BigInteger(
			String.valueOf((int)Math.pow(10, decimal[1].length()))));

		// Display fraction number
		System.out.println("The fraction number is " + 
			(decimal[0].charAt(0) == '-' ? (r1).subtract(r2) : (r1).add(r2)));
	}
}


class Rational extends Number implements Comparable {
	// Data fields for numerator and denominator
	private BigInteger[] r = new BigInteger[2];

	/** Construct a ratinoal with default properties */
	public Rational() {
		this(BigInteger.ZERO, BigInteger.ONE);
	}

	/** Construct a rational with specifiec numerator and denominator */
	public Rational(BigInteger numerator, BigInteger denominator) {
		BigInteger gcd = gcd(numerator, denominator);
		r[0] = (denominator.compareTo(BigInteger.ZERO) > 0
			? BigInteger.ONE : new BigInteger("-1")).multiply(
			numerator.divide(gcd));
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
	public Rational add(Rational secondRational) {
		BigInteger n = (r[0].multiply(secondRational.getDenominator())).add(
			r[1].multiply(secondRational.getNumerator()));
		BigInteger d = r[1].multiply(secondRational.getDenominator());
		return new Rational(n, d);
	}

	/** Subtract a rational number from this rational */
	public Rational subtract(Rational secondRational) {
		BigInteger n = (r[0].multiply(secondRational.getDenominator())).subtract(
			r[1].multiply(secondRational.getNumerator()));
		BigInteger d = r[1].multiply(secondRational.getDenominator());
		return new Rational(n, d);
	}

	/** Mulitply a rational number by this rational */
	public Rational multiply(Rational secondRational) {
		BigInteger n = r[0].multiply(secondRational.getNumerator());
		BigInteger d = r[1].multiply(secondRational.getDenominator());
		return new Rational(n, d);
	}

	/** Divide a rational number by this rational */
	public Rational divide(Rational secondRational) {
		BigInteger n = r[0].multiply(secondRational.getDenominator());
		BigInteger d = r[1].multiply(secondRational.getNumerator());
		return new Rational(n, d);
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
		if (((this.subtract((Rational)(other))).getNumerator()).compareTo(
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
	public int compareTo(Object o) {
		BigInteger n = this.subtract((Rational)o).getNumerator();
		if (n.compareTo(BigInteger.ZERO) > 0)
			return 1;
		else if (n.compareTo(BigInteger.ZERO) < 0)
			return -1;
		else
			return 0;
	}
}


/////////////////////////////////////////////////////////////////////////////////

import java.util.Scanner;

public class Exercise13_21 {
	/** Main method */
	public static void main(String[] args) {
		// Create a Scanner Object
		Scanner input = new Scanner(System.in);

		// Prompt the user to enter a, b and c and 
		// create three Rational objects form input
		System.out.print("Enter a, b, c: ");
		Rational a = new Rational(input.nextLong(), 1);
		Rational b = new Rational(input.nextLong(), 1);
		Rational c = new Rational(input.nextLong(), 1);

		// Compute h and k
		// h = -b / 2a
		Rational h = new Rational(-b.getNumerator(), 2 * a.getNumerator());

		// k = f(h) = a * h^2 + b * h + c
		Rational k = (a.multiply(h.multiply(h)).add(b.multiply(h))).add(c);

		// Display results
		System.out.println("h is " + h + " k is " + k);
	}
}


class Rational extends Number implements Comparable<Rational> {
	// Data fields for numerator and denominator
	private long[] r = new long[2];

	/** Construct a ratinoal with default properties */
	public Rational() {
		this(0, 1);
	}

	/** Construct a rational with specifiec numerator and denominator */
	public Rational(long numerator, long denominator) {
		long gcd = gcd(numerator, denominator);
		r[0] = ((denominator > 0) ? 1 : -1) * numerator / gcd;
		r[1] = Math.abs(denominator) / gcd;
	}

	/** Find GCD of two numbers */
	private static long gcd(long n, long d) {
		long n1 = Math.abs(n);
		long n2 = Math.abs(d);
		int gcd = 1;

		for (int k = 1; k <= n1 && k <= n2; k++) {
			if (n1 % k == 0 && n2 % k == 0)
				gcd = k;
		}

		return gcd;
	}

	/** Return numerator */
	public long getNumerator() {
		return r[0];
	}

	/** Return denominator */
	public long getDenominator() {
		return r[1];
	}

	/** Add a rational number to this rational */
	public Rational add(Rational secondRational) {
		long n = r[0] * secondRational.getDenominator() +
			r[1] * secondRational.getNumerator();
		long d = r[1] * secondRational.getDenominator();
		return new Rational(n, d);
	}

	/** Subtract a rational number from this rational */
	public Rational subtract(Rational secondRational) {
		long n = r[0] * secondRational.getDenominator()
			- r[1] * secondRational.getNumerator();
		long d = r[1] * secondRational.getDenominator();
		return new Rational(n, d);
	}

	/** Mulitply a rational number by this rational */
	public Rational multiply(Rational secondRational) {
		long n = r[0] * secondRational.getNumerator();
		long d = r[1] * secondRational.getDenominator();
		return new Rational(n, d);
	}

	/** Divide a rational number by this rational */
	public Rational divide(Rational secondRational) {
		long n = r[0] * secondRational.getDenominator();
		long d = r[1] * secondRational.getNumerator();
		return new Rational(n, d);
	}

	@Override
	public String toString() {
		if (r[1] == 1)
			return r[0] + "";
		else
			return r[0] + "/" + r[1];
	}

	@Override // Override the equals method in the Object class
	public boolean equals(Object other) {
		if ((this.subtract((Rational)(other))).getNumerator() == 0)
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
		return r[0] * 1.0 / r[1];
	}

	@Override // Implement the abstract longValue method in Number
	public long longValue() {
		return (long)doubleValue();
	}

	@Override // Implement the compareTo method in Coparable
	public int compareTo(Rational o) {
		if (this.subtract(o).getNumerator() > 0)
			return 1;
		else if (this.subtract(o).getNumerator() < 0)
			return -1;
		else
			return 0;
	}
}


/////////////////////////////////////////////////////////////////////////////