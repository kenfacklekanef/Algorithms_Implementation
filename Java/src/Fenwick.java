public class Fenwick {
    private long[] bit;
    private int n;

    public Fenwick(int n){
        this.n=n;
        bit=new long[n+1];
    }

    public void add(int idx,long v){
        for (int i=idx+1;i<=n;i+=i&-i) bit[i]+=v;
    }

    public long sum(int idx){
        long s=0;
        for (int i=idx+1;i>0;i-=i&-i) s+=bit[i];
        return s;
    }

    public long range(int l,int r){
        return sum(r)-(l>0?sum(l-1):0);
    }
}
