import java.io.*;
import java.util.*;

public class prob1{
    // https://codeforces.com/problemset/problem/1904/A
    static class FastReader {
        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        private int read() throws IOException {
            if (ptr >= len) {
                ptr = 0;
                len = in.read(buffer);
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c, sign = 1, x = 0;
            while ((c = read()) <= ' ') if (c == -1) return -1;
            if (c == '-') { sign = -1; c = read(); }
            while (c > ' ') {
                x = x * 10 + (c - '0');
                c = read();
            }
            return x * sign;
        }

        long nextLong() throws IOException {
            int c, sign = 1;
            long x = 0;
            while ((c = read()) <= ' ') if (c == -1) return -1;
            if (c == '-') { sign = -1; c = read(); }
            while (c > ' ') {
                x = x * 10 + (c - '0');
                c = read();
            }
            return x * sign;
        }

        String next() throws IOException {
            StringBuilder sb = new StringBuilder();
            int c;
            while ((c = read()) <= ' ') if (c == -1) return "";
            while (c > ' ') {
                sb.append((char)c);
                c = read();
            }
            return sb.toString();
        }
    }

    static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

    static final int INF = (int)1e9;
    static final long LINF = (long)1e18;

    static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    static long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }

    static long modPow(long a, long b, long mod) {
        long res = 1;
        a %= mod;
        while (b > 0) {
            if ((b & 1) == 1) res = (res * a) % mod;
            a = (a * a) % mod;
            b >>= 1;
        }
        return res;
    }

    static void shuffle(int[] a) {
        Random r = new Random();
        for (int i = 0; i < a.length; i++) {
            int j = r.nextInt(i + 1);
            int tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }
    }

    static class DSU {
        int[] parent, rank;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }

        void union(int a, int b) {
            a = find(a);
            b = find(b);
            if (a != b) {
                if (rank[a] < rank[b]) {
                    int t = a; a = b; b = t;
                }
                parent[b] = a;
                if (rank[a] == rank[b]) rank[a]++;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        int t = fr.nextInt(); // test cases

        while (t-- > 0) {
            solve(fr);
        }

        out.flush();
    }

    static void solve(FastReader fr) throws Exception {
        int a = fr.nextInt();
        int b = fr.nextInt();
        int kx = fr.nextInt();
        int ky = fr.nextInt();
        int qx = fr.nextInt();
        int qy = fr.nextInt();
        HashSet<String> st = new HashSet<>();
        HashSet<String> st2 = new HashSet<>();
        
        int[] dx = {a,a,-a,-a,b,b,-b,-b};
        int[] dy = {b,-b,b,-b,a,-a,a,-a};
        for(int i = 0; i < 8; i++){
            int m1x = kx + dx[i];
            int m1y = ky + dy[i];

            String m1 = m1x + " " + m1y;
            st.add(m1);
        }
        // out.println(st);
        int ct = 0;
        for(int i = 0; i < 8; i++){
            int m1x = qx + dx[i];
            int m1y = qy + dy[i];


            String m1 = m1x + " " + m1y;
            st2.add(m1);
        }
        for(String s : st){
            if(st2.contains(s)) ct++;
        }
        // out.println(st);
        out.println(ct);
    }
}