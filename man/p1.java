


import org.jetbrains.annotations.NotNull;
import org.junit.Assert;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;

/**
 * Find a maximum sum of any consequitive elements. 
 */

public class p1 {
	public p1() throws Exception {
		lbeg=System.currentTimeMillis();

		//problem_graph();
		//problem_contigious_largest_sum();
		//problem_water_over_columns();
		//problem_convertBase();
        //problem_fibonachi();
        //problem_buildBinarySearchTree();
		//problem_permutation();
		//problem_combination();
		//findDuplicateInArray();

		//checkPrimeFactors();

		//problem_reverseWords();

		//problem_findKClosestElements();

		studyBigDecimal();
		
		log(" " + ((System.currentTimeMillis()-lbeg)/(double)1000) + " seconds." );
	}

	void studyBigDecimal() {
	    //
//        printDouble(0.1);
//        printDouble( BigDecimal.valueOf(0.1).doubleValue());
//        printDouble( (new BigDecimal(0.1)).doubleValue());

        //
        header();
        double d=Math.nextAfter(0.1,Double.MIN_VALUE);
        for (int i = 1; i <=10; i++) {
            printDouble2(d);
            d=Math.nextAfter(d,Double.MAX_VALUE);
        }

    }

    void header() {
        log(Uf.padInFront("double",24, " ") +
              Uf.padInFront("BigDecimal.valueOf",30, " ")+
              Uf.padInFront("BigDecimal.binary",70, " ")
        );
    }

    void printDouble2(double d) {
        log(Uf.padInFront(d + "",24, " ") +
             Uf.padInFront((BigDecimal.valueOf(d)).toPlainString(),30, " ") +
             Uf.padInFront(Long.toBinaryString(Double.doubleToRawLongBits(d)),70, " ")
        );
    }

    void printDouble(double d) {
        log("double: " + d);
        log("Double.toString(): " + Double.toString(d) + ", " + Long.toBinaryString(Double.doubleToRawLongBits(d)));
        log("new BigDecimal: " + (new BigDecimal(d)).toPlainString());
        log("BigDecimal.valueOf: " + (BigDecimal.valueOf(d)).toPlainString());
    }

	/**
	 * Find K Closest Elements
	 * Given a sorted array, two integers k and x, find the k closest elements to x in the array. The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred
	 * Example 1:
	 * Input: [1,2,3,4,5], k=4, x=3
	 * Output: [1,2,3,4]
	 * Example 2:
	 * Input: [1,2,3,4,5], k=4, x=-1
	 * Output: [1,2,3,4]Note:
	 *
	 * The value k is positive and will always be smaller than the length of the sorted array.
	 * Length of the given array is positive and will not exceed 104
	 * Absolute value of elements in the array and x will not exceed 104
	 *
	 */
	void problem_findKClosestElements() {
		Assert.assertArrayEquals(findClosestElements(new int[]{1,2,3,4,5},3,4),new int[]{1,2,3,4});
		Assert.assertArrayEquals(findClosestElements(new int[]{1,2,3,4,5},-1,4),new int[]{1,2,3,4});
	}

	int[] findClosestElements(int in[], int x, int k) {
		List<Integer> out=new ArrayList();

		Collections.sort(out);
		int _out[]=new int[out.size()];
		for (int i = 0; i < _out.length; i++) { _out[i]=out.get(i); }
		return _out;
	}

	void problem_reverseWords() {
		Assert.assertArrayEquals(reverseWords("hello".toCharArray()), "hello".toCharArray());
		Assert.assertArrayEquals(reverseWords(" hello ".toCharArray()), " hello ".toCharArray());
		Assert.assertArrayEquals(reverseWords("hello world".toCharArray()), "world hello".toCharArray());
		Assert.assertArrayEquals(reverseWords("a b c".toCharArray()), "c b a".toCharArray());
		Assert.assertArrayEquals(reverseWords(" a b c ".toCharArray()), " c b a ".toCharArray());
	}

