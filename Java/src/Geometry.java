import java.util.*;

public class Geometry {

    private static long cross(long[] O,long[] A,long[] B){
        return (A[0]-O[0])*(B[1]-O[1]) - (A[1]-O[1])*(B[0]-O[0]);
    }

    public static List<long[]> convexHull(List<long[]> pts){
        pts = new ArrayList<>(pts);
        pts.sort((a,b)-> a[0]==b[0] ? Long.compare(a[1],b[1]) : Long.compare(a[0],b[0]));
        if (pts.size()<=1) return pts;

        List<long[]> lower=new ArrayList<>(), upper=new ArrayList<>();

        for (long[] p: pts){
            while (lower.size()>=2 && cross(lower.get(lower.size()-2), lower.get(lower.size()-1), p)<=0)
                lower.remove(lower.size()-1);
            lower.add(p);
        }
        for (int i=pts.size()-1;i>=0;i--){
            long[] p=pts.get(i);
            while (upper.size()>=2 && cross(upper.get(upper.size()-2), upper.get(upper.size()-1), p)<=0)
                upper.remove(upper.size()-1);
            upper.add(p);
        }

        lower.remove(lower.size()-1);
        upper.remove(upper.size()-1);
        lower.addAll(upper);
        return lower;
    }
}
