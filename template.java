import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;

class Pair implements Comparable<Pair>
{
    private long first,second;

    public Pair(final long first, final long second) 
    {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() 
    {
        return "Pair [first=" + first + ", second=" + second + "]";
    }

    public long getFirst() 
    {
        return first;
    }

    public void setFirst(final long first) 
    {
        this.first = first;
    }

    public long getSecond() 
    {
        return second;
    }

    public void setSecond(final long second) 
    {
        this.second = second;
    }
    
    public int compareTo(Pair p)        // dictionary order
    {
        if(this.first==p.first)
        {
            return this.second>p.second?1:-1;
        }
        return this.first>p.first?1:-1;
    }

}

class Compare implements Comparator<Pair>   // dictionary order
{
    public int compare(Pair p1,Pair p2)
    {
        if(p1.getFirst()==p2.getFirst())
        {
            return p1.getSecond()>p2.getSecond()?1:-1;
        }
        return p1.getFirst()>p2.getFirst()?1:-1;
    }
}

public class Main 
{
    // https://www.geeksforgeeks.org/fast-io-in-java-in-competitive-programming/?ref=lbp
    static class Reader
    {
        final private int BUFFER_SIZE=(1<<16);
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer,bytesRead;
        public Reader()
        {
            din=new DataInputStream(System.in);
            buffer=new byte[BUFFER_SIZE];
            bufferPointer=bytesRead=0;
        }
        public Reader(String file_name) throws IOException
        {
            din=new DataInputStream(new FileInputStream(file_name));
            buffer=new byte[BUFFER_SIZE];
            bufferPointer=bytesRead=0;
        }
        public String nextLine() throws IOException
        {
            byte[] buf=new byte[64]; // line length
            int cnt=0,c;
            while((c=read())!=-1)
            {
                if(c=='\n')
                {
                    if(cnt!=0)
                    {
                        break;
                    }
                    else
                    {
                        continue;
                    }
                }
                buf[cnt++]=(byte)c;
            }
            return new String(buf,0,cnt);
        }
        public String next() throws IOException
        {
            byte[] buf=new byte[64]; // line length
            int cnt=0,c;
            while((c=read())!=-1)
            {
                if(c=='\n'||c==' ')
                {
                    if(cnt!=0)
                    {
                        break;
                    }
                    else
                    {
                        continue;
                    }
                }
                buf[cnt++]=(byte)c;
            }
            return new String(buf,0,cnt);
        }
        public int nextInt() throws IOException
        {
            int ret=0;
            byte c=read();
            while(c<=' ')
            {
                c=read();
            }
            boolean neg=(c=='-');
            if(neg)
            {
                c=read();
            }
            do
            {
                ret=ret*10+c-'0';
            }while((c=read())>='0'&&c<='9');
            if(neg)
            {
                return -ret;
            }
            return ret;
        }
        public long nextLong() throws IOException
        {
            long ret=0;
            byte c=read();
            while(c<=' ')
            {
                c=read();
            } 
            boolean neg=(c=='-');
            if(neg)
            {
                c=read();
            } 
            do
            {
                ret=ret*10+c-'0';
            }while((c=read())>='0'&&c<='9');
            if(neg)
            {
                return -ret;
            } 
            return ret;
        }
        public double nextDouble() throws IOException
        { 
            double ret=0,div=1;
            byte c=read();
            while(c<=' ')
            {
                c=read();
            }
            boolean neg=(c=='-');
            if(neg)
            {
                c=read();
            }
            do
            {
                ret=ret*10+c-'0';
            }while((c=read())>='0'&&c<='9');
            if(c=='.')
            {
                while((c=read())>='0'&&c<='9')
                {
                    ret+=(c-'0')/(div*=10);
                }
            }
            if(neg)
            {
                return -ret;
            }
            return ret;
        } 
        private void fillBuffer() throws IOException
        { 
            bytesRead=din.read(buffer,bufferPointer=0,BUFFER_SIZE);
            if(bytesRead==-1)
            {
                buffer[0]=-1; 
            }
        }
        private byte read() throws IOException
        {
            if(bufferPointer==bytesRead)
            {
                fillBuffer();
            }
            return buffer[bufferPointer++];
        }
        public void close() throws IOException
        {
            if(din==null)
            {
                return;
            }
            din.close();
        }
    }

    final static long mod=1000000007;

    static long add(long a,long b)
    {
        return (a%mod+b%mod)%mod;
    }

    static long sub(long a,long b)
    {
        return (a%mod-b%mod+mod)%mod;
    }

    static long mul(long a,long b)
    {
        return (a%mod*b%mod)%mod;
    }

    static long[] fact=new long[2000001];
    static boolean[] isPrime=new boolean[2000001];
    static long dp[][]=new long[10001][10001];

    static void fillFact()
    {
        fact[0]=1;
        for(int i=1;i<fact.length;++i)
        {
            fact[i]=mul(i,fact[i-1]);
        }
    }
    
    static void sieve()
    {
        for(int i=0;i<isPrime.length;++i)
        {
            isPrime[i]=true;
        }
        isPrime[0]=isPrime[1]=false;
        isPrime[2]=true;
        for(int p=2;p*p<isPrime.length;p++)
        {
            if(isPrime[p]==true)
            {
                for(int i=p*p;i<isPrime.length;i+=p)
                {
                    isPrime[i]=false;
                }
            }
        }
    }
    
    static long gcd(long a,long b)
    {
        return ((b==0)?a:gcd(b,a%b));
    }

    static long power(long a,long b)        // Use when MOD is of order 10^9 or less
    {
        long ans=1;
        a=a%mod;
        while(b>0)
        {
            if((b&1)!=0)
            {
                ans=mul(ans,a);
            }
            a=mul(a,a);     
            b>>=1;
        }
        return ans%mod;
    }

    static long nCrModPFermat(long n,long r)         // Ensure that fillFactMod() is called before this function is used
    {
        if(n<r)
        {
            return 0;
        }
        if(r==0||r==n)
        {
            return 1;
        }
        return mul(mul(fact[(int)n],power(fact[(int)r],mod-2)),power(fact[(int)(n-r)],mod-2));
    }
    public static void main(String[] args) throws IOException
    {
        Reader cin=new Reader();
        PrintWriter cout=new PrintWriter(System.out);
        // fillFact();
        // sieve();
        long Test_Case=1;
        Test_Case=cin.nextLong();
        for(long TestCase=1;TestCase<=Test_Case;++TestCase)
        {
            
        }
        cin.close();
        cout.close();
    }
}