	/**
	 * Time complexity O(N)=3*n/2 + n*( ~3*n ) = n^2;
	 * Space complexity O(1) - in place.
	 * @param in
	 * @return
	 */
	char[] reverseWords(char in[]) {
		int n=in.length;
		// reverse string
		for (int i = 0; i < n/2; i++) {
			char tmp=in[i]; in[i]=in[n-1-i]; in[n-1-i]=tmp;
		}
		// reverse words
		int word_start=-1;
		for (int i = 0; i < n; i++) {
			if( word_start==-1 && in[i]!=' ') { word_start=i; }
			else if( word_start!=-1 && in[i]==' ') {
				int word_len=i-word_start;
				for (int k = 0; k < word_len/2; k++) {
					char tmp=in[word_start+k];in[word_start+k]=in[i-1-k];in[i-1-k]=tmp;
				}
				word_start=-1;
			}
			else if( word_start!=-1 && in[i]!=' ' && i==n-1 ) {
				int word_len=i-word_start +1;
				for (int k = 0; k < word_len/2; k++) {
					char tmp=in[word_start+k];in[word_start+k]=in[i-k];in[i-k]=tmp;
				}
			}
		}
		return in;
	}

	void checkPrimeFactors()throws Exception {

		//buildTree();

		//speedTest();

//		int seeked=33;
//		int number = buildTreeAndGetNthNumber(seeked);
//		log( seeked + "th number is " + number );

		for (int i = 1; i <1700; i++) {
			int number = buildTreeAndGetNthNumber(i);
			log( i + "th number is " + number );
		}
	}

	Set<Integer> setPrimeFactorization=new HashSet();
	/**
	 * first calculated:
	 * [1]-1, [2]-2, [3]-3, [4]-4, [5]-5, [6]-6, [7]-8, [8]-9, [9]-10, [10]-12
	 */
	int buildTreeAndGetNthNumber(int seeked) throws Exception {
		//setPrimeFactorization.clear();
		Consumer<Tracker> consumer=t->{
			if( t.number>0 && !setPrimeFactorization.contains(t.number) ) {
				setPrimeFactorization.add(t.number);
			}
		};
		if( seeked==4 ) {
			int bp=0;
		}
		// find levels one by one
		int maxLevel=19;
		for (int n = 1; n<=maxLevel; n++) {
			Tracker tracker=new Tracker();
			recurse(seeked, tracker,n,consumer);
			if( setPrimeFactorization.size()>=seeked ) { break; }
		}
		// sort numbers
		Integer found[]=setPrimeFactorization.toArray(new Integer[setPrimeFactorization.size()]);
		Arrays.sort(found);
		return found[seeked-1];
	}

	void recurse(int seeked, Tracker t, int n, Consumer<Tracker> consumer) {
		if( setPrimeFactorization.size()>=seeked ) { return; }
		consumer.accept(t);
		t.push(2); recurse(seeked, t,n,consumer);
		if( setPrimeFactorization.size()>=seeked ) { return; }
		t.push(3); recurse(seeked, t,n,consumer);
		if( setPrimeFactorization.size()>=seeked ) { return; }
		t.push(5); recurse(seeked, t,n,consumer);
		if( setPrimeFactorization.size()>=seeked ) { return; }
		if( t.factors>0 ) {
			t.pop();
		}
	}

	class Tracker {
		int elements[] = new int[32];
		int number=1;
		int factors;
		void push(int m) {
			elements[factors]=m;
			number*=m;
			factors++;
		}
		void pop() {
			factors--;
			number/=elements[factors];
		}
	}

	void speedTest() throws Exception {

		int numbers=0; int seeked=500;
		Map<Integer,Boolean> cache=new HashMap();
		Map<Integer,Boolean> runCache=new HashMap();
		for (int i = 1; i <=Integer.MAX_VALUE-1; i++) {
			//if( i==28 ) {  int bp=0;  }
			runCache.clear(); // to make sure bad combinations are cleared
			if( findPrimeFactorsFlagged(i, cache, runCache) ) {
				numbers++;
				runCache.forEach((k,v)->cache.put(k,v));
				log(numbers + "th number is " + i);

				String s=Integer.toString(i);
				if( i>1000 && "139".contains(s.substring(s.length()-1)) ) {
					throw new Exception("i=" + i);
				}
			}else{
				if( cache.size()>100*1000 ) {
					runCache.forEach((k, v) -> cache.put(k, false));
					//log("i-th: " + i + ", FALSE, found numbers: " + numbers);
				}
			}
			if( numbers==seeked ) {
				log("i: " + i + ", i-th number: " + numbers);
			}
		}
	}

	/**  */
	final static boolean findPrimeFactorsFlagged(int n, Map<Integer,Boolean> cache, Map<Integer,Boolean> runCache) {
		while( n>=1 ) {
			if(n>1) {
				if( !cache.containsKey(n) ) {
					if (n % 5 == 0) {
						runCache.put(n, true);
						n = n / 5;
					} else if (n % 3 == 0) {
						runCache.put(n, true);
						n = n / 3;
					} else if (n % 2 == 0) {
						runCache.put(n, true);
						n = n / 2;
					} else {
						runCache.put(n, false); // bad n
						return false;
					}
				}else{
					if( cache.get(n)==true ) {
						// it is in cache and known to lead to good combination
						return true;
					}else{
						return false; // this N is not divisibly by 2,3,5
					}
				}
			} else if(n==1) {
				runCache.put(n, true);
				return true;
			}
		}
		return true;
	}

