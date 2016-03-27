/**
 * Created by vaishalibehl on 3/27/16.
 */
    public class WeightedQuickUnionPathCompressionUF{
        int count;
        int[] parent;
        int[] size;
        public WeightedQuickUnionPathCompressionUF(int N){
            count = N;
            parent = new int[N];
            size = new int[N];
            for(int i = 0; i < N; i++){
                parent[i] = i;
                size[i] = 1;
            }
        }
        public void union(int p, int q){
            validate(p);
            validate(q);
            int rootP = root(p),
                    rootQ = root(q);
            if(size[rootP] >= size[rootQ]){
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
            }
            else{
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
            }
            count--;
        }
        public boolean connected(int p, int q){
            return root(p)==root(q);
        }
        public int count(){
            return count;
        }
        private int root(int p){
            while(p!=parent[p]){
                parent[p] = parent[parent[p]];
                p = parent[p];
            }
            return p;
        }
        private void validate(int p){
            int N = parent.length;
            if(p < 0 || p >= N)
                throw new IllegalArgumentException("index "+p+" should be between 0 and "+N);
        }
        public static void main(String[] args){
            Integer N = StdIn.readInt();
            WeightedQuickUnionPathCompressionUF uf = new WeightedQuickUnionPathCompressionUF(N);
            while(!StdIn.isEmpty()){
                int p = StdIn.readInt();
                int q = StdIn.readInt();
                if(uf.connected(p,q))
                    continue;
                uf.union(p, q);
                System.out.println(p + " "+ q);
            }
            System.out.println(uf.count() + " components");
        }
    }

