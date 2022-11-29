public class Main {
    public static void main(String[] args) {
        int Degree = 2;
        double Val = 0;
        double[] Matrix={1,2,3};
        MultiMember A = new MultiMember(Matrix, Degree);
        double x = 1;

        int Degree2 =2;
        double[] Matrix2 = {4,5,6};

        MultiMember B = new MultiMember(Matrix2, Degree2);
        MultiMember C = MultiMember.MultiMemberSum(A, B);
        MultiMember D = MultiMember.MultiMemberSub(A, B);
        MultiMember E = MultiMember.MultiMemberMult(A, B);
        System.out.print("A=");
        System.out.println(A.Vivod(A));
        A.calculate(Matrix, Val, x, Degree);
        System.out.print("B=");
        System.out.println(B.Vivod(B));
        B.calculate(Matrix2, Val, x, Degree2);
        System.out.print("A+B=");
        System.out.println(C.Vivod(C) + "\n");
        System.out.print("A-B=");
        System.out.println(D.Vivod(D)+"\n");
        System.out.print("A*B=");
        System.out.println(E.Vivod(E) + "\n");
        System.out.print("Получение коэффициента по индексу 0 для многочлена А: "+ A.Matrix[0]);
    }
}
class MultiMember
{
    double[] Matrix;
    int Degree;
    public MultiMember(double[] matrix, int degree)
    {
        Matrix = matrix;
        Degree = degree;
    }

    public static MultiMember MultiMemberSum (MultiMember A, MultiMember B)
    {
        int D1 = A.Degree ;
        double[] M1=new double[D1+1] ;
        MultiMember C = new MultiMember(M1,D1);
        for (int i = 0; i < A.Degree+1; i++)
        {
            C.Matrix[i]=A.Matrix[i]+B.Matrix[i] ;
        }

        return C;
    }
    public static MultiMember MultiMemberSub (MultiMember A, MultiMember B)
    {
        int D1 = A.Degree;
        double[] M1 = new double[D1 + 1];
        MultiMember C = new MultiMember(M1, D1);
        for (int i = 0; i < A.Degree + 1; i++)
        {
            C.Matrix[i] = A.Matrix[i] - B.Matrix[i];
        }

        return C;
    }
    public static MultiMember MultiMemberMult(MultiMember A, MultiMember B)
    {
        int D1 = A.Degree;
        double[] M1 = new double[D1 + 1];
        MultiMember C = new MultiMember(M1, D1);
        for (int i = 0; i < A.Degree + 1; i++)
        {
            C.Matrix[i] = A.Matrix[i] * B.Matrix[i];
        }
        return C;
    }
    public int Vivod(MultiMember A)
    {
        for (int i=0;i<=Degree ;i++)
        {
            System.out.print(A.Matrix[i] + "X" + "^" + (Degree - i) + "+");
        }
        return 0;
    }
    public double calculate(double[] matrix, double value, double x, int Degree)
    {
        for( int i = Degree; i >0; i--)
        {
            for (int j = i; j < Degree; j++) x *= x;
            value += matrix[i-1] * x;
        }
        value+=matrix[Degree];
        System.out.println("значения многочлена для x=1: "+value+"\n");
        return value;
    }
}