	/**
	 * https://leetcode.com/problems/ugly-number-ii/description/
	 *
	 * Write a program to find the n-th ugly number.
	 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
	 * For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
	 * Note that 1 is typically treated as an ugly number, and n does not exceed 1690.
	 *
	 * https://www.mathsisfun.com/prime-factorization.html
	 * @param n
	 * @return
	 */
	public int nthUglyNumber(int n) {
		int k=0;
		int result[]=new int[300];
		for(int i=1;i<Integer.MAX_VALUE-1;i++) {
			result[0]=0;
			findPrimeFactorsIteratively(i, result);
			if( result!=null && result.length>0 && result[0]>0 ) {
				k++;
			}
			if( k==n ) { return i; }
		}
		return -1;
	}

	final static void findPrimeFactorsIteratively(int n, int[] out) {
		while( n>=1 ) {
			if(n>1) {
				if( n%5==0 ) {
					out[0]++;out[out[0]]=5;
					n=n/5;
				} else if( n%3==0 ) {
					out[0]++;out[out[0]]=3;
					n=n/3;
				}
				else if( n%2==0 ) {
					out[0]++;out[out[0]]=2;
					n=n/2;
				}else{
					out[0]=0;
					break;
				}
			} else if(n==1) {
				out[0]++; out[out[0]]=1;
				break;
			}
		}
	}

	int findPrimeFactors(int n, List<Integer> list) {
		if( n==1 ) { list.add(1); }
		else if(n>1){
			if( n%5==0 ) { list.add(5); n=findPrimeFactors( n/5, list); }
			else if( n%3==0 ) { list.add(3); n=findPrimeFactors( n/3, list); }
			else if( n%2==0 ) { list.add(2); n=findPrimeFactors( n/2, list); }
			else {
				list.clear();
			}
		}
		return n;
	}

	void findDuplicateInArray() {
		Assert.assertTrue( solutionDuplicateInArrayBrute(new int[]{2,1,2})==2 );
		Assert.assertTrue( solutionDuplicateInArrayBrute(new int[]{1,2,3,4,1})==1 );
		Assert.assertTrue( solutionDuplicateInArrayBrute(new int[]{3,4,5,2,1,5})==5 );
		Assert.assertTrue( solutionDuplicateInArrayBrute(new int[]{5,5,3,2,1,5})==5 );
	}

	/**
	 * Return duplicate array element by brute force method.
	 * @param input: integer array in [1..n+1] range, values [1..n]
	 * @return first duplicate value
	 *
	 * For this algorithm, integer range property is not important.
	 *
	 * Time complexity: O(n) - in the worst case, one loop.
	 * Space complexity: O(n) - auxiliary array of n+1.
	 */
	int solutionDuplicateInArrayBrute(int input[]) {
		int f[]=new int[input.length+1]; // values [1...n] inclusive
		for (int i = 0; i <input.length; i++) {
			f[input[i]]++;
			if( f[input[i]]>1 ) {
				System.out.println("dupe element: " + i);
				return input[i];
			}
		}
		return -1;
	}

	/**
	 * Return duplicate array element by heuristic.
	 * @param input: integer array in [1..n+1] range, values [1..n]
	 * @return first duplicate value
	 *
	 * For this algorithm, integer range property is important since it is in place.
	 *
	 * Time complexity: O(n)  - in the worst case, one loop.
	 * Space complexity: O(1) - meaning no additional space is required
	 *                          BUT original input is changed.
	 */
	int solutionDuplicateInArrayInPlace(int input[]) {
		int copy[]=Arrays.copyOf(input,input.length);
			for (int i = 0; i < copy.length; i++) {
				boolean moved=true;
				int value=copy[i];
				while( moved ) {
					moved=false;
					if( i!=value ) {
						// put in right place
						if( copy[value]!=value ) {
							// place i into right place and remember cell value
							int tmp = copy[value];
							copy[value] = i;
							value = tmp;
							moved = true;
						}else{
							// found duplicate
							return value;
						}
					}else{
						// it is in the right place
					}
				}
			}
		return -1;
	}

