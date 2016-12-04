package cmmdc;
import javax.inject.Inject;

public class ApelCmmdc{

    @Inject
    Cmmdc obj;

    public long compute(long m,long n) {
        return obj.cmmdc(m,n);
    }
}