	void problem_combination() {
		String letters="ABC"; String combinations="A;B;C;AB;AC;BC;ABC";
		Set<String> set=new HashSet<>(); Arrays.stream(combinations.split(";")).forEach(s->{ if( s.length()>0 ) {
			s=s.replace(",",""); set.add(sort(s)); }});
		Assert.assertEquals( set, combination(letters) );
	}

	Set<String> combination(String letters) {
		Set<String> set=new HashSet<>();
		int n=letters.length();
		for (int len = 1; len <=n; len++) {
			Stack<Integer> s=new Stack();
			s.push(0);
			while( !s.isEmpty() ) {
				if (s.size() < len) {
					// build next level
					int nextLetter = getNextLetter(n, s, 0);
					if (nextLetter >= 0) {
						s.push(nextLetter); // found letter
					}
				}
				if( s.size()==len ) {
					// print permutation
					String out=sort(print(letters, s));
					if( !set.contains(out) ) {
						log(out);
						set.add(out);
					}
					// check other letters on this level
					while( !s.isEmpty() ) {
						int nextLetter=getNextLetter(n, s, s.get(s.size() - 1));
						if (nextLetter < 0) {
							// no more, go up one level and check there.
							s.pop();
						}else{
							// replace with valid and jump out of loop
							s.pop();
							s.push(nextLetter);
							break;
						}
					}
				}
			}
		}
		return set;
	}

	String sort(String s) {
		char[] ch=s.toCharArray(); Arrays.sort(ch);
		return new String(ch);
	}

	void problem_permutation() {
		String letters="A"; String permutatons="A;";
		Set<String> set=new HashSet<>(); Arrays.stream(permutatons.split(";")).forEach(s->{ if( s.length()>0 ) { set.add(s.replace(",","")); }});
		Assert.assertEquals( set, permute(letters) );
		//
		//String letters="ABC"; String permutatons="ABC;ACB;CAB;CBA;BCA;BAC;";
		//Set<String> set=new HashSet<>(); Arrays.stream(permutatons.split(";")).forEach(s->{ if( s.length()>0 ) { set.add(s.replace(",","")); }});
		//Assert.assertEquals( set, permute(letters) );
		//
//		String letters="ABCD"; String permutatons="A,B,C,D;B,A,C,D;C,A,B,D;A,C,B,D;B,C,A,D;C,B,A,D;C,B,D,A;B,C,D,A;D,C,B,A;C,D,B,A;B,D,C,A;D,B,C,A;D,A,C,B;A,D,C,B;C,D,A,B;D,C,A,B;A,C,D,B;C,A,D,B;B,A,D,C;A,B,D,C;D,B,A,C;B,D,A,C;A,D,B,C;D,A,B,C;";
//		Set<String> set=new HashSet<>(); Arrays.stream(permutatons.split(";")).forEach(s->{ if( s.length()>0 ) { set.add(s.replace(",","")); }});
//		Assert.assertEquals( set, permute(letters) );
	}

	/**
	 * iterative algorithm.
	 *
	 * it does not store anything so memory requirement is low.
	 *
	 * however for convinience this version stores found permutations
	 * so it can assert them.
	 */
    static long cntPermutation=0;
	public static Set<String> permute(String letters) {
		Set<String> set=new HashSet<>();
			int n=letters.length();
			// first letter for first level is manual
			Stack<Integer> s=new Stack();
			s.push(0);
			while( !s.isEmpty() ) {
				if( s.size()<n ) {
					// build next level
					int nextLetter = getNextLetter(n,s,0);
					if (nextLetter >= 0) {
						s.push(nextLetter); // found letter
					}
				}
				if( s.size()==n ) {
                    cntPermutation++;
					// print permutation
					String out=print(letters, s);
					//log(out);
                    if( cntPermutation%1000000==0 ) { log("processed: " + cntPermutation);}
					set.add(out);
					// go back and loop until you find one good next letter
					while( !s.isEmpty() ) {
						int nextLetter=getNextLetter(n, s, s.get(s.size() - 1));
						if (nextLetter < 0) {
							// no more, go up one level and check there.
							s.pop();
						}else{
							// replace with valid and jump out of loop
							s.pop();
							s.push(nextLetter);
							break;
						}
					}
				}
			}
		return set;
	}

	/**
	 * Return next letter to be used or none if exhausted.
	 * @param choices - letter choices [0...n-1], where n - of letters
	 * @param s - used letter so far. actual indexes to letter array are stored
	 * @param startingLetter - search for free letter starting from this one.
	 * @return
	 */
	public static int getNextLetter(int choices, Stack<Integer> s, int startingLetter) {
		Set<Integer> used= Sets.newHashSet();
		s.forEach((k)->used.add(k));
		for (int freeLetter = startingLetter; freeLetter < choices; freeLetter++) {
			if( !used.contains(freeLetter) ) {
				return freeLetter;
			}
		}
		return -1;
	}

	public static String print(String letters, Stack<Integer> s) {
		String out="";
		for (int i = 0; i < s.size(); i++) {
			out +=letters.charAt(s.get(i));
		}
		return out;
	}

	void problem_buildBinarySearchTree() {
        //solution_buildBinarySearchTree(new int[]{50,30,20,40,70,60,80});
        //int in[]=new int[]{10,7,14,20,1,5,8}; int sorted[]=Arrays.copyOf(in,in.length); Arrays.sort(sorted);
		int in[]=new int[]{10, 7, 16, 12, 5, 11, 2, 20, 1, 14}; int sorted[]=Arrays.copyOf(in,in.length); Arrays.sort(sorted);
        Assert.assertArrayEquals( solution_buildBinarySearchTree(in), sorted );
        //solution_buildBinarySearchTree(new int[]{1,5,7,8,10,14,20});
    }

    int[] solution_buildBinarySearchTree(int in[]) {
        // build tree
		Arrays.sort(in);
        BstNode root=buildBinarySearchTree(in, 0, in.length-1);

        // traverse it
		List<Integer> list=Lists.newArrayList();
        inOrderTraveral(root,list);
        log("IN-ORDER: " + list );
        int array[]=new int[list.size()];
        for (int i = 0; i < array.length; i++) { array[i]=list.get(i); }

        return array;
    }

    /**
     *  EX 1: BST input:
     *
     *       1
     *     2    3
     *  4    5
     *
     *
     *   EX 2: BST input:10,7,14,20,1,5,8
     *
     *          10
     *      7        14
     *   1     8         20
     *      5
     *
     */
    // In a binary search tree, in-order traversal retrieves data in sorted order
    void inOrderTraveral(BstNode r,List<Integer> traversal) {
	    if( r==null ) { return; }
	    inOrderTraveral(r.left,traversal);
        traversal.add(r.v);
        inOrderTraveral(r.right,traversal);
    }

    // fucken works
    BstNode buildBinarySearchTree(int in[], int fromIndex, int toIndex) {

    	if( fromIndex>toIndex) { return null; }

    	// get middle
		if( fromIndex<toIndex ) {
			int middleIndex = fromIndex + (toIndex-fromIndex)/ 2;

			// set root
			BstNode root = new BstNode(in[middleIndex]);

			// recursively add half
			root.left = buildBinarySearchTree(in, fromIndex, middleIndex - 1);
			root.right = buildBinarySearchTree(in, middleIndex +1 , toIndex);

			//
			return root;
		}else{
    		// fromIndex=toIndex
			return new BstNode(in[fromIndex]);
		}
    }

    BstNode insertNode__(BstNode node, int value) {
        Preconditions.checkArgument(value!=node.v); // don't support duplicate nodes
        if( value>node.v ) {
            // go right
            if( node.right!=null ) {
                return insertNode__(node.right, value);
            }
            else {
                node.right=new BstNode(value);
                return node.right;
            }
        }else{
            // go left
            if( node.left!=null ) {
                return insertNode__(node.left, value);
            }
            else{
                node.left=new BstNode(value);
                return node.left;
            }
        }
    }

    class BstNode {
	    int v;
        public BstNode(int v) { this.v = v;  }
        BstNode left, right;

        @Override
        public String toString() {
            String s="" + v;
            return s;
        }
    }

	void problem_fibonachi() {
        Assert.assertTrue( Arrays.equals(solution_fibonachi(10),new Long[]{ 1l , 1l , 2l , 3l , 5l , 8l , 13l , 21l , 34l , 55l , 89l , 144l}) );
        solution_fibonachi_perf(25);
    }

    Long[] solution_fibonachi(int n) {
        List<Long> list=Lists.newArrayList();
            Preconditions.checkArgument(n>2);
            for (int i = 1; i <=12; i++) {
                list.add( getFib(i) );
            }
        return list.toArray(new Long[list.size()]);
    }

    long solution_fibonachi_perf(long n) {
        ProfNano p=new ProfNano();

        p.b();
        long result=getFib(n);
        p.e();
        log("res: " + result);
        log(p.reportTimingMini());

        return result;
    }

    long getFib(long n) {
	    Preconditions.checkArgument(n>=1);

        if( n==1 || n==2 )
            return 1; // first two Fibs are hardcoded
        else
            return getFib(n-1) + getFib(n-2);
    }

	void problem_convertBase() {
		Assert.assertTrue( solution_convertBase("001",2)==1 );
		Assert.assertTrue( solution_convertBase("010",2)==2 );
		Assert.assertTrue( solution_convertBase("110",2)==6 );
		Assert.assertTrue( solution_convertBase("0F",16)==15 );
		Assert.assertTrue( solution_convertBase("1 5096",16)==86166 );
		Assert.assertTrue( solution_convertBase("1",10)==1 );
		Assert.assertTrue( solution_convertBase("758",10)==758 );
		Assert.assertTrue( solution_convertBase("FE DCBA",16)==16702650 );
	}

	/** convert to decimal number */
	long solution_convertBase(String number, int base) {
		long l=0;
		Preconditions.checkArgument( base>=2 || base<=16 );
		number=number.replace(" ","");
		for (int i = 0; i<number.length(); i++) {
			int digit=letterToDigit(number.charAt( number.length()-1-i ));
			int exp=(int)Math.pow(base,i);
			l+=digit*exp;
		}
		return l;
	}

	/** convert to decimal since you calculate number in decimal */
	int letterToDigit(char c) {
		Preconditions.checkArgument( (c>=48 && c<=(57)) || (c>=65 && c<=(70)) );
		if( c>=48 && c<=(57) ) { return c-48; }
		if( c>=65 && c<=(70) ) { return 10 + (c-65); } // this one is tricky,
		return -1;
	}

	void problem_graph() {
		//
		Assert.assertTrue( solution_DFS("0=5,0=4,0=1,1=4,1=3,3=2,3=4,2=1").equals("0,1,3,2,4,5") );

		//
		Assert.assertTrue( solution_BFS("0=5,0=4,0=1,1=4,1=3,3=2,3=4,2=1").equals("0,1,4,5,3,2") );
	}

	String solution_BFS(String edges) {
		Graph g=makeGraph(edges);
		MyGraphConsumer visitor=new MyGraphConsumer();

		_breadthFirstSearch(g,visitor);
		//breadthFirstSearch_verified(g,visitor);
		//
		StringBuilder visitedStr=new StringBuilder();
		for (int i = 0; i < visitor.visited.size(); i++) {
			visitedStr.append(visitor.visited.get(i).value);
			if( i<visitor.visited.size()-1 ) { visitedStr.append(","); }
		}
		log("Breadth First Search: " + visitedStr);
		return visitedStr.toString();
	}

	void _breadthFirstSearch(Graph g, MyGraphConsumer visitor) {
		_breadthFirstSearch(g.getNodes().next(), visitor.visited, visitor);
	}

	void _breadthFirstSearch(Node entry, List<Node> visited, Consumer<Node> visitor) {
		// create stack
		Queue<Node> s=new LinkedBlockingQueue();
		s.add(entry);
		while( !s.isEmpty() ) {
			Node node=s.remove();
			if( !visited.contains(node) ) visited.add(node);
			visitor.accept(node);
			for (Iterator<Node> t=node.getChildren();t.hasNext(); ) {
				Node n=t.next();
				if( !visited.contains(n) ) {
					visited.add(n);
					s.add(n);
				}
			}
		}
	}

	/** step thru */
	String solution_BFS_verified(String edges) {
		Graph g=makeGraph(edges);
		MyGraphConsumer visitor=new MyGraphConsumer();
		breadthFirstSearch_verified(g,visitor);
		//
		StringBuilder visitedStr=new StringBuilder();
		for (int i = 0; i < visitor.visited.size(); i++) {
			visitedStr.append(visitor.visited.get(i).value);
			if( i<g.nodes.size()-1 ) { visitedStr.append(","); }
		}
		log("Breadth First Search: " + visitedStr);
		return visitedStr.toString();
	}

	/** main entrance  */
	void breadthFirstSearch_verified(Graph g,MyGraphConsumer visitor){
		//
		Node first=g.getNodes().next();
		breadthFirstSearch_verified(first, visitor.visited, visitor);
	}

	/** recursive function */
	void breadthFirstSearch_verified(Node entry, List<Node> visited, Consumer<Node> visitor){

		Queue<Node> queue=new LinkedBlockingQueue<>();
		queue.add(entry);
        visited.add(entry);
		while( !queue.isEmpty() ) {
			// visit it
			Node node=queue.poll();
			visitor.accept(node);
			// visit children and add them to queue
			for (Iterator<Node> t=node.getChildren();t.hasNext(); ) {
				Node n=t.next();
				if( !visited.contains(n) ) {
                    visited.add(n);
				    queue.add(n);
                }
			}
		}
	}


	/** step thru */
	String solution_DFS(String edges) {
		Graph g=makeGraph(edges);
		MyGraphConsumer visitor=new MyGraphConsumer();
		//
		depthFirstSearch_2(g,visitor);
		//
		StringBuilder visitedStr=new StringBuilder();
		for (int i = 0; i < visitor.visited.size(); i++) {
			visitedStr.append(visitor.visited.get(i).value);
			if( i<g.nodes.size()-1 ) { visitedStr.append(","); }
		}
		log("Depth First Search: " + visitedStr);
		return visitedStr.toString();
	}

	void depthFirstSearch_2(Graph g, MyGraphConsumer visitor) {
		depthFirstSearch_2( g.getNodes().next(), visitor.visited, visitor );
	}

	void depthFirstSearch_2(Node node, List<Node> visited, Consumer<Node> visitor) {
		if( visited.contains(node)) { return; }
		visited.add(node);
		visitor.accept(node);
		for (Iterator<Node> iter = node.getChildren(); iter.hasNext();) {
			Node n=iter.next();
			depthFirstSearch_2(n,visited,visitor);
		}
	}


	/** step thru */
	String solution_DFS_verified(String edges) {
		Graph g=makeGraph(edges);
		MyGraphConsumer visitor=new MyGraphConsumer();
		depthFirstSearch(g,visitor);
		//
		StringBuilder visitedStr=new StringBuilder();
		for (int i = 0; i < visitor.visited.size(); i++) {
			visitedStr.append(visitor.visited.get(i).value);
			if( i<g.nodes.size()-1 ) { visitedStr.append(","); }
		}
		log("Depth First Search: " + visitedStr);
		return visitedStr.toString();
	}

	class MyGraphConsumer implements Consumer<Node> {
		List<Node> visited= Lists.newArrayList();
		@Override public void accept(Node n) {
			//log(n);
		}
	}

	/** main entrance  */
	void depthFirstSearch(Graph g,MyGraphConsumer visitor){
		depthFirstSearch(g.getNodes().next(), visitor.visited, visitor);
	}

	/** recursive function */
	void depthFirstSearch(Node node, List<Node> visited, Consumer<Node> visitor){
		// children of node have been visited, mark this node visited
		visited.add(node);
		visitor.accept(node);

		// visit children
		for (Iterator<Node> t=node.getChildren();t.hasNext(); ) {
			Node n=t.next();
			if( !visited.contains(n)) {
				depthFirstSearch(n,visited,visitor);
			}
		}
	}

	/** 1=2,2=3,2=5,... */
	Graph makeGraph(String edges) {
		Graph g=new Graph();
		String elements[]=edges.split("\\s*,\\s*");
		Arrays.stream(elements).forEach((s)->{
			int a=Integer.parseInt(s.split("=")[0].trim());
			int b=Integer.parseInt(s.split("=")[1].trim());
			Node node=getOrMake(g,a);
			Preconditions.checkArgument( !containsChild(node,b) );
			node._children.add( getOrMake(g,b) );
		});
		return g;
	}

	Node getOrMake(Graph g,int a) {
		for (Node n: g.nodes) {
			if( n.value==a) { return n; }
		}
		Node n=new Node(a);
		g.nodes.add(n);
		return n;
	}

	boolean containsChild(Node n, int value) {
		for (Node child: n._children) {
			if( child.value==value) { return true; }
		}
		return false;
	}

	class Graph {
		Set<Node> nodes=new TreeSet<>();

		public Iterator<Node> getNodes() {
			return nodes.iterator();
		}

		public Node get(int value) {
			for(Node n:nodes) {
				if( n.value==value) { return n; }
			}
			return null;
		}

		@Override
		public String toString() {
			return "Graph(" + nodes.size() + "){" +
					"nodes=" + nodes +
					'}';
		}
	}

	class Node implements Comparable<Node> {
		int value;
		private Set<Node> _children=new TreeSet(Comparator.naturalOrder());

		public Node(int value) {
			this.value = value;
		}

		public Iterator getChildren() {
			return _children.iterator();
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (!(o instanceof Node)) return false;
			Node node = (Node) o;
			return value == node.value;
		}
		@Override
		public int hashCode() {
			return Objects.hash(value);
		}

		@Override
		public String toString() {
			return "Node{" +
					"value=" + value +
					", children.size()=" + _children.size() +
					'}';
		}

		@Override
		public int compareTo(@NotNull Node o) {
			return Integer.compare(value,o.value);
		}
	}


	/**
	 * https://www.geeksforgeeks.org/trapping-rain-water/
	 *
	 * Input: arr[] = [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]
	 * Output: 6
	 *        |
	 *    |   || |
	 * _|_||_||||||
	 * Trap "1 unit" between first 1 and 2, "4 units" between
	 * first 2 and 3 and "1 unit" between second last 1 and last 2
	 */
	void problem_water_over_columns() throws Exception {
		Assert.assertTrue( solution_water_over_columns(new int[]{3,2,1})==0 );
		Assert.assertTrue( solution_water_over_columns(new int[]{3,0,1})==1 );
		Assert.assertTrue( solution_water_over_columns(new int[]{3,0,2,0,1})==3 );
		Assert.assertTrue( solution_water_over_columns(new int[]{3,0,0,2,0,0})==4 );
		Assert.assertTrue( solution_water_over_columns(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1})==6 );
		Assert.assertTrue( solution_water_over_columns(new int[]{3,0,1,0,2,0,0,1})==7 );
	}

	/** resolving it. */
	int solution_water_over_columns(int d[]) throws Exception {
		int sum=0;
		int i = 0;
		while(i < d.length) {
			int right=findRightWall(d,i);
			if( right>0) {
				// found right wall
				int v=volume(d,i,right);
				sum +=v;
				if( v>0 ) i = right;
				else i++;
			}else {
				i++; // not found wall
			}
		}
		log("volume: " + sum );
		return sum;
	}

	/** return index of right wall or none */
	int findRightWall(int d[], int from) {
		int out=-1;
			if( from==d.length-1) { return -1; }
		    int maxValueIndex=from+1;
			for (int i = from+1; i <d.length ; i++) {
				if( d[i]>d[maxValueIndex] ) { maxValueIndex=i; }
				if( d[i]>=d[from] ) { return i; }
			}
		return maxValueIndex;
	}

	/** */
	int solution_water_over_columns_verified(int d[]) throws Exception {
		int sum=0;
		int i = 0;
		while(i < d.length) {
			int right=findRightWall_verified(d,i);
			if( right>0) {
				// found right wall
				int v=volume(d,i,right);
				sum +=v;
				//log("volume: " + v + ", [" + i + "]=" + d[i] + ", [" + right + "]=" + d[right]);
				if( v>0 ) i = right;
				else i++;
			}else {
				i++; // not found wall
			}
		}
		log("volume: " + sum );
		return sum;
	}

	/** l,r - inclusive array indexes */
	int volume(int d[], int l, int r) {
		int s=0; int minWall=Math.min(d[l],d[r]);
		for (int i = l+1; i <=r-1; i++) {
		  // if is unnecessary if you know for sure [l,r] are a bowl.
		  if( d[i]<minWall) s+=minWall-d[i];
		}
		return s;
	}

	/** find wall is most important function */
	int findRightWall_verified(int d[], int leftWallIndex) {
		for(int i=leftWallIndex+1;i<d.length;i++) {
			if( i<d.length-1) {
				// there is at least one more i ahead
				if( d[i]>=d[leftWallIndex] && d[i+1]<d[i] ) return i;
			}
		}
		return d.length-1;
	}

	/**
	 * https://www.geeksforgeeks.org/largest-sum-contiguous-subarray/
	 * @throws Exception
	 */
	void problem_contigious_largest_sum() throws Exception {
		//int input[]=new int[]{-2, -3, 4, -1, -2, 1, 5, -3}; int expected=7;
		//int input[]=new int[]{3,2,1}; int expected=6;
		int input[]=new int[]{3,-3,3,-3,3}; int expected=3;
		Assert.assertTrue( solution_contigious_largest_sum(input)==expected );

	}

	/** */
	int solution_contigious_largest_sum(int d[]) throws Exception {
		int r=d[0], max=d[0], start=0, end=0;
			for (int i=1;i<d.length;i++) {
			 int e=d[i];
			  if( e>(r+e) ) {
			  	r=e; // reset
				start=i;
			  }else{
				r+=e; // continue running sum
			  }
			  if(max<r){ max=r; end=i; }
			}
			log("max_sum: " + max + ", elements: " + (end-start+1) + ", start_index: " + start + ", end_index: " + end);
		return max;
	}

	public static void main(String[] args) {
		try {
			new p1();
		}catch(Exception e){ e.printStackTrace(); }
	}

	public static void log(Object s){ System.out.println(s); }
	long lbeg;
}